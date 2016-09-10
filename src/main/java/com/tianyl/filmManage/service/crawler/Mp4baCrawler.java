package com.tianyl.filmManage.service.crawler;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tianyl.filmManage.model.NewFilm;
import com.tianyl.filmManage.util.DateUtil;

public class Mp4baCrawler implements Crawler {

	@Override
	public String getUrl() {
		return "http://www.mp4ba.com/index.php?o=today";
	}

	@Override
	public List<NewFilm> parseFilm(String html) {
		List<NewFilm> nfs = new ArrayList<NewFilm>();
		Document doc = Jsoup.parse(html);
		Element tbody = doc.getElementById("data_list");
		if (tbody == null) {
			return nfs;
		}
		Elements trs = tbody.getElementsByTag("tr");
		for (Element tr : trs) {
			Elements tds = tr.children();
			if (tds.size() != 8) {
				continue;
			}
			String dateStr = tds.first().ownText();
			Element a = tds.get(2).getElementsByTag("a").first();
			String url = "http://www.mp4ba.com/" + a.attr("href");
			String name = a.ownText();
			NewFilm nf = new NewFilm();
			nf.setName(name);
			nf.setUpdateTime(DateUtil.parseDate(dateStr));
			nf.setUrl(url);
			nf.setWebSiteId(2);
			nfs.add(nf);
		}
		return nfs;
	}

}
