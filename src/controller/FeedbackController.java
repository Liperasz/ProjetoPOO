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
    private Button jbtnVoltar;
    @FXML
    private ScrollPane scrollpane;

    private VBox verBox = new VBox();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        scrollpane.setContent(verBox);

        Map<Integer, Feedback> listaFeedbacks = new HashMap<>();
        try {
            listaFeedbacks = FeedBackBO.BuscarFeedbacs();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        listaFeedbacks.forEach((key, value) -> {

            try {
                ListarFeedback(key, value);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    public void Voltar() throws IOException {
        TrocadorTelas.TrocarTela("/view/Relatorio.fxml", (Stage) jbtnVoltar.getScene().getWindow());

    }

    public void ListarFeedback(Integer id_feedback, Feedback feedback) throws IOException {

        HBox horBox = new HBox();
        horBox.setSpacing(15);
        horBox.setPadding(new Insets(10, 10, 10, 10));

        Label Feedback = new Label("Comentario: " + feedback.getFeedback());
        Label Horario = new Label("Horario: " + String.valueOf(Timestamp.valueOf(feedback.getFeedbackTime())));
        Label id = new Label("ID: " + String.valueOf(id_feedback));

        horBox.getChildren().addAll(id, Feedback, Horario);
        verBox.getChildren().add(horBox);
    }
}
