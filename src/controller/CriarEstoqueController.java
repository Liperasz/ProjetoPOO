package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lib.MascarasFX;
import model.bo.EstoqueBO;
import model.bo.IngredienteBO;
import model.dao.IngredienteDAO;
import model.vo.Estoque;
import model.vo.Ingrediente;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CriarEstoqueController implements Initializable { // Classe que controla a criação de estoque e implementa a interface Initializable para inicializar componentes

    @FXML
    private ChoiceBox<String> jcbEscolherIngrediente; // Combobox para selecionar o ingrediente
    @FXML
    private TextField jtfQuantidade; // Campo de texto para informar a quantidade
    @FXML
    private TextField jtfValidade; // Campo de texto para informar a validade (formato de data)
    @FXML
    private TextField jtfLote; // Campo de texto para informar o lote do ingrediente
    @FXML
    private Button jbtnAddIngrediente; // Botão para adicionar um novo ingrediente
    @FXML
    private Button jbtnConcluirEstoque; // Botão para concluir a criação de estoque
    @FXML
    private Button jbtnVoltar; // Botão para voltar à tela anterior

    @Override
    public void initialize(URL url, ResourceBundle rb) { // Método chamado automaticamente para inicializar a interface gráfica
        try {
            // Preenche o ChoiceBox com os ingredientes disponíveis através do método ListarIngrediente da classe EstoqueBO
            jcbEscolherIngrediente.getItems().addAll(EstoqueBO.ListarIngrediente());
        } catch (SQLException e) { // Tratamento de erro para SQL
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) { // Tratamento de erro de classe não encontrada
            throw new RuntimeException(e);
        }

        // Aplica máscara de entrada no campo de validade
        MascarasFX.mascaraData(jtfValidade);
    }

    public void Voltar() throws IOException { // Método chamado ao acionar o botão de voltar
        TrocadorTelas.TrocarTela("/view/Estoque.fxml", (Stage) jbtnVoltar.getScene().getWindow()); // Troca a tela atual para a tela de Estoque
    }

    public void AdicionarIngrediente() { // Método para abrir uma nova janela e adicionar um novo ingrediente
        VBox layout = new VBox(); // Layout vertical
        Label Nome = new Label("Nome do Ingrediente: "); // Rótulo para o campo de texto
        TextField NomeIngrediente = new TextField(); // Campo de texto para o nome do ingrediente
        Button concluir = new Button("Concluir"); // Botão de conclusão

        // Adiciona os componentes ao layout
        layout.getChildren().addAll(Nome, NomeIngrediente, concluir);

        Scene scene = new Scene(layout, 300, 300); // Cria a cena com o layout definido e tamanho 300x300
        Stage stage = new Stage(); // Cria uma nova janela (Stage)
        stage.setScene(scene); // Define a cena na janela
        stage.show(); // Exibe a janela

        // Define ação do botão Concluir
        concluir.setOnAction(e -> {
            try {
                // Chama o método para criar o ingrediente e fecha a janela
                CriarIngrediente(NomeIngrediente);
                stage.close();
            } catch (IOException | SQLException | ClassNotFoundException ex) { // Tratamento de exceções em caso de erro
                System.out.println("Erro ao criar ingrediente: " + ex.getMessage());
            }
        });
    }

    public void ConcluirEstoque() throws IOException, SQLException, ClassNotFoundException { // Método para validar os campos e salvar o novo estoque
        // Verifica se os campos estão vazios ou com valores inválidos
        if (jcbEscolherIngrediente.getValue() == null || jtfLote.getText().isEmpty() || jtfQuantidade.getText().isEmpty()
                || jtfValidade.getText().isEmpty() || !jtfValidade.getText().matches("\\d{2}/\\d{2}/\\d{4}")) {
            AlertaErro(); // Mostra mensagem de erro se os campos estão inválidos
            throw new IOException("Os campos não foram devidamente preenchidos"); // Lança exceção
        }

        // Cria um objeto Ingrediente com o valor selecionado no ChoiceBox
        Ingrediente i = new Ingrediente(jcbEscolherIngrediente.getValue());

        // Cria o objeto Estoque com os valores fornecidos nos campos
        Estoque estoque = new Estoque(
                i,
                LocalDate.of(
                        Integer.parseInt(jtfValidade.getText(6, 10)), // Ano
                        Integer.parseInt(jtfValidade.getText(3, 5)), // Mês
                        Integer.parseInt(jtfValidade.getText(0, 2))  // Dia
                ),
                Integer.parseInt(jtfQuantidade.getText()), // Quantidade
                Integer.parseInt(jtfLote.getText()) // Lote
        );

        EstoqueBO.CriarEstoque(estoque); // Salva o objeto Estoque usando a lógica de negócios (BO)

        // Reinicia a tela Criar Estoque
        TrocadorTelas.TrocarTela("/view/CriarEstoque.fxml", (Stage) jbtnConcluirEstoque.getScene().getWindow());    }

    public void AlertaErro() { // Método para exibir alerta de erro ao usuário
        Alert alert = new Alert(Alert.AlertType.ERROR); // Criação da caixa de alerta de erro
        alert.setTitle("Erro"); // Título da janela de alerta
        alert.setHeaderText("Um erro ocorreu"); // Cabeçalho do alerta
        alert.setContentText("Os campos devem ser preenchidos!"); // Mensagem informando o erro
        alert.showAndWait(); // Exibe o alerta e aguarda interação do usuário
    }

    public void CriarIngrediente(TextField NomeIngrediente) throws IOException, SQLException, ClassNotFoundException { // Método para criar ingrediente
        if (NomeIngrediente.getText().isEmpty()) { // Verifica se o campo está vazio
            AlertaErro(); // Mostra mensagem de erro se o campo está vazio
            throw new IOException("Os campos não foram devidamente preenchidos"); // Lança exceção
        }

        // Cria um novo objeto Ingrediente com o nome informado
        Ingrediente ingrediente = new Ingrediente(NomeIngrediente.getText());
        IngredienteBO.CriarIngrediente(ingrediente); // Salva o ingrediente usando a lógica de negócios (BO)
    }
}