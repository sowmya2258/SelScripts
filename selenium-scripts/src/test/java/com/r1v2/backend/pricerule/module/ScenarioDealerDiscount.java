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

public class ScenarioDealerDiscount extends BaseTest {
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
	
	CSVTableRow price = priceruledata.get(1);
	
	String regiondatabase=td.get(propUtil.getString("region")+".env");
	
	@BeforeClass
	public void setUpOnce1() {
		extentTest = report.createTest(getClass().getName());
		CSVTableRow logindata = login.get(0);
		scpages=getPageFactory().scHomePage();
			pricerule=getPageFactory().priceRulePage();
			scpages.openSCLoginpage();
			scpages.goToHomePage(logindata.getString("admin_username"),logindata.getString("admin_password"));		
	}
	@Test(priority=1)
	public void testTD_1() {
				boolean actual= scpages.selectOrganization()
				.selectSiteList(price.getString("Dealer"));
				pricerule.navigatePriceRulePage();
		Assert.assertEquals(actual, true, " Navigate to PageBuilder Page on  Respective Dealer ");
	}

	@Test(priority=2)
	public void testTD_2() {
				boolean actual=pricerule.selectPriceCategoryDropdownitem(price.getString("Price Category"));
		Assert.assertEquals(actual, true, " Selecting PriceCategory DropDown Field Value ");
	}
	
	@Test(priority=3)
	public void testTD_3() {
				boolean actual=pricerule.selectVehicleTypeDropdownitem(price.getString("Vehicle Type"));
		Assert.assertEquals(actual, true, " Selecting VehicleType DropDown Field Value ");
	}
	
	@Test(priority=4)
	public void testTD_4()   {
				boolean actual=pricerule.selectProviderDropdownitem(price.getString("Provider"));
		Assert.assertEquals(actual, true, " Selecting Provider DropDown Field Value");
	}
	
	
	@Test(priority=5)
	public void testTD_5()  {
				boolean actual=pricerule.selectMakeDropdownitem(price.getString("Make"));
		Assert.assertEquals(actual, true, " Selecting Make DropDown Field Value");
	}
	
	
	@Test(priority=6)
	public void testTD_6()  {
				boolean actual=pricerule.selectModelDropdownitem(price.getString("Model"));
		Assert.assertEquals(actual, true, " Selecting Model DropDown Field Value");
	}
	
	@Test(priority=7)
	public void testTD_7()  {
				boolean actual=pricerule.selectTrimDropdownitem(price.getString("Trim"));
		Assert.assertEquals(actual, true, " Selecting Trim DropDown Field Value");
	}
	
	
	@Test(priority=8)
	public void testTD_8()  {
				boolean actual=pricerule.startDate();
		 Assert.assertEquals(actual, true, " Passing CurentDate For Start Date Field ");
	}
	
	@Test(priority=9)
	public void testTD_9()   {
				boolean actual=pricerule.selectDiscountDropdownitem(price.getString("Discount Type"));
			Assert.assertEquals(actual, true, " Selecting Provider DropDown Field Value");
	}
	
	@Test(priority=10)
	public void testTD_10()   {
				boolean actual=pricerule.discountValue(price.getString("DiscountValue"));
		Assert.assertEquals(actual, true, " Selecting Provider DropDown Field Value");
	}
	
	
	@Test(priority=11)
	public void testTD_11()   {
				boolean actual=pricerule.savePriceRule();
		Assert.assertEquals(actual, true, " Dealer Discount Price is Saved");
	}
	
}
