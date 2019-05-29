package com.bkpp.tables;

import com.bkpp.model.Owner;
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

public class OwnerTable extends TableView<Owner> {
    private List<Owner> owners;
    private ResourceBundle resourceBundle;

    public OwnerTable(List<Owner> owners, ResourceBundle resourceBundle) {
        super(FXCollections.observableArrayList(owners));
        this.owners = owners;
        this.resourceBundle = resourceBundle;
        setUp();
    }

    private void setUp() {
        setUpOwnerFirstNamesColumn();
        setUpOwnerLastNameColumn();
        setUpCollectionsUpdate();
        super.setEditable(true);
    }


    private void setUpOwnerFirstNamesColumn() {
        TableColumn<Owner, String> userFirstNamesColumn = new TableColumn<>(resourceBundle.getString("firstNames"));

        userFirstNamesColumn.setCellValueFactory(param -> {
            StringBuilder stringBuilder = new StringBuilder();

            for(String firstName : param.getValue().getFirstNames()){
                stringBuilder.append(firstName).append(" ");
            }

            return new SimpleStringProperty(stringBuilder.toString());
        });
        userFirstNamesColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        userFirstNamesColumn.setOnEditCommit(event -> {
            TablePosition<Owner, String> pos = event.getTablePosition();

            String [] names = event.getNewValue().split(" ");

            int row = pos.getRow();

            Owner owner = event.getTableView().getItems().get(row);
            owner.clearFirstNames();

            for(String name : names){
                owner.addFirstName(name);
            }
        });


        super.getColumns().add(userFirstNamesColumn);
    }

    private void setUpOwnerLastNameColumn() {
        TableColumn<Owner, String> userLastNameColumn = new TableColumn<>(resourceBundle.getString("lastName"));

        userLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        userLastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        userLastNameColumn.setOnEditCommit(event -> {
            TablePosition<Owner, String> pos = event.getTablePosition();

            String newLastName = event.getNewValue();

            int row = pos.getRow();

            Owner owner = event.getTableView().getItems().get(row);
            owner.setLastName(newLastName);
        });

        super.getColumns().add(userLastNameColumn);
    }

    private void setUpCollectionsUpdate() {
        super.getItems().addListener((ListChangeListener<Owner>) change ->{
            while(change.next()){
                if(change.wasAdded()){
                    owners.addAll(change.getAddedSubList());
                } else if(change.wasRemoved()){
                    owners.removeAll(change.getList());
                }
            }
        });
    }
}
