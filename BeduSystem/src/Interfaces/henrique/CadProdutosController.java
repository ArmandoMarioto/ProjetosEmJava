/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces.henrique;

import Controladoras.CtrClassificacao;
import Controladoras.CtrProdutos;
import Utils.MaskFieldUtil;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Henrique K. Secchi
 */
public class CadProdutosController implements Initializable {

    @FXML
    private BorderPane panePrincipal;
    @FXML
    private AnchorPane paneInf;
    @FXML
    private JFXTextField txtCod;
    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXTextField txtPreco;
    @FXML
    private JFXTextField txtQtd;
    @FXML
    private JFXComboBox<String> cbClass;
    @FXML
    private Label lblInf;
    @FXML
    private AnchorPane paneBotoes;
    @FXML
    private AnchorPane paneNovo;
    @FXML
    private ImageView imgNovo;
    @FXML
    private Label lblNovo;
    @FXML
    private AnchorPane paneAlterar;
    @FXML
    private ImageView imgAlterar;
    @FXML
    private Label lblAlterar;
    @FXML
    private AnchorPane paneConfirmar;
    @FXML
    private ImageView imgConfirmar;
    @FXML
    private Label lblConfirmar;
    @FXML
    private AnchorPane paneCancelar;
    @FXML
    private ImageView imgCancelar;
    @FXML
    private Label lblCancelar;
    @FXML
    private AnchorPane paneExcluir;
    @FXML
    private ImageView imgExcluir;
    @FXML
    private Label lblExcluir;
    @FXML
    private AnchorPane panePesquisar;
    @FXML
    private ImageView imgPesquisar;
    @FXML
    private Label lblPesquisar;
    @FXML
    private Label lblObs;

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
    private CtrProdutos ctrprod;
    private CtrClassificacao ctrclassi;
    private Object[] obj;
    private int flagAlter;
    private ArrayList<Object[]> listClassi;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flagVolta = 1;
        RegistrarCompraController.setFlagVolta(0);
        
        inicializa("#000000", "#DFDFDF", "#FFFFFF", "#000000"); //paneEnter, paneExit, labelEnter, labelExit
        desab = new boolean[6];
        iniciarEstilo();
        carregarImagens();
        habilitarBotoes(true, false, false, false, false, true); //Oficial
        //habilitarBotoes(true, true, true, true, true, true); //Teste
        limparCampos();
        habilitarCampos(false);
        
        flagAlter = 0;
        ctrclassi = new CtrClassificacao();
        listClassi = ctrclassi.buscar();
        povoarCombobox();
        
        if(BuscaProdutosController.getFlag() == 1)
        {
            obj = BuscaProdutosController.getRetorno();
            setarCampos(obj);
            habilitarBotoes(false, true, false, true, true, true);
        }
       
    }    

    private void setarCampos(Object[] obj)
    {
        txtCod.setText("" + obj[0]);
        txtNome.setText((String)obj[1]);
        
        String preco = String.valueOf((double)obj[2]);
        preco = preco.replace(".", ",");
        
        txtPreco.setText(preco);
        txtQtd.setText("" + obj[3]);
        for (int i = 0; i < listClassi.size(); i++) {
            if((int)listClassi.get(i)[0] == (int)obj[4])
                cbClass.getSelectionModel().select(i);
        }
    }
    
    private void povoarCombobox()
    {
        String aux;
        for (int i = 0; i < listClassi.size(); i++) {
            aux = (String)listClassi.get(i)[1];
            cbClass.getItems().add(aux);
        }
    }
    
    private void carregarImagens() {
        Image img = new Image("Utils/Resources/new.png");
        imgNovo.setImage(img);
        img = new Image("Utils/Resources/alter.png");
        imgAlterar.setImage(img);
        img = new Image("Utils/Resources/close.png");
        imgCancelar.setImage(img);
        img = new Image("Utils/Resources/confirm.png");
        imgConfirmar.setImage(img);
        img = new Image("Utils/Resources/remove.png");
        imgExcluir.setImage(img);
        img = new Image("Utils/Resources/search.png");
        imgPesquisar.setImage(img);
        
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
    
    private void habilitarBotoes(boolean v1, boolean v2, boolean v3, boolean v4, boolean v5, boolean v6) {
       desab[0] = v1;
       desab[1] = v2;
       desab[2] = v3;
       desab[3] = v4;
       desab[4] = v5;
       desab[5] = v6;
       Image img[] = new Image[6];
       if(v1) img[0] = new Image("Utils/Resources/new.png");
       else img[0] = new Image("Utils/Resources/new_disable.png");
       
       if(v2) img[1] = new Image("Utils/Resources/alter.png");
       else img[1] = new Image("Utils/Resources/alter_disable.png");
       
       if(v3) img[2] = new Image("Utils/Resources/confirm.png");
       else img[2] = new Image("Utils/Resources/confirm_disable.png");
       
       if(v4) img[3] = new Image("Utils/Resources/close.png");
       else img[3] = new Image("Utils/Resources/close_disable.png");
       
       if(v5) img[4] = new Image("Utils/Resources/remove.png");
       else img[4] = new Image("Utils/Resources/remove_disable.png");
       
       if(v6) img[5] = new Image("Utils/Resources/search.png");
       else img[5] = new Image("Utils/Resources/search_disable.png");
       
       
        imgNovo.setImage(img[0]);
        paneNovo.setStyle(estiloExit);
        lblNovo.setStyle("-fx-text-fill:" + corLblExit);
        
        imgAlterar.setImage(img[1]);
        paneAlterar.setStyle(estiloExit);
        lblAlterar.setStyle("-fx-text-fill:" + corLblExit);
        
        imgConfirmar.setImage(img[2]);
        paneConfirmar.setStyle(estiloExit);
        lblConfirmar.setStyle("-fx-text-fill:" + corLblExit);
        
        imgCancelar.setImage(img[3]);
        paneCancelar.setStyle(estiloExit);
        lblCancelar.setStyle("-fx-text-fill:" + corLblExit);
        
        imgExcluir.setImage(img[4]);
        paneExcluir.setStyle(estiloExit);
        lblExcluir.setStyle("-fx-text-fill:" + corLblExit);
        
        imgPesquisar.setImage(img[5]);
        panePesquisar.setStyle(estiloExit);
        lblPesquisar.setStyle("-fx-text-fill:" + corLblExit);
        
           
    }
    
    private void habilitarCampos(boolean value)
    {
        value = !value;
        lblInf.setDisable(value);
        lblObs.setDisable(value);
        
        txtCod.setDisable(value);
        txtNome.setDisable(value);
        txtPreco.setDisable(value);
        txtQtd.setDisable(value);
        
        cbClass.setDisable(value);
    }
    
    private void limparCampos()
    {
        txtCod.setText("");
        txtNome.setText("");
        txtPreco.setText("");
        txtQtd.setText("");
        cbClass.getSelectionModel().select(-1);
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
    private void evtConfirmarExit(MouseEvent event) {
        if(desab[2]){ 
            paneConfirmar.setStyle(estiloExit);
            lblConfirmar.setStyle("-fx-text-fill:" + corLblExit);
        }
    }

    @FXML
    private void evtConfirmarEnter(MouseEvent event) {
        if(desab[2]){ 
            paneConfirmar.setStyle(estiloEnter);
            lblConfirmar.setStyle("-fx-text-fill:" + corLblEnter);
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
    private void evtExcluirExit(MouseEvent event) {
        if(desab[4]){   
            paneExcluir.setStyle(estiloExit);
            lblExcluir.setStyle("-fx-text-fill:" + corLblExit);
        }
    }

    @FXML
    private void evtExcluirEnter(MouseEvent event) {
        if(desab[4]){  
            paneExcluir.setStyle(estiloEnter);
            lblExcluir.setStyle("-fx-text-fill:" + corLblEnter);
        }
    }

    

    @FXML
    private void evtPesquisarExit(MouseEvent event) {
        if(desab[5]){   
            panePesquisar.setStyle(estiloExit);
            lblPesquisar.setStyle("-fx-text-fill:" + corLblExit);
        }
    }

    @FXML
    private void evtPesquisarEnter(MouseEvent event) {
        if(desab[5]){ 
            panePesquisar.setStyle(estiloEnter);
            lblPesquisar.setStyle("-fx-text-fill:" + corLblEnter);
        }
    }
    
    @FXML
    private void evtMaskPreco(KeyEvent event) {
        MaskFieldUtil.monetaryField(txtPreco);
    }

    @FXML
    private void evtMaskQtd(KeyEvent event) {
        MaskFieldUtil.numericField(txtQtd);
    }
   
    @FXML
    private void evtNovo(MouseEvent event) {
        if(desab[0])
        {   
            System.out.println("Novo");
            txtCod.setText("---");
            flagAlter = 0;
            habilitarCampos(true);
            habilitarBotoes(false, false, true, true, false, true);
            txtNome.requestFocus();
        }
    }
    
    @FXML
    private void evtAlterar(MouseEvent event) {
        if(desab[1])
        {
            System.out.println("Alter");
            habilitarCampos(true);
            habilitarBotoes(false, false, true, true, true, true);
            flagAlter = 1;
        }
    }
    
    @FXML
    private void evtCancelar(MouseEvent event) {
        if(desab[3])
        {
            System.out.println("Cancel");
            habilitarCampos(false);
            limparCampos();
            habilitarBotoes(true, false, false, false, false, true);
        }
    }

    @FXML
    private void evtPesquisar(MouseEvent event) {
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
    
    @FXML
    private void evtConfirmar(MouseEvent event) {
        if(desab[2])
        {
            System.out.println("Confirm");
            Object[] obj = new Object[6];
            
            try
            {
                obj[0] = flagAlter == 0 ? -1 : this.obj[0];
                obj[1] = txtNome.getText();
                obj[2] = txtPreco.getText().replace(".", "").replace(",", ".");
                obj[3] = txtQtd.getText();
                if(cbClass.getSelectionModel().getSelectedIndex() != -1)
                {
                    obj[4] = listClassi.get(cbClass.getSelectionModel().getSelectedIndex())[0]; //Pega o codigo do item escolhido no combobox
                    obj[5] = listClassi.get(cbClass.getSelectionModel().getSelectedIndex())[1]; //Pega o codigo do item escolhido no combobox
                }
                
                if(validarErros())
                {
                    //preencherNulos(obj);
                    obj[2] = Double.parseDouble((String)obj[2]);
                    obj[3] = txtQtd.getText().equals("") ? 0 : Integer.parseInt((String)obj[3]);
                    
                    ctrprod = new CtrProdutos();
                    System.out.println("Chegou aqui");
                    if(ctrprod.salvar(obj, flagAlter))
                    {
                        String msg;
                        if(flagAlter == 1)
                            msg = "Produto alterado com sucesso!";
                        else
                            msg = "Produto cadastrado com sucesso!";
                        
                        Alert a = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
                        a.showAndWait();
                        limparCampos();
                        habilitarCampos(false);
                        habilitarBotoes(true, false, false, false, false, true);
                    }
                }
                
            }catch(Exception er){
                Alert a = new Alert(Alert.AlertType.ERROR, "Erro ao tentar gravar os dados no banco!\nErro: " + er.getMessage(), ButtonType.OK);
                a.showAndWait();
            }
        }
        
    }

    @FXML
    private void evtExcluir(MouseEvent event) {
        
        if(desab[4])
        {
            System.out.println("Excluir");
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Tem certeza que deseja excluir este produto?", ButtonType.YES, ButtonType.NO);
            a.showAndWait();
            
            if(a.getResult() == ButtonType.YES)
            {
                ctrprod = new CtrProdutos();
                if(ctrprod.excluir((int)obj[0]))
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Produto excluído com sucesso!", ButtonType.OK);
                    alert.showAndWait();
                    
                    limparCampos();
                    habilitarCampos(false);
                    habilitarBotoes(true, false, false, false, false, true);
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao tentar excluir este produto!", ButtonType.OK);
                    alert.showAndWait();
                }
            }
        }
    }

    private boolean validarErros() {
        boolean flag = true;
        int[] erro = new int[]{0,0,0,0,0};
        
        if(txtNome.getText().equals(""))
        {
            erro[0] = 1;
            flag = false;
        }
        if(txtPreco.getText().equals(""))
        {
            erro[1] = 1;
            flag = false;
        }
        if(!txtQtd.getText().equals(""))
        {
            try
            {
                int qtd = Integer.parseInt(txtQtd.getText());
                if(qtd < 0)
                {
                    erro[3] = 1;
                    flag = false;
                }
            }catch(Exception er){
                erro[2] = 1;
                flag = false;
            }
        }
        
        if(cbClass.getSelectionModel().getSelectedIndex() == -1)
        {
            erro[4] = 1;
            flag = false;
        }
        
        if(!flag)
        {
            String str = "";
            if(erro[0] == 1)
                str += "Não foi digitado o nome do Produto!\n";
            if(erro[1] == 1)
                str += "Não foi digitado o preço do Produto!\n";
            if(erro[2] == 1)
                str += "Quantidade digitada inválida! Certifique-se de digitar um valor inteiro positivo!\n";
            if(erro[3] == 1)
                str += "Quantidade digitada inválida! Certifique-se de digitar um valor positivo!\n";
            if(erro[4] == 1)
                str += "Não foi selecionado a classificação do Produto";
            
            Alert a = new Alert(Alert.AlertType.ERROR, str, ButtonType.OK);
            a.showAndWait();
            
            if(erro[0] == 1)
                txtNome.requestFocus();
            else if(erro[1] == 1)
                txtPreco.requestFocus();
            else if(erro[2] == 1 || erro[3] == 1)
                txtQtd.requestFocus();
            else if(erro[4] == 1)
                cbClass.requestFocus();
        }
        return flag;
    }

    @Deprecated
    private void preencherNulos(Object[] obj) {
        if(obj[3] == null)
            obj[3] = 0;
    }

    public static int getFlagVolta() {
        return flagVolta;
    }

    public static void setFlagVolta(int flagVolta) {
        CadProdutosController.flagVolta = flagVolta;
    }
}
