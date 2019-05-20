package com.bkpp.dialogs;

import com.bkpp.model.Author;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.Arrays;
import java.util.ResourceBundle;

public class AddAuthorDialog extends Dialog<Author> {
    private ResourceBundle resourceBundle;

    public AddAuthorDialog(ResourceBundle resourceBundle, String authorId){
        this.resourceBundle = resourceBundle;
        setUp(authorId);
    }

    private void setUp(String authorId) {
        super.setTitle(resourceBundle.getString("addAuthor"));

        GridPane root = new GridPane();
        root.setHgap(10.0);
        root.setVgap(10.0);

        root.add(new Label(resourceBundle.getString("ID")), 0 , 0);
        root.add(new Label(authorId), 1, 0);
        root.add(new Label(resourceBundle.getString("firstNames")), 0 , 1);
        root.add(new Label(resourceBundle.getString("lastName")), 0 , 2);
        root.add(new Label(resourceBundle.getString("pseudonym")), 0 , 3);

        TextField firstNames = new TextField();
        TextField lastName = new TextField();
        TextField pseudonym = new TextField();

        root.add(firstNames, 1, 1);
        root.add(lastName, 1, 2);
        root.add(pseudonym, 1, 3);

        super.getDialogPane().setContent(root);

        super.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);

        super.setResultConverter(buttonType -> {
            if(buttonType == ButtonType.APPLY){
                Author author = new Author();

                author.setAuthorId(authorId);
                author.setLastName(lastName.getText());
                author.setFirstNames(Arrays.asList(firstNames.getText().split(" ")));
                author.setPseudonym(pseudonym.getText());

                return author;
            }
            return null;
        });
    }
}
