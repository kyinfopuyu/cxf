package com.bin.cxf.server.ws;

import javax.jws.WebService;

/**
 * @author shaobin.qin
 */
@WebService(targetNamespace="http://ws.cxf.bin.com/")
public interface UserWsService {
	
	String getUserInfoById(Integer id) throws Exception;
	
	String getUsers() throws Exception;

	String addUser(String userJsonStr) throws Exception;
}
