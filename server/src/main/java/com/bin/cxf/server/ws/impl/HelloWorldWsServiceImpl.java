package com.bin.cxf.server.ws.impl;

import com.bin.cxf.server.ws.HelloWorldWsService;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

@Service
@WebService(serviceName = "helloWorldService",
			targetNamespace="http://ws.cxf.bin.com/",
			endpointInterface = "com.bin.cxf.server.ws.HelloWorldWsService")
public class HelloWorldWsServiceImpl implements HelloWorldWsService {
	
	@Override
	public String sayHi(String text) {
		return "hello," + text;
	}

	@Override
	public String helloWorld() {
		return "helloWorld";
	}

}
