package CommonBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import util.FileUtil;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class TestCaseBase {
    public WebDriver driver;
    public int timeout;
    public FileUtil fileUtil = FileUtil.getInstance();

    public void getWebSiteUrl(String browser, String url){
        driver = getDriver(browser);
        driver.get(url);
    }

    protected WebDriver getDriver(String browser){
        try{
            if(browser.equals("internet explorer")){
                String ieDriver = new File(new File(".").getCanonicalPath()+ "\\"+"driver/IEDriverServer.exe").getCanonicalPath();
                System.setProperty("webdriver.ie.driver",ieDriver);
                driver = new InternetExplorerDriver();
            } else if(browser.equals("chrome")){
                String chromeDriver = new File(new File(".").getCanonicalPath() + "\\"+"driver/chromedriver.exe").getCanonicalPath();
                System.setProperty("webdriver.chrome.driver", chromeDriver);
                driver = new ChromeDriver();
            }else if(browser.equals("firefox")){
                String firefoxDriver = new File(new File(".").getCanonicalPath() +"\\" + "driver/geckodriver.exe").getCanonicalPath();
                System.setProperty("webdriver.firefox.driver",firefoxDriver);
                driver = new FirefoxDriver();
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);

        }catch(Exception e){

        }
        return driver;
    }

    public void clearDriver(){
        driver.quit();
    }
}
