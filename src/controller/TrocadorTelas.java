package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class TrocadorTelas {

    private TrocadorTelas() {}

    public static void TrocarTela(String caminho, Stage fecha) throws IOException {
        fecha.close();
        Parent trocar = FXMLLoader.load(TrocadorTelas.class.getResource(caminho));
        Scene scene = new Scene(trocar);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
