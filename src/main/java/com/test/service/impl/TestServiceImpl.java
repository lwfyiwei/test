package com.test.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.ativemq.Producer;
import com.test.dao.GenericReadDao;
import com.test.dao.GenericWriteDao;
import com.test.entity.HcbTest;
import com.test.service.TestService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private GenericReadDao readDao;

	@Autowired
	private GenericWriteDao writeDao;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate redisTemplate;
	
	@Autowired
	private Producer producer;

	@Override
	@Transactional
	public Map<String, Object> getTestData(Map<String, Object> params) {
		Map<String, Object> rst = new HashMap<>();
		rst.put("code", "10000");
		rst.put("info", "success");
		
		HcbTest p1 = new HcbTest();
		p1.setAge(144);
		p1.setDescription("法律后果大概");
		p1.setName("修改大方双方都");
		
		readDao.insertAndReturnAffectedCount("com.test.dao.read.HcbTestMapper.insert", p1);
		
		HcbTest hcbTest = readDao.queryOne("com.test.dao.read.HcbTestMapper.selectByPrimaryKey", new Integer(1));
		log.info("hcbTest: "+hcbTest);
		rst.put("data", hcbTest);
		
		HcbTest p = new HcbTest();
		p.setAge(144);
		p.setDescription("法律后果大概");
		p.setName("端口供电方面");
		int count = writeDao.insertAndReturnAffectedCount("com.test.dao.write.HcbTestMapper.insert", p);
		log.info("inset count: "+count);
		
		stringRedisTemplate.opsForValue().set("test_liweifeng_redis", "999999");
		stringRedisTemplate.expire("test_liweifeng_redis", 1, TimeUnit.MINUTES);
		log.info("redis test_liweifeng_redis: "+stringRedisTemplate.opsForValue().get("test_liweifeng_redis"));
		
		producer.send("法律后果大概");
		
		return rst;
	}

}
