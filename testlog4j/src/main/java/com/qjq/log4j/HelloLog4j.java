package com.qjq.log4j;

import org.apache.log4j.Logger;

public class HelloLog4j {
	private static Logger logger = Logger.getLogger(HelloLog4j.class);

	public static void main(String[] args) {
		logger.debug("this message is debug");
		logger.info("this message is info");
		logger.error("this message is error");
		logger.warn("this message is warn");
		logger.fatal("this message is fatal");
		
	}

}
