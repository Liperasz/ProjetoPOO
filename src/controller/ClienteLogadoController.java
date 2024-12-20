package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClienteLogadoController implements Initializable {

    @FXML
    private Button jbtnVoltar;
    @FXML
    private Button jbtnFazerPedido;
    @FXML
    private Button jbtnHistorico;

    @Override
    public void initialize (URL url, ResourceBundle rb) {

    }

    public void acessarFazerPedido() throws IOException {

        TrocadorTelas.TrocarTela("/view/PedidoCliente.fxml", (Stage) jbtnFazerPedido.getScene().getWindow());

    }

    public void acessarHistorico() throws IOException {

        TrocadorTelas.TrocarTela("/view/HistoricoCliente.fxml", (Stage) jbtnHistorico.getScene().getWindow());

    }

    public void Voltar() throws IOException {

        TrocadorTelas.TrocarTela("/view/TelaInicialCliente.fxml", (Stage) jbtnVoltar.getScene().getWindow());

    }


}
