/*
 * Product.java 17/10/9
 */

package com.bean;

import java.io.Serializable;

//do not delete this class! 17/10/14
/**
 * 这个商品实体尽可能一般化，最终删除该类并由Object或泛型代替。
 * 需要注意的是：
 * 实体中的属性的值不一定全部显示在页面上。一般是显示一部分信息，
 * 比如id一般不显示(但是需要遍历出来)。即从源实体获取到的信息部分
 * 显示在页面上，之后再利用beanUtils封装成的实体已经不是原来的实体
 * 而是其子类。
 * 子实验：将页面上的数据封装成实体利用beanUtils
 * -17/10/21
 * 将该类一般化:也就是将一个实体一般化必要用到反射。其实这里的反射不
 * 需要做太多工作。对于Product对象的大部分操作是传实体。当调用该对象
 * 的方法时，才需使用反射。这里只需使用反射去执行Product对象中的两个
 * 方法:1.获取商品id,	2.获取商品单价。
 * 
 * @author Gladiateur
 * @version 17/10/25
 */
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;				//商品ID，遍历，不显示
	private String name;		//商品名，遍历，显示
	private Double price;		//商品单价，遍历，显示
	/* ***************	以上是必要属性	*********** */
	
	/* ***************	以下并不是必要的属性	*********** */
	private String introduce;	//商品介绍，不遍历
	private String picPath;		//图片目录，不遍历
	private int del;			//状态码，不遍历
	private int state;			//状态码，不遍历
	
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public int getDel() {
		return del;
	}
	public void setDel(int del) {
		this.del = del;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	
}
