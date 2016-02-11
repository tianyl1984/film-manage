package com.tianyl.filmManage.service.crawler;

import java.util.List;

import com.tianyl.filmManage.model.NewFilm;

public interface Crawler {

	public String getUrl();

	public List<NewFilm> parseFilm(String html);

}
