<template>
<div :style='{"width":"calc(100% - 40px)","padding":"30px 20px 40px","margin":"20px auto 0","position":"relative","background":"rgba(255,255,255,.3)"}'>
    <el-form
      class="add-update-preview"
      ref="ruleForm"
      :model="ruleForm"
      :rules="rules"
      label-width="80px"
    >
          <el-form-item :style='{"border":"0px solid #dfdfdf","padding":"10px","boxShadow":"0px 0px 0px #eee","margin":"0 0 8px 0","borderRadius":"8px","background":"none"}' label="车名" prop="cheming">
            <el-input v-model="ruleForm.cheming" 
                placeholder="车名" clearable ></el-input>
          </el-form-item>
          <el-form-item :style='{"border":"0px solid #dfdfdf","padding":"10px","boxShadow":"0px 0px 0px #eee","margin":"0 0 8px 0","borderRadius":"8px","background":"none"}'  label="品牌类别" prop="pinpaileibie">
            <el-select v-model="ruleForm.pinpaileibie" placeholder="请选择品牌类别"  >
              <el-option
                  v-for="(item,index) in pinpaileibieOptions"
                  :key="index"
                  :label="item"
                  :value="item">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item :style='{"border":"0px solid #dfdfdf","padding":"10px","boxShadow":"0px 0px 0px #eee","margin":"0 0 8px 0","borderRadius":"8px","background":"none"}' label="汽车图片" v-if="type!='cross' || (type=='cross' && !ro.qichetupian)" prop="qichetupian">
            <file-upload
            tip="点击上传汽车图片"
            action="file/upload"
            :limit="3"
            :multiple="true"
            :fileUrls="ruleForm.qichetupian?ruleForm.qichetupian:''"
            @change="qichetupianUploadChange"
            ></file-upload>
          </el-form-item>
            <el-form-item :style='{"border":"0px solid #dfdfdf","padding":"10px","boxShadow":"0px 0px 0px #eee","margin":"0 0 8px 0","borderRadius":"8px","background":"none"}' class="upload" v-else label="汽车图片" prop="qichetupian">
                <img v-if="ruleForm.qichetupian.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.qichetupian.split(',')[0]" width="100" height="100">
                <img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.qichetupian.split(',')" :src="baseUrl+item" width="100" height="100">
            </el-form-item>
          <el-form-item :style='{"border":"0px solid #dfdfdf","padding":"10px","boxShadow":"0px 0px 0px #eee","margin":"0 0 8px 0","borderRadius":"8px","background":"none"}' label="颜色" prop="yanse">
            <el-input v-model="ruleForm.yanse" 
                placeholder="颜色" clearable ></el-input>
          </el-form-item>
          <el-form-item :style='{"border":"0px solid #dfdfdf","padding":"10px","boxShadow":"0px 0px 0px #eee","margin":"0 0 8px 0","borderRadius":"8px","background":"none"}' label="价格" prop="jiage">
            <el-input v-model="ruleForm.jiage" 
                placeholder="价格" clearable ></el-input>
          </el-form-item>
          <el-form-item :style='{"border":"0px solid #dfdfdf","padding":"10px","boxShadow":"0px 0px 0px #eee","margin":"0 0 8px 0","borderRadius":"8px","background":"none"}' label="数量" prop="shuliang">
            <el-input v-model="ruleForm.shuliang" 
                placeholder="数量" clearable ></el-input>
          </el-form-item>
          <el-form-item :style='{"border":"0px solid #dfdfdf","padding":"10px","boxShadow":"0px 0px 0px #eee","margin":"0 0 8px 0","borderRadius":"8px","background":"none"}' label="厂商" prop="changshang">
            <el-input v-model="ruleForm.changshang" 
                placeholder="厂商" clearable ></el-input>
          </el-form-item>
          <el-form-item :style='{"border":"0px solid #dfdfdf","padding":"10px","boxShadow":"0px 0px 0px #eee","margin":"0 0 8px 0","borderRadius":"8px","background":"none"}'  label="有无天窗" prop="youwutianchuang">
            <el-select v-model="ruleForm.youwutianchuang" placeholder="请选择有无天窗"  >
              <el-option
                  v-for="(item,index) in youwutianchuangOptions"
                  :key="index"
                  :label="item"
                  :value="item">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item :style='{"border":"0px solid #dfdfdf","padding":"10px","boxShadow":"0px 0px 0px #eee","margin":"0 0 8px 0","borderRadius":"8px","background":"none"}'  label="能源类型" prop="nengyuanleixing">
            <el-select v-model="ruleForm.nengyuanleixing" placeholder="请选择能源类型"  >
              <el-option
                  v-for="(item,index) in nengyuanleixingOptions"
                  :key="index"
                  :label="item"
                  :value="item">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item :style='{"border":"0px solid #dfdfdf","padding":"10px","boxShadow":"0px 0px 0px #eee","margin":"0 0 8px 0","borderRadius":"8px","background":"none"}' label="纯电续航" prop="chundianxuhang">
            <el-input v-model="ruleForm.chundianxuhang" 
                placeholder="纯电续航" clearable ></el-input>
          </el-form-item>
          <el-form-item :style='{"border":"0px solid #dfdfdf","padding":"10px","boxShadow":"0px 0px 0px #eee","margin":"0 0 8px 0","borderRadius":"8px","background":"none"}' label="基本参数" prop="jibencanshu">
            <el-input
              type="textarea"
              :rows="8"
              placeholder="基本参数"
              v-model="ruleForm.jibencanshu">
            </el-input>
          </el-form-item>
          <el-form-item :style='{"border":"0px solid #dfdfdf","padding":"10px","boxShadow":"0px 0px 0px #eee","margin":"0 0 8px 0","borderRadius":"8px","background":"none"}' label="汽车配置" prop="qichepeizhi">
            <editor 
                :style='{"minHeight":"200px","padding":"0","margin":"0","borderColor":"#ccc","backgroundColor":"#fff","borderRadius":"0","borderWidth":"0px","width":"100%","borderStyle":"solid","height":"auto"}'
                v-model="ruleForm.qichepeizhi" 
                class="editor" 
                action="file/upload">
            </editor>
          </el-form-item>

      <el-form-item :style='{"padding":"0","margin":"20px 0 20px 0"}'>
        <el-button :style='{"border":"0","cursor":"pointer","padding":"0","boxShadow":"0px 0px 0px #ccc","margin":"0 20px 0 0","color":"#fff","outline":"none","borderRadius":"0px","background":"linear-gradient(320deg, rgba(48,134,185,1) 0%, rgba(197,230,250,1) 80%, rgba(48,134,185,1) 100%),#3086b9","width":"110px","lineHeight":"40px","fontSize":"14px","height":"40px"}'  type="primary" @click="onSubmit">提交</el-button>
        <el-button :style='{"border":"0px solid #bbb","cursor":"pointer","padding":"0","boxShadow":"0px 0px 0px #eee","margin":"0","color":"#333","outline":"none","borderRadius":"0px","background":"linear-gradient(320deg, rgba(204,204,204,1) 0%, rgba(255,255,255,1) 80%, rgba(204,204,204,1) 100%),#999","width":"110px","lineHeight":"40px","fontSize":"14px","height":"40px"}' @click="back()">返回</el-button>
      </el-form-item>
    </el-form>
</div>
</template>

<script>
  export default {
    data() {
      return {
        id: '',
        baseUrl: '',
        ro:{
            cheming : false,
            pinpaileibie : false,
            qichetupian : false,
            yanse : false,
            jiage : false,
            shuliang : false,
            changshang : false,
            youwutianchuang : false,
            nengyuanleixing : false,
            qichepeizhi : false,
            chundianxuhang : false,
            jibencanshu : false,
            clicktime : false,
        },
        type: '',
        userTableName: localStorage.getItem('UserTableName'),
        ruleForm: {
          cheming: '',
          pinpaileibie: '',
          qichetupian: '',
          yanse: '',
          jiage: '',
          shuliang: '',
          changshang: '',
          youwutianchuang: '',
          nengyuanleixing: '',
          qichepeizhi: '',
          chundianxuhang: '',
          jibencanshu: '',
          clicktime: '',
        },
        pinpaileibieOptions: [],
        youwutianchuangOptions: [],
        nengyuanleixingOptions: [],
        rules: {
          cheming: [
            { required: true, message: '车名不能为空', trigger: 'blur' },
          ],
          pinpaileibie: [
            { required: true, message: '品牌类别不能为空', trigger: 'blur' },
          ],
          qichetupian: [
          ],
          yanse: [
          ],
          jiage: [
            { validator: this.$validate.isNumber, trigger: 'blur' },
          ],
          shuliang: [
            { validator: this.$validate.isIntNumer, trigger: 'blur' },
          ],
          changshang: [
          ],
          youwutianchuang: [
          ],
          nengyuanleixing: [
          ],
          qichepeizhi: [
          ],
          chundianxuhang: [
          ],
          jibencanshu: [
          ],
          clicktime: [
          ],
        },
      };
    },
    computed: {



    },
    created() {
	  //this.bg();
      let type = this.$route.query.type ? this.$route.query.type : '';
      this.init(type);
      this.baseUrl = this.$config.baseUrl;
    },
    methods: {
      getMakeZero(s) {
          return s < 10 ? '0' + s : s;
      },
      // 下载
      download(file){
        window.open(`${file}`)
      },
      // 初始化
      init(type) {
        this.type = type;
        if(type=='cross'){
          var obj = JSON.parse(localStorage.getItem('crossObj'));
          for (var o in obj){
            if(o=='cheming'){
              this.ruleForm.cheming = obj[o];
              this.ro.cheming = true;
              continue;
            }
            if(o=='pinpaileibie'){
              this.ruleForm.pinpaileibie = obj[o];
              this.ro.pinpaileibie = true;
              continue;
            }
            if(o=='qichetupian'){
              this.ruleForm.qichetupian = obj[o].split(",")[0];
              this.ro.qichetupian = true;
              continue;
            }
            if(o=='yanse'){
              this.ruleForm.yanse = obj[o];
              this.ro.yanse = true;
              continue;
            }
            if(o=='jiage'){
              this.ruleForm.jiage = obj[o];
              this.ro.jiage = true;
              continue;
            }
            if(o=='shuliang'){
              this.ruleForm.shuliang = obj[o];
              this.ro.shuliang = true;
              continue;
            }
            if(o=='changshang'){
              this.ruleForm.changshang = obj[o];
              this.ro.changshang = true;
              continue;
            }
            if(o=='youwutianchuang'){
              this.ruleForm.youwutianchuang = obj[o];
              this.ro.youwutianchuang = true;
              continue;
            }
            if(o=='nengyuanleixing'){
              this.ruleForm.nengyuanleixing = obj[o];
              this.ro.nengyuanleixing = true;
              continue;
            }
            if(o=='qichepeizhi'){
              this.ruleForm.qichepeizhi = obj[o];
              this.ro.qichepeizhi = true;
              continue;
            }
            if(o=='chundianxuhang'){
              this.ruleForm.chundianxuhang = obj[o];
              this.ro.chundianxuhang = true;
              continue;
            }
            if(o=='jibencanshu'){
              this.ruleForm.jibencanshu = obj[o];
              this.ro.jibencanshu = true;
              continue;
            }
            if(o=='clicktime'){
              this.ruleForm.clicktime = obj[o];
              this.ro.clicktime = true;
              continue;
            }
          }
        }
        // 获取用户信息
        this.$http.get(this.userTableName + '/session', {emulateJSON: true}).then(res => {
          if (res.data.code == 0) {
            var json = res.data.data;
          }
        });
        this.$http.get('option/pinpaileibie/pinpaileibie', {emulateJSON: true}).then(res => {
          if (res.data.code == 0) {
            this.pinpaileibieOptions = res.data.data;
          }
        });
        this.youwutianchuangOptions = "有,无".split(',')
        this.nengyuanleixingOptions = "纯电动,油电混合,增程式".split(',')
      },

    // 多级联动参数
      // 多级联动参数
      info(id) {
        this.$http.get('xinnengyuanqiche/detail/${id}', {emulateJSON: true}).then(res => {
          if (res.data.code == 0) {
            this.ruleForm = res.data.data;
          }
        });
      },
      // 提交
      onSubmit() {

        //更新跨表属性
        var crossuserid;
        var crossrefid;
        var crossoptnum;
        this.$refs["ruleForm"].validate(valid => {
          if(valid) {
            if(this.type=='cross'){
                 var statusColumnName = localStorage.getItem('statusColumnName');
                 var statusColumnValue = localStorage.getItem('statusColumnValue');
                 if(statusColumnName && statusColumnName!='') {
                     var obj = JSON.parse(localStorage.getItem('crossObj'));
                     if(!statusColumnName.startsWith("[")) {
                         for (var o in obj){
                             if(o==statusColumnName){
                                 obj[o] = statusColumnValue;
                             }
                         }
                         var table = localStorage.getItem('crossTable');
                         this.$http.post(table+'/update', obj).then(res => {});
                     } else {
                            crossuserid=Number(localStorage.getItem('userid'));
                            crossrefid=obj['id'];
                            crossoptnum=localStorage.getItem('statusColumnName');
                            crossoptnum=crossoptnum.replace(/\[/,"").replace(/\]/,"");
                     }
                 }
            }
            if(crossrefid && crossuserid) {
                 this.ruleForm.crossuserid=crossuserid;
                 this.ruleForm.crossrefid=crossrefid;
                 var params = {
                     page: 1,
                     limit: 10,
                     crossuserid:crossuserid,
                     crossrefid:crossrefid,
                 }
                 this.$http.get('xinnengyuanqiche/list', {
                  params: params
                 }).then(res => {
                     if(res.data.data.total>=crossoptnum) {
                         this.$message({
                          message: localStorage.getItem('tips'),
                          type: 'success',
                          duration: 1500,
                         });
                          return false;
                     } else {
                         // 跨表计算


                          this.$http.post('xinnengyuanqiche/add', this.ruleForm).then(res => {
                              if (res.data.code == 0) {
                                  this.$message({
                                      message: '操作成功',
                                      type: 'success',
                                      duration: 1500,
                                      onClose: () => {
                                          this.$router.go(-1);
                                      }
                                  });
                              } else {
                                  this.$message({
                                      message: res.data.msg,
                                      type: 'error',
                                      duration: 1500
                                  });
                              }
                          });
                     }
                 });
             } else {


                  this.$http.post('xinnengyuanqiche/add', this.ruleForm).then(res => {
                     if (res.data.code == 0) {
                          this.$message({
                              message: '操作成功',
                              type: 'success',
                              duration: 1500,
                              onClose: () => {
                                  this.$router.go(-1);
                              }
                          });
                      } else {
                          this.$message({
                              message: res.data.msg,
                              type: 'error',
                              duration: 1500
                          });
                      }
                  });
             }
          }
        });
      },
      // 获取uuid
      getUUID () {
        return new Date().getTime();
      },
      // 返回
      back() {
        this.$router.go(-1);
      },
      qichetupianUploadChange(fileUrls) {
          this.ruleForm.qichetupian = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");;
      },
    }
  };
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.el-date-editor.el-input {
		width: auto;
	}
	
	.add-update-preview .el-form-item ::v-deep .el-form-item__label {
	  padding: 0 10px 0 0;
	  color: #666;
	  font-weight: 500;
	  width: 80px;
	  font-size: 14px;
	  line-height: 40px;
	  text-align: right;
	}
	
	.add-update-preview .el-form-item ::v-deep .el-form-item__content {
	  margin-left: 80px;
	}
	
	.add-update-preview .el-input ::v-deep .el-input__inner {
	  border-radius: 4px;
	  padding: 0 12px;
	  outline: none;
	  color: #666;
	  background: linear-gradient(320deg, rgba(204,204,204,.0) 0%, rgba(255,255,255,.9) 80%, rgba(204,204,204,.0) 100%);
	  width: 400px;
	  font-size: 14px;
	  border-color: #9dcde9;
	  border-width: 0 0 2px;
	  border-style: dotted;
	  height: 40px;
	}
	
	.add-update-preview .el-select ::v-deep .el-input__inner {
	  border-radius: 4px;
	  padding: 0 10px;
	  outline: none;
	  color: #666;
	  background: linear-gradient(320deg, rgba(204,204,204,.0) 0%, rgba(255,255,255,.9) 80%, rgba(204,204,204,.0) 100%);
	  width: 200px;
	  font-size: 14px;
	  border-color: #9dcde9;
	  border-width: 0 0 2px;
	  border-style: dotted;
	  height: 40px;
	}
	
	.add-update-preview .el-date-editor ::v-deep .el-input__inner {
	  border-radius: 4px;
	  padding: 0 10px 0 30px;
	  outline: none;
	  color: #666;
	  background: linear-gradient(320deg, rgba(204,204,204,.0) 0%, rgba(255,255,255,.9) 80%, rgba(204,204,204,.0) 100%);
	  width: 200px;
	  font-size: 14px;
	  border-color: #9dcde9;
	  border-width: 0 0 2px;
	  border-style: dotted;
	  height: 40px;
	}
	
	.add-update-preview ::v-deep .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
	}
	
	.add-update-preview ::v-deep .upload .upload-img {
	  cursor: pointer;
	  border: 2px dotted #9dcde9;
	  border-radius: 6px;
	  color: #9dcde9;
	  background: linear-gradient(320deg, rgba(204,204,204,.0) 0%, rgba(255,255,255,.9) 80%, rgba(204,204,204,.0) 100%);
	  width: 200px;
	  font-size: 32px;
	  line-height: 100px;
	  text-align: center;
	  height: auto;
	}
	
	.add-update-preview ::v-deep .el-upload-list .el-upload-list__item {
	  cursor: pointer;
	  border: 2px dotted #9dcde9;
	  border-radius: 6px;
	  color: #9dcde9;
	  background: linear-gradient(320deg, rgba(204,204,204,.0) 0%, rgba(255,255,255,.9) 80%, rgba(204,204,204,.0) 100%);
	  width: 200px;
	  font-size: 32px;
	  line-height: 100px;
	  text-align: center;
	  height: auto;
	}
	
	.add-update-preview ::v-deep .el-upload .el-icon-plus {
	  cursor: pointer;
	  border: 2px dotted #9dcde9;
	  border-radius: 6px;
	  color: #9dcde9;
	  background: linear-gradient(320deg, rgba(204,204,204,.0) 0%, rgba(255,255,255,.9) 80%, rgba(204,204,204,.0) 100%);
	  width: 200px;
	  font-size: 32px;
	  line-height: 100px;
	  text-align: center;
	  height: auto;
	}
	
	.add-update-preview .el-textarea ::v-deep .el-textarea__inner {
	  border: 2px dotted #9dcde9;
	  border-radius: 4px;
	  padding: 12px;
	  outline: none;
	  color: #666;
	  background: linear-gradient(320deg, rgba(204,204,204,.0) 0%, rgba(255,255,255,.9) 80%, rgba(204,204,204,.0) 100%);
	  width: 400px;
	  font-size: 14px;
	  min-height: 120px;
	}
</style>
