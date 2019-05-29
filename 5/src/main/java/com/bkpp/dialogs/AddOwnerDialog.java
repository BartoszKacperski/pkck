package com.bkpp.dialogs;

import com.bkpp.model.Owner;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.Arrays;
import java.util.ResourceBundle;

public class AddOwnerDialog extends Dialog<Owner> {
    private ResourceBundle resourceBundle;

    public AddOwnerDialog(ResourceBundle resourceBundle){
        this.resourceBundle = resourceBundle;
        setUp();
    }

    private void setUp() {
        super.setTitle(resourceBundle.getString("addOwner"));

        GridPane root = new GridPane();
        root.setHgap(10.0);
        root.setVgap(10.0);

        root.add(new Label(resourceBundle.getString("firstNames")), 0 , 0);
        root.add(new Label(resourceBundle.getString("lastName")), 0 , 1);

        TextField firstNames = new TextField();
        TextField lastName = new TextField();

        root.add(firstNames, 1, 0);
        root.add(lastName, 1, 1);

        super.getDialogPane().setContent(root);

        super.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);

        super.setResultConverter(buttonType -> {
            if(buttonType == ButtonType.APPLY){
                Owner owner = new Owner();

                owner.setLastName(lastName.getText());
                owner.setFirstNames(Arrays.asList(firstNames.getText().split(" ")));

                return owner;
            }
            return null;
        });
    }


}
