package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PagamentoClienteController implements Initializable {

    @FXML
    private Button jbtnVoltar;
    @FXML
    private TextField jtfValor;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void Voltar() throws IOException {

        TrocadorTelas.TrocarTela("/view/TelaInicialCliente.fxml", (Stage) jbtnVoltar.getScene().getWindow());

    }

}
