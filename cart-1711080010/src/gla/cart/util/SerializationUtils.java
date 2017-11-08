/*
 * SerializationUtils.java 17/10/26
 */

package gla.cart.util;

import gla.cart.Cart;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			System.out.println("你向服务器上保存了一个Cart实例");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
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
			System.out.println("首次创建Cart实例！");
			//e.printStackTrace();
		} catch(EOFException e){
			System.out.println("r:用户["+userId+"]购物车文件损坏！已重新创建了空的购物车文件，历史数据丢失！");
		} catch(StreamCorruptedException e){
			System.out.println("r:用户["+userId+"]购物车文件校验失败！已重新创建了空的购物车文件，历史数据丢失！");
		}
		catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//System.out.println("反序列化成功！");
		System.out.println("你从服务器上获取了一个Cart实例");
		return cart;
	}
	
	
	//////////////////////////
	//userId是主键时:dbname.table
	static Connection conn = null;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/aaaa", "root", "123456");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isExist(String userId) {
		String sql = "select userid from cart2 where userid = ?";
		String id = null;
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			ResultSet res = psmt.executeQuery();
			while(res.next()){
				id = res.getString("userid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id!=null;
	}
	
	public static void setCartToDB(String userid, Cart cart) {
		String sql=null;
		if(isExist(userid)==true){
			sql = "update cart2 set cart= ? where userid=?";
		}else{
			sql = "insert into cart2(cart,userid) values(?,?)";
		}
		//String sql= "insert into cart(cart,userid) values(?,?)";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setObject(1, cart);
			psmt.setString(2, userid);
			System.out.println(sql);
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static Cart getCartFromDB(String userid) {
		String sql = "select cart from cart2 where userid=?";
		PreparedStatement pres = null;
		Cart cart = null;
		try {
			pres = conn.prepareStatement(sql);
			pres.setString(1, userid);
			ResultSet res = pres.executeQuery();
			while (res.next()) {
				Blob inBlob = res.getBlob("cart"); // 获取blob对象
				InputStream is = inBlob.getBinaryStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				cart = (Cart) ois.readObject();
			}
			// byte[] data = res.getBytes("cart");
			// ByteArrayInputStream byteArrayInputStream = new
			// ByteArrayInputStream(data);
			// ObjectInputStream objectInputStream=new
			// ObjectInputStream(byteArrayInputStream);
			// cart = (String)objectInputStream.readObject();
			// InputStream is=inBlob.getBinaryStream(); //获取二进制流对象
			// BufferedInputStream bis=new BufferedInputStream(is); //带缓冲区的流对象

			// byte[] buff=new byte[(int) inBlob.length()];
			// System.out.println(buff.length);
			// while(-1!=(bis.read(buff, 0, buff.length))){ //一次性全部读到buff中
			// ObjectInputStream in=new ObjectInputStream(new
			// ByteArrayInputStream(buff));
			// cart=(String)in.readObject(); //读出对象
			// }
			// byteArrayInputStream.close();
			// objectInputStream.close();
			res.close();
			pres.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return cart;
	}
}
