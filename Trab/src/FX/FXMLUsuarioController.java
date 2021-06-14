package FX;

import Classes.Usuario;
import Controladoras.ctrUsuario;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class FXMLUsuarioController implements Initializable
{
    private Usuario s_usuario;
    @FXML
    private JFXButton btn_novo;
    @FXML
    private JFXButton btn_alterar;
    @FXML
    private JFXButton btn_apagar;
    @FXML
    private JFXButton btn_limpar;
    @FXML
    private JFXTextField tf_codigo;
    @FXML
    private JFXTextField tf_usuario;
    @FXML
    private JFXTextField tf_senha;
    @FXML
    private JFXCheckBox cb_adm;
    @FXML
    private JFXTextField tf_buscar;
    @FXML
    private Button btn_buscar;
    @FXML
    private TableView<Usuario> tv_table;
    @FXML
    private TableColumn<Usuario, Integer> c_codigo;
    @FXML
    private TableColumn<Usuario, String> c_usuario;
    @FXML
    private TableColumn<Usuario, String> c_senha;
    @FXML
    private TableColumn<Usuario, Boolean> c_tipo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        tf_codigo.setDisable(true);
        Inicial();
        c_codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        c_usuario.setCellValueFactory(new PropertyValueFactory<>("nome"));
        c_senha.setCellValueFactory(new PropertyValueFactory<>("senha"));
        c_tipo.setCellValueFactory(new PropertyValueFactory<>("adm"));
    }    

    private void Inicial()
    {
        btn_novo.setDisable(false);
        btn_alterar.setDisable(true);
        btn_apagar.setDisable(true);
        btn_limpar.setDisable(true);
        btn_novo.setText("Novo");
        
        tf_usuario.setDisable(true);
        tf_senha.setDisable(true);
        cb_adm.setDisable(true);
    }
    
    private void Liberar()
    {
        btn_alterar.setDisable(false);
        btn_apagar.setDisable(false);
        btn_limpar.setDisable(false);
        tf_usuario.setDisable(false);
        tf_senha.setDisable(false);
        cb_adm.setDisable(false);
    }
    
    private void Limpar()
    {
        tf_codigo.setText("");
        tf_usuario.setText("");
        tf_senha.setText("");
        tf_buscar.setText("");
        cb_adm.setSelected(false);
    }
    
    @FXML
    private void ClickNovo(ActionEvent event) throws SQLException
    {
        if(btn_novo.getText().equals("Novo"))
        {
            Liberar();
            btn_novo.setText("Salvar");
        }
        else//salvar
        {
            if(!tf_usuario.getText().isEmpty() && !tf_senha.getText().isEmpty())
            {
                Usuario user = new Usuario(0, tf_usuario.getText(), tf_senha.getText(), cb_adm.isSelected());
                if(new ctrUsuario().Salvar(user))
                {
                    Limpar();
                    Inicial();
                    tv_table.setItems(new ctrUsuario().BuscarTodos());
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Falha na Inserção");
                    alert.showAndWait();
                }
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Dados insuficientes");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void ClickAlterar(ActionEvent event)
    {
        if(!tf_usuario.getText().isEmpty() && !tf_senha.getText().isEmpty())
        {
            s_usuario.setNome(tf_usuario.getText());
            s_usuario.setSenha(tf_senha.getText());
            s_usuario.setAdm(cb_adm.isSelected());
            
            if(new ctrUsuario().Alterar(s_usuario))
            {
                Limpar();
                Inicial();
                tv_table.setItems(new ctrUsuario().BuscarTodos());
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Falha na Alteração");
                alert.showAndWait();
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Dados Insuficientes");
            alert.showAndWait();
        }
    }

    @FXML
    private void ClickApagar(ActionEvent event)
    {
        if(new ctrUsuario().Apagar(s_usuario))
        {
            Limpar();
            Inicial();
            tv_table.setItems(new ctrUsuario().BuscarTodos());
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Falha na Exclusão");
            alert.showAndWait();
            Limpar();
        }
    }

    @FXML
    private void ClickBuscar(ActionEvent event)
    {
        if(tf_buscar.getText().isEmpty())
            tv_table.setItems(new ctrUsuario().BuscarTodos());
        else
        {
            tv_table.getItems().clear();
            tv_table.getItems().add(new ctrUsuario().Procurar(tf_buscar.getText()));
        }     
    }

    @FXML
    private void ClickLimpar(ActionEvent event)
    {
        Limpar();
        Inicial();
    }

    @FXML
    private void ClickTabela(MouseEvent event)
    {
        if(tv_table.getSelectionModel().getSelectedIndex() >= 0)
        {
            Inicial();
            Liberar();
            Limpar();
            btn_novo.setDisable(true);
            s_usuario = tv_table.getSelectionModel().getSelectedItem();
            tf_codigo.setText(String.valueOf(s_usuario.getCodigo()));
            tf_usuario.setText(s_usuario.getNome());
            tf_senha.setText(s_usuario.getSenha());
            cb_adm.setSelected(s_usuario.isAdm());
        }
    }
    
}
