<template>
	<div class="main-content" :style='{"padding":"30px"}'>
		<!-- 列表页 -->
		<template v-if="showFlag">
			<el-form class="center-form-pv" :style='{"width":"90%","margin":"0 auto 20px"}' :inline="true" :model="searchForm">
				<el-row :style='{"display":"block"}' >
					<div :style='{"margin":"0 30px 0 0","display":"inline-block"}'>
						<label :style='{"margin":"0 10px 0 0","color":"#333","textAlign":"center","display":"inline-block","width":"auto","lineHeight":"40px","fontSize":"16px","fontWeight":"500","height":"40px"}' class="item-label">车名</label>
						<el-input v-model="searchForm.cheming" placeholder="车名" clearable></el-input>
					</div>
					<div :style='{"margin":"0 30px 0 0","display":"inline-block"}'>
						<label :style='{"margin":"0 10px 0 0","color":"#333","textAlign":"center","display":"inline-block","width":"auto","lineHeight":"40px","fontSize":"16px","fontWeight":"500","height":"40px"}' class="item-label">颜色</label>
						<el-input v-model="searchForm.yanse" placeholder="颜色" clearable></el-input>
					</div>
					<div :style='{"margin":"0 30px 0 0","display":"inline-block"}'>
						<label :style='{"margin":"0 10px 0 0","color":"#333","textAlign":"center","display":"inline-block","width":"auto","lineHeight":"40px","fontSize":"16px","fontWeight":"500","height":"40px"}' class="item-label">厂商</label>
						<el-input v-model="searchForm.changshang" placeholder="厂商" clearable></el-input>
					</div>
					<div :style='{"margin":"0 30px 0 0","display":"inline-block"}' class="select" label="有无天窗" prop="youwutianchuang">
						<label :style='{"margin":"0 10px 0 0","color":"#333","textAlign":"center","display":"inline-block","width":"auto","lineHeight":"40px","fontSize":"16px","fontWeight":"500","height":"40px"}' class="item-label">有无天窗</label>
						<el-select  @change="youwutianchuangChange" clearable v-model="searchForm.youwutianchuang" placeholder="请选择有无天窗">
							<el-option v-for="(item,index) in youwutianchuangOptions" v-bind:key="index" :label="item" :value="item"></el-option>
						</el-select>
					</div>
					<div :style='{"margin":"0 30px 0 0","display":"inline-block"}' class="select" label="能源类型" prop="nengyuanleixing">
						<label :style='{"margin":"0 10px 0 0","color":"#333","textAlign":"center","display":"inline-block","width":"auto","lineHeight":"40px","fontSize":"16px","fontWeight":"500","height":"40px"}' class="item-label">能源类型</label>
						<el-select  @change="nengyuanleixingChange" clearable v-model="searchForm.nengyuanleixing" placeholder="请选择能源类型">
							<el-option v-for="(item,index) in nengyuanleixingOptions" v-bind:key="index" :label="item" :value="item"></el-option>
						</el-select>
					</div>
					<el-button :style='{"border":"3px solid rgba(222, 239, 255, 1)","cursor":"pointer","padding":"0 24px","outline":"none","color":"#333","borderRadius":"4px","background":"rgba(236, 242, 249, 1)","width":"auto","fontSize":"14px","height":"40px"}' type="success" @click="search()">查询</el-button>
				</el-row>

				<el-row :style='{"margin":"20px 0","display":"flex"}'>
					<el-button :style='{"border":"0","cursor":"pointer","padding":"0 24px","margin":"0 10px 0 0","outline":"none","color":"#333","borderRadius":"4px","background":"rgba(222, 239, 255, 1)","width":"auto","fontSize":"14px","height":"40px"}' v-if="isAuth('xinnengyuanqiche','新增')" type="success" @click="addOrUpdateHandler()">新增</el-button>
					<el-button :style='{"border":"0","cursor":"pointer","padding":"0 24px","margin":"0 10px 0 0","outline":"none","color":"#333","borderRadius":"4px","background":"rgba(222, 239, 255, 1)","width":"auto","fontSize":"14px","height":"40px"}' v-if="isAuth('xinnengyuanqiche','删除')" :disabled="dataListSelections.length <= 0" type="danger" @click="deleteHandler()">删除</el-button>




					<el-button :style='{"border":"0","cursor":"pointer","padding":"0 24px","margin":"0 10px 0 0","outline":"none","color":"#333","borderRadius":"4px","background":"rgba(222, 239, 255, 1)","width":"auto","fontSize":"14px","height":"40px"}' v-if="isAuth('xinnengyuanqiche','品牌类别统计')" type="warning" @click="chartDialog1()">品牌类别统计</el-button>
					<el-button :style='{"border":"0","cursor":"pointer","padding":"0 24px","margin":"0 10px 0 0","outline":"none","color":"#333","borderRadius":"4px","background":"rgba(222, 239, 255, 1)","width":"auto","fontSize":"14px","height":"40px"}' v-if="isAuth('xinnengyuanqiche','汽车库存统计')" type="warning" @click="chartDialog2()">汽车库存统计</el-button>
				</el-row>
			</el-form>
			
			<!-- <div> -->
				<el-table class="tables"
					:stripe='true'
					:style='{"padding":"0","borderColor":"rgba(234, 243, 255, 1)","margin":"0 auto","borderRadius":"30px","borderWidth":"0 6px 6px","background":"#fff","width":"90%","borderStyle":"solid"}' 
					v-if="isAuth('xinnengyuanqiche','查看')"
					:data="dataList"
					v-loading="dataListLoading"
				@selection-change="selectionChangeHandler">
					<el-table-column :resizable='true' type="selection" align="center" width="50"></el-table-column>
					<el-table-column :resizable='true' :sortable='false' label="索引" type="index" width="50" />
					<el-table-column :resizable='true' :sortable='false'  
						prop="cheming"
					label="车名">
						<template slot-scope="scope">
							{{scope.row.cheming}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='false'  
						prop="pinpaileibie"
					label="品牌类别">
						<template slot-scope="scope">
							{{scope.row.pinpaileibie}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='false' prop="qichetupian" width="200" label="汽车图片">
						<template slot-scope="scope">
							<div v-if="scope.row.qichetupian">
								<img v-if="scope.row.qichetupian.substring(0,4)=='http'" :src="scope.row.qichetupian.split(',')[0]" width="100" height="100">
								<img v-else :src="$base.url+scope.row.qichetupian.split(',')[0]" width="100" height="100">
							</div>
							<div v-else>无图片</div>
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='false'  
						prop="yanse"
					label="颜色">
						<template slot-scope="scope">
							{{scope.row.yanse}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='false'  
						prop="jiage"
					label="价格">
						<template slot-scope="scope">
							{{scope.row.jiage}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='false'  
						prop="shuliang"
					label="数量">
						<template slot-scope="scope">
							{{scope.row.shuliang}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='false'  
						prop="changshang"
					label="厂商">
						<template slot-scope="scope">
							{{scope.row.changshang}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='false'  
						prop="youwutianchuang"
					label="有无天窗">
						<template slot-scope="scope">
							{{scope.row.youwutianchuang}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='false'  
						prop="nengyuanleixing"
					label="能源类型">
						<template slot-scope="scope">
							{{scope.row.nengyuanleixing}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='false'  
						prop="chundianxuhang"
					label="纯电续航">
						<template slot-scope="scope">
							{{scope.row.chundianxuhang}}
						</template>
					</el-table-column>
					<el-table-column width="300" label="操作">
						<template slot-scope="scope">
							<el-button :style='{"border":"0","cursor":"pointer","padding":"0 24px","margin":"0 10px 5px 0","outline":"none","color":"#333","borderRadius":"4px","background":"rgba(230, 242, 254, 1)","width":"auto","fontSize":"14px","height":"32px"}' v-if=" isAuth('xinnengyuanqiche','查看')" type="success" size="mini" @click="addOrUpdateHandler(scope.row.id,'info')">详情</el-button>
							<el-button :style='{"border":"0","cursor":"pointer","padding":"0 24px","margin":"0 10px 5px 0","outline":"none","color":"#333","borderRadius":"4px","background":"rgba(230, 242, 254, 1)","width":"auto","fontSize":"14px","height":"32px"}' v-if="isAuth('xinnengyuanqiche','购买汽车')" type="success" size="mini" @click="qichedingdanCrossAddOrUpdateHandler(scope.row,'cross','','','')">购买汽车</el-button>
							<el-button :style='{"border":"0","cursor":"pointer","padding":"0 24px","margin":"0 10px 5px 0","outline":"none","color":"#333","borderRadius":"4px","background":"rgba(230, 242, 254, 1)","width":"auto","fontSize":"14px","height":"32px"}' v-if="isAuth('xinnengyuanqiche','预约看车')" type="success" size="mini" @click="yuyuekancheCrossAddOrUpdateHandler(scope.row,'cross','','','')">预约看车</el-button>
							<el-button :style='{"border":"0","cursor":"pointer","padding":"0 24px","margin":"0 10px 5px 0","outline":"none","color":"#333","borderRadius":"4px","background":"rgba(230, 242, 254, 1)","width":"auto","fontSize":"14px","height":"32px"}' v-if=" isAuth('xinnengyuanqiche','修改')" type="primary" size="mini" @click="addOrUpdateHandler(scope.row.id)">修改</el-button>


							<el-button :style='{"border":"0","cursor":"pointer","padding":"0 24px","margin":"0 10px 5px 0","outline":"none","color":"#333","borderRadius":"4px","background":"rgba(230, 242, 254, 1)","width":"auto","fontSize":"14px","height":"32px"}' v-if="isAuth('xinnengyuanqiche','查看评论')" type="primary" size="mini" @click="disscussListHandler(scope.row.id)">查看评论</el-button>



							<el-button :style='{"border":"0","cursor":"pointer","padding":"0 24px","margin":"0 10px 5px 0","outline":"none","color":"#333","borderRadius":"4px","background":"rgba(230, 242, 254, 1)","width":"auto","fontSize":"14px","height":"32px"}' v-if="isAuth('xinnengyuanqiche','删除') " type="danger" size="mini" @click="deleteHandler(scope.row.id)">删除</el-button>
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

		<qichedingdan-cross-add-or-update v-if="qichedingdanCrossAddOrUpdateFlag" :parent="this" ref="qichedingdanCrossaddOrUpdate"></qichedingdan-cross-add-or-update>
		<yuyuekanche-cross-add-or-update v-if="yuyuekancheCrossAddOrUpdateFlag" :parent="this" ref="yuyuekancheCrossaddOrUpdate"></yuyuekanche-cross-add-or-update>




		<el-dialog
		  title="品牌类别统计"
		  :visible.sync="chartVisiable1"
		  width="800">
			<div id="pinpaileibieChart1" style="width:100%;height:600px;"></div>
		  <span slot="footer" class="dialog-footer">
			<el-button @click="chartDialog1">返回</el-button>
		  </span>
		</el-dialog>
		<el-dialog
		  title="汽车库存统计"
		  :visible.sync="chartVisiable2"
		  width="800">
			<div id="shuliangChart2" style="width:100%;height:600px;"></div>
		  <span slot="footer" class="dialog-footer">
			<el-button @click="chartDialog2">返回</el-button>
		  </span>
		</el-dialog>
	</div>
</template>

<script>
import * as echarts from 'echarts'
import axios from 'axios'
import AddOrUpdate from "./add-or-update";
import qichedingdanCrossAddOrUpdate from "../qichedingdan/add-or-update";
import yuyuekancheCrossAddOrUpdate from "../yuyuekanche/add-or-update";
export default {
  data() {
    return {
      searchForm: {
        key: ""
      },
      form:{},
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
      addOrUpdateFlag:false,
      qichedingdanCrossAddOrUpdateFlag: false,
      yuyuekancheCrossAddOrUpdateFlag: false,
      layouts: ["total","prev","pager","next","sizes","jumper"],

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
      return val.replace(/<[^>]*>/g).replace(/undefined/g,'');
    }
  },
  components: {
    AddOrUpdate,
    qichedingdanCrossAddOrUpdate,
    yuyuekancheCrossAddOrUpdate,
  },
  methods: {

    contentStyleChange() {
      this.contentPageStyleChange()
    },
    // 分页
    contentPageStyleChange(){
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

    qichedingdanCrossAddOrUpdateHandler(row,type,crossOptAudit,statusColumnName,tips,statusColumnValue){
      this.showFlag = false;
      this.addOrUpdateFlag = false;
      this.qichedingdanCrossAddOrUpdateFlag = true;
      this.$storage.set('crossObj',row);
      this.$storage.set('crossTable','xinnengyuanqiche');
      this.$storage.set('statusColumnName',statusColumnName);
      this.$storage.set('statusColumnValue',statusColumnValue);
      this.$storage.set('tips',tips);
	if(statusColumnName!=''&&!statusColumnName.startsWith("[")) {
		var obj = this.$storage.getObj('crossObj');
		for (var o in obj){
		  if(o==statusColumnName && obj[o]==statusColumnValue){
		    this.$message({
		      message: tips,
		      type: "success",
		      duration: 1500,
		      onClose: () => {
			this.getDataList();
		      }
		    });
		      this.showFlag = true;
		      this.qichedingdanCrossAddOrUpdateFlag = false;
			return;
		  }
		}
	}
      this.$nextTick(() => {
      this.$refs.qichedingdanCrossaddOrUpdate.init(row.id,type);
      });
    },
    yuyuekancheCrossAddOrUpdateHandler(row,type,crossOptAudit,statusColumnName,tips,statusColumnValue){
      this.showFlag = false;
      this.addOrUpdateFlag = false;
      this.yuyuekancheCrossAddOrUpdateFlag = true;
      this.$storage.set('crossObj',row);
      this.$storage.set('crossTable','xinnengyuanqiche');
      this.$storage.set('statusColumnName',statusColumnName);
      this.$storage.set('statusColumnValue',statusColumnValue);
      this.$storage.set('tips',tips);
	if(statusColumnName!=''&&!statusColumnName.startsWith("[")) {
		var obj = this.$storage.getObj('crossObj');
		for (var o in obj){
		  if(o==statusColumnName && obj[o]==statusColumnValue){
		    this.$message({
		      message: tips,
		      type: "success",
		      duration: 1500,
		      onClose: () => {
			this.getDataList();
		      }
		    });
		      this.showFlag = true;
		      this.yuyuekancheCrossAddOrUpdateFlag = false;
			return;
		  }
		}
	}
      this.$nextTick(() => {
      this.$refs.yuyuekancheCrossaddOrUpdate.init(row.id,type);
      });
    },


//统计接口
    chartDialog1() {
      this.chartVisiable1 = !this.chartVisiable1;
      this.$nextTick(()=>{

        var pinpaileibieChart1 = echarts.init(document.getElementById("pinpaileibieChart1"),'macarons');
        this.$http({
            url: "xinnengyuanqiche/group/pinpaileibie",
            method: "get",
        }).then(({ data }) => {
            if (data && data.code === 0) {
                let res = data.data;
                let xAxis = [];
                let yAxis = [];
                let pArray = []
                for(let i=0;i<res.length;i++){
                    xAxis.push(res[i].pinpaileibie);
                    yAxis.push(parseFloat((res[i].total)));
                    pArray.push({
                        value: parseFloat((res[i].total)),
                        name: res[i].pinpaileibie
                    })
                }
                var option = {};
                option = {
                        title: {
                            text: '品牌类别统计',
                            left: 'center'
                        },
                        legend: {
                          orient: 'vertical',
                          left: 'left'
                        },
                        tooltip: {
                          trigger: 'item',
                          formatter: '{b} : {c} ({d}%)'
                        },
                        series: [
                            {
                                type: 'pie',
                                radius: ['25%', '55%'],
                                center: ['50%', '60%'],
                                data: pArray,
                                emphasis: {
                                    itemStyle: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                }
                            }
                        ]
                };
                // 使用刚指定的配置项和数据显示图表。
                pinpaileibieChart1.setOption(option);
                  //根据窗口的大小变动图表
                window.onresize = function() {
                    pinpaileibieChart1.resize();
                };
            }
        });
      })
    },

//统计接口
    chartDialog2() {
      this.chartVisiable2 = !this.chartVisiable2;
      this.$nextTick(()=>{
        // cheming cheming
        //  shuliang

        var shuliangChart2 = echarts.init(document.getElementById("shuliangChart2"),'macarons');
        this.$http({
            url: `xinnengyuanqiche/value/cheming/shuliang`,
            method: "get",
        }).then(({ data }) => {
            if (data && data.code === 0) {
                let res = data.data;
                let xAxis = [];
                let yAxis = [];
                let pArray = []
                for(let i=0;i<res.length;i++){
                    xAxis.push(res[i].cheming);
                    yAxis.push(parseFloat((res[i].total)));
                    pArray.push({
                        value: parseFloat((res[i].total)),
                        name: res[i].cheming
                    })
                }
                var option = {};
                option = {
                    title: {
                        text: '汽车库存统计',
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
                shuliangChart2.setOption(option);
                  //根据窗口的大小变动图表
                window.onresize = function() {
                    shuliangChart2.resize();
                };
            }
        });
      })
    },




    init () {
          this.youwutianchuangOptions = "有,无".split(',')
          this.nengyuanleixingOptions = "纯电动,油电混合,增程式".split(',')
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
           if(this.searchForm.cheming!='' && this.searchForm.cheming!=undefined){
            params['cheming'] = '%' + this.searchForm.cheming + '%'
          }
           if(this.searchForm.yanse!='' && this.searchForm.yanse!=undefined){
            params['yanse'] = '%' + this.searchForm.yanse + '%'
          }
           if(this.searchForm.changshang!='' && this.searchForm.changshang!=undefined){
            params['changshang'] = '%' + this.searchForm.changshang + '%'
          }
           if(this.searchForm.youwutianchuang!='' && this.searchForm.youwutianchuang!=undefined){
            params['youwutianchuang'] = this.searchForm.youwutianchuang
          }
           if(this.searchForm.nengyuanleixing!='' && this.searchForm.nengyuanleixing!=undefined){
            params['nengyuanleixing'] = this.searchForm.nengyuanleixing
          }
      this.$http({
        url: "xinnengyuanqiche/page",
        method: "get",
        params: params
      }).then(({ data }) => {
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
    addOrUpdateHandler(id,type) {
      this.showFlag = false;
      this.addOrUpdateFlag = true;
      this.crossAddOrUpdateFlag = false;
      if(type!='info'){
        type = 'else';
      }
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id,type);
      });
    },
    // 查看评论
    disscussListHandler(id,type) {
	this.$router.push({path:'/discussxinnengyuanqiche',query:{refid:id}});
    },
    // 下载
    download(file){
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
          url: "xinnengyuanqiche/delete",
          method: "post",
          data: ids
        }).then(({ data }) => {
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
