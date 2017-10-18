package com.bin.cxf.client.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.LRUMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.EndpointInfo;
import org.apache.cxf.service.model.ServiceInfo;

import java.net.URL;
import java.util.Collection;
import java.util.List;


/**
 * @author shaobin.qin
 */
@Slf4j
public class CxfCommunicationUtil {

	private final static LRUMap WS_CLIENT_CACHE = new LRUMap(200);

	private static Bus bus;
	
	/**
	 * @Description 动态webService调用
	 * @param wsUrl
	 *            webservcie地址
	 * @param method
	 *            调用方法
	 * @param arg
	 *            参数
	 * @return
	 * @throws Exception
	 * @author caobin
	 */
	public static Object wsInvoke(String wsUrl, String method, Object... arg) throws Exception {
		if (StringUtils.isBlank(wsUrl))
			throw new Exception("wsUrl is required.");
		Client client = getCxfClient(wsUrl, null, null);
		Object[] res = client.invoke(method, arg);
		return res[0];
	}

	/**
	 * 调用https的webservice
	 */
	public static Object wsInvokeHttps(String wsUrl, String method, Object... arg) throws Exception {
		if (StringUtils.isBlank(wsUrl))
			throw new Exception("wsUrl is required.");
		if(bus == null)
		{
	        URL busFile = Client.class.getResource("/cxf-conduit.xml");
	        bus = new SpringBusFactory().createBus(busFile.toString());
		}
		Client client = getCxfClient(wsUrl, null, bus);
		Object[] res = client.invoke(method, arg);
		return res[0];
	}

	@SuppressWarnings("unused")
	public static Object wsInvokeByWsdlFile(String wsUrl, URL wsdlLocation, String method, Object... arg) throws Exception {
		if (StringUtils.isBlank(wsUrl))
			throw new Exception("wsUrl is required.");
		Client client = getCxfClient(wsUrl, wsdlLocation, null);
		Object[] res = client.invoke(method, arg);
		return res[0];
	}

	private static Client getCxfClient(String wsUrl, URL wsdlLocation, Bus bus) {
		JaxWsDynamicClientFactory dcf;
		if(bus != null)
			dcf = JaxWsDynamicClientFactory.newInstance(bus);
		else
			dcf = JaxWsDynamicClientFactory.newInstance();

		String wsdlUrl = wsUrl.concat("?wsdl");
		if (!WS_CLIENT_CACHE.containsKey(wsUrl)) {
			synchronized (CxfCommunicationUtil.class) {
				if (!WS_CLIENT_CACHE.containsKey(wsUrl)) {
					Client client;
					if (null != wsdlLocation) {
						client = dcf.createClient(wsdlLocation);
					} else {
						client = dcf.createClient(wsdlUrl);
					}
					client.setThreadLocalRequestContext(true);
					List<ServiceInfo> lstSI = client.getEndpoint().getService().getServiceInfos();
					if (lstSI != null) {
						for (ServiceInfo si : lstSI) {
							Collection<EndpointInfo> cs = si.getEndpoints();
							if (cs != null) {
								for (EndpointInfo ei : cs) {
									ei.setAddress(wsUrl);
								}
							}
						}
					}
					WS_CLIENT_CACHE.put(wsUrl, client);
				}
			}
		}
		return (Client) WS_CLIENT_CACHE.get(wsUrl);
	}
}