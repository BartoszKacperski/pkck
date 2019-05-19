package com.bkpp.tables;

import com.bkpp.model.Author;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.List;
import java.util.ResourceBundle;

public class AuthorsTable extends TableView<Author> {
    private List<Author> authors;
    private ResourceBundle resourceBundle;

    public AuthorsTable(List<Author> authors, ResourceBundle resourceBundle){
        super(FXCollections.observableArrayList(authors));
        this.authors = authors;
        this.resourceBundle = resourceBundle;
        setUp();
    }

    private void setUp() {
        setUpIdColumn();
        setUpFirstNameColumn();
        setUpLastNameColumn();
        setUpPseudonymColumn();
        setUpCollectionsUpdate();
        super.setEditable(true);
    }

    private void setUpIdColumn() {
        TableColumn<Author, String> idColumn = new TableColumn<>(resourceBundle.getString("ID"));

        idColumn.setCellValueFactory(new PropertyValueFactory<>("authorId"));

        super.getColumns().add(idColumn);
    }

    private void setUpFirstNameColumn() {
        TableColumn<Author, String> authorFirstNames = new TableColumn<>(resourceBundle.getString("firstNames"));

        authorFirstNames.setCellValueFactory(param -> {
            StringBuilder stringBuilder = new StringBuilder();

            for(String firstName : param.getValue().getFirstNames()){
                stringBuilder.append(firstName).append(" ");
            }

            return new SimpleStringProperty(stringBuilder.toString());
        });

        authorFirstNames.setCellFactory(TextFieldTableCell.forTableColumn());

        authorFirstNames.setOnEditCommit(event -> {
            TablePosition<Author, String> pos = event.getTablePosition();

            String [] names = event.getNewValue().split(" ");

            int row = pos.getRow();

            Author author = event.getTableView().getItems().get(row);
            author.clearFirstNames();

            for(String name : names){
                author.addFirstName(name);
            }
        });

        super.getColumns().add(authorFirstNames);
    }


    private void setUpLastNameColumn() {
        TableColumn<Author, String> authorLastNameColumn = new TableColumn<>(resourceBundle.getString("lastName"));

        authorLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        authorLastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        authorLastNameColumn.setOnEditCommit(event -> {
            TablePosition<Author, String> pos = event.getTablePosition();

            String newLastName = event.getNewValue();

            int row = pos.getRow();

            Author author = event.getTableView().getItems().get(row);
            author.setLastName(newLastName);
        });

        super.getColumns().add(authorLastNameColumn);
    }

    private void setUpPseudonymColumn() {
        TableColumn<Author, String> authorPseudonymColumn = new TableColumn<>(resourceBundle.getString("pseudonym"));

        authorPseudonymColumn.setCellValueFactory(new PropertyValueFactory<>("pseudonym"));
        authorPseudonymColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        authorPseudonymColumn.setOnEditCommit(event -> {
            TablePosition<Author, String> pos = event.getTablePosition();

            String newPseudonym = event.getNewValue();

            int row = pos.getRow();

            Author author = event.getTableView().getItems().get(row);
            author.setPseudonym(newPseudonym);
        });

        super.getColumns().add(authorPseudonymColumn);
    }

    private void setUpCollectionsUpdate() {
        super.getItems().addListener((ListChangeListener<Author>) change -> {
            while(change.next()){
                if(change.wasAdded()){
                    authors.addAll(change.getAddedSubList());
                } else if(change.wasRemoved()){
                    authors.removeAll(change.getList());
                }
            }
        });
    }
}
