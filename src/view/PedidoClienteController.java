package view;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PedidoClienteController implements Initializable {
    @FXML
    private Button jbtnVoltar;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void voltar() throws IOException {
        Stage fecha = (Stage) jbtnVoltar.getScene().getWindow();
        fecha.close();
        Parent voltar = FXMLLoader.load(getClass().getResource("/view/TelaInicialCliente.fxml"));
        Scene scene = new Scene(voltar);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    public void enviar(){

    }
}
