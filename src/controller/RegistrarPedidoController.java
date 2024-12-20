package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrarPedidoController implements Initializable {

    @FXML
    private Button jbtnVoltar;
    @FXML
    private TextField jtfCliente;
    @FXML
    private Button jbtnEscolherPedidos;
    @FXML
    private Button jbtnCadastrar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void Voltar() throws IOException {
        TrocadorTelas.TrocarTela("/view/PedidosAtuais.fxml", (Stage) jbtnVoltar.getScene().getWindow());

    }

    public void CadastrarPedido() throws IOException {
        if (jtfCliente.getText().isEmpty()) {
            AlertaErro();
            throw new IOException("Erro ao cadastrar");
        }
    }

    public void EscolherPedido() throws IOException {
        TrocadorTelas.TrocarTela("/view/PedidoCliente.fxml", (Stage) jbtnEscolherPedidos.getScene().getWindow());

    }
    public void AlertaErro() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Um erro ocorreu");
        alert.setContentText("Os campos devem ser preenchidos!");
        alert.showAndWait();
    }


}