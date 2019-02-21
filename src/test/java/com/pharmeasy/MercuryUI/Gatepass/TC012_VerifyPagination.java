package com.pharmeasy.MercuryUI.Gatepass;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.GatepassPage;
import com.pharmeasy.MercuryUI.Page.LandingPage;

public class TC012_VerifyPagination extends TestBase{

	
	public static final Logger log = Logger.getLogger(TC012_VerifyPagination.class.getSimpleName());
	
	@Test
	public void verifyPaginationNEXTbuttonInGatepassPage() throws InterruptedException {

		log.info(" ====== Test Started ======");
		
		LandingPage landingPage = new LandingPage();
		GatepassPage gatePassPage = new GatepassPage();
		
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		Thread.sleep(5000);
		
		for (int i = 1; i <5; i++) {
			ArrayList<Integer> beforePagination = gatePassPage.fetchGatePassID();
			landingPage.clickOnPaginationNEXTbutton();
			Thread.sleep(3000);
			ArrayList<Integer> afterPagination = gatePassPage.fetchGatePassID();
			Assert.assertTrue(!beforePagination.equals(afterPagination));
		}
		
		for (int i = 1; i <3; i++) {
			ArrayList<Integer> beforePagination = gatePassPage.fetchGatePassID();
			landingPage.clickOnPaginationPREVbutton();
			Thread.sleep(3000);
			ArrayList<Integer> afterPagination = gatePassPage.fetchGatePassID();
			Assert.assertTrue(!beforePagination.equals(afterPagination));
		}
		
	}
}
