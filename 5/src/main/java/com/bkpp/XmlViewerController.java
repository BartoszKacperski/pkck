package com.bkpp;

import com.bkpp.dialogs.AddAuthorDialog;
import com.bkpp.dialogs.AddBookDialog;
import com.bkpp.dialogs.AddOwnerDialog;
import com.bkpp.model.Author;
import com.bkpp.model.Book;
import com.bkpp.model.Owner;
import com.bkpp.model.Root;
import com.bkpp.tables.AuthorsTable;
import com.bkpp.tables.BooksTable;
import com.bkpp.tables.OwnerTable;
import com.bkpp.tables.TableCellExceptionHandler;
import com.bkpp.utils.IdGenerator;
import com.bkpp.utils.Validator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import lombok.Setter;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.ResourceBundle;

public class XmlViewerController implements Initializable {
    @FXML
    ProgressIndicator progressBar;
    @FXML
    TabPane tab;
    @FXML
    Tab books;
    @FXML
    Tab authors;
    @FXML
    Tab owners;

    private ResourceBundle resourceBundle;
    @Setter
    private Root xmlRoot;
    @Setter
    private String xmlPath;
    @Setter
    private String xsdPath;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
        progressBar.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
        Platform.runLater(this::loadAllContent);
    }

    public void onOwnersSelected() {
        if(xmlRoot != null){
            owners.setContent(createOwnersContent());
        }
    }

    public void onAuthorsSelected() {
        if(xmlRoot != null){
            authors.setContent(createAuthorsContent());
        }

    }

    public void onBooksSelected() {
        if(xmlRoot != null){
            books.setContent(createBooksContent());
        }
    }

    public void saveXml() {
        try{
            validate();
            save(xmlPath);
        } catch (JAXBException | SAXException e) {
            showErrorDialog(resourceBundle.getString("validationError"));
        }
    }

    public void saveAsXml() {
        FileChooser fileChooser = fileChooser();

        Optional<File> optionalFile = Optional.ofNullable(fileChooser.showSaveDialog(Main.getPrimaryStage()));

        optionalFile.ifPresent(file -> {
            try {
                save(file.getAbsolutePath());
            } catch (JAXBException e) {
                showErrorDialog(resourceBundle.getString("saveXmlError"));
            }
        });
    }

    public void validateXml() {
        try {
            validate();
            showInfoDialog(resourceBundle.getString("validationSuccess"),
                            resourceBundle.getString("validationSuccessInfo"),"");
        } catch (SAXException | JAXBException e) {
            showErrorDialog(e.getMessage());
        }
    }

    private TableCellExceptionHandler getTableCellExceptionHandler() {
        return this::showErrorDialog;
    }

    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(resourceBundle.getString("errorTitle"));
        alert.setHeaderText(resourceBundle.getString("errorHeader"));
        alert.setContentText(message);

        alert.showAndWait();
    }

    private void showInfoDialog(String title,
                                String header,
                                String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        alert.showAndWait();
    }


    private Node createOwnersContent(){
        OwnerTable ownerTable = new OwnerTable(xmlRoot.getOwners(), resourceBundle);
        VBox vBox = new VBox();
        HBox hBox = new HBox();
        Button add = new Button(resourceBundle.getString("add"));
        Button delete = new Button(resourceBundle.getString("delete"));

        add.setOnAction(actionEvent -> {
            Optional<Owner> optionalOwner = new AddOwnerDialog(resourceBundle).showAndWait();

            optionalOwner.ifPresent(owner -> ownerTable.getItems().addAll(owner));
        });

        delete.setOnAction(actionEvent -> {
            Owner owner = ownerTable.getSelectionModel().getSelectedItem();

            ownerTable.getItems().remove(owner);
        });

        hBox.getChildren().add(add);
        hBox.getChildren().add(delete);
        hBox.setAlignment(Pos.CENTER_RIGHT);

        vBox.getChildren().add(ownerTable);
        vBox.getChildren().add(hBox);

        return vBox;
    }

    private Node createAuthorsContent(){
        AuthorsTable authorsTable = new AuthorsTable(xmlRoot.getBookStore().getAuthors(), resourceBundle);
        VBox vBox = new VBox();
        HBox hBox = new HBox();
        Button add = new Button(resourceBundle.getString("add"));
        Button delete = new Button(resourceBundle.getString("delete"));

        add.setOnAction(actionEvent -> {
            Optional<Author> optionalOwner = new AddAuthorDialog(resourceBundle, IdGenerator.generateAuthorId(authorsTable.getItems())).showAndWait();

            optionalOwner.ifPresent(author -> authorsTable.getItems().add(author));
        });

        delete.setOnAction(actionEvent -> {
            Author author = authorsTable.getSelectionModel().getSelectedItem();

            authorsTable.getItems().remove(author);
        });

        hBox.getChildren().add(add);
        hBox.getChildren().add(delete);
        hBox.setAlignment(Pos.CENTER_RIGHT);

        vBox.getChildren().add(authorsTable);
        vBox.getChildren().add(hBox);

        return vBox;
    }

    private Node createBooksContent(){
        BooksTable bookTable = new BooksTable(xmlRoot.getBookStore().getBooks(),
                xmlRoot.getBookStore().getAuthors(),
                getTableCellExceptionHandler(),
                resourceBundle);

        VBox vBox = new VBox();
        HBox hBox = new HBox();
        Button add = new Button(resourceBundle.getString("add"));
        Button delete = new Button(resourceBundle.getString("delete"));

        add.setOnAction(actionEvent -> {
            Optional<Book> optionalOwner = new AddBookDialog(resourceBundle, IdGenerator.generateBookId(bookTable.getItems()), xmlRoot.getBookStore().getAuthors()).showAndWait();

            optionalOwner.ifPresent(author -> bookTable.getItems().add(author));
        });

        delete.setOnAction(actionEvent -> {
            Book book  = bookTable.getSelectionModel().getSelectedItem();

            bookTable.getItems().remove(book);
        });

        hBox.getChildren().add(add);
        hBox.getChildren().add(delete);
        hBox.setAlignment(Pos.CENTER_RIGHT);

        vBox.getChildren().add(bookTable);
        vBox.getChildren().add(hBox);

        return vBox;
    }

    private void loadAllContent(){
        progressBar.setVisible(false);
        tab.setVisible(true);

        owners.setContent(createOwnersContent());
        authors.setContent(createAuthorsContent());
        books.setContent(createBooksContent());
    }

    private FileChooser fileChooser() {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle(resourceBundle.getString("chooseFile"));

        File defaultDirectory = new File(Paths.get(".").toAbsolutePath().normalize().toString());
        fileChooser.setInitialDirectory(defaultDirectory);

        return fileChooser;
    }

    private void validate() throws SAXException, JAXBException {
        Validator.validate(xsdPath, xmlRoot);
    }

    private void save(String path) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Root.class);
        Marshaller marshaller = context.createMarshaller();

        marshaller.marshal(xmlRoot, new File(path));
    }


}
