<template>
	<div class="addEdit-block" :style='{"padding":"30px"}' style="width: 100%;">
		<el-form
			:style='{"padding":"30px","boxShadow":"0px 4px 10px 0px rgba(0,0,0,0.3020)","margin":"0 auto","borderColor":"rgba(230, 242, 254, 1)","borderRadius":"6px","borderWidth":"30px 0 0 0","width":"90%","borderStyle":"solid"}'
			class="add-update-preview"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="140px"
		>
			<template >
				<el-form-item :style='{"width":"45%","margin":"0 2% 20px 0","display":"inline-block"}' class="input" v-if="type!='info'"  label="车名" prop="cheming">
					<el-input v-model="ruleForm.cheming" placeholder="车名" clearable  :readonly="ro.cheming"></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"45%","margin":"0 2% 20px 0","display":"inline-block"}' v-else class="input" label="车名" prop="cheming">
					<el-input v-model="ruleForm.cheming" placeholder="车名" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"45%","margin":"0 2% 20px 0","display":"inline-block"}' class="select" v-if="type!='info'"  label="品牌类别" prop="pinpaileibie">
					<el-select :disabled="ro.pinpaileibie" v-model="ruleForm.pinpaileibie" placeholder="请选择品牌类别" >
						<el-option
							v-for="(item,index) in pinpaileibieOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item :style='{"width":"45%","margin":"0 2% 20px 0","display":"inline-block"}' v-else class="input" label="品牌类别" prop="pinpaileibie">
					<el-input v-model="ruleForm.pinpaileibie"
						placeholder="品牌类别" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"45%","margin":"0 2% 20px 0","display":"inline-block"}' class="upload" v-if="type!='info' && !ro.qichetupian" label="汽车图片" prop="qichetupian">
					<file-upload
						tip="点击上传汽车图片"
						action="file/upload"
						:limit="3"
						:multiple="true"
						:fileUrls="ruleForm.qichetupian?ruleForm.qichetupian:''"
						@change="qichetupianUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item :style='{"width":"45%","margin":"0 2% 20px 0","display":"inline-block"}' class="upload" v-else-if="ruleForm.qichetupian" label="汽车图片" prop="qichetupian">
					<img v-if="ruleForm.qichetupian.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.qichetupian.split(',')[0]" width="100" height="100">
					<img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.qichetupian.split(',')" :src="$base.url+item" width="100" height="100">
				</el-form-item>
				<el-form-item :style='{"width":"45%","margin":"0 2% 20px 0","display":"inline-block"}' class="input" v-if="type!='info'"  label="颜色" prop="yanse">
					<el-input v-model="ruleForm.yanse" placeholder="颜色" clearable  :readonly="ro.yanse"></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"45%","margin":"0 2% 20px 0","display":"inline-block"}' v-else class="input" label="颜色" prop="yanse">
					<el-input v-model="ruleForm.yanse" placeholder="颜色" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"45%","margin":"0 2% 20px 0","display":"inline-block"}' class="input" v-if="type!='info'"  label="价格" prop="jiage">
					<el-input v-model="ruleForm.jiage" placeholder="价格" clearable  :readonly="ro.jiage"></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"45%","margin":"0 2% 20px 0","display":"inline-block"}' v-else class="input" label="价格" prop="jiage">
					<el-input v-model="ruleForm.jiage" placeholder="价格" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"45%","margin":"0 2% 20px 0","display":"inline-block"}' class="input" v-if="type!='info'"  label="数量" prop="shuliang">
					<el-input v-model="ruleForm.shuliang" placeholder="数量" clearable  :readonly="ro.shuliang"></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"45%","margin":"0 2% 20px 0","display":"inline-block"}' v-else class="input" label="数量" prop="shuliang">
					<el-input v-model="ruleForm.shuliang" placeholder="数量" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"45%","margin":"0 2% 20px 0","display":"inline-block"}' class="input" v-if="type!='info'"  label="厂商" prop="changshang">
					<el-input v-model="ruleForm.changshang" placeholder="厂商" clearable  :readonly="ro.changshang"></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"45%","margin":"0 2% 20px 0","display":"inline-block"}' v-else class="input" label="厂商" prop="changshang">
					<el-input v-model="ruleForm.changshang" placeholder="厂商" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"45%","margin":"0 2% 20px 0","display":"inline-block"}' class="select" v-if="type!='info'"  label="有无天窗" prop="youwutianchuang">
					<el-select :disabled="ro.youwutianchuang" v-model="ruleForm.youwutianchuang" placeholder="请选择有无天窗" >
						<el-option
							v-for="(item,index) in youwutianchuangOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item :style='{"width":"45%","margin":"0 2% 20px 0","display":"inline-block"}' v-else class="input" label="有无天窗" prop="youwutianchuang">
					<el-input v-model="ruleForm.youwutianchuang"
						placeholder="有无天窗" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"45%","margin":"0 2% 20px 0","display":"inline-block"}' class="select" v-if="type!='info'"  label="能源类型" prop="nengyuanleixing">
					<el-select :disabled="ro.nengyuanleixing" v-model="ruleForm.nengyuanleixing" placeholder="请选择能源类型" >
						<el-option
							v-for="(item,index) in nengyuanleixingOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item :style='{"width":"45%","margin":"0 2% 20px 0","display":"inline-block"}' v-else class="input" label="能源类型" prop="nengyuanleixing">
					<el-input v-model="ruleForm.nengyuanleixing"
						placeholder="能源类型" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"45%","margin":"0 2% 20px 0","display":"inline-block"}' class="input" v-if="type!='info'"  label="纯电续航" prop="chundianxuhang">
					<el-input v-model="ruleForm.chundianxuhang" placeholder="纯电续航" clearable  :readonly="ro.chundianxuhang"></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"45%","margin":"0 2% 20px 0","display":"inline-block"}' v-else class="input" label="纯电续航" prop="chundianxuhang">
					<el-input v-model="ruleForm.chundianxuhang" placeholder="纯电续航" readonly></el-input>
				</el-form-item>
			</template>
				<el-form-item :style='{"width":"45%","margin":"0 2% 20px 0","display":"inline-block"}' class="textarea" v-if="type!='info'" label="基本参数" prop="jibencanshu">
					<el-input
					  style="min-width: 200px; max-width: 600px;"
					  type="textarea"
					  :rows="8"
					  placeholder="基本参数"
					  v-model="ruleForm.jibencanshu" >
					</el-input>
				</el-form-item>
				<el-form-item :style='{"width":"45%","margin":"0 2% 20px 0","display":"inline-block"}' v-else-if="ruleForm.jibencanshu" label="基本参数" prop="jibencanshu">
					<span :style='{"fontSize":"14px","lineHeight":"40px","color":"#333","fontWeight":"500","display":"inline-block"}'>{{ruleForm.jibencanshu}}</span>
				</el-form-item>
				<el-form-item :style='{"width":"45%","margin":"0 2% 20px 0","display":"inline-block"}' v-if="type!='info'"  label="汽车配置" prop="qichepeizhi">
					<editor 
						style="min-width: 200px; max-width: 600px;"
						v-model="ruleForm.qichepeizhi" 
						class="editor" 
						action="file/upload">
					</editor>
				</el-form-item>
				<el-form-item :style='{"width":"45%","margin":"0 2% 20px 0","display":"inline-block"}' v-else-if="ruleForm.qichepeizhi" label="汽车配置" prop="qichepeizhi">
                    <span :style='{"fontSize":"14px","lineHeight":"40px","color":"#333","fontWeight":"500","display":"inline-block"}' v-html="ruleForm.qichepeizhi"></span>
                </el-form-item>
			<el-form-item :style='{"padding":"0","margin":"0"}' class="btn">
				<el-button :style='{"border":"0","cursor":"pointer","padding":"0","margin":"0 20px 0 0","outline":"none","color":"#333","borderRadius":"4px","background":"rgba(185, 216, 255, 1)","width":"128px","lineHeight":"40px","fontSize":"14px","height":"40px"}'  v-if="type!='info'" type="primary" class="btn-success" @click="onSubmit">提交</el-button>
				<el-button :style='{"border":"0px solid rgba(64, 158, 255, 1)","cursor":"pointer","padding":"0","margin":"0","outline":"none","color":"#fff","borderRadius":"4px","background":"rgba(198, 205, 214, 1)","width":"128px","lineHeight":"40px","fontSize":"14px","height":"40px"}' v-if="type!='info'" class="btn-close" @click="back()">取消</el-button>
				<el-button :style='{"border":"0px solid rgba(64, 158, 255, 1)","cursor":"pointer","padding":"0","margin":"0","outline":"none","color":"#fff","borderRadius":"4px","background":"rgba(198, 205, 214, 1)","width":"128px","lineHeight":"40px","fontSize":"14px","height":"40px"}' v-if="type=='info'" class="btn-close" @click="back()">返回</el-button>
			</el-form-item>
		</el-form>
    

  </div>
</template>
<script>
// 数字，邮件，手机，url，身份证校验
import { isNumber,isIntNumer,isEmail,isPhone, isMobile,isURL,checkIdCard } from "@/utils/validate";
export default {
	data() {
		let self = this
		var validateIdCard = (rule, value, callback) => {
			if(!value){
				callback();
			} else if (!checkIdCard(value)) {
				callback(new Error("请输入正确的身份证号码"));
			} else {
				callback();
			}
		};
		var validateUrl = (rule, value, callback) => {
			if(!value){
				callback();
			} else if (!isURL(value)) {
				callback(new Error("请输入正确的URL地址"));
			} else {
				callback();
			}
		};
		var validateMobile = (rule, value, callback) => {
			if(!value){
				callback();
			} else if (!isMobile(value)) {
				callback(new Error("请输入正确的手机号码"));
			} else {
				callback();
			}
		};
		var validatePhone = (rule, value, callback) => {
			if(!value){
				callback();
			} else if (!isPhone(value)) {
				callback(new Error("请输入正确的电话号码"));
			} else {
				callback();
			}
		};
		var validateEmail = (rule, value, callback) => {
			if(!value){
				callback();
			} else if (!isEmail(value)) {
				callback(new Error("请输入正确的邮箱地址"));
			} else {
				callback();
			}
		};
		var validateNumber = (rule, value, callback) => {
			if(!value){
				callback();
			} else if (!isNumber(value)) {
				callback(new Error("请输入数字"));
			} else {
				callback();
			}
		};
		var validateIntNumber = (rule, value, callback) => {
			if(!value){
				callback();
			} else if (!isIntNumer(value)) {
				callback(new Error("请输入整数"));
			} else {
				callback();
			}
		};
		return {
			id: '',
			type: '',
			
			
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
					{ validator: validateNumber, trigger: 'blur' },
				],
				shuliang: [
					{ validator: validateIntNumber, trigger: 'blur' },
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
			}
		};
	},
	props: ["parent"],
	computed: {



	},
	created() {
	},
	methods: {
		
		// 下载
		download(file){
			window.open(`${file}`)
		},
		// 初始化
		init(id,type) {
			if (id) {
				this.id = id;
				this.type = type;
			}
			if(this.type=='info'||this.type=='else'){
				this.info(id);
			}else if(this.type=='logistics'){
				this.logistics=false;
				this.info(id);
			}else if(this.type=='cross'){
				var obj = this.$storage.getObj('crossObj');
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
							this.ruleForm.qichetupian = obj[o];
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
			this.$http({
				url: `${this.$storage.get('sessionTable')}/session`,
				method: "get"
			}).then(({ data }) => {
				if (data && data.code === 0) {
					
					var json = data.data;
				} else {
					this.$message.error(data.msg);
				}
			});
			
            this.$http({
				url: `option/pinpaileibie/pinpaileibie`,
				method: "get"
            }).then(({ data }) => {
				if (data && data.code === 0) {
					this.pinpaileibieOptions = data.data;
				} else {
					this.$message.error(data.msg);
				}
            });
            this.youwutianchuangOptions = "有,无".split(',')
            this.nengyuanleixingOptions = "纯电动,油电混合,增程式".split(',')
			
		},
    // 多级联动参数

    info(id) {
      this.$http({
        url: `xinnengyuanqiche/info/${id}`,
        method: "get"
      }).then(({ data }) => {
        if (data && data.code === 0) {
        this.ruleForm = data.data;
        //解决前台上传图片后台不显示的问题
        let reg=new RegExp('../../../upload','g')//g代表全部
        this.ruleForm.qichepeizhi = this.ruleForm.qichepeizhi.replace(reg,'../../../springbootsnu6t/upload');
        } else {
          this.$message.error(data.msg);
        }
      });
    },


    // 提交
    onSubmit() {






	if(this.ruleForm.qichetupian!=null) {
		this.ruleForm.qichetupian = this.ruleForm.qichetupian.replace(new RegExp(this.$base.url,"g"),"");
	}





















var objcross = this.$storage.getObj('crossObj');

      //更新跨表属性
       var crossuserid;
       var crossrefid;
       var crossoptnum;
       if(this.type=='cross'){
                var statusColumnName = this.$storage.get('statusColumnName');
                var statusColumnValue = this.$storage.get('statusColumnValue');
                if(statusColumnName!='') {
                        var obj = this.$storage.getObj('crossObj');
                       if(statusColumnName && !statusColumnName.startsWith("[")) {
                               for (var o in obj){
                                 if(o==statusColumnName){
                                   obj[o] = statusColumnValue;
                                 }
                               }
                               var table = this.$storage.get('crossTable');
                             this.$http({
                                 url: `${table}/update`,
                                 method: "post",
                                 data: obj
                               }).then(({ data }) => {});
                       } else {
                               crossuserid=this.$storage.get('userid');
                               crossrefid=obj['id'];
                               crossoptnum=this.$storage.get('statusColumnName');
                               crossoptnum=crossoptnum.replace(/\[/,"").replace(/\]/,"");
                        }
                }
        }
       this.$refs["ruleForm"].validate(valid => {
         if (valid) {
		 if(crossrefid && crossuserid) {
			 this.ruleForm.crossuserid = crossuserid;
			 this.ruleForm.crossrefid = crossrefid;
			let params = { 
				page: 1, 
				limit: 10, 
				crossuserid:this.ruleForm.crossuserid,
				crossrefid:this.ruleForm.crossrefid,
			} 
			this.$http({ 
				url: "xinnengyuanqiche/page", 
				method: "get", 
				params: params 
			}).then(({ 
				data 
			}) => { 
				if (data && data.code === 0) { 
				       if(data.data.total>=crossoptnum) {
					     this.$message.error(this.$storage.get('tips'));
					       return false;
				       } else {
					 this.$http({
					   url: `xinnengyuanqiche/${!this.ruleForm.id ? "save" : "update"}`,
					   method: "post",
					   data: this.ruleForm
					 }).then(({ data }) => {
					   if (data && data.code === 0) {
					     this.$message({
					       message: "操作成功",
					       type: "success",
					       duration: 1500,
					       onClose: () => {
						 this.parent.showFlag = true;
						 this.parent.addOrUpdateFlag = false;
						 this.parent.xinnengyuanqicheCrossAddOrUpdateFlag = false;
						 this.parent.search();
						 this.parent.contentStyleChange();
					       }
					     });
					   } else {
					     this.$message.error(data.msg);
					   }
					 });

				       }
				} else { 
				} 
			});
		 } else {
			 this.$http({
			   url: `xinnengyuanqiche/${!this.ruleForm.id ? "save" : "update"}`,
			   method: "post",
			   data: this.ruleForm
			 }).then(({ data }) => {
			   if (data && data.code === 0) {
			     this.$message({
			       message: "操作成功",
			       type: "success",
			       duration: 1500,
			       onClose: () => {
				 this.parent.showFlag = true;
				 this.parent.addOrUpdateFlag = false;
				 this.parent.xinnengyuanqicheCrossAddOrUpdateFlag = false;
				 this.parent.search();
				 this.parent.contentStyleChange();
			       }
			     });
			   } else {
			     this.$message.error(data.msg);
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
      this.parent.showFlag = true;
      this.parent.addOrUpdateFlag = false;
      this.parent.xinnengyuanqicheCrossAddOrUpdateFlag = false;
      this.parent.contentStyleChange();
    },
    qichetupianUploadChange(fileUrls) {
	    this.ruleForm.qichetupian = fileUrls;
    },
  }
};
</script>
<style lang="scss" scoped>
	.amap-wrapper {
		width: 100%;
		height: 500px;
	}
	
	.search-box {
		position: absolute;
	}
	
	.el-date-editor.el-input {
		width: auto;
	}
	
	.add-update-preview .el-form-item ::v-deep .el-form-item__label {
	  	  padding: 0 20px 0 0;
	  	  color: #666;
	  	  font-weight: 500;
	  	  width: 140px;
	  	  font-size: 14px;
	  	  line-height: 40px;
	  	  text-align: right;
	  	}
	
	.add-update-preview .el-form-item ::v-deep .el-form-item__content {
	  margin-left: 140px;
	}
	
	.add-update-preview .el-input ::v-deep .el-input__inner {
	  	  border:  2px solid rgba(198, 205, 214, 1);
	  	  border-radius: 4px;
	  	  padding: 0 12px;
	  	  outline: none;
	  	  color: #333;
	  	  width: 250px;
	  	  font-size: 14px;
	  	  height: 40px;
	  	}
	
	.add-update-preview .el-select ::v-deep .el-input__inner {
	  	  border:  2px solid rgba(198, 205, 214, 1);
	  	  border-radius: 4px;
	  	  padding: 0 10px;
	  	  outline: none;
	  	  color: #333;
	  	  width: 200px;
	  	  font-size: 14px;
	  	  height: 40px;
	  	}
	
	.add-update-preview .el-date-editor ::v-deep .el-input__inner {
	  	  border:  2px solid rgba(198, 205, 214, 1);
	  	  border-radius: 4px;
	  	  padding: 0 10px 0 30px;
	  	  outline: none;
	  	  color: #333;
	  	  width: 200px;
	  	  font-size: 14px;
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
	  	  border:  2px dashed rgba(198, 205, 214, 1);
	  	  cursor: pointer;
	  	  border-radius: 6px;
	  	  color: rgba(198, 205, 214, 1);
	  	  width: 150px;
	  	  font-size: 32px;
	  	  line-height: 150px;
	  	  text-align: center;
	  	  height: 150px;
	  	}
	
	.add-update-preview ::v-deep .el-upload-list .el-upload-list__item {
	  	  border:  2px dashed rgba(198, 205, 214, 1);
	  	  cursor: pointer;
	  	  border-radius: 6px;
	  	  color: rgba(198, 205, 214, 1);
	  	  width: 150px;
	  	  font-size: 32px;
	  	  line-height: 150px;
	  	  text-align: center;
	  	  height: 150px;
	  	}
	
	.add-update-preview ::v-deep .el-upload .el-icon-plus {
	  	  border:  2px dashed rgba(198, 205, 214, 1);
	  	  cursor: pointer;
	  	  border-radius: 6px;
	  	  color: rgba(198, 205, 214, 1);
	  	  width: 150px;
	  	  font-size: 32px;
	  	  line-height: 150px;
	  	  text-align: center;
	  	  height: 150px;
	  	}
	
	.add-update-preview .el-textarea ::v-deep .el-textarea__inner {
	  	  border:  2px dashed rgba(198, 205, 214, 1);
	  	  border-radius: 4px;
	  	  padding: 12px;
	  	  outline: none;
	  	  color: #333;
	  	  width: 300px;
	  	  font-size: 14px;
	  	  height: 120px;
	  	}
</style>
