<template>
	<div class="main-containers">
		<div class="top-container" :style='{"boxShadow":"0 1px 0px rgba(64, 158, 255, .3)","padding":"0px 0 0","margin":"0 auto","alignItems":"center","display":"flex","right":"0","justifyContent":"space-around","overflow":"hidden","top":"0","left":"0","background":"url(http://codegen.caihongy.cn/20221231/a49ce5974eb04feb9b09f2c15f0e2bb2.jpg) no-repeat center bottom / 100% auto,#fff","width":"100%","lineHeight":"64px","position":"fixed","height":"64px","zIndex":"1002"}'>
			<img v-if='false' :style='{"width":"44px","objectFit":"cover","borderRadius":"100%","display":"block","height":"44px"}' src='http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg'>
			<div v-if="true" :style='{"margin":"0 0 0 210px","color":"#333","textAlign":"center","background":"none","display":"inline-block","width":"calc(100% - 370px)","fontSize":"22px"}'>新能源汽车AI综合服务平台</div>
			<div>
				<div v-if="true" :style='{"color":"#666","fontSize":"14px","display":"inline-block"}'></div>
				<div v-if="Token" :style='{"color":"#666","fontSize":"14px","display":"inline-block"}'>{{username}}</div>
				<el-button v-if="!Token" @click="toLogin()" :style='{"border":"0px solid #00adb5","padding":"0 12px","boxShadow":"0px 0px 0px #ccc","margin":"10px 0 12px","color":"#fff","borderRadius":"2px","background":"#3086b9","display":"inline-block","fontSize":"14px","lineHeight":"32px","height":"32px"}'>登录/注册</el-button>
                <el-button v-if="Token" @click="logout" :style='{"border":"0px solid #00adb5","padding":"0 12px","boxShadow":"0px 0px 0px #bbb","margin":"10px 0 0 10px","color":"#666","borderRadius":"2px","background":"#fff","display":"inline-block","fontSize":"14px","lineHeight":"32px","height":"32px"}'>退出</el-button>
			</div>
		</div>
		
		<div class="menu-preview" :style='{"padding":"64px 0 0","boxShadow":"0px 0 6px  #aaa","overflow":"hidden","top":"0","left":"0","background":"#98c6e2","bottom":"0","width":"210px","fontSize":"0px","position":"fixed","height":"100%","zIndex":"1001"}'>
			<el-scrollbar wrap-class="scrollbar-wrapper">
				<el-menu class="el-menu-vertical-demo" :default-active="activeIndex" :style='{"border":0,"padding":"0","listStyle":"none","margin":"0","position":"relative","background":"none"}' :unique-opened="true" mode="vertical" :router="true" @select="handleSelect">
					<el-image v-if="false" :style='{"width":"44px","margin":"10px auto","objectFit":"cover","borderRadius":"100%","display":"block","height":"44px"}' src="http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg" fit="cover"></el-image>
					<el-menu-item v-for="(menu, index) in menuList" :index="index + ''" :key="index" :route="menu.url">
						<i v-if="true" :style='{"padding":"0 10px","margin":"0","color":"inherit","width":"14px","lineHeight":"44px","fontSize":"14px","height":"44px"}' :class="iconArr[index]"></i>
						<span :style='{"padding":"0 10px","lineHeight":"44px","fontSize":"14px","color":"inherit","height":"44px"}'>{{menu.name}}</span>
					</el-menu-item>
					<el-menu-item @click="goBackend">
						<i v-if="true" :style='{"padding":"0 10px","margin":"0","color":"inherit","width":"14px","lineHeight":"44px","fontSize":"14px","height":"44px"}' class="el-icon-box"></i>
						<span :style='{"padding":"0 10px","lineHeight":"44px","fontSize":"14px","color":"inherit","height":"44px"}'>后台管理</span>
					</el-menu-item>
					<el-menu-item @click="goChat">
						<i v-if="true" :style='{"padding":"0 10px","margin":"0","color":"inherit","width":"14px","lineHeight":"44px","fontSize":"14px","height":"44px"}' class="el-icon-service"></i>
						<span :style='{"padding":"0 10px","lineHeight":"44px","fontSize":"14px","color":"inherit","height":"44px"}'>在线咨询</span>
					</el-menu-item>
          <el-menu-item @click="showChatPopup=true">
						<i v-if="true" :style='{"padding":"0 10px","margin":"0","color":"inherit","width":"14px","lineHeight":"44px","fontSize":"14px","height":"44px"}' class="el-icon-service"></i>
						<span :style='{"padding":"0 10px","lineHeight":"44px","fontSize":"14px","color":"inherit","height":"44px"}'>智能助手</span>
					</el-menu-item>
					<el-menu-item :index="menuList.length + 2 + ''" v-if="Token && notAdmin" @click="goMenu('/index/center')">
						<i v-if="true" :style='{"padding":"0 10px","margin":"0","color":"inherit","width":"14px","lineHeight":"44px","fontSize":"14px","height":"44px"}' class="el-icon-user"></i>
						<span :style='{"padding":"0 10px","lineHeight":"44px","fontSize":"14px","color":"inherit","height":"44px"}'>个人中心</span>
					</el-menu-item>
				</el-menu>
			</el-scrollbar>
		</div>
		
		<div class="body-containers" :style='"vertical" == "vertical" ? {"minHeight":"100vh","padding":"64px 0 0","margin":"0 0 0 210px","position":"relative","background":"url(http://codegen.caihongy.cn/20230101/3b575ca380e14124bc1945e639178e9d.png) fixed no-repeat center top / 100% 100%,rgba(64, 158, 255, .3)","display":"block"} : {"minHeight":"100vh","padding":"0","margin":"0","position":"relative","background":"#f6f6f6"}'>
			
			<div class="banner-preview" :style='{"width":"100%","padding":"20px 20px 0","margin":"0","height":"400px"}'>
				<el-carousel :style='{"width":"100%","margin":"0 auto"}' trigger="click" indicator-position="inside" arrow="always" type="default" direction="horizontal" height="400px" :autoplay="true" :interval="5000" :loop="true">
					<el-carousel-item :style='{"borderRadius":"0","width":"100%","height":"100%"}' v-for="item in carouselList" :key="item.id">
						<el-image :style='{"objectFit":"cover","width":"100%","height":"100%"}' :src="baseUrl + item.value" fit="cover"></el-image>
					</el-carousel-item>
				</el-carousel>
			</div>
			
			<router-view></router-view>
			
			<div class="bottom-preview" :style='{"minHeight":"150px","padding":"20px 0","alignItems":"center","color":"#fff","background":"#3086b9","flexDirection":"column","display":"flex","width":"100%","justifyContent":"center"}'>
			    <img :style='{"width":"44px","objectFit":"cover","borderRadius":"100%","display":"none","height":"44px"}' src="http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg" >
			    <div :style='{"margin":"0","fontSize":"14px","lineHeight":"28px","color":"#fff"}'></div>
			    <div :style='{"margin":"10px 0 0","fontSize":"14px","lineHeight":"28px","color":"#fff"}'></div>
			    <div :style='{"margin":"10px 0 0","fontSize":"14px","lineHeight":"28px","color":"#fff"}'></div>
			</div>
		</div>
		
        <el-dialog title="在线咨询" :visible.sync="chatFormVisible" width="600px" :before-close="chatClose">
            <div class="chat-content"
                style="background: #f5f5f5;padding: 10px;box-sizing: border-box;overflow-y: scroll;max-height: 40vh;min-height: 40vh;">
                <div :class="{'right-content': item.reply == '' || item.reply == null, 'left-content': item.ask == '' || item.ask == null}"
                    v-for="item in chatList" :key="item.id">
                    <el-tag :type="(item.ask == '' || item.ask == null) ? 'warning' : 'success'" style="margin: 10px;">
                        {{(item.ask == '' || item.ask == null) ? item.reply : item.ask}}
                    </el-tag>
                </div>
            </div>
            <div slot="footer" class="dialog-footer">
                <el-input v-model="form.ask" placeholder="请输入内容" style="width: calc(100% - 80px);float: left;">
                </el-input>
                <el-button type="primary" @click="addChat">发送</el-button>
            </div>
        </el-dialog>
		 <!-- AI 聊天弹窗（Element UI Dialog 组件） -->
    <el-dialog
      title="智能助手"
      :visible.sync="showChatPopup"
      width="400px"
      top="10%"
      :close-on-click-modal="false"
      :show-close="true"
    >
      <!-- 聊天消息区域 -->
      <div class="chat-messages" ref="messagesContainer">
        <!-- 欢迎消息 -->
        <div class="welcome-message" v-if="messageList.length === 0">
          <i class="el-icon-service"></i>
          <p>你好！有什么可以帮你的？</p>
        </div>

        <!-- 消息列表 -->
        <div 
          class="message-item" 
          v-for="(msg, index) in messageList" 
          :key="index"
          :class="{ 'user-message': msg.role === 'user' }"
        >
          <!-- 头像 -->
          <div class="message-avatar">
            <i :class="msg.role === 'user' ? 'el-icon-user' : 'el-icon-service'"></i>
          </div>
          <!-- 消息内容 -->
          <div class="message-content">
            <div class="message-bubble" :class="msg.role === 'user' ? 'user-bubble' : 'ai-bubble'">
              <p>{{ msg.content }}</p>
              <!-- 加载中状态 -->
              <div class="loading" v-if="msg.loading">
                <span class="dot"></span>
                <span class="dot"></span>
                <span class="dot"></span>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 输入区域 -->
      <div class="chat-input-area">
        <el-input
          v-model="inputMessage"
          type="textarea"
          :rows="3"
          placeholder="请输入你的问题..."
          @keydown.enter.prevent="sendMessage"
          :disabled="isSending"
          class="message-input"
        ></el-input>
        <el-button 
          type="primary"
          @click="sendMessage"
          :disabled="!inputMessage.trim() || isSending"
          class="send-btn"
          icon="el-icon-paper-plane"
          style="margin-top: 10px;"
        >
          发送
        </el-button>
      </div>
    </el-dialog>
	</div>
</template>

<script>
import Vue from 'vue'
export default {
    data() {
		return {
            activeIndex: '0',
			roleMenus: [{"backMenu":[{"child":[{"appFrontIcon":"cuIcon-paint","buttons":["新增","查看","修改","删除"],"menu":"用户","menuJump":"列表","tableName":"yonghu"}],"menu":"用户管理"},{"child":[{"appFrontIcon":"cuIcon-goods","buttons":["新增","查看","修改","删除"],"menu":"品牌类别","menuJump":"列表","tableName":"pinpaileibie"}],"menu":"品牌类别管理"},{"child":[{"appFrontIcon":"cuIcon-news","buttons":["查看","新增","修改","删除","品牌类别统计","汽车库存统计","查看评论","首页总数","首页统计"],"menu":"新能源汽车","menuJump":"列表","tableName":"xinnengyuanqiche"}],"menu":"新能源汽车管理"},{"child":[{"appFrontIcon":"cuIcon-vipcard","buttons":["查看","删除","审核","每月销售额统计","首页总数","首页统计","修改","接单配送"],"menu":"汽车订单","menuJump":"列表","tableName":"qichedingdan"}],"menu":"汽车订单管理"},{"child":[{"appFrontIcon":"cuIcon-vip","buttons":["查看","删除","修改","更新配送"],"menu":"配送订单","menuJump":"列表","tableName":"peisongdingdan"}],"menu":"配送订单管理"},{"child":[{"appFrontIcon":"cuIcon-time","buttons":["查看","修改","删除"],"menu":"配送信息","menuJump":"列表","tableName":"peisongxinxi"}],"menu":"配送信息管理"},{"child":[{"appFrontIcon":"cuIcon-rank","buttons":["查看","删除","审核"],"menu":"预约看车","menuJump":"列表","tableName":"yuyuekanche"}],"menu":"预约看车管理"},{"child":[{"appFrontIcon":"cuIcon-send","buttons":["查看","修改"],"menu":"系统简介","tableName":"systemintro"},{"appFrontIcon":"cuIcon-present","buttons":["查看","修改"],"menu":"轮播图管理","tableName":"config"},{"appFrontIcon":"cuIcon-news","buttons":["新增","查看","修改","删除"],"menu":"公告信息","tableName":"news"},{"appFrontIcon":"cuIcon-service","buttons":["新增","查看","修改","删除"],"menu":"在线咨询","tableName":"chat"},{"appFrontIcon":"cuIcon-clothes","buttons":["查看","修改"],"menu":"关于我们","tableName":"aboutus"}],"menu":"系统管理"}],"frontMenu":[{"child":[{"appFrontIcon":"cuIcon-full","buttons":["查看","购买汽车","预约看车"],"menu":"新能源汽车列表","menuJump":"列表","tableName":"xinnengyuanqiche"}],"menu":"新能源汽车模块"}],"hasBackLogin":"是","hasBackRegister":"否","hasFrontLogin":"否","hasFrontRegister":"否","roleName":"管理员","tableName":"users"},{"backMenu":[{"child":[{"appFrontIcon":"cuIcon-vipcard","buttons":["查看","删除","支付"],"menu":"汽车订单","menuJump":"列表","tableName":"qichedingdan"}],"menu":"汽车订单管理"},{"child":[{"appFrontIcon":"cuIcon-vip","buttons":["查看"],"menu":"配送订单","menuJump":"列表","tableName":"peisongdingdan"}],"menu":"配送订单管理"},{"child":[{"appFrontIcon":"cuIcon-time","buttons":["查看"],"menu":"配送信息","menuJump":"列表","tableName":"peisongxinxi"}],"menu":"配送信息管理"},{"child":[{"appFrontIcon":"cuIcon-rank","buttons":["查看","删除"],"menu":"预约看车","menuJump":"列表","tableName":"yuyuekanche"}],"menu":"预约看车管理"}],"frontMenu":[{"child":[{"appFrontIcon":"cuIcon-full","buttons":["查看","购买汽车","预约看车"],"menu":"新能源汽车列表","menuJump":"列表","tableName":"xinnengyuanqiche"}],"menu":"新能源汽车模块"}],"hasBackLogin":"是","hasBackRegister":"否","hasFrontLogin":"是","hasFrontRegister":"是","roleName":"用户","tableName":"yonghu"}],
			baseUrl: '',
			carouselList: [],
			menuList: [],
			chatFormVisible: false,
			chatList: [],
			form: {
				ask: '',
				userid: localStorage.getItem('userid')
			},
			Token: localStorage.getItem('Token'),
            username: localStorage.getItem('username'),
            notAdmin: localStorage.getItem('sessionTable')!='"users"',
			timer: '',
			iconArr: [
				'el-icon-star-off',
				'el-icon-goods',
				'el-icon-warning',
				'el-icon-question',
				'el-icon-info',
				'el-icon-help',
				'el-icon-picture-outline-round',
				'el-icon-camera-solid',
				'el-icon-video-camera-solid',
				'el-icon-video-camera',
				'el-icon-bell',
				'el-icon-s-cooperation',
				'el-icon-s-order',
				'el-icon-s-platform',
				'el-icon-s-operation',
				'el-icon-s-promotion',
				'el-icon-s-release',
				'el-icon-s-ticket',
				'el-icon-s-management',
				'el-icon-s-open',
				'el-icon-s-shop',
				'el-icon-s-marketing',
				'el-icon-s-flag',
				'el-icon-s-comment',
				'el-icon-s-finance',
				'el-icon-s-claim',
				'el-icon-s-opportunity',
				'el-icon-s-data',
				'el-icon-s-check'
			],	
			showChatPopup: false, // 控制弹窗显示/隐藏
			inputMessage: '', // 输入框内容
			messageList: [], // 聊天消息列表
			isSending: false // 是否正在发送请求
		}
    },
	watch: {
    // 消息列表变化时自动滚动到底部
    messageList() {
      this.scrollToBottom();
    },
    $route(newValue) {
      let that = this
      let url = window.location.href
      let arr = url.split('#')
      for (let x in this.menuList) {
        if (newValue.path == this.menuList[x].url) {
          this.activeIndex = x
        }
      }
      this.Token = localStorage.getItem('Token')
    },
  },
    created() {
		this.baseUrl = this.$config.baseUrl;
		this.menuList = this.$config.indexNav;
		this.getCarousel();
        if(localStorage.getItem('Token') && localStorage.getItem('Token')!=null) {
            this.saveChathelper('主人，我是您的智能助手小搏，请问有什么可以帮您！');
            this.getChatList();
        }
    },
    mounted() {
        this.activeIndex = localStorage.getItem('keyPath') || '0';
    },

    methods: {
		// 发送消息
    async sendMessage() {
      const content = this.inputMessage.trim();
      if (!content || this.isSending) return;

      // 1. 添加用户消息到列表
      this.messageList.push({
        role: 'user',
        content: content,
        loading: false
      });

      // 2. 清空输入框 + 标记发送中
      this.inputMessage = '';
      this.isSending = true;

      // 3. 添加 AI 加载中消息
      const aiMsgIndex = this.messageList.length;
      this.messageList.push({
        role: 'assistant',
        content: '',
        loading: true
      });

      try {
        // 4. 调用后端 Java 接口（后端需转发百度千帆请求）
        const response = await this.$http.post('http://localhost:8080/springbootsnu6t/api/ai/chat', {
          message: content,
          // 传递对话历史（支持多轮对话）
          history: this.messageList
            .filter(msg => !msg.loading)
            .map(msg => ({ role: msg.role, content: msg.content }))
        });

        // 5. 更新 AI 回复消息
        const aiReply = response.data.data || '抱歉，暂时无法提供回复';
        this.messageList[aiMsgIndex] = {
          role: 'assistant',
          content: aiReply,
          loading: false
        };
      } catch (error) {
        // 6. 错误处理
        this.messageList[aiMsgIndex] = {
          role: 'assistant',
          content: '网络错误，请稍后重试',
          loading: false
        };
        console.error('AI 聊天请求失败：', error);
      } finally {
        // 7. 取消发送中状态
        this.isSending = false;
      }
    },

    // 滚动消息区域到底部
    scrollToBottom() {
      this.$nextTick(() => {
        const container = this.$refs.messagesContainer;
        if (container) {
          container.scrollTop = container.scrollHeight;
        }
      });
    },
        handleSelect(keyPath) {
            if (keyPath) {
                localStorage.setItem('keyPath', keyPath)
            }
        },
		toLogin() {
		  this.$router.push('/login');
		},
        logout() {
            localStorage.clear();
            Vue.http.headers.common['Token'] = "";
            this.$router.push('/index/home');
            this.activeIndex = '0'
            localStorage.setItem('keyPath', this.activeIndex)
            this.Token = ''
            this.$forceUpdate()
            this.$message({
                message: '登出成功',
                type: 'success',
                duration: 1000,
            });
        },
		getCarousel() {
			this.$http.get('config/list', {params: { page: 1, limit: 3 }}).then(res => {
				if (res.data.code == 0) {
					this.carouselList = res.data.data.list;
				}
			});
		},
		goBackend() {
			window.open(`${this.$config.baseUrl}admin/dist/index.html`, "_blank");
		},
		getChatList() {
			this.$http.get('chat/list', {params: { userid: localStorage.getItem('userid'), sort: 'addtime', order: 'asc',limit: 1000 }}).then(res => {
				if (res.data.code == 0) {
					this.chatList = res.data.data.list;
                    let div = document.getElementsByClassName('chat-content')[0]
                    setTimeout(() => {
                        if (div)
                            div.scrollTop = div.scrollHeight
                    }, 0)
				}
			});
		},
		addChat() {
			this.$http.post('chat/add', this.form).then(res => {
				if (res.data.code == 0) {
					this.form.ask = '';
					this.getChatList();
				}
			});
		},
		chatClose() {
			clearInterval(this.timer);
			this.chatFormVisible = false;
		},
		goChat() {
            if(!localStorage.getItem('Token')) {
                this.toLogin();
                return;
            }
			this.chatFormVisible = true;
			this.timer = setInterval(this.getChatList, 2000);
		},
		goMenu(path) {
            if (!localStorage.getItem('Token')) {
                this.toLogin();
            } else {
                this.$router.push(path);
            }
		},
    }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
// 聊天消息区域
.chat-messages {
  height: 400px;
  overflow-y: auto;
  padding: 15px;
  background-color: #f9f9f9;
  border-radius: 8px;
  margin-bottom: 15px;

  // 滚动条美化
  &::-webkit-scrollbar {
    width: 6px;
  }
  &::-webkit-scrollbar-thumb {
    background-color: #e5e6eb;
    border-radius: 3px;
  }

  // 欢迎消息
  .welcome-message {
    text-align: center;
    padding: 60px 0;
    color: #666;

    .el-icon-service {
      font-size: 48px;
      margin-bottom: 16px;
      color: #409eff;
    }

    p {
      font-size: 15px;
    }
  }

  // 消息项
  .message-item {
    display: flex;
    margin-bottom: 12px;
    align-items: flex-start;

    // 用户消息靠右对齐
    &.user-message {
      flex-direction: row-reverse;
    }

    // 头像
    .message-avatar {
      width: 36px;
      height: 36px;
      border-radius: 50%;
      background-color: #409eff;
      color: white;
      display: flex;
      align-items: center;
      justify-content: center;
      font-size: 16px;
      flex-shrink: 0;

      // 用户头像样式
      .el-icon-user {
        background-color: #86909c;
        border-radius: 50%;
        width: 100%;
        height: 100%;
        display: flex;
        align-items: center;
        justify-content: center;
      }
    }

    // 消息内容
    .message-content {
      margin: 0 12px;
      max-width: 75%;

      // 用户消息内容靠左（因为父容器反向）
      .user-message & {
        margin-left: 0;
        margin-right: 12px;
      }

      // 消息气泡
      .message-bubble {
        padding: 10px 14px;
        border-radius: 16px;
        word-wrap: break-word;
        box-shadow: 0 2px 6px rgba(0, 0, 0, 0.05);

        // AI 气泡样式
        &.ai-bubble {
          background-color: #ffffff;
          color: #333;
          border-top-left-radius: 4px;
        }

        // 用户气泡样式
        &.user-bubble {
          background-color: #409eff;
          color: #ffffff;
          border-top-right-radius: 4px;
        }

        // 加载中动画
        .loading {
          display: flex;
          gap: 4px;
          padding: 4px 0;

          .dot {
            width: 8px;
            height: 8px;
            border-radius: 50%;
            background-color: #409eff;
            animation: bounce 1.4s infinite ease-in-out both;

            &:nth-child(1) { animation-delay: -0.32s; }
            &:nth-child(2) { animation-delay: -0.16s; }
          }
        }
      }
    }
  }
}

// 输入区域
.chat-input-area {
  .message-input {
    border-radius: 8px;
    resize: none;
  }

  .send-btn {
    width: 100%;
  }
}

// 加载动画关键帧
@keyframes bounce {
  0%, 80%, 100% { transform: scale(0); }
  40% { transform: scale(1); }
}
	.menu-preview {
	  .el-scrollbar {
	    height: 100%;
	
	    & ::v-deep .scrollbar-wrapper {
	      overflow-x: hidden;
	    }
	  }
	}
	
	.menu-preview .el-menu-vertical-demo .el-menu-item {
		cursor: pointer;
		padding: 0 20px;
		margin: 0 auto 0px auto;
		color: #333;
		white-space: nowrap;
		display: flex;
		font-size: 14px;
		border-color: #98c6e2 #ccc #98c6e2 #ccc;
		line-height: 44px;
		border-radius: 0;
		box-shadow: 0px 0px 0px #ddd;
		background: linear-gradient(180deg, rgba(232,232,232,1) 0%, rgba(255,255,255,1) 50%, rgba(212,211,211,1) 100%),#fff;
		width: 100%;
		border-width: 8px 16px 8px 16px;
		align-items: center;
		position: relative;
		border-style: solid;
		list-style: none;
		height: 60px;
	}
	
	.menu-preview .el-menu-vertical-demo .el-menu-item:hover {
		cursor: pointer;
		padding: 0 20px;
		margin: 0 auto 0px auto;
		color: #fff;
		white-space: nowrap;
		font-size: 14px;
		border-color: #98c6e2 #5fa6d0 #98c6e2 #5fa6d0;
		line-height: 44px;
		border-radius: 0px;
		box-shadow: 0px 0px 0px #ddd;
		background: linear-gradient(180deg, rgba(88,157,199,1) 0%, rgba(170,208,231,1) 50%, rgba(95,166,208,1) 100%),#3086b9;
		width: 100%;
		border-width: 8px 16px 8px 16px;
		position: relative;
		border-style: solid;
		list-style: none;
		height: 60px;
	}
	
	.menu-preview .el-menu-vertical-demo .el-menu-item.is-active {
		cursor: pointer;
		padding: 0 20px;
		margin: 0 auto 0px auto;
		color: #fff;
		white-space: nowrap;
		font-size: 14px;
		border-color: #98c6e2 #5fa6d0 #98c6e2 #5fa6d0;
		line-height: 44px;
		border-radius: 0px;
		box-shadow: 0px 0px 0px #ddd;
		background: linear-gradient(180deg, rgba(88,157,199,1) 0%, rgba(170,208,231,1) 50%, rgba(95,166,208,1) 100%),#3086b9;
		width: 100%;
		border-width: 8px 16px 8px 16px;
		position: relative;
		border-style: solid;
		list-style: none;
		height: 60px;
	}
	
	
	.banner-preview {
	  .el-carousel ::v-deep .el-carousel__indicator button {
	    width: 0;
	    height: 0;
	    display: none;
	  }
	}
	
	.banner-preview .el-carousel ::v-deep .el-carousel__container .el-carousel__arrow--left {
		width: 36px;
		font-size: 12px;
		height: 36px;
	}
	
	.banner-preview .el-carousel ::v-deep .el-carousel__container .el-carousel__arrow--left:hover {
		background: #2087c3;
	}
	
	.banner-preview .el-carousel ::v-deep .el-carousel__container .el-carousel__arrow--right {
		width: 36px;
		font-size: 12px;
		height: 36px;
	}
	
	.banner-preview .el-carousel ::v-deep .el-carousel__container .el-carousel__arrow--right:hover {
		background: #2087c3;
	}

	.banner-preview .el-carousel ::v-deep .el-carousel__indicators {
		padding: 0;
		margin: 0 0 12px 0;
		z-index: 2;
		position: absolute;
		list-style: none;
	}
	
	.banner-preview .el-carousel ::v-deep .el-carousel__indicators li {
		border-radius: 50%;
		padding: 0;
		margin: 0 4px;
		background: #fff;
		display: inline-block;
		width: 12px;
		opacity: 0.4;
		transition: 0.3s;
		height: 12px;
	}
	
	.banner-preview .el-carousel ::v-deep .el-carousel__indicators li:hover {
		border-radius: 50%;
		padding: 0;
		margin: 0 4px;
		background: #fff;
		display: inline-block;
		width: 12px;
		opacity: 0.7;
		height: 12px;
	}
	
	.banner-preview .el-carousel ::v-deep .el-carousel__indicators li.is-active {
		border-radius: 50%;
		padding: 0;
		margin: 0 4px;
		background: #fff;
		display: inline-block;
		width: 12px;
		opacity: 1;
		height: 12px;
	}

    .chat-content {
      .left-content {
          width: 100%;
          text-align: left;
      }
      .right-content {
          width: 100%;
          text-align: right;
      }
    }
</style>
