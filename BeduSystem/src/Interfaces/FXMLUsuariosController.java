/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Controladoras.ctrFuncionarios;
import Controladoras.ctrUsuario;
import Entidades.Funcionário;
import Entidades.Usuario;
import Utils.Objeto;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Caique
 */
public class FXMLUsuariosController implements Initializable
{
    final ToggleGroup group = new ToggleGroup();
    
    @FXML
    private TableColumn<Object, String> tc_nome;
    @FXML
    private TableColumn<Object, String> tc_login;
    @FXML
    private TableColumn<Object, String> tc_nivel;
    @FXML
    private JFXTextField tf_login;
    @FXML
    private JFXTextField tf_nome;
    @FXML
    private JFXPasswordField pf_senha;
    @FXML
    private JFXComboBox<String> cb_nivel;
    @FXML
    private JFXButton btn_novo;
    @FXML
    private JFXButton btn_alterar;
    @FXML
    private JFXButton btn_remover;
    @FXML
    private JFXTextField tf_busca;
    @FXML
    private JFXRadioButton rb_nome;
    @FXML
    private JFXRadioButton rb_login;
    @FXML
    private JFXRadioButton rb_nivel;
    @FXML
    private JFXButton btn_procurar;
    @FXML
    private JFXButton btn_cancelar;
    @FXML
    private TableView<Objeto> tv_usuarios;
    ArrayList<String> nomes = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        nomes = new ctrFuncionarios().buscaTodos();
        TextFields.bindAutoCompletion(tf_nome, nomes);
        
        rb_nome.setSelected(true);
        rb_nome.setToggleGroup(group);
        rb_login.setToggleGroup(group);
        rb_nivel.setToggleGroup(group);
        
        ObservableList<String> data = FXCollections.observableArrayList("alto", "medio", "baixo");
        cb_nivel.setItems(data);
        cb_nivel.getSelectionModel().select(0);
        
        tc_login.setCellValueFactory(new PropertyValueFactory<>("param1"));
        tc_nivel.setCellValueFactory(new PropertyValueFactory<>("param2"));
        tc_nome.setCellValueFactory(new PropertyValueFactory<>("param3"));
        /*tc_login.setCellValueFactory(new PropertyValueFactory<Usuario, String>("login"));
        tc_nivel.setCellValueFactory(new PropertyValueFactory<Usuario,String>("nivel"));
        tc_nome.setCellValueFactory(new PropertyValueFactory<Usuario, String>("funcionario"));
        tc_nome.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getFuncionario().getNome()));
        */
        inicializa();
    }   
    
    public void limpa()
    {
        tf_busca.clear();
        tf_login.clear();
        tf_nome.clear();
        pf_senha.clear();
        btn_alterar.setText("Alterar");
        btn_novo.setText("Novo");
    }
    
    public void altera_campos(boolean b1, boolean b2,boolean b3,boolean b4,boolean b5,boolean b6,boolean b7)
    {
        tf_nome.setDisable(b1);
        cb_nivel.setDisable(b2);
        tf_login.setDisable(b3);
        pf_senha.setDisable(b4);
        btn_novo.setDisable(b5);
        btn_alterar.setDisable(b6);
        btn_remover.setDisable(b7);
    }
    
    public void inicializa()
    {
        altera_campos(true, true, true, true, false, true, true);
        clickProcurar(new ActionEvent());
    }
    

    @FXML
    private void clickNovo(ActionEvent event)
    {
        altera_campos(false, false, false, false, false, true, true);
        Alert alert;
        if(btn_novo.getText().equals("Confirmar"))
        {            
            String res = valida_campos();
            if(res.equals(""))
            {
                boolean ativo = new ctrFuncionarios().getAtivo(tf_nome.getText());
                if(nomes.contains(tf_nome.getText()) && ativo)
                {
                    ArrayList<Object[]> list = new ctrUsuario().procurar(rb_nome, tf_nome);
                    if(list.isEmpty())
                    {
                            list = new ctrUsuario().procurar(rb_login, tf_login);
                            if(list.isEmpty())
                            {
                                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    if(new ctrUsuario().gravar(tf_nome, tf_login, pf_senha, cb_nivel))
                                    {
                                        alert.setContentText("Usuario adicionado com suceso");
                                        clickCancelar(event);
                                        inicializa();
                                    }
                                    else
                                        alert.setContentText("Erro na adição do usuário");
                            }
                            else
                                alert = new Alert(Alert.AlertType.ERROR, "Login já existente", ButtonType.NO);                
                    }
                    else
                        alert = new Alert(Alert.AlertType.ERROR, "Já existe um usuário vinculado ao funcionário", ButtonType.NO);
                }
                else
                    alert = new Alert(Alert.AlertType.ERROR, "Funcionário inexistente ou inativo", ButtonType.OK);
            }
            else
                alert = new Alert(Alert.AlertType.ERROR, res, ButtonType.NO);
            alert.showAndWait();
        }
        else
            btn_novo.setText("Confirmar");
    }

    @FXML
    private void clickAlterar(ActionEvent event) throws InterruptedException
    {
        if(btn_alterar.getText().equals("Alterar"))
        {
            altera_campos(false, false, false, false, true, false, false);
            btn_alterar.setText("Confirmar");
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Deseja alterar " + tf_nome.getText() + "?");
            alert.setTitle("Alteração");

            ButtonType btn_yes = new ButtonType("Yes");
            ButtonType btn_no = new ButtonType("No");

            alert.getButtonTypes().setAll(btn_yes,btn_no);

            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == btn_yes)
            {
                String res = valida_campos();
                if(res.equals("") &&  new ctrUsuario().alterar(tf_nome, tf_login, pf_senha, cb_nivel))
                {
                    alert = new Alert(Alert.AlertType.INFORMATION,"Alteração concluida com sucesso",ButtonType.OK);
                    clickCancelar(event);
                    inicializa();
                }
                else if(!res.equals(""))
                    alert = new Alert(Alert.AlertType.INFORMATION,res,ButtonType.OK);
                else
                    alert = new Alert(Alert.AlertType.INFORMATION,"Erro na alteração, por favor revise os campos",ButtonType.OK);
                alert.showAndWait();
            }
        }
        
    }

    @FXML
    private void clickRemover(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Deseja remover " + tf_nome.getText() + "?");
        alert.setTitle("Remoção");
        
        ButtonType btn_yes = new ButtonType("Yes");
        ButtonType btn_no = new ButtonType("No");
        
        alert.getButtonTypes().setAll(btn_yes,btn_no);
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == btn_yes)
        {
            alert = new Alert(Alert.AlertType.INFORMATION);
            if(new ctrUsuario().remover(tf_login))
            {
                alert.setContentText("Exclusão concluida com sucesso");
                clickCancelar(event);
                inicializa();
            }
            else
                alert.setContentText("Erro na exclusão do usuário");
            alert.showAndWait();
        }      
        
    }

    @FXML
    private void clickProcurar(ActionEvent event)
    {
        ArrayList<Object[]> list;
        ArrayList<Objeto> obsList = new ArrayList<>();
        Objeto obj;
        //ObservableList<Usuario> usuarios;
        if(rb_login.isSelected())
             //usuarios = new ctrUsuario().procurar(rb_login, tf_busca);
            list = new ctrUsuario().procurar(rb_login, tf_busca);
        else if(rb_nivel.isSelected())
            list = new ctrUsuario().procurar(rb_nivel, tf_busca);
        else
            list = new ctrUsuario().procurar(rb_nome, tf_busca);
        
        for (int i = 0; i < list.size(); i++) 
        { 
            obj = new Objeto((String)list.get(i)[0], String.valueOf(list.get(i)[1]), (String)list.get(i)[2]);
            obsList.add(obj);    
        }
       
        tv_usuarios.setItems(FXCollections.observableArrayList(obsList));
    }

    @FXML
    private void clickCancelar(ActionEvent event)
    {
        limpa();
        inicializa();
    }

    @FXML
    private void clickTabela(MouseEvent event)
    {
        if(event.getClickCount() == 2 && tv_usuarios.getSelectionModel().getSelectedItem() != null)
        {
            altera_campos(true, true, true, true, true, false, false);
            tf_login.setText(tv_usuarios.getSelectionModel().getSelectedItem().getParam1());
            tf_nome.setText(tv_usuarios.getSelectionModel().getSelectedItem().getParam3());
            switch(tv_usuarios.getSelectionModel().getSelectedItem().getParam2())
            {
                case "0":
                    cb_nivel.getSelectionModel().select(0);
                    break;
                case "1":
                    cb_nivel.getSelectionModel().select(1);
                    break;
                case "2":
                    cb_nivel.getSelectionModel().select(2);
                    break;
            }
            pf_senha.setText(new ctrUsuario().getSenha(tf_nome));
        }
    }
    
    public String valida_campos()
    {
        String erro = "";
        
        if(!tf_nome.getText().equals(""))
        {
            if(!tf_login.getText().equals(""))
            {
                if(pf_senha.getText().equals(""))
                    erro = "Campo Senha obrigatório";
            }    
            else
                erro = "Campo Login obrigatório";
        }  
        else
            erro = "Campo Nome obrigatório";
        return erro;
    }
}
