package com.tianyl.filmManage.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tianyl.filmManage.model.NewFilm;
import com.tianyl.filmManage.util.sql.RowMapper;

public class NewFilmRowMapper implements RowMapper<NewFilm> {

	@Override
	public NewFilm mapRow(ResultSet rs, int rowNum) throws SQLException {
		NewFilm nf = new NewFilm();
		nf.setId(rs.getInt("id"));
		nf.setName(rs.getString("name"));
		nf.setUpdateTime(rs.getDate("updateTime"));
		nf.setUrl(rs.getString("url"));
		nf.setWebSiteId(rs.getInt("id_website"));
		nf.setDelFlag(rs.getBoolean("delFlag"));
		return nf;
	}

	private NewFilmRowMapper() {

	}

	private static final NewFilmRowMapper INSTANCE = new NewFilmRowMapper();

	public static NewFilmRowMapper getInstance() {
		return INSTANCE;
	}
}
