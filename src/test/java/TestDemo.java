import CommonBase.TestCaseBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;

public class TestDemo extends TestCaseBase {
    private HashMap<String,HashMap<String,String>> classDataMap = fileUtil.getTestData(TestDemo.class);
    private HashMap<String,String> caseDataMap;

    private static final String SEARCH_TEXT = "searchText";

    @Parameters({"browser","url"})
    @BeforeMethod
    public void setup(String browser, String url){

        getWebSiteUrl(browser,url);
    }

    @AfterMethod
    public void teardown(){
        clearDriver();
    }

    @Test
    public void testCase1(){

        caseDataMap = classDataMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());

        driver.findElement(By.id("kw")).sendKeys(caseDataMap.get(SEARCH_TEXT));
        driver.findElement(By.id("su")).click();
        Assert.assertTrue(true);
    }
}
