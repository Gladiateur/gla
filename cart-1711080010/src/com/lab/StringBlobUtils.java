/*
 * CartMapping.java 17/11/4
 */

package com.lab;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//do not delete this class! 17/11/4
//该类目前实验用，之后和SerializationUtils合并成一个类。
public class StringBlobUtils {
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

	public static void main(String[] args) {
		
		//System.out.println(isExist("7"));
		StringBlobUtils blobUtils = new StringBlobUtils();
//		blobUtils.setCartToDB("1", "sd大发发");
		// String str = blobUtils.getCartFromDB(103);
		 String str = blobUtils.blobToString("10");
		 String str2 = blobUtils.getCartFromDB("1");
		 System.out.println("str="+str);
		 System.out.println("str2="+str2);
	}
	
	//userId是主键时:dbname.table
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

	public void setCartToDB(String userid, String cart) {
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

	/////***************************************////////////
	public String blobToString(String userid) {
		PreparedStatement pres = null;
		String sql = "select cart from cart2 where userid = ?";
		String cart = null;
		try {
			pres = conn.prepareStatement(sql);
			pres.setString(1, userid);
			ResultSet res = pres.executeQuery();
			while (res.next()) {
				Blob inBlob = res.getBlob("cart"); // 获取blob对象

				InputStream is = inBlob.getBinaryStream(); // 获取二进制流对象
				cart = inputstreamToString(is);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return cart;
	}

	public static String inputstreamToString(InputStream in) throws IOException {

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[4096];
		int count = -1;
		while ((count = in.read(data, 0, 4096)) != -1)
			outStream.write(data, 0, count);

		data = null;
		String result = new String(outStream.toByteArray(), "utf-8");
		return result;

	}

	// ///////////////////////////////////////////////////
	/*
	 * 
	 */
	public String getCartFromDB(String userid) {
		String sql = "select cart from cart2 where userid=?";
		PreparedStatement pres = null;
		String cart = null;
		try {
			pres = conn.prepareStatement(sql);
			pres.setString(1, userid);
			ResultSet res = pres.executeQuery();
			while (res.next()) {
				Blob inBlob = res.getBlob("cart"); // 获取blob对象
				InputStream is = inBlob.getBinaryStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				cart = (String) ois.readObject();
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
			conn.close();
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
