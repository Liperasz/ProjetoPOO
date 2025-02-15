package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lib.MascarasFX;
import model.bo.AtendenteBO;
import model.vo.Atendente;
import model.vo.Funcionario;
import model.vo.Sexo;
import model.vo.Status;
import sun.security.util.Length;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class FuncionarioController implements Initializable {

    @FXML
    private Button jbtnVoltar;
    @FXML
    private Button jbtnCadastrarFuncionario;
    @FXML
    private Button jbtnSalvar;
    @FXML
    private ScrollPane scrollPane;

    private VBox verBox = new VBox();

    private Map<Integer, Atendente> atendentes = new HashMap<>();

    private ChoiceBox<Status> status = new ChoiceBox<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        scrollPane.setContent(verBox);
        status.getItems().addAll(Status.values());


        try {
            System.out.println("Tentando listar os funcionarios com a função ATENDENTEBO.ListarAtendentes()");
            atendentes = AtendenteBO.listarAtendentes();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        atendentes.forEach((key, value) -> {

            try {
                System.out.println("Tentando listar os clientes na tela");
                ListarAtendentes(key, value);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

    }


    public void Voltar() throws IOException {
        TrocadorTelas.TrocarTela("/view/AdmLogado.fxml", (Stage) jbtnVoltar.getScene().getWindow());

    }

    public void AcessarCadastrarFuncionario() throws IOException {
        TrocadorTelas.TrocarTela("/view/Cadastrar.fxml", (Stage) jbtnCadastrarFuncionario.getScene().getWindow());

    }

    public void ListarAtendentes(Integer ID_Atendente, Atendente atendente) throws IOException {

        System.out.println("Chegou no listar atendentes()");

        HBox horBox = new HBox();
        horBox.setSpacing(15);
        horBox.setPadding(new Insets(10, 10, 10, 10));

        Label lnome = new Label("Nome: " + atendente.getNome());
        Label lnascimento = new Label("Nascimento: " + atendente.getNascimento());
        Label lsexo = new Label("Sexo: " + atendente.getSexo());
        Label lcpf = new Label("CPF: " + atendente.getCpf());
        Label ltelefone = new Label("Telefone: " + atendente.getTelefone());
        Label lemail = new Label("Email: " + atendente.getEmail());
        Label lsenha = new Label("Senha: ********");
        Label lstatus = new Label("Status: " + atendente.getStatus());

        status.setValue(atendente.getStatus());
        Button editar = new Button("Editar");
        Button salvar = new Button("Salvar");
        Button cancelar = new Button("Cancelar");

        verBox.getChildren().add(horBox);
        horBox.getChildren().addAll(lnome, lnascimento, lsexo, lcpf, ltelefone, lemail, lsenha, lstatus, editar);

        TextField nome = new TextField();
        TextField nascimento = new TextField();
        TextField cpf = new TextField();
        TextField telefone = new TextField();
        TextField email = new TextField();
        TextField senha = new TextField();

        MascarasFX.mascaraTelefone(telefone);
        MascarasFX.mascaraEmail(email);
        MascarasFX.mascaraData(nascimento);
        MascarasFX.mascaraCPF(cpf);

        editar.setOnAction(event -> {

            horBox.getChildren().clear();
            horBox.getChildren().addAll(nome, nascimento, cpf, telefone, email, senha, status, cancelar, salvar);

        });

        cancelar.setOnAction(event -> {

            horBox.getChildren().clear();
            horBox.getChildren().addAll(lnome, lnascimento, lsexo, lcpf, ltelefone, lemail, lsenha, lstatus, editar);

        });

        salvar.setOnAction(event -> {

            if (email.getText().isEmpty() ||
                    senha.getText().isEmpty() ||
                    telefone.getText().isEmpty() ||
                    nascimento.getText().isEmpty() ||
                    nome.getText().isEmpty() ||
                    cpf.getText().isEmpty() ||
                    !email.getText().matches(".*@(gmail\\.com|hotmail\\.com|outlook\\.com|yahoot\\.com)$") ||
                    !senha.getText().matches("^(?=.*\\d).{8,}$") ||
                    !telefone.getText().matches("\\(\\d{2}\\)\\d{5}-\\d{4}") ||
                    !nascimento.getText().matches("\\d{2}/\\d{2}/\\d{4}") ||
                    !cpf.getText().matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {


                try {
                    throw new IOException("Algum Campo Inválido");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }

            atendente.setNome(nome.getText());
            atendente.setNascimento(LocalDate.of(Integer.parseInt(nascimento.getText(6, 10)), Integer.parseInt(nascimento.getText(3, 5)), Integer.parseInt(nascimento.getText(0, 2))));
            atendente.setEmail(email.getText());
            atendente.setSenha(senha.getText());
            atendente.setStatus(status.getValue());
            atendente.setCpf(cpf.getText(0,3) + cpf.getText(4, 7) + cpf.getText(8, 11) + cpf.getText(12, 14));
            atendente.setTelefone(telefone.getText(1,3) + telefone.getText(4, 9) + telefone.getText(10, 14));


            try {
                System.out.println("Acessando alterar atendente de AtendenteBO");
                AtendenteBO.AlterarAtendente(ID_Atendente, atendente);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });


    }
}
