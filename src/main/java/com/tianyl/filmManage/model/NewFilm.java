package com.tianyl.filmManage.model;

import java.util.Date;

public class NewFilm {

	private Integer id;

	private Integer webSiteId;

	private String name;

	private String url;

	private Date updateTime;

	public NewFilm() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWebSiteId() {
		return webSiteId;
	}

	public void setWebSiteId(Integer webSiteId) {
		this.webSiteId = webSiteId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return String.format("id:%d,name:%s,url:%s,updateTime:%tF", id, name, url, updateTime);
	}
}
