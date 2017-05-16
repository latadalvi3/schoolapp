package implant.lata.com.dumetschool;//
//	Item.java
//
//	Create by MP-03 on 7/4/2017
//	Copyright © 2017. All rights reserved.
//	Model file generated using JSONExport: https://github.com/Ahmed-Ali/JSONExport

import org.json.JSONException;
import org.json.JSONObject;


public class Item{

	private String created;
	private String description;
	private int headline;
	private int hit;
	private String image;
	private String metadescription;
	private Postcategory postcategory;
	private String slug;
	private int status;
	private String title;
	private String titletag;
	private String updated;
	private User user;

	public void setCreated(String created){
		this.created = created;
	}
	public String getCreated(){
		return this.created;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){
		return this.description;
	}
	public void setHeadline(int headline){
		this.headline = headline;
	}
	public int getHeadline(){
		return this.headline;
	}
	public void setHit(int hit){
		this.hit = hit;
	}
	public int getHit(){
		return this.hit;
	}
	public void setImage(String image){
		this.image = image;
	}
	public String getImage(){
		return this.image;
	}
	public void setMetadescription(String metadescription){
		this.metadescription = metadescription;
	}
	public String getMetadescription(){
		return this.metadescription;
	}
	public void setPostcategory(Postcategory postcategory){
		this.postcategory = postcategory;
	}
	public Postcategory getPostcategory(){
		return this.postcategory;
	}
	public void setSlug(String slug){
		this.slug = slug;
	}
	public String getSlug(){
		return this.slug;
	}
	public void setStatus(int status){
		this.status = status;
	}
	public int getStatus(){
		return this.status;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public String getTitle(){
		return this.title;
	}
	public void setTitletag(String titletag){
		this.titletag = titletag;
	}
	public String getTitletag(){
		return this.titletag;
	}
	public void setUpdated(String updated){
		this.updated = updated;
	}
	public String getUpdated(){
		return this.updated;
	}
	public void setUser(User user){
		this.user = user;
	}
	public User getUser(){
		return this.user;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Item(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		created = (String) jsonObject.opt("created");
		description = (String) jsonObject.opt("description");
		headline = jsonObject.optInt("headline");
		hit = jsonObject.optInt("hit");
		image = (String) jsonObject.opt("image");
		metadescription = (String) jsonObject.opt("metadescription");
		postcategory = new Postcategory(jsonObject.optJSONObject("postcategory"));
		slug = (String) jsonObject.opt("slug");
		status = jsonObject.optInt("status");
		title = (String) jsonObject.opt("title");
		titletag = (String) jsonObject.opt("titletag");
		updated = (String) jsonObject.opt("updated");
		user = new User(jsonObject.optJSONObject("user"));
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("created", created);
			jsonObject.put("description", description);
			jsonObject.put("headline", headline);
			jsonObject.put("hit", hit);
			jsonObject.put("image", image);
			jsonObject.put("metadescription", metadescription);
			jsonObject.put("postcategory", postcategory.toJsonObject());
			jsonObject.put("slug", slug);
			jsonObject.put("status", status);
			jsonObject.put("title", title);
			jsonObject.put("titletag", titletag);
			jsonObject.put("updated", updated);
			jsonObject.put("user", user.toJsonObject());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}