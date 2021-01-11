<template>
  <el-table
    v-loading="loading"
    :data="tableData"
    :header-cell-style="{color:'black',background:'#f8f8ff'}"
    size="small"
    highlight-current-row
    tooltip-effect="dark"
    style="width: 100%;">
    <el-table-column align="center" width="55" label="" prop="id">
      <template slot-scope="scope">
        <el-radio v-model="getChooseRadio" :label="scope.row.id" @change.native="handleCurrentChange(scope.row, scope.$index)">{{ &nbsp; }}</el-radio>
      </template>
    </el-table-column>
    <el-table-column v-if="status==='01'" align="center" prop="insurancename" label="险种" show-overflow-tooltip/>
    <el-table-column v-if="status==='01'" align="center" prop="dutyname" label="责任" show-overflow-tooltip/>
    <el-table-column v-if="status==='01'" :formatter="queryConclusion" align="center" prop="circulationvalue1" label="赔付结论" show-overflow-tooltip/>
    <el-table-column v-if="status==='01'" :formatter="queryConclusionreason" align="center" prop="circulationvalue2" label="赔付结论依据" show-overflow-tooltip/>
    <el-table-column v-if="status==='02'" :formatter="querySupplementarytype" align="center" prop="circulationvalue1" label="补材类型" show-overflow-tooltip/>
<!--    <el-table-column v-if="status==='02'" :formatter="queryImagetype" align="center" prop="circulationvalue2" label="材料类型" show-overflow-tooltip/>-->
<!--    <el-table-column v-if="status==='02'" :formatter="queryImagetype" align="center" prop="circulationvalue2" label="材料类型" show-overflow-tooltip/>-->
    <el-table-column v-if="status==='03'" align="center" prop="withdrawnReason" label="撤案原因" show-overflow-tooltip/>
    <el-table-column v-else-if="status==='04'" :formatter="queryAcceptreason" align="center" prop="circulationvalue1" label="不予受理原因" show-overflow-tooltip/>
    <el-table-column v-if="status==='02'" :formatter="formatSupplementarymode" align="center" prop="supplementarymode" label="推送方式" show-overflow-tooltip/>
    <el-table-column :formatter="formatCompensationtype" v-if="status==='01'" align="center" prop="circulationvalue3" label="拒付类型" show-overflow-tooltip/>
    <el-table-column v-else-if="status==='02'" :formatter="queryTemplateType" align="center" prop="circulationvalue3" label="补材业务类型" show-overflow-tooltip/>
<!--    <el-table-column v-if="status==='04'" :formatter="queryInadmissibilityType" align="center" prop="circulationvalue2" label="不予受理类型" show-overflow-tooltip/>-->
    <el-table-column align="center" prop="contents" label="话术模板" show-overflow-tooltip/>
    <el-table-column width="150px" align="center" prop="operatedate" label="最近一次修改时间"></el-table-column>
    <el-table-column align="center" prop="operator" label="操作人" show-overflow-tooltip/>
    <el-table-column align="center" label="操作">
      <template slot-scope="scope">
        <el-button size="mini" type="text" @click="delHandle(scope.row, 'customer')">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
    export default {
        dicts: ['conclusion', 'supplementmode', 'compensationtype', 'supplementarytype', 'supplementary_material_business_type', 'acceptreason',
            'inadmissibility_type', 'conclusionreason', 'conclusionreason2', 'conclusionreason3', 'conclusionreason4', 'image_type'],
        props: {
            tableData: {
                type: Array,
                default: function() {
                    return []
                }
            },
            chooseRadio: {
              type: Number,
              default: function() {
                  return null
              }
            },
            status: String,
            loading: {
                type: Boolean,
                default: function() {
                    return false
                }
            }
        },
        // watch: {
        //     chooseRadio(newVal) {
        //         console.log(this.chooseRadio)
        //         this.getChooseRadio = newVal
        //     }
        // },
        computed: {
            getChooseRadio: {
                set() {},
                get() {
                    return this.chooseRadio
                }
            }
        },
        data() {
            return {
                // getChooseRadio: 0
            }
        },
        mounted() {
            console.log(this.tableData)
        },
        methods: {
            // 处理跳转
            delHandle(row, status) {
                this.$emit('delHandle', row)
            },
            handleCurrentChange(row, index) {
                this.$emit('handleCurrentChange', { row: row, index: index})
            },
            // 赔付结论
            queryConclusion(row) {
                return this.dict.label.conclusion[row.circulationvalue1]
            },
            // 理赔依据
            queryConclusionreason(row) {
                switch(row.circulationvalue1) {
                    case '1' : return this.dict.label.conclusionreason[row.circulationvalue2]; break
                    case '2' : return this.dict.label.conclusionreason[row.circulationvalue2]; break
                    case '3' : return this.dict.label.conclusionreason2[row.circulationvalue2]; break
                    case '4' : return this.dict.label.conclusionreason3[row.circulationvalue2]; break
                    case '5' : return this.dict.label.conclusionreason4[row.circulationvalue2]; break
                    default : return []
                }
            },
            // 推送方式
            formatSupplementarymode(row) {
                let reason = ''
                if (row.supplementarymode) {
                    row.supplementarymode.split(',').map((item, i) => {
                        this.dict.label.supplementmode[item] ? reason += `|${this.dict.label.supplementmode[item]}` : reason = ''
                    })
                }
                return reason.slice(1)
            },
            // 赔付结论类型
            formatCompensationtype(row) {
                let reason = ''
                if (row.circulationvalue3) {
                    row.circulationvalue3.split(',').map((item, i) => {
                        this.dict.label.compensationtype[item] ? reason += `|${this.dict.label.compensationtype[item]}` : reason = ''
                    })
                }
                return reason.slice(1)
            },
            // 材料类型
            queryImagetype(row) {
                let reason = ''
                if (row.circulationvalue2) {
                    row.circulationvalue2.split(',').map((item, i) => {
                        this.dict.label.image_type[item] ? reason += `|${this.dict.label.image_type[item]}` : reason = ''
                    })
                }
                return reason.slice(1)
            },
            // 补材类型
            querySupplementarytype(row) {
                return this.dict.label.supplementarytype[row.circulationvalue1]
            },
            // 补材业务类型
            queryTemplateType(row) {
                return this.dict.label.supplementary_material_business_type[row.circulationvalue3]
            },
            // 不予受理原因
            queryAcceptreason(row) {
                return this.dict.label.acceptreason[row.circulationvalue1]
            },
            // 不予受理类型
            queryInadmissibilityType(row) {
                return this.dict.label.inadmissibility_type[row.circulationvalue2]
            }
        }
    }
</script>
