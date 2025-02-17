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
    private Button jbtnPedido; // Botão para acessar a tela de pedidos
    @FXML
    private Button jbtnCardapio; // Botão para acessar a tela de cardápio
    @FXML
    private Button jbtnEstoque; // Botão para acessar a tela de estoque
    @FXML
    private Button jbtnRelatorio; // Botão para acessar a tela de relatórios
    @FXML
    private Button jbtnFuncionario; // Botão para acessar a tela de funcionários
    @FXML
    private Button jbtnSair; // Botão para retornar à tela de login

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Método vazio, pode ser utilizado para configurações iniciais da tela
    }

    // Métodos que realizam a troca de telas com base na ação do usuário
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
