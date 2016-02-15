package com.tianyl.filmManage.service.crawler;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tianyl.filmManage.model.NewFilm;
import com.tianyl.filmManage.util.DateUtil;

public class BttiantangCrawler implements Crawler {

	@Override
	public String getUrl() {
		return "http://www.bttiantang.com";
	}

	@Override
	public List<NewFilm> parseFilm(String html) {
		Document doc = Jsoup.parse(html);
		Elements eles = doc.getElementsByClass("title");
		List<NewFilm> result = new ArrayList<NewFilm>();
		for (Element ele : eles) {
			Element p = ele.getElementsByClass("tt").first();
			if (p != null) {
				Element a = p.getElementsByTag("a").first();
				String url = getUrl() + a.attr("href");
				Element span = p.getElementsByTag("span").first();
				String dateStr = span.text();
				String name = a.text();
				NewFilm nf = new NewFilm();
				nf.setName(name);
				nf.setWebSiteId(1);
				nf.setUrl(url);
				nf.setUpdateTime(DateUtil.parseDate(dateStr));
				result.add(nf);
			}
		}
		return result;
	}

}
