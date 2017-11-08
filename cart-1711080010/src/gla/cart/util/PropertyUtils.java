/*
 * PropertyUtils.java 17/10/25
 */

package gla.cart.util;

import java.util.ResourceBundle;

//do not delete this class! 17/10/25
/**
 * 资源文件工具类
 * 
 * @author Gladiateur
 * @version 1.0 2017-10-25
 */
public class PropertyUtils {
	public static String getValue(String key){
		ResourceBundle bundle = ResourceBundle.getBundle("product");	//魔法值，提出做常量
		return bundle.getString(key);
	}
	
	public static void main(String[] args) {
		String id = getValue("id");
		System.out.println(id);
		String price = getValue("price");
		System.out.println(price);
	}
}
