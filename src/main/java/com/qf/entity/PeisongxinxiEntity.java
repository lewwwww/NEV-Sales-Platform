package com.qf.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;


/**
 * 配送信息
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-12-26 11:34:55
 */
@TableName("peisongxinxi")
public class PeisongxinxiEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public PeisongxinxiEntity() {
		
	}
	
	public PeisongxinxiEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 订单编号
	 */
					
	private String dingdanbianhao;
	
	/**
	 * 车名
	 */
					
	private String cheming;
	
	/**
	 * 品牌类别
	 */
					
	private String pinpaileibie;
	
	/**
	 * 颜色
	 */
					
	private String yanse;
	
	/**
	 * 价格
	 */
					
	private Float jiage;
	
	/**
	 * 数量
	 */
					
	private Integer shuliang;
	
	/**
	 * 应付金额
	 */
					
	private Float yingfujine;
	
	/**
	 * 厂商
	 */
					
	private String changshang;
	
	/**
	 * 纯电续航
	 */
					
	private String chundianxuhang;
	
	/**
	 * 账号
	 */
					
	private String zhanghao;
	
	/**
	 * 姓名
	 */
					
	private String xingming;
	
	/**
	 * 手机号码
	 */
					
	private String shoujihaoma;
	
	/**
	 * 收货地址
	 */
					
	private String shouhuodizhi;
	
	/**
	 * 下单时间
	 */
					
	private String xiadanshijian;
	
	/**
	 * 更新时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 		
	private Date gengxinshijian;
	
	/**
	 * 更新内容
	 */
					
	private String gengxinneirong;
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：订单编号
	 */
	public void setDingdanbianhao(String dingdanbianhao) {
		this.dingdanbianhao = dingdanbianhao;
	}
	/**
	 * 获取：订单编号
	 */
	public String getDingdanbianhao() {
		return dingdanbianhao;
	}
	/**
	 * 设置：车名
	 */
	public void setCheming(String cheming) {
		this.cheming = cheming;
	}
	/**
	 * 获取：车名
	 */
	public String getCheming() {
		return cheming;
	}
	/**
	 * 设置：品牌类别
	 */
	public void setPinpaileibie(String pinpaileibie) {
		this.pinpaileibie = pinpaileibie;
	}
	/**
	 * 获取：品牌类别
	 */
	public String getPinpaileibie() {
		return pinpaileibie;
	}
	/**
	 * 设置：颜色
	 */
	public void setYanse(String yanse) {
		this.yanse = yanse;
	}
	/**
	 * 获取：颜色
	 */
	public String getYanse() {
		return yanse;
	}
	/**
	 * 设置：价格
	 */
	public void setJiage(Float jiage) {
		this.jiage = jiage;
	}
	/**
	 * 获取：价格
	 */
	public Float getJiage() {
		return jiage;
	}
	/**
	 * 设置：数量
	 */
	public void setShuliang(Integer shuliang) {
		this.shuliang = shuliang;
	}
	/**
	 * 获取：数量
	 */
	public Integer getShuliang() {
		return shuliang;
	}
	/**
	 * 设置：应付金额
	 */
	public void setYingfujine(Float yingfujine) {
		this.yingfujine = yingfujine;
	}
	/**
	 * 获取：应付金额
	 */
	public Float getYingfujine() {
		return yingfujine;
	}
	/**
	 * 设置：厂商
	 */
	public void setChangshang(String changshang) {
		this.changshang = changshang;
	}
	/**
	 * 获取：厂商
	 */
	public String getChangshang() {
		return changshang;
	}
	/**
	 * 设置：纯电续航
	 */
	public void setChundianxuhang(String chundianxuhang) {
		this.chundianxuhang = chundianxuhang;
	}
	/**
	 * 获取：纯电续航
	 */
	public String getChundianxuhang() {
		return chundianxuhang;
	}
	/**
	 * 设置：账号
	 */
	public void setZhanghao(String zhanghao) {
		this.zhanghao = zhanghao;
	}
	/**
	 * 获取：账号
	 */
	public String getZhanghao() {
		return zhanghao;
	}
	/**
	 * 设置：姓名
	 */
	public void setXingming(String xingming) {
		this.xingming = xingming;
	}
	/**
	 * 获取：姓名
	 */
	public String getXingming() {
		return xingming;
	}
	/**
	 * 设置：手机号码
	 */
	public void setShoujihaoma(String shoujihaoma) {
		this.shoujihaoma = shoujihaoma;
	}
	/**
	 * 获取：手机号码
	 */
	public String getShoujihaoma() {
		return shoujihaoma;
	}
	/**
	 * 设置：收货地址
	 */
	public void setShouhuodizhi(String shouhuodizhi) {
		this.shouhuodizhi = shouhuodizhi;
	}
	/**
	 * 获取：收货地址
	 */
	public String getShouhuodizhi() {
		return shouhuodizhi;
	}
	/**
	 * 设置：下单时间
	 */
	public void setXiadanshijian(String xiadanshijian) {
		this.xiadanshijian = xiadanshijian;
	}
	/**
	 * 获取：下单时间
	 */
	public String getXiadanshijian() {
		return xiadanshijian;
	}
	/**
	 * 设置：更新时间
	 */
	public void setGengxinshijian(Date gengxinshijian) {
		this.gengxinshijian = gengxinshijian;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getGengxinshijian() {
		return gengxinshijian;
	}
	/**
	 * 设置：更新内容
	 */
	public void setGengxinneirong(String gengxinneirong) {
		this.gengxinneirong = gengxinneirong;
	}
	/**
	 * 获取：更新内容
	 */
	public String getGengxinneirong() {
		return gengxinneirong;
	}

}
