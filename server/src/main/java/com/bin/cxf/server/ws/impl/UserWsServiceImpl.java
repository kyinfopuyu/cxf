package com.bin.cxf.server.ws.impl;

import com.bin.cxf.entity.User;
import com.bin.cxf.server.ws.UserWsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
@WebService(serviceName="userService",
			targetNamespace="http://ws.cxf.bin.com/",
			endpointInterface= "com.bin.cxf.server.ws.UserWsService")
public class UserWsServiceImpl implements UserWsService {
	
	private Map<Integer, User> userMap = Maps.newTreeMap();
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	{
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		for(int i=0; i<5; i++)
		{
			User user = new User();
			user.setId(10000 + i);
			user.setName("qinshaobin" + i);
			user.setDate(new Date());
			userMap.put(user.getId(), user);
		}
	}

	@Override
	public String getUserInfoById(Integer id) throws Exception {
		
		return objectMapper.writeValueAsString(userMap.get(id));
	}

	@Override
	public String getUsers() throws Exception {
		
		return objectMapper.writeValueAsString(userMap);
	}

	@Override
	public String addUser(String userJsonStr) throws Exception {
		User user = objectMapper.readValue(userJsonStr, User.class);
		userMap.put(user.getId(), user);
		return "ok";
	}
}
