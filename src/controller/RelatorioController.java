package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.bo.PedidoBO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RelatorioController implements Initializable {
    @FXML
    private Button jbtnVoltar; // Botão para voltar à tela anterior
    @FXML
    private ListView<String> jlvMaisVendaMes; // Lista de vendas mais realizadas no mês
    @FXML
    private ListView<String> jlvMaisVendaAno; // Lista de vendas mais realizadas no ano
    @FXML
    private ListView<String> jlvMnVendaMes; // Lista de vendas menos realizadas no mês
    @FXML
    private ListView<String> jlvMnVendaAno; // Lista de vendas menos realizadas no ano
    @FXML
    private HBox jhbMes; // Elemento gráfico agrupador para seções do mês
    @FXML
    private HBox jhbAno; // Elemento gráfico agrupador para seções do ano
    @FXML
    private Button jbtnHistoricoVenda; // Botão para acessar o histórico de vendas
    @FXML
    private Button jbtnFeedback; // Botão para acessar a seção de feedbacks

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialização da tela do controlador
        ArrayList<ArrayList<String>> relatorios = new ArrayList<>();
        try {
            // Obtém os relatórios através do PedidoBO
            relatorios = PedidoBO.ListarRelatorios();
        } catch (SQLException e) {
            throw new RuntimeException(e); // Trata exceção de SQL
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e); // Trata exceção de classe não encontrada
        }
        try {
            // Lista os relatórios na interface
            ListarRelatorios(relatorios);
        } catch (IOException e) {
            throw new RuntimeException(e); // Trata exceção de entrada/saída
        }
    }

    public void Voltar() throws IOException {
        // Troca de tela para a interface de administrador logado
        TrocadorTelas.TrocarTela("/view/AdmLogado.fxml", (Stage) jbtnVoltar.getScene().getWindow());
    }

    public void AcessarHistoricoVenda() throws IOException {
        // Troca de tela para visualizar o Histórico de Vendas
        TrocadorTelas.TrocarTela("/view/HistoricoVenda.fxml", (Stage) jbtnHistoricoVenda.getScene().getWindow());
    }

    public void AcessarFeedback() throws IOException {
        // Troca de tela para visualizar a seção de Feedback
        TrocadorTelas.TrocarTela("/view/Feedback.fxml", (Stage) jbtnFeedback.getScene().getWindow());
    }

    public void ListarRelatorios(ArrayList<ArrayList<String>> relatorios) throws IOException {
        // Lista os dados nos componentes gráficos de acordo com os relatórios recebidos
        jlvMaisVendaMes.getItems().setAll(relatorios.get(0)); // Relatórios de maior venda no mês
        jlvMaisVendaAno.getItems().setAll(relatorios.get(1)); // Relatórios de maior venda no ano
        jlvMnVendaMes.getItems().setAll(relatorios.get(2)); // Relatórios de menor venda no mês
        jlvMnVendaAno.getItems().setAll(relatorios.get(3)); // Relatórios de menor venda no ano
    }
}