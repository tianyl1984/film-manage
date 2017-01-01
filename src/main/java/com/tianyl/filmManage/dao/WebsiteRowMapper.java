package com.tianyl.filmManage.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tianyl.filmManage.model.Website;
import com.tianyl.filmManage.util.sql.RowMapper;

public class WebsiteRowMapper implements RowMapper<Website> {

	@Override
	public Website mapRow(ResultSet rs, int rowNum) throws SQLException {
		Website ws = new Website();
		ws.setId(rs.getInt("id"));
		ws.setName(rs.getString("name"));
		ws.setUsed(rs.getBoolean("used"));
		return ws;
	}

	private WebsiteRowMapper() {

	}

	private static final WebsiteRowMapper INSTANCE = new WebsiteRowMapper();

	public static WebsiteRowMapper getInstance() {
		return INSTANCE;
	}
}
