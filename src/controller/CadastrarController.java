package controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lib.MascarasFX;
import model.bo.ClienteBO;
import model.dao.ClienteDAO;
import model.vo.Cliente;
import model.vo.Sexo;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
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
    private ChoiceBox<Sexo> jcbSexo;
    @FXML
    private Button jbtnVoltar;
    @FXML
    private Button jbtnCadastrar;



    @Override
    public void initialize(URL url, ResourceBundle rb) {

        jcbSexo.getItems().addAll(Sexo.MASCULINO, Sexo.FEMININO);


        MascarasFX.mascaraTelefone(jtfCelular);
        MascarasFX.mascaraEmail(jtfEmail);
        MascarasFX.mascaraCPF(jtfCPF);
        MascarasFX.mascaraData(jtfDN);
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


    public void CadastrarCliente() throws IOException, SQLException, ClassNotFoundException {

        if (jtfEmail.getText().isEmpty() ||
                jpfSenha.getText().isEmpty() ||
                jtfCelular.getText().isEmpty() ||
                jtfDN.getText().isEmpty() ||
                jtfNome.getText().isEmpty() ||
                jtfCPF.getText().isEmpty() ||
                jcbSexo.getValue() == null ||
                !jtfEmail.getText().matches(".*@(gmail\\.com|hotmail\\.com|outlook\\.com|yahoot\\.com)$") ||
                !jpfSenha.getText().matches("^(?=.*\\d).{8,}$") ||
                !jtfCelular.getText().matches("\\(\\d{2}\\)\\d{5}-\\d{4}") ||
                !jtfDN.getText().matches("\\d{2}/\\d{2}/\\d{4}") ||
                !jtfCPF.getText().matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {

            AlertaErro();
            throw new IOException("Algum Campo Inválido");

        } else {

            if (TrocadorTelas.getUsuario() == Usuario.CLIENTE) {

                TrocadorTelas.TrocarTela("/view/TelaInicialCliente.fxml", (Stage) jbtnCadastrar.getScene().getWindow());


                Cliente cliente = new Cliente(jtfNome.getText(), LocalDate.of(Integer.parseInt(jtfDN.getText(6, 10)), Integer.parseInt(jtfDN.getText(3, 5)),
                        Integer.parseInt(jtfDN.getText(0, 2))), jtfEmail.getText(), jcbSexo.getValue(), jtfCPF.getText(0,3) + jtfCPF.getText(4, 7) + jtfCPF.getText(8, 11) + jtfCPF.getText(12, 14),
                        jtfCelular.getText(1,3) + jtfCelular.getText(4, 9) + jtfCelular.getText(10, 14),
                        jpfSenha.getText());

                ClienteBO.cadastrarCliente(cliente);

            } else {



                TrocadorTelas.TrocarTela("/view/Funcionario.fxml", (Stage) jbtnCadastrar.getScene().getWindow());
            }
        }
    }

}
