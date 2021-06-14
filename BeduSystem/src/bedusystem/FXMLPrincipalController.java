
package bedusystem;

import Entidades.Parametrizacao;
import Interfaces.FXMLLoginController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLPrincipalController implements Initializable
{
    public static int nivel = -1;
    @FXML
    private MenuItem menu_produtos;
    @FXML
    private MenuItem menu_funcionario;
    @FXML
    private MenuItem menu_cliente;
    @FXML
    private MenuItem menu_usuarios;
    @FXML
    private MenuItem menu_fornecedor;
    @FXML
    private Pane hb_tela;
    @FXML
    private MenuItem menu_para;
    @FXML
    private ImageView imgLogoGrande;
    private Parametrizacao para;
    @FXML
    private Menu menu_admin;
    @FXML
    private MenuItem menu_servicos;
    @FXML
    private MenuItem menu_orcamento;
    @FXML
    private MenuItem menu_os;
    @FXML
    private MenuItem menu_or;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
        try {
           para = Controladoras.CtrParametrizacao.instancia().carrega();
        } catch (SQLException | IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        imgLogoGrande.setImage(SwingFXUtils.toFXImage(para.getLogoPequeno(), null));
        
        
        inicializa();
        if(nivel != 0)
        {
            menu_admin.setDisable(true);
            menu_usuarios.setDisable(true);
            if(nivel != 1)
            {
                menu_fornecedor.setDisable(true);
                menu_funcionario.setDisable(true);
                menu_produtos.setDisable(true);
            }
        }
    }    
    
    public void inicializa()
    {
        Parent root;
        Stage s1 = new Stage();
        try 
        {
            if(nivel == -1)
            {
                root = FXMLLoader.load(getClass().getResource("/Interfaces/FXMLLogin.fxml"));
                Scene scene = new Scene(root);

                s1.setScene(scene);
                s1.initModality(Modality.APPLICATION_MODAL);
                s1.setTitle("Login");
                s1.showAndWait();  

                if(nivel == -1)
                    Platform.exit();
                s1.close();
            }
              
              
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clickProdutos(ActionEvent event)
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/Interfaces/henrique/CadProdutos.fxml"));
        
            ((Stage)hb_tela.getScene().getWindow()).setHeight(560);
            ((Stage)hb_tela.getScene().getWindow()).setWidth(600);
            hb_tela.getChildren().clear();
            hb_tela.getChildren().add(root);
        }catch(Exception er){
            Alert a = new Alert(Alert.AlertType.ERROR, "Erro ao abrir tela de Produtos! " + er.getLocalizedMessage(), ButtonType.OK);
            a.showAndWait();
        }
    }

    @FXML
    private void clickFuncionario(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/Interfaces/FXMLFuncionario.fxml"));
        
        hb_tela.getChildren().clear();
        hb_tela.getChildren().add(root);
        ((Stage)hb_tela.getScene().getWindow()).setHeight(540);
        ((Stage)hb_tela.getScene().getWindow()).setWidth(693);
        ((Stage)hb_tela.getScene().getWindow()).setTitle("BeduSystem - Gerenciamento de Funcionários");
    }

    @FXML
    private void clickClientes(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/Interfaces/TelaCliente.fxml"));
        
        hb_tela.getChildren().clear();
        hb_tela.getChildren().add(root);
        ((Stage)hb_tela.getScene().getWindow()).setHeight(650);
        ((Stage)hb_tela.getScene().getWindow()).setWidth(1013);
        ((Stage)hb_tela.getScene().getWindow()).setTitle("BeduSystem - Gerenciamento de Clientes");
    }

    @FXML
    private void clickUsuario(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/Interfaces/FXMLUsuarios.fxml"));
        
        hb_tela.getChildren().clear();
        hb_tela.getChildren().add(root);
        ((Stage)hb_tela.getScene().getWindow()).setHeight(475);
        ((Stage)hb_tela.getScene().getWindow()).setWidth(600);
        ((Stage)hb_tela.getScene().getWindow()).setTitle("BeduSystem - Gerenciamento de Usuarios");
    }

    @FXML
    private void clickFornecedor(ActionEvent event)
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/Interfaces/henrique/CadFornecedor.fxml"));
        
            ((Stage)hb_tela.getScene().getWindow()).setHeight(560);
            ((Stage)hb_tela.getScene().getWindow()).setWidth(600);
            hb_tela.getChildren().clear();
            hb_tela.getChildren().add(root);
        }catch(Exception er){
            Alert a = new Alert(Alert.AlertType.ERROR, "Erro ao abrir tela de Forncedores! " + er.getLocalizedMessage(), ButtonType.OK);
            a.showAndWait();
        }
    }

    @FXML
    private void clickPara(ActionEvent event) 
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/Interfaces/TelaParametrizacao.fxml"));
        
            hb_tela.getChildren().clear();
            hb_tela.getChildren().add(root);
            ((Stage)hb_tela.getScene().getWindow()).setHeight(650);
            ((Stage)hb_tela.getScene().getWindow()).setWidth(778);
            ((Stage)hb_tela.getScene().getWindow()).setTitle("BeduSystem - Gerenciamento da Parametrização");
            
        }
        catch(Exception er)
        {
            Alert a = new Alert(Alert.AlertType.ERROR, "Erro ao abrir tela Parametrização! " + er.getLocalizedMessage(), ButtonType.OK);
            a.showAndWait();
        }
    }

    @FXML
    private void clickOS(ActionEvent event)
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/Interfaces/Fundamentais/FXMLOS.fxml"));
        
            hb_tela.getChildren().clear();
            hb_tela.getChildren().add(root);
            ((Stage)hb_tela.getScene().getWindow()).setHeight(500);
            ((Stage)hb_tela.getScene().getWindow()).setWidth(717);
            ((Stage)hb_tela.getScene().getWindow()).setTitle("BeduSystem - Ordem de Serviço");
            
        }
        catch(Exception er)
        {
            Alert a = new Alert(Alert.AlertType.ERROR, "Erro ao abrir tela Ordem de Serviço! " + er.getLocalizedMessage(), ButtonType.OK);
            a.showAndWait();
        }
    }

    @FXML
    private void clickRegistrarCompra(ActionEvent event) {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/Interfaces/henrique/RegistrarCompra.fxml"));
        
            ((Stage)hb_tela.getScene().getWindow()).setHeight(560);
            ((Stage)hb_tela.getScene().getWindow()).setWidth(600);
            hb_tela.getChildren().clear();
            hb_tela.getChildren().add(root);
        }catch(Exception er){
            Alert a = new Alert(Alert.AlertType.ERROR, "Erro ao abrir tela de Registrar Compras! " + er.getLocalizedMessage(), ButtonType.OK);
            a.showAndWait();
        }
    }

    @FXML
    private void clickOR(ActionEvent event) 
    {
        System.out.println("Aquii");
        hb_tela.getChildren().clear();
        try
        {
            Parent p = FXMLLoader.load(getClass().getResource("/Interfaces/TelaGenOrcamento.fxml"));
            hb_tela.getChildren().add(p);
            ((Stage)hb_tela.getScene().getWindow()).setHeight(825);
            ((Stage)hb_tela.getScene().getWindow()).setWidth(1124);
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
