package com.bin.cxf.server.config;

import com.google.common.collect.Lists;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author shaobin.qin
 */
@Configuration
@ImportResource("classpath:spring-ws.xml")
public class CxfServerConfig {

	@Bean
	public ServletRegistrationBean cxfServlet() {
		ServletRegistrationBean cxfServlet = new ServletRegistrationBean();
		cxfServlet.setServlet(new CXFServlet());
		cxfServlet.setLoadOnStartup(1);
		cxfServlet.setUrlMappings(Lists.newArrayList("/webservice/*"));
		return cxfServlet;
	}
}
