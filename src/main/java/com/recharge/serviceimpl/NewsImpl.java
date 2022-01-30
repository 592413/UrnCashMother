package com.recharge.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.recharge.model.Api;
import com.recharge.model.News;
import com.recharge.servicedao.NewsDao;

@Repository("newsDao")
public class NewsImpl implements NewsDao{
	private static final Logger logger_log = Logger.getLogger(NewsDao.class);
	@Autowired private SessionFactory sessionFactory;
	
	@Override
	public List<News> getAllNews() {
		List<News> list = new ArrayList<News>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(News.class).list();
			String message = "Method : getAllNews() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getAllNews Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public News getNewsByApId(String newsId) {
		Session session = sessionFactory.openSession();
		News news = new News();
		try {
			news = session.get(News.class, newsId);
			String message = "Method : getNewsByApId(newsId) || newsId: "+ news.getNewsid();
			logger_log.warn(message);

		} catch (Exception e) {
			news = null;
			String message = "getNewsByApId(newsId) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return news;
	}

	@Override
	public boolean insertNews(News news) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(news);
			String message = "QUERY : Save News|| ID: " + news;
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertNews(news)  Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateNews(News news) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(news);
			String message = "QUERY : Update News || ID: " + news;
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "updateNews(news) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public void deleteNews(int newsId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<News> getNewsByNamedQuery(String query, Map<String, Object> param) {
		List<News> list = new ArrayList<News>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getNewsByNamedQuery() "+list.size();
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
