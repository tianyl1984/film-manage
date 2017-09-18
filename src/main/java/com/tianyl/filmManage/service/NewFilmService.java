package com.tianyl.filmManage.service;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianyl.filmManage.dao.NewFilmDAO;
import com.tianyl.filmManage.dao.WebsiteDAO;
import com.tianyl.filmManage.model.NewFilm;
import com.tianyl.filmManage.model.Website;

public class NewFilmService {

	public static String findByDate(Date sdate, Date edate) {
		List<Website> websites = WebsiteDAO.findAll();
		List<NewFilm> newFilms = NewFilmDAO.findByDate(sdate, edate);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("websites", websites);
		JSONObject filmsJson = new JSONObject();
		for (NewFilm nf : newFilms) {
			JSONArray tempObj = filmsJson.getJSONArray(nf.getWebSiteId() + "");
			if (tempObj == null) {
				tempObj = new JSONArray();
				filmsJson.put(nf.getWebSiteId() + "", tempObj);
			}
			tempObj.add(nf);
		}
		jsonObj.put("newFilms", filmsJson);
		return jsonObj.toJSONString();
	}

	public static NewFilm findById(Integer id) {
		return NewFilmDAO.findById(id);
	}

	public static void updateToDel(Integer id) {
		NewFilmDAO.updateToDel(id);
	}
}
