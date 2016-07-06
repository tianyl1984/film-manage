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

public class RarBTCrawler implements Crawler {

	@Override
	public String getUrl() {
		return "http://www.rarbt.com/";
	}

	@Override
	public List<NewFilm> parseFilm(String html) {
		List<NewFilm> nfs = new ArrayList<NewFilm>();
		Document doc = Jsoup.parse(html);
		Elements eles = doc.getElementsByClass("item");
		for (Element ele : eles) {
			Element tt = JsoupUtil.getByClass(ele, "tt");
			if (tt == null) {
				continue;
			}
			String dateStr = JsoupUtil.getValue(tt, "span");
			Element atag = JsoupUtil.getByTag(tt, "a");
			String href = atag.attr("href");
			String name = atag.text();
			NewFilm nf = new NewFilm();
			nf.setName(name);
			nf.setUrl("http://www.rarbt.com" + href);
			nf.setUpdateTime(DateUtil.parseDate(dateStr));
			nf.setWebSiteId(3);
			nfs.add(nf);
		}
		return nfs;
	}

}
