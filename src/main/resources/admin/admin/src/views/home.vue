<template>
<div class="content" :style='{"padding":"30px"}'>
	<div class="text" :style='{"margin":"50px auto","fontSize":"24px","color":"rgb(51, 51, 51)","textAlign":"center","fontWeight":"bold"}'>欢迎使用 {{this.$project.projectName}}</div>
    <div class="cardView">
        <div class="cards" :style='{"margin":"0 0 20px 0","alignItems":"center","justifyContent":"center","display":"flex"}'>
			<div :style='{"boxShadow":"0 1px 6px rgba(0,0,0,.3)","margin":"0 10px","borderRadius":"15px 5px 5px 15px","display":"flex"}' v-if="isAuth('xinnengyuanqiche','首页总数')">
				<div :style='{"width":"40px","borderRadius":"15px 0 0 15px","background":"#deecfb","height":"80px"}'></div>
				<div :style='{"width":"160px","alignItems":"center","flexDirection":"column","justifyContent":"center","display":"flex"}'>
					<div :style='{"margin":"5px 0","lineHeight":"24px","fontSize":"20px","color":"#333","fontWeight":"bold","height":"24px"}'>{{xinnengyuanqicheCount}}</div>
					<div :style='{"margin":"5px 0","lineHeight":"24px","fontSize":"16px","color":"#666","height":"24px"}'>新能源汽车总数</div>
				</div>
			</div>
			<div :style='{"boxShadow":"0 1px 6px rgba(0,0,0,.3)","margin":"0 10px","borderRadius":"15px 5px 5px 15px","display":"flex"}' v-if="isAuth('qichedingdan','首页总数')">
				<div :style='{"width":"40px","borderRadius":"15px 0 0 15px","background":"#deecfb","height":"80px"}'></div>
				<div :style='{"width":"160px","alignItems":"center","flexDirection":"column","justifyContent":"center","display":"flex"}'>
					<div :style='{"margin":"5px 0","lineHeight":"24px","fontSize":"20px","color":"#333","fontWeight":"bold","height":"24px"}'>{{qichedingdanCount}}</div>
					<div :style='{"margin":"5px 0","lineHeight":"24px","fontSize":"16px","color":"#666","height":"24px"}'>汽车订单总数</div>
				</div>
			</div>
        </div>
        <div style="display: flex;align-items: center;width: 100%;margin-bottom: 10px;">
            <el-card style="width: 33.3%;margin: 0 10px;" v-if="isAuth('xinnengyuanqiche','首页统计')">
                <div id="xinnengyuanqicheChart1" style="width:100%;height:400px;"></div>
            </el-card>
            <el-card style="width: 33.3%;margin: 0 10px;" v-if="isAuth('xinnengyuanqiche','首页统计')">
                <div id="xinnengyuanqicheChart2" style="width:100%;height:400px;"></div>
            </el-card>
            <el-card style="width: 33.3%;margin: 0 10px;" v-if="isAuth('qichedingdan','首页统计')">
                <div id="qichedingdanChart1" style="width:100%;height:400px;"></div>
            </el-card>
        </div>
    </div>
</div>
</template>
<script>
//3
import router from '@/router/router-static'
import * as echarts from 'echarts'
export default {
	data() {
		return {
            xinnengyuanqicheCount: 0,
            qichedingdanCount: 0,
		};
	},
  mounted(){
    this.init();
    this.getxinnengyuanqicheCount();
    this.xinnengyuanqicheChat1();
    this.xinnengyuanqicheChat2();
    this.getqichedingdanCount();
    this.qichedingdanChat1();
  },
  methods:{
    init(){
        if(this.$storage.get('Token')){
        this.$http({
            url: `${this.$storage.get('sessionTable')}/session`,
            method: "get"
        }).then(({ data }) => {
            if (data && data.code != 0) {
            router.push({ name: 'login' })
            }
        });
        }else{
            router.push({ name: 'login' })
        }
    },
    getxinnengyuanqicheCount() {
        this.$http({
            url: `xinnengyuanqiche/count`,
            method: "get"
        }).then(({
            data
        }) => {
            if (data && data.code == 0) {
                this.xinnengyuanqicheCount = data.data
            }
        })
    },

    xinnengyuanqicheChat1() {
      this.$nextTick(()=>{

        var xinnengyuanqicheChart1 = echarts.init(document.getElementById("xinnengyuanqicheChart1"),'macarons');
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
                xinnengyuanqicheChart1.setOption(option);
                  //根据窗口的大小变动图表
                window.onresize = function() {
                    xinnengyuanqicheChart1.resize();
                };
            }
        });
      })
    },

    xinnengyuanqicheChat2() {
      this.$nextTick(()=>{
        // cheming cheming
        //  shuliang

        var xinnengyuanqicheChart2 = echarts.init(document.getElementById("xinnengyuanqicheChart2"),'macarons');
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
                xinnengyuanqicheChart2.setOption(option);
                  //根据窗口的大小变动图表
                window.onresize = function() {
                    xinnengyuanqicheChart2.resize();
                };
            }
        });
      })
    },





    getqichedingdanCount() {
        this.$http({
            url: `qichedingdan/count`,
            method: "get"
        }).then(({
            data
        }) => {
            if (data && data.code == 0) {
                this.qichedingdanCount = data.data
            }
        })
    },

    qichedingdanChat1() {
      this.$nextTick(()=>{
        //  yingfujine
        // xiadanshijian xiadanshijian

        var qichedingdanChart1 = echarts.init(document.getElementById("qichedingdanChart1"),'macarons');
        this.$http({
            url: `qichedingdan/value/xiadanshijian/yingfujine/月`,
            method: "get",
        }).then(({ data }) => {
            if (data && data.code === 0) {
                let res = data.data;
                let xAxis = [];
                let yAxis = [];
                let pArray = []
                for(let i=0;i<res.length;i++){
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
                qichedingdanChart1.setOption(option);
                  //根据窗口的大小变动图表
                window.onresize = function() {
                    qichedingdanChart1.resize();
                };
            }
        });
      })
    },






  }
};
</script>
<style lang="scss" scoped>
    .cardView {
        display: flex;
        flex-wrap: wrap;
        width: 100%;

        .cards {
            display: flex;
            align-items: center;
            width: 100%;
            margin-bottom: 10px;
            justify-content: center;
            .card {
                width: calc(25% - 20px);
                margin: 0 10px;
                /deep/.el-card__body{
                    padding: 0;
                }
            }
        }
    }
</style>
