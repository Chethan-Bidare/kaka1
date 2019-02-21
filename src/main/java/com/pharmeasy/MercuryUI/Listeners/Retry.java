package com.pharmeasy.MercuryUI.Listeners;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	
	
	public static final Logger log = Logger.getLogger(Retry.class.getSimpleName());
	
	int retryCount = 0 ;
	int maxretryCount = 2 ;
	

	@Override
	public boolean retry(ITestResult result) {
		if(retryCount<maxretryCount) {
			log.info("Retrying Test " + result.getName() + " with status " + getResultStatusName(result.getStatus()) + " for the " + (retryCount+1));
			retryCount++ ;
			return true ;
		}
		return false;
	}
	
	
	public String getResultStatusName(int status) {
		String resultName = null ;
		
		if(status==1) 
			resultName= "SUCCESS" ;
		
		if(status==2) 
			resultName= "FAILURE" ;
		
		if(status==3) 
			resultName = "SKIP" ;
		
		return resultName ;
		
	}

	
	
	
}
