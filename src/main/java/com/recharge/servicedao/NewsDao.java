package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.News;

public interface NewsDao {
	public List<News> getAllNews();

	public News getNewsByApId(String newsId);

	public boolean insertNews(News news);

	public boolean updateNews(News news);

	public void deleteNews(int newsId);

	public List<News> getNewsByNamedQuery(String query, Map<String, Object> param);
}
