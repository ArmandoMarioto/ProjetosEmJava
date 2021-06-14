/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trablfa;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Armando
 */
public class TelaPrincipalController implements Initializable {
    
    private Label label;
    @FXML
    private TextField tf_er;
    @FXML
    private TextField tf_pala;
    @FXML
    private Button bt_er;
    @FXML
    private Button bt_pala;
    @FXML
    private TableView<Palavra> tb_aceita;
    @FXML
    private TableColumn<Palavra, String> cl_aceita;
    @FXML
    private TableView<Palavra> tb_rejeita;
    @FXML
    private TableColumn<Palavra, String> cl_rejeita;
    
    private ObservableList<Palavra> obaceita;
    private ObservableList<Palavra> obrejeita;
    @FXML
    private Button bt_novo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        obaceita = FXCollections.observableArrayList();
        obrejeita = FXCollections.observableArrayList();
        cl_aceita.setCellValueFactory(new PropertyValueFactory<>("string"));
        cl_rejeita.setCellValueFactory(new PropertyValueFactory<>("string"));
        tb_aceita.setItems(obaceita);
        tb_rejeita.setItems(obrejeita);
    }    
    public void estadoinicial()
    {
        tf_er.setText(" ");
        tf_pala.setText(" ");
        tf_er.setDisable(false);
        bt_er.setDisable(false);
        tb_rejeita.getItems().clear();
        tb_aceita.getItems().clear();
        ///limpa tabela
    }

    @FXML
    private void evt_er(ActionEvent event) 
    {
        if(!tf_er.getText().isEmpty())
        {
           tf_er.setDisable(true);
           bt_er.setDisable(true);
        }
    }

    @FXML
    private void evt_pala(ActionEvent event) 
    {
        boolean t = tf_pala.getText().matches(tf_er.getText());
        if(t)
        {
            obaceita.add(new Palavra(tf_pala.getText()));
            tf_pala.setText(" ");
        }
        else
        {
            obrejeita.add(new Palavra(tf_pala.getText()));
            tf_pala.setText(" ");
        }
    }

    @FXML
    private void evt_novo(ActionEvent event) 
    {
        estadoinicial();
        
    }
    
}
