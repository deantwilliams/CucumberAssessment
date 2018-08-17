package MavenTest.Cucumber;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PassionTeaHome {
	@FindBy(xpath = "//*[@id=\"wsb-nav-00000000-0000-0000-0000-000450914915\"]/ul/li[3]/a") 
	private WebElement menuLink;
	
	public void clickMenuLink()
	{
		menuLink.click();
	}
}
