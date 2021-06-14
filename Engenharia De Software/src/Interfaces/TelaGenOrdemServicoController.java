
package Interfaces;

import Banco.Banco;
import Controladoras.CtrCliente;
import Controladoras.CtrOrdemServico;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TelaGenOrdemServicoController implements Initializable
{

    @FXML
    private JFXTextField txcodigo;
    @FXML
    private JFXTextField txcpf;
    @FXML
    private JFXTextField txcodigocliente;
    @FXML
    private JFXTextField txnomecliente;
    @FXML
    private JFXTextField txrgcliente;
    @FXML
    private DatePicker dtos;
    @FXML
    private JFXComboBox<String> cbparcelas;
    @FXML
    private Label lblValorParcelas;
    @FXML
    private DatePicker dtfechamento;
    @FXML
    private JFXComboBox<Object> cbfuncionario;
    @FXML
    private Label lblTotalOS;
    @FXML
    private TableView<Object> tabela;
    @FXML
    private TableColumn<Object, Integer> colcod;
    @FXML
    private TableColumn<Object, Object> colCliente;
    @FXML
    private TableColumn<Object, Object> colFuncionario;
    @FXML
    private TableColumn<Object, Date> colDataOS;
    @FXML
    private TableColumn<Object, Date> colFechamento;
    @FXML
    private TableColumn<Object, Double> colTotal;
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
    private AnchorPane pndado;

    private Object orcamento;
    public static Stage stage;
    @FXML
    private JFXRadioButton rbBoleto;
    @FXML
    private JFXRadioButton rbCartao;
    @FXML
    private JFXRadioButton rbDinheiro;
    
    private Group gb;
    @FXML
    private JFXListView<Object> listaitens;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        IniciaComponentes();
        EstadoOriginal();
        CarregaTabela("");
        gb = new Group(rbBoleto, rbCartao, rbDinheiro);
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
    private void evtAtualizaParcelas(ActionEvent event)
    {
        CtrOrdemServico.AtulizaPArcelas(cbparcelas, lblValorParcelas, orcamento);
    }

    private void IniciaComponentes()
    {
        colCliente.setCellValueFactory(new PropertyValueFactory<Object, Object>("cliente"));
        colDataOS.setCellValueFactory(new PropertyValueFactory<Object, Date>("data_os"));
        colFechamento.setCellValueFactory(new PropertyValueFactory<Object, Date>("data_fechamento"));
        colFuncionario.setCellValueFactory(new PropertyValueFactory<Object, Object>("funcionario"));
        colTotal.setCellValueFactory(new PropertyValueFactory<Object, Double>("valor_total"));
        colcod.setCellValueFactory(new PropertyValueFactory<Object, Integer>("codigo"));
        cbparcelas.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");

        orcamento = null;
    }

    private void EstadoOriginal()
    {
        //txcodigo.setText("");
        txcodigo.setText("");
        txcodigocliente.setText("");
        txcpf.setText("");
        txnomecliente.setText("");
        txnomecliente.setText("");
        cbparcelas.getSelectionModel().select(0);
        txrgcliente.setText("");
        lblTotalOS.setText("0");

        lblValorParcelas.setText("0");
        pndado.setDisable(true);

        dtos.setValue(LocalDate.now());
        dtfechamento.setValue(LocalDate.now());
        btalterar.setDisable(true);
        btcancelar.setDisable(true);
        btconfirmar.setDisable(true);
        btexcluir.setDisable(true);
        btnovo.setDisable(false);
        //modo = 0;
        tabela.refresh();
    }

    private void CarregaTabela(String string)
    {
        CtrOrdemServico.GetInfoTabela(tabela, string);
    }

    @FXML
    private void evtBuscaOrcamento(ActionEvent event)
    {
        stage = new Stage();
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/Interfaces/TelaSelectOrcamento.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.showAndWait();
            if (TelaSelectOrcamentoController.ob != null)
            {
                orcamento = TelaSelectOrcamentoController.ob;
                if (orcamento != null)
                {
                    CtrOrdemServico.AtualizaTotal(lblTotalOS, orcamento);
                    CtrOrdemServico.AtulizaPArcelas(cbparcelas, lblValorParcelas, orcamento);
                    CtrOrdemServico.AtualizaClienteANDFuncionario(txcodigocliente, txcpf, txnomecliente, txrgcliente, cbfuncionario, orcamento);
                }
            }
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
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
    }

    @FXML
    private void evtAlterar(ActionEvent event)
    {
        ModoEdicao();
    }

    @FXML
    private void evtExcluir(ActionEvent event)
    {

    }

    @FXML
    private void evtConfirmar(ActionEvent event)
    {
        if (orcamento != null)
        {
            if (txcodigo.getText().isEmpty())//novo
            {
                if (CtrOrdemServico.Adiciona(Integer.parseInt(txcodigocliente.getText()),
                        cbfuncionario.getSelectionModel().getSelectedItem(),
                        cbparcelas.getSelectionModel().getSelectedItem(),
                        Double.parseDouble(lblTotalOS.getText()),
                        dtos.getValue(),
                        dtfechamento.getValue(),
                        orcamento, rbBoleto, rbCartao, rbDinheiro, listaitens.getItems()))
                {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("Ordem de Serviço Gerada Com Sucesso!!!");
                    a.show();
                } else
                {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Falha ao Gerar Ordem de Serviço!!!\n" + Banco.getConexao().getMensagemErro());
                    a.show();
                }
            } else//alterar
            {

            }
        } else
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Porfavor Selecione um Orçamento válido");
            a.show();
        }

    }

    @FXML
    private void evtCancelar(ActionEvent event)
    {
        EstadoOriginal();
        CarregaTabela("");
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

}
