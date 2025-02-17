package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// Classe que controla a interface de um cliente logado.
// Implementa o Initializable para inicializar componentes da interface gráfica.
public class ClienteLogadoController implements Initializable {

    // Botão para voltar à tela anterior.
    @FXML
    private Button jbtnVoltar;

    // Botão para acessar a tela de criação de pedidos.
    @FXML
    private Button jbtnFazerPedido;

    // Botão para acessar o histórico de pedidos do cliente.
    @FXML
    private Button jbtnHistorico;

    // Método que é executado automaticamente na inicialização da interface.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Nenhuma lógica específica de inicialização foi implementada.
    }

    // Navega para a tela de criação de pedidos do cliente.
    public void acessarFazerPedido() throws IOException {
        TrocadorTelas.TrocarTela("/view/PedidoCliente.fxml", (Stage) jbtnFazerPedido.getScene().getWindow());
    }

    // Navega para a tela de histórico de pedidos do cliente.
    public void acessarHistorico() throws IOException {
        TrocadorTelas.TrocarTela("/view/HistoricoCliente.fxml", (Stage) jbtnHistorico.getScene().getWindow());
    }

    // Retorna à tela inicial do cliente.
    public void Voltar() throws IOException {
        TrocadorTelas.TrocarTela("/view/TelaInicialCliente.fxml", (Stage) jbtnVoltar.getScene().getWindow());
    }
}