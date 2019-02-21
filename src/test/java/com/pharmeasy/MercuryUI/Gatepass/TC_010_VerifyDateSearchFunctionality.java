package com.pharmeasy.MercuryUI.Gatepass;

import org.testng.annotations.Test;
import org.testng.Assert;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import com.pharmeasy.MercuryUI.Base.TestBase;
import com.pharmeasy.MercuryUI.Page.GatepassPage;
import com.pharmeasy.MercuryUI.Page.LandingPage;

public class TC_010_VerifyDateSearchFunctionality extends TestBase{

	//Created by Chethan Bidare on 23-01-19
	
	@Test
	public void verifyDateSearch() throws InterruptedException {
		LandingPage landingPage = new LandingPage();
		
		landingPage.loginByCredentials(OR.getProperty("userEmail"),OR.getProperty("userPwd"));
		Thread.sleep(5000);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
		String fullDate = formatter.format(cal.getTime());
		String date = fullDate.substring(0, 2);
		landingPage.searchByDate(date,date);
		Thread.sleep(5000);
		GatepassPage gatePassPage = new GatepassPage();
		ArrayList<String> dates = gatePassPage.fetchGatePassDate();
		System.out.println(dates);
		for(String orderDate : dates) {
			Assert.assertTrue(orderDate.contains(fullDate));
		}
	}
}
