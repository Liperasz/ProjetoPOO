package controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.vo.Comida;
import model.vo.Ingrediente;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PedidoClienteController implements Initializable {

    @FXML
    private Button jbtnVoltar;
    @FXML
    private Button jbtnEnviar;
    @FXML
    private ScrollPane scrollPane;

    private VBox verBox = new VBox();


    @Override
    public void initialize(URL location, ResourceBundle resources) {




    }

    public void Voltar() throws IOException {
        if (TrocadorTelas.getUsuario() == Usuario.ATENDENTE) {
            TrocadorTelas.TrocarTela("/view/RegistrarPedido.fxml", (Stage) jbtnVoltar.getScene().getWindow());
        } else {
            TrocadorTelas.TrocarTela("/view/ClienteLogado.fxml", (Stage) jbtnVoltar.getScene().getWindow());
        }
    }

    public void Enviar() throws IOException {
        if (TrocadorTelas.getUsuario() == Usuario.ATENDENTE) {
            TrocadorTelas.TrocarTela("/view/RegistrarPedido.fxml", (Stage) jbtnEnviar.getScene().getWindow());
        } else {
            TrocadorTelas.TrocarTela("/view/PagamentoCliente.fxml", (Stage) jbtnEnviar.getScene().getWindow());
        }
    }

    public void AlertaErro() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Um erro ocorreu");
        alert.setContentText("Os campos devem ser preenchidos!");
        alert.showAndWait();
    }

}
