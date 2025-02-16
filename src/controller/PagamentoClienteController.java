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

public class PagamentoClienteController implements Initializable {

    @FXML
    private Button jbtnVoltar;
    @FXML
    private Label valor;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setValor();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void Voltar() throws IOException {

        TrocadorTelas.TrocarTela("/view/ClienteLogado.fxml", (Stage) jbtnVoltar.getScene().getWindow());

    }

    public void setValor() throws SQLException, ClassNotFoundException {
        valor.setText(String.valueOf(PedidoBO.getValor()));

    }

}
