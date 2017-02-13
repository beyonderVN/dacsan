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

/**
 * Created by Long on 2/9/2017.
 */

public class GetDataFromAssets<T> {
    private static final String TAG = "GetDataFromAssets";

    public List<T> getPostList(String fileName, Context context){
        Gson gson = new Gson();
        List<T> rateList = new ArrayList<>();
        String rateListString =  FileUtils.readFromfile(fileName, context);
        if (rateListString==null||rateListString.equals("")){
            Log.d(TAG, "getPostList: File not found or File type is wrong!");
        }
        Type listType = new TypeToken<List<T>>() {}.getType();
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = (JsonArray) parser.parse(rateListString).getAsJsonObject().get("posts");
        rateList = new Gson().fromJson(jsonArray, listType);
        JsonElement element = gson.toJsonTree(rateList, listType);
        return  rateList;
    }
//    public static List<Post> getPostListWithComments(String fileName, Context context){
//        Gson gson = new Gson();
//        List<Post> rateList = new ArrayList<>();
//        String rateListString =  FileUtils.readFromfile(fileName, context);
//
//        JsonParser parser = new JsonParser();
//        JsonArray jsonArray = parser.parse(rateListString).getAsJsonObject().getAsJsonArray("posts");
//
//        Type listType = new TypeToken<List<Post>>() {}.getType();
//        rateList = new Gson().fromJson(jsonArray, listType);
//        for (Post post : rateList){
//
//            Log.d(TAG, "onClick: " + post.getTipName());
//            HashMap<String,Comment> comments = new HashMap<String,Comment>();
//            String[] names = {"Ezeal","Leona","Corgi","Lucian","Olaf","Kennen"};
//            String[] messages = {"I will cook it!","Delicious!","Thank you!","I love this recipe!","Yummy!","Ahihi!"};
//            for (int i = 0; i < 10; i++) {
//                User user = new User();
//                user.setId("gSUNZWLvLmS5vdu7YTcQlXEDX5p1");
//                Random r = new Random();
//                int i1 = (r.nextInt(names.length-1));
//                user.setName(names[i1]);
//                user.setPhoto_profile("http://fanexpodallas.com/wp-content/uploads/550w_soaps_silhouettesm2.jpg");
//                Comment comment = new Comment();
//                comment.user = user;
//                Random r2 = new Random();
//                int i2 = (r2.nextInt(messages.length-1));
//                comment.message = messages[i2];
//                comment.createAt = "" + Calendar.getInstance().getTime().getTime();
//                comment.updateAt = "" + Calendar.getInstance().getTime().getTime();
//                comments.put(""+i,comment);
//            }
//            post.setTipComments(comments);
//
//        }
//
//        JsonElement element = gson.toJsonTree(rateList, listType);
//
//        String result = element.getAsJsonArray().toString();
//        Log.d(TAG, "rateList: "+result);
//        return  rateList;
//    }
}
