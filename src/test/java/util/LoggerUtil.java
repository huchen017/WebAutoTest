package util;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerUtil {
    private Logger logger;

    public LoggerUtil(Class<?> className){
        PropertyConfigurator.configure(LoggerUtil.class.getClassLoader().getResource("log4j.properties").getPath());
        logger = Logger.getLogger(className);
    }

    public void info(String message){
        logger.info(message);
    }

    public void info(String testCaseId, String testCaseName){
        logger.info(testCaseId +":"+testCaseName);
    }

    public void warn(String message){
        logger.warn(message);
    }

    public void error(String testCaseId, Exception e){
        logger.error(testCaseId);
        error(e);
    }

    public void error(Exception e){
        String errorMessage = e.toString();

        for(int i=0; i<e.getStackTrace().length; i++){
            errorMessage = errorMessage + "\n" + " at " + e.getStackTrace()[i];
        }
        logger.error(errorMessage);
    }

    public void error(String message){
        logger.error(message);
    }

}

