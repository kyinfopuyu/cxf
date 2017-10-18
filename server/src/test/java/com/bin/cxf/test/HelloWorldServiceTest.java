package com.bin.cxf.test;

import com.bin.cxf.server.ws.HelloWorldWsService;
import com.bin.cxf.server.ws.impl.HelloWorldWsServiceImpl;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;

import javax.xml.ws.Endpoint;

public class HelloWorldServiceTest{
	
	private static final String ADDRESS = "http://localhost:8080/helloWorld";
	
	@Test
	public void testHelloworldServer()
	{
		HelloWorldWsServiceImpl helloWorldServiceImpl = new HelloWorldWsServiceImpl();
		Endpoint.publish(ADDRESS, helloWorldServiceImpl);
		System.out.println("helloWorldServer service start");
	}
	
	@Test
	public void testHelloworldClient()
	{
		JaxWsProxyFactoryBean svr = new JaxWsProxyFactoryBean();
		svr.setServiceClass(HelloWorldWsService.class);
		svr.setAddress("http://localhost:8080/server/webservice/helloWorldService");
		HelloWorldWsService hService = (HelloWorldWsService) svr.create();
		
		System.out.println(hService.sayHi("cxf"));
		
	}

}
