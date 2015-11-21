package com.pubmatic.loginext.services;

import java.util.List;

import com.pubmatic.loginext.bean.LocationPoints;

public interface LocationPointsService {

	LocationPoints startAttributeScan(LocationPoints creativeScan);
	
	//LocationPoints submitCreative(String encodedCreativeContent) throws Exception;
	
	LocationPoints insertLocations(LocationPoints locationPoint) throws Exception;

	List<LocationPoints> getAllLocations() throws Exception;

	List<LocationPoints> insertMultipleLocations(List<LocationPoints> locationPoints);
}
