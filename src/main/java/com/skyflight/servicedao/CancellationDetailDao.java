package com.skyflight.servicedao;

import java.util.List;
import java.util.Map;

import com.skyflight.model.CancellationDetail;

public interface CancellationDetailDao {

	List<CancellationDetail> getCancellationDetailByNamedQuery(String query, Map<String, Object> param);

	boolean insertCancellationDetail(CancellationDetail airline);

}
