package com.bobo.cms.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.bobo.cms.vo.ArticleVO;

public class Article  implements Serializable{
    /**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String title;

    private String picture;

    private Integer channelId;

    private Integer categoryId;

    private Integer userId;
    
    private User user;//文章发布人
    
    private List<ArticleVO> voList;//图片集
    
    
    private  String original;//文章来源
    private String keywords;//关键词
    
    

    public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	private Integer hits;

    private Integer hot;

    private Integer status;

    private Integer deleted;

    private Date created;

    private Date updated;
    
    private Integer contentType;
    
  

	public Integer getContentType() {
		return contentType;
	}

	public void setContentType(Integer contentType) {
		this.contentType = contentType;
	}

	public List<ArticleVO> getVoList() {
		return voList;
	}

	public void setVoList(List<ArticleVO> voList) {
		this.voList = voList;
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
        this.title = title == null ? null : title.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
    
   
}