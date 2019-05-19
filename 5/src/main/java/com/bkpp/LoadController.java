package com.bkpp;

import com.bkpp.model.Root;
import com.bkpp.utils.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoadController implements Initializable {
    @FXML
    Label xsdPath;
    @FXML
    Label xmlPath;

    private ResourceBundle resourceBundle;
    private String xsdAbsolutePath;
    private String xmlAbsolutePath;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    public void startXmlViewer(ActionEvent actionEvent) {
        try {
            validate();
            start();
        } catch (IOException | SAXException | JAXBException e){
            showErrorDialog(e);
        }
    }

    private void validate() throws SAXException, JAXBException {
        Validator.validate(xsdAbsolutePath, getXmlRoot());
    }

    private void start() throws IOException, JAXBException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/xml_viewer.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.XMLReaderBundle"));

        Parent root = fxmlLoader.load();
        XmlViewerController controller = fxmlLoader.getController();

        controller.setXmlPath(xmlAbsolutePath);
        controller.setXsdPath(xsdAbsolutePath);
        controller.setXmlRoot(getXmlRoot());
        Scene scene = new Scene(root);

        Main.getPrimaryStage().setScene(scene);

        Main.getPrimaryStage().show();
    }

    private Root getXmlRoot() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Root.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        return (Root) unmarshaller.unmarshal(new File(xmlAbsolutePath));
    }

    private void showErrorDialog(Exception exception) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(resourceBundle.getString("validationError"));
        alert.setHeaderText(resourceBundle.getString("validationInfo"));


        Label label = new Label(resourceBundle.getString("stacktrace"));

        TextArea textArea = new TextArea(exception.getMessage());
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }

    public void loadXSD(ActionEvent actionEvent) {
        FileChooser fileChooser = fileChooser();

        Optional<File> file = Optional.ofNullable(fileChooser.showOpenDialog(Main.getPrimaryStage()));

        file.ifPresent(f -> {
            xsdPath.setText(f.getName());
            xsdAbsolutePath = f.getAbsolutePath();
        });
    }

    public void loadXML(ActionEvent actionEvent) {
        FileChooser fileChooser = fileChooser();

        Optional<File> file = Optional.ofNullable(fileChooser.showOpenDialog(Main.getPrimaryStage()));

        file.ifPresent(f -> {
            xmlPath.setText(f.getName());
            xmlAbsolutePath = f.getAbsolutePath();
        });
    }

    private FileChooser fileChooser() {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle(resourceBundle.getString("chooseFile"));

        File defaultDirectory = new File(Paths.get(".").toAbsolutePath().normalize().toString());
        fileChooser.setInitialDirectory(defaultDirectory);

        return fileChooser;
    }
}
