/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Controladoras.ctrStatus;
import Utils.Objeto;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Caique
 */
public class FXMLStatusController implements Initializable
{

    @FXML
    private JFXTextField tf_status;
    @FXML
    private TableView<Objeto> tv_tabela;
    @FXML
    private TableColumn<Object, String> tc_status;
    @FXML
    private JFXButton btn_novo;
    @FXML
    private JFXButton bnt_alterar;
    @FXML
    private JFXButton bnt_remover;
    @FXML
    private JFXButton btn_cancelar;
    private static int cod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        tc_status.setCellValueFactory(new PropertyValueFactory<>("param2"));
        inicializa();
    } 
    
    public void inicializa()
    {
        procura();
        tf_status.clear();
        btn_novo.setText("Novo");
        bnt_alterar.setText("Alterar");
        altera_campos(true, false, true, true);
    }
    
    public void altera_campos(boolean b1,boolean b2,boolean b3,boolean b4)
    {
        tf_status.setDisable(b1);
        btn_novo.setDisable(b2);
        bnt_alterar.setDisable(b3);
        bnt_remover.setDisable(b4);
    }
    
    public void procura()
    {
        ArrayList<Object[]> list;
        ArrayList<Objeto> obsList = new ArrayList<>();
        Objeto obj;
        
        list = new ctrStatus().buscaTodosS();
        for (int i = 0; i < list.size(); i++) 
        { 
            obj = new Objeto(String.valueOf(list.get(i)[0]), (String)list.get(i)[1]);  
            obsList.add(obj);
        }
        tv_tabela.setItems(FXCollections.observableArrayList(obsList));
    }

    @FXML
    private void clickTabela(MouseEvent event)
    {
        if(event.getClickCount() == 2 && tv_tabela.getSelectionModel().getSelectedItem() != null)
        {
            tf_status.setText(tv_tabela.getSelectionModel().getSelectedItem().getParam2());
            cod = Integer.parseInt(tv_tabela.getSelectionModel().getSelectedItem().getParam1());
            altera_campos(false, true, false, false);
        }
    }

    @FXML
    private void clickNovo(ActionEvent event)
    {
        if(btn_novo.getText().equals("Novo"))
        {
            altera_campos(false, false, true, true);
            btn_novo.setText("Confirmar");
        }
        else
        {
            Alert alert;
            if(!tf_status.getText().equals(""))
            {
                if(new ctrStatus().gravar(tf_status))
                {
                    alert = new Alert(Alert.AlertType.INFORMATION, "Status cadastrado com sucesso!", ButtonType.OK);
                    inicializa();
                }
                else
                    alert = new Alert(Alert.AlertType.ERROR, "Erro no cadastro", ButtonType.OK);
            }
            else
                alert = new Alert(Alert.AlertType.ERROR, "Digite um status válido", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void clickAlterar(ActionEvent event)
    {
        if(btn_novo.getText().equals("Novo"))
        {
            altera_campos(false, true, false, true);
            bnt_remover.setText("Confirmar");
        }
        else
        {
            Alert alert;
            if(!tf_status.getText().equals(""))
            {
                if(new ctrStatus().alterar(tf_status,cod))
                {
                    alert = new Alert(Alert.AlertType.INFORMATION, "Status alterado com sucesso!", ButtonType.OK);
                    inicializa();
                }
                else
                    alert = new Alert(Alert.AlertType.ERROR, "Erro na alteração", ButtonType.OK);
            }
            else
                alert = new Alert(Alert.AlertType.ERROR, "Digite um status válido", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void clickRemover(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Deseja remover " + tf_status.getText() + "?");
        alert.setTitle("Alteração");

        ButtonType btn_yes = new ButtonType("Yes");
        ButtonType btn_no = new ButtonType("No");

        alert.getButtonTypes().setAll(btn_yes,btn_no);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == btn_yes)
        {
            if(new ctrStatus().remover(cod))
            {
                alert = new Alert(Alert.AlertType.INFORMATION, "Status excluido com sucesso", ButtonType.OK);
                inicializa();
            }
            else
                alert = new Alert(Alert.AlertType.ERROR, "Erro na exclusão", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void clickCancelar(ActionEvent event)
    {
        inicializa();
    }
    
}
