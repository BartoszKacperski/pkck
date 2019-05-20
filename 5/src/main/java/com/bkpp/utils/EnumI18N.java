package com.bkpp.utils;

import com.bkpp.model.Category;
import com.bkpp.model.Rating;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.ResourceBundle;

@AllArgsConstructor

public class EnumI18N {
    private ResourceBundle resourceBundle;
    @Getter
    private HashMap<String, Category> categoriesI18N;
    @Getter
    private HashMap<String, Rating> ratingI18N;

    public EnumI18N(ResourceBundle resourceBundle){
        this.resourceBundle = resourceBundle;
        setUp();
    }

    private void setUp() {
        categoriesI18N = new HashMap<>();

        categoriesI18N.put(resourceBundle.getString("drama"), Category.drama);
        categoriesI18N.put(resourceBundle.getString("novel"), Category.novel);
        categoriesI18N.put(resourceBundle.getString("action"), Category.action);
        categoriesI18N.put(resourceBundle.getString("horror"), Category.horror);
        categoriesI18N.put(resourceBundle.getString("criminal"), Category.criminal);

        ratingI18N = new HashMap<>();

        ratingI18N.put(resourceBundle.getString("empty"), Rating.empty);
        ratingI18N.put(resourceBundle.getString("one"), Rating.one);
        ratingI18N.put(resourceBundle.getString("two"), Rating.two);
        ratingI18N.put(resourceBundle.getString("three"), Rating.three);
        ratingI18N.put(resourceBundle.getString("four"), Rating.four);
        ratingI18N.put(resourceBundle.getString("five"), Rating.five);
    }


    public String getCategoryI18N(Category category) {
        for(HashMap.Entry<String, Category> categoryEntry : categoriesI18N.entrySet()){
            if(category == categoryEntry.getValue()){
                return categoryEntry.getKey();
            }
        }

        return "";
    }

    public Category getCategoryI18N(String category) {
        return categoriesI18N.get(category);
    }

    public String getRatingI18N(Rating rating) {
        for(HashMap.Entry<String, Rating> categoryEntry : ratingI18N.entrySet()){
            if(rating == categoryEntry.getValue()){
                return categoryEntry.getKey();
            }
        }

        return "";
    }

    public Rating getRaringI18N(String rating) {
        return ratingI18N.get(rating);
    }
}
