package com.bkpp.utils;

import com.bkpp.model.Category;
import lombok.AllArgsConstructor;

import java.util.ResourceBundle;

@AllArgsConstructor

public class EnumI18N {
    private ResourceBundle resourceBundle;


    public String getCategoryI18N(Category category) {
        if(category == null){
            return "";
        }
        switch (category) {
            case drama:
                return resourceBundle.getString("drama");
            case novel:
                return resourceBundle.getString("novel");
            case action:
                return resourceBundle.getString("action");
            case horror:
                return resourceBundle.getString("horror");
            case criminal:
                return resourceBundle.getString("criminal");
            default:
                return "";
        }
    }
}
