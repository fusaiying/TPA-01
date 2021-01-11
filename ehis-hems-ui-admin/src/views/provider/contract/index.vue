<template>
  <div class="app-container">
    <!-- 条件查询区域 -->
    <el-form :model="queryParams" ref="queryForm" v-show="showSearch" :inline="true" label-width="125px">

      <!-- start 第一行 -->
      <el-row>
        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="合约编号" prop="contractno">
            <el-input
              v-model="queryParams.contractno"
              placeholder="请输入合约编号"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>

        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="合约名称" prop="contractname">
            <el-input
              v-model="queryParams.contractname"
              placeholder="请输入合约名称"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>

        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="合约类型" prop="contracttype">
            <el-select v-model="queryParams.contracttype" placeholder="请选择合约类型" clearable filterable @keyup.enter.native="handleQuery">
              <el-option
                v-for="dict in contracttype"
                :key="dict.dictValue"
                :label="dict.dictValue + ' - ' + dict.dictLabel"
                :value="dict.dictValue"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <!-- 第一行 end -->

      <!-- start 第二行 -->
      <el-row>
        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="合约对象编码" prop="servcomno">
            <el-input
              v-model="queryParams.servcomno"
              placeholder="请输入合约对象编码"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>

        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="合约对象名称" prop="contractobjectname">
            <el-input clearable
                      v-model="queryParams.contractobjectname"
                      placeholder="请输入合约对象名称"
                      @keyup.enter.native="handleQuery">
            </el-input>
          </el-form-item>
        </el-col>

        <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8">
          <el-form-item label="合约期限类型" prop="contracttermtype">
            <el-select v-model="queryParams.contracttermtype" filterable placeholder="请选择合约期限类型" @keyup.enter.native="handleQuery" clearable>
              <el-option
                v-for="dict in contracttermtype"
                :key="dict.dictValue"
                :label="dict.dictValue + ' - ' +dict.dictLabel"
                :value="dict.dictValue"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
<!--        <el-col>-->
<!--          <el-date-picker-->
<!--            v-model="value"-->
<!--            type="daterange"-->
<!--            start-placeholder="开始日期"-->
<!--            end-placeholder="结束日期"-->
<!--            :default-time="['00:00:00', '23:59:59']">-->
<!--          </el-date-picker>-->
<!--        </el-col>-->
      </el-row>
      <!-- 第二行 end -->

      <!-- start 第三行 -->
      <el-row>
        <el-col :xs="36" :sm="36" :md="36" :lg="12" :xl="12">
          <el-form-item label="合约签订日期" prop="signdate">
<!--            <el-date-picker clearable-->
<!--                            v-model="queryParams.signdate"-->
<!--                            type="date"-->
<!--                            value-format="yyyy-MM-dd"-->
<!--                            placeholder="选择合约签订日期">-->
<!--            </el-date-picker>-->
<!--            <el-date-picker-->
<!--              v-model="queryParams.signdate"-->
<!--              type="datetimerange"-->
<!--              value-format="yyyy-MM-dd"-->
<!--              start-placeholder="开始日期"-->
<!--              end-placeholder="结束日期"-->
<!--              range-separator="-"-->
<!--              :default-time="['00:00:00', '00:00:00']">-->
<!--            </el-date-picker>-->
            <el-date-picker
              v-model="queryParams.signdate"
              type="daterange"
              align="right"
              unlink-panels
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              value-format="yyyy-MM-dd"
              :picker-options="pickerOptions">
            </el-date-picker>
          </el-form-item>
        </el-col>

        <el-col :xs="36" :sm="36" :md="36" :lg="12" :xl="12">
          <el-form-item label="合约生效日期">
<!--            <el-date-picker clearable-->
<!--                            v-model="queryParams.cvalidate"-->
<!--                            type="date"-->
<!--                            value-format="yyyy-MM-dd"-->
<!--                            placeholder="选择合约生效日期">-->
<!--            </el-date-picker>-->
<!--            <el-date-picker-->
<!--              v-model="queryParams.cvalidate"-->
<!--              type="datetimerange"-->
<!--              value-format="yyyy-MM-dd"-->
<!--              start-placeholder="开始日期"-->
<!--              end-placeholder="结束日期"-->
<!--              range-separator="-">-->
<!--            </el-date-picker>-->
            <el-date-picker
              v-model="queryParams.cvalidate"
              type="daterange"
              align="right"
              unlink-panels
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              value-format="yyyy-MM-dd"
              :picker-options="pickerOptions">
            </el-date-picker>
          </el-form-item>
        </el-col>

        <el-col :xs="36" :sm="36" :md="36" :lg="12" :xl="12">
          <el-form-item label="合约截止日期" prop="expirydate">
            <!--            <el-date-picker clearable-->
            <!--                            v-model="queryParams.expirydate"-->
            <!--                            type="date"-->
            <!--                            value-format="yyyy-MM-dd"-->
            <!--                            placeholder="选择合约截止日期">-->
            <!--            </el-date-picker>-->
<!--            <el-date-picker-->
<!--              v-model="queryParams.expirydate"-->
<!--              type="datetimerange"-->
<!--              value-format="yyyy-MM-dd"-->
<!--              start-placeholder="开始日期"-->
<!--              end-placeholder="结束日期"-->
<!--              range-separator="-"-->
<!--              :default-time="['00:00:00', '00:00:00']">-->
<!--            </el-date-picker>-->
            <el-date-picker
              v-model="queryParams.expirydate"
              type="daterange"
              align="right"
              unlink-panels
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              value-format="yyyy-MM-dd"
              :picker-options="pickerOptions">
            </el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>

      </el-row>
      <!-- 第三行 end -->

      <!-- 搜索重置框 -->
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-card class="box-card" shadow="hover">
      <div slot="header" class="clearfix">
        <i class="el-icon-s-grid mr5"></i>
        <span>供应商合约信息</span>
      </div>

      <!-- 新增 修改 删除 导出 按钮 -->
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="primary"
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
            v-hasPermi="['provider:contract:add']"
          >新增
          </el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="success"
            icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['provider:contract:edit']"
          >修改
          </el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="danger"
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['provider:contract:remove']"
          >删除
          </el-button>
        </el-col>
        <!--      <el-col :span="1.5">-->
        <!--        <el-button-->
        <!--          type="warning"-->
        <!--          icon="el-icon-download"-->
        <!--          size="mini"-->
        <!--          @click="handleExport"-->
        <!--          v-hasPermi="['provider:contract:export']"-->
        <!--        >导出-->
        <!--        </el-button>-->
        <!--      </el-col>-->
        <el-col :span="1.5">
          <el-button
            icon="el-icon-upload" size="mini" @click="toImport" type="info" :disabled="single"
          >合约附件
          </el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>

      <!-- 表格区域 -->
      <el-table v-loading="loading" :data="contractList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"/>
        <el-table-column label="合约编码" align="center" prop="contractno"/>
        <el-table-column label="合约名称" align="center" prop="contractname" show-overflow-tooltip/>
        <el-table-column label="合约类型" align="center" prop="contracttype" :formatter="contracttypeFormat" show-overflow-tooltip/>
        <el-table-column label="合约对象名称" align="center" prop="contractobjectname" show-overflow-tooltip/>
        <el-table-column label="合约期限类型" align="center" prop="contracttermtype" :formatter="contracttermtypeFormat" show-overflow-tooltip/>

        <el-table-column label="提前协商时间" align="center" prop="contractadvance" />

        <el-table-column label="合约签订时间" align="center" prop="signdate" >
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.signdate, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>

        <el-table-column label="合约生效日期" align="center" prop="cvalidate" >
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.cvalidate, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>

        <el-table-column label="合约截止日期" align="center" prop="expirydate" >
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.expirydate, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>

        <el-table-column label="合约备注" align="center" prop="remark" />

        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right" width="120px">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['provider:contract:edit']"
            >修改
            </el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['provider:contract:remove']"
            >删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
    </el-card>

    <!-- 添加或修改供应商管理对话框 -->
<!--    <el-dialog :title="title" :visible.sync="open" width="1400px" append-to-body>-->
<!--      <el-form ref="form" :model="form" :rules="rules" label-width="125px">-->
<!--        &lt;!&ndash; 第一行&ndash;&gt;-->
<!--        <el-row>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="合约ID">-->
<!--              <el-input v-model="form.contractno" disabled clearable placeholder="合约ID自动生成"/>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="合约编码">-->
<!--              <el-input v-model="form.contractversion" placeholder="请输入合约编码" clearable/>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="合约名称" prop="contractname">-->
<!--              <el-input v-model="form.contractname" clearable placeholder="请输入合约名称"/>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--        </el-row>-->
<!--        &lt;!&ndash; 第二行 &ndash;&gt;-->
<!--        <el-row>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="合约类型" prop="contracttype">-->
<!--              <el-select v-model="form.contracttype" placeholder="请选择合约类型" clearable style="width: 100%">-->
<!--                <el-option-->
<!--                  v-for="dict in contracttype"-->
<!--                  :key="dict.dictValue"-->
<!--                  :label="dict.dictValue + ' - ' +dict.dictLabel"-->
<!--                  :value="dict.dictValue"-->
<!--                ></el-option>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="合约对象编码" prop="servcomno">-->
<!--              <el-select-->
<!--                v-model="form.servcomno"-->
<!--                placeholder="请选择合约对象编码"-->
<!--                clearable-->
<!--                @change="getContractObjectName"-->
<!--                style="width: 100%">-->
<!--                <el-option-->
<!--                  v-for="dict in  contractObj"-->
<!--                  :key="dict.servcomno"-->
<!--                  :label="dict.servcomno + ' - ' +dict.chname"-->
<!--                  :value="dict.servcomno"-->
<!--                ></el-option>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="合约签订日期" prop="signdate">-->
<!--              <el-date-picker clearable-->
<!--                              v-model="form.signdate"-->
<!--                              type="date"-->
<!--                              value-format="yyyy-MM-dd"-->
<!--                              placeholder="请选择合约签订日期"-->
<!--                              style="width: 100%;">-->
<!--              </el-date-picker>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--        </el-row>-->
<!--        &lt;!&ndash; 第三行 &ndash;&gt;-->
<!--        <el-row>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="提前协商时间" prop="contractadvance">-->
<!--              <el-input v-model="form.contractadvance" placeholder="请输入提前协商时间" clearable/>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="合约期限类型" prop="contracttermtype">-->
<!--              <el-select-->
<!--                v-model="form.contracttermtype"-->
<!--                placeholder="请选择合约期限类型"-->
<!--                clearable-->
<!--                @change="isshow"-->
<!--                style="width: 100%">-->
<!--                <el-option-->
<!--                  v-for="dict in contracttermtype"-->
<!--                  :key="dict.dictValue"-->
<!--                  :label="dict.dictValue + ' - ' +dict.dictLabel"-->
<!--                  :value="dict.dictValue"-->
<!--                ></el-option>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="合约有效期" prop="cvalidate" v-show="show1">-->
<!--              <el-date-picker-->
<!--                v-model="form.cvalidate"-->
<!--                type="date"-->
<!--                style="width: 100%;"-->
<!--                placeholder="请选择合约有效期">-->
<!--              </el-date-picker>-->
<!--            </el-form-item>-->
<!--            <el-form-item label="合约有效期" prop="cvalidate" v-show="show2">-->
<!--              <el-date-picker-->
<!--                v-model="form.cvalidate"-->
<!--                type="daterange"-->
<!--                align="right"-->
<!--                unlink-panels-->
<!--                range-separator="至"-->
<!--                start-placeholder="开始日期"-->
<!--                end-placeholder="结束日期"-->
<!--                value-format="yyyy-MM-dd"-->
<!--                :picker-options="pickerOptions">-->
<!--              </el-date-picker>-->
<!--            </el-form-item>-->
<!--          </el-col>-->

<!--&lt;!&ndash;          <el-col :span="8">&ndash;&gt;-->
<!--&lt;!&ndash;            <el-form-item label="合约有效期" prop="expirydate" v-show="show2">&ndash;&gt;-->
<!--&lt;!&ndash;              <el-date-picker&ndash;&gt;-->
<!--&lt;!&ndash;                clearable&ndash;&gt;-->
<!--&lt;!&ndash;                v-model="form.expirydate"&ndash;&gt;-->
<!--&lt;!&ndash;                type="datetimerange"&ndash;&gt;-->
<!--&lt;!&ndash;                value-format="yyyy-MM-dd"&ndash;&gt;-->
<!--&lt;!&ndash;                style="width: 100%"&ndash;&gt;-->
<!--&lt;!&ndash;                start-placeholder="开始日期"&ndash;&gt;-->
<!--&lt;!&ndash;                end-placeholder="结束日期"&ndash;&gt;-->
<!--&lt;!&ndash;                range-separator="-"&ndash;&gt;-->
<!--&lt;!&ndash;                :default-time="['00:00:00', '00:00:00']"&ndash;&gt;-->
<!--&lt;!&ndash;              >&ndash;&gt;-->
<!--&lt;!&ndash;              </el-date-picker>&ndash;&gt;-->
<!--&lt;!&ndash;            </el-form-item>&ndash;&gt;-->
<!--&lt;!&ndash;          </el-col>&ndash;&gt;-->
<!--        </el-row>-->
<!--        &lt;!&ndash; 第四行 &ndash;&gt;-->
<!--        <el-row>-->

<!--          <el-col :span="8">-->
<!--            <el-form-item label="合同分类">-->
<!--              <el-select v-model="form.contractsort"-->
<!--                         placeholder="请选择合同分类"-->
<!--                         clearable-->
<!--                         style="width: 100%">-->
<!--                <el-option-->
<!--                  v-for="dict in contclassifyOptions"-->
<!--                  :key="dict.dictValue"-->
<!--                  :label="dict.dictValue + ' - ' +dict.dictLabel"-->
<!--                  :value="dict.dictValue"-->
<!--                ></el-option>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--        </el-row>-->
<!--        &lt;!&ndash; 第五行 &ndash;&gt;-->
<!--        <el-row>-->
<!--          <el-col :span="24">-->
<!--            <el-form-item label="合约备注" prop="remark">-->
<!--              <el-input-->
<!--                type="textarea"-->
<!--                v-model="form.remark"-->
<!--                clearable-->
<!--                placeholde="包括：权责、联系人、价格、约定服务流程等">-->
<!--              </el-input>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--        </el-row>-->
<!--        &lt;!&ndash;供应商服务项目卡单&ndash;&gt;-->
<!--        <el-row>-->
<!--          <el-card class="box-card" shadow="hover">-->
<!--            <div slot="header" class="clearfix">-->
<!--              <i class="el-icon-s-cooperation mr5"></i>-->
<!--              <span>供应商服务项目</span>-->
<!--            </div>-->
<!--            <el-table :data="serviceInfo" highlight-current-row>-->
<!--              <el-table-column type="index" width="50" align="center">-->
<!--              </el-table-column>-->
<!--              <el-table-column label="服务项目名称" align="center">-->
<!--                <template slot-scope="scope">-->
<!--                  <el-select-->
<!--                    v-model="scope.row.serviceprojName"-->
<!--                    v-show="scope.row.show"-->
<!--                    placeholder="服务项目名称"-->
<!--                    clearable-->
<!--                  >-->
<!--                    <el-option-->
<!--                      v-for="dict in  contractObj"-->
<!--                      :key="dict.servcomno"-->
<!--                      :label="dict.servcomno + ' - ' +dict.chname"-->
<!--                      :value="dict.servcomno"-->
<!--                    ></el-option>-->
<!--                  </el-select>-->
<!--                  <span v-show="!scope.row.show">{{scope.row.serviceprojName}}</span>-->
<!--                </template>-->
<!--              </el-table-column>-->

<!--              <el-table-column label="结算方式" align="center">-->
<!--                <template slot-scope="scope">-->
<!--                  <el-select-->
<!--                    v-model="scope.row.clearingType"-->
<!--                    v-show="scope.row.show"-->
<!--                    placeholder="结算方式"-->
<!--                    clearable>-->
<!--                    <el-option-->
<!--                      v-for="dict in supsettlementtype"-->
<!--                      :key="dict.dictValue"-->
<!--                      :label="dict.dictValue + ' - ' +dict.dictLabel"-->
<!--                      :value="dict.dictValue"-->
<!--                    ></el-option>-->
<!--                  </el-select>-->
<!--                  <span v-show="!scope.row.show">{{scope.row.clearingType}}</span>-->
<!--                </template>-->
<!--              </el-table-column>-->

<!--              <el-table-column label="合约价格" align="center">-->
<!--              <template slot-scope="scope">-->
<!--                <el-input v-model="scope.row.price"-->
<!--                          v-show="scope.row.show"-->
<!--                          placeholder="合约价格"-->
<!--                          clearable-->
<!--                          size="mini"-->
<!--                />-->
<!--                <span v-show="!scope.row.show">{{scope.row.price}}</span>-->
<!--              </template>-->
<!--            </el-table-column>-->

<!--              <el-table-column label="备注" align="center">-->
<!--              <template slot-scope="scope">-->
<!--                <el-input v-model="scope.row.serviceprojRemark"-->
<!--                          v-show="scope.row.show"-->
<!--                          placeholder="备注信息"-->
<!--                          clearable-->
<!--                          size="mini"-->
<!--                />-->
<!--                <span v-show="!scope.row.show">{{scope.row.serviceprojRemark}}</span>-->
<!--              </template>-->
<!--            </el-table-column>-->
<!--              <el-table-column label="操作" align="center" width="120px" class-name="small-padding fixed-width"-->
<!--                               fixed="right">-->
<!--                <template slot-scope="scope">-->
<!--                  <el-button-->
<!--                    size="mini"-->
<!--                    type="text"-->
<!--                    icon="el-icon-s-claim"-->
<!--                    v-show="scope.row.show"-->
<!--                    @click="scope.row.show=false"-->
<!--                    v-hasPermi="['system:information:remove']"-->
<!--                  >保存-->
<!--                  </el-button>-->
<!--                  <el-button-->
<!--                    size="mini"-->
<!--                    type="text"-->
<!--                    icon="el-icon-edit"-->
<!--                    v-show="!scope.row.show"-->
<!--                    @click="scope.row.show=true"-->
<!--                    v-hasPermi="['system:information:remove']"-->
<!--                  >编辑-->
<!--                  </el-button>-->
<!--                  <el-button-->
<!--                    size="mini"-->
<!--                    type="text"-->
<!--                    icon="el-icon-delete"-->
<!--                    @click="deleteServiceProjListOneRow(scope.row)"-->
<!--                    v-hasPermi="['system:information:remove']"-->
<!--                  >删除-->
<!--                  </el-button>-->
<!--                </template>-->
<!--              </el-table-column>-->
<!--            </el-table>-->
<!--            <el-row :gutter="10" class="mb8">-->
<!--              <el-col :span="24">-->
<!--                <el-button-->
<!--                  style="width: 100%; margin-top: 3px;"-->
<!--                  icon="el-icon-plus"-->
<!--                  size="mini"-->
<!--                  @click="addServiceProjListOneRow"-->
<!--                  v-hasPermi="['system:definition:add']"-->
<!--                >新增-->
<!--                </el-button>-->
<!--              </el-col>-->
<!--            </el-row>-->
<!--          </el-card>-->
<!--        </el-row>-->
<!--        &lt;!&ndash;合同附件信息卡单&ndash;&gt;-->
<!--        <el-row>-->
<!--          <el-card class="box-card" shadow="hover">-->
<!--            <div slot="header" class="clearfix">-->
<!--              <el-row>-->
<!--                <el-col :span="12">-->
<!--              <i class="el-icon-folder mr5"></i>-->
<!--                  <span>合同附件信息</span>-->
<!--                </el-col>-->
<!--                <el-col :span="12" style="text-align: right">-->
<!--                  <el-button-->
<!--                    style=""-->
<!--                    type="success"-->
<!--                    icon="el-icon-download"-->
<!--                    size="mini"-->
<!--                    @click=""-->
<!--                    v-hasPermi="['system:definition:add']"-->
<!--                  >下载附件-->
<!--                  </el-button>-->
<!--                  <el-button-->
<!--                    style=""-->
<!--                    type="danger"-->
<!--                    icon="el-icon-delete"-->
<!--                    size="mini"-->
<!--                    @click=""-->
<!--                    v-hasPermi="['system:definition:add']"-->
<!--                  >删除附件-->
<!--                  </el-button>-->
<!--                </el-col>-->
<!--              </el-row>-->
<!--              <el-row>-->
<!--                <el-col>-->
<!--                  <el-button-->
<!--                    style=""-->
<!--                    type="primary"-->
<!--                    icon="el-icon-upload2"-->
<!--                    size="mini"-->
<!--                    @click=""-->
<!--                    v-hasPermi="['system:definition:add']"-->
<!--                  >附件上传-->
<!--                  </el-button>-->
<!--                  <el-tag type="info">支持扩展名：.rar .zip .doc .docx .pdf .jpg .png</el-tag>-->
<!--                </el-col>-->
<!--              </el-row>-->
<!--            </div>-->
<!--            <el-table v-loading="loading" :data="tableData" @selection-change="handleSelectionChange">-->
<!--              <el-table-column type="selection" width="55" align="center"/>-->
<!--              <el-table-column type="index" label="序号"  align="center" show-overflow-tooltip width="50px"/>-->
<!--              <el-table-column label="合约名称" align="center" prop="fileName" show-overflow-tooltip/>-->
<!--            </el-table>-->
<!--          </el-card>-->
<!--        </el-row>-->
<!--      </el-form>-->
<!--      &lt;!&ndash; 添加或修改按钮提示框中确定和取消 &ndash;&gt;-->
<!--      <div slot="footer" class="dialog-footer">-->
<!--        <el-button type="primary" size="mini" @click="iscvalidate">确 定</el-button>-->
<!--        <el-button @click="cancel" size="mini">取 消</el-button>-->
<!--      </div>-->
<!--    </el-dialog>-->

  </div>
</template>

<script>
  import {listContract, getContract, delContract, addContract, updateContract, getContractObj, getExpireContract} from "@/api/provider/contract";

  export default {
    name: "contract",
    data() {
      return {
        // 模拟表格数据
        tableData: [
          {
            fileCode: 'F000006015',
            fileName: '9.csv',
            uploadDate: '2018-04-26',
            uploadTime: '16:23:51',
            uploader: 'admin',
            fileDesc: 'csv文件'
          },
          {
            fileCode: 'F000006014',
            fileName: 'test0426.txt',
            uploadDate: '2018-04-26',
            uploadTime: '16:23:51',
            uploader: 'admin',
            fileDesc: 'txt文件'
          },
          {
            fileCode: 'F000006013',
            fileName: '文件下载.png',
            uploadDate: '2018-04-26',
            uploadTime: '18:59:27',
            uploader: 'admin',
            fileDesc: 'png文件'
          },
          {
            fileCode: 'F000006012',
            fileName: '订单打印.txt',
            uploadDate: '2018-04-26',
            uploadTime: '18:59:27',
            uploader: 'admin',
            fileDesc: 'txt文件'
          },
        ],
        //截止日期的显示与隐藏
        show1:false,
        show2:false,
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 供应商合约表格数据
        contractList: [],
        // 服务项目数据
        serviceInfo: [],
        //
        supsettlementtype:[],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          contractno: null,
          contractname: null,
          contracttype: null,
          servcomno: null,
          contractadvance: null,
          contracttermtype: null,
          contractobjectname: null,
          signdate: null,
          cvalidate: null,
          expirydate: null,
          contclassify: null,
          // 新增
          signdatebegin: null,
          signdateend: null,
          cvalidatebegin: null,
          cvalidateend: null,
          expirydatebegin: null,
          expirydateend: null,
        },
        // 表单参数
        form: {},
        // 合约对象数据
        contractObj: [],
        // 合约类型 下拉
        // 合约对象编码查询返回数据
        servcomnoOpions:[],
        // 合约类型
        contracttype: [],
        // 合约期限类型
        contracttermtype: [],
        // 合同分类
        contclassifyOptions: [],
        // 表单校验
        rules: {
          contractname: [
            {required: true, message: "合约名称不能为空", trigger: "blur"}
          ],
          contracttype: [
            {required: true, message: "合约类型不能为空", trigger: "change"}
          ],
          servcomno: [
            {required: true, message: "合约对象编码", trigger: "blur"}
          ],
          contracttermtype: [
            {required: true, message: '合约期限类型不能为空', trigger: 'change'}
          ],
          cvalidate: [
            {required: true, message: "合约生效日期不能为空", trigger: "change"}
          ],
          expirydate: [
            {required: true, message: "合约有效期不能为空",trigger: "change"}
          ],
        },

        //  日期插件 自带函数
        pickerOptions: {
          shortcuts: [{
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
      };
    },
    created() {
      /** 加载合约信息列表 */
      this.getList();
      /** 检测即将到期合约 */
      this.queryExpireContract();
      /** 合约类型数据字典 */
      this.getDicts("contract_contracttype").then(response => {
        this.contracttype = response.data;
      });
      /** 合约期限类型数据字典 */
      this.getDicts("contract_contracttermtype").then(response => {
        this.contracttermtype = response.data;
      });
      /** 合同分类数据字典 contract */
      this.getDicts("contract_type").then(response => {
        this.contclassifyOptions = response.data;
      });
      // 结算方式下拉框数据
      this.getDicts("contract_payway").then(response => {
        this.supsettlementtype = response.data;
      });

    },
    methods: {
      /** 查询供应商合约列表 */
      getList() {
        this.loading = true;
        // 合约签订日期
        const signdate = this.queryParams.signdate;
        if(signdate != null) {
          this.queryParams.signdatebegin = signdate[0];
          this.queryParams.signdateend = signdate[1];
        }
        this.queryParams.signdate = '';

        // 合约截止日期
        const expirydate = this.queryParams.expirydate;
        if(expirydate != null) {
          this.queryParams.expirydatebegin = expirydate[0];
          this.queryParams.expirydateend = expirydate[1];
        }
        this.queryParams.expirydate = '';

        // 合约生效日期
        const cvalidate = this.queryParams.cvalidate;
        if(cvalidate != null) {
          this.queryParams.cvalidatebegin = cvalidate[0];
          this.queryParams.cvalidateend = cvalidate[1];
        }
        this.queryParams.cvalidate = '';

        listContract(this.queryParams).then(response => {
          this.contractList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      /** 查询到期合约 */
      queryExpireContract() {
        getExpireContract().then(response => {
          for (let i = 0; i <response.data.length ; i++) {
             this.$notify({
              title: '信息提示',
              type: 'info',
              dangerouslyUseHTMLString: true,
              message: '合约即将失效,请确认!即将失效合约为:'+'</br>'+'合约编码：'+ response.data[i].contractno + '</br>' +'合约名称：'+response.data[i].contractname,
              duration: 0,
              offset: 40
            });
          }
        });
      },

      // 新增一行供应商服务项目
      addServiceProjListOneRow() {
        const row = {
          serviceprojName: '',
          clearingType: '',
          price:'',
          serviceprojRemark:'',
          show: true
        };
        this.serviceInfo.push(row);
      },
      //删除一行
      deleteServiceProjListOneRow(index) {
        this.$confirm('是否确认删除数据?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.serviceInfo.splice(index, 1);
        }).then(() => {
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
      },
      /** 合约对象名称 */
      getContractObjectName() {
        if(this.contractObj != null){
          for (let i = 0;i<this.contractObj.length;i++){
            if (this.contractObj[i].servcomno == this.form.servcomno){
              this.form.contractobjectname = this.contractObj[i].chname;
            }
          }
        }
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.show = false;
        this.reset();
      },
      /** 表单重置 */
      reset() {
        this.form = {
          contractno: null,
          contractname: null,
          contracttype: null,
          servcomno: null,
          contractobjectname: null,
          signdate: null,
          constate: null,
          contracttermtype: null,
          cvalidate: null,
          expirydate: null,
          contractadvance: null,
          contractversion: null,
          contractsort: null,
          remark: null,
          alternatefield1: null,
          alternatefield2: null,
          operator: null,
          makedate: null,
          maketime: null,
          modifyoperator: null,
          modifydate: null,
          modifytime: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      /** 多选框选中数据 */
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.contractno)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.$router.push({path: '/provider/contract/add'});
        // // 将新增弹框重置
        // this.reset();
        // this.show = false;
        // // 将弹框显示
        // this.open = true;
        // // 设置弹框标题
        // this.title = "添加-供应商合约";
        // // 获取合约对象 - 下拉框数据
        // getContractObj().then(response => {
        //   this.contractObj = response.data;
        // });
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        const contractno = row.contractno || this.ids[0];
        this.$router.push('/provider/contract/edit/' + contractno);
        // this.reset();
        // this.open = true;
        // if(row.contracttermtype == "01"){
        //   this.show = true;
        // }else{
        //   this.show = false;
        // }
        // this.title = "修改-供应商合约";
        // const contractno = row.contractno || this.ids
        // // 获取合约对象编码编码下拉列表
        // getContractObj().then(response => {
        //   this.contractObj=response.data;
        // });
        // // 用contractno查询对应的信息
        // getContract(contractno).then(response => {
        //   this.form = response.data;
        // });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.contractno != null) {
              updateContract(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                }
              });
            } else {
              addContract(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("新增成功");
                  this.open = false;
                  this.getList();
                }
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const contractnos = row.contractno || this.ids;
        // 编号换行
        var contractStr = '<br/>';
        for (var i = 0; i < contractnos.length; i++) {
          contractStr += contractnos[i] + "<br/>";
        }
        this.$confirm('是否确认删除供应商合约编号为"' + contractStr + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
          dangerouslyUseHTMLString: true,
        }).then(function () {
          return delContract(contractnos);
        }).then(() => {
        if(this.contractList.length == 1 && this.queryParams.pageNum != 1){
            this.queryParams.pageNum = this.queryParams.pageNum - 1;
          }
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('provider/contract/export', {
          ...this.queryParams
        }, `provider_contract.xlsx`)
      },

      /** 合约附件 */
      toImport(row) {
        const contractno = row.contractno || this.ids[0];
        this.$router.push({path: '/provider/contract/attachment/' + contractno});
      },

      /** 是否显示合约截至日期选择栏 */
      isshow() {
        if (this.form.contracttermtype === "01"){
          this.show1 = false;
          this.show2 = true;
        }else if(this.form.contracttermtype === "02"){
          this.show2 = false;
          this.show1 = true;
        }
      },
      /** 对合约生效时间进行校验 */
      iscvalidate() {
       if(this.form.signdate != null ){
         if (this.form.signdate > this.form.cvalidate) {
           this.$confirm('当前[合约签订日期]小于[合约生效日期]，请确认！','提示',{
             confirmButtonText: '确定',
             cancelButtonText: '取消',
             type: 'warning'
           }).then(() => {
             // 调用submitForm提交方法
             this.submitForm();
           }).catch(() => {
             return;
           })
         }else{
           // 调用submitForm提交方法
           this.submitForm();
         }
       }
      },
      /** 对合约烈性列值进行处理 */
      contracttypeFormat(row) {
        for (let i = 0; i <this.contracttype.length ; i++) {
          if (row.contracttype === this.contracttype[i].dictValue) {
            return this.contracttype[i].dictLabel
          }
        }
      },
      /** 对合约期限类型列值进行处理 */
      contracttermtypeFormat(row) {
        for (let i = 0; i <this.contracttermtype.length ; i++) {
          if (row.contracttermtype === this.contracttermtype[i].dictValue) {
            return this.contracttermtype[i].dictLabel
          }
        }
      },

    }
  };
</script>
