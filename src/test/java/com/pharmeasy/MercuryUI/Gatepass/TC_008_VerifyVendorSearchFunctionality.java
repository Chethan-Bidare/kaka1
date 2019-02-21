package com.pharmeasy.MercuryUI.Gatepass;


import org.testng.annotations.Test;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.HashMap;

import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.GatepassPage;
import com.pharmeasy.MercuryUI.Page.LandingPage;

public class TC_008_VerifyVendorSearchFunctionality extends TestBase{

	//Created by Chethan Bidare on 22-01-19
	
	@Test
	public void verifyVendorSearch() throws InterruptedException {
		LandingPage landingPage = new LandingPage();
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		Thread.sleep(5000);
		landingPage.searchByVendorName(OR.getProperty("vendorName"));
		Thread.sleep(5000);
		GatepassPage gatePassPage = new GatepassPage();
		ArrayList<Integer> orderIDS = gatePassPage.fetchGatePassID();
		HashMap<Integer, ArrayList<String>> orderDetails = gatePassPage.fetchGatePassDetailsByID();
		for(int orderNum : orderIDS) {
			Assert.assertTrue(orderDetails.get(orderNum).contains(OR.getProperty("vendorName")));
		}
	}
}
