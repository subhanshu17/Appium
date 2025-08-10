/**
 * 
 */
package framework.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

/**
 * 
 */
public class YamlUtil {
	/**
	 * this method reads yaml file content into a map and return it
	 * 
	 * @param filePath
	 * @return yamlFileMap
	 */
	public static Map<String, Object> readYAMLIntoMap(String filePath) {
		Yaml yaml = new Yaml();
		Map<String, Object> yamlFileMap = null;
		try {
			yamlFileMap = yaml.loadAs(new FileInputStream(filePath), Map.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return yamlFileMap;
	}

	@SuppressWarnings("unchecked")
	public static <T> T mapYamlToClass(String filePath, Class<?> className) {
		Yaml yaml = new Yaml();
		Object object = null;
		try {
			object = yaml.loadAs(new FileInputStream(filePath), className);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return (T) object;
	}
}
