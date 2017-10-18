package com.bin.cxf.server.ws;

import javax.jws.WebService;

/**
 * @author shaobin.qin
 */
@WebService(targetNamespace="http://ws.cxf.bin.com/")
public interface HelloWorldWsService {
	
	String helloWorld();
	
	String sayHi(String text);
	
}
