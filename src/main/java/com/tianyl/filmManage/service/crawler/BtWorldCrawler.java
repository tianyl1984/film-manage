package com.tianyl.filmManage.service.crawler;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tianyl.filmManage.model.NewFilm;
import com.tianyl.filmManage.util.DateUtil;
import com.tianyl.filmManage.util.JsoupUtil;

public class BtWorldCrawler implements Crawler {

	@Override
	public String getUrl() {
		return "http://www.btworld.cc/new";
	}

	@Override
	public List<NewFilm> parseFilm(String html) {
		List<NewFilm> nfs = new ArrayList<NewFilm>();
		Document doc = Jsoup.parse(html);
		Elements eles = doc.getElementsByClass("post");
		for (Element ele : eles) {
			String name = JsoupUtil.getValue(ele, "h3 a");
			String href = JsoupUtil.getValue(ele, "h3 a[href]");
			String dateStr = "";
			Elements dateEles = ele.getElementsByClass("date");
			for (Element ele1 : dateEles) {
				if (ele1.text().contains("最后更新：")) {
					dateStr = ele1.text().replaceAll("最后更新：", "");
				}
			}
			NewFilm nf = new NewFilm();
			nf.setName(name);
			nf.setUrl(href);
			nf.setUpdateTime(DateUtil.parseDate(dateStr));
			nf.setWebSiteId(4);
			nfs.add(nf);
		}
		return nfs;
	}

}
