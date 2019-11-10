package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        if (json == null || json.isEmpty()) {
            return null;
        }
        List<String> alsoKnownAsList = new ArrayList<>();;
        List<String> ingredientsList = new ArrayList<>();;

        try {
//            JSONObject sandwichRootJSON = new JSONObject(json);
//            JSONObject nameJsonObject = sandwichRootJSON.getJSONObject("name");
//            String mainName = nameJsonObject.getString("mainName");
//            JSONArray alsoKnownAs = nameJsonObject.getJSONArray("alsoKnownAs");
//
//            if (alsoKnownAs != null) {
//                for (int j = 0; j < alsoKnownAs.length(); j++) {
//                    alsoKnownAsList.add(alsoKnownAs.getString(j));
//                }
//            }

            JSONObject rootJSON = new JSONObject(json);
            JSONObject name = rootJSON.getJSONObject("name");
            String mainName = name.getString("mainName");
            JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");
            if (alsoKnownAs != null) {
                for (int i = 0; i < alsoKnownAs.length(); i++) {
                    alsoKnownAsList.add(alsoKnownAs.getString(i));
                }
            }

            String placeOfOrigin = rootJSON.getString("placeOfOrigin");
            String description = rootJSON.getString("description");
            String image = rootJSON.getString("image");
            JSONArray ingredients = rootJSON.getJSONArray("ingredients");
            if (ingredients != null) {
                for (int j = 0; j < ingredients.length(); j++) {
                    ingredientsList.add(ingredients.getString(j));
                }
            }


            Log.d("It is main name ",mainName);
            Sandwich sandwich = new Sandwich(mainName,alsoKnownAsList, placeOfOrigin, description, image, ingredientsList);
            return sandwich;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}


