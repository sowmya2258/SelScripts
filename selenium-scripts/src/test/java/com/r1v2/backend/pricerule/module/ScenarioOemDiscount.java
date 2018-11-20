package com.r1v2.backend.pricerule.module;

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
import com.qa.sc.pageobjects.PriceRulePage;
import com.qa.sc.pageobjects.SCPages;
import com.r1v2.backend.pagebuilder.module.ScenarioCamapignPage;
import com.r1v2.common.BaseTest;
import com.r1v2.common.DataBase;

public class ScenarioOemDiscount extends BaseTest {
	public static final Logger logger = LogManager.getLogger(ScenarioCamapignPage.class);
	DataBase database=getPageFactory().databse();
	SCPages scpages;
	PriceRulePage pricerule;
	private Map<String, String> td = getTestDataProperties();
	private PropertyFileUtil propUtil = new PropertyFileUtil("config");

	CSVTable loginpage = new CSVTable(td.get(propUtil.getString("region")+".loginDataFilePath"));
	List<CSVTableRow> login = loginpage.getRecords();

	CSVTable pricerulepage = new CSVTable(td.get(propUtil.getString("region")+".priceRuleFilePath"));
	List<CSVTableRow> priceruledata = pricerulepage.getRecords();
	
	CSVTableRow price = priceruledata.get(0);
	
	String regiondatabase=td.get(propUtil.getString("region")+".env");
	
	@BeforeClass
	public void setUpOnce1() {
		extentTest = report.createTest("SC OEM Discount ");
		CSVTableRow logindata = login.get(0);
			scpages=getPageFactory().scHomePage();
			pricerule=getPageFactory().priceRulePage();
			scpages.openSCLoginpage();
			scpages.goToHomePage(logindata.getString("admin_username"),logindata.getString("admin_password"));	
			
	}
	@Test(priority=1)
	public void testPG_1() {
				boolean actual= scpages.selectOrganization()
				.selectSiteList(price.getString("Dealer"));
				pricerule.navigatePriceRulePage();
		Assert.assertEquals(actual, true, " Navigate to PageBuilder Page on  Respective Dealer ");
	}
}