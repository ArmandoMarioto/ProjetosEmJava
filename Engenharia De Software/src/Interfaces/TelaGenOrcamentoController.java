
package Interfaces;

import Banco.Banco;
import Controladoras.CtrCliente;
import Controladoras.CtrOrcamento;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.MaskFieldUtil;


public class TelaGenOrcamentoController implements Initializable
{

    @FXML
    private JFXComboBox<String> cbbusca;
    @FXML
    private TableView<Object> tabela;
    @FXML
    private TableColumn<Object, Object> colfuncionario;
    @FXML
    private TableColumn<Object, Object> colcliente;
    @FXML
    private TableColumn<Object, Date> coldtorca;
    @FXML
    private TableColumn<Object, Date> colvalidade;
    @FXML
    private JFXTextField txcodigo;
    @FXML
    private JFXDatePicker dtorcamento;
    @FXML
    private JFXDatePicker dtvalidade;
    @FXML
    private Label lbltotal;
    @FXML
    private JFXTextArea txobs;
    @FXML
    private JFXTextField txfuncionario;
    @FXML
    private JFXTextField txcpf;
    @FXML
    private JFXTextField txcodigocliente;
    @FXML
    private JFXTextField txnomecliente;
    @FXML
    private JFXTextField txrgcliente;
    @FXML
    private JFXTextField txquantidadeproduto;
    @FXML
    private JFXTextField txvalorproduto;
    @FXML
    private JFXTextField txquantidadeservico;
    @FXML
    private JFXTextField txvalorservico;
    @FXML
    private AnchorPane pndado;
    /*private JFXComboBox<Object> cbprodutos;
    private JFXComboBox<Object> cbservicos;*/
    @FXML
    private TableColumn<Object, Double> coltotal;

    public static Stage stage;
    @FXML
    private JFXButton btnovo;
    @FXML
    private JFXButton btalterar;
    @FXML
    private JFXButton btconfirmar;
    @FXML
    private JFXButton btexcluir;
    @FXML
    private JFXButton btcancelar;

    private static Integer modo;//1 = novo, 2 = edicao, 0 = não iniciado
    /*private Label lerroProduto;
    private Label lerroServico;*/
    @FXML
    private Label lerroCliente;
    @FXML
    private TableView<Object> tabelaProduto;
    @FXML
    private TableColumn<Object, Object> colTPproduto;
    @FXML
    private TableColumn<Object, Integer> colTPquantidade;
    @FXML
    private TableColumn<Object, Double> colTPvalor;
    @FXML
    private TableColumn<Object, Object> colTSservico;
    @FXML
    private TableView<Object> TabelaServico;
    @FXML
    private TableColumn<Object, Integer> colTSquantidade;
    @FXML
    private TableColumn<Object, Double> colTSvalor;
    @FXML
    private Label lerroPS;
    @FXML
    private Label lerroProdutoC;
    @FXML
    private Label lerroServicoC;

    private Boolean flagTransicaodeData;
    @FXML
    private JFXTextField txBusca;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        iniciacomponentes();
        EstadoOriginal();
        CarregaTabela("");
        flagTransicaodeData = false;
        modo = 0;
    }

    @FXML
    private void evtBusca(ActionEvent event)
    {
        CarregaTabela(txBusca.getText());
    }

    @FXML
    private void evtBuscaCliente(ActionEvent event)
    {
        if (!CtrCliente.getClienteSimples(txcpf, txcodigocliente, txnomecliente, txrgcliente))
        {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Cpf não cadastrado no sistema\nFavor Cadastrar o Cliente!!!");
            a.show();
        }
    }

    @FXML
    private void evtAdicionaProduto(ActionEvent event)
    {
        stage = new Stage();
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/Interfaces/TelaAddProduto.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.showAndWait();
            if (TelaAddProdutoController.getItemP() != null)
            {
                //System.out.println(TelaAddProdutoController.itemP);
                tabelaProduto.getItems().add(TelaAddProdutoController.getItemP());
            }
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        AtualizaTotal();
    }

    @FXML
    private void evtRemoveProduto(ActionEvent event)
    {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Esse produto será removido permanentemente do orçamento selecionado.\nDeseja prosseguir");
        if (a.showAndWait().get() == ButtonType.OK)
        {
            if (modo == 1)
            {
                tabelaProduto.getItems().remove(tabelaProduto.getSelectionModel().getSelectedItem());
            } else if (modo == 2)
            {
                if (CtrOrcamento.apagarProdutoDoOrcamento(tabelaProduto))
                {
                    a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("Produto Apagado com Sucesso!!!");
                    a.show();
                    //tabelaProduto.getItems().remove(tabelaProduto.getSelectionModel().getSelectedItem());
                } else
                {
                    a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Não Foi Possível Apagar Este Item!!!\n" + Banco.getConexao().getMensagemErro());
                    a.show();
                }
            }
        } else
        {
            a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Operação Cancelada com Sucesso!!!");
            a.show();
        }
        AtualizaTotal();
    }

    @FXML
    private void evtAdicionaServico(ActionEvent event)
    {
        stage = new Stage();
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/Interfaces/TelaAddServico.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.showAndWait();
            if (TelaAddServicoController.getItem() != null)
            {
                TabelaServico.getItems().add(TelaAddServicoController.getItem());
            }
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        AtualizaTotal();
    }

    @FXML
    private void evtRemoveServico(ActionEvent event)
    {
        if (modo == 1)
        {
            TabelaServico.getItems().remove(TabelaServico.getSelectionModel().getSelectedItem());
        } else if (modo == 2)
        {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Esse Serviço será removido permanentemente do orçamento selecionado.\nDeseja prosseguir");
            if (a.showAndWait().get() == ButtonType.OK)
            {
                if (CtrOrcamento.apagarServicoDoOrcamento(TabelaServico))
                {
                    a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("Serviço Apagado com Sucesso!!!");
                    a.show();
                    //TabelaServico.getItems().remove(TabelaServico.getSelectionModel().getSelectedItem());
                } else
                {
                    a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Não Foi Possível Apagar Este Item!!!\n" + Banco.getConexao().getMensagemErro());
                    a.show();
                }
            } else
            {
                a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Operação Cancelada com Sucesso!!!");
                a.show();
            }
        }
        AtualizaTotal();
    }

    @FXML
    private void evtNovo(ActionEvent event)
    {
        pndado.setDisable(false);
        //btalterar.setDisable(false);
        //btexcluir.setDisable(false);
        btconfirmar.setDisable(false);
        btcancelar.setDisable(false);
        btnovo.setDisable(true);
        modo = 1;
    }

    @FXML
    private void evtAlterar(ActionEvent event)
    {
        ModoEdicao();
        modo = 2;
    }

    @FXML
    private void evtConfirmar(ActionEvent event)
    {
        if (ValidaCampos())
        {
            if (txcodigo.getText().isEmpty())//novo
            {
                try
                {
                    if (CtrOrcamento.Adicionar(Integer.parseInt(txcodigocliente.getText()),
                            dtorcamento.getValue(), dtvalidade.getValue(),
                            Double.parseDouble(lbltotal.getText()),
                            txobs.getText(), 1, tabelaProduto.getItems(), TabelaServico.getItems()))//substituir o 1 pelo id do usuario logado no sistema
                    {
                        Alert a = new Alert(Alert.AlertType.INFORMATION);
                        a.setContentText("Orçamento registrado com sucesso!!!");
                        a.show();
                        EstadoOriginal();
                        CarregaTabela("");
                    } else
                    {
                        Alert a = new Alert(Alert.AlertType.ERROR);
                        a.setContentText("Falha ao realizar o orçamento");
                        a.show();
                    }
                } catch (Exception ex)
                {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText(ex.getMessage() + "\n" + Banco.getConexao().getMensagemErro());
                    a.show();
                }
            } else if (modo == 2)
            {
                if (CtrOrcamento.Alterar(Integer.parseInt(txcodigo.getText()), Integer.parseInt(txcodigocliente.getText()),
                        dtorcamento.getValue(), dtvalidade.getValue(),
                        Double.parseDouble(lbltotal.getText()),
                        txobs.getText(), 1, tabelaProduto.getItems(), TabelaServico.getItems()))//substituir o 1 pelo id do usuario logado no sistema
                {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("Orçamento modificado com sucesso!!!");
                    a.show();
                    EstadoOriginal();
                    CarregaTabela("");
                } else
                {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Falha ao modificar o orçamento");
                    a.show();
                }
            }
        }

    }

    @FXML
    private void evtExcluir(ActionEvent event)
    {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("O orcamento bem como todos os seus itens serão apagados.\nDeseja Prosseguir?");
        if (a.showAndWait().get() == ButtonType.OK)
        {
            if (CtrOrcamento.ApagarOrcamento(Integer.parseInt(txcodigo.getText())))
            {
                a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Orcamento Apagado Com Sucesso");
                a.show();
                EstadoOriginal();
            } else
            {
                a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Erro ao Apagar Orcamento");
                a.show();
            }
        } else
        {
            a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Operação Cancelada com sucesso");
            a.show();
        }
    }

    @FXML
    private void evtCancelar(ActionEvent event)
    {
        EstadoOriginal();
        CarregaTabela("");
    }

    @FXML
    private void evtClickInTable(MouseEvent event)
    {
        EstadoOriginal();
        CtrOrcamento.PreencheCampos(tabela.getSelectionModel().getSelectedItem(), txcodigo, txcodigocliente, txcpf, txfuncionario, txnomecliente, txobs, txrgcliente, tabelaProduto, TabelaServico, dtorcamento, dtvalidade);
        btnovo.setDisable(true);
        btcancelar.setDisable(false);
        btexcluir.setDisable(false);
        btalterar.setDisable(false);
        AtualizaTotal();
    }

    private void EstadoOriginal()
    {
        txBusca.setText("");
        txcodigo.setText("");
        txcodigocliente.setText("");
        txcpf.setText("");
        txnomecliente.setText("");
        txnomecliente.setText("");
        txobs.setText("");
        txquantidadeproduto.setText("");
        txquantidadeservico.setText("");
        txrgcliente.setText("");
        txvalorproduto.setText("");
        txvalorservico.setText("");
        lbltotal.setText("0");
        flagTransicaodeData = true;
        dtorcamento.setValue(LocalDate.now());
        dtvalidade.setValue(dtorcamento.getValue().plusDays(1));
        flagTransicaodeData = false;
        pndado.setDisable(true);
        lerroCliente.setVisible(false);
        lerroPS.setVisible(false);
        lerroProdutoC.setVisible(false);
        lerroServicoC.setVisible(false);

        btalterar.setDisable(true);
        btcancelar.setDisable(true);
        btconfirmar.setDisable(true);
        btexcluir.setDisable(true);
        btnovo.setDisable(false);
        modo = 0;
        tabela.refresh();
    }

    private void ModoEdicao()
    {
        pndado.setDisable(false);
        btalterar.setDisable(true);
        btnovo.setDisable(true);
        btexcluir.setDisable(true);
        btconfirmar.setDisable(false);
        btcancelar.setDisable(false);
    }

    private void CarregaTabela(String string)
    {
        tabela.getItems().clear();
        tabela.setItems(CtrOrcamento.getInfoTabela(string, cbbusca.getSelectionModel().getSelectedItem()));
    }

    private void iniciacomponentes()
    {
        colcliente.setCellValueFactory(new PropertyValueFactory<Object, Object>("cliente"));
        coldtorca.setCellValueFactory(new PropertyValueFactory<Object, Date>("dtorcamento"));
        colfuncionario.setCellValueFactory(new PropertyValueFactory<Object, Object>("usuarioid"));
        colvalidade.setCellValueFactory(new PropertyValueFactory<Object, Date>("dtvalidade"));
        coltotal.setCellValueFactory(new PropertyValueFactory<Object, Double>("total"));

        colTPproduto.setCellValueFactory(new PropertyValueFactory<Object, Object>("produto"));
        colTPquantidade.setCellValueFactory(new PropertyValueFactory<Object, Integer>("qtd"));
        colTPvalor.setCellValueFactory(new PropertyValueFactory<Object, Double>("valor"));

        colTSquantidade.setCellValueFactory(new PropertyValueFactory<Object, Integer>("qtd"));
        colTSservico.setCellValueFactory(new PropertyValueFactory<Object, Object>("servico"));
        colTSvalor.setCellValueFactory(new PropertyValueFactory<Object, Double>("valor"));
        cbbusca.getItems().addAll("RG", "Ano");

        MaskFieldUtil.cpfField(txcpf);
        MaskFieldUtil.ignoreKeys(txBusca);
        MaskFieldUtil.numericField(txcodigo);
        MaskFieldUtil.numericField(txcodigocliente);
        MaskFieldUtil.numericField(txquantidadeproduto);
        MaskFieldUtil.numericField(txquantidadeservico);
        MaskFieldUtil.monetaryField(txvalorproduto);
        MaskFieldUtil.monetaryField(txvalorservico);
    }

    @FXML
    private void evtalterarProduto(ActionEvent event)
    {
        CtrOrcamento.alteraProdutoSelecionado(tabelaProduto, txquantidadeproduto, txvalorproduto, lerroProdutoC);
        AtualizaTotal();
    }

    @FXML
    private void evtalterarServico(ActionEvent event)
    {
        CtrOrcamento.alteraServicoSelecionado(TabelaServico, txquantidadeservico, txvalorservico, lerroServicoC);
        AtualizaTotal();
    }

    private void AtualizaTotal()
    {
        CtrOrcamento.AtualizaTot(lbltotal, tabelaProduto, TabelaServico);
    }

    private boolean ValidaCampos()
    {
        boolean flag = (tabelaProduto.getItems().size() == 0 && TabelaServico.getItems().size() == 0) || txcodigocliente.getText().isEmpty();
        if (flag)
        {
            lerroCliente.setVisible(txcodigocliente.getText().isEmpty());
            lerroPS.setVisible(tabelaProduto.getItems().size() == 0 && TabelaServico.getItems().size() == 0);
        }
        return !flag;
    }

    @FXML
    private void evtClickTableProduto(MouseEvent event)
    {
        CtrOrcamento.PreencheCamposPS(txquantidadeproduto, txvalorproduto, tabelaProduto.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void evtClickTableServico(MouseEvent event)
    {
        CtrOrcamento.PreencheCamposPS(txquantidadeservico, txvalorservico, TabelaServico.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void evtmudaData(ActionEvent event)
    {

        if (!flagTransicaodeData && Period.between(dtorcamento.getValue(), dtvalidade.getValue()).isNegative())
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Erro em Data\nData do Orçamento deve ser anterior a validade!!!\nDatas Redefinidas");
            a.show();
            dtorcamento.setValue(LocalDate.now());
            dtvalidade.setValue(dtorcamento.getValue().plusDays(1));
        }
    }

    @FXML
    private void evtmudaData2(ActionEvent event)
    {
        if (!flagTransicaodeData && Period.between(dtorcamento.getValue(), dtvalidade.getValue()).isNegative())
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Erro em Data\nData do Orçamento deve ser anterior a validade!!!\nDatas Redefinidas");
            a.show();
            dtorcamento.setValue(LocalDate.now());
            dtvalidade.setValue(dtorcamento.getValue().plusDays(1));
        }
    }
}
