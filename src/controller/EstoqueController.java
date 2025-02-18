package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.bo.EstoqueBO;
import model.vo.Estoque;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class EstoqueController implements Initializable {

    // Botão para retornar à tela anterior.
    @FXML
    private Button jbtnVoltar;

    // Botão para concluir as alterações.
    @FXML
    private Button jbtnConcluir;

    // Componente de rolagem para exibir os itens do estoque.
    @FXML
    private ScrollPane scrollPane;

    // Botão para criar um novo estoque.
    @FXML
    private Button jbtnCriarEstoque;

    // Contêiner que será incluído no ScrollPane para exibir os itens do estoque.
    private VBox verBox = new VBox();

    // Map para armazenar os estoques que foram modificados pelo usuário.
    private Map<Integer, Estoque> estoquesModificados = new HashMap<>();

    /**
     * Método inicializador do controlador. Este método é chamado automaticamente ao carregar a tela.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Define o conteúdo do ScrollPane como a VBox verBox.
        scrollPane.setContent(verBox);

        // Criação de um mapa para armazenar os estoques recuperados do banco de dados.
        Map<Integer, Estoque> listaEstoque = new HashMap<>();

        // Busca os estoques do banco de dados usando a classe EstoqueBO.
        try {
            listaEstoque = EstoqueBO.ListarEstoque();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Itera sobre os estoques recuperados, chamando o método ManipularEstoque para cada item.
        listaEstoque.forEach((key, value) -> {
            try {
                ManipularEstoque(key, value);
            } catch (IOException | SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        // Configura a ação do botão "Concluir" para salvar as alterações nos estoques modificados.
        jbtnConcluir.setOnAction(e -> {
            estoquesModificados.forEach((key, value) -> {
                try {
                    EstoqueBO.ManipularEstoque(value, key);
                } catch (SQLException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });
        });
    }

    /**
     * Método para retornar à tela anterior. Verifica o tipo de usuário e redireciona para a tela apropriada.
     */
    public void Voltar() throws IOException {
        if (TrocadorTelas.getUsuario() == Usuario.ATENDENTE) {
            // Redireciona para a tela de pedidos atuais.
            TrocadorTelas.TrocarTela("/view/PedidosAtuais.fxml", (Stage) jbtnVoltar.getScene().getWindow());
        } else {
            // Redireciona para a tela do administrador.
            TrocadorTelas.TrocarTela("/view/AdmLogado.fxml", (Stage) jbtnVoltar.getScene().getWindow());
        }
    }

    /**
     * Método para navegar até a tela de criação de estoque.
     */
    public void CriarEstoque() throws IOException {
        // Redireciona para a tela de criação de estoque.
        TrocadorTelas.TrocarTela("/view/CriarEstoque.fxml", (Stage) jbtnCriarEstoque.getScene().getWindow());
    }

    /**
     * Método para configurar e exibir os dados de um item do estoque na interface do usuário.
     *
     * @param ID_estoque ID do estoque.
     * @param estoque    Objeto Estoque a ser exibido e manipulado.
     */
    public void ManipularEstoque(Integer ID_estoque, Estoque estoque) throws IOException, SQLException, ClassNotFoundException {

        // Cria uma horizontal box (HBox) para organizar os elementos relacionados a um item do estoque.
        HBox horBox = new HBox();
        horBox.setSpacing(15);
        horBox.setPadding(new Insets(10, 10, 10, 10));

        // Criação de rótulos (labels) para exibir os detalhes do item como ingrediente, validade e lote.
        Label ingrediente = new Label(estoque.getEstoqueingrediente().getNome());
        Label validade = new Label(String.valueOf("Validade: " + estoque.getValidade()));
        Label lote = new Label("Lote: " + String.valueOf(estoque.getLote()));

        // Botões para adicionar, remover e excluir itens do estoque.
        Button adicionar = new Button("Adicionar");
        Button remover = new Button("Remover");
        Button excluir = new Button("Excluir");

        // Contador para monitorar e alterar a quantidade do estoque.
        final int[] contador = {estoque.getQuantidade()};
        Label quant = new Label("Quantidade: " + contador[0]);

        // Adiciona a HBox ao VBox principal (verBox) para exibição.
        verBox.getChildren().add(horBox);
        // Adiciona os rótulos e botões na HBox para organizar um item do estoque.
        horBox.getChildren().addAll(ingrediente, validade, lote, adicionar, remover, quant, excluir);

        // Configura a ação do botão "Adicionar". Incrementa a quantidade do estoque e atualiza o contador.
        adicionar.setOnAction(e -> {
            contador[0]++;
            quant.setText("Quantidade: " + contador[0]);
            estoque.setQuantidade(contador[0]);
            estoquesModificados.put(ID_estoque, estoque); // Adiciona o estoque modificado ao Map.
        });

        // Configura a ação do botão "Remover". Decrementa a quantidade do estoque se o valor for maior que 0.
        remover.setOnAction(e -> {
            if (contador[0] > 0) {
                contador[0]--;
                quant.setText("Quantidade: " + contador[0]);
                estoque.setQuantidade(contador[0]);
                estoquesModificados.put(ID_estoque, estoque); // Atualiza o estoque modificado no Map.


            }
        });

        // Configura a ação do botão "Excluir". Remove o estoque completamente do banco de dados.
        excluir.setOnAction(e -> {
            try {
                EstoqueBO.deletarEstoque(ID_estoque);
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            try {
                TrocadorTelas.TrocarTela("/view/Estoque.fxml", (Stage) remover.getScene().getWindow());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}