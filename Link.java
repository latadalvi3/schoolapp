package implant.lata.com.dumetschool;//
//	Link.java
//
//	Create by MP-03 on 7/4/2017
//	Copyright © 2017. All rights reserved.
//	Model file generated using JSONExport: https://github.com/Ahmed-Ali/JSONExport

import org.json.JSONException;
import org.json.JSONObject;


public class Link{

	private String first;
	private String last;
	private String next;
	private String self;

	public void setFirst(String first){
		this.first = first;
	}
	public String getFirst(){
		return this.first;
	}
	public void setLast(String last){
		this.last = last;
	}
	public String getLast(){
		return this.last;
	}
	public void setNext(String next){
		this.next = next;
	}
	public String getNext(){
		return this.next;
	}
	public void setSelf(String self){
		this.self = self;
	}
	public String getSelf(){
		return this.self;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Link(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		first = (String) jsonObject.opt("first");
		last = (String) jsonObject.opt("last");
		next = (String) jsonObject.opt("next");
		self = (String) jsonObject.opt("self");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("first", first);
			jsonObject.put("last", last);
			jsonObject.put("next", next);
			jsonObject.put("self", self);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}