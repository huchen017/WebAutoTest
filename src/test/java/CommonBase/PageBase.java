package CommonBase;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageBase {
    public int timeout = 20;

    public WebElement findElementById(WebDriver driver, String elementId){
        WebElement webElement = null;
        try{
            webElement = (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<WebElement>() {
                public WebElement apply(WebDriver d){
                    return d.findElement(By.id(elementId));
                }

            });
        }catch (Exception e){

        }
        return webElement;
    }

    public WebElement findElementByXpath(WebDriver driver, String elementXpath){
        WebElement webElement = null;
        webElement = (new WebDriverWait(driver,timeout)).until(new ExpectedCondition<WebElement>(){
            public WebElement apply(WebDriver d){
                return d.findElement(By.xpath(elementXpath));
            }

        });
        return webElement;
    }

//    public void switchToWindowByTitle(WebDriver driver, String windowTitle){
//        long currentTime = System.currentTimeMillis();
//        long endTime = currentTime+timeout;
//
//        while(currentTime<=endTime){
//            set<String> handles = driver.getWindowHandles();
//            for(String s: handles){
//                driver.switchTo().window(s);
//                if(driver.getTitle().contains(windowTitle)){
//                    driver.switchTo().window(s);
//                    break;
//                }
//            }
//        }
//    }

}
