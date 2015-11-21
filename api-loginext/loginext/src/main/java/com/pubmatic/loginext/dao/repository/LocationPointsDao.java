package com.pubmatic.loginext.dao.repository;

import org.springframework.data.repository.CrudRepository;

import com.pubmatic.loginext.bean.LocationPoints;

public interface LocationPointsDao extends CrudRepository<LocationPoints, Integer>{
}

/*public interface LocationPointsDao extends Repository<CreativeScan, Serializable> {
	void save(CreativeScan creativeScan);
}*/
