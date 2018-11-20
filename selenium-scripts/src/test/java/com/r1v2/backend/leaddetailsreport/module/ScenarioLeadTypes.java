package com.r1v2.backend.leaddetailsreport.module;

import com.r1v2.backend.pagebuilder.module.ScenarioContentPage;
import com.r1v2.common.BaseTest;

import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.core.util.CSVTable;
import com.core.util.CSVTableRow;
import com.core.util.PropertyFileUtil;
import com.core.util.Utility;
import com.qa.sc.pageobjects.SCLeadreport;
import com.qa.sc.pageobjects.SCPages;
import com.r1v2.common.BaseTest;
import com.r1v2.common.DataBase;



public class ScenarioLeadTypes extends BaseTest {
	
	public static final Logger logger = LogManager.getLogger(ScenarioContentPage.class);

	SCPages scpages;
	SCLeadreport scleadreport;
	public int i=0;
	
	
	DataBase database=getPageFactory().databse();
	
	 
	private Map<String, String> td = getTestDataProperties();
	private PropertyFileUtil propUtil = new PropertyFileUtil("config");
  
	CSVTable loginpage = new CSVTable(td.get(propUtil.getString("region")+".loginDataFilePath"));
	List<CSVTableRow> login = loginpage.getRecords();

	CSVTable pagebuilderpage = new CSVTable(td.get(propUtil.getString("region")+".PageBuilderFilePath"));
	List<CSVTableRow> page = pagebuilderpage.getRecords();
	CSVTableRow pagesdata = page.get(0);
	 
	CSVTable leadDeatilsReport = new CSVTable(td.get(propUtil.getString("region")+".leadDetailsReportFilepath"));
		List<CSVTableRow> leads = leadDeatilsReport.getRecords();
	CSVTableRow leadsdata = leads.get(0);
	String regiondatabase=td.get(propUtil.getString("region")+".env");

	String csv=td.get(propUtil.getString("region")+".leadDetailsReportFilepath");
	
	
	@BeforeClass
	public void setUpOnce1() {
		extentTest = report.createTest(getClass().getName());
		CSVTableRow logindata = login.get(0);
			scpages=getPageFactory().scHomePage();
			scleadreport  = getPageFactory().scleadreport();
            scpages.openSCLoginpage()
			.goToHomePage(logindata.getString("admin_username"),logindata.getString("admin_password"));	
	}
	
	@Test(priority=1)
	public void testLR() {
		boolean actual = scpages.verifyLogOutAndChangePasswordLinks();
		Assert.assertEquals(actual,true,"is not displayed on SC Home Page");
	}
	
	
	@Test(priority=2)

	public void testLR_1() {
		
		/*boolean actual= scleadreport.selectOrganization()
				.selectSiteList(leadsdata.getString("Dealers"));
				scleadreport.navigateLeadDetailsReport();
		Assert.assertEquals(actual, true," Navigate to Leads Deatils report page for  Respective Dealer ");*/
		
		boolean actual= scpages.selectOrganization()
				.selectSiteList(leadsdata.getString("Dealers"));
		scleadreport.navigateLeadDetailsReport();
				Assert.assertEquals(actual, true," Navigate to Leads Deatils report page for  Respective Dealer ");
	}
	
	/*@Test(priority=3)
	public void testLR_2() {
		boolean actual=scleadreport.selectNewLeads();
		Assert.assertEquals(actual, true, "New leads");
				
	}
		

	@Test(priority=4)
	public void testLR_3() {
		boolean actual=scleadreport.selectprocessedLeads();
		Assert.assertEquals(actual, true, "Processed leads");		
	}
	

	@Test(priority=5)
	public void testLR_4() {
		boolean actual=   scleadreport.selectjunkleads();
		Assert.assertEquals(actual, true, "Junk leads");			
	}
	
	@Test(priority=6)
	public void testLR_5() {
		boolean actual=   scleadreport.selectdeletedleads();
		Assert.assertEquals(actual, true, "Deleted leads");			
	}
	
	@Test(priority=7)
	public void testLR_6() {
		boolean actual= scleadreport.selectconfmissedlead();
		Assert.assertEquals(actual, true, "Confmissed leads");			
	}
	
	@Test(priority=8)
	public void testLR_7() {
		boolean actual=   scleadreport.selectshowall();
		Assert.assertEquals(actual, true, "ShowALL leads");			
	}
	
	
	@Test(priority=9)
	public void testLR_8(){
		
		boolean actual=   scleadreport.dateselection();
		Assert.assertEquals(actual, true, "DateRange");
		
	}*/
	
	@Test(priority=10)
	public void testLR_9(){
		boolean actual= scleadreport.movetodeleted();
		Assert.assertEquals(actual, true, "movetodeleted");
	}
	
	/*@Test(priority=11)
	public void testLR_10(){
		boolean actual= scleadreport.reprocess();
		Assert.assertEquals(actual, true, "reprocess");
	}
	
	@Test(priority=12)
	public void testLR_11(){
		boolean actual= scleadreport.movetojunk();
		Assert.assertEquals(actual, true, "movetojunk");
	}
	
	@Test(priority=13)
	public void testLR_12(){
		boolean actual=scleadreport.numberofleads();
		Assert.assertEquals(actual,true,"numberofleads_10");
	}
	
	@Test(priority=14)
	public void testLR_13(){
		boolean actual=scleadreport.numberofleads1();
		Assert.assertEquals(actual,true,"numberofleads_25");
	}
	
	@Test(priority=15)
	public void testLR_14(){
		boolean actual=scleadreport.numberofleads2();
		Assert.assertEquals(actual,true,"numberofleads_50");
	}
	
	@Test(priority=16)
	public void testLR_15(){
		boolean actual=scleadreport.numberofleads3();
		Assert.assertEquals(actual,true,"numberofleads_100");
	}*/

}

