package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.SettlementReport;


public interface SettlementReportDao {

	public List<SettlementReport> getAllSettlementReport();

	public SettlementReport getSettlementReportById(Integer id);

	public boolean insertSettlementReport(SettlementReport SettlementReport);

	public boolean updateSettlementReport(SettlementReport SettlementReport);

	public void deleteSettlementReport(Integer id);

	public List<SettlementReport> getSettlementReportByNamedQuery(String query, Map<String, Object> param);


}
