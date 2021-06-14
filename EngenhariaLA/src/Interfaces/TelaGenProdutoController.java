package Interfaces;

import Controladoras.CtrProduto;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.MaskFieldUtil;

public class TelaGenProdutoController implements Initializable
{

    @FXML
    private TableColumn<Object, Integer> colcodigo;
    @FXML
    private TableColumn<Object, String> colnome;
    @FXML
    private TableColumn<Object, String> collote;
    @FXML
    private TableColumn<Object, Object> coltipo;
    @FXML
    private TableColumn<Object, Double> colpreço;
    @FXML
    private TableColumn<Object, Integer> colquantidade;
    @FXML
    private JFXTextField tnome;
    @FXML
    private JFXTextField tpreco;
    @FXML
    private JFXTextField tquantidade;
    @FXML
    private JFXComboBox<Object> cbfabricante;
    @FXML
    private JFXButton btnovo;
    @FXML
    private JFXButton btalterar;
    @FXML
    private JFXButton btexcluir;
    @FXML
    private JFXButton btconfirmar;
    @FXML
    private JFXButton btcancelar;
    @FXML
    private JFXTextField tbusca;
    @FXML
    private Label lblErroNome;
    @FXML
    private Label lblErroPreco;
    @FXML
    private Label lblErroQuantidade;
    @FXML
    private Label lblErroFabricante;
    @FXML
    private AnchorPane pndados;
    @FXML
    private TableView<Object> tabela;

    @FXML
    private JFXComboBox<Object> cbtipo;
    @FXML
    private JFXTextField tlote;
    @FXML
    private JFXTextArea tobs;
    //private JFXComboBox<Object> cbfornecedor;

    @FXML
    private JFXTextField tcodigo;
    @FXML
    private Label lblErroTipo;
    @FXML
    private JFXComboBox<String> cbbusca;
    @FXML
    private Label lblErroLote;

    //private Object o;
    //private int flag;
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        IniciaComponentes();
        EstadoOriginal();
        CarregaTabela("");
    }

    @FXML
    private void evtClickTabela(MouseEvent event)
    {
        EstadoOriginal();
        CarregaCombos();
        CtrProduto.GetProduto(tcodigo, tnome, tpreco,
                cbfabricante,
                cbtipo, tquantidade, tobs, tlote,
                tabela.getSelectionModel().getSelectedItem());
        btnovo.setDisable(true);
        btalterar.setDisable(false);
        btexcluir.setDisable(false);
        btcancelar.setDisable(false);
    }

    @FXML
    private void evtNovo(ActionEvent event)
    {
        EstadoEdicao();
        //flag = 0;
    }

    @FXML
    private void evtAlterar(ActionEvent event)
    {
        //flag = 1;
        EstadoEdicao();
    }

    @FXML
    private void evtExcluir(ActionEvent event)
    {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("O Produto Será Apagado Permanentemente Da Base de Dados");
        if (a.showAndWait().get() == ButtonType.OK)
        {
            if (CtrProduto.Remove(Integer.parseInt(tcodigo.getText())))
            {
                EstadoOriginal();
                CarregaTabela("");
            } else
            {
                a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Erro ao Excluir Registro!!!");
                a.show();
            }
        }
    }

    @FXML
    private void evtConfirmar(ActionEvent event)
    {
        Alert a;
        if (CamposValidos())
        {
            if (tcodigo.getText().isEmpty())//novo
            {
                //String nome, Double preco, Object fabricante, Object fornecedor, Object tipo, Integer quantidade, String obs, String lote)
                if (CtrProduto.SalvarProduto(tnome.getText(), Double.parseDouble(tpreco.getText().replace(".", "").replace(",", ".")),
                        cbfabricante.getSelectionModel().getSelectedItem(),
                        cbtipo.getSelectionModel().getSelectedItem(), Integer.parseInt(tquantidade.getText()), tobs.getText(), tlote.getText()))
                {
                    a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("Produto Cadastrado Com Sucesso!!!");
                    a.show();
                    EstadoOriginal();
                } else
                {
                    a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Erro ao Cadastrar Produto");
                    a.show();
                }
            } else if (!tcodigo.getText().isEmpty())//alterar
            {
                if (CtrProduto.Altera(Integer.parseInt(tcodigo.getText()), tnome.getText(), Double.parseDouble(tpreco.getText().replace(".", "").replace(",", ".")),
                        cbfabricante.getSelectionModel().getSelectedItem(),
                        cbtipo.getSelectionModel().getSelectedItem(), Integer.parseInt(tquantidade.getText()), tobs.getText(), tlote.getText()))
                {
                    a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("Produto Alterado Com Sucesso!!!");
                    a.show();
                    EstadoOriginal();
                } else
                {
                    a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Erro ao Alterar Produto");
                    a.show();
                }
            }
            CarregaTabela("");
        }
    }

    @FXML
    private void evtCancelar(ActionEvent event)
    {
        EstadoOriginal();
    }

    @FXML
    private void evtBusca(ActionEvent event)
    {
        CarregaTabela(tbusca.getText());
    }

    private void IniciaComponentes()
    {
        colcodigo.setCellValueFactory(new PropertyValueFactory<Object, Integer>("codigo"));
        colnome.setCellValueFactory(new PropertyValueFactory<Object, String>("nome"));
        colpreço.setCellValueFactory(new PropertyValueFactory<Object, Double>("preco"));
        colquantidade.setCellValueFactory(new PropertyValueFactory<Object, Integer>("quantidade"));
        collote.setCellValueFactory(new PropertyValueFactory<Object, String>("lote"));
        coltipo.setCellValueFactory(new PropertyValueFactory<Object, Object>("tipo"));
        MaskFieldUtil.monetaryField(tpreco);
        MaskFieldUtil.numericField(tquantidade);
        cbbusca.getItems().addAll("Nome", "Tipo");
        cbbusca.getSelectionModel().select(0);
        CarregaCombos();
    }

    private void EstadoEdicao()
    {
        btnovo.setDisable(true);
        btexcluir.setDisable(true);
        btalterar.setDisable(true);
        btconfirmar.setDisable(false);
        btcancelar.setDisable(false);
        pndados.setDisable(false);
        desativasLabelsErro();
    }

    private void EstadoOriginal()
    {
        tbusca.setText("");
        tlote.setText("");
        tnome.setText("");
        tpreco.setText("");
        tbusca.setText("");
        tcodigo.setText("");
        tobs.setText("");
        tquantidade.setText("");

        cbtipo.getSelectionModel().select(null);
        cbfabricante.getSelectionModel().select(null);

        pndados.setDisable(true);
        btalterar.setDisable(true);
        btcancelar.setDisable(true);
        btconfirmar.setDisable(true);
        btexcluir.setDisable(true);
        btnovo.setDisable(false);
        desativasLabelsErro();
    }

    private void desativasLabelsErro()
    {
        lblErroLote.setVisible(false);
        lblErroNome.setVisible(false);
        lblErroPreco.setVisible(false);
        lblErroQuantidade.setVisible(false);
        lblErroFabricante.setVisible(false);
        lblErroTipo.setVisible(false);

        lblErroNome.setText("");
        lblErroPreco.setText("");
        lblErroQuantidade.setText("");
        lblErroFabricante.setText("");
        lblErroTipo.setText("");
        lblErroLote.setText("");
    }

    private boolean CamposValidos()
    {
        boolean b = true;
        if (tnome.getText().matches(".*\\d+.*") || tnome.getText().length() == 0 || tnome.getText().length() > 70)
        {
            if (tnome.getText().matches(".*\\d+.*"))
            {
                lblErroNome.setText("Caractéres Inválidos");
            } else if (tnome.getText().length() == 0)
            {
                lblErroNome.setText("Insira o Nome");
            } else
            {
                lblErroNome.setText("Limite Máximo Atingido(70 Caracteres)");
            }
            lblErroNome.setVisible(true);
            b = false;
        } else
        {
            lblErroNome.setVisible(false);
        }
        if (tlote.getText().length() == 0)
        {
            lblErroLote.setText("Informe o Lote");
            lblErroLote.setVisible(true);
            b = false;
        } else
        {
            lblErroLote.setVisible(false);
        }
        if (cbfabricante.getSelectionModel().getSelectedItem() == null)
        {
            lblErroFabricante.setText("Escolha o Fabricante");
            lblErroFabricante.setVisible(true);
            b = false;
        } else
        {
            lblErroFabricante.setVisible(false);
        }
        if (cbtipo.getSelectionModel().getSelectedItem() == null)
        {
            lblErroTipo.setText("Escolha o Tipo");
            lblErroTipo.setVisible(true);
            b = false;
        } else
        {
            lblErroTipo.setVisible(false);
        }
        if (tpreco.getText().length() == 0)
        {
            lblErroPreco.setText("Defina um Preço Para o Produto");
            lblErroPreco.setVisible(true);
            b = false;
        } else
        {
            lblErroPreco.setVisible(false);
        }
        if (tquantidade.getText().length() == 0)
        {
            lblErroQuantidade.setText("Defina uma Quantidade Para o Produto");
            lblErroQuantidade.setVisible(true);
            b = false;
        } else
        {
            lblErroQuantidade.setVisible(false);
        }
        return b;
    }

    private void CarregaTabela(String filtro)
    {
        tabela.getItems().clear();
        ObservableList<Object> ob = FXCollections.observableArrayList(CtrProduto.GetListaProdutos(filtro, cbbusca.getSelectionModel().getSelectedItem()));
        tabela.setItems(ob);
    }

    private void CarregaCombos()
    {
        cbfabricante.getItems().clear();
        //cbfornecedor.getItems().clear();
        cbtipo.getItems().clear();
        CtrProduto.CarregaCombosDaTela(cbfabricante, cbtipo);
    }
}
