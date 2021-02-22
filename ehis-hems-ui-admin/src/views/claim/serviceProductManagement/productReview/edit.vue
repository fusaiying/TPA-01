<template>
  <div class="app-container">
    <!--产品基本信息-->
    <div>
      <product-info ref="productInfoForm" :parProductChname="parProductChname" :parProductEnname="parProductEnname"
                    :disableFlag="disabledFlag" :status="status"
                    :parOutProductChname="parOutProductChname" :parOutProductEnname="parOutProductEnname"
                    :productCode="productCode"
      ></product-info>
    </div>
    <!--服务项目-->
    <div>
      <services-available ref="servicesAvailableForm" :productCode="productCode" :disabledFlag="disabledFlag"
                          :status="status"></services-available>
    </div>
    <!--供应商信息-->
    <div>
      <el-form ref="supplierInfo" :model="supplierInfo" size="small" disabled>
        <el-card class="box-card " style="margin-top: 10px;">
          <div slot="header" class="clearfix">
            <span>供应商信息</span>
          </div>
          <el-table ref="medicalRecordTable"
                    :data="supplierInfo.supplierData"
                    :header-cell-style="{color:'black',background:'#f8f8ff'}"
                    size="small"
                    highlight-current-row
                    tooltip-effect="dark"
                    style="width: 100%;">

            <!--            <el-table-column align="center" min-width="50" type="selection" width="120px"  ></el-table-column>-->
            <el-table-column key="1" align="center" prop="supplierCode" min-width="150" label="供应商编码"
                             show-overflow-tooltip/>
            <el-table-column key="2" align="center" min-width="100" prop="chname" label="供应商名称" show-overflow-tooltip/>
            <el-table-column key="3" align="center" prop="addressdetail" min-width="150" label="适用区域"
                             show-overflow-tooltip/>
            <el-table-column key="4" align="center" min-width="100" prop="endDate" label="合约止期" show-overflow-tooltip/>
            <el-table-column key="5" align="center" min-width="150" label="服务价格" show-overflow-tooltip>
              <template slot-scope="scope">
                <span>{{ scope.row.minPrice }}-{{ scope.row.maxPrice }}</span>
              </template>
            </el-table-column>
            <el-table-column key="6" align="center" prop="priority" label="优先级次序" min-width="120" show-overflow-tooltip>
              <template slot-scope="scope">
                <span>{{ scope.row.priority }}</span>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-form>
    </div>

    <!--服务手册列表信息-->
    <div>
      <service-manual-list :productCode="productCode" :disabledFlag="disabledFlag"
                           :status="status"></service-manual-list>
    </div>

    <!--审核日志-->

    <audit :productCode="productCode" :status="status"></audit>


  </div>

</template>

<script>
import {selectProductSupplier} from '@/api/productManage/serviceProductManagement'
import productInfo from './../components/productInfo'
import servicesAvailable from './../components/servicesAvailable'
import serviceManualList from './../components/serviceManualList'
import audit from './../components/audit'


export default {
  components: {
    productInfo,
    servicesAvailable,
    serviceManualList,
    audit
  },
  data() {
    return {
      status: '',
      disabledFlag: false,
      parProductChname: '',
      parProductEnname: '',
      parOutProductChname: '',
      parOutProductEnname: '',
      productCode: '',
      supplierInfo: {
        supplierData: []
      },


    }
  },
  async created() {
    await this.init()
  },
  methods: {
    init() {
      if (this.$route.query.status != null && this.$route.query.status != '') {
        if (this.$route.query.status == 'review') {
          this.status = 'review'
        }
        else if(this.$route.query.status == 'management'){
          this.status = 'management'
        }
        this.disabledFlag = true
        this.productCode = this.$route.query.productCode
        //调用查询供应商的接口
        let query = {
          productCode: this.$route.query.productCode
        }
        selectProductSupplier(query).then(res => {
          this.supplierInfo.supplierData = res.data;
        })
      }

    },


  }
}
</script>

<style scoped>
.item-width {
  width: 200px;
}

/*element原有样式修改*/
.el-form-item ::v-deep label {
  font-weight: normal;
}

::v-deep .el-table__header-wrapper .el-checkbox__input::after {
  content: '全选';
  position: absolute;
  font-weight: bolder;
  margin-left: 5px;
}
</style>

