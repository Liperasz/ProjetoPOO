package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class TrocadorTelas {

    private static Usuario usuario;

    private TrocadorTelas() {}

    public static void TrocarTela(String caminho, Stage fecha) throws IOException {
        fecha.close();
        Parent trocar = FXMLLoader.load(TrocadorTelas.class.getResource(caminho));
        Scene scene = new Scene(trocar);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Restaurante Camarada Camarão");
        stage.show();
    }

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        TrocadorTelas.usuario = usuario;
    }
}
