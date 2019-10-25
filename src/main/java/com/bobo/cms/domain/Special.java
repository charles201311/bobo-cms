package com.bobo.cms.domain;

import java.util.Date;
import java.util.List;

/**
 * 
 * @ClassName: Special 
 * @Description: 专题
 * @author: charles
 * @date: 2019年10月25日 上午8:23:23
 */
public class Special {
	private Integer id;
	private String title;// 专题标题
	private String abstracts;// 专题摘要
	private Date created;// 创建时间
	
	private Integer count;//专题下的文章个数
	
	List<SpecialArticle> specialArticles;//专题下的文章
	
	
	


	public List<SpecialArticle> getSpecialArticles() {
		return specialArticles;
	}

	public void setSpecialArticles(List<SpecialArticle> specialArticles) {
		this.specialArticles = specialArticles;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAbstracts() {
		return abstracts;
	}

	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
