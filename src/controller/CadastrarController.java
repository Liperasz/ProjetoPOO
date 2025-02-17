package controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lib.MascarasFX;
import model.bo.AtendenteBO;
import model.bo.ClienteBO;
import model.dao.ClienteDAO;
import model.vo.Atendente;
import model.vo.Cliente;
import model.vo.Sexo;
import model.vo.Status;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CadastrarController implements Initializable {
    // Definição dos campos correspondentes aos elementos de interface do usuário (FXML)
    @FXML
    private TextField jtfNome; // Campo de entrada para nome
    @FXML
    private TextField jtfEmail; // Campo de entrada para e-mail
    @FXML
    private TextField jtfCelular; // Campo de entrada para celular
    @FXML
    private PasswordField jpfSenha; // Campo de entrada para senha
    @FXML
    private TextField jtfCPF; // Campo de entrada para CPF
    @FXML
    private TextField jtfDN; // Campo de entrada para data de nascimento
    @FXML
    private ChoiceBox<Sexo> jcbSexo; // Elemento de escolha para o sexo
    @FXML
    private Button jbtnVoltar; // Botão para voltar
    @FXML
    private Button jbtnCadastrar; // Botão para cadastrar

    // Método chamado na inicialização do controlador
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Preenche o ChoiceBox de sexo com as opções disponíveis
        jcbSexo.getItems().addAll(Sexo.MASCULINO, Sexo.FEMININO);

        // Aplica máscaras de formato aos campos de entrada para validação
        MascarasFX.mascaraTelefone(jtfCelular); // Máscara para telefones
        MascarasFX.mascaraEmail(jtfEmail); // Máscara para e-mail
        MascarasFX.mascaraCPF(jtfCPF); // Máscara para CPF
        MascarasFX.mascaraData(jtfDN); // Máscara para data de nascimento
    }

    // Método para ação de voltar à tela anterior
    public void Voltar() throws IOException {
        // Verifica o tipo de usuário logado e redireciona para a tela correspondente
        if (TrocadorTelas.getUsuario() == Usuario.CLIENTE) {
            // Caso o usuário seja um cliente, direciona para a tela inicial do cliente
            TrocadorTelas.TrocarTela("/view/TelaInicialCliente.fxml", (Stage) jbtnVoltar.getScene().getWindow());
        } else {
            // Caso contrário, direciona para a tela funcional do atendente
            TrocadorTelas.TrocarTela("/view/Funcionario.fxml", (Stage) jbtnVoltar.getScene().getWindow());
        }
    }

    // Método para exibir uma mensagem de erro em um popup (alerta)
    public void AlertaErro() {
        Alert alert = new Alert(Alert.AlertType.ERROR); // Criação de um alerta do tipo ERRO
        alert.setTitle("Erro"); // Configura o título do alerta
        alert.setHeaderText("Um erro ocorreu"); // Configura o cabeçalho do alerta
        alert.setContentText("Os campos devem ser preenchidos!"); // Configura a mensagem do alerta
        alert.showAndWait(); // Exibe o alerta e aguarda a confirmação do usuário
    }

    // Método chamado ao clicar no botão de cadastrar
    public void CadastrarCliente() throws IOException, SQLException, ClassNotFoundException {
        // Verifica se algum campo está vazio ou contém um valor inválido
        if (jtfEmail.getText().isEmpty() || // Campo de e-mail vazio
                jpfSenha.getText().isEmpty() || // Campo de senha vazio
                jtfCelular.getText().isEmpty() || // Campo de celular vazio
                jtfDN.getText().isEmpty() || // Campo de data de nascimento vazio
                jtfNome.getText().isEmpty() || // Campo de nome vazio
                jtfCPF.getText().isEmpty() || // Campo de CPF vazio
                jcbSexo.getValue() == null || // Nenhuma opção de sexo selecionada
                !jtfEmail.getText().matches(".*@(gmail\\.com|hotmail\\.com|outlook\\.com|yahoot\\.com)$") || // Validação do formato do e-mail
                !jpfSenha.getText().matches("^(?=.*\\d).{8,}$") || // Senha necessita conter ao menos 8 caracteres e um número
                !jtfCelular.getText().matches("\\(\\d{2}\\)\\d{5}-\\d{4}") || // Formato de celular inválido
                !jtfDN.getText().matches("\\d{2}/\\d{2}/\\d{4}") || // Data de nascimento precisa seguir o formato DD/MM/AAAA
                !jtfCPF.getText().matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) { // CPF precisa estar no formato XXX.XXX.XXX-XX

            // Caso exista algum campo inválido, exibe um alerta de erro
            AlertaErro();
            throw new IOException("Algum Campo Inválido"); // Exceção para tratar o erro
        } else {
            // Se todos os campos forem válidos, verifica o tipo de usuário
            if (TrocadorTelas.getUsuario() == Usuario.CLIENTE) {
                // Criação de um objeto Cliente com os dados inseridos
                Cliente cliente = new Cliente(jtfNome.getText(),
                        LocalDate.of(Integer.parseInt(jtfDN.getText(6, 10)), // Ano
                                Integer.parseInt(jtfDN.getText(3, 5)), // Mês
                                Integer.parseInt(jtfDN.getText(0, 2))), // Dia
                        jtfEmail.getText(),
                        jcbSexo.getValue(),
                        jtfCPF.getText(0, 3) + jtfCPF.getText(4, 7) + jtfCPF.getText(8, 11) + jtfCPF.getText(12, 14), // CPF sem os separadores
                        jtfCelular.getText(1, 3) + jtfCelular.getText(4, 9) + jtfCelular.getText(10, 14), // Número do celular sem separadores
                        jpfSenha.getText());

                // Chama o método responsável por cadastrar o cliente no banco
                ClienteBO.cadastrarCliente(cliente);

                // Redireciona para a tela inicial do cliente
                TrocadorTelas.TrocarTela("/view/TelaInicialCliente.fxml", (Stage) jbtnCadastrar.getScene().getWindow());

            } else {
                // Criação de um objeto Atendente com os dados inseridos
                Atendente atendente = new Atendente(jtfNome.getText(),
                        LocalDate.of(Integer.parseInt(jtfDN.getText(6, 10)), // Ano
                                Integer.parseInt(jtfDN.getText(3, 5)), // Mês
                                Integer.parseInt(jtfDN.getText(0, 2))), // Dia
                        jtfEmail.getText(),
                        jcbSexo.getValue(),
                        jtfCPF.getText(0, 3) + jtfCPF.getText(4, 7) + jtfCPF.getText(8, 11) + jtfCPF.getText(12, 14), // CPF formatado
                        jtfCelular.getText(1, 3) + jtfCelular.getText(4, 9) + jtfCelular.getText(10, 14), // Celular formatado
                        jpfSenha.getText(),
                        Status.ATIVO); // Define o atendente como ativo

                // Chama o método para cadastrar o atendente no banco
                AtendenteBO.cadastrarAtendente(atendente);

                // Redireciona para a tela funcional do atendente
                TrocadorTelas.TrocarTela("/view/Funcionario.fxml", (Stage) jbtnCadastrar.getScene().getWindow());
            }
        }
    }
}