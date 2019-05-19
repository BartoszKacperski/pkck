package com.bkpp.utils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import lombok.AllArgsConstructor;


@AllArgsConstructor

public class IntegerTextViewListener implements ChangeListener<String> {
    private TextField textField;


    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue,
                        String newValue) {
        if (!newValue.matches("\\d*")) {
            textField.setText(newValue.replaceAll("[^\\d]", ""));
        }
    }
}
