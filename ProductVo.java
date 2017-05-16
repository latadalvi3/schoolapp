package implant.lata.com.dumetschool;//
//	ProductVo.java
//
//	Create by MP-03 on 8/4/2017
//	Copyright © 2017. All rights reserved.
//	Model file generated using JSONExport: https://github.com/Ahmed-Ali/JSONExport

import org.json.JSONException;
import org.json.JSONObject;


public class ProductVo {

	private String created;
	private String description;
	private String image;
	private String metadescription;
	private Productcategory productcategory;
	private String slug;
	private String title;
	private String titletag;
	private String updated;

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
	public void setProductcategory(Productcategory productcategory){
		this.productcategory = productcategory;
	}
	public Productcategory getProductcategory(){
		return this.productcategory;
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

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public ProductVo(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		created = (String) jsonObject.opt("created");
		description = (String) jsonObject.opt("description");
		image = (String) jsonObject.opt("image");
		metadescription = (String) jsonObject.opt("metadescription");
		productcategory = new Productcategory(jsonObject.optJSONObject("productcategory"));
		slug = (String) jsonObject.opt("slug");
		title = (String) jsonObject.opt("title");
		titletag = (String) jsonObject.opt("titletag");
		updated = (String) jsonObject.opt("updated");
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
			jsonObject.put("image", image);
			jsonObject.put("metadescription", metadescription);
			jsonObject.put("productcategory", productcategory.toJsonObject());
			jsonObject.put("slug", slug);
			jsonObject.put("title", title);
			jsonObject.put("titletag", titletag);
			jsonObject.put("updated", updated);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}
