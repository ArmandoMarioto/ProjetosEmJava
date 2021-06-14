/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces.Fundamentais;

import Controladoras.ctrFuncionarios;
import Controladoras.ctrOS;
import Controladoras.ctrStatus;
import Utils.Objeto;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Caique
 */
public class FXMLOSController implements Initializable
{

    @FXML
    private JFXComboBox<String> cb_status;
    @FXML
    private JFXDatePicker dp_data;
    @FXML
    private JFXTextArea ta_descricao;
    @FXML
    private JFXButton btn_alterar;
    @FXML
    private JFXButton bnt_fechar;
    @FXML
    private JFXButton btn_iniciar;
    @FXML
    private TableView<Objeto> tv_os;
    @FXML
    private TableColumn<Object, String> tc_nome;
    @FXML
    private TableColumn<Object, String> tc_veiculo;
    @FXML
    private TableColumn<Object, String> tc_status;
    @FXML
    private TableColumn<Object, String> tc_data;
    @FXML
    private TableColumn<Object, String> tc_descricao;
    @FXML
    private JFXButton btn_help;
    @FXML
    private JFXTextField tf_funcionario;
    
    ArrayList<String> nomes = new ArrayList<>();
    ArrayList<String> status = new ArrayList<>();
    private final Tooltip t = new Tooltip();
    public static int cod;
    @FXML
    private JFXButton btn_cancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        t.setText("Não achou o status? Clique aqui para adiciona-lo");
        btn_help.setTooltip(t);
        
        tc_data.setCellValueFactory(new PropertyValueFactory<>("param8"));
        tc_descricao.setCellValueFactory(new PropertyValueFactory<>("param2"));
        tc_nome.setCellValueFactory(new PropertyValueFactory<>("param5"));
        tc_status.setCellValueFactory(new PropertyValueFactory<>("param7"));
        tc_veiculo.setCellValueFactory(new PropertyValueFactory<>("param6"));
        
        inicializa();
    }    
    
    public void inicializa()
    {
        nomes = new ctrFuncionarios().buscaTodos();
        TextFields.bindAutoCompletion(tf_funcionario, nomes);
        
        ObservableList<String> data = FXCollections.observableArrayList(new ctrStatus().buscaTodos());
        cb_status.setItems(data);
        cb_status.getSelectionModel().select(0);
        
        altera_campos(true, true, true, true);
        cod = -1;
        btn_alterar.setText("Alterar Status");
        limpa();
        procura();
    }
    
    public void limpa()
    {
        tf_funcionario.clear(); cb_status.getSelectionModel().select(0); ta_descricao.clear(); dp_data.setValue(LocalDate.now());
    }
    
    public void altera_campos(boolean b1,boolean b2,boolean b3,boolean b4)
    {
        tf_funcionario.setDisable(b1);
        ta_descricao.setDisable(b1);
        cb_status.setDisable(b1);
        dp_data.setDisable(b1);
        btn_iniciar.setDisable(b2);
        btn_alterar.setDisable(b3);
        bnt_fechar.setDisable(b4);
    }

    @FXML
    private void clickAlterar(ActionEvent event)
    {
        if(btn_alterar.getText().equals("Alterar Status"))
        {
            btn_alterar.setText("Confirmar");
            altera_campos(false, true, false, true);
        }
        else
        {
            if(valida_campos().equals(""))
            {
                Alert alert;
                if(cb_status.getSelectionModel().getSelectedItem().equals("finalizado"))
                {
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Alterar a Ordem de Serviço com Status finalizado irá finalizar a manutenção. Deseja Continuar?");

                    ButtonType btn_yes = new ButtonType("Yes");
                    ButtonType btn_no = new ButtonType("No");

                    alert.getButtonTypes().setAll(btn_yes,btn_no);

                    Optional<ButtonType> result = alert.showAndWait();
                    if(result.get() == btn_yes)
                        clickFechar(event);
                }
                else
                {
                    if(new ctrOS().alterar(cb_status, tf_funcionario, ta_descricao, dp_data, cod))
                    {
                        alert = new Alert(Alert.AlertType.INFORMATION, "Ordem de serviço alterada com sucesso!", ButtonType.OK);
                        inicializa();
                    }
                    else
                        alert = new Alert(Alert.AlertType.ERROR, "Erro na alteração da Ordem de serviço", ButtonType.OK);
                    alert.showAndWait();
                }
            }
        }
    }

    @FXML
    private void clickFechar(ActionEvent event)
    {
        Alert alert;
        cb_status.getSelectionModel().select("finalizado");
        if(new ctrOS().alterar(cb_status, tf_funcionario, ta_descricao, dp_data, cod))
        {
            alert = new Alert(Alert.AlertType.INFORMATION, "Ordem de serviço finalizada com sucesso!", ButtonType.OK);
            inicializa();
        }
        else
            alert = new Alert(Alert.AlertType.ERROR, "Erro na alteração da Ordem de serviço", ButtonType.OK);
        alert.showAndWait();
    }

    @FXML
    private void clickIniciar(ActionEvent event)
    {
        Alert alert;
        if(tv_os.getSelectionModel().getSelectedItem().getParam7().equals("não iniciado"))
        {
            cb_status.getSelectionModel().select(0);
            if(new ctrOS().alterar(cb_status, tf_funcionario, ta_descricao, dp_data, cod))
            {
                alert = new Alert(Alert.AlertType.INFORMATION, "Ordem de serviço foi iniciada", ButtonType.OK);
                inicializa();
            }
            else
                alert = new Alert(Alert.AlertType.ERROR, "Erro na alteração da Ordem de serviço", ButtonType.OK);
        }
        else
            alert = new Alert(Alert.AlertType.ERROR, "Ordem de serviço já iniciada", ButtonType.OK);
        alert.showAndWait();
    }

    @FXML
    private void clickStatus(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/Interfaces/FXMLStatus.fxml"));
        Stage s1 = new Stage();
        Scene scene = new Scene(root);

        s1.setScene(scene);
        s1.initModality(Modality.APPLICATION_MODAL);
        s1.setTitle("Login");
        s1.showAndWait();
        inicializa();
    }
    
    public void procura()
    {
        ArrayList<Object[]> list;
        ArrayList<Objeto> obsList = new ArrayList<>();
        Objeto obj;
        
        list = new ctrOS().procurar();
        for(int i = 0; i < list.size();i++)
        {
            obj = new Objeto(String.valueOf(list.get(i)[0]),(String)list.get(i)[1],String.valueOf(list.get(i)[2]),
                String.valueOf(list.get(i)[3]),(String)list.get(i)[4],(String)list.get(i)[5],(String)list.get(i)[6],
            String.valueOf(list.get(i)[7]));
            obsList.add(obj);
        }
        tv_os.setItems(FXCollections.observableArrayList(obsList));
    }
    
    public String valida_campos()
    {
        String erro = "";
        if(!nomes.contains(tf_funcionario.getText()))
            erro = "Funcionário não cadastrado";
        else if(dp_data.getValue().compareTo(LocalDate.now()) > 0)
            erro = "Data inválida";
        return erro;
    }

    @FXML
    private void clickTabela(MouseEvent event)
    {
        
        if(event.getClickCount() == 2 && tv_os.getSelectionModel().getSelectedItem() != null)
        {
            if(tv_os.getSelectionModel().getSelectedItem().getParam7().equals("não iniciado"))
                altera_campos(true, false, true, true);
            else
                altera_campos(true, true, false, false);
            cod = Integer.parseInt(tv_os.getSelectionModel().getSelectedItem().getParam1());
            cb_status.getSelectionModel().select(tv_os.getSelectionModel().getSelectedItem().getParam7());
            tf_funcionario.setText(new ctrFuncionarios().busca(Integer.parseInt(tv_os.getSelectionModel().getSelectedItem().getParam3())));
            if(!tv_os.getSelectionModel().getSelectedItem().getParam8().equals("null"))
                dp_data.setValue(LocalDate.parse(tv_os.getSelectionModel().getSelectedItem().getParam8()));
        }
    }

    @FXML
    private void clickCancelar(ActionEvent event)
    {
        inicializa();
    }
}
