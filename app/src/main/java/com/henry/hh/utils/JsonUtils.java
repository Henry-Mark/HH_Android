package com.henry.hh.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.henry.hh.entity.Friend;
import com.henry.library.utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2017/1/5. 15:31
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:Json解析
 */
public class JsonUtils {

    private static String TAG = "JsonUtils";


    /**
     * 从Json中解析出好友列表
     *
     * @param result
     * @return
     */
    public static List<Friend> getFriendlistFromJson(String result) {
        Gson gson = new Gson();
        List<Friend> friends = null;
        if (result != null) {
            friends = new ArrayList<Friend>();
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("datas");
                int length = jsonArray.length();
                if (length != 0) {
                    for (int j = 0; j < length; j++) {
                        JSONObject friendObject = jsonArray.getJSONObject(j);
                        LogUtils.d(TAG, "friendObject=" + friendObject.toString());
                        Friend friend = gson.fromJson(friendObject.toString(), new TypeToken<Friend>() {
                        }.getType());
                        friends.add(friend);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return friends;
    }
}
