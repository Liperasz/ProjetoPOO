package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.bo.FeedBackBO;
import model.vo.Feedback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class FeedbackController implements Initializable {
    @FXML
    private Button jbtnVoltar; // Botão para voltar à tela anterior
    @FXML
    private ScrollPane scrollpane; // Painel de rolagem para exibir os feedbacks
    private VBox verBox = new VBox(); // Container vertical para armazenar as caixas de feedback

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scrollpane.setContent(verBox); // Define o VBox como conteúdo do ScrollPane

        Map<Integer, Feedback> listaFeedbacks = new HashMap<>(); // Lista de feedbacks com ID associado
        try {
            listaFeedbacks = FeedBackBO.BuscarFeedbacs(); // Obtém os feedbacks do banco de dados
        } catch (SQLException e) {
            throw new RuntimeException(e); // Lança exceção em caso de falha no SQL
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e); // Lança exceção em caso de erro de classe não encontrada
        }

        // Itera sobre a lista de feedbacks e adiciona cada um à interface
        listaFeedbacks.forEach((key, value) -> {
            try {
                ListarFeedback(key, value); // Adiciona o feedback à interface
            } catch (IOException e) {
                throw new RuntimeException(e); // Lança exceção em caso de erro de I/O
            }
        });
    }

    // Método para voltar à tela anterior
    public void Voltar() throws IOException {
        TrocadorTelas.TrocarTela(
                "/view/Relatorio.fxml", // Caminho para a nova tela
                (Stage) jbtnVoltar.getScene().getWindow() // Obtém a janela atual
        );
    }

    // Método para exibir um feedback na interface
    public void ListarFeedback(Integer id_feedback, Feedback feedback) throws IOException {
        HBox horBox = new HBox(); // Container horizontal para exibir os detalhes do feedback
        horBox.setSpacing(15); // Define espaço entre os elementos
        horBox.setPadding(new Insets(10, 10, 10, 10)); // Define margem interna do container

        // Criação dos rótulos para exibição dos dados do feedback
        Label Feedback = new Label("Comentario: " + feedback.getFeedback()); // Comentário do feedback
        Label Horario = new Label("Horario: " + String.valueOf(Timestamp.valueOf(feedback.getFeedbackTime()))); // Horário do feedback
        Label id = new Label("ID: " + String.valueOf(id_feedback)); // ID do feedback

        // Adiciona os rótulos ao container horizontal
        horBox.getChildren().addAll(id, Feedback, Horario);

        // Adiciona o container à lista vertical
        verBox.getChildren().add(horBox);
    }
}