/*
 * CommonUtils.java 17/11/10
 */

package gla.cart.util;

//do not delete this class! 17/11/10
public class CommonUtils {
	//通过设置参数，决定开启开发时的提示或关闭开发时的提示。
	public static void printer(String info , boolean flag){
		if(flag){
			System.out.println(info);
		}
	}
	
//	public static void main(String[] args) {
//		printer("这个消息不显示", false);
//		printer("这个消息显示", true);
//	}
}
