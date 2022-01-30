package com.skyflight.servicedao;

import java.util.List;
import java.util.Map;

import com.skyflight.model.Markup;




public interface MarkupDao {



	public boolean insertMarkup(Markup markup);

	public boolean updateMarkup(Markup markup);

	public void deleteMarkup(Integer id);

	public List<Markup> getMarkupByNamedQuery(String query, Map<String, Object> param);

	public List<Markup> getAllAirline();

	public Markup getMarkupByid(int id);



}
