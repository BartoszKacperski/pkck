package com.bkpp;

import com.bkpp.model.Owner;
import com.bkpp.model.Root;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    TableView<Owner> table;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn<Owner, String>  userLastNameColumn = new TableColumn<>("Last name");
        TableColumn<Owner, List<String>> userFirstNamesColumn = new TableColumn<>("First names");

        userLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        userFirstNamesColumn.setCellValueFactory(new PropertyValueFactory<>("firstNames"));

        try {
            table.setItems(owners());
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        table.getColumns().addAll(userFirstNamesColumn, userLastNameColumn);
    }

    private ObservableList<Owner> owners() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        unmarshaller.getProperty(Marshaller.JAXB_SCHEMA_LOCATION);
        Root root = (Root) unmarshaller.unmarshal(new File("BookStore.xml"));

        return FXCollections.observableArrayList(root.getOwners());
    }
}
