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
    private Root xmlRoot;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    public void tryToContinue() {
        if(xsdAbsolutePath != null && xmlAbsolutePath != null){
            try {
                loadXmlRoot();
                validate();
                startXmlViewer();
            } catch (IOException e) {
                showErrorDialog(resourceBundle.getString("errorTitle"),
                                resourceBundle.getString("errorHeader"),
                                resourceBundle.getString("fxmlLoaderError"));
            } catch (JAXBException | SAXException e) {
                showErrorDialog(resourceBundle.getString("errorTitle"),
                                resourceBundle.getString("errorHeader"),
                                e.getMessage());
            }
        }
    }

    private void validate() throws SAXException, JAXBException {
        Validator.validate(xsdAbsolutePath, xmlRoot);
    }

    private void startXmlViewer() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/xml_viewer.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.XMLReaderBundle"));

        Parent root = fxmlLoader.load();
        XmlViewerController controller = fxmlLoader.getController();

        controller.setXmlPath(xmlAbsolutePath);
        controller.setXsdPath(xsdAbsolutePath);
        controller.setXmlRoot(xmlRoot);
        Scene scene = new Scene(root);

        Main.getPrimaryStage().setScene(scene);

        Main.getPrimaryStage().show();
    }

    private void loadXmlRoot() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Root.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        xmlRoot = (Root) unmarshaller.unmarshal(new File(xmlAbsolutePath));
    }

    private void showErrorDialog(String title,
                                String header,
                                String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public void loadXSDPath() {
        FileChooser fileChooser = fileChooser(new FileChooser.ExtensionFilter("XML Schema","*.xsd"));

        Optional<File> file = Optional.ofNullable(fileChooser.showOpenDialog(Main.getPrimaryStage()));

        file.ifPresent(f -> {
            xsdPath.setText(f.getName());
            xsdAbsolutePath = f.getAbsolutePath();
        });
    }

    public void loadXMLPath() {
        FileChooser fileChooser = fileChooser(new FileChooser.ExtensionFilter("XML","*.xml"));

        Optional<File> file = Optional.ofNullable(fileChooser.showOpenDialog(Main.getPrimaryStage()));

        file.ifPresent(f -> {
            xmlPath.setText(f.getName());
            xmlAbsolutePath = f.getAbsolutePath();
        });
    }

    private FileChooser fileChooser(FileChooser.ExtensionFilter extensionFilter) {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle(resourceBundle.getString("chooseFile"));
        fileChooser.getExtensionFilters().add(extensionFilter);

        File defaultDirectory = new File(Paths.get(".").toAbsolutePath().normalize().toString());
        fileChooser.setInitialDirectory(defaultDirectory);

        return fileChooser;
    }
}
