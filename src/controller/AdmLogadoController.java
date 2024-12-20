package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdmLogadoController implements Initializable {

    @FXML
    private Button jbtnPedido;
    @FXML
    private Button jbtnCardapio;
    @FXML
    private Button jbtnEstoque;
    @FXML
    private Button jbtnRelatorio;
    @FXML
    private Button jbtnFuncionario;
    @FXML
    private Button jbtnSair;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }


    public void AcessarPedidos() throws IOException {
        TrocadorTelas.TrocarTela("/view/PedidosAtuais.fxml", (Stage) jbtnPedido.getScene().getWindow());

    }

    public void AcessarCardapio() throws IOException {
        TrocadorTelas.TrocarTela("/view/Cardapio.fxml", (Stage) jbtnCardapio.getScene().getWindow());

    }

    public void AcessarEstoque() throws IOException {
        TrocadorTelas.TrocarTela("/view/Estoque.fxml", (Stage) jbtnEstoque.getScene().getWindow());

    }

    public void AcessarRelatorio() throws IOException {
        TrocadorTelas.TrocarTela("/view/Relatorio.fxml", (Stage) jbtnRelatorio.getScene().getWindow());

    }

    public void GerenciarFuncionario() throws IOException {
        TrocadorTelas.TrocarTela("/view/Funcionario.fxml", (Stage) jbtnFuncionario.getScene().getWindow());

    }

    public void Sair() throws IOException {
        TrocadorTelas.TrocarTela("/view/Login.fxml", (Stage) jbtnSair.getScene().getWindow());

    }
}
