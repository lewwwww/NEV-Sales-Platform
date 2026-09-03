<template>
  <div class="main-content" :style='{"padding":"30px"}'>
    <!-- 列表页 -->
    <template v-if="showFlag">
      <el-form class="center-form-pv" :style='{"width":"90%","margin":"0 auto 20px"}' :inline="true"
               :model="searchForm">
        <el-row :style='{"display":"block"}'>
          <div :style='{"margin":"0 30px 0 0","display":"inline-block"}'>
            <label
                :style='{"margin":"0 10px 0 0","color":"#333","textAlign":"center","display":"inline-block","width":"auto","lineHeight":"40px","fontSize":"16px","fontWeight":"500","height":"40px"}'
                class="item-label">车名</label>
            <el-input v-model="searchForm.cheming" placeholder="车名" clearable></el-input>
          </div>
          <div :style='{"margin":"0 30px 0 0","display":"inline-block"}'>
            <label
                :style='{"margin":"0 10px 0 0","color":"#333","textAlign":"center","display":"inline-block","width":"auto","lineHeight":"40px","fontSize":"16px","fontWeight":"500","height":"40px"}'
                class="item-label">姓名</label>
            <el-input v-model="searchForm.xingming" placeholder="姓名" clearable></el-input>
          </div>
          <div :style='{"margin":"0 30px 0 0","display":"inline-block"}' class="select">
            <label
                :style='{"margin":"0 10px 0 0","color":"#333","textAlign":"center","display":"inline-block","width":"auto","lineHeight":"40px","fontSize":"16px","fontWeight":"500","height":"40px"}'
                class="item-label">维修状态</label>
            <el-select @change="sfshChange" clearable v-model="searchForm.sfsh" placeholder="维修状态">
              <el-option v-for="(item,index) in sfshOptions" v-bind:key="index" :label="item" :value="item"></el-option>
            </el-select>
          </div>
          <el-button
              :style='{"border":"3px solid rgba(222, 239, 255, 1)","cursor":"pointer","padding":"0 24px","outline":"none","color":"#333","borderRadius":"4px","background":"rgba(236, 242, 249, 1)","width":"auto","fontSize":"14px","height":"40px"}'
              type="success" @click="search()">查询
          </el-button>
        </el-row>

        <el-row :style='{"margin":"20px 0","display":"flex"}'>
          <el-button
              :style='{"border":"0","cursor":"pointer","padding":"0 24px","margin":"0 10px 0 0","outline":"none","color":"#333","borderRadius":"4px","background":"rgba(222, 239, 255, 1)","width":"auto","fontSize":"14px","height":"40px"}'
              v-if="isAuth('weixiudingdan','新增')" type="success" @click="addOrUpdateHandler()">新增
          </el-button>
          <el-button
              :style='{"border":"0","cursor":"pointer","padding":"0 24px","margin":"0 10px 0 0","outline":"none","color":"#333","borderRadius":"4px","background":"rgba(222, 239, 255, 1)","width":"auto","fontSize":"14px","height":"40px"}'
              v-if="isAuth('weixiudingdan','删除')" :disabled="dataListSelections.length <= 0" type="danger"
              @click="deleteHandler()">删除
          </el-button>
          <el-button
              :style='{"border":"0","cursor":"pointer","padding":"0 24px","margin":"0 10px 0 0","outline":"none","color":"#333","borderRadius":"4px","background":"rgba(222, 239, 255, 1)","width":"auto","fontSize":"14px","height":"40px"}'
              v-if="isAuth('weixiudingdan','每月销售额统计')" type="warning" @click="chartDialog1()">每月销售额统计
          </el-button>
        </el-row>
      </el-form>

      <!-- <div> -->
      <el-table class="tables"
                :stripe='true'
                :style='{"padding":"0","borderColor":"rgba(234, 243, 255, 1)","margin":"0 auto","borderRadius":"30px","borderWidth":"0 6px 6px","background":"#fff","width":"90%","borderStyle":"solid"}'
                v-if="isAuth('weixiudingdan','查看')"
                :data="dataList"
                v-loading="dataListLoading"
                @selection-change="selectionChangeHandler">
        <el-table-column :resizable='true' type="selection" align="center" width="50"></el-table-column>
        <el-table-column :resizable='true' :sortable='false' label="索引" type="index" width="50"/>
        <el-table-column :resizable='true' :sortable='false'
                         prop="dingdanbianhao"
                         label="订单编号">
          <template slot-scope="scope">
            {{ scope.row.dingdanbianhao }}
          </template>
        </el-table-column>
        <el-table-column :resizable='true' :sortable='false'
                         prop="cheming"
                         label="车名">
          <template slot-scope="scope">
            {{ scope.row.cheming }}
          </template>
        </el-table-column>
        <el-table-column :resizable='true' :sortable='false'
                         prop="pinpaileibie"
                         label="品牌类别">
          <template slot-scope="scope">
            {{ scope.row.pinpaileibie }}
          </template>
        </el-table-column>
        <el-table-column :resizable='true' :sortable='false'
                         prop="yingfujine"
                         label="应付金额">
          <template slot-scope="scope">
            {{ scope.row.yingfujine }}
          </template>
        </el-table-column>
        <el-table-column :resizable='true' :sortable='false'
                         prop="zhanghao"
                         label="账号">
          <template slot-scope="scope">
            {{ scope.row.zhanghao }}
          </template>
        </el-table-column>
        <el-table-column :resizable='true' :sortable='false'
                         prop="xingming"
                         label="姓名">
          <template slot-scope="scope">
            {{ scope.row.xingming }}
          </template>
        </el-table-column>
        <el-table-column :resizable='true' :sortable='false'
                         prop="shoujihaoma"
                         label="手机号码">
          <template slot-scope="scope">
            {{ scope.row.shoujihaoma }}
          </template>
        </el-table-column>
        <el-table-column :resizable='true' :sortable='false'
                         prop="xiadanshijian"
                         label="预约时间">
          <template slot-scope="scope">
            {{ scope.row.xiadanshijian }}
          </template>
        </el-table-column>
        <el-table-column :resizable='true' :sortable='false'
                         prop="xiadanbeizhu"
                         label="预约备注">
          <template slot-scope="scope">
            {{ scope.row.xiadanbeizhu }}
          </template>
        </el-table-column>
        <el-table-column :resizable='true' :sortable='false' prop="ispay" label="是否支付">
          <template slot-scope="scope">
            <span style="margin-right:10px">{{ scope.row.ispay == '已支付' ? '已支付' : '未支付' }}</span>
            <el-button v-if="scope.row.ispay!='已支付' && isAuth('weixiudingdan','支付') && scope.row.sfsh=='已维修'" type="text" size="small"
                       @click="payHandler(scope.row)">支付
            </el-button>
          </template>
        </el-table-column>
        <el-table-column :resizable='true' :sortable='false' prop="shhf" label="维修说明"></el-table-column>
        <el-table-column :resizable='true' :sortable='false' prop="sfsh" label="维修状态">
          <template slot-scope="scope">
            <span style="margin-right:10px" v-if="scope.row.sfsh=='已维修'">已维修</span>
            <span style="margin-right:10px" v-if="scope.row.sfsh=='待维修'">待维修</span>
          </template>
        </el-table-column>
        <el-table-column width="300" label="操作">
          <template slot-scope="scope">
            <el-button
                :style='{"border":"0","cursor":"pointer","padding":"0 24px","margin":"0 10px 5px 0","outline":"none","color":"#333","borderRadius":"4px","background":"rgba(230, 242, 254, 1)","width":"auto","fontSize":"14px","height":"32px"}'
                v-if=" isAuth('weixiudingdan','查看')" type="success" size="mini"
                @click="addOrUpdateHandler(scope.row.id,'info')">详情
            </el-button>
            <el-button
                :style='{"border":"0","cursor":"pointer","padding":"0 24px","margin":"0 10px 5px 0","outline":"none","color":"#333","borderRadius":"4px","background":"rgba(230, 242, 254, 1)","width":"auto","fontSize":"14px","height":"32px"}'
                v-if=" isAuth('weixiudingdan','修改')" type="primary" size="mini"
                @click="addOrUpdateHandler(scope.row.id)">修改
            </el-button>
            <el-button
                :style='{"border":"0","cursor":"pointer","padding":"0 24px","margin":"0 10px 5px 0","outline":"none","color":"#333","borderRadius":"4px","background":"rgba(230, 242, 254, 1)","width":"auto","fontSize":"14px","height":"32px"}'
                v-if="isAuth('weixiudingdan','审核') && scope.row.sfsh=='待维修'" type="info" size="mini" @click="shDialog(scope.row)">维修</el-button>
            <el-button
                :style='{"border":"0","cursor":"pointer","padding":"0 24px","margin":"0 10px 5px 0","outline":"none","color":"#333","borderRadius":"4px","background":"rgba(230, 242, 254, 1)","width":"auto","fontSize":"14px","height":"32px"}'
                v-if="isAuth('weixiudingdan','删除') " type="danger" size="mini" @click="deleteHandler(scope.row.id)">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          @size-change="sizeChangeHandle"
          @current-change="currentChangeHandle"
          :current-page="pageIndex"
          background
          :page-sizes="[10, 20, 30, 50]"
          :page-size="pageSize"
          :layout="layouts.join()"
          :total="totalPage"
          prev-text="<"
          next-text=">"
          :hide-on-single-page="false"
          :style='{"padding":"0","margin":"20px auto 0","whiteSpace":"nowrap","color":"#333","textAlign":"center","width":"90%","fontWeight":"500"}'
      ></el-pagination>
      <!-- </div> -->
    </template>

    <!-- 添加/修改页面  将父组件的search方法传递给子组件-->
    <add-or-update v-if="addOrUpdateFlag" :parent="this" ref="addOrUpdate"></add-or-update>

    <peisongdingdan-cross-add-or-update v-if="peisongdingdanCrossAddOrUpdateFlag" :parent="this"
                                        ref="peisongdingdanCrossaddOrUpdate"></peisongdingdan-cross-add-or-update>

    <el-dialog title="维修" :visible.sync="sfshVisiable" width="50%">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="维修状态">
          <el-select v-model="shForm.sfsh" placeholder="维修状态">
            <el-option label="待维修" value="待维修"></el-option>
            <el-option label="已维修" value="已维修"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="应付金额">
          <el-input type="integer" :rows="8" v-model="shForm.yingfujine"></el-input>
        </el-form-item>
        <el-form-item label="维修说明">
          <el-input type="textarea" :rows="8" v-model="shForm.shhf"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
				<el-button @click="shDialog">取 消</el-button>
				<el-button type="primary" @click="shHandler">确 定</el-button>
			</span>
    </el-dialog>


    <el-dialog
        title="每月销售额统计"
        :visible.sync="chartVisiable1"
        width="800">
      <div id="yingfujineChart1" style="width:100%;height:600px;"></div>
      <span slot="footer" class="dialog-footer">
			<el-button @click="chartDialog1">返回</el-button>
		  </span>
    </el-dialog>
  </div>
</template>

<script>
import * as echarts from 'echarts'
import axios from 'axios'
import AddOrUpdate from "./add-or-update";
import peisongdingdanCrossAddOrUpdate from "../peisongdingdan/add-or-update";
import {isNumber} from "@/utils/validate";

export default {
  data() {
    return {
      searchForm: {
        key: ""
      },
      form: {},
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      showFlag: true,
      sfshVisiable: false,
      shForm: {},
      chartVisiable: false,
      chartVisiable1: false,
      chartVisiable2: false,
      chartVisiable3: false,
      chartVisiable4: false,
      chartVisiable5: false,
      addOrUpdateFlag: false,
      peisongdingdanCrossAddOrUpdateFlag: false,
      layouts: ["total", "prev", "pager", "next", "sizes", "jumper"],

    };
  },
  created() {
    this.init();
    this.getDataList();
    this.contentStyleChange()
  },
  mounted() {
  },
  filters: {
    htmlfilter: function (val) {
      return val.replace(/<[^>]*>/g).replace(/undefined/g, '');
    }
  },
  components: {
    AddOrUpdate,
    peisongdingdanCrossAddOrUpdate,
  },
  methods: {

    contentStyleChange() {
      this.contentPageStyleChange()
    },
    // 分页
    contentPageStyleChange() {
      let arr = []

      // if(this.contents.pageTotal) arr.push('total')
      // if(this.contents.pageSizes) arr.push('sizes')
      // if(this.contents.pagePrevNext){
      //   arr.push('prev')
      //   if(this.contents.pagePager) arr.push('pager')
      //   arr.push('next')
      // }
      // if(this.contents.pageJumper) arr.push('jumper')
      // this.layouts = arr.join()
      // this.contents.pageEachNum = 10
    },

    peisongdingdanCrossAddOrUpdateHandler(row, type, crossOptAudit, statusColumnName, tips, statusColumnValue) {
      if (crossOptAudit == '是' && row.sfsh != '是') {
        this.$message({
          message: "请审核通过后再操作",
          type: "success",
          duration: 1500,
          onClose: () => {
          }
        });
        return
      }
      this.showFlag = false;
      this.addOrUpdateFlag = false;
      this.peisongdingdanCrossAddOrUpdateFlag = true;
      this.$storage.set('crossObj', row);
      this.$storage.set('crossTable', 'weixiudingdan');
      this.$storage.set('statusColumnName', statusColumnName);
      this.$storage.set('statusColumnValue', statusColumnValue);
      this.$storage.set('tips', tips);
      if (statusColumnName != '' && !statusColumnName.startsWith("[")) {
        var obj = this.$storage.getObj('crossObj');
        for (var o in obj) {
          if (o == statusColumnName && obj[o] == statusColumnValue) {
            this.$message({
              message: tips,
              type: "success",
              duration: 1500,
              onClose: () => {
                this.getDataList();
              }
            });
            this.showFlag = true;
            this.peisongdingdanCrossAddOrUpdateFlag = false;
            return;
          }
        }
      }
      this.$nextTick(() => {
        this.$refs.peisongdingdanCrossaddOrUpdate.init(row.id, type);
      });
    },
    payHandler(row) {
      this.$storage.set('paytable', 'weixiudingdan');
      this.$storage.set('payObject', row);
      this.$router.push('pay');
    },


//统计接口
    chartDialog1() {
      this.chartVisiable1 = !this.chartVisiable1;
      this.$nextTick(() => {

        var yingfujineChart1 = echarts.init(document.getElementById("yingfujineChart1"), 'macarons');
        this.$http({
          url: `weixiudingdan/value/xiadanshijian/yingfujine/月`,
          method: "get",
        }).then(({data}) => {
          if (data && data.code === 0) {
            let res = data.data;
            let xAxis = [];
            let yAxis = [];
            let pArray = []
            for (let i = 0; i < res.length; i++) {
              xAxis.push(res[i].xiadanshijian);
              yAxis.push(parseFloat((res[i].total)));
              pArray.push({
                value: parseFloat((res[i].total)),
                name: res[i].xiadanshijian
              })
            }
            var option = {};
            option = {
              title: {
                text: '每月销售额统计',
                left: 'center'
              },
              tooltip: {
                trigger: 'item',
                formatter: '{b} : {c}'
              },
              xAxis: {
                type: 'category',
                data: xAxis
              },
              yAxis: {
                type: 'value'
              },
              series: [{
                data: yAxis,
                type: 'bar'
              }]
            };
            // 使用刚指定的配置项和数据显示图表。
            yingfujineChart1.setOption(option);
            //根据窗口的大小变动图表
            window.onresize = function () {
              yingfujineChart1.resize();
            };
          }
        });
      })
    },


    init() {
      this.sfshOptions = "待维修,已维修".split(',');
    },
    search() {
      this.pageIndex = 1;
      this.getDataList();
    },

    // 获取数据列表
    getDataList() {
      this.dataListLoading = true;
      let params = {
        page: this.pageIndex,
        limit: this.pageSize,
        sort: 'id',
        order: 'desc',
      }
      if (this.searchForm.sfsh != '' && this.searchForm.sfsh != undefined) {
        params['sfsh'] = this.searchForm.sfsh
      }
      if (this.searchForm.cheming != '' && this.searchForm.cheming != undefined) {
        params['cheming'] = '%' + this.searchForm.cheming + '%'
      }
      if (this.searchForm.sfsh != '' && this.searchForm.sfsh != undefined) {
        params['sfsh'] = this.searchForm.sfsh
      }
      if (this.searchForm.sfsh != '' && this.searchForm.sfsh != undefined) {
        params['sfsh'] = this.searchForm.sfsh
      }
      if (this.searchForm.sfsh != '' && this.searchForm.sfsh != undefined) {
        params['sfsh'] = this.searchForm.sfsh
      }
      if (this.searchForm.sfsh != '' && this.searchForm.sfsh != undefined) {
        params['sfsh'] = this.searchForm.sfsh
      }
      if (this.searchForm.sfsh != '' && this.searchForm.sfsh != undefined) {
        params['sfsh'] = this.searchForm.sfsh
      }
      if (this.searchForm.sfsh != '' && this.searchForm.sfsh != undefined) {
        params['sfsh'] = this.searchForm.sfsh
      }
      if (this.searchForm.sfsh != '' && this.searchForm.sfsh != undefined) {
        params['sfsh'] = this.searchForm.sfsh
      }
      if (this.searchForm.sfsh != '' && this.searchForm.sfsh != undefined) {
        params['sfsh'] = this.searchForm.sfsh
      }
      if (this.searchForm.sfsh != '' && this.searchForm.sfsh != undefined) {
        params['sfsh'] = this.searchForm.sfsh
      }
      if (this.searchForm.sfsh != '' && this.searchForm.sfsh != undefined) {
        params['sfsh'] = this.searchForm.sfsh
      }
      if (this.searchForm.xingming != '' && this.searchForm.xingming != undefined) {
        params['xingming'] = '%' + this.searchForm.xingming + '%'
      }
      if (this.searchForm.sfsh != '' && this.searchForm.sfsh != undefined) {
        params['sfsh'] = this.searchForm.sfsh
      }
      if (this.searchForm.sfsh != '' && this.searchForm.sfsh != undefined) {
        params['sfsh'] = this.searchForm.sfsh
      }
      if (this.searchForm.sfsh != '' && this.searchForm.sfsh != undefined) {
        params['sfsh'] = this.searchForm.sfsh
      }
      if (this.searchForm.sfsh != '' && this.searchForm.sfsh != undefined) {
        params['sfsh'] = this.searchForm.sfsh
      }
      if (this.searchForm.sfsh != '' && this.searchForm.sfsh != undefined) {
        params['sfsh'] = this.searchForm.sfsh
      }
      if (this.searchForm.sfsh != '' && this.searchForm.sfsh != undefined) {
        params['sfsh'] = this.searchForm.sfsh
      }
      if (this.searchForm.sfsh != '' && this.searchForm.sfsh != undefined) {
        params['sfsh'] = this.searchForm.sfsh
      }
      if (this.searchForm.sfsh != '' && this.searchForm.sfsh != undefined) {
        params['sfsh'] = this.searchForm.sfsh
      }
      if (this.searchForm.sfsh != '' && this.searchForm.sfsh != undefined) {
        params['sfsh'] = this.searchForm.sfsh
      }
      this.$http({
        url: "weixiudingdan/page",
        method: "get",
        params: params
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.dataList = data.data.list;
          this.totalPage = data.data.total;
        } else {
          this.dataList = [];
          this.totalPage = 0;
        }
        this.dataListLoading = false;
      });
    },
    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getDataList();
    },
    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getDataList();
    },
    // 多选
    selectionChangeHandler(val) {
      this.dataListSelections = val;
    },
    // 添加/修改
    addOrUpdateHandler(id, type) {
      this.showFlag = false;
      this.addOrUpdateFlag = true;
      this.crossAddOrUpdateFlag = false;
      if (type != 'info') {
        type = 'else';
      }
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id, type);
      });
    },
    // 审核窗口
    shDialog(row) {
      this.sfshVisiable = !this.sfshVisiable;
      if (row) {
        this.shForm = {
          dingdanbianhao: row.dingdanbianhao,
          cheming: row.cheming,
          pinpaileibie: row.pinpaileibie,
          yanse: row.yanse,
          jiage: row.jiage,
          shuliang: row.shuliang,
          yingfujine: row.yingfujine,
          chundianxuhang: row.chundianxuhang,
          nengyuanleixing: row.nengyuanleixing,
          zhanghao: row.zhanghao,
          xingming: row.xingming,
          shoujihaoma: row.shoujihaoma,
          shouhuodizhi: row.shouhuodizhi,
          xiadanshijian: row.xiadanshijian,
          xiadanbeizhu: row.xiadanbeizhu,
          sfsh: row.sfsh,
          shhf: row.shhf,
          ispay: row.ispay,
          id: row.id
        }
      }
    },
    // 审核
    shHandler() {
      if(this.shForm.yingfujine == null || this.shForm.yingfujine==undefined){
        this.$message.error('应付金额不能为空');
        return
      } else if (!isNumber(this.shForm.yingfujine)) {
        this.$message.error('应付金额是数字');
        return
      } else if (this.shForm.yingfujine <= 0) {
        this.$message.error('应付金额必须大于0');
        return
      }
      this.$confirm(`确定操作?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$http({
          url: "weixiudingdan/update",
          method: "post",
          data: this.shForm
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.$message({
              message: "操作成功",
              type: "success",
              duration: 1500,
              onClose: () => {
                this.getDataList();
                this.shDialog()
              }
            });
          } else {
            this.$message.error(data.msg);
          }
        });
      });
    },
    // 下载
    download(file) {
      window.open(`${file}`)
    },
    // 删除
    deleteHandler(id) {
      var ids = id
          ? [Number(id)]
          : this.dataListSelections.map(item => {
            return Number(item.id);
          });
      this.$confirm(`确定进行[${id ? "删除" : "批量删除"}]操作?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$http({
          url: "weixiudingdan/delete",
          method: "post",
          data: ids
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.$message({
              message: "操作成功",
              type: "success",
              duration: 1500,
              onClose: () => {
                this.search();
              }
            });
          } else {
            this.$message.error(data.msg);
          }
        });
      });
    },


  }

};
</script>
<style lang="scss" scoped>

.center-form-pv {
  .el-date-editor.el-input {
    width: auto;
  }
}

.el-input {
  width: auto;
}

// form
.center-form-pv .el-input ::v-deep .el-input__inner {
  border: 0;
  border-radius: 4px;
  padding: 0 12px;
  box-shadow: 0 0 6px rgba(64, 158, 255, .5);
  outline: none;
  color: #000;
  background: rgba(230, 242, 254, 1);
  width: 170px;
  font-size: 14px;
  height: 40px;
}

.center-form-pv .el-select ::v-deep .el-input__inner {
  border: 0;
  border-radius: 4px;
  padding: 0 10px;
  box-shadow: 0 0 6px rgba(64, 158, 255, .5);
  outline: none;
  color: #000;
  background: rgba(230, 242, 254, 1);
  width: 170px;
  font-size: 14px;
  height: 40px;
}

.center-form-pv .el-date-editor ::v-deep .el-input__inner {
  border: 0;
  border-radius: 4px;
  padding: 0 10px 0 30px;
  box-shadow: 0 0 6px rgba(64, 158, 255, .5);
  outline: none;
  color: #000;
  background: rgba(230, 242, 254, 1);
  width: 170px;
  font-size: 14px;
  height: 40px;
}

// table
.el-table ::v-deep .el-table__header-wrapper thead {
  color: #333;
  font-weight: 500;
  width: 100%;
}

.el-table ::v-deep .el-table__header-wrapper thead tr {
  background: #fff;
}

.el-table ::v-deep .el-table__header-wrapper thead tr th {
  padding: 12px 0;
  background: linear-gradient(122deg, #E6F2FE 0%, #EAF3FF 100%);
  border-color: #fff;
  border-width: 0 1px 1px 0;
  border-style: solid;
  text-align: center;
}

.el-table ::v-deep .el-table__header-wrapper thead tr th .cell {
  padding: 0 10px;
  word-wrap: normal;
  word-break: break-all;
  white-space: normal;
  font-weight: bold;
  display: inline-block;
  vertical-align: middle;
  width: 100%;
  line-height: 24px;
  position: relative;
  text-overflow: ellipsis;
}


.el-table ::v-deep .el-table__body-wrapper tbody {
  width: 100%;
}

.el-table ::v-deep .el-table__body-wrapper tbody tr {
  background: #fff;
}

.el-table ::v-deep .el-table__body-wrapper tbody tr td {
  padding: 12px 0;
  color: #999;
  background: #fff;
  border-color: #eee;
  border-width: 0 1px 1px 0;
  border-style: solid;
  text-align: center;
}

.el-table ::v-deep .el-table__body-wrapper tbody tr.el-table__row--striped td {
  background: #FAFAFA;
}

.el-table ::v-deep .el-table__body-wrapper tbody tr:hover td {
  padding: 12px 0;
  color: #333;
  border-color: #eee;
  border-width: 0 1px 1px 0;
  border-style: solid;
  text-align: center;
}

.el-table ::v-deep .el-table__body-wrapper tbody tr td {
  padding: 12px 0;
  color: #999;
  background: #fff;
  border-color: #eee;
  border-width: 0 1px 1px 0;
  border-style: solid;
  text-align: center;
}

.el-table ::v-deep .el-table__body-wrapper tbody tr td .cell {
  padding: 0 10px;
  overflow: hidden;
  word-break: break-all;
  white-space: normal;
  line-height: 24px;
  text-overflow: ellipsis;
}

// pagination
.main-content .el-pagination ::v-deep .el-pagination__total {
  margin: 0 10px 0 0;
  color: #666;
  font-weight: 400;
  display: inline-block;
  vertical-align: top;
  font-size: 13px;
  line-height: 28px;
  height: 28px;
}

.main-content .el-pagination ::v-deep .btn-prev {
  border: none;
  border-radius: 2px;
  padding: 0;
  margin: 0 5px;
  color: #666;
  background: #f4f4f5;
  display: inline-block;
  vertical-align: top;
  font-size: 13px;
  line-height: 28px;
  min-width: 35px;
  height: 28px;
}

.main-content .el-pagination ::v-deep .btn-next {
  border: none;
  border-radius: 2px;
  padding: 0;
  margin: 0 5px;
  color: #666;
  background: #f4f4f5;
  display: inline-block;
  vertical-align: top;
  font-size: 13px;
  line-height: 28px;
  min-width: 35px;
  height: 28px;
}

.main-content .el-pagination ::v-deep .btn-prev:disabled {
  border: none;
  cursor: not-allowed;
  border-radius: 2px;
  padding: 0;
  margin: 0 5px;
  color: #C0C4CC;
  background: #f4f4f5;
  display: inline-block;
  vertical-align: top;
  font-size: 13px;
  line-height: 28px;
  height: 28px;
}

.main-content .el-pagination ::v-deep .btn-next:disabled {
  border: none;
  cursor: not-allowed;
  border-radius: 2px;
  padding: 0;
  margin: 0 5px;
  color: #C0C4CC;
  background: #f4f4f5;
  display: inline-block;
  vertical-align: top;
  font-size: 13px;
  line-height: 28px;
  height: 28px;
}

.main-content .el-pagination ::v-deep .el-pager {
  padding: 0;
  margin: 0;
  display: inline-block;
  vertical-align: top;
}

.main-content .el-pagination ::v-deep .el-pager .number {
  cursor: pointer;
  padding: 0 4px;
  margin: 0 5px;
  color: #666;
  display: inline-block;
  vertical-align: top;
  font-size: 13px;
  line-height: 28px;
  border-radius: 2px;
  background: #f4f4f5;
  text-align: center;
  min-width: 30px;
  height: 28px;
}

.main-content .el-pagination ::v-deep .el-pager .number:hover {
  cursor: pointer;
  padding: 0 4px;
  margin: 0 5px;
  color: #333;
  display: inline-block;
  vertical-align: top;
  font-size: 13px;
  line-height: 28px;
  border-radius: 2px;
  background: #f4f4f5;
  text-align: center;
  min-width: 30px;
  height: 28px;
}

.main-content .el-pagination ::v-deep .el-pager .number.active {
  cursor: default;
  padding: 0 4px;
  margin: 0 5px;
  color: #333;
  display: inline-block;
  vertical-align: top;
  font-size: 13px;
  line-height: 28px;
  border-radius: 2px;
  background: rgba(230, 242, 254, 1);
  text-align: center;
  min-width: 30px;
  height: 28px;
}

.main-content .el-pagination ::v-deep .el-pagination__sizes {
  display: inline-block;
  vertical-align: top;
  font-size: 13px;
  line-height: 28px;
  height: 28px;
}

.main-content .el-pagination ::v-deep .el-pagination__sizes .el-input {
  margin: 0 5px;
  width: 100px;
  position: relative;
}

.main-content .el-pagination ::v-deep .el-pagination__sizes .el-input .el-input__inner {
  border: 1px solid #DCDFE6;
  cursor: pointer;
  padding: 0 25px 0 8px;
  color: #606266;
  display: inline-block;
  font-size: 13px;
  line-height: 28px;
  border-radius: 3px;
  outline: 0;
  background: #FFF;
  width: 100%;
  text-align: center;
  height: 28px;
}

.main-content .el-pagination ::v-deep .el-pagination__sizes .el-input span.el-input__suffix {
  top: 0;
  position: absolute;
  right: 0;
  height: 100%;
}

.main-content .el-pagination ::v-deep .el-pagination__sizes .el-input .el-input__suffix .el-select__caret {
  cursor: pointer;
  color: #C0C4CC;
  width: 25px;
  font-size: 14px;
  line-height: 28px;
  text-align: center;
}

.main-content .el-pagination ::v-deep .el-pagination__jump {
  margin: 0 0 0 24px;
  color: #606266;
  display: inline-block;
  vertical-align: top;
  font-size: 13px;
  line-height: 28px;
  height: 28px;
}

.main-content .el-pagination ::v-deep .el-pagination__jump .el-input {
  border-radius: 3px;
  padding: 0 2px;
  margin: 0 2px;
  display: inline-block;
  width: 50px;
  font-size: 14px;
  line-height: 18px;
  position: relative;
  text-align: center;
  height: 28px;
}

.main-content .el-pagination ::v-deep .el-pagination__jump .el-input .el-input__inner {
  border: 1px solid #DCDFE6;
  cursor: pointer;
  padding: 0 3px;
  color: #606266;
  display: inline-block;
  font-size: 14px;
  line-height: 28px;
  border-radius: 3px;
  outline: 0;
  background: #FFF;
  width: 100%;
  text-align: center;
  height: 28px;
}
</style>
