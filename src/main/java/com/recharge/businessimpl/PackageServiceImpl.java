package com.recharge.businessimpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.recharge.businessdao.PackageService;
import com.recharge.model.PackageDetail;
import com.recharge.servicedao.PackageDetailDao;
@Service("packageservice")
public class PackageServiceImpl implements PackageService {
	private static final Logger log=Logger.getLogger(PackageService.class);

	@Autowired PackageDetailDao packagedetailDao;
	
	
	@Override
	public String generatePackageId() {
		PackageDetail pd = null;
		String package_id = "";
		int id = 0;
		try {
			pd = packagedetailDao.getLastPackage();
			if (pd != null) {
				id = pd.getId() + 1;
				package_id = "P000" + id;
			} else {
				id = id + 1;
				package_id = "P000" + id;
			}
			log.warn("generatePackageId:::::::::::::::" + package_id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return package_id;
	}

}
