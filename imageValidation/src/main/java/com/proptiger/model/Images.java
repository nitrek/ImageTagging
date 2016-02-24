package com.proptiger.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "image")
public class Images {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "imageUrl")
	private String url;

	@Column(name = "tag")
	private String tag;

	public int getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setId(int id) {
		this.id = id;
	}

}