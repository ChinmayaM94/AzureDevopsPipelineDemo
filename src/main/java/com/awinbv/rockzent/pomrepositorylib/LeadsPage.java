package com.awinbv.rockzent.pomrepositorylib;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.awinbv.rockzent.generalUtility.WebDriverUtility;

public class LeadsPage extends WebDriverUtility {
	WebDriver driver;
	
	public LeadsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//img[@title='Create Lead...']")
	private WebElement createLeadImg;
	
	@FindBy(xpath = "//a[@class='hdrLink']")
	private WebElement leadsHeader;
	
	@FindBy(xpath = "//span[@vtfieldname='lead_no']/ancestor::tr[@class='lvtColData']")
	private WebElement lastName;
	
	public WebElement getLastName() {
		return lastName;
	}

	@FindBy(xpath = "//span[@vtfieldname='lead_no']/..")
	private List<WebElement> UniqueleadNoList;

	@FindBy(xpath = "//span[@vtfieldname='lastname']/preceding-sibling::a")
	private List<WebElement> lastNameList;
	
	@FindBy(xpath = "//img[@src='themes/images/next.gif']")
	private WebElement nextBtn;
	
	@FindBy(name = "pagenum")
	private WebElement pageNumEdt;
	
	@FindBy(xpath = "//table[@class='lvt small']")
	private WebElement leadsTable;
	
	public WebElement getLeadsTable() {
		return leadsTable;
	}

	public WebElement getPageNumEdt() {
		return pageNumEdt;
	}
	
	public List<WebElement> getUniqueleadNoList() {
		return UniqueleadNoList;
	}

	public WebElement getNextBtn() {
		return nextBtn;
	}

	public List<WebElement> getLastNameList() {
		return lastNameList;
	}

	public WebElement getCreateLeadImg() {
		return createLeadImg;
	}
	
	public WebElement getLeadsHeader() {
		return leadsHeader;
	}
	
	
	public void selectALead(String updatedLastName) {
		int count=1;
		outerloop:
		for(;;) {
			if(Integer.parseInt(pageNumEdt.getAttribute("value"))==count) {
				try {
					turnOffImplicitWait(driver);
					WebElement leadNameElement = leadsTable.findElement(By.xpath("//a[text()='"+updatedLastName+"']"));
					if(leadNameElement.isDisplayed()) {
						leadNameElement.click();
						waitUntilPageLoad(driver);
						break outerloop;
					}
				} catch (Exception e) {
					count++;
					nextBtn.click();
					waitForAttributeValue(driver, pageNumEdt, "value", String.valueOf(count));
				}
			} else {
				waitUntilPageLoad(driver);
				break;
			}
		}
	}
	
}
