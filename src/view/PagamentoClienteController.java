package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PagamentoClienteController implements Initializable {

    @FXML
    private Button jbtnVoltar;
    @FXML
    private TextField jtfValor;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void Voltar() throws IOException {
        Stage fecha = (Stage) jbtnVoltar.getScene().getWindow();
        fecha.close();
        Parent voltar = FXMLLoader.load(getClass().getResource("/view/TelaInicialCliente.fxml"));
        Scene scene = new Scene(voltar);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}
