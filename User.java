package implant.lata.com.dumetschool;//
//	User.java
//
//	Create by MP-03 on 7/4/2017
//	Copyright © 2017. All rights reserved.
//	Model file generated using JSONExport: https://github.com/Ahmed-Ali/JSONExport

import org.json.JSONException;
import org.json.JSONObject;


public class User{

	private String foto;
	private String namaLengkap;

	public void setFoto(String foto){
		this.foto = foto;
	}
	public String getFoto(){
		return this.foto;
	}
	public void setNamaLengkap(String namaLengkap){
		this.namaLengkap = namaLengkap;
	}
	public String getNamaLengkap(){
		return this.namaLengkap;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public User(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		foto = (String) jsonObject.opt("foto");
		namaLengkap = (String) jsonObject.opt("nama_lengkap");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("foto", foto);
			jsonObject.put("nama_lengkap", namaLengkap);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}