/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entidades.Usuario;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Caique
 */
public class FXMLLoginController implements Initializable
{
    private final Tooltip t = new Tooltip();
    @FXML
    private JFXTextField tf_login;
    @FXML
    private JFXPasswordField tf_senha;
    @FXML
    private JFXButton btn_entrar;
    @FXML
    private Label lb_erro;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void clickEntrar(ActionEvent event)
    {   
        lb_erro.setText("");
        if(!tf_login.getText().equals(""))
            if(!tf_senha.getText().equals(""))
            {
                Usuario user = new Usuario();
                if(user.busca(tf_login.getText()))
                {
                    if(user.getSenha().equals(tf_senha.getText()))
                    {
                        bedusystem.FXMLPrincipalController.nivel = user.getNivel();
                        //Platform.exit();
                        btn_entrar.getScene().getWindow().hide();
                        System.out.println("achou");
                    }
                    else
                    {
                        lb_erro.setText("Senha incorreta, por favor digite novamente");
                        tf_senha.clear();
                        t.setText("Senha incorreta");
                        tf_senha.setTooltip(t);
                    }
                }
                else
                {
                    lb_erro.setText("Usuario não encontrado, por favor digite novamente");
                    tf_login.clear();
                    tf_senha.clear();
                    t.setText("Usuario não encontrado");
                    tf_login.setTooltip(t);
                }
            }
            else
            {
                lb_erro.setText("Digite uma senha");
                t.setText("Digite o campo da senha corretamente");
                tf_senha.setTooltip(t);
            }
        else
        {
            lb_erro.setText("Digite um login");
            t.setText("Digite o campo do login corretamente");
            tf_login.setTooltip(t);
        }
    }

    @FXML
    private void clickEnter(KeyEvent event)
    {
        if(event.getCode().equals(KeyCode.ENTER))
            clickEntrar(new ActionEvent());
    }
    
}
