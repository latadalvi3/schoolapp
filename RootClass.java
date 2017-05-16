package implant.lata.com.dumetschool;//
//	RootClass.java
//
//	Create by MP-03 on 7/4/2017
//	Copyright © 2017. All rights reserved.
//	Model file generated using JSONExport: https://github.com/Ahmed-Ali/JSONExport

import org.json.*;
import java.util.*;


public class RootClass{

	private Link links;
	private int count;
	private Item[] items;
	private int total;

	public void setLinks(Link links){
		this.links = links;
	}
	public Link getLinks(){
		return this.links;
	}
	public void setCount(int count){
		this.count = count;
	}
	public int getCount(){
		return this.count;
	}
	public void setItems(Item[] items){
		this.items = items;
	}
	public Item[] getItems(){
		return this.items;
	}
	public void setTotal(int total){
		this.total = total;
	}
	public int getTotal(){
		return this.total;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public RootClass(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		links = new Link(jsonObject.optJSONObject("_links"));
		count = jsonObject.optInt("count");
		JSONArray itemsJsonArray = jsonObject.optJSONArray("items");
		if(itemsJsonArray != null){
			ArrayList<Item> itemsArrayList = new ArrayList<>();
			for (int i = 0; i < itemsJsonArray.length(); i++) {
				JSONObject itemsObject = itemsJsonArray.optJSONObject(i);
				itemsArrayList.add(new Item(itemsObject));
			}
			items = (Item[]) itemsArrayList.toArray();
		}		total = jsonObject.optInt("total");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("_links", links.toJsonObject());
			jsonObject.put("count", count);
			if(items != null && items.length > 0){
				JSONArray itemsJsonArray = new JSONArray();
				for(Item itemsElement : items){
					itemsJsonArray.put(itemsElement.toJsonObject());
				}
				jsonObject.put("items", itemsJsonArray);
			}
			jsonObject.put("total", total);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}