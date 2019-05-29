package com.bkpp.tables;

import com.bkpp.model.Author;
import com.bkpp.model.Book;
import com.bkpp.model.Category;
import com.bkpp.model.Rating;
import com.bkpp.utils.EnumI18N;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class BooksTable extends TableView<Book> {
    private List<Book> books;
    private List<Author> authors;
    private TableCellExceptionHandler tableCellExceptionHandler;
    private ResourceBundle resourceBundle;
    private EnumI18N enumI18N;

    public BooksTable(List<Book> books, List<Author> authors, TableCellExceptionHandler tableCellExceptionHandler, ResourceBundle resourceBundle){
        super(FXCollections.observableArrayList(books));
        this.books = books;
        this.authors = authors;
        this.tableCellExceptionHandler = tableCellExceptionHandler;
        this.resourceBundle = resourceBundle;
        this.enumI18N = new EnumI18N(resourceBundle);
        setUp();
    }

    private void setUp() {
        setUpBookIdColumn();
        setUpAuthorsColumn();
        setUpCategoryColumn();
        setUpPagesColumn();
        setUpRatingColumn();
        setUpTitleColumn();
        setUpReleaseDate();
        setUpCollectionsUpdate();
        super.setEditable(true);
    }

    private void setUpBookIdColumn() {
        TableColumn<Book, String> idColumn = new TableColumn<>(resourceBundle.getString("ID"));

        idColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));

        super.getColumns().add(idColumn);
    }

    private void setUpAuthorsColumn() {
        TableColumn<Book, String> authorsColumn = new TableColumn<>(resourceBundle.getString("author"));
        Map<String, Author> authorsNames = authors.stream().collect(Collectors.toMap(this::mapAuthor, a ->a));

        authorsColumn.setCellValueFactory(param -> {
            Book book = param.getValue();

            return new SimpleStringProperty(mapAuthor(book.getAuthor()));
        });
        authorsColumn.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(authorsNames.keySet())));
        authorsColumn.setOnEditCommit(event -> {
            TablePosition<Book, String> pos = event.getTablePosition();

            String newAuthorName = event.getNewValue();

            int row = pos.getRow();

            Book book = event.getTableView().getItems().get(row);
            book.setAuthor(authorsNames.get(newAuthorName));
        });

        super.getColumns().add(authorsColumn);
    }

    private void setUpCategoryColumn() {
        TableColumn<Book, String> categoryColumn = new TableColumn<>(resourceBundle.getString("category"));

        categoryColumn.setCellValueFactory(param-> {
            Book book = param.getValue();

            String categoryString = enumI18N.getCategoryI18N(book.getCategory());

            return new SimpleStringProperty(categoryString);
        });

        categoryColumn.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(enumI18N.getCategoriesI18N().keySet())));

        categoryColumn.setOnEditCommit(event -> {
            TablePosition<Book, String> pos = event.getTablePosition();

            Category newCategory = enumI18N.getCategoryI18N(event.getNewValue());

            int row = pos.getRow();

            Book book = event.getTableView().getItems().get(row);
            book.setCategory(newCategory);
        });

        super.getColumns().add(categoryColumn);
    }

    private void setUpPagesColumn() {
        TableColumn<Book, Integer> pagesColumn = new TableColumn<>(resourceBundle.getString("pages"));


        pagesColumn.setCellValueFactory(new PropertyValueFactory<>("pages"));
        pagesColumn.setCellFactory(TextFieldTableCell.forTableColumn(getIntegerStringConverter()));
        pagesColumn.setOnEditCommit(event -> {
            TablePosition<Book, Integer> pos = event.getTablePosition();

            int newPages = event.getNewValue();
            int row = pos.getRow();

            Book book = event.getTableView().getItems().get(row);
            book.setPages(newPages);
        });

        super.getColumns().add(pagesColumn);
    }

    private void setUpRatingColumn() {
        TableColumn<Book, String> ratingColumn = new TableColumn<>(resourceBundle.getString("rating"));

        ratingColumn.setCellValueFactory(param-> {
            Book book = param.getValue();

            String ratingString = enumI18N.getRatingI18N(book.getRating());

            return new SimpleStringProperty(ratingString);
        });

        ratingColumn.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(enumI18N.getRatingI18N().keySet())));

        ratingColumn.setOnEditCommit(event -> {
            TablePosition<Book, String> pos = event.getTablePosition();

            Rating newRating = enumI18N.getRaringI18N(event.getNewValue());

            int row = pos.getRow();

            Book book = event.getTableView().getItems().get(row);
            book.setRating(newRating);
        });

        super.getColumns().add(ratingColumn);
    }

    private void setUpTitleColumn() {
        TableColumn<Book, String> titleColumn = new TableColumn<>(resourceBundle.getString("title"));


        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        titleColumn.setOnEditCommit(event -> {
            TablePosition<Book, String> pos = event.getTablePosition();

            String newTitle = event.getNewValue();

            int row = pos.getRow();

            Book book = event.getTableView().getItems().get(row);
            book.setTitle(newTitle);
        });

        super.getColumns().add(titleColumn);
    }

    private void setUpReleaseDate() {
        TableColumn<Book, String> releaseDateColumn = new TableColumn<>(resourceBundle.getString("releaseDate"));
        TableColumn<Book, Integer> releaseDateDayColumn = new TableColumn<>(resourceBundle.getString("day"));
        TableColumn<Book, Integer> releaseDateMonthColumn = new TableColumn<>(resourceBundle.getString("month"));
        TableColumn<Book, Integer> releaseDateYearColumn = new TableColumn<>(resourceBundle.getString("year"));

        releaseDateDayColumn.setCellValueFactory(param -> {
            Book book = param.getValue();

            return new SimpleIntegerProperty(book.getReleaseDate().getDay()).asObject();
        });
        releaseDateMonthColumn.setCellValueFactory(param -> {
            Book book = param.getValue();

            return new SimpleIntegerProperty(book.getReleaseDate().getMonth()).asObject();
        });
        releaseDateYearColumn.setCellValueFactory(param -> {
            Book book = param.getValue();

            return new SimpleIntegerProperty(book.getReleaseDate().getYear()).asObject();
        });

        releaseDateDayColumn.setCellFactory(TextFieldTableCell.forTableColumn(getIntegerStringConverter()));
        releaseDateMonthColumn.setCellFactory(TextFieldTableCell.forTableColumn(getIntegerStringConverter()));
        releaseDateYearColumn.setCellFactory(TextFieldTableCell.forTableColumn(getIntegerStringConverter()));

        releaseDateDayColumn.setOnEditCommit(event -> {
            TablePosition<Book, Integer> pos = event.getTablePosition();

            int newDay = event.getNewValue();

            int row = pos.getRow();

            Book book = event.getTableView().getItems().get(row);
            book.getReleaseDate().setDay(newDay);
        });
        releaseDateMonthColumn.setOnEditCommit(event -> {
            TablePosition<Book, Integer> pos = event.getTablePosition();

            int newMonth = event.getNewValue();

            int row = pos.getRow();

            Book book = event.getTableView().getItems().get(row);
            book.getReleaseDate().setMonth(newMonth);
        });
        releaseDateYearColumn.setOnEditCommit(event -> {
            TablePosition<Book, Integer> pos = event.getTablePosition();

            int newYear = event.getNewValue();

            int row = pos.getRow();

            Book book = event.getTableView().getItems().get(row);
            book.getReleaseDate().setYear(newYear);
        });


        releaseDateColumn.getColumns().add(releaseDateDayColumn);
        releaseDateColumn.getColumns().add(releaseDateMonthColumn);
        releaseDateColumn.getColumns().add(releaseDateYearColumn);
        super.getColumns().add(releaseDateColumn);
    }

    private void setUpCollectionsUpdate() {
        super.getItems().addListener((ListChangeListener<Book>) change -> {
            while(change.next()){
                if(change.wasAdded()){
                    books.addAll(change.getAddedSubList());
                } else if(change.wasRemoved()){
                    books.removeAll(change.getList());
                }
            }
        });
    }

    private IntegerStringConverter getIntegerStringConverter(){
        return new IntegerStringConverter(){
            @Override
            public Integer fromString(String value){
                try {
                    return Integer.valueOf(value);
                } catch(NumberFormatException e){
                    tableCellExceptionHandler.handleException(resourceBundle.getString("mustBeInteger"));
                    return 1;
                }
            }
        };
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
