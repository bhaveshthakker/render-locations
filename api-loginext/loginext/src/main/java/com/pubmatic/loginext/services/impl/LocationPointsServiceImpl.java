package com.pubmatic.loginext.services.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.pubmatic.loginext.bean.LocationPoints;
import com.pubmatic.loginext.dao.repository.LocationPointsDao;
import com.pubmatic.loginext.services.LocationPointsService;

@Service
// @Async
public class LocationPointsServiceImpl implements LocationPointsService {

	@Autowired
	LocationPointsDao locationPointsDao;

	@Override
	public LocationPoints startAttributeScan(LocationPoints creativeScan) {
		System.out.println("LocationPointsServiceImpl.startAttributeScan()");
		return creativeScan;
	}

	@Override
	public LocationPoints insertLocations(LocationPoints locationPoint)
			throws Exception {
		System.out.println("LocationPointsServiceImpl.submitCreative()");
		locationPoint = locationPointsDao.save(locationPoint);
		return locationPoint;
	}

	@Async
	public LocationPoints scanCreative(LocationPoints creativeScan,
			String encodedCreativeContent) throws Exception {
		System.out.println("LocationPointsServiceImpl.scanCreative()");
		return creativeScan;
	}

	private String writeToFile(String encodedCreativeContent)
			throws IOException {
		System.out.println("LocationPointsServiceImpl.writeToFile()");
		return encodedCreativeContent;
	}

	private LocationPoints insertCreative(String encodedCreativeContent) {
		System.out.println("LocationPointsServiceImpl.insertCreative()");
		return null;
	}

	@Override
	public List<LocationPoints> getAllLocations() throws Exception {
		List<LocationPoints> locationPoints = (List<LocationPoints>) locationPointsDao.findAll();
		return locationPoints;
	}

	@Override
	public List<LocationPoints> insertMultipleLocations(
			List<LocationPoints> locationPoints) {
		locationPointsDao.save(locationPoints);
		return null;
	}
}
