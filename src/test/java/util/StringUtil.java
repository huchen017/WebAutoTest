package util;

public class StringUtil {

    public static String changeFirstCharToLower(String str){
        String firstChar = String.valueOf(str.charAt(0));
        str = str.replaceFirst(firstChar, firstChar.toLowerCase());
        return str;
    }

    public static String replaceSpecialCharsInXml(String str){
        str = str.replace("&gt",">");
        str = str.replace("&lt","<");
        str = str.replace("&amp","&");
        str = str.replace("&quot","\"");
        str = str.replace("&apos","\'");
        return str;
    }
}
