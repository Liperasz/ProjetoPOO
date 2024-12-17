package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TesteCadastrarCliente extends Application {

    public static void main(String[] args) {
        launch(args);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("CadastrarCliente.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setTitle("Cadastro de Cliente");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
