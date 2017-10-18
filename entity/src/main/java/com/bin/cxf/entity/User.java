package com.bin.cxf.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author shaobin.qin
 */
@Getter
@Setter
public class User {
	
	private int id;
	
	private String name;
	
	private Date date;
}
