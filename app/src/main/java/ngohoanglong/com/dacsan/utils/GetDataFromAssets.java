package ngohoanglong.com.dacsan.utils;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ngohoanglong.com.dacsan.model.PostVivmall;
import ngohoanglong.com.dacsan.model.ProductType;

/**
 * Created by Long on 2/9/2017.
 */

public class GetDataFromAssets<T> {
    private static final String TAG = "GetDataFromAssets";

    public static List<PostVivmall> getPostList(String fileName, Context context){
        Gson gson = new Gson();
        List<PostVivmall> rateList = new ArrayList<>();
        String rateListString =  FileUtils.readFromfile(fileName, context);
        if (rateListString==null||rateListString.equals("")){
            Log.d(TAG, "getPostList: File not found or File type is wrong!");
        }
        Type listType = new TypeToken<List<PostVivmall>>() {}.getType();
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = (JsonArray) parser.parse(rateListString).getAsJsonObject().get("posts");
        rateList = new Gson().fromJson(jsonArray, listType);
        JsonElement element = gson.toJsonTree(rateList, listType);
        return  rateList;
    }
    public static List<PostVivmall> getProductsByType(String fileName,String productType, Context context){
        Gson gson = new Gson();
        List<PostVivmall> rateList = new ArrayList<>();
        String rateListString =  FileUtils.readFromfile(fileName, context);
        if (rateListString==null||rateListString.equals("")){
            Log.d(TAG, "getProductsByType: File not found or File type is wrong!");
        }
        Type listType = new TypeToken<List<PostVivmall>>() {}.getType();
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = (JsonArray) parser.parse(rateListString).getAsJsonObject().get(productType);
        rateList = new Gson().fromJson(jsonArray, listType);
        return  rateList;
    }
    public static List<ProductType> getProductType(String fileName, Context context){
        Gson gson = new Gson();
        List<ProductType> rateList = new ArrayList<>();
        String rateListString =  FileUtils.readFromfile(fileName, context);
        if (rateListString==null||rateListString.equals("")){
            Log.d(TAG, "getPostList: File not found or File type is wrong!");
        }
        Type listType = new TypeToken<List<ProductType>>() {}.getType();
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = (JsonArray) parser.parse(rateListString).getAsJsonObject().get("cata");
        rateList = new Gson().fromJson(jsonArray, listType);
        JsonElement element = gson.toJsonTree(rateList, listType);
        return  rateList;
    }

}
