package com.tianyl.filmManage.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tianyl.filmManage.model.NewFilm;
import com.tianyl.filmManage.service.NewFilmService;

@WebServlet("/filmRedirect")
public class FilmRedirectServlet extends HttpServlet {

	private static final long serialVersionUID = 6701290261391186745L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.valueOf(req.getParameter("id"));
		NewFilmService.updateToDel(id);
		NewFilm film = NewFilmService.findById(id);
		resp.sendRedirect(film.getUrl());
	}

}
