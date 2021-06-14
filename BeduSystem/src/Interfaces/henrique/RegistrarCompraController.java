/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces.henrique;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class RegistrarCompraController implements Initializable {

    @FXML
    private AnchorPane paneInf;
    @FXML
    private JFXComboBox<?> cbProdutos;
    @FXML
    private JFXTextField txtQuantidade;
    @FXML
    private AnchorPane paneAdd;
    @FXML
    private ImageView imgAdd;
    @FXML
    private Label lblAdd;
    @FXML
    private JFXComboBox<?> cbFornecedores;
    @FXML
    private JFXTextField txtValorPago;
    @FXML
    private JFXRadioButton rdAVista;
    @FXML
    private JFXRadioButton rdParcelado;
    @FXML
    private JFXTextField txtParcelas;
    @FXML
    private JFXTextField txtJuros;
    @FXML
    private AnchorPane panePesquisarProd;
    @FXML
    private ImageView imgPesquisarProd;
    @FXML
    private Label lblPesquisarProd;
    @FXML
    private AnchorPane panePesquisarFunc;
    @FXML
    private ImageView imgPesquisarFunc;
    @FXML
    private Label lblPesquisarFunc;
    @FXML
    private TableView<?> tableview;
    @FXML
    private TableColumn<?, ?> colProduto;
    @FXML
    private TableColumn<?, ?> colFornecedor;
    @FXML
    private TableColumn<?, ?> colPreco;
    @FXML
    private TableColumn<?, ?> colQuantidade;
    @FXML
    private TableColumn<?, ?> colParcelas;
    @FXML
    private TableColumn<?, ?> colTotal;
    @FXML
    private AnchorPane paneBotoes;
    @FXML
    private AnchorPane paneAlterar;
    @FXML
    private ImageView imgAlterar;
    @FXML
    private Label lblAlterar;
    @FXML
    private AnchorPane paneRegistrar;
    @FXML
    private ImageView imgRegistrar;
    @FXML
    private Label lblRegistrar;
    @FXML
    private AnchorPane paneCancelar;
    @FXML
    private ImageView imgCancelar;
    @FXML
    private Label lblCancelar;
    @FXML
    private AnchorPane paneRemover;
    @FXML
    private ImageView imgRemover;
    @FXML
    private Label lblRemover;
    @FXML
    private AnchorPane paneNovo;
    @FXML
    private ImageView imgNovo;
    @FXML
    private Label lblNovo;
    @FXML
    private Label lblFormasPagamento;
    @FXML
    private BorderPane panePrincipal;

    //Variáveis para controlar a aparência da tela.
    private boolean[] desab;
    private String corPaneEnter;
    private String corPaneExit;
    private String corLblEnter;
    private String corLblExit;
    private String estiloEnter;
    private String estiloExit;
    
    //Variáveis para controlar a funcionalidade do sistema.
    private static int flagVolta = 0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flagVolta = 1;
        CadProdutosController.setFlagVolta(0);
        CadFornecedorController.setFlagVolta(0);
        
        inicializa("#000000", "#DFDFDF", "#FFFFFF", "#000000"); //paneEnter, paneExit, labelEnter, labelExit
        desab = new boolean[8];
        iniciarEstilo();
        carregarImagens();
        habilitarBotoes(true, false, false, false, false, false, false, false); //Oficial
        //habilitarBotoes(true, true, true, true, true, true, true, true); //Teste
        limparCampos();
        habilitarCampos(false);
        
    }    

    private void carregarImagens() {
        Image img = new Image("Utils/Resources/new.png");
        imgNovo.setImage(img);
        imgAdd.setImage(img);
        img = new Image("Utils/Resources/alter.png");
        imgAlterar.setImage(img);
        img = new Image("Utils/Resources/close.png");
        imgCancelar.setImage(img);
        img = new Image("Utils/Resources/confirm.png");
        imgRegistrar.setImage(img);
        img = new Image("Utils/Resources/remove.png");
        imgRemover.setImage(img);
        img = new Image("Utils/Resources/search.png");
        imgPesquisarProd.setImage(img);
        imgPesquisarFunc.setImage(img);
    }
    
    private void inicializa(String c1, String c2, String c3, String c4) {
        corPaneEnter = c1;
        corPaneExit = c2;
        corLblEnter = c3;
        corLblExit = c4;
        
        estiloEnter = "-fx-background-color:" + corPaneEnter + ";"
                + " -fx-background-radius:7px;"
                + " -fx-border-color:d1d1d1;"
                + " -fx-border-radius:5px;"
                + " -fx-border-width:2px;";
        estiloExit = "-fx-background-color:" + corPaneExit + ";"
                + " -fx-background-radius:7px;"
                + " -fx-border-color:d1d1d1;"
                + " -fx-border-radius:5px;"
                + " -fx-border-width:2px;";
    }
    
    private void iniciarEstilo() {
        String str = "-fx-background-color:" + "#E1E1E1" + ";"
                + " -fx-background-radius:2px;"
                + " -fx-border-color:d1d1d1;"
                + " -fx-border-radius:2px;"
                + " -fx-border-width:2px";
        
        paneInf.setStyle(str);
        paneBotoes.setStyle(str);
    }
    
    private void habilitarBotoes(boolean v1, boolean v2, boolean v3, boolean v4, boolean v5, boolean v6, boolean v7, boolean v8) {
       desab[0] = v1;
       desab[1] = v2;
       desab[2] = v3;
       desab[3] = v4;
       desab[4] = v5;
       desab[5] = v6;
       desab[6] = v7;
       desab[7] = v8;
       Image img[] = new Image[8];
       if(v1) img[0] = new Image("Utils/Resources/new.png");
       else img[0] = new Image("Utils/Resources/new_disable.png");
       
       if(v2) img[1] = new Image("Utils/Resources/alter.png");
       else img[1] = new Image("Utils/Resources/alter_disable.png");
       
       if(v3) img[2] = new Image("Utils/Resources/remove.png");
       else img[2] = new Image("Utils/Resources/remove_disable.png");
       
       if(v4) img[3] = new Image("Utils/Resources/close.png");
       else img[3] = new Image("Utils/Resources/close_disable.png");
       
       if(v5) img[4] = new Image("Utils/Resources/confirm.png");
       else img[4] = new Image("Utils/Resources/confirm_disable.png");
       
       if(v6) img[5] = new Image("Utils/Resources/search.png");
       else img[5] = new Image("Utils/Resources/search_disable.png");
       
       if(v7) img[6] = new Image("Utils/Resources/search.png");
       else img[6] = new Image("Utils/Resources/search_disable.png");
       
       if(v8) img[7] = new Image("Utils/Resources/new.png");
       else img[7] = new Image("Utils/Resources/new_disable.png");
       
        imgNovo.setImage(img[0]);
        paneNovo.setStyle(estiloExit);
        lblNovo.setStyle("-fx-text-fill:" + corLblExit);
        
        imgAlterar.setImage(img[1]);
        paneAlterar.setStyle(estiloExit);
        lblAlterar.setStyle("-fx-text-fill:" + corLblExit);
        
        imgRemover.setImage(img[2]);
        paneRemover.setStyle(estiloExit);
        lblRemover.setStyle("-fx-text-fill:" + corLblExit);
        
        imgCancelar.setImage(img[3]);
        paneCancelar.setStyle(estiloExit);
        lblCancelar.setStyle("-fx-text-fill:" + corLblExit);
        
        imgRegistrar.setImage(img[4]);
        paneRegistrar.setStyle(estiloExit);
        lblRegistrar.setStyle("-fx-text-fill:" + corLblExit);
        
        imgPesquisarProd.setImage(img[5]);
        panePesquisarProd.setStyle(estiloExit);
        lblPesquisarProd.setStyle("-fx-text-fill:" + corLblExit);
        
        imgPesquisarFunc.setImage(img[6]);
        panePesquisarFunc.setStyle(estiloExit);
        lblPesquisarFunc.setStyle("-fx-text-fill:" + corLblExit);
        
        imgAdd.setImage(img[7]);
        paneAdd.setStyle(estiloExit);
        lblAdd.setStyle("-fx-text-fill:" + corLblExit);
           
    }
    
    
    private void habilitarCampos(boolean value)
    {
        value = !value;
        cbProdutos.setDisable(value);
        cbFornecedores.setDisable(value);
        
        txtValorPago.setDisable(value);
        txtQuantidade.setDisable(value);
        lblFormasPagamento.setDisable(value);
        
        rdAVista.setDisable(value);
        rdParcelado.setDisable(value);
        txtParcelas.setDisable(value);
        txtJuros.setDisable(value);
    }
    
    private void limparCampos()
    {
        cbProdutos.getSelectionModel().select(-1);
        cbFornecedores.getSelectionModel().select(-1);
        
        txtValorPago.setText("");
        txtQuantidade.setText("");
        
        setRd(true, false);
        txtParcelas.setText("");
        txtJuros.setText("");
    }
    
    private void setRd(boolean v1, boolean v2)
    {
        rdAVista.setSelected(v1);
        rdParcelado.setSelected(v2);
        txtParcelas.setVisible(v2);
        txtJuros.setVisible(v2);
    }
    
    public static int getFlagVolta() {
        return flagVolta;
    }

    public static void setFlagVolta(int flagVolta) {
        RegistrarCompraController.flagVolta = flagVolta;
    }
    
    @FXML
    private void evtRdAVista(ActionEvent event) {
        setRd(true, false);
        
    }

    @FXML
    private void evtRdParcelado(ActionEvent event) {
        setRd(false, true);
    }
   
    @FXML
    private void evtNovoExit(MouseEvent event) {
        if(desab[0]){   
            paneNovo.setStyle(estiloExit);
            lblNovo.setStyle("-fx-text-fill:" + corLblExit);
        }
    }

    @FXML
    private void evtNovoEnter(MouseEvent event) {
        if(desab[0]){
            paneNovo.setStyle(estiloEnter);
            lblNovo.setStyle("-fx-text-fill:" + corLblEnter);
        }
    }


    @FXML
    private void evtAlterarExit(MouseEvent event) {
        if(desab[1]){  
            paneAlterar.setStyle(estiloExit);
            lblAlterar.setStyle("-fx-text-fill:" + corLblExit);
        }
    }

    @FXML
    private void evtAlterarEnter(MouseEvent event) {
        if(desab[1]){
            paneAlterar.setStyle(estiloEnter);
            lblAlterar.setStyle("-fx-text-fill:" + corLblEnter);
        }
    }    

    @FXML
    private void evtRemoverExit(MouseEvent event) {
        if(desab[2]){ 
            paneRemover.setStyle(estiloExit);
            lblRemover.setStyle("-fx-text-fill:" + corLblExit);
        }
    }

    @FXML
    private void evtRemoverEnter(MouseEvent event) {
        if(desab[2]){ 
            paneRemover.setStyle(estiloEnter);
            lblRemover.setStyle("-fx-text-fill:" + corLblEnter);
        }
    }
    
    @FXML
    private void evtCancelarExit(MouseEvent event) {
        if(desab[3]){ 
            paneCancelar.setStyle(estiloExit);
            lblCancelar.setStyle("-fx-text-fill:" + corLblExit);
        }
    }

    @FXML
    private void evtCancelarEnter(MouseEvent event) {
        if(desab[3]){   
            paneCancelar.setStyle(estiloEnter);
            lblCancelar.setStyle("-fx-text-fill:" + corLblEnter);
        }
    }
    
    @FXML
    private void evtRegistrarExit(MouseEvent event) {
        if(desab[4]){   
            paneRegistrar.setStyle(estiloExit);
            lblRegistrar.setStyle("-fx-text-fill:" + corLblExit);
        }
    }

    @FXML
    private void evtRegistrarEnter(MouseEvent event) {
        if(desab[4]){   
            paneRegistrar.setStyle(estiloEnter);
            lblRegistrar.setStyle("-fx-text-fill:" + corLblEnter);
        }
    }
    


    @FXML
    private void evtPesquisarProdExit(MouseEvent event) {
        if(desab[5]){   
            panePesquisarProd.setStyle(estiloExit);
            lblPesquisarProd.setStyle("-fx-text-fill:" + corLblExit);
        }
    }

    @FXML
    private void evtPesquisarProdEnter(MouseEvent event) {
        if(desab[5]){ 
            panePesquisarProd.setStyle(estiloEnter);
            lblPesquisarProd.setStyle("-fx-text-fill:" + corLblEnter);
        }
    }    

    @FXML
    private void evtPesquisarFuncExit(MouseEvent event) {
        if(desab[6]){ 
            panePesquisarFunc.setStyle(estiloExit);
            lblPesquisarFunc.setStyle("-fx-text-fill:" + corLblExit);
        }
    }

    @FXML
    private void evtPesquisarFuncEnter(MouseEvent event) {
        if(desab[6]){ 
            panePesquisarFunc.setStyle(estiloEnter);
            lblPesquisarFunc.setStyle("-fx-text-fill:" + corLblEnter);
        }
    }
    
    @FXML
    private void evtAddExit(MouseEvent event) {
        if(desab[7]){ 
            paneAdd.setStyle(estiloExit);
            lblAdd.setStyle("-fx-text-fill:" + corLblExit);
        }
    }

    @FXML
    private void evtAddEnter(MouseEvent event) {
        if(desab[7]){ 
            paneAdd.setStyle(estiloEnter);
            lblAdd.setStyle("-fx-text-fill:" + corLblEnter);
        }
    }
    /*------------------------------------------------------------------------*/
    
    @FXML
    private void evtPesquisarProd(MouseEvent event) {
        if(desab[5])
        {
            try
            {
                Parent root = FXMLLoader.load(getClass().getResource("/Interfaces/henrique/BuscaProdutos.fxml"));

                panePrincipal.getChildren().clear();
                panePrincipal.getChildren().add(root);
            }catch(Exception er){
                Alert a = new Alert(Alert.AlertType.ERROR, "Erro ao abrir tela de Busca de Produtos! " + er.getLocalizedMessage(), ButtonType.OK);
                a.showAndWait();
            }
        }
    }
    
    @FXML
    private void evtPesquisarFunc(MouseEvent event) {
        if(desab[6])
        { 
            try
            {
                Parent root = FXMLLoader.load(getClass().getResource("/Interfaces/henrique/buscaFornecedor.fxml"));

                panePrincipal.getChildren().clear();
                panePrincipal.getChildren().add(root);
            }catch(Exception er){
                Alert a = new Alert(Alert.AlertType.ERROR, "Erro ao abrir tela de Busca de Fornecedores! " + er.getLocalizedMessage(), ButtonType.OK);
                a.showAndWait();
            }
        }
    }
    
    /*------------------------------------------------------------------------*/
    
    @FXML
    private void evtAdd(MouseEvent event) {
        if(desab[7])
        {
            
        }
    }
    
    @FXML
    private void evtNovo(MouseEvent event) {
        if(desab[0])
        {
            habilitarBotoes(false, false, false, true, true, true, true, true);
            habilitarCampos(true);
            cbProdutos.requestFocus();
        }
    }
    
    @FXML
    private void evtAlterar(MouseEvent event) {
    }
    
    @FXML
    private void evtRemover(MouseEvent event) {
    }
    
    @FXML
    private void evtCancelar(MouseEvent event) {
        habilitarBotoes(true, false, false, false, false, false, false, false);
        limparCampos();
        habilitarCampos(false);
    }
    
    @FXML
    private void evtRegistrar(MouseEvent event) {
    }
}
