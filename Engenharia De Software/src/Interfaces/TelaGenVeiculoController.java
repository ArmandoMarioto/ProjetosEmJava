package Interfaces;

import Controladoras.CtrMarca;
import Controladoras.CtrModelo;
import Controladoras.CtrVeiculo;
import Entidades.Marca;
import Entidades.Modelo;
import Entidades.Veiculo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaGenVeiculoController implements Initializable
{

    @FXML
    private JFXComboBox<Object> cb_marca;
    @FXML
    private JFXComboBox<Object> cb_modelo;
    @FXML
    private JFXTextField tf_placa;
    @FXML
    private JFXDatePicker dtp_ano;
    @FXML
    private JFXColorPicker tf_cor;
    @FXML
    private JFXTextField tf_chassi;
    @FXML
    private JFXButton btn_adicionar;
    @FXML
    private JFXButton btn_remover;
    @FXML
    private JFXButton btn_confirmar;
    @FXML
    private JFXButton btn_editar;
    @FXML
    private JFXButton btn_cancelar;
    @FXML
    private JFXButton btn_sair;
    @FXML
    private TableView<Veiculo> tv_veiculo;
    @FXML
    private TableColumn<Marca, String> tc_marca;
    @FXML
    private TableColumn<Modelo, String> tc_modelo;
    @FXML
    private TableColumn<String, String> tc_placa;
    @FXML
    private TableColumn<String, String> tc_ano;
    @FXML
    private TableColumn<String, String> tc_cor;
    @FXML
    private TableColumn<String, String> tc_chassi;

    public static ArrayList<Veiculo> veiculos = new ArrayList<>();

    public void inicializa()
    {
        btn_cancelar.setDisable(true);
        btn_confirmar.setDisable(true);
        btn_remover.setDisable(true);
        cb_marca.setDisable(true);
        cb_modelo.setDisable(true);
        tf_chassi.setDisable(true);
        tf_cor.setDisable(true);
        tf_placa.setDisable(true);
        dtp_ano.setDisable(true);
    }

    public void novo_editar()
    {
        btn_remover.setDisable(true);
        btn_adicionar.setDisable(true);
        btn_editar.setDisable(true);

        btn_cancelar.setDisable(false);
        btn_confirmar.setDisable(false);
        cb_marca.setDisable(false);
        cb_modelo.setDisable(false);
        tf_chassi.setDisable(false);
        tf_cor.setDisable(false);
        tf_placa.setDisable(false);
        dtp_ano.setDisable(false);
    }

    public void cancelar()
    {
        btn_adicionar.setDisable(false);
        btn_editar.setDisable(false);
        tf_chassi.clear();
        tf_placa.clear();
        CtrMarca.PreencheCombo(cb_marca);
        cb_marca.getSelectionModel().select(0);
        CtrModelo.PreencheCombo(cb_modelo, cb_marca.getSelectionModel().getSelectedItem());
        inicializa();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        CtrMarca.PreencheCombo(cb_marca);
        cb_marca.getSelectionModel().select(0);
        CtrModelo.PreencheCombo(cb_modelo, cb_marca.getSelectionModel().getSelectedItem());
        Marca m = CtrMarca.get(cb_marca);
        inicializa();
        //tc_marca.setCellValueFactory(new PropertyValueFactory<Marca,String>("Marca"));
        tc_marca.setCellValueFactory(CtrMarca -> new SimpleStringProperty(CtrMarca.getValue().getNome()));
        tc_modelo.setCellValueFactory(CtrModelo -> new SimpleStringProperty(CtrModelo.getValue().getNome()));
        tc_ano.setCellValueFactory(new PropertyValueFactory<>("Ano"));
        tc_chassi.setCellValueFactory(new PropertyValueFactory<>("Chassi"));
        tc_placa.setCellValueFactory(new PropertyValueFactory<>("Placa"));
        tc_cor.setCellValueFactory(new PropertyValueFactory<>("Cor"));
        tv_veiculo.setDisable(false);
        //tc_marca.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Marca, String>, ObservableValue<String>>) new SimpleStringProperty(CtrMarca.get(cb_marca).toString()));
    }

    @FXML
    private void click_marca(ActionEvent event)
    {
        CtrModelo.PreencheCombo(cb_modelo, cb_marca.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void click_adicionar(ActionEvent event)
    {
        btn_confirmar.setText("Confirmar adição");
        novo_editar();
    }

    @FXML
    private void click_remover(ActionEvent event)
    {
        if (tv_veiculo.getSelectionModel().getSelectedIndex() >= 0)//se tem alguma linha selecionada
        {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja remover o veiculo selecionado?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alerta.showAndWait();
            if (result.get() == ButtonType.YES)
            {
                CtrVeiculo.removerVeiculo(tv_veiculo);
            }
        }
    }

    @FXML
    private void click_editar(ActionEvent event)
    {
        if (tv_veiculo.getSelectionModel().getSelectedIndex() >= 0)//se tem alguma linha selecionada
        {
            btn_confirmar.setText("Confirmar edição");
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja alterar o veiculo selecionado?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alerta.showAndWait();
            if (result.get() == ButtonType.YES)
            {
                cb_marca.getSelectionModel().select(tv_veiculo.getSelectionModel().getSelectedItem().getMarca());
                cb_modelo.getSelectionModel().select(tv_veiculo.getSelectionModel().getSelectedItem().getModelo());
                tf_chassi.setText(tv_veiculo.getSelectionModel().getSelectedItem().getChassi());
                tf_placa.setText(tv_veiculo.getSelectionModel().getSelectedItem().getPlaca());
                tf_cor.setValue(tv_veiculo.getSelectionModel().getSelectedItem().getCor());
            }
            novo_editar();
        }
    }

    @FXML
    private void click_confirmar(ActionEvent event)
    {
        if (tv_veiculo.getSelectionModel().getSelectedIndex() >= 0)//se tem alguma linha selecionada
        {
            if (btn_confirmar.getText().equals("Confirmar edição"))
            {
                CtrVeiculo.removerVeiculo(tv_veiculo);
            }
            CtrVeiculo.adicionarVeiculo(tv_veiculo, cb_marca.getSelectionModel().getSelectedItem(), cb_modelo.getSelectionModel().getSelectedItem(), tf_chassi.getText(), tf_placa.getText(), tf_cor.getValue());
            btn_confirmar.setText("Confirmar");
            Alert alerta = new Alert(Alert.AlertType.INFORMATION, "Operação realizada com sucesso!", ButtonType.OK);
            alerta.showAndWait();
            cancelar();
        }
    }

    @FXML
    private void click_cancelar(ActionEvent event)
    {
        btn_confirmar.setText("Confirmar");
        cancelar();
    }

    @FXML
    private void click_sair(ActionEvent event)
    {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja sair?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alerta.showAndWait();
        if (result.get() == ButtonType.YES)
        {
            veiculos = CtrVeiculo.copia(tv_veiculo);
        }
    }

}
