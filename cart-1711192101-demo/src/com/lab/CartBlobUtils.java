/*
 * CartMapping.java 17/11/4
 */

package com.lab;


import gla.cart.Cart;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
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
public class CartBlobUtils {
	public static void main(String[] args) {
		CartBlobUtils blobUtils = new CartBlobUtils();
		//blobUtils.setCartToDB(100,"dsfsadssafahah");
		//String str = blobUtils.getCartFromDB(100);
		String str = blobUtils.f(100);
		System.out.println(str);
	}
	
	
	public void setCartToDB(int userid , String cart){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aaaa", "root", "123456");
			String sql= "insert into cart(cart,userid) values(?,?)";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setObject(1, cart);
			psmt.setInt(2, userid);
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 从数据库中读出存入的对象
	 * return:
	 * 	list:Person对象列表
	 */
	public String getCartFromDB(int userid){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aaaa", "root", "123456");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String sql="select cart from cart where userid=?";
		PreparedStatement pres = null;
		String cart = null;
		try {
			pres=conn.prepareStatement(sql);
			pres.setInt(1, userid);
			ResultSet res=pres.executeQuery();
			
				//Blob inBlob=res.getBlob("cart"); //获取blob对象
				byte[] data = res.getBytes("cart");
				ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
				ObjectInputStream objectInputStream=new ObjectInputStream(byteArrayInputStream);
				cart = (String)objectInputStream.readObject();
				//InputStream is=inBlob.getBinaryStream();                //获取二进制流对象
				//BufferedInputStream bis=new BufferedInputStream(is);    //带缓冲区的流对象
				
//				byte[] buff=new byte[(int) inBlob.length()];
//				System.out.println(buff.length);
//				while(-1!=(bis.read(buff, 0, buff.length))){            //一次性全部读到buff中
//					ObjectInputStream in=new ObjectInputStream(new ByteArrayInputStream(buff));
//					cart=(String)in.readObject();                   //读出对象
//				}
			byteArrayInputStream.close();
			objectInputStream.close();
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
	
	
	public String f(int userid){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		Connection conn = null;
		PreparedStatement pres = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aaaa", "root", "123456");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String sql="select cart from cart where userid = ?";
		String cart =  null;
		try {
			pres=conn.prepareStatement(sql);
			pres.setInt(1, userid);
			ResultSet res=pres.executeQuery();
			while(res.next()){
				Blob inBlob=res.getBlob("cart");                             //获取blob对象
				
				InputStream is=inBlob.getBinaryStream();                //获取二进制流对象
				BufferedInputStream bis=new BufferedInputStream(is);    //带缓冲区的流对象
				
				byte[] buff=new byte[(int) inBlob.length()];
				while(-1!=(bis.read(buff, 0, buff.length))){            //一次性全部读到buff中
					ObjectInputStream in=new ObjectInputStream(new ByteArrayInputStream(buff));
					cart=(String)in.readObject();                   //读出对象
				}
			}
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
