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
        TrocadorTelas.TrocarTela("/view/TelaInicialCliente.fxml", (Stage) jbtnVoltar.getScene().getWindow());
    }

    public void CadastrarCliente() throws IOException {

        TrocadorTelas.TrocarTela("/view/TelaInicialCliente.fxml", (Stage) jbtnCadastrar.getScene().getWindow());
    }

    public void DiaNascimento() throws IOException {

    }

    public void MesNascimento() throws IOException {

    }

    public void AnoNascimento() throws IOException {

    }

    public void getTelefone() {

    }

    public void getCPF() throws IOException {

    }

    public void setSexo() throws IOException {

    }
}
