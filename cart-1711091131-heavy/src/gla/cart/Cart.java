/*
 * CartMapping.java 17/10/9
 */

package gla.cart;

import gla.cart.util.PropertyUtils;
import gla.cart.util.ReflectionUtils;
import gla.cart.util.SerializationUtils;
import static gla.cart.util.CommonUtils.printer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

//do not delete this class! 17/10/15
/**
 * Cart必须使用单例。为了防止恶意new出它的对象，因为一个用户只能 有一个购物车。
 * 设计：Map<Integer,CartItem>，其中K是商品id,V是购物项。 购物项的设计：CartItem{Product,
 * product;Integer count;Double total} 其中total=count*product.getPrice()。
 * -17/10/21
 * 将商品实体一般化。
 * -17/10/25
 * 
 * @author Gladiateur
 * @version	17/10/30
 */
public class Cart implements Serializable{
	private static final long serialVersionUID = 1L;
	private static final boolean PRINTER = true ;	//其实是从配置文件获取
	static String getId;
	static String getPrice;
	
	/*	这里获取到的是资源文件中对应键的键值，反射时需用到的方法名	*/
	static{
		getId = PropertyUtils.getValue("id");
		getPrice = PropertyUtils.getValue("price");
	}
	
	private static Cart cart = null;
	private Cart() {
	}
	private final Double INIT_DOUBLE = 0d;
	private final int INIT_INT = 0 ;
	
	
	private Double sumPrice = INIT_DOUBLE; // 商品价格总和
	private int sumCount = INIT_INT ; // 商品总件数

	private String userid;
	
	public static Cart getNewCart(String userId){
		cart = new Cart();
		cart.userid = userId;
		//System.out.println("cur_user_id:"+userId);
		printer("cur_user_id", PRINTER);
		return cart;
	}
	// singleton pattern
	public static Cart getCart(String userId) {
		if (cart == null) {
			//cart = new Cart();
			cart = SerializationUtils.readCart(userId);
			if(cart == null){
				cart = new Cart();
				SerializationUtils.saveCart(userId, cart);
			}
		}
		cart.userid = userId;
		return cart;
	}

	public Double getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}

	public int getSumCount() {
		return sumCount;
	}

	public void setSumCount(int sumCount) {
		this.sumCount = sumCount;
	}

	private Map<Integer, CartItem<?>> cartMapping = new HashMap<Integer, CartItem<?>>();

	public Map<Integer, CartItem<?>> getCartMapping() {
		return cartMapping;
	}

	public void setCartMapping(Map<Integer, CartItem<?>> cartMapping) {
		this.cartMapping = cartMapping;
	}

	/**
	 * 只需要Product的两个属性，id(商品ID),price(商品单价)
	 * 需要通过反射来调用getId(从该类的角度出发方法名是不确定的，后同)和
	 * getPrice方法。
	 * @param product
	 */
	public <T>void addElem(T t) {		// public <T>void addElem(T t)  //传谁就是谁
		// cautious NullPointEx
		Integer id = (Integer) ReflectionUtils.execute(t, getId);
		Double price = (Double) ReflectionUtils.execute(t, getPrice);
		//Integer id =product.getId();
		CartItem<T> cartItem = (CartItem<T>) cart.getCartMapping().get(id);// cartItem这个对象在这里不能new否则count数会计算错误
		if (cartItem == null) {
			cartItem = new CartItem<T>();
		}
		//cartItem.setProduct(t);	//	cartItem.setT(t);
		cartItem.setT(t);
		
		int count = cartItem.getCount();
		if (cartMapping.containsKey(id)) {

			cartItem.setCount(++count);
			//cartMapping.put(id, cartItem);
		} 
		cartMapping.put(id, cartItem);
		
		cartItem.setTotal((cartItem.getCount())
				* price);
		
		//*  必须有这两个临时的变量否则计算出错                            ///
		Double initSumPrice = INIT_DOUBLE;
		int initSumCount = INIT_INT;
		for (Integer elem : cartMapping.keySet()) {
			CartItem<T> items =(CartItem<T>) cartMapping.get(elem);
			Double itemsTotal = items.getTotal();
			int itemsCount = items.getCount();
			initSumPrice += itemsTotal;
			initSumCount += itemsCount;

		}

		cart.setSumCount(initSumCount);
		cart.setSumPrice(initSumPrice);
	}

	public void clearCart() {
		cartMapping.clear();
		sumPrice = INIT_DOUBLE;
		sumCount = INIT_INT ;
		SerializationUtils.saveCart(userid, cart);
		//System.out.println("已清空购物车！");
		printer("已清空购物车！", PRINTER);
	}
	
	//showCart
		@SuppressWarnings("unchecked")
		public <T>void showCart(){
			for (Entry<Integer, CartItem<?>> element : cartMapping.entrySet()) {
				Integer id = element.getKey();
				CartItem<T> cartItem = (CartItem<T>) element.getValue();
				T t = cartItem.getT();
				Integer count = cartItem.getCount();
				Double total = cartItem.getTotal();
				System.out.println(id+"\t"+t+"\t"+count+"\t"+total+"\n");
			}
			System.out.println(sumCount+"\t"+sumPrice); 
		}
	
	//removeElem
	
	
	
}
