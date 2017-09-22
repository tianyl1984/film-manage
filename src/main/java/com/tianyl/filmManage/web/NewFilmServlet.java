package com.tianyl.filmManage.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tianyl.filmManage.service.NewFilmService;
import com.tianyl.filmManage.util.DateUtil;

@WebServlet("/newFilm")
public class NewFilmServlet extends HttpServlet {

	private static final long serialVersionUID = -5757275960482413170L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String jsonResult = "";
		if (uri.contains("/not-del")) {
			jsonResult = NewFilmService.findNotDel();
		} else {
			String dateStr = req.getParameter("updateTime");
			Date sdate = DateUtil.parseDate(dateStr + " 00:00:00");
			Date edate = DateUtil.parseDate(dateStr + " 23:59:59");
			jsonResult = NewFilmService.findByDate(sdate, edate);
		}
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().print(jsonResult);
	}

}
