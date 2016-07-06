package com.tianyl.filmManage.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.tianyl.filmManage.dao.NewFilmDAO;
import com.tianyl.filmManage.model.NewFilm;
import com.tianyl.filmManage.service.crawler.BttiantangCrawler;
import com.tianyl.filmManage.service.crawler.Crawler;
import com.tianyl.filmManage.service.crawler.Mp4baCrawler;
import com.tianyl.filmManage.service.crawler.RarBTCrawler;
import com.tianyl.filmManage.util.LogManager;
import com.tianyl.filmManage.util.RequestResult;
import com.tianyl.filmManage.util.WebUtil;

public class FilmCrawlerManager {

	public static void crawl() {
		List<Crawler> crawlers = new ArrayList<Crawler>();
		crawlers.add(new BttiantangCrawler());
		crawlers.add(new Mp4baCrawler());
		crawlers.add(new RarBTCrawler());
		List<String> urls = NewFilmDAO.findUrlsLast1000();
		Set<String> urlSet = new HashSet<String>();
		urlSet.addAll(urls);
		for (Crawler cw : crawlers) {
			String url = cw.getUrl();
			RequestResult rs = WebUtil.getUrlResponse(url, null, null, true);
			if (!rs.isOk()) {
				LogManager.log("get html error,error code:" + rs.getResponseCode());
				LogManager.log("get html error,error msg:\r\n" + rs.getResultStr());
				continue;
			}
			List<NewFilm> newFilms = new ArrayList<NewFilm>();
			try {
				newFilms = cw.parseFilm(rs.getResultStr());
			} catch (Exception e) {
				e.printStackTrace();
				LogManager.log(e);
			}
			// 去掉已存在的，用详细页面的url代替
			Iterator<NewFilm> nfIt = newFilms.iterator();
			while (nfIt.hasNext()) {
				if (urlSet.contains(nfIt.next().getUrl())) {
					nfIt.remove();
				}
			}
			Collections.reverse(newFilms);
			// 保存
			NewFilmDAO.save(newFilms);
		}
	}

	public static void main(String[] args) {
		crawl();
	}
}
