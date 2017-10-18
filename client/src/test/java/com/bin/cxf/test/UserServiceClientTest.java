package com.bin.cxf.test;

import com.bin.cxf.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class UserServiceClientTest {
	
	private Client client;
	
	private final static String USER_SERVICE_WSDLURL = "http://localhost:8080/cxf-server/webservice/userService?wsdl";
	
	@Before
	public void init()
	{
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		client = dcf.createClient(USER_SERVICE_WSDLURL);
		client.setThreadLocalRequestContext(true);
	}
	
	@Test
	public void testGetUserInfoById() throws Exception
	{
		Object[] res = client.invoke("getUserInfoById", 10001);
		System.err.println(res[0]);
	}

	@Test
	public void testGetUsers() throws Exception
	{
		Object[] res = client.invoke("getUsers");
		System.err.println(res[0]);
	}

	@Test
	public void testAddUser() throws Exception
	{
		User user = new User();
		user.setId(100001);
		user.setName("qinshaobin");
		user.setDate(new Date());

		client.invoke("addUser", new ObjectMapper().writeValueAsString(user));
	}
}
