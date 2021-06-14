/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces.henrique;

import Controladoras.CtrFornecedor;
import Utils.MaskFieldUtil;
import Utils.ValidadorCPFeCNPJ;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
public class CadFornecedorController implements Initializable {

    //Componentes da tela
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
    private Label lblAlterar;
    @FXML
    private AnchorPane paneInf;
    @FXML
    private AnchorPane paneEnd;
    @FXML
    private AnchorPane paneBotoes;
    @FXML
    private JFXTextField txtCNPJ;
    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXTextField txtCelular;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtRua;
    @FXML
    private JFXTextField txtBairro;
    @FXML
    private JFXTextField txtCidade;
    @FXML
    private JFXTextField txtNumero;
    @FXML
    private JFXTextField txtCep;
    @FXML
    private BorderPane panePrincipal;
    @FXML
    private Label lblInfBasicas;
    @FXML
    private Label lblInfEnd;
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
    private CtrFornecedor ctrforn;
    private Object[] obj;
    private int flagAlter;
    @FXML
    private AnchorPane paneCep;
    @FXML
    private ImageView imgCep;
    @FXML
    private Label lblCep;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flagVolta = 1;
        RegistrarCompraController.setFlagVolta(0);
        
        inicializa("#000000", "#DFDFDF", "#FFFFFF", "#000000"); //paneEnter, paneExit, labelEnter, labelExit
        desab = new boolean[7];
        iniciarEstilo();
        carregarImagens();
        habilitarBotoes(true, false, false, false, false, true, false); //Oficial
        //habilitarBotoes(true, true, true, true, true, true); //Teste
        limparCampos();
        habilitarCampos(false);
        flagAlter = 0;
        if(BuscaFornecedorController.getFlag() == 1)
        {
            obj = BuscaFornecedorController.getRetorno();
            setarCampos(obj);
            habilitarBotoes(false, true, false, true, true, true, false);
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
        imgCep.setImage(img);
        
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

    private void habilitarBotoes(boolean v1, boolean v2, boolean v3, boolean v4, boolean v5, boolean v6, boolean v7) {
       desab[0] = v1;
       desab[1] = v2;
       desab[2] = v3;
       desab[3] = v4;
       desab[4] = v5;
       desab[5] = v6;
       desab[6] = v7;
       Image img[] = new Image[7];
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
       
       if(v7) img[6] = new Image("Utils/Resources/search.png");
       else img[6] = new Image("Utils/Resources/search_disable.png");
       
       
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
        
        imgCep.setImage(img[6]);
        paneCep.setStyle(estiloExit);
        lblCep.setStyle("-fx-text-fill:" + corLblExit);
    }

    private void iniciarEstilo() {
        String str = "-fx-background-color:" + "#E1E1E1" + ";"
                + " -fx-background-radius:2px;"
                + " -fx-border-color:d1d1d1;"
                + " -fx-border-radius:2px;"
                + " -fx-border-width:2px";
        
        paneInf.setStyle(str);
        paneEnd.setStyle(str);
        paneBotoes.setStyle(str);
    }

    @FXML
    private void evtMaskCPF(KeyEvent event) {
        MaskFieldUtil.cpfCnpjField(txtCNPJ);
    }

    @FXML
    private void evtMaskFone(KeyEvent event) {
        MaskFieldUtil.foneField(txtCelular);
    }

    @FXML
    private void evtMaskNum(KeyEvent event) {
        MaskFieldUtil.numericField(txtNumero);
    }

    @FXML
    private void evtMaskCep(KeyEvent event) {
        MaskFieldUtil.cepField(txtCep);
    }

    @FXML
    private void evtPesquisar(MouseEvent event) {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/Interfaces/henrique/buscaFornecedor.fxml"));
        
            panePrincipal.getChildren().clear();
            panePrincipal.getChildren().add(root);
        }catch(Exception er){
            Alert a = new Alert(Alert.AlertType.ERROR, "Erro ao abrir tela de Busca de Forncedores! " + er.getLocalizedMessage(), ButtonType.OK);
            a.showAndWait();
        }
    }
    
    private void habilitarCampos(boolean value)
    {
        value = !value;
        lblInfBasicas.setDisable(value);
        lblInfEnd.setDisable(value);
        lblObs.setDisable(value);
        
        txtCNPJ.setDisable(value);
        txtNome.setDisable(value);
        txtCelular.setDisable(value);
        txtEmail.setDisable(value);
        
        txtRua.setDisable(value);
        txtBairro.setDisable(value);
        txtNumero.setDisable(value);
        txtCidade.setDisable(value);
        txtCep.setDisable(value);
    }
    
    private void limparCampos()
    {
        txtCNPJ.setText("");
        txtNome.setText("");
        txtCelular.setText("");
        txtEmail.setText("");
        
        txtRua.setText("");
        txtBairro.setText("");
        txtNumero.setText("");
        txtCidade.setText("");
        txtCep.setText("");
    }

    @FXML
    private void evtNovo(MouseEvent event) {
        if(desab[0])
        {   
            System.out.println("Novo");
            flagAlter = 0;
            habilitarCampos(true);
            habilitarBotoes(false, false, true, true, false, true, true);
        }
    }

    @FXML
    private void evtAlterar(MouseEvent event) {
        if(desab[1])
        {
            System.out.println("Alter");
            habilitarCampos(true);
            habilitarBotoes(false, false, true, true, true, true, true);
            flagAlter = 1;
        }
    }

    @FXML
    private void evtConfirmar(MouseEvent event) {
        if(desab[2])
        {
            System.out.println("Confirm");
            
            Object[] obj = new Object[10];
            try
            {
                obj[0] = flagAlter == 0 ? -1 : this.obj[0];
                obj[1] = txtCNPJ.getText();//.replace(".", "").replace("/", "").replace("-", "");
                obj[2] = txtNome.getText();
                obj[3] = txtCelular.getText();//.replace("(", "").replace(")", "").replace("-", "");
                obj[4] = txtEmail.getText();
                
                obj[5] = txtRua.getText();
                obj[6] = txtBairro.getText();
                if(!txtNumero.getText().equals(""))
                    obj[7] = Integer.parseInt(txtNumero.getText());
                obj[8] = txtCidade.getText();
                obj[9] = txtCep.getText();//.replace("-", "");
                
                if(validarErros())
                {
                    //Continua para gravar no banco.
                    preencherNulos(obj);
                    
                    ctrforn = new CtrFornecedor();
                    
                    Alert a;
                    
                    int saida = ctrforn.salvar(obj, flagAlter);
                    if(saida == 1)
                    {
                        String msg;
                        if(flagAlter == 1)
                            msg = "Fornecedor alterado com sucesso!";
                        else
                            msg = "Fornecedor cadastrado com sucesso!";
                        
                        a = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
                        limparCampos();
                        habilitarCampos(false);
                        habilitarBotoes(true, false, false, false, false, true, false);
                    }
                    else if(saida == 0)
                        a = new Alert(Alert.AlertType.ERROR, "Erro ao gravar dados no banco!", ButtonType.OK);
                    else
                        a = new Alert(Alert.AlertType.ERROR, "Erro ao gravar dados no banco! Este CPF/CNPJ já foi cadastrado!", ButtonType.OK);
                    
                    a.showAndWait();
                }
                //else - tem erros
                
            }catch(Exception er)
            {
                Alert a = new Alert(Alert.AlertType.ERROR, "Erro ao tentar gravar os dados no banco!\nErro: " + er.getMessage(), ButtonType.OK);
                a.showAndWait();
            }
        }
    }

    @FXML
    private void evtCancelar(MouseEvent event) {
        if(desab[3])
        {
            System.out.println("Cancel");
            habilitarCampos(false);
            limparCampos();
            habilitarBotoes(true, false, false, false, false, true, false);
        }
    }

    @FXML
    private void evtExcluir(MouseEvent event) {
        if(desab[4])
        {
            System.out.println("Excluir");
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Tem certeza que deseja excluir este fornecedor?", ButtonType.YES, ButtonType.NO);
            a.showAndWait();
            
            if(a.getResult() == ButtonType.YES)
            {
                ctrforn = new CtrFornecedor();
                if(ctrforn.excluir((int)obj[0]))
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Fornecedor excluído com sucesso!", ButtonType.OK);
                    alert.showAndWait();
                    
                    limparCampos();
                    habilitarCampos(false);
                    habilitarBotoes(true, false, false, false, false, true, false);
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao tentar excluir este fornecedor!", ButtonType.OK);
                    alert.showAndWait();
                }
            }
        }
    }

    private boolean validarErros() {
        boolean flag = true;
        int[] erro = new int[]{0,0,0,0,0};
        
        int aux = ValidadorCPFeCNPJ.isValidCPForCNPJ(txtCNPJ.getText());
        if(aux == -1) //CPF ou CNPJ inválido.
        {
            erro[0] = 1;
            flag = false;
        }

        if(txtCNPJ.getText().equals("")) //Não foi digitado nenhum cpf ou cnpj.
        {
            erro[1] = 1;
            flag = false;
        }

        if(txtNome.getText().equals("")) //Não foi digitado nenhum nome.
        {
            erro[2] = 1;
            flag = false;
        }

        String auxstr = txtCelular.getText();
        if(auxstr.equals("") || auxstr.length() < 13) //Validar se o celular foi digitado corretamente.
        {
            erro[3] = 1;
            flag = false;
        }
        
        if(!txtCep.getText().equals("") && txtCep.getText().length() != 9)
        {
            erro[4] = 1;
            flag = false;
        }
        
        if(!flag)
        {
            String str = "";
            if(erro[1] == 1)
                str += "Não foi digitado o CPF/CNPJ.\n";
            else if(erro[0] == 1)
                str += "CPF/CNPJ inválido.\n";
            if(erro[2] == 1)
                str += "Não foi digitado o Nome do fornecedor.\n";
            if(erro[3] == 1)
                str += "Não foi digitado o número de celular ou número inválido.\n";
            if(erro[4] == 1)
                str += "CEP inválido.";
            
            Alert a = new Alert(Alert.AlertType.ERROR, str, ButtonType.OK);
            a.showAndWait();
            
            if(erro[0] == 1 || erro[1] == 1)
               txtCNPJ.requestFocus();
            else if(erro[2] == 1)
                txtNome.requestFocus();
            else if(erro[3] == 1)
                txtCelular.requestFocus();
            else if(erro[4] == 1)
                txtCep.requestFocus();
        }
        return flag;
    }

    private void preencherNulos(Object[] obj) {
        if(obj[7] == null)
            obj[7] = -1;
        
        for (int i = 0; i < obj.length; i++)
            if(obj[i] == null)
                obj[i] = "";
    }

    private void setarCampos(Object[] obj) {
        txtCNPJ.setText((String)obj[1]);
        txtNome.setText((String)obj[2]);
        txtCelular.setText((String)obj[3]);
        txtEmail.setText((String)obj[4]);
        txtRua.setText((String)obj[5]);
        txtBairro.setText((String)obj[6]);
        if((int)obj[7] != -1)
            txtNumero.setText("" + (int)obj[7]);
        txtCidade.setText((String)obj[8]);
        txtCep.setText((String)obj[9]);
    }
    
    public String consultaCep()
    { 
        String urlString = "http://cep.republicavirtual.com.br/web_cep.php";
        urlString += "?cep=" + txtCep.getText() + "&formato= xml";
        try 
        { 
            URL url = new URL(urlString);
            // cria o objeto httpurlconnection
            HttpURLConnection con=(HttpURLConnection) url.openConnection();
            // seta para modo GET
            con.setRequestProperty("Request-Method", "GET");
            // conecta com a url destino
            con.connect();
            // abre a conexão para leitura
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            // le ate o final dos dados
            StringBuffer dados = new StringBuffer();
            String s = "";
            limparEnd();
            while (null != (s = br.readLine())) 
            {
                dados.append(s);
                s = s.replace("<", "").replace(">", "").replace("/", "");
                if(s.contains("bairro"))
                {
                    s = s.replace("bairro", "");
                    txtBairro.setText(s);
                }
                if(s.contains("cidade"))
                {
                    s = s.replace("cidade", "");
                    txtCidade.setText(s);
                }
                if(s.contains("tipo_logradouro") || s.contains("logradouro"))
                {
                    s = s.replace("tipo_logradouro", "").replace("logradouro", "");
                    txtRua.setText(txtRua.getText() + " " + s);
                }
            } 
            
            if(txtRua.getText().equals("  "))
                txtRua.setText("");
            
            br.close();
            return dados.toString();
        } 
        catch (Exception e) 
        {  
            return "Erro: " + e.getMessage();
        }
    }

    @FXML
    private void evtCepExit(MouseEvent event) {
        if(desab[6]){   
            paneCep.setStyle(estiloExit);
            lblCep.setStyle("-fx-text-fill:" + corLblExit);
        }
    }

    @FXML
    private void evtCepEnter(MouseEvent event) {
        if(desab[6]){   
            paneCep.setStyle(estiloEnter);
            lblCep.setStyle("-fx-text-fill:" + corLblEnter);
        }
    }

    @FXML
    private void evtCep(MouseEvent event) {
        String aux = consultaCep();
    }

    private void limparEnd() {
        txtRua.setText("");
        txtBairro.setText("");
        txtRua.setText("");
    }

    public static int getFlagVolta() {
        return flagVolta;
    }

    public static void setFlagVolta(int flagVolta) {
        CadFornecedorController.flagVolta = flagVolta;
    }
}
