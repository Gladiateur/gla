/*
 * ReflectionUtils.java 17/10/25
 */

package gla.cart.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.bean.Product;

//do not delete this class! 17/10/25
public class ReflectionUtils {
	/**
	 * 通过类的Class对象和方法名，利用反射执行对象中的方法。
	 * 其中方法名参数通过读取配置文件获取。故应该先在配置文件配置获取id
	 * 方法的方法名和获取单价的方法的方法名。
	 */
	 @SuppressWarnings("unchecked")
	public static <T>Object execute(T t,String methodName){
		Object obj = null ;
		Class<T> cls =  (Class<T>) t.getClass();
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
//		product.setId(55);
//		product.setPrice(6.6);
//		
//		Integer id = (Integer) execute(product, "getId");
//		System.out.println(id);
//		
//		Double price = (Double) execute(product, "getPrice");
//		System.out.println(price);
//	}
}
