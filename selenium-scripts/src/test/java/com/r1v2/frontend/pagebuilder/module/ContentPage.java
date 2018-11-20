package com.r1v2.frontend.pagebuilder.module;

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
import com.qa.sc.pageobjects.SCPages;
import com.r1v2.backend.pagebuilder.module.ScenarioContentPage;
import com.r1v2.common.BaseTest;
import com.r1v2.common.DataBase;

public class ContentPage extends BaseTest {
	public static final Logger logger = LogManager.getLogger(ScenarioContentPage.class);
	DataBase database;
	SCPages pages;

	private Map<String, String> td = getTestDataProperties();
	private PropertyFileUtil propUtil = new PropertyFileUtil("config");

	CSVTable loginpage = new CSVTable(td.get(propUtil.getString("region") + ".loginDataFilePath"));
	List<CSVTableRow> login = loginpage.getRecords();
	CSVTableRow loginData = login.get(0);
	
	CSVTable pagebuilderpage = new CSVTable(td.get(propUtil.getString("region") + ".PageBuilderFilePath"));
	List<CSVTableRow> page = pagebuilderpage.getRecords();
	CSVTableRow pagesdata = page.get(0);

	String regiondatabase = td.get(propUtil.getString("region") + ".env");
	 String dealerID=pagesdata.getString("DealerId");
	 String userID=loginData.getString("UserId");
	
	
	@BeforeClass
	public void setUpOnce1() throws InterruptedException {
		extentTest = report.createTest(getClass().getName());
		database = getPageFactory().databse();
		pages = getPageFactory().scHomePage();
		
		String processqueQuery="select  module_id  from  process_que where  page_type='CONT'"
				+ "and change_flag=1 and   fk_dealer_id="+dealerID+"";
		Utility.processQue(processqueQuery);
	}

	@Test(priority = 1)
	public void testCOPG_1() {
		CSVTableRow pagesdata = page.get(3);
		 
			String query ="select page_url from izmoweb_r1v2.idw_dealer_webpages where  page_type='CONT' and"
				+ " fk_dealer_id="+dealerID+" and user_added="+userID+" and status='ACTV'";
					String webpageId = database.executeSQLQuery(regiondatabase, query);
		 System.out.println(webpageId);
		pages.frontendUrl(pagesdata.getString("Dealers") + webpageId);
				
	}
	
	@Test(priority = 2)
	public void testCOPG_2() {
			boolean actual=	pages.veirfyFrontEndPageTitle(pagesdata.getString("Title"));
			Utility.captureScreenshot(driver, "Front end ContentPage");
		Assert.assertEquals(actual, true, " Content Page is Loading on FrontEnd ");
	}
	
	@Test(priority=3)
	public void testCOPG_3() {
		boolean actual=pages.browserClose();			
		    Assert.assertEquals(actual, true, " Close the browser ");
    }
}



