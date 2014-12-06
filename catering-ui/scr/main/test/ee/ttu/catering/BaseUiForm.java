package ee.ttu.catering;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
public abstract class BaseUiForm {
	
	private String baseUrl = "http://localhost:8080/";
	
	public void openForm(String url){
		open(baseUrl + url);
	}
	public LinkedList<String> extractDigits(String src) {
		LinkedList<String> numbers = new LinkedList<String>();

		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(src); 
		while (m.find()) {
		   numbers.add(m.group());
		}
	    return numbers;
	}
	
	public String getModifiedDate(){
		return $(By.id("modifyDate")).getText();
	}
	
	public String getAddDate(){
		return $(By.id("created")).getText();
	}
	
	public String successMessage(){
		return $(By.xpath("//div[contains(@class,'alert alert-success')]")).getText();
	}
	
	public String getCurrentUrl(){
		return getWebDriver().getCurrentUrl();
	}

}
