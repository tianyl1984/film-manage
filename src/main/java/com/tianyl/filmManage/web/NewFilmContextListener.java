package com.tianyl.filmManage.web;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.tianyl.filmManage.service.FilmCrawlerManager;
import com.tianyl.filmManage.util.LogManager;

@WebListener
public class NewFilmContextListener implements ServletContextListener {

	private Timer timer = new Timer();

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				LogManager.log("start crawl");
				try {
					FilmCrawlerManager.crawl();
				} catch (Exception e) {
					LogManager.log(e);
				}
				LogManager.log("end crawl");
			}
		}, 10 * 1000, 1000 * 60 * 60 * 2);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		timer.cancel();
	}

}
