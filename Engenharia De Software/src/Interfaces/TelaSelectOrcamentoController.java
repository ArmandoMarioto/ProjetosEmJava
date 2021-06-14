
package Interfaces;

import Controladoras.CtrOrcamento;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaSelectOrcamentoController implements Initializable
{

    @FXML
    private JFXTextField txCliente;
    @FXML
    private JFXTextField txFuncionario;
    @FXML
    private JFXCheckBox chdatas;
    @FXML
    private VBox pndatas;
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
    private TableColumn<Object, Double> coltotal;

    @FXML
    private JFXDatePicker dtinicio;
    @FXML
    private JFXDatePicker dtfim;
    public static Object ob;
    private static Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        colcliente.setCellValueFactory(new PropertyValueFactory<Object, Object>("cliente"));
        coldtorca.setCellValueFactory(new PropertyValueFactory<Object, Date>("dtorcamento"));
        colfuncionario.setCellValueFactory(new PropertyValueFactory<Object, Object>("usuarioid"));
        colvalidade.setCellValueFactory(new PropertyValueFactory<Object, Date>("dtvalidade"));
        coltotal.setCellValueFactory(new PropertyValueFactory<Object, Double>("total"));
        stage = TelaGenOrdemServicoController.stage;
        dtinicio.setValue(LocalDate.now());
        dtfim.setValue(LocalDate.now());
        CarregaTabela();
    }

    @FXML
    private void evtBusca(ActionEvent event)
    {
        CarregaTabela();
    }

    @FXML
    private void evtClickInTable(MouseEvent event)
    {
        try
        {
            ob = tabela.getSelectionModel().getSelectedItem();
        } catch (Exception ex)
        {
            ob = null;
        }

    }

    private void CarregaTabela()
    {
        try
        {
            tabela.getItems().clear();
            tabela.setItems(CtrOrcamento.getInfoTabelaAvancado(txCliente.getText(),
                    txFuncionario.getText(), chdatas.selectedProperty(),
                    Date.valueOf(dtinicio.getValue()), Date.valueOf(dtfim.getValue())));
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void evtCancelar(ActionEvent event)
    {
        ob = null;
        stage.close();
    }

    @FXML
    private void evtConfirmar(ActionEvent event)
    {
        if (ob != null)
        {
            stage.close();
        } else
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Por favor Selecione um Orcamento válido!!!");
            a.show();
        }
    }

}
