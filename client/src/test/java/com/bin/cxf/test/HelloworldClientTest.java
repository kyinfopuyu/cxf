package com.bin.cxf.test;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Before;
import org.junit.Test;

public class HelloworldClientTest {
	
	private Client client;
	
	private final static String HELLO_WORLD_WSDLURL = "http://localhost:8080/cxf-server/webservice/helloWorldService?wsdl";

	@Before
	public void init()
	{
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		client = dcf.createClient(HELLO_WORLD_WSDLURL);
		client.setThreadLocalRequestContext(true);
	}
	
	@Test
	public void testHelloworld() throws Exception
	{
		Object[] res = client.invoke("helloWorld");
		System.err.println(res[0]);
	}

	@Test
	public void testSayHi() throws Exception
	{
		Object[] res = client.invoke("sayHi", "qinshaobin");
		System.err.println(res[0]);
	}
}
