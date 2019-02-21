package com.pharmeasy.MercuryUI.Gatepass;

import org.testng.annotations.Test;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.DataProvider;
import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.GatepassPage;
import com.pharmeasy.MercuryUI.Page.LandingPage;
import com.pharmeasy.MercuryUI.Page.PurchaseEntryPage;

public class TC_004_VerifyGatePassCreatedDetailsInOrderDetails extends TestBase{

	
	@DataProvider(name="TestData")
	public Object[][] getTestData(){
		String[][] getData = readExcel("TestData.xlsx", "CreateGatePass");
		return getData ;
		
	}
	
	
			//Verifying New gate pass details in Order Details 
			//This method is created by Chethan Bidare on 22-01-19
			
			@Test(dataProvider="TestData")
			public void verifyGatePassCreatedDetailsInOrderDetails(String vendorName,String invNumber,String invAmount) throws InterruptedException {
				LandingPage landingPage = new LandingPage();
				landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
				GatepassPage gatePassPage = new GatepassPage();
				PurchaseEntryPage pe = new PurchaseEntryPage();
				
				int orderIDBeforeGatePassCreation = gatePassPage.fetchGatePassID().get(gatePassPage.fetchGatePassID().size()-1);
				landingPage.clickOnNewEntry();
				landingPage.selectVendor(vendorName);
				String genInvNum = pe.getInvoiceNum();
				gatePassPage.enterGatePassEntryDetails(genInvNum,invAmount);
				gatePassPage.disablePOavailableslider();
				gatePassPage.clickOnCreateGatePassButton();
				Thread.sleep(1000);
				Assert.assertEquals(gatePassPage.getToastMSG(), APP.getProperty("GatePassSuccessMSG"));
				Thread.sleep(5000);
				int orderIDAfterGatePassCreation = gatePassPage.fetchGatePassID().get(gatePassPage.fetchGatePassID().size()-1);
				HashMap<Integer,ArrayList<String>> orderDetailsAfterGatePassCreation = gatePassPage.fetchGatePassDetailsByID();
				Assert.assertEquals(orderIDAfterGatePassCreation, orderIDBeforeGatePassCreation+1);
				Assert.assertTrue(orderDetailsAfterGatePassCreation.get(orderIDAfterGatePassCreation).contains(vendorName));
				Assert.assertTrue(orderDetailsAfterGatePassCreation.get(orderIDAfterGatePassCreation).contains(genInvNum));
				invAmount= invAmount.substring(0,invAmount.length()-2);
				Assert.assertTrue(orderDetailsAfterGatePassCreation.get(orderIDAfterGatePassCreation).contains("\u20B9"+invAmount));
				
			}
	
}
