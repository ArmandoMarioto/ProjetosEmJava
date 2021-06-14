
package Interfaces;

import Controladoras.CtrOrcamento;
import Controladoras.CtrServico;
import Entidades.NovaEntidades.ItemOrcamentoServico;
import Entidades.NovaEntidades.Servico;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class TelaAddServicoController implements Initializable
{

    @FXML
    private JFXTextField txnomebuscar;
    @FXML
    private TableView<Object> tabela;
    @FXML
    private TableColumn<Object, String> colnome;
    @FXML
    private TableColumn<Object, String> coldescr;

    private static Object item;
    private static Stage stage;
    @FXML
    private JFXButton btnconfirmar;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        item = null;
        colnome.setCellValueFactory(new PropertyValueFactory<Object, String>("nome"));
        coldescr.setCellValueFactory(new PropertyValueFactory<Object, String>("desc"));
        stage = TelaGenOrcamentoController.stage;
        btnconfirmar.setDisable(true);
        CarregaTabela("");
    }

    @FXML
    private void evtbuscar(ActionEvent event)
    {
        CarregaTabela(txnomebuscar.getText());
    }

    @FXML
    private void evtCancelar(ActionEvent event)
    {
        item = null;
        stage.close();
    }

    @FXML
    private void evtConfirmar(ActionEvent event)
    {
        stage.close();
    }

    @FXML
    private void evtClicktanela(MouseEvent event)
    {
        try
        {
            item = CtrOrcamento.getItemOrcamentoServico(tabela.getSelectionModel().getSelectedItem(), 0.0, 0);
            if (item != null)//talvez desnecessário
            {
                btnconfirmar.setDisable(false);
            }
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    private void CarregaTabela(String string)
    {
        tabela.getItems().clear();
        ObservableList<Object> ob;
        try
        {
            ob = FXCollections.observableArrayList(CtrServico.getAll(string));
        } catch (Exception ex)
        {
            ob = null;
        }
        tabela.setItems(ob);
    }

    public static Object getItem()
    {
        return item;
    }

    public static void setItem(Object item)
    {
        TelaAddServicoController.item = item;
    }

    public static Stage getStage()
    {
        return stage;
    }

    public static void setStage(Stage stage)
    {
        TelaAddServicoController.stage = stage;
    }

}
