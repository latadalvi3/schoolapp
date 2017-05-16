package implant.lata.com.dumetschool;//
//	Productcategory.java
//
//	Create by MP-03 on 8/4/2017
//	Copyright © 2017. All rights reserved.
//	Model file generated using JSONExport: https://github.com/Ahmed-Ali/JSONExport

import org.json.JSONException;
import org.json.JSONObject;


public class Productcategory{

	private String created;
	private int id;
	private String slug;
	private String title;

	public void setCreated(String created){
		this.created = created;
	}
	public String getCreated(){
		return this.created;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setSlug(String slug){
		this.slug = slug;
	}
	public String getSlug(){
		return this.slug;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public String getTitle(){
		return this.title;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Productcategory(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		created = (String) jsonObject.opt("created");
		id = jsonObject.optInt("id");
		slug = (String) jsonObject.opt("slug");
		title = (String) jsonObject.opt("title");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("created", created);
			jsonObject.put("id", id);
			jsonObject.put("slug", slug);
			jsonObject.put("title", title);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}