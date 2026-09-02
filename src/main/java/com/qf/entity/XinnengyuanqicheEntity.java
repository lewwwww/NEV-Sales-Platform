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
 * 新能源汽车
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-12-26 11:34:54
 */
@TableName("xinnengyuanqiche")
public class XinnengyuanqicheEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public XinnengyuanqicheEntity() {
		
	}
	
	public XinnengyuanqicheEntity(T t) {
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
	 * 车名
	 */
					
	private String cheming;
	
	/**
	 * 品牌类别
	 */
					
	private String pinpaileibie;
	
	/**
	 * 汽车图片
	 */
					
	private String qichetupian;
	
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
	 * 厂商
	 */
					
	private String changshang;
	
	/**
	 * 有无天窗
	 */
					
	private String youwutianchuang;
	
	/**
	 * 能源类型
	 */
					
	private String nengyuanleixing;
	
	/**
	 * 汽车配置
	 */
					
	private String qichepeizhi;
	
	/**
	 * 纯电续航
	 */
					
	private String chundianxuhang;
	
	/**
	 * 基本参数
	 */
					
	private String jibencanshu;
	
	/**
	 * 最近点击时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 		
	private Date clicktime;
	
	
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
	 * 设置：汽车图片
	 */
	public void setQichetupian(String qichetupian) {
		this.qichetupian = qichetupian;
	}
	/**
	 * 获取：汽车图片
	 */
	public String getQichetupian() {
		return qichetupian;
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
	 * 设置：有无天窗
	 */
	public void setYouwutianchuang(String youwutianchuang) {
		this.youwutianchuang = youwutianchuang;
	}
	/**
	 * 获取：有无天窗
	 */
	public String getYouwutianchuang() {
		return youwutianchuang;
	}
	/**
	 * 设置：能源类型
	 */
	public void setNengyuanleixing(String nengyuanleixing) {
		this.nengyuanleixing = nengyuanleixing;
	}
	/**
	 * 获取：能源类型
	 */
	public String getNengyuanleixing() {
		return nengyuanleixing;
	}
	/**
	 * 设置：汽车配置
	 */
	public void setQichepeizhi(String qichepeizhi) {
		this.qichepeizhi = qichepeizhi;
	}
	/**
	 * 获取：汽车配置
	 */
	public String getQichepeizhi() {
		return qichepeizhi;
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
	 * 设置：基本参数
	 */
	public void setJibencanshu(String jibencanshu) {
		this.jibencanshu = jibencanshu;
	}
	/**
	 * 获取：基本参数
	 */
	public String getJibencanshu() {
		return jibencanshu;
	}
	/**
	 * 设置：最近点击时间
	 */
	public void setClicktime(Date clicktime) {
		this.clicktime = clicktime;
	}
	/**
	 * 获取：最近点击时间
	 */
	public Date getClicktime() {
		return clicktime;
	}

}
