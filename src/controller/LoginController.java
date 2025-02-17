package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.bo.AdministradorBO;
import model.bo.AtendenteBO;
import model.bo.ClienteBO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button jbtnLogin; // Botão para realizar o login
    @FXML
    private Button jbtnVoltar; // Botão para retornar à tela inicial (apenas para clientes)
    @FXML
    private PasswordField jtfSenha; // Campo de senha para o usuário
    @FXML
    private TextField jtfEmail; // Campo de texto para o email do usuário

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Torna o botão "Voltar" invisível caso o usuário logado não seja um cliente
        if (TrocadorTelas.getUsuario() != Usuario.CLIENTE) {
            jbtnVoltar.setVisible(false);
        }
    }

    public void Voltar() throws IOException {
        // Retorna à tela inicial do cliente
        TrocadorTelas.TrocarTela("/view/TelaInicialCliente.fxml", (Stage) jbtnVoltar.getScene().getWindow());
    }

    public void Logar() throws IOException, SQLException, ClassNotFoundException {
        // Verifica se os campos de email e senha estão preenchidos
        if (jtfEmail.getText().isEmpty() || jtfSenha.getText().isEmpty()) {
            AlertaErro(); // Exibe uma mensagem de erro se estiverem vazios
            throw new IOException("Email ou senha Invalido");
        }

        // Verifica o tipo de usuário logado e realiza as operações de login correspondentes
        if (TrocadorTelas.getUsuario() == Usuario.CLIENTE) {
            // Realiza login de um cliente e redireciona para a tela correspondente
            if (ClienteBO.loginCliente(jtfEmail.getText(), jtfSenha.getText())) {
                ClienteBO.setEmailLogado(jtfEmail.getText()); // Registra o email do cliente logado
                TrocadorTelas.TrocarTela("/view/ClienteLogado.fxml", (Stage) jbtnLogin.getScene().getWindow());
            }
        } else if (TrocadorTelas.getUsuario() == Usuario.ADMINISTRADOR) {
            // Realiza login de um administrador e redireciona para a tela correspondente
            if (AdministradorBO.loginAdministrador(jtfEmail.getText(), jtfSenha.getText())) {
                TrocadorTelas.TrocarTela("/view/AdmLogado.fxml", (Stage) jbtnLogin.getScene().getWindow());
            }
        } else {
            // Realiza login de um atendente e redireciona para a tela de pedidos
            if (AtendenteBO.loginAtendente(jtfEmail.getText(), jtfSenha.getText())) {
                AtendenteBO.setEmailLogado(jtfEmail.getText()); // Registra o email do atendente logado
                TrocadorTelas.TrocarTela("/view/PedidosAtuais.fxml", (Stage) jbtnLogin.getScene().getWindow());
            }
        }
    }

    public void AlertaErro() {
        // Exibe uma mensagem de erro alertando que os campos de email e senha devem ser preenchidos
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro"); // Título do alerta
        alert.setHeaderText("Um erro ocorreu"); // Cabeçalho do alerta
        alert.setContentText("Os campos devem ser preenchidos!"); // Conteúdo do alerta
        alert.showAndWait(); // Exibe a mensagem de erro e aguarda interação do usuário
    }
}