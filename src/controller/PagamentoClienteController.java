package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.bo.PedidoBO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

// Controlador para gerenciar a tela de pagamento do cliente.
// Implementa a interface Initializable para inicializar componentes FXML.
public class PagamentoClienteController implements Initializable {

    // Botão FXML usado para voltar para uma tela anterior.
    @FXML
    private Button jbtnVoltar;

    // Rótulo FXML que exibirá o valor do pagamento.
    @FXML
    private Label valor;

    // Método chamado automaticamente ao carregar a interface.
    // Inicializa o valor exibido no rótulo, tratando possíveis exceções de SQL e ClassNotFound.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setValor(); // Define o valor do pagamento no rótulo.
        } catch (SQLException e) {
            throw new RuntimeException(e); // Lança uma exceção de tempo de execução em caso de erro de SQL.
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e); // Lança uma exceção de tempo de execução em caso de erro de classe.
        }
    }

    // Método disparado para voltar à tela anterior.
    // Troca a tela atual pela tela "ClienteLogado.fxml".
    public void Voltar() throws IOException {
        TrocadorTelas.TrocarTela("/view/ClienteLogado.fxml", (Stage) jbtnVoltar.getScene().getWindow());
    }

    // Método que define o texto no rótulo "valor", obtendo-o do pedido.
    // Realiza uma operação de consulta ao obter o valor usando a classe PedidoBO.
    public void setValor() throws SQLException, ClassNotFoundException {
        valor.setText(String.valueOf(PedidoBO.getValor())); // Define o valor do rótulo para o valor obtido.
    }
}