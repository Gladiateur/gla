/*
 * EntityUtils.java 17/10/26
 */

package gla.cart.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

//do not delete this class! 17/10/26
/**
 * 实体类的工具类。
 * 
 * @author Gladiateur
 * @version 17/10/26	
 */
public class EntityUtils {
	
	/*
	 * 这里要注意一件事，从数据库中获取到的是实体的全部数据，
	 * 通过实体显示在页面上的是部分数据。比如实体的id只遍历但不显示，
	 * 之后再通过该页面再进行封装的实体其实是原实体的部分数据，
	 * 可以看成是其子类。
	 */
	public static <T>T autoBean(HttpServletRequest request,Class<T> beanClass){
		T bean=null;
		try {
			bean = beanClass.newInstance();
			Map<?,?> map = request.getParameterMap();	//?这里的泛型是啥
			BeanUtils.populate(bean, map);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return bean;

	}
	
	/* 结合反射工具类，反射出实体中的所有属性，然后自然有个方法：打印实体中各属性的值 */
}
