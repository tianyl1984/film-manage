package com.tianyl.filmManage.dao;

import java.util.List;

import com.tianyl.filmManage.model.Website;
import com.tianyl.filmManage.util.sql.JdbcUtil;

public class WebsiteDAO {

	public static List<Website> findAll() {
		String sql = "select * from website where used = ? order by orderNum";
		return JdbcUtil.query(sql, WebsiteRowMapper.getInstance(), true);
	}

}
