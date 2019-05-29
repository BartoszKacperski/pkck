package com.bkpp.dialogs;

import com.bkpp.model.*;
import com.bkpp.utils.EnumI18N;
import com.bkpp.utils.IntegerTextViewListener;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AddBookDialog extends Dialog<Book> {
    private ResourceBundle resourceBundle;

    public AddBookDialog(ResourceBundle resourceBundle, String bookId, List<Author> authors){
        this.resourceBundle = resourceBundle;
        setUp(bookId, authors);
    }

    private void setUp(String bookId, List<Author> authors) {
        super.setTitle(resourceBundle.getString("addBook"));

        GridPane root = new GridPane();
        root.setHgap(10.0);
        root.setVgap(10.0);

        root.add(new Label(resourceBundle.getString("ID")), 0 , 0);
        root.add(new Label(resourceBundle.getString("authorID")), 0 , 1);
        root.add(new Label(resourceBundle.getString("category")), 0 , 2);
        root.add(new Label(resourceBundle.getString("pages")), 0 , 3);
        root.add(new Label(resourceBundle.getString("rating")), 0 , 4);
        root.add(new Label(resourceBundle.getString("title")), 0 , 5);
        root.add(new Label(resourceBundle.getString("releaseDate")), 0 , 6);

        Map<String, Author> authorsNames = authors.stream().collect(Collectors.toMap(this::mapAuthor, a ->a));

        ComboBox<String> authorsComboBox = new ComboBox<>(FXCollections.observableArrayList(authorsNames.keySet()));
        authorsComboBox.setPrefWidth(150.0);

        ComboBox<Category> categories = new ComboBox<>(FXCollections.observableArrayList(Category.values()));
        categories.setPrefWidth(150.0);
        categories.setConverter(new StringConverter<>() {
            private final EnumI18N enumI18N = new EnumI18N(resourceBundle);

            @Override
            public String toString(Category category) {
                return enumI18N.getCategoryI18N(category);
            }

            @Override
            public Category fromString(String s) {
                return enumI18N.getCategoryI18N(s);
            }
        });

        ComboBox<Rating> ratings = new ComboBox<>(FXCollections.observableArrayList(Rating.values()));
        ratings.setPrefWidth(150.0);
        ratings.setConverter(new StringConverter<>() {
            private final EnumI18N enumI18N = new EnumI18N(resourceBundle);

            @Override
            public String toString(Rating rating) {
                return enumI18N.getRatingI18N(rating);
            }

            @Override
            public Rating fromString(String s) {
                return enumI18N.getRaringI18N(s);
            }
        });

        TextField pages = new TextField();
        pages.textProperty().addListener(new IntegerTextViewListener(pages));

        TextField title = new TextField();
        DatePicker datePicker = new DatePicker(LocalDate.now());

        root.add(new Label(bookId), 1, 0);
        root.add(authorsComboBox, 1, 1);
        root.add(categories, 1, 2);
        root.add(pages, 1, 3);
        root.add(ratings, 1, 4);
        root.add(title, 1, 5);
        root.add(datePicker, 1, 6);


        super.getDialogPane().setContent(root);

        super.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);

        super.setResultConverter(buttonType -> {
            if(buttonType == ButtonType.APPLY){
                Book book = new Book();

                book.setBookId(bookId);
                book.setAuthor(authorsNames.get(authorsComboBox.getSelectionModel().getSelectedItem()));
                book.setCategory(categories.getSelectionModel().getSelectedItem());
                book.setPages(Integer.valueOf(pages.getText()));
                book.setRating(ratings.getSelectionModel().getSelectedItem());
                book.setTitle(title.getText());

                ReleaseDate releaseDate = new ReleaseDate();
                LocalDate date = datePicker.getValue();

                releaseDate.setDay(date.getDayOfMonth());
                releaseDate.setMonth(date.getMonthValue() + 1);
                releaseDate.setYear(date.getYear());

                book.setReleaseDate(releaseDate);

                return book;
            }
            return null;
        });
    }

    private String mapAuthor(Author author){
        StringBuilder stringBuilder = new StringBuilder();

        for(String firstName : author.getFirstNames()){
            stringBuilder.append(firstName.charAt(0)).append(". ");
        }

        stringBuilder.append(author.getLastName());

        return stringBuilder.toString();
    }
}
