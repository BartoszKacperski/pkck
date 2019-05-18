package com.bkpp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.setTitle("PKCS Bartosz Kacperski & Paweł Pomarański");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) throws JAXBException {
        launch(args);
        /*JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        Root root = (Root) unmarshaller.unmarshal(new File("BookStore.xml"));

        root.getBookStore().getAuthors().forEach(System.out::println);
        root.getBookStore().getBooks().forEach(System.out::println);
        root.getOwners().forEach(System.out::println);*/
    }
}
