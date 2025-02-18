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
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class FuncionarioController implements Initializable {
    @FXML
    private Button jbtnVoltar; // Botão para voltar à tela anterior
    @FXML
    private Button jbtnCadastrarFuncionario; // Botão para acessar a tela de cadastro de funcionário
    @FXML
    private ScrollPane scrollPane; // Área de rolagem para exibir informações
    private VBox verBox = new VBox(); // Contêiner vertical para agrupar elementos da interface
    private Map<Integer, Atendente> atendentes = new HashMap<>(); // Mapeamento de ID e objetos Atendente
    private ChoiceBox<Status> status = new ChoiceBox<>(); // Dropdown para exibir/selecionar status de um atendente

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scrollPane.setContent(verBox); // Define o conteúdo do ScrollPane como verBox
        status.getItems().addAll(Status.values()); // Adiciona os valores possíveis do enum Status no ChoiceBox

        try {
            // Busca lista de atendentes usando AtendenteBO.listarAtendentes()
            System.out.println("Tentando listar os funcionarios com a função ATENDENTEBO.ListarAtendentes()");
            atendentes = AtendenteBO.listarAtendentes();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e); // Lança exceção em caso de erro
        }

        // Itera sobre os atendentes e os adiciona na interface
        atendentes.forEach((key, value) -> {
            try {
                System.out.println("Tentando listar os clientes na tela");
                ListarAtendentes(key, value); // Atualiza a interface com os dados dos atendentes
            } catch (IOException e) {
                throw new RuntimeException(e); // Lança exceção em caso de erro
            }
        });
    }

    public void Voltar() throws IOException {
        // Troca a tela atual pela tela de "AdmLogado.fxml"
        TrocadorTelas.TrocarTela("/view/AdmLogado.fxml", (Stage) jbtnVoltar.getScene().getWindow());
    }

    public void AcessarCadastrarFuncionario() throws IOException {
        // Troca a tela atual pela tela de "Cadastrar.fxml"
        TrocadorTelas.TrocarTela("/view/Cadastrar.fxml", (Stage) jbtnCadastrarFuncionario.getScene().getWindow());
    }

    public void ListarAtendentes(Integer ID_Atendente, Atendente atendente) throws IOException {
        System.out.println("Chegou no listar atendentes()");

        // Cria um HBox (linha horizontal) para exibir os dados do atendente
        HBox horBox = new HBox();
        horBox.setSpacing(15); // Espaçamento entre os elementos no HBox
        horBox.setPadding(new Insets(10, 10, 10, 10)); // Margem interna do HBox

        // Criação de labels para exibir informações do atendente
        Label lnome = new Label("Nome: " + atendente.getNome());
        Label lnascimento = new Label("Nascimento: " + atendente.getNascimento());
        Label lsexo = new Label("Sexo: " + atendente.getSexo());
        Label lcpf = new Label("CPF: " + atendente.getCpf());
        Label ltelefone = new Label("Telefone: " + atendente.getTelefone());
        Label lemail = new Label("Email: " + atendente.getEmail());
        Label lsenha = new Label("Senha: ********"); // Oculta a senha
        Label lstatus = new Label("Status: " + atendente.getStatus());

        // Define o status atual do atendente no ChoiceBox
        status.setValue(atendente.getStatus());

        // Botões para editar, salvar e cancelar alterações
        Button editar = new Button("Editar");
        Button salvar = new Button("Salvar");
        Button cancelar = new Button("Cancelar");

        // Adiciona os elementos na interface
        verBox.getChildren().add(horBox);
        horBox.getChildren().addAll(lnome, lnascimento, lsexo, lcpf, ltelefone, lemail, lsenha, lstatus, editar);

        // Criando rótulos e campos de texto para edição
        Label lxnome = new Label("Nome: ");
        Label lxnascimento = new Label("Nascimento: ");
        Label lxsexo = new Label("Sexo: ");
        Label lxcpf = new Label("CPF: ");
        Label lxtelefone = new Label("Telefone: ");
        Label lxemail = new Label("Email: ");
        Label lxsenha = new Label("Senha: ");
        Label lxstatus = new Label("Status: ");

        TextField nome = new TextField();
        TextField nascimento = new TextField();
        TextField cpf = new TextField();
        TextField telefone = new TextField();
        TextField email = new TextField();
        TextField senha = new TextField();

        // Preenche os campos com os dados atuais do atendente
        nome.setText(atendente.getNome());

        // Definindo o formato desejado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = atendente.getNascimento().format(formatter);
        nascimento.setText(dataFormatada);

        //Formatando o cpf
        String cpfformatado = atendente.getCpf().replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        cpf.setText(cpfformatado);

        //Formatando o telefone
        String telefoneformatado = atendente.getTelefone().replaceAll("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3");
        telefone.setText(atendente.getTelefone());
        email.setText(atendente.getEmail());
        senha.setText(atendente.getSenha());

        // Aplica máscaras aos campos de texto (formatação e validação)
        MascarasFX.mascaraTelefone(telefone);
        MascarasFX.mascaraEmail(email);
        MascarasFX.mascaraData(nascimento);
        MascarasFX.mascaraCPF(cpf);

        // Ação do botão "Editar" para entrar no modo de edição
        editar.setOnAction(event -> {
            horBox.getChildren().clear(); // Limpa o conteúdo do HBox
            horBox.getChildren().addAll(lxnome, nome, lxnascimento, nascimento, lxcpf, cpf, lxtelefone, telefone, lxemail, email, lxsenha, senha, lxstatus, status, cancelar, salvar);
        });

        // Ação do botão "Cancelar" para retornar ao modo de visualização
        cancelar.setOnAction(event -> {
            horBox.getChildren().clear(); // Limpa o conteúdo do HBox
            horBox.getChildren().addAll(lnome, lnascimento, lsexo, lcpf, ltelefone, lemail, lsenha, lstatus, editar);
        });

        // Ação do botão "Salvar" para validar os campos e salvar as alterações
        salvar.setOnAction(event -> {
            // Validação dos campos de entrada
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
                    throw new RuntimeException(e); // Lança exceção em caso de erro na validação
                }
            }

            // Atualiza os dados do atendente com as informações do formulário
            atendente.setNome(nome.getText());
            atendente.setNascimento(LocalDate.of(
                    Integer.parseInt(nascimento.getText(6, 10)),
                    Integer.parseInt(nascimento.getText(3, 5)),
                    Integer.parseInt(nascimento.getText(0, 2))));
            atendente.setEmail(email.getText());
            atendente.setSenha(senha.getText());
            atendente.setStatus(status.getValue());
            atendente.setCpf(cpf.getText(0, 3) + cpf.getText(4, 7) + cpf.getText(8, 11) + cpf.getText(12, 14));
            atendente.setTelefone(telefone.getText(1, 3) + telefone.getText(4, 9) + telefone.getText(10, 14));

            try {
                // Salva alterações no banco de dados usando AtendenteBO.AlterarAtendente()
                System.out.println("Acessando alterar atendente de AtendenteBO");
                AtendenteBO.AlterarAtendente(ID_Atendente, atendente);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e); // Lança exceção em caso de erro no salvamento
            }

            // Reinicia a tela funcionario
            try {
                TrocadorTelas.TrocarTela("/view/Funcionario.fxml", (Stage) salvar.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}