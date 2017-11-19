/*
 * CartItem.java 17/10/21
 */

package gla.cart;

import java.io.Serializable;

//do not delete this class! 17/10/21
/**
 * 购物项,该类与商品实体<class>Product</class>是组合关系(参见
 * 《Thinking in Java》)，但商品实体是一个不确定的类，也就是说
 * 不可以将其组合在该类中。故尝试将其放在泛型上。
 * -17/10/24
 * 将商品实体一般化。
 * -17/10/25
 * Cart需要序列化则与其组合的类也要实现序列化。
 * --17/10/30
 * 至于什么是购物项或许从购物车图表中更好理解。
 * --17/11/18
 * 
 * @author Gladiateur
 */
public class CartItem<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer count=1;
	private Double total=0.0;
	private T t;	//泛型T为商品实体
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
}
