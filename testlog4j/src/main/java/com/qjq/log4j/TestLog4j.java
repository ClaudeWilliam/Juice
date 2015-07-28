package com.qjq.log4j;

import org.apache.log4j.Logger;

public class TestLog4j {
	//其实log4j的很多东西都在于properties文件的配置，
	//不同的properties文件就会产生不同的log效果
	public static void main(String[] args) {
		Logger logger=Logger.getLogger(TestLog4j.class);
		logger.debug("this is debug");
		logger.error("this is error");
		logger.info("this is info");
		logger.warn("this is warn");
		
	}

}
