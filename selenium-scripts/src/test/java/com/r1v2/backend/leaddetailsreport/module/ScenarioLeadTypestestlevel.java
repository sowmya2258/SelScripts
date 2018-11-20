package com.r1v2.backend.leaddetailsreport.module;

import com.r1v2.backend.pagebuilder.module.ScenarioContentPage;
import com.r1v2.common.BaseTest;

import java.util.Iterator;
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



public class ScenarioLeadTypestestlevel extends BaseTest {
	
	public static final Logger logger = LogManager.getLogger(ScenarioContentPage.class);

	SCPages scpages;
	SCLeadreport scleadreport;
	public int i=0;
	
	
	DataBase database=getPageFactory().databse();
	
	 
	private Map<String, String> td = getTestDataProperties();
	private PropertyFileUtil propUtil = new PropertyFileUtil("config");
  
	CSVTable loginpage = new CSVTable(td.get(propUtil.getString("region")+".loginDataFilePath"));
	List<CSVTableRow> login = loginpage.getRecords();
	
	

	CSVTable leadDeatilsReport = new CSVTable(td.get(propUtil.getString("region")+".leadDetailsReportFilepath"));
		List<CSVTableRow> leads = leadDeatilsReport.getRecords();
	CSVTableRow leadsdata = leads.get(0);
	String regiondatabase=td.get(propUtil.getString("region")+".env");
	
	Iterator<CSVTableRow> itr = leads.iterator();
	CSVTableRow currentRow = itr.next();

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
	public void testPG_1() {
		boolean actual = scpages.verifyLogOutAndChangePasswordLinks();
		Assert.assertEquals(actual,true,"is not displayed on SC Home Page");
	}
	
	
	@Test(priority=2)

	public void testLR_1() {
		
		try{
		
		
			Iterator<CSVTableRow> itr = leads.iterator();
	        while(itr.hasNext()){
	        	
	        	
	        	CSVTableRow currentRow = itr.next();
		
		        String level = currentRow.getString("Level");
	        	
	        		if("oem".equalsIgnoreCase(level)){
	        			scleadreport.selectOrganization()
					.selectOEM(currentRow.getString("Dealers"));
	        			scleadreport.navigateLeadDetailsReport();
	        			testLR_2();
	        		
			} 
	        		if("group".equalsIgnoreCase(level)){
	        		 scleadreport.selectOrganization()
					.selectGroup((currentRow.getString("Dealers")));
	        		 scleadreport.navigateLeadDetailsReport();
	        		 testLR_2();
	        		 
		     }
			  if("dealer".equalsIgnoreCase(level)){
	        		 scleadreport.selectOrganization()
					.selectDealer((currentRow.getString("Dealers")));
	        		 scleadreport.navigateLeadDetailsReport();
	        		 testLR_2();
				            		
	        	 }
	        		
	        		
	        }
	        
		
		
		}
	        	 catch (Exception e) {
	     			System.out.println(e);
	     		}
		
	}
		
	     
		public void testLR_2() {
		
		boolean actual=scleadreport.selectNewLeads();
		Assert.assertEquals(actual, true, "New leads");
		
	}
	

	

}

