package com.bobo.cms.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.bobo.cms.domain.ArticleWithBLOBs;
import com.bobo.cms.domain.User;
import com.bobo.cms.service.ArticleService;
import com.bobo.cms.util.ArticleEnum;

public class Spider2SouHuNewsTest extends JunitParent {

	@Resource
	private ArticleService articleService;
	@Test
	public void gain() throws Exception {
		// 声明连接对象
		Connection connect = Jsoup.connect("http://news.sohu.com/");
		// 得到一个文档对象,就是这个网站的html的源代码
		Document document = connect.get();
		// System.out.println(document);
		Elements titleDiv = document.select(".list16");
		for (Element element : titleDiv) {
			// System.out.println(element);
			Elements lis = element.select("a[href]");
			for (Element li : lis) {
				String title = li.attr("title");
				// System.out.println(li.attr("href"));
				String url = li.attr("href");
				if (!url.startsWith("http")) {
					url = "http:" + url;
				}
				title = title.replace("|", "").replace("*", "").replace("\"", "").replace("?", "").replace("/", "")
						.replace("\\", "").replace(">", "").replace("<", "").replace(":", "");
			
				Connection connect2 = Jsoup.connect(url);
				Document document2 = connect2.get();
				//
				Elements select = document2.select("#news-time");
				
				Elements ps = document2.select("article");
				ArticleWithBLOBs article = new ArticleWithBLOBs();
				
				for (Element element2 : select) {
				
					article.setTitle(title);
					
					//System.err.println(element2.attr("data-val"));
					long time =Long.parseLong(element2.attr("data-val"));//==null?new Date().getTime():Long.parseLong(element2.attr("data-val"));
					//System.out.println("!!!!!!!!!!!!!"+time+"===================");
					article.setCreated(new Date(time));
				}
			
			
				for (Element p : ps) {
					// System.out.println(p);
					//String content = p.text();
				//	System.out.println("---------------------------->"+p);
					article.setContent(p+"");
					
					// System.out.println(content);
					// 我把爬取出来的内容保存到本地磁盘:
					// 要求:以文章标题作文文件名称,以.txt为后缀
					// 文章内容保存在txt文本里
					//FileUtilIO.writeFile("D:/1706D/" + title + ".txt", content, "utf8");
				}
				//User user = (User) session.getAttribute("user");
				article.setUserId(159);// 文章作者
				article.setStatus(0);// 未审核
				article.setDeleted(0);// 未删除
				article.setHits(0);// 点击量默认0
				article.setHot(0);// 默认非热门
				article.setCreated(new Date());
				article.setUpdated(new Date());
				article.setContentType(ArticleEnum.HTML.getCode());//文章类型
				articleService.insertSelective(article);
				
			}
		}
	}
}
