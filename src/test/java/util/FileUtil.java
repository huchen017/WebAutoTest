package util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import CommonBase.Setup;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.Document;


public class FileUtil {
    private static FileUtil instance = null;
    public static Document testDocument = null;
    private Properties properties = new Properties();
    private FileInputStream inputStream = null;
    private static LoggerUtil logger = new LoggerUtil(FileUtil.class);

    public static FileUtil getInstance(){
        if(instance==null){
            synchronized(Setup.class){
                if(instance==null){
                    instance=new FileUtil();
                }
            }
        }

        return instance;
    }

    public void initTestDocument(Class<?> classObject){
        String str[] = classObject.getName().split("\\.");
//      String className = StringUtil.changeFirstCharToLower(str[str.length-1]);
        String className = str[str.length-1];
        String pathOfXml = "";
        try{
            pathOfXml = System.getProperty("user.dir").replace("\\","/") + "/src/test/java/testdata/" + className + ".xml";
            File file = new File(pathOfXml);
            if(file.exists()){
                SAXReader saxReader = new SAXReader();
                testDocument = saxReader.read(file);
            }
        }catch(Exception e){
            logger.error(e);
        }
    }

    public HashMap<String ,HashMap<String,String>> getTestData(Class<?> classObject){
        HashMap<String,HashMap<String,String>> testMap = new HashMap<String, HashMap<String,String>>();
        initTestDocument(classObject);
        if(!(testDocument==null)){
            Document document = testDocument.getDocument();
            Element root = document.getRootElement();
            for(Iterator<?> i=root.elementIterator();i.hasNext();){
                Element head = (Element)i.next();
                String name = head.getName();
                HashMap<String, String> dataMap = new HashMap<String,String>();
                for(Iterator<?> j=head.elementIterator();j.hasNext();){
                    Element element = (Element)j.next();
                    dataMap.put(StringUtil.replaceSpecialCharsInXml(element.getName()),StringUtil.replaceSpecialCharsInXml(element.getText()));
                }
                testMap.put(name,dataMap);
            }
        }
        return testMap;
    }

    public void loadProperties(){
        try{
            File file = new File(System.getProperty("user.dir").replace("\\","/") + "/config.properties");
            inputStream = new FileInputStream(file);
            properties.load(inputStream);
            inputStream.close();
        }catch(FileNotFoundException e){
            logger.error(e);
        }catch(IOException e){
            logger.error(e);
        }
    }

    public String getValueFromProperties(String key){
        String valueString = "";
        try{
            if(properties.containsKey(key)){
                valueString = properties.getProperty(key);
            }
        }catch(Exception e){
            logger.error(e);
        }
        return valueString;
    }

    public void clearProperties(){
        properties.clear();
    }

    public HashMap<String,String> initialPropertiesToMap(){
        HashMap<String,String> propertiesMap = new HashMap<String,String>();
        loadProperties();
        for(Iterator<?> i=properties.keySet().iterator();i.hasNext();){
            String key = i.next().toString();
            propertiesMap.put(key,getValueFromProperties(key));
        }
        return propertiesMap;
    }

}
