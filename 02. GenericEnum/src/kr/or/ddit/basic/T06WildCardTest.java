package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T06WildCardTest {
	
	// 장바구니에 담긴 항목조회를 위한 메소드
	public static void displayCartItemInfo(Cart<?> cart) {
		System.out.println("= 장바구니 항목 리스트 =  ");
		for(Object obj : cart.getItemList()) {
			System.out.println(obj);
		}
		System.out.println("──────────────────────────");
	}
	
	
	// 장바구니에 담긴 항목조회를 위한 메소드2
		public static void displayCartItemInfo2(Cart<? extends Drink> cart) {
			System.out.println("= 장바구니 항목 리스트 =  ");
			for(Object obj : cart.getItemList()) {
				System.out.println(obj);
			}
			System.out.println("──────────────────────────");
		}
	
	// 장바구니에 담긴 항목조회를 위한 메소드3
		public static void displayCartItemInfo3(Cart<? super Drink> cart) {
			System.out.println("= 장바구니 항목 리스트 =  ");
			for(Object obj : cart.getItemList()) {
				System.out.println(obj);
			}
			System.out.println("──────────────────────────");
		}
		
		
		
	public static void main(String[] args) {
		
		Cart<Food> foodCart = new Cart<>();
		foodCart.addItem(new Meat("돼지고기", 5000));
		foodCart.addItem(new Meat("소고기", 50000));
		foodCart.addItem(new Juice("망고주스", 3000));
		foodCart.addItem(new Coffee("아메리카노", 4500));
		
		Cart<Meat> meatCart = new Cart<>();
		meatCart.addItem(new Meat("돼지고기", 5000));
		meatCart.addItem(new Meat("소고기", 50000));
//		meatCart.addItem(new Juice("망고주스", 3000));
//		meatCart.addItem(new Coffee("아메리카노", 4500));
		
		Cart<Drink> drinkCart = new Cart<>();
//		drinkCart.addItem(new Meat("돼지고기", 5000));
//		drinkCart.addItem(new Meat("소고기", 50000));
		drinkCart.addItem(new Juice("망고주스", 3000));
		drinkCart.addItem(new Coffee("아메리카노", 4500));
		
		///////////////////////////////////////////////////
		
		displayCartItemInfo(foodCart);
		displayCartItemInfo(meatCart);
		displayCartItemInfo(drinkCart);
		
//		displayCartItemInfo2(foodCart);
//		displayCartItemInfo2(meatCart);
		displayCartItemInfo2(drinkCart);
		
		displayCartItemInfo3(foodCart);
//		displayCartItemInfo3(meatCart);
		displayCartItemInfo3(drinkCart);
		
	}
}


class Food {
	private String name;
	private int price;
	
	public Food(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	@Override
	public String toString() {
		return this.name + "(" + this.price + "원)";
	}
}

class Meat extends Food {
	
	public Meat(String name, int price) {
		super(name, price);
	}
}
class Drink extends Food{

	public Drink(String name, int price) {
		super(name, price);
	}
	
}
class Juice extends Drink {
	public Juice(String name, int price) {
		super(name, price);
	}
}

class Coffee extends Drink {
	public Coffee(String name, int price) {
		super(name, price);
	}
}

class Cart<T> {
	
	private List<T> itemList;
	
	public Cart() {
		itemList = new ArrayList<>();
	}

	public List<T> getItemList() {
		return itemList;
	}

	public void setItemList(List<T> itemList) {
		this.itemList = itemList;
	}
	
	public void addItem(T item) {
		itemList.add(item);
	}
	
	
}