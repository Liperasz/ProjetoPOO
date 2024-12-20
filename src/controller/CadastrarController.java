package controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.vo.Sexo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CadastrarController implements Initializable {

    @FXML
    private TextField jtfNome;
    @FXML
    private TextField jtfEmail;
    @FXML
    private TextField jtfCelular;
    @FXML
    private PasswordField jpfSenha;
    @FXML
    private TextField jtfCPF;
    @FXML
    private TextField jtfDN;
    @FXML
    private TextField jtfMN;
    @FXML
    private TextField jtfAN;
    @FXML
    private ChoiceBox<Sexo> jcbSexo;
    @FXML
    private Button jbtnVoltar;
    @FXML
    private Button jbtnCadastrar;



    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void Voltar() throws IOException {

        if (TrocadorTelas.getUsuario() == Usuario.CLIENTE) {
            TrocadorTelas.TrocarTela("/view/TelaInicialCliente.fxml", (Stage) jbtnVoltar.getScene().getWindow());
        } else {
            TrocadorTelas.TrocarTela("/view/Funcionario.fxml", (Stage) jbtnVoltar.getScene().getWindow());
        }
    }

    public void AlertaErro() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Um erro ocorreu");
        alert.setContentText("Os campos devem ser preenchidos!");
        alert.showAndWait();
    }


    public void CadastrarCliente() throws IOException {

        if (jtfEmail.getText().isEmpty() || jpfSenha.getText().isEmpty() || jtfCelular.getText().isEmpty() || jtfDN.getText().isEmpty() || jtfMN.getText().isEmpty() || jtfNome.getText().isEmpty() || jtfCPF.getText().isEmpty() || jtfAN.getText().isEmpty() || jcbSexo.getValue() == null) {
            AlertaErro();
            throw new IOException("Email ou senha Invalido");
        }


        if (TrocadorTelas.getUsuario() == Usuario.CLIENTE) {
            TrocadorTelas.TrocarTela("/view/TelaInicialCliente.fxml", (Stage) jbtnCadastrar.getScene().getWindow());
        } else {
            TrocadorTelas.TrocarTela("/view/Funcionario.fxml", (Stage) jbtnCadastrar.getScene().getWindow());
        }
    }

}
