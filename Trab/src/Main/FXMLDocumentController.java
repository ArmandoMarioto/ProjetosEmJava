package Main;

import Classes.SystemData;
import Classes.Usuario;
import Controladoras.ctrSystem;
import Controladoras.ctrUsuario;
import JDBC.Banco;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable
{
    public static Stage STAGE;
    public static SystemData SYSTEM;
    @FXML
    private VBox vb_scene;
    @FXML
    private ImageView img_logo;
    @FXML
    private JFXTextField tf_usuario;
    @FXML
    private JFXPasswordField tf_senha;
    @FXML
    private JFXButton btn_logar;
    @FXML
    private Menu m_adm;
    @FXML
    private Menu m_func;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        m_adm.setDisable(true);
        m_func.setDisable(true);
        SYSTEM = new ctrSystem().getSystemData();
        img_logo.setImage(SYSTEM.getImg_big());
        STAGE.setTitle(SYSTEM.getSoftware_name() + "  #  Waiting login...");
    }    

    @FXML
    private void ClickLogar(ActionEvent event)
    {
        if(!tf_usuario.getText().isEmpty() && !tf_senha.getText().isEmpty())
        {
            Usuario user = new ctrUsuario().BuscarLoginSenha(tf_usuario.getText(), tf_senha.getText());
            
            if(user != null)
            {
                String access = SYSTEM.getSoftware_name() + "  #  " + (user.isAdm() ? "[Administrador] - " : "[Funcionario] - ") + user.getNome();
                STAGE.setTitle(access);
                if(user.isAdm())
                    m_adm.setDisable(false);
                m_func.setDisable(false);
                vb_scene.getChildren().clear();
                vb_scene.setPadding(new Insets(0, 0, 0, 0));
                vb_scene.setAlignment(Pos.CENTER);
                vb_scene.getChildren().add(img_logo);
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Usuario e(ou) Senha Incorreto(s)!");
                alert.showAndWait();
            }
        }
        
    }

    @FXML
    private void ClickMenuCaixa(ActionEvent event)
    {
        
    }

    @FXML
    private void ClickMenuCompra(ActionEvent event)
    {
        
    }

    @FXML
    private void ClickMenuDespesa(ActionEvent event)
    {
        
    }

    @FXML
    private void ClickMenuFornecedor(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/FX/FXMLFornecedor.fxml"));
        
        vb_scene.getChildren().clear();
        vb_scene.getChildren().add(root);
    }

    @FXML
    private void ClickMenuMovimento(ActionEvent event)
    {
        
    }

    @FXML
    private void ClickMenuPagar(ActionEvent event)
    {
        
    }

    @FXML
    private void ClickMenuUsuario(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/FX/FXMLUsuario.fxml"));
        
        vb_scene.getChildren().clear();
        vb_scene.getChildren().add(root);
    }

    @FXML
    private void ClickMenuTipoDespesa(ActionEvent event)
    {
        
    }

    @FXML
    private void ClickMenuCliente(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/FX/FXMLCliente.fxml"));
        
        vb_scene.getChildren().clear();
        vb_scene.getChildren().add(root);
    }

    @FXML
    private void ClickMenMaterial(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/FX/FXMLMaterial.fxml"));
        
        vb_scene.getChildren().clear();
        vb_scene.getChildren().add(root);
    }

    @FXML
    private void ClickMenuReceber(ActionEvent event)
    {
        
    }

    @FXML
    private void ClickMenuTipoMaterial(ActionEvent event)
    {
        
    }

    @FXML
    private void ClickMenuVenda(ActionEvent event)
    {
        
    }
    
}
