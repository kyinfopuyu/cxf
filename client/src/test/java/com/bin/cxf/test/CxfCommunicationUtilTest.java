package com.bin.cxf.test;

import com.bin.cxf.client.utils.CxfCommunicationUtil;
import com.bin.cxf.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author shaobin.qin
 */
public class CxfCommunicationUtilTest {

	private final static String HELLO_WORLD_WSDLURL = "http://localhost:8080/cxf-server/webservice/helloWorldService";

	private final static String USER_SERVICE_WSDLURL = "http://localhost:8080/cxf-server/webservice/userService";

	private ObjectMapper objectMapper;

	@Before
	public void init()
	{
		objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	}

	@Test
	public void testHelloworld() throws Exception
	{
		Object res = CxfCommunicationUtil.wsInvoke(HELLO_WORLD_WSDLURL, "helloWorld");
		System.err.println(res);
	}

	@Test
	public void testSayHi() throws Exception
	{
		Object res = CxfCommunicationUtil.wsInvoke(HELLO_WORLD_WSDLURL, "sayHi", "qinshaobin");
		System.err.println(res);
	}
	
	@Test
	public void testGetUserInfoById() throws Exception
	{
		Object res = CxfCommunicationUtil.wsInvoke(USER_SERVICE_WSDLURL, "getUserInfoById", 10001);
		System.err.println(res);
	}

	@Test
	public void testGetUsers() throws Exception
	{
		Object res = CxfCommunicationUtil.wsInvoke(USER_SERVICE_WSDLURL, "getUsers");
		System.err.println(res);
	}

	@Test
	public void testAddUser() throws Exception
	{
		User user = new User();
		user.setId(100001);
		user.setName("qinshaobin");
		user.setDate(new Date());
		Object res = CxfCommunicationUtil.wsInvoke(USER_SERVICE_WSDLURL, "addUser", objectMapper.writeValueAsString(user));
		System.err.println(res);
	}

	@Test
	public void testGetUsersHttps() throws Exception
	{
		Object res = CxfCommunicationUtil.wsInvokeHttps(USER_SERVICE_WSDLURL, "getUsers");
		System.err.println(res);
	}
}
