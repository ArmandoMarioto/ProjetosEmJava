package sisdentalfx.ui;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sisdentalfx.db.controles.CtrAgenda;
import sisdentalfx.db.controles.CtrPessoa;
import sisdentalfx.db.entidades.Agenda;
import sisdentalfx.db.entidades.Dentista;
import sisdentalfx.db.entidades.Horarios;
import sisdentalfx.db.entidades.Paciente;
import sisdentalfx.db.entidades.Pessoa;
import static sisdentalfx.ui.TelaPrincipalController._lbTelaCadAtual;

public class TelaCadAgendamentoController implements Initializable {

    private ArrayList<Horarios> auxHorarios;
    private ArrayList<Horarios> HorariosList;
    public static Paciente auxPaciente;
    public static Stage palco;

    @FXML
    private DatePicker dpData;
    @FXML
    private ComboBox<Pessoa> cbDentista;
    @FXML
    private TableView<Horarios> table;
    @FXML
    private TableColumn colHorario;
    @FXML
    private TableColumn colPaciente;
    @FXML
    private Button btAgendar;
    @FXML
    private Button btDesmarcar;
    @FXML
    private Button btFechar;
    @FXML
    private HBox pnAgendamento;

    private void estadoOriginal() {
        auxPaciente = null;
        dpData.setValue(LocalDate.now());
        btAgendar.setDisable(true);
        btDesmarcar.setDisable(true);
        table.getSelectionModel().clearSelection();
        cbDentista.requestFocus();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dpData.setEditable(false);
        auxHorarios = new ArrayList();

        colHorario.setCellValueFactory(new PropertyValueFactory("hora"));
        colPaciente.setCellValueFactory(new PropertyValueFactory("paciente"));

        carregaComboDentista();
        estadoOriginal();
        carregaTabela();
    }

    private void carregaComboDentista() {
        //Carregando comboBox de Dentistas
        CtrPessoa ctrD = new CtrPessoa();
        ArrayList<Pessoa> result = ctrD.getPessoa("", new Dentista());

        ObservableList<Pessoa> dentistas = FXCollections.observableArrayList(result);

        if (!result.isEmpty()) {
            cbDentista.setItems(dentistas);
            cbDentista.setValue(cbDentista.getItems().get(0)); //inicializando comboBox
        }
    }

    private void carregaTabela() {
        CtrAgenda ctrA = new CtrAgenda();
        HorariosList = new ArrayList();
        auxHorarios = new ArrayList();
        String vet[];

        Agenda age = ctrA.getAgendaDia((Dentista) cbDentista.getSelectionModel().getSelectedItem(), dpData.getValue());
        HorariosList = age.getArrayHorarios();

        for (int i = 8; i <= 17; i++) {
            auxHorarios.add(new Horarios(-1, LocalDate.now(), i + ":00", null, null));
        }

        for (int i = 0; i < auxHorarios.size(); i++) {
            for (int j = 0; j < HorariosList.size(); j++) {
                if (auxHorarios.get(i).getHora().equals(HorariosList.get(j).getHora())) {
                    auxHorarios.set(i, HorariosList.get(j));
                }
            }
        }

        table.getItems().clear();
        ObservableList<Horarios> horarios = FXCollections.observableArrayList(auxHorarios);
        table.setItems(horarios);
    }

    @FXML
    private void evtBtAgendar(ActionEvent event) {
        try {
            Effect frostEffect = new BoxBlur(4, 4, 1);
            pnAgendamento.setEffect(frostEffect);

            palco = new Stage();
            Scene cenario = new Scene(FXMLLoader.load(getClass().getResource("TelaPesPaciente.fxml")));

            palco.setScene(cenario);
            palco.initModality(Modality.APPLICATION_MODAL);
            palco.showAndWait();
            pnAgendamento.setEffect(null);

            //ver se o paciente é nulo, se não colocar na tabela
            if (auxPaciente != null) {

                if (dpData.getValue().isAfter(LocalDate.now().minusDays(1))) {
                    Agenda age = new Agenda((Dentista) cbDentista.getSelectionModel().getSelectedItem());//salvando dentista
                    Horarios hor = new Horarios(dpData.getValue(), (String) colHorario.getCellData(table.getSelectionModel().getFocusedIndex()), auxPaciente, null);//salvando outros dados

                    CtrAgenda ctrA = new CtrAgenda();
                    if (ctrA.salvarAgendamento(age, hor)) {
                        carregaTabela();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Data Inválida");
                    alert.showAndWait();
                }
            }
            estadoOriginal();

        } catch (Exception e) {
            System.out.println("Erro de abertura: " + e.getMessage());
        }
    }

    @FXML
    private void evtBtDesmarcar(ActionEvent event) {
         Alert alert;
        CtrAgenda ctrA = new CtrAgenda();
        Horarios h = table.getSelectionModel().getSelectedItem();
        
        if(h.getConsulta().isStatus()){
            alert =  new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("O Paciente '" + h.getPaciente() + "' Já Foi Consultado.\n Ele não Pode ser Exclúido.");
            alert.showAndWait();
            estadoOriginal();
        }
        else{
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Confirmar o Desmarque de Consulta de: \n" + h.getPaciente());

            if (alert.showAndWait().get() == ButtonType.OK)
                ctrA.apagarAgendamento(h);
            
            btDesmarcar.setDisable(true);
            carregaTabela();
        }
    }

    @FXML
    private void evtBtFechar(ActionEvent event) {
        _lbTelaCadAtual.setText("");
        if (btAgendar.isDisable()) {
            TelaPrincipalController._pnDados.getChildren().clear();
        } else {
            estadoOriginal();
        }
    }

    @FXML
    private void evtTable(MouseEvent event) {
        if (table.getSelectionModel().getSelectedIndex() >= 0) {
            if (table.getSelectionModel().getSelectedItem().getPaciente() == null) {
                btAgendar.setDisable(false);
                btDesmarcar.setDisable(true);
            } else {
                btAgendar.setDisable(true);
                btDesmarcar.setDisable(false);
            }
        }
    }

    @FXML
    private void evtCbDentista(ActionEvent event) {
        carregaTabela();
    }

    @FXML
    private void evtDpData(ActionEvent event) {
        carregaTabela();
    }

}
