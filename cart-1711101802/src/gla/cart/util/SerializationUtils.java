/*
 * SerializationUtils.java 17/10/26
 */

package gla.cart.util;

import gla.cart.Cart;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import static gla.cart.util.CommonUtils.printer;


//do not delete this class! 17/10/26
/**
 * 序列化与反序列化工具类
 * 
 * @author Jim Calark
 * @author Gladiateur
 * @version 17/11/4
 */
public class SerializationUtils {
	private static String FILE_BASE_PATH = "C:/";	//路径应可配置
	private static final boolean PRINTER = true ;	//其实是从配置文件获取
	// 对象序列化
	// 这里是购物车实体实现可序列化接口
	public static void saveCart(String userId,Serializable serializable) {
		if(userId == null){
			throw new NullPointerException();
		}
		if(userId.length() == 0){
			throw new RuntimeException("userid is empty");
		}
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					new FileOutputStream(FILE_BASE_PATH+userId+".cart"));
			objectOutputStream.writeObject(serializable);
			objectOutputStream.close();
			//System.out.println("序列化成功！");
			//System.out.println("你向服务器上保存了一个Cart实例");
			printer("你向服务器上保存了一个Cart实例", PRINTER);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 反序列化
	//定义一个方法：传入路径和文件全名
	//若该文件在指定目录存在，则反序列化该对象
	public static Cart readCart(String userId) {
		Cart cart = null;
		try {
			ObjectInput input = new ObjectInputStream(new FileInputStream(
					FILE_BASE_PATH+userId+".cart"));
			
			cart = (Cart)input.readObject();
			input.close();
		} catch (FileNotFoundException e) {
			cart = Cart.getNewCart(userId);
			saveCart(userId, cart);
			//System.out.println("首次创建Cart实例！");
			printer("首次创建Cart实例！", PRINTER);
			//e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//System.out.println("反序列化成功！");
		printer("你从服务器上获取了一个Cart实例",PRINTER);
		return cart;
	}
}
