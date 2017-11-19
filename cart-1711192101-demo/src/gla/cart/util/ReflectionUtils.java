/*
 * ReflectionUtils.java 17/10/25
 */

package gla.cart.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


//do not delete this class! 17/10/25
/**
 * 反射工具类。
 * 
 * @author Gladiateur
 */
public class ReflectionUtils {
	/**
	 * 通过类的Class对象和方法名，利用反射执行对象中的方法。
	 * 其中方法名参数通过读取配置文件获取。故应该先在配置文件配置获取id
	 * 方法的方法名和获取单价的方法的方法名。
	 */
	public static <T>Object execute(T t,String methodName){
		Object obj = null ;
		Class<?> cls =  t.getClass();
		try {
			Method method = cls.getMethod(methodName);
			obj = method.invoke(t);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return  obj;
	}
	 
//	 public static void main(String[] args) {
//		Product product  = new Product();
//		product.setId(1);
//		product.setPrice(250d);
//		
//		Integer id = (Integer) execute(product, "getId");
//		System.out.println(id);
//		
//		Double price = (Double) execute(product, "getPrice");
//		System.out.println(price);
//	}
}
