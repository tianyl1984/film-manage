package com.tianyl.filmManage.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tianyl.filmManage.model.NewFilm;
import com.tianyl.filmManage.util.sql.JdbcUtil;

public class NewFilmDAO {

	public static List<String> findUrlsLast1000() {
		String sql = "select url from new_film order by id desc limit 1000";
		return JdbcUtil.queryStr(sql);
	}

	public static void save(List<NewFilm> newFilms) {
		String sql = "insert into new_film(id_website,name,url,updateTime,delFlag) values(?,?,?,?,?)";
		List<Object[]> valueList = new ArrayList<Object[]>();
		for (NewFilm nf : newFilms) {
			Object[] vals = new Object[] { nf.getWebSiteId(), nf.getName(), nf.getUrl(), nf.getUpdateTime(),
					nf.isDelFlag() };
			valueList.add(vals);
		}
		JdbcUtil.saveList(sql, valueList);
	}

	public static List<NewFilm> findByDate(Date sdate, Date edate) {
		String sql = "select * from new_film where updateTime >= ? and updateTime <= ? and delFlag = ? order by updateTime";
		return JdbcUtil.query(sql, NewFilmRowMapper.getInstance(), sdate, edate, false);
	}

	public static NewFilm findById(Integer id) {
		String sql = "select * from new_film where id = ? ";
		return JdbcUtil.query(sql, NewFilmRowMapper.getInstance(), id).get(0);
	}

	public static void updateToDel(Integer id) {
		String sql = "update new_film set delFlag = ? where id = ?";
		JdbcUtil.update(sql, true, id);
	}

}
