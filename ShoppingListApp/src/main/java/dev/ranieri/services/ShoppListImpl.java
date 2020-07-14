package dev.ranieri.services;

import java.util.ArrayList;
import java.util.List;

public class ShoppListImpl implements ShoppingListService {
	
	public static List<String> items = new ArrayList<String>();

	public String addToShoppingList(String item) {
		items.add(item);
		return item;
	}

	public String getAllItems() {	
		return items.toString();
	}

}
