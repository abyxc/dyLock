package com.mnsn.utils;

import org.apache.log4j.Logger;
public class Logout {
        public static Logger logger = Logger.getLogger(Logout.class);
               
		public static void main(String[] args) { 
			 
			//  记录 debug 级别的信息   
			logger.debug("This is debug message."); 
			//  记录 info 级别的信息   
			logger.info("This is info message.");
			//  记录 error 级别的信息   
			logger.error("This is error message."); 
		} 
} 