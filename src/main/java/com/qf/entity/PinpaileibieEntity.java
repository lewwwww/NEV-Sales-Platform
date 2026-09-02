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
 * 品牌类别
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-12-26 11:34:54
 */
@TableName("pinpaileibie")
public class PinpaileibieEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public PinpaileibieEntity() {
		
	}
	
	public PinpaileibieEntity(T t) {
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
	 * 品牌类别
	 */
					
	private String pinpaileibie;
	
	
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

}
