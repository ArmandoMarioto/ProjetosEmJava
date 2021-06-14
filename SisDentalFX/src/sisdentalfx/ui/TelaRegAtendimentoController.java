package sisdentalfx.ui;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.ListSpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import sisdentalfx.db.controles.CtrAgenda;
import sisdentalfx.db.controles.CtrMaterial;
import sisdentalfx.db.controles.CtrPessoa;
import sisdentalfx.db.controles.CtrProcedimentos;
import sisdentalfx.db.entidades.Agenda;
import sisdentalfx.db.entidades.Consulta;
import sisdentalfx.db.entidades.Dentista;
import sisdentalfx.db.entidades.Horarios;
import sisdentalfx.db.entidades.Material;
import sisdentalfx.db.entidades.Pessoa;
import sisdentalfx.db.entidades.Procedimentos;
import static sisdentalfx.ui.TelaPrincipalController._lbTelaCadAtual;

public class TelaRegAtendimentoController implements Initializable {

    private ArrayList<Horarios> auxHorarios;
    private ArrayList<Horarios> HorariosList;
    private ObservableList<Integer> spItems;
    private Horarios con; //obj tipo horario, para modificar a consulta
    private Consulta.itensMat matDel;
    private Consulta.itensProc proDel;
    
    //var´s para caso o individuo cancele suas ações
    private Horarios auxCon;
    
    @FXML
    private HBox pnPrincipal;
    @FXML
    private AnchorPane pnPacientes;
    @FXML
    private TableView<Horarios> tablePacientes;
    @FXML
    private TableColumn colHora;
    @FXML
    private TableColumn colPaciente;
    @FXML
    private DatePicker dpData;
    @FXML
    private AnchorPane pnDados;
    @FXML
    private TextArea taObs;
    @FXML
    private ComboBox<Material> cbMaterial;
    @FXML
    private Spinner<Integer> spMaterial;
    @FXML
    private Button btPlusMat;
    @FXML
    private Button btNegMat;
    @FXML
    private TableView<Consulta.itensMat> tableMaterial;
    @FXML
    private TableColumn colMaterial;
    @FXML
    private TableColumn colQuantidadeMat;
    @FXML
    private Button btConfirmar;
    @FXML
    private Button btCancelar;
    @FXML
    private ComboBox<Procedimentos> cbProcedimentos;
    @FXML
    private Spinner<Integer> spProcedimentos;
    @FXML
    private Button btPlusPro;
    @FXML
    private Button btNegProc;
    @FXML
    private TableView<Consulta.itensProc> TableProcedimentos;
    @FXML
    private TableColumn colProcedimentos;
    @FXML
    private TableColumn colQuantidadePro;
    @FXML
    private ComboBox<Pessoa> cbDentista;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = new Horarios();
        auxCon = new Horarios();
        matDel = null;
        proDel = null;
        
        dpData.setValue(LocalDate.now());
        btPlusMat.setDisable(true);
        btPlusPro.setDisable(true);
        btNegMat.setDisable(true);
        btNegProc.setDisable(true);
        
        //configurando spinner
        spItems = FXCollections.observableArrayList();
        for (int i = 0; i < 100; i++) 
            spItems.add(i);
        spProcedimentos.valueFactoryProperty().set(new ListSpinnerValueFactory<>(spItems));
        spMaterial.valueFactoryProperty().set(new ListSpinnerValueFactory<>(spItems));
        
        
        //tabela pacientes
        colHora.setCellValueFactory(new PropertyValueFactory("hora"));
        colPaciente.setCellValueFactory(new PropertyValueFactory("paciente"));
        
        //tabela material
        colMaterial.setCellValueFactory(new PropertyValueFactory("material"));
        colQuantidadeMat.setCellValueFactory(new PropertyValueFactory("qtd"));
        
        //tabela procedimentos
        colProcedimentos.setCellValueFactory(new PropertyValueFactory("procedimentos"));
        colQuantidadePro.setCellValueFactory(new PropertyValueFactory("qtd"));

        carregaComboBoxMaterial();
        carregaComboBoxProcedimentos();
        carregaComoboBoxDentitas();
        carregaTabelaPaciente();
        
        estadoOriginal();
    }

    private void estadoOriginal() {
        dpData.setValue(LocalDate.now());
        pnDados.setDisable(true);
        pnPacientes.setDisable(false);
        tablePacientes.getSelectionModel().clearSelection();
        
        //configurando spinner
        spItems.clear();
        for (int i = 0; i < 100; i++) 
            spItems.add(i);
        spProcedimentos.valueFactoryProperty().set(new ListSpinnerValueFactory<>(spItems));
        spMaterial.valueFactoryProperty().set(new ListSpinnerValueFactory<>(spItems));
        spMaterial.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100));
        spProcedimentos.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100));
        
        //arrumando botões
        btPlusMat.setDisable(true);
        btPlusPro.setDisable(true);
        btNegMat.setDisable(true);
        btNegProc.setDisable(true);

        taObs.setText("");
        spMaterial.getEditor().textProperty().setValue("0");
        spProcedimentos.getEditor().textProperty().setValue("0");
        TableProcedimentos.getSelectionModel().clearSelection();
        tableMaterial.getSelectionModel().clearSelection();
        tablePacientes.getSelectionModel().clearSelection();
        TableProcedimentos.getItems().clear();
        tableMaterial.getItems().clear();
        
        //dando null para objetos;
        con = null;
        matDel = null;
        proDel = null;
        
        cbDentista.requestFocus();
    }
    
    private void  estadoOriginalPnDados(){
        //configurando spinner
        spItems.clear();
        for (int i = 0; i < 100; i++) 
            spItems.add(i);
        spProcedimentos.valueFactoryProperty().set(new ListSpinnerValueFactory<>(spItems));
        spMaterial.valueFactoryProperty().set(new ListSpinnerValueFactory<>(spItems));
        spMaterial.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100));
        spProcedimentos.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100));
        spMaterial.getEditor().textProperty().setValue("0");
        spProcedimentos.getEditor().textProperty().setValue("0");
        
        //arrumando comboBox
        cbMaterial.setValue(cbMaterial.getItems().get(0));
        cbProcedimentos.setValue(cbProcedimentos.getItems().get(0));
        
        //arrumando tabelas
        tableMaterial.getSelectionModel().clearSelection();
        tablePacientes.getSelectionModel().clearSelection();
        
        //arrumando botões
        btPlusMat.setDisable(true);
        btPlusPro.setDisable(true);
        btNegMat.setDisable(true);
        btNegProc.setDisable(true);
        
        //dando null para objetos;
        matDel = null;
        proDel = null; 
    }

    private void carregaComboBoxProcedimentos() {
        CtrProcedimentos ctrP = new CtrProcedimentos();
        ArrayList<Procedimentos> listaP = ctrP.getProcedimentos("");

        ObservableList<Procedimentos> procedimentos = FXCollections.observableArrayList(listaP);

        if (!listaP.isEmpty()) {
            cbProcedimentos.setItems(procedimentos);
            cbProcedimentos.setValue(cbProcedimentos.getItems().get(0));
        }
    }

    private void carregaComboBoxMaterial() {
        CtrMaterial ctrM = new CtrMaterial();
        ArrayList<Material> listaM = ctrM.getMaterial("");

        ObservableList<Material> materiais = FXCollections.observableArrayList(listaM);

        if (!listaM.isEmpty()) {
            cbMaterial.setItems(materiais);
            cbMaterial.setValue(cbMaterial.getItems().get(0));
        }
    }

    private void carregaComoboBoxDentitas() {
        //Carregando comboBox de Dentistas
        CtrPessoa ctrD = new CtrPessoa();
        ArrayList<Pessoa> result = ctrD.getPessoa("", new Dentista());

        ObservableList<Pessoa> dentistas = FXCollections.observableArrayList(result);

        if (!result.isEmpty()) {
            cbDentista.setItems(dentistas);
            cbDentista.setValue(cbDentista.getItems().get(0)); //inicializando comboBox
        }
    }

    private void carregaTabelaPaciente() {
        CtrAgenda ctrA = new CtrAgenda();
        HorariosList = new ArrayList();
        auxHorarios = new ArrayList();

        Agenda age = ctrA.getAgendaDia((Dentista) cbDentista.getSelectionModel().getSelectedItem(), dpData.getValue());
        HorariosList = age.getArrayHorarios();

        for (int i = 8; i <= 17; i++) {
            auxHorarios.add(new Horarios(-1, LocalDate.now(), i + ":00", null, null));
        }

        String vet[];

        for (int i = 0; i < auxHorarios.size(); i++) {
            for (int j = 0; j < HorariosList.size(); j++) {
                if (auxHorarios.get(i).getHora().equals(HorariosList.get(j).getHora())) {
                    auxHorarios.set(i, HorariosList.get(j));
                }
            }
        }

        tablePacientes.getItems().clear();
        ObservableList<Horarios> horarios = FXCollections.observableArrayList(auxHorarios);
        tablePacientes.setItems(horarios);
    }

    @FXML
    private void evtTablePacientes(MouseEvent event) {
        if (tablePacientes.getSelectionModel().getSelectedItem().getPaciente() != null && tablePacientes.getSelectionModel().getSelectedIndex() >= 0) {
            
            if(dpData.getValue().isEqual(LocalDate.now()))//dpData.getValue().isAfter(LocalDate.now().minusDays(1))
            {
                auxCon = null;
                
                CtrAgenda ctrA =  new CtrAgenda();
                
                pnDados.setDisable(false);
                pnPacientes.setDisable(true);
                auxCon = con =  tablePacientes.getSelectionModel().getSelectedItem();
                con = ctrA.buscaConsulta(con);
                
                if(con != null){
                    auxCon.getConsulta().getMateriais().clear();
                    auxCon.getConsulta().getProcedimentos().clear();
                    
                    taObs.setText(con.getConsulta().getObs());
                    //Carregar tabela de material e procedimentos daquele paciente
                    carregaTabelaMaterial();
                    carregaTabelaProcedimentos();
                }
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Esta Consulta não está Marcada para hoje.");
                //alert.setHeaderText("A Data Selecionada Já Expirou.");
                alert.showAndWait();
                estadoOriginal();
            }  
        }
    }
    
    private void carregaTabelaMaterial(){
        CtrAgenda ctrA = new CtrAgenda();
        ArrayList<Consulta.itensMat> list = ctrA.getItensMat(con);
        ObservableList<Consulta.itensMat> itensMat = FXCollections.observableArrayList(list);
        tableMaterial.getItems().clear();
        tableMaterial.setItems(itensMat);
        
         //copiando arrayList Material
        for (int i = 0; i < list.size(); i++)
            auxCon.getConsulta().addMaterial(list.get(i).getMaterial(), list.get(i).getQtd());
    }
    
    private void carregaTabelaProcedimentos(){
        CtrAgenda ctrA = new CtrAgenda();
        ArrayList<Consulta.itensProc> list = ctrA.getItensPro(con);
        ObservableList<Consulta.itensProc> itensPro = FXCollections.observableArrayList(list);
        TableProcedimentos.getItems().clear();
        TableProcedimentos.setItems(itensPro);
        
        //copiando arrayList de procedimento
        for (int i = 0; i < list.size(); i++)
            auxCon.getConsulta().addProcedimentos(list.get(i).getProcedimentos(), list.get(i).getQtd());
    }

    @FXML
    private void evtBtPlusMat(ActionEvent event) {
        boolean flag = true;
        CtrAgenda ctrA = new CtrAgenda();
        
       
        for (int i = 0; flag && i <  con.getConsulta().getMateriais().size(); i++)
            if(con.getConsulta().getMateriais().get(i).getMaterial().getCodigo() == cbMaterial.getSelectionModel().getSelectedItem().getCodigo()){
                con.getConsulta().getMateriais().get(i).setQtd(con.getConsulta().getMateriais().get(i).getQtd() + spMaterial.getValue());
                flag = false;
            }
                    
        if(flag)
            con.getConsulta().addMaterial(cbMaterial.getSelectionModel().getSelectedItem(), spMaterial.getValue());
        
        tableMaterial.getItems().clear();
        ObservableList<Consulta.itensMat> itensMat = FXCollections.observableArrayList(con.getConsulta().getMateriais());
        tableMaterial.setItems(itensMat);
        estadoOriginalPnDados();
    }

    @FXML
    private void evtBtNegMat(ActionEvent event) {
        //se ja esta gravado no banco
        if(con.getConsulta().isStatus()){
            CtrAgenda ctrA =  new CtrAgenda();
            ctrA.apagarConMat(con, matDel);
        }
            
        for (int i = 0; i < con.getConsulta().getMateriais().size(); i++)
            if(matDel.getMaterial().getCodigo() == con.getConsulta().getMateriais().get(i).getMaterial().getCodigo())
                 con.getConsulta().getMateriais().remove(i);
        
        tableMaterial.getItems().clear();
        ObservableList<Consulta.itensMat> itensMat = FXCollections.observableArrayList(con.getConsulta().getMateriais());
        tableMaterial.setItems(itensMat);
        estadoOriginalPnDados();
    }

    @FXML
    private void evtTableMat(MouseEvent event) {
        if(tableMaterial.getSelectionModel().getSelectedIndex() >=0) {
            btNegMat.setDisable(false);
            matDel = tableMaterial.getSelectionModel().getSelectedItem();
        }
    }

    @FXML
    private void evtBtConfirmar(ActionEvent event) {
        CtrAgenda ctrA = new CtrAgenda();
        
        //salvando dentro do obj
        con.getConsulta().setObs(taObs.getText());
        con.getConsulta().setStatus(true);
        
        //gravando no banco uma consulta
        ctrA.salvarConsulta(con);
        
        //gravando no banco os materiais
        try {
            ctrA.SalvarConMat(con);
        } catch (Exception e) {
            System.out.println("ERRO AO SALVAR MATERIAIS: " + e.getMessage());
        }
        
        //gravando no banco os procedimentos
        try {
            ctrA.SalvarConPro(con);
        } catch (Exception e) {
            System.out.println("ERRO AO SALVAR PROCEDIMENTOS: " + e.getMessage());
        }
             
        estadoOriginal();
    }

    @FXML
    private void evtBtCancelar(ActionEvent event){
        if(pnDados.isDisable()){
            _lbTelaCadAtual.setText("");
            TelaPrincipalController._pnDados.getChildren().clear();
        }
        else{
            //se o fdp mudou varias fitas e apertou em cancela
            CtrAgenda ctrA = new CtrAgenda();
            //gravando no banco uma consulta
            ctrA.salvarConsulta(auxCon);

            //gravando no banco os materiais
            try {
                ctrA.SalvarConMat(auxCon);
            } catch (Exception e) {
                 System.out.println("ERRO AO SALVAR MATERIAIS: " + e.getMessage());
            }

             //gravando no banco os procedimentos
            try {
                ctrA.SalvarConPro(auxCon);
            } catch (Exception e) {
                 System.out.println("ERRO AO SALVAR PROCEDIMENTOS: " + e.getMessage());
            }
             
            estadoOriginal();
        }
            
    }

    @FXML
    private void evtBtPlusPro(ActionEvent event) {
        boolean flag = true;
        
        for (int i = 0; flag && i <  con.getConsulta().getProcedimentos().size(); i++)
            if(con.getConsulta().getProcedimentos().get(i).getProcedimentos().getCodigo() == cbProcedimentos.getSelectionModel().getSelectedItem().getCodigo()){
                con.getConsulta().getProcedimentos().get(i).setQtd(con.getConsulta().getProcedimentos().get(i).getQtd() + spProcedimentos.getValue());
                flag = false;
            }
                    
        if(flag)
            con.getConsulta().addProcedimentos(cbProcedimentos.getSelectionModel().getSelectedItem(), spProcedimentos.getValue());
        
        TableProcedimentos.getItems().clear();
        ObservableList<Consulta.itensProc> itensProc = FXCollections.observableArrayList(con.getConsulta().getProcedimentos());
        TableProcedimentos.setItems(itensProc);
        estadoOriginalPnDados();
    }

    @FXML
    private void evtBtNegPro(ActionEvent event) {
        //se ja esta gravado no banco
        if(con.getConsulta().isStatus()){
            CtrAgenda ctrA =  new CtrAgenda();
            ctrA.apagarConPro(con, proDel);
        }
        
        for (int i = 0; i < con.getConsulta().getProcedimentos().size(); i++)
            if(proDel.getProcedimentos().getCodigo() == con.getConsulta().getProcedimentos().get(i).getProcedimentos().getCodigo())
                 con.getConsulta().getProcedimentos().remove(i);
        
        TableProcedimentos.getItems().clear();
        ObservableList<Consulta.itensProc> itensProc = FXCollections.observableArrayList(con.getConsulta().getProcedimentos());
        TableProcedimentos.setItems(itensProc);
        estadoOriginalPnDados();
    }

    @FXML
    private void evtTablePro(MouseEvent event) {
        if(TableProcedimentos.getSelectionModel().getSelectedIndex() >=0) {
            btNegProc.setDisable(false);
            proDel = TableProcedimentos.getSelectionModel().getSelectedItem();
        }
    }

    @FXML
    private void evtDpData(ActionEvent event) {
        carregaTabelaPaciente();
    }

    @FXML
    private void evtCbDentista(ActionEvent event) {
        carregaTabelaPaciente();
    }

    @FXML
    private void evtSpMaterial(MouseEvent event) {
        if(spMaterial.getValue() > 0)
            btPlusMat.setDisable(false);
        else
            btPlusMat.setDisable(true);
    }

    @FXML
    private void evtSpProcedimentos(MouseEvent event) {
        if(spProcedimentos.getValue() > 0)
            btPlusPro.setDisable(false);
        else
            btPlusPro.setDisable(true);
    }
}
