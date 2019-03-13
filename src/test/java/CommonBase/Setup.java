package CommonBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.FileUtil;

import java.util.HashMap;

public class Setup {
    private static final String CONFIG_BROWSER = "browser";
    private static final String CONFIG_URL = "url";
    private static final String CONFIG_WAY = "runCaseWay";
    private static final String CONFIG_TIME_OUT = "timeout";
    private static final String CONFIG_NEXT_PAGE_TIME= "nextPageTime";

    private static Setup instance = null;
    private static FileUtil fileUtil = FileUtil.getInstance();
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static String browser;
    private static String url;
    private static String way;
    private static String timeout;
    private static String nextPageTime;

    protected static HashMap<String,String> configPropertiesMap = fileUtil.initialPropertiesToMap();

    public static Setup getInstance(){
        if(instance == null){
            synchronized(Setup.class){
                if(instance==null){
                    instance=new Setup();
                }
            }
        }
        return instance;
    }

    protected Setup(){
        browser = configPropertiesMap.get(CONFIG_BROWSER);
        url = configPropertiesMap.get(CONFIG_URL);
        way = configPropertiesMap.get(CONFIG_WAY);
        timeout = configPropertiesMap.get(CONFIG_TIME_OUT);
        nextPageTime = configPropertiesMap.get(CONFIG_NEXT_PAGE_TIME);
    }

    public static FileUtil getFileUtil(){
        return fileUtil;
    }

    public static WebDriver getDriver(){
        return driver;
    }

    public static WebDriverWait getWait(){
        return wait;
    }
}
