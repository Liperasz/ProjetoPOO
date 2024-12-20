package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private TextField jtfValor;
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

    }

    public void EscolherPedido() throws IOException {
        TrocadorTelas.TrocarTela("/view/PedidoCliente.fxml", (Stage) jbtnEscolherPedidos.getScene().getWindow());

    }

    public void getCliente() {

    }

    @FXML
    public void getValor() {

    }
}
