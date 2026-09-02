export function isAuth(tableName, key) {
  let role = localStorage.getItem("UserTableName");
  let menus = [{"backMenu":[{"child":[{"appFrontIcon":"cuIcon-paint","buttons":["新增","查看","修改","删除"],"menu":"用户","menuJump":"列表","tableName":"yonghu"}],"menu":"用户管理"},{"child":[{"appFrontIcon":"cuIcon-goods","buttons":["新增","查看","修改","删除"],"menu":"品牌类别","menuJump":"列表","tableName":"pinpaileibie"}],"menu":"品牌类别管理"},{"child":[{"appFrontIcon":"cuIcon-news","buttons":["查看","新增","修改","删除","品牌类别统计","汽车库存统计","查看评论","首页总数","首页统计"],"menu":"新能源汽车","menuJump":"列表","tableName":"xinnengyuanqiche"}],"menu":"新能源汽车管理"},{"child":[{"appFrontIcon":"cuIcon-vipcard","buttons":["查看","删除","审核","每月销售额统计","首页总数","首页统计","修改","接单配送"],"menu":"汽车订单","menuJump":"列表","tableName":"qichedingdan"}],"menu":"汽车订单管理"},{"child":[{"appFrontIcon":"cuIcon-vip","buttons":["查看","删除","修改","更新配送"],"menu":"配送订单","menuJump":"列表","tableName":"peisongdingdan"}],"menu":"配送订单管理"},{"child":[{"appFrontIcon":"cuIcon-time","buttons":["查看","修改","删除"],"menu":"配送信息","menuJump":"列表","tableName":"peisongxinxi"}],"menu":"配送信息管理"},{"child":[{"appFrontIcon":"cuIcon-rank","buttons":["查看","删除","审核"],"menu":"预约看车","menuJump":"列表","tableName":"yuyuekanche"}],"menu":"预约看车管理"},{"child":[{"appFrontIcon":"cuIcon-send","buttons":["查看","修改"],"menu":"系统简介","tableName":"systemintro"},{"appFrontIcon":"cuIcon-present","buttons":["查看","修改"],"menu":"轮播图管理","tableName":"config"},{"appFrontIcon":"cuIcon-news","buttons":["新增","查看","修改","删除"],"menu":"公告信息","tableName":"news"},{"appFrontIcon":"cuIcon-service","buttons":["新增","查看","修改","删除"],"menu":"在线咨询","tableName":"chat"},{"appFrontIcon":"cuIcon-clothes","buttons":["查看","修改"],"menu":"关于我们","tableName":"aboutus"}],"menu":"系统管理"}],"frontMenu":[{"child":[{"appFrontIcon":"cuIcon-full","buttons":["查看","购买汽车","预约看车"],"menu":"新能源汽车列表","menuJump":"列表","tableName":"xinnengyuanqiche"}],"menu":"新能源汽车模块"}],"hasBackLogin":"是","hasBackRegister":"否","hasFrontLogin":"否","hasFrontRegister":"否","roleName":"管理员","tableName":"users"},{"backMenu":[{"child":[{"appFrontIcon":"cuIcon-vipcard","buttons":["查看","删除","支付"],"menu":"汽车订单","menuJump":"列表","tableName":"qichedingdan"}],"menu":"汽车订单管理"},{"child":[{"appFrontIcon":"cuIcon-vip","buttons":["查看"],"menu":"配送订单","menuJump":"列表","tableName":"peisongdingdan"}],"menu":"配送订单管理"},{"child":[{"appFrontIcon":"cuIcon-time","buttons":["查看"],"menu":"配送信息","menuJump":"列表","tableName":"peisongxinxi"}],"menu":"配送信息管理"},{"child":[{"appFrontIcon":"cuIcon-rank","buttons":["查看","删除"],"menu":"预约看车","menuJump":"列表","tableName":"yuyuekanche"}],"menu":"预约看车管理"}],"frontMenu":[{"child":[{"appFrontIcon":"cuIcon-full","buttons":["查看","购买汽车","预约看车"],"menu":"新能源汽车列表","menuJump":"列表","tableName":"xinnengyuanqiche"}],"menu":"新能源汽车模块"}],"hasBackLogin":"是","hasBackRegister":"否","hasFrontLogin":"是","hasFrontRegister":"是","roleName":"用户","tableName":"yonghu"}];
  for(let i=0;i<menus.length;i++){
    if(menus[i].tableName==role){
      for(let j=0;j<menus[i].frontMenu.length;j++){
          for(let k=0;k<menus[i].frontMenu[j].child.length;k++){
            if(tableName==menus[i].frontMenu[j].child[k].tableName){
              let buttons = menus[i].frontMenu[j].child[k].buttons.join(',');
              return buttons.indexOf(key) !== -1 || false
            }
          }
      }
    }
  }
  return false;
}

/**
 *  * 获取当前时间（yyyy-MM-dd hh:mm:ss）
 *   */
export function getCurDateTime() {
    let currentTime = new Date(),
    year = currentTime.getFullYear(),
    month = currentTime.getMonth() + 1 < 10 ? '0' + (currentTime.getMonth() + 1) : currentTime.getMonth() + 1,
    day = currentTime.getDate() < 10 ? '0' + currentTime.getDate() : currentTime.getDate(),
    hour = currentTime.getHours(),
    minute = currentTime.getMinutes(),
    second = currentTime.getSeconds();
    return year + "-" + month + "-" + day + " " +hour +":" +minute+":"+second;
}

/**
 *  * 获取当前日期（yyyy-MM-dd）
 *   */
export function getCurDate() {
    let currentTime = new Date(),
    year = currentTime.getFullYear(),
    month = currentTime.getMonth() + 1 < 10 ? '0' + (currentTime.getMonth() + 1) : currentTime.getMonth() + 1,
    day = currentTime.getDate() < 10 ? '0' + currentTime.getDate() : currentTime.getDate();
    return year + "-" + month + "-" + day;
}
