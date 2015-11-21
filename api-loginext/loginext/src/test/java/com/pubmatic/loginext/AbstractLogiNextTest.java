package com.pubmatic.loginext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pubmatic.loginext.dao.repository.LocationPointsDao;
import com.pubmatic.loginext.spring.LogiNextContextConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={LogiNextContextConfig.class})
public class AbstractLogiNextTest {

	@Autowired
	LocationPointsDao creativeScanDao;
	
	@Test
	public void testGet(){
		System.out.println("Give me a record : " + creativeScanDao.findOne(1));
	}
}
