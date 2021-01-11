package com.paic.ehis.common.core.maxno;

import com.paic.ehis.common.core.utils.PubFun;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.incrementer.AbstractColumnMaxValueIncrementer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 此类为重写的Spring MySQL编码自增类，主要用于SysMaxNo的编码自增
 * 作者；李碧辉
 * 时间：2020-10-29
 */
public class MySQLSysMaxNoIncrementer extends AbstractColumnMaxValueIncrementer {
    private static final String VALUE_SQL = "select last_insert_id()";
    private long nextId = 0L;
    private long maxId = 0L;
    private boolean useNewConnection = true;

    public MySQLSysMaxNoIncrementer() {
    }

    public MySQLSysMaxNoIncrementer(DataSource dataSource, String noType) {
        super(dataSource, "sys_maxno", noType);
    }

    public void setUseNewConnection(boolean useNewConnection) {
        this.useNewConnection = useNewConnection;
    }

    protected synchronized long getNextKey() throws DataAccessException {
        if (this.maxId == this.nextId) {
            Connection con = null;
            Statement stmt = null;
            boolean mustRestoreAutoCommit = false;

            try {
                /*
                * 如果useNewConnection为true, 获取一个未被管理的连接，让我们的操作在一个独立的事务中
                * 如果useNewConnection为false，使用当前事务的连接, 但依赖发号表是无事务的表.
                * 需要直接使用JDBC风格的代码，因为我们需要确保insert操作和select操作是在一个connection下进行的 (不然的话我们无法保证last_insert_id()返回正确的值)
                */
                if (this.useNewConnection) {
                    con = this.getDataSource().getConnection();
                    if (con.getAutoCommit()) {
                        mustRestoreAutoCommit = true;
                        con.setAutoCommit(false);
                    }
                } else {
                    con = DataSourceUtils.getConnection(this.getDataSource());
                }

                String columnName = this.getColumnName();

                //查询数据库是否有这个类型的码值
                StringBuffer tSBql = new StringBuffer(256);
                tSBql.append("select maxno from sys_maxno where notype='");
                tSBql.append(columnName);
                tSBql.append("' and nolimit='SN' for update");
                String result = null;
                result = PubFun.getOneValue(con,tSBql.toString());
                if (result != null) {
                    //没有则插入数据库一条数据，数据大小为缓存大小
                    if (result.equals("")) {
                        tSBql = new StringBuffer(256);
                        tSBql.append("insert into sys_maxno(notype, nolimit, maxno) values('");
                        tSBql.append(columnName);
                        tSBql.append("', 'SN', ");
                        tSBql.append(this.getCacheSize());
                        tSBql.append(")");
                        if (!PubFun.execUpdateSQL(con, tSBql.toString())) {
                            con.rollback();
                            con.close();
                            return 0;
                        }
                        this.maxId = this.getCacheSize();
                    }else{
                        //数据库有则更新最大值，并缓存下来
                        stmt = con.createStatement();
                        if (!this.useNewConnection) {
                            DataSourceUtils.applyTransactionTimeout(stmt, this.getDataSource());
                        }

                        try {
                            stmt.executeUpdate("update sys_maxno set maxno = last_insert_id(maxno + " + this.getCacheSize() + ") where notype = '"+columnName+"'");
                        } catch (SQLException var20) {
                            throw new DataAccessResourceFailureException("Could not increment " + columnName + " for " + this.getIncrementerName() + " sequence table", var20);
                        }

                        ResultSet rs = stmt.executeQuery(VALUE_SQL);

                        try {
                            if (!rs.next()) {
                                throw new DataAccessResourceFailureException("last_insert_id() failed after executing an update");
                            }

                            this.maxId = rs.getLong(1);
                        } finally {
                            JdbcUtils.closeResultSet(rs);
                        }
                    }
                } else {
                    con.rollback();
                    return 0;
                }
                this.nextId = this.maxId - (long)this.getCacheSize() + 1L;
            } catch (SQLException var23) {
                throw new DataAccessResourceFailureException("Could not obtain last_insert_id()", var23);
            } finally {
                JdbcUtils.closeStatement(stmt);
                if (con != null) {
                    if (this.useNewConnection) {
                        try {
                            con.commit();
                            if (mustRestoreAutoCommit) {
                                con.setAutoCommit(true);
                            }
                        } catch (SQLException var21) {
                            throw new DataAccessResourceFailureException("Unable to commit new sequence value changes for " + this.getIncrementerName());
                        }

                        JdbcUtils.closeConnection(con);
                    } else {
                        DataSourceUtils.releaseConnection(con, this.getDataSource());
                    }
                }

            }
        } else {
            ++this.nextId;
        }

        return this.nextId;
    }
}