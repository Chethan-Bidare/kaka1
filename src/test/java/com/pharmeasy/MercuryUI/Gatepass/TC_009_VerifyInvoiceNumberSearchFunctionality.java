package com.pharmeasy.MercuryUI.Gatepass;

import org.testng.annotations.Test;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.HashMap;

import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.GatepassPage;
import com.pharmeasy.MercuryUI.Page.LandingPage;

public class TC_009_VerifyInvoiceNumberSearchFunctionality extends TestBase {

	//Created by Chethan Bidare on 22-01-19
	
	@Test
	public void verifyInvoiceNumberSearch() throws InterruptedException {
		LandingPage landingPage = new LandingPage();
		GatepassPage gatePassPage = new GatepassPage();
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		Thread.sleep(5000);
		gatePassPage.searchByInvoiceNumberInGatepassPage(OR.getProperty("invoiceNumber"));
		Thread.sleep(5000);
		
		ArrayList<Integer> orderIDS = gatePassPage.fetchGatePassID();
		HashMap<Integer, ArrayList<String>> orderDetails = gatePassPage.fetchGatePassDetailsByID();
		for(int orderNum : orderIDS) {
			Assert.assertTrue(orderDetails.get(orderNum).contains(OR.getProperty("invoiceNumber")));
		}
	}
}
