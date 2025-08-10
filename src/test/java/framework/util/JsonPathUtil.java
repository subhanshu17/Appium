/**
 * 
 */
package framework.util;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

/**
 * 
 */
public class JsonPathUtil {
	public static <T> T extractDataByJpath(String jsonDataString, String jpath) {
		DocumentContext jsonContext = JsonPath.parse(jsonDataString);
		return jsonContext.read(jpath);
	}
	
}
