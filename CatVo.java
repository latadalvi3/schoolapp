package implant.lata.com.dumetschool;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by Sami on 4/6/2017.
 */

public class CatVo {
    String id,title;
    CatVo(){
        super();
    }
    CatVo(JSONObject jsonObject){
        try{
           setId(jsonObject.getString("id"));
        }catch (Throwable t){
            Log.e("", "Unable to parse from JSON: " + t.toString());
        }
        try{
            setTitle(jsonObject.getString("title"));
        } catch (Throwable t){
            Log.e("", "Unable to parse from JSON: " + t.toString());
        }
    }
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return id;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }

    @Override
    public String toString() {
        return getId() + "" + getTitle();
    }
}


