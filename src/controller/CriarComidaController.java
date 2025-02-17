package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lib.MascarasFX;
import model.bo.ComidaBO;
import model.bo.IngredienteBO;
import model.dao.ComidaDAO;
import model.vo.Comida;
import model.vo.Ingrediente;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class CriarComidaController implements Initializable {

    @FXML
    private Button jbtnVoltar; // Botão para voltar à tela anterior
    @FXML
    private Button jbtnConcluir; // Botão para concluir o cadastro
    @FXML
    private ScrollPane scrollPane; // Área de rolagem para exibição dinâmica de ingredientes
    @FXML
    private TextField jtfNome; // Campo para o nome da comida
    @FXML
    private TextField jtfDescricao; // Campo para a descrição da comida
    @FXML
    private TextField jtfValor; // Campo para o valor da comida

    private VBox verBox = new VBox(); // Layout vertical para exibir os ingredientes
    private Map<Integer, Integer> ingredientes = new HashMap<>(); // Lista de ingredientes e quantidades
    private Comida comida = new Comida(); // Objeto para representar a comida sendo criada

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Aplicar uma máscara para permitir apenas números no campo do valor
        MascarasFX.mascaraNumero(jtfValor);

        // Define a VBox no ScrollPane para exibir os ingredientes
        scrollPane.setContent(verBox);

        Map<Integer, Ingrediente> listaIngredientes = new HashMap<>();
        try {
            System.out.println("Criando lista de ingredientes");
            // Busca a lista de ingredientes do banco de dados
            listaIngredientes = ComidaBO.SelecionarIngredientes();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Para cada ingrediente na lista, adiciona ao layout
        listaIngredientes.forEach((key, value) -> {
            try {
                System.out.println("Criando ingrediente " + key);
                ListarIngredientes(key, value); // Adiciona ingrediente à interface
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        try {
            System.out.println("Tentando Criar comida");
            Concluir(ingredientes); // Configura evento de clique no botão "Concluir"
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para voltar à tela anterior
    public void Voltar() throws IOException {
        TrocadorTelas.TrocarTela("/view/Cardapio.fxml", (Stage) jbtnVoltar.getScene().getWindow());
    }

    // Método para concluir o cadastro de comida
    public void Concluir(Map<Integer, Integer> ingredientes) throws IOException {
        jbtnConcluir.setOnAction(event -> {
            // Verifica se os campos obrigatórios estão preenchidos
            if (jtfValor.getText().isEmpty() || jtfNome.getText().isEmpty() || jtfDescricao.getText().isEmpty()) {
                AlertaErro(); // Exibe mensagem de erro
                throw new RuntimeException("Erro ao cadastrar");
            }

            // Remove ingredientes cujo valor seja 0
            ingredientes.entrySet().removeIf(entry -> entry.getValue() == 0);

            System.out.println("Criando comida dentro da função concluir()");
            // Define as propriedades da comida com os valores dos campos
            comida.setNome(jtfNome.getText());
            comida.setDescricao(jtfDescricao.getText());
            comida.setPreco(Double.valueOf(jtfValor.getText()));

            try {
                System.out.println("Chamando a função ComidaBO.CadastrarComida()");
                // Cadastra a comida no banco de dados
                ComidaBO.CadastrarComida(comida, ingredientes);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            try {
                // Reinicia a tela criar comida
                TrocadorTelas.TrocarTela("/view/CriarComida.fxml", (Stage) jbtnConcluir.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    // Método para listar e gerenciar os ingredientes na interface
    public void ListarIngredientes(Integer ID_Ingrediente, Ingrediente ingrediente) throws IOException {
        HBox horBox = new HBox(); // Layout horizontal para um ingrediente
        horBox.setSpacing(15); // Espaçamento entre os elementos
        horBox.setPadding(new Insets(10, 10, 10, 10)); // Margens internas

        Label nome = new Label(ingrediente.getNome()); // Nome do ingrediente
        Button adicionar = new Button("Adicionar"); // Botão para adicionar quantidade
        Button remover = new Button("Remover"); // Botão para remover quantidade
        verBox.getChildren().add(horBox); // Adiciona o layout ao VBox principal

        final int[] contador = {0}; // Contador da quantidade do ingrediente
        Label quant = new Label("Quantidade: " + contador[0]); // Exibe a quantidade atual
        horBox.getChildren().addAll(nome, adicionar, remover, quant); // Adiciona os elementos ao HBox

        // Evento ao clicar no botão "Adicionar"
        adicionar.setOnAction(e -> {
            contador[0]++;
            quant.setText("Quantidade: " + contador[0]); // Atualiza o contador exibido
            ingredientes.put(ID_Ingrediente, contador[0]); // Atualiza o mapa de ingredientes
            System.out.println("ID = " + ID_Ingrediente + " Quantidade na lista " + ingredientes.get(ID_Ingrediente) + " Quantidade = " + contador[0]);
        });

        // Evento ao clicar no botão "Remover"
        remover.setOnAction(e -> {
            if (contador[0] > 0) { // Decrementa somente se houver quantidade
                contador[0]--;
                quant.setText("Quantidade: " + contador[0]); // Atualiza o contador exibido
                ingredientes.put(ID_Ingrediente, contador[0]); // Atualiza o mapa de ingredientes
            }
            System.out.println("ID = " + ID_Ingrediente + " Quantidade na lista " + ingredientes.get(ID_Ingrediente) + " Quantidade = " + contador[0]);
        });
    }

    // Método para exibir uma mensagem de erro
    public void AlertaErro() {
        Alert alert = new Alert(Alert.AlertType.ERROR); // Cria um alerta do tipo "Erro"
        alert.setTitle("Erro"); // Define o título
        alert.setHeaderText("Um erro ocorreu"); // Define o cabeçalho
        alert.setContentText("Os campos devem ser preenchidos!"); // Define a mensagem do erro
        alert.showAndWait(); // Exibe o alerta
    }
}