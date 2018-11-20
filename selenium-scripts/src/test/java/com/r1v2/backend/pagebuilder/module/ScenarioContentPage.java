package com.r1v2.backend.pagebuilder.module;

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
import com.qa.sc.pageobjects.SCPages;
import com.r1v2.common.BaseTest;
import com.r1v2.common.DataBase;

public class ScenarioContentPage extends BaseTest{
	public static final Logger logger = LogManager.getLogger(ScenarioContentPage.class);

	SCPages scpages;
	DataBase database=getPageFactory().databse();
	private Map<String, String> td = getTestDataProperties();
	private PropertyFileUtil propUtil = new PropertyFileUtil("config");
  
	CSVTable loginpage = new CSVTable(td.get(propUtil.getString("region")+".loginDataFilePath"));
	List<CSVTableRow> login = loginpage.getRecords();

	CSVTable pagebuilderpage = new CSVTable(td.get(propUtil.getString("region")+".PageBuilderFilePath"));
	List<CSVTableRow> page = pagebuilderpage.getRecords();
	
	Iterator<CSVTableRow> itr = page.iterator();
	CSVTableRow currentRow = itr.next();
	 
	String regiondatabase=td.get(propUtil.getString("region")+".env");
	String csv=td.get(propUtil.getString("region")+".PageBuilderFilePath");

	
	@BeforeClass
	public void setUpOnce() {
		extentTest = report.createTest(getClass().getName());
		CSVTableRow logindata = login.get(0);
			scpages=getPageFactory().scHomePage();
			scpages.openSCLoginpage()
			.goToHomePage(logindata.getString("admin_username"),logindata.getString("admin_password"));	
			//System.out.println(currentRow);
	}
	
	@Test(priority=1)
	public void testPG_1() {
		boolean actual = scpages.verifyLogOutAndChangePasswordLinks();
		Assert.assertEquals(actual,true,"is not displayed on SC Home Page");
	}
	
	
	@Test(priority=2)
	 public void testPG_2() {
		try{
		Iterator<CSVTableRow> itr = page.iterator();
        while(itr.hasNext()){
        	CSVTableRow currentRow = itr.next();
        	String level = currentRow.getString("Level");
        	
        		if("oem".equalsIgnoreCase(level)){
        		scpages.selectOrganization()
				.selectOEM(currentRow.getString("Dealers"));
        		scpages.navigatePageBuilder();
		}
       
        	 else if("group".equalsIgnoreCase(level)){
        		 scpages.selectOrganization()
				.selectGroup((currentRow.getString("Dealers")));
				scpages.navigatePageBuilder();
	     }
        
        	 else if("dealer".equalsIgnoreCase(level)){
        		 scpages.selectOrganization()
				.selectDealer((currentRow.getString("Dealers")));
			    scpages.navigatePageBuilder();	
			            		
        }
            	testPG_3(level);
            	testPG_4();
            	testPG_5(level);
               	testPG_7(level);
            	testPG_8(level);
            	testPG_10(level);
        	}
		}
        catch (Exception e) {
			System.out.println(e);
		}
	}
	 
	
	public void testPG_3(String level){
		try{
				if("oem".equalsIgnoreCase(level)){
			boolean actual=scpages
				.verifyMandatoryField();
				scpages.verifyOEMArea();
				scpages.selectOEMArea();
		Assert.assertEquals(actual, true, " OEM Area and checkbox is Displayed on  PageBuilder Page ");
		}
		else if(level.equalsIgnoreCase("group")||(level.equalsIgnoreCase("Dealer"))){
			//throw new SkipException("This Method is only for OEM Level");
		}
		}
		
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
		
	//@Test(priority=3)
	public void testPG_4() {
		try{
		
			boolean actual= scpages
					.verifyContentPageButton();
			Assert.assertEquals(actual, true, " Radio Button is Selected on PageBuilder Page ");
		}			
		catch (Exception e){
				System.out.println(e);
			}
	}
			
		

	public void testPG_5(String level) {
				try{
				if("oem".equalsIgnoreCase(level)){
		      	boolean actual= scpages.verifyMandatoryField();
				scpages.pageTitle(currentRow.getString("Title"));
				scpages.verifyMandatoryField();
				scpages.englishPageTitle(currentRow.getString("Title1"));
				scpages.verifyMandatoryField();
				scpages.pageUrl(currentRow.getString("Url"));
				scpages.verifyMandatoryField();
				scpages.englishPageUrl(currentRow.getString("Url1"));
			 Assert.assertEquals(actual, true, " Title and Url  data  passing on Adding Pagefor OEM ");
		 	
		   }
			else if("group".equalsIgnoreCase(level)){
				boolean actual= scpages.
						verifyMandatoryField();
				scpages.pageTitle(currentRow.getString("Title"));
				scpages.verifyMandatoryField();
				scpages.pageUrl(currentRow.getString("Url"));
			Assert.assertEquals(actual, true, " Title and Url  data  passing on Adding Page for Group ");
		 }
			else if("dealer".equalsIgnoreCase(level)){
				
				boolean actual= scpages.
						verifyMandatoryField();
				scpages.pageTitle(currentRow.getString("Title"));
				scpages.verifyMandatoryField();
				scpages.pageUrl(currentRow.getString("Url"));
				Assert.assertEquals(actual, true, " Title and Url  data  passing on Adding Page for Delaer ");
			}
		}
	catch(Exception  e){
		System.out.println(e);
	}
	}	

	///@Test(priority=5)
	public void testPG_7(String level) {
		try{
		if(level.equalsIgnoreCase("oem")){
					boolean actual=scpages.
					selectDepartmentdropdownitem(currentRow.getString("Department"));
		Assert.assertEquals(actual, true, " Selecting Deparment on Department DropDown Field ");
		}
		else if(level.equalsIgnoreCase("group")){
				boolean actual=scpages.
					selectDepartmentdropdownitem(currentRow.getString("Department"));
		Assert.assertEquals(actual, true, " Selecting Deparment on Department DropDown Field ");
		}
		else if(level.equalsIgnoreCase("dealer")){
			boolean actual=scpages.
					selectDepartmentdropdownitem(currentRow.getString("Department"));
		Assert.assertEquals(actual, true, " Selecting Deparment on Department DropDown Field ");
		}
		}
	catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	//@Test(priority=6)
	public void testPG_8(String level) {
		try{
		Iterator<CSVTableRow> itr = page.iterator();
		CSVTableRow currentRow = itr.next();	
				if("oem".equalsIgnoreCase(level)){
					boolean actual=
						scpages.resposniveContent(currentRow.getString("Responsive_Content"));
					    scpages.resposniveEnglishContent(currentRow.getString("Responsive_Content1"));
			   Assert.assertEquals(actual, true, "Responsive Content data is  passing Responsive Editor ");
					    
				}
        			else if(level.equalsIgnoreCase("group")){
        				boolean actual=
					scpages.resposniveContent(currentRow.getString("Responsive_Content"));
				Assert.assertEquals(actual, true, "Responsive Content data is  passing Responsive Editor ");
				}
        			else if(level.equalsIgnoreCase("Dealer")){
        				boolean actual=
    					scpages.resposniveContent(currentRow.getString("Responsive_Content"));
    			Assert.assertEquals(actual, true, "Responsive Content data is  passing Responsive Editor ");
        			}
			}
        catch(Exception e){
        		System.out.println(e);
        	}
	}
  
	//@Test(priority=7)
	public void testPG_10(String level) {
			try{
				if(level.equalsIgnoreCase("group")||(level.equalsIgnoreCase("Dealer"))||level.equalsIgnoreCase("oem"));
				boolean actual= scpages
						.savePage()
						.veirfyPageTitle(currentRow.getString("PageTitle"));
			Assert.assertEquals(actual, true, " Content Page is Saved  ");
		}
			catch (Exception e) {
			 System.out.println(e);
			}
        }
	
	//@Test(priority=8)
	public void testPG_11() {
		boolean actual= scpages.logoutAdmin();
		                scpages.browserClose();			
		  Assert.assertEquals(actual, true, "LogOut and Close the browser ");
    }

}
	
	
	
	

	

