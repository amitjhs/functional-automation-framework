package com.automationframework.functionaltest;

import org.apache.log4j.Logger;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
* General Utility functions
* 
* @author AmitAgarwal
*
*/
public class StringHelper {

	private static final Logger LOGGER = Logger.getLogger(StringHelper.class);

	/***
	 * Return text appended with unique String
	 * 
	 * @param text
	 * @return
	 */
	public static String getUniqueName(String text) {
		return text + UUID.randomUUID();
	}

	/***
	 * Return Substring text appended with unique String
	 * 
	 * @param text
	 * @param charCount
	 *            :Count of the Characters starting with 0
	 * @return
	 */
	public static String getUniqueName(String text, int charCount) {
		return (text + UUID.randomUUID()).substring(0, charCount);
	}

	/***
	 * Get List of Integer from the String
	 * 
	 * @param text
	 * @return
	 */
	public static List<String> getIntegerListFromString(String text) {
		List<String> integerList = new ArrayList<String>();
		Matcher matcher = Pattern.compile("\\d+").matcher(text);

		while (matcher.find()) {
			integerList.add(matcher.group());
		}
		return integerList;
	}
	
	public static String getFilePathForUpload(String fileName,Object className){
		LOGGER.debug("File name recieved is:"+fileName);
		return System.getProperty("user.dir")+File.separator+fileName;
	}

	public static String getFullMethodName(Method m) {
		return m.getDeclaringClass().getName() + "." + m.getName();
	}
}
