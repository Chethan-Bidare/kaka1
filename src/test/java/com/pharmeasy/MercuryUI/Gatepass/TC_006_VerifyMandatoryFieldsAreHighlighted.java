package com.pharmeasy.MercuryUI.Gatepass;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.GatepassPage;
import com.pharmeasy.MercuryUI.Page.LandingPage;

public class TC_006_VerifyMandatoryFieldsAreHighlighted extends TestBase{

	//Created by Chethan Bidare on 22-01-19
	
	@Test(priority=0)
	public void verifyMandatoryFieldMSG_Invoicenum() throws InterruptedException {
		LandingPage landingPage = new LandingPage();
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		GatepassPage gatePassPage = new GatepassPage();
		landingPage.clickOnNewEntry();
		landingPage.selectVendor("VARDHMAN");
		gatePassPage.enterGatePassEntryDetails("", "100");
		gatePassPage.disablePOavailableslider();
		gatePassPage.clickOnCreateGatePassButton();
		Thread.sleep(2000);		
		Assert.assertEquals(gatePassPage.getBorderColorForInvoiceNumber(), APP.getProperty("redColor"));
	}
	
	//Created by Chethan Bidare on 22-01-19
	
	@Test(priority=1)
	public void verifyMandatoryFieldMSG_Amount() throws InterruptedException {
		LandingPage landingPage = new LandingPage();
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		GatepassPage gatePassPage = new GatepassPage();
		landingPage.clickOnNewEntry();
		landingPage.selectVendor("VARDHMAN");
		gatePassPage.enterGatePassEntryDetails("100", "");
		gatePassPage.disablePOavailableslider();
		gatePassPage.clickOnCreateGatePassButton();
		Thread.sleep(2000);		
		Assert.assertEquals(gatePassPage.getBorderColorForInvoiceAmount(), APP.getProperty("redColor"));
	}
}
