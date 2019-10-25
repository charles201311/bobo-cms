package com.bobo.cms.domain;
/**
 * 
 * @ClassName: SpecialArticle 
 * @Description: 专题文章中间表
 * @author: charles
 * @date: 2019年10月25日 上午8:27:35
 */
public class SpecialArticle {
	private Integer sid;
	private Integer aid;
	
	
	private Article article;
	
	
	
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	

}
