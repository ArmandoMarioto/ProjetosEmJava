package sisdentalfx.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import sisdentalfx.db.controles.CtrPessoa;
import sisdentalfx.db.entidades.Paciente;
import sisdentalfx.db.entidades.Pessoa;

public class TelaPesPacienteController implements Initializable {

    @FXML
    private TableView<Pessoa> table;
    @FXML
    private TableColumn colNome;
    @FXML
    private TableColumn colTelefone;
    @FXML
    private Button btConfirmar;
    @FXML
    private Button btCancelar;
    @FXML
    private TextField tfBuscar;
    @FXML
    private Button btBuscar;
    @FXML
    private BorderPane pnPrincipal;

    private void carregaTabela(String filtro) {
        CtrPessoa ctrP = new CtrPessoa();
        ArrayList<Pessoa> resultado = ctrP.getPessoa(filtro, new Paciente());

        ObservableList<Pessoa> modelo = FXCollections.observableArrayList(resultado);
        table.setItems(modelo);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfBuscar.setText("");
        tfBuscar.requestFocus();
        btConfirmar.setDisable(true);

        colTelefone.setCellValueFactory(new PropertyValueFactory("telefone"));
        colNome.setCellValueFactory(new PropertyValueFactory("nome"));

        carregaTabela("");
    }

    @FXML
    private void evtTabela(MouseEvent event) {
        if (table.getSelectionModel().getSelectedIndex() >= 0)//selecionando um paciente da minha litas
        {
            btConfirmar.setDisable(false);
            TelaCadAgendamentoController.auxPaciente = (Paciente) table.getSelectionModel().getSelectedItem();
            TelaCadAgendamentoController.palco.close();//fechando a tela
        }
    }

    @FXML
    private void evtBtConfirmar(ActionEvent event) {
    }

    @FXML
    private void evtBtCancelar(ActionEvent event) {
        TelaCadAgendamentoController.palco.close();
    }

    @FXML
    private void evtBtBuscar(ActionEvent event) {
        carregaTabela("upper(pac_nome) like '%" + tfBuscar.getText().toUpperCase() + "%'");
        tfBuscar.setText("");
    }

}
