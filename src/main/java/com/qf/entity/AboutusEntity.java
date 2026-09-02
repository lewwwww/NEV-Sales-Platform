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
 * 关于我们
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-12-26 11:34:55
 */
@TableName("aboutus")
public class AboutusEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	public AboutusEntity() {
		
	}
	
	public AboutusEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 副标题
	 */
	private String subtitle;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 图片1
	 */
	private String picture1;
	
	/**
	 * 图片2
	 */
	private String picture2;
	
	/**
	 * 图片3
	 */
	private String picture3;
	
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
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getSubtitle() {
		return subtitle;
	}
	
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getPicture1() {
		return picture1;
	}
	
	public void setPicture1(String picture1) {
		this.picture1 = picture1;
	}
	
	public String getPicture2() {
		return picture2;
	}
	
	public void setPicture2(String picture2) {
		this.picture2 = picture2;
	}
	
	public String getPicture3() {
		return picture3;
	}
	
	public void setPicture3(String picture3) {
		this.picture3 = picture3;
	}
}
