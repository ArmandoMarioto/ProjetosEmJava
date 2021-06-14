/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces.henrique;

import Controladoras.CtrFornecedor;
import Utils.Objeto;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Henrique K. Secchi
 */
public class BuscaFornecedorController implements Initializable {

    //Componentes da tela
    @FXML
    private AnchorPane paneSelecionar;
    @FXML
    private ImageView imgSelecionar;
    @FXML
    private Label lblSelecionar;
    @FXML
    private AnchorPane paneCancelar;
    @FXML
    private ImageView imgCancelar;
    @FXML
    private Label lblCancelar;
    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXRadioButton rdNome;
    @FXML
    private JFXRadioButton rdCnpj;
    @FXML
    private JFXRadioButton rdEmail;
    @FXML
    private AnchorPane paneBuscar;
    @FXML
    private ImageView imgBuscar;
    @FXML
    private Label lblBuscar;
    @FXML
    private TableColumn<String, String> colCnpj;
    @FXML
    private TableColumn<String, String> colNome;
    @FXML
    private TableColumn<String, String> colCelular;
    @FXML
    private TableColumn<String, String> colEmail;
    @FXML
    private TableColumn<String, String> colCep;
    @FXML
    private TableColumn<String, String> colCidade;
    @FXML
    private AnchorPane paneBotoes;
    @FXML
    private AnchorPane paneBusca;
    @FXML
    private TableView<Objeto> tableview;
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
    private static int flag = 0;
    private static Object[] retorno;
    ArrayList<Object[]> list;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializa("#000000", "#DFDFDF", "#FFFFFF", "#000000");
        iniciarEstilo();
        carregarImagens();
        desab = new boolean[3];
        habilitarBotoes(true, false, true);
        setRadioButtons(true, false, false);
        iniciarColunas();
        lblCancelar.setText("Voltar");
    }    

    @FXML
    private void evtBuscarExit(MouseEvent event) {
        if(desab[0]){   
            paneBuscar.setStyle(estiloExit);
            lblBuscar.setStyle("-fx-text-fill:" + corLblExit);
        }
    }

    @FXML
    private void evtBuscarEnter(MouseEvent event) {
        if(desab[0]){   
            paneBuscar.setStyle(estiloEnter);
            lblBuscar.setStyle("-fx-text-fill:" + corLblEnter);
        }
    }
    
    @FXML
    private void evtConfirmarExit(MouseEvent event) {
        if(desab[1]){   
            paneSelecionar.setStyle(estiloExit);
            lblSelecionar.setStyle("-fx-text-fill:" + corLblExit);
        }
    }

    @FXML
    private void evtConfirmarEnter(MouseEvent event) {
        if(desab[1]){   
            paneSelecionar.setStyle(estiloEnter);
            lblSelecionar.setStyle("-fx-text-fill:" + corLblEnter);
        }
    }

    @FXML
    private void evtCancelarExit(MouseEvent event) {
        if(desab[2]){   
            paneCancelar.setStyle(estiloExit);
            lblCancelar.setStyle("-fx-text-fill:" + corLblExit);
        }
    }

    @FXML
    private void evtCancelarEnter(MouseEvent event) {
        if(desab[2]){   
            paneCancelar.setStyle(estiloEnter);
            lblCancelar.setStyle("-fx-text-fill:" + corLblEnter);
        }
    }
    
    private void carregarImagens() {
        Image img = new Image("Utils/Resources/search.png");
        imgBuscar.setImage(img);
        img = new Image("Utils/Resources/confirm.png");
        imgSelecionar.setImage(img);
        img = new Image("Utils/Resources/close.png");
        imgCancelar.setImage(img);
    }

    private void habilitarBotoes(boolean v1, boolean v2, boolean v3) {
        desab[0] = v1;
        desab[1] = v2;
        desab[2] = v3;
        
        Image img[] = new Image[3];
        if(v1) img[0] = new Image("Utils/Resources/search.png");
        else img[0] = new Image("Utils/Resources/search_disable.png");

        if(v2) img[1] = new Image("Utils/Resources/confirm.png");
        else img[1] = new Image("Utils/Resources/confirm_disable.png");

        if(v3) img[2] = new Image("Utils/Resources/close.png");
        else img[2] = new Image("Utils/Resources/close_disable.png");
        
        imgBuscar.setImage(img[0]);
        paneBuscar.setStyle(estiloExit);
        lblBuscar.setStyle("-fx-text-fill:" + corLblExit);
        
        imgSelecionar.setImage(img[1]);
        paneSelecionar.setStyle(estiloExit);
        lblSelecionar.setStyle("-fx-text-fill:" + corLblExit);
        
        imgCancelar.setImage(img[2]);
        paneCancelar.setStyle(estiloExit);
        lblCancelar.setStyle("-fx-text-fill:" + corLblExit);
    }
    
    private void iniciarEstilo() {
        String str = "-fx-background-color:" + "#E1E1E1" + ";"
                + " -fx-background-radius:2px;"
                + " -fx-border-color:d1d1d1;"
                + " -fx-border-radius:2px;"
                + " -fx-border-width:2px";
        
        paneBusca.setStyle(str);
        paneBotoes.setStyle(str);
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

    @FXML
    private void evtSelecionar(MouseEvent event) {
        if(desab[1])
        {
            try{
                
                if(tableview.getSelectionModel().getSelectedIndex() != -1)
                {
                    flag = 1;

                    int index = tableview.getSelectionModel().getSelectedIndex();
                    retorno = list.get(index);

                    //Parent root = FXMLLoader.load(getClass().getResource("/Interfaces/henrique/CadFornecedor.fxml"));
                    Parent root;
                    if(RegistrarCompraController.getFlagVolta() == 1)
                        root = FXMLLoader.load(getClass().getResource("/Interfaces/henrique/RegistrarCompra.fxml"));
                    else
                        root = FXMLLoader.load(getClass().getResource("/Interfaces/henrique/CadFornecedor.fxml"));
                    panePrincipal.getChildren().clear();
                    panePrincipal.getChildren().add(root);
                }
                else
                {
                    Alert a = new Alert(Alert.AlertType.ERROR, "Selecione uma linha da tabela!", ButtonType.OK);
                    a.showAndWait();
                }

            }catch(Exception er){
                Alert a = new Alert(Alert.AlertType.ERROR, "Erro ao abrir tela de cadastro de forneceodor!", ButtonType.OK);
                a.showAndWait();
            }
        }
    }

    @FXML
    private void evtCancelar(MouseEvent event) {
        if(lblCancelar.getText().equals("Voltar"))
        {
            try{
                flag = 0;
                //Adicionar objeto de retorno aqui
                //Parent root = FXMLLoader.load(getClass().getResource("/Interfaces/henrique/CadFornecedor.fxml"));
                Parent root;
                    if(RegistrarCompraController.getFlagVolta() == 1)
                        root = FXMLLoader.load(getClass().getResource("/Interfaces/henrique/RegistrarCompra.fxml"));
                    else
                        root = FXMLLoader.load(getClass().getResource("/Interfaces/henrique/CadFornecedor.fxml"));
                panePrincipal.getChildren().clear();
                panePrincipal.getChildren().add(root);

            }catch(Exception er){
                Alert a = new Alert(Alert.AlertType.ERROR, "Erro ao abrir tela de cadastro de fornecedor!", ButtonType.OK);
                a.showAndWait();
            }
        }
        else if(lblCancelar.getText().equals("Cancelar"))
        {
            habilitarBotoes(true, false, true);
            setRadioButtons(true, false, false);
            
            txtNome.requestFocus();
            lblCancelar.setText("Voltar");
            
            ArrayList<Objeto> obsList = new ArrayList<>();
            tableview.setItems(FXCollections.observableArrayList(obsList));
        }
    }

    @FXML
    private void evtBuscar(MouseEvent event) {
        if(desab[0])
        {
            String aux = "forn_nome ilike '%%'";
            CtrFornecedor ctrforn = new CtrFornecedor();
            ArrayList<Objeto> obsList = new ArrayList<>();
            Objeto obj;

            if(rdNome.isSelected()) //Pesquisa por nome
                aux = "forn_nome ilike '%" + txtNome.getText() + "%'";
            else if(rdCnpj.isSelected()) //Pesquisa por cnpj
                aux = "forn_cnpj ilike '%" + txtNome.getText() + "%'";
            else if(rdEmail.isSelected()) //Pesquisa por email
                aux = "forn_email ilike '%" + txtNome.getText() + "%'";

            list = ctrforn.buscar(aux);

            for (int i = 0; i < list.size(); i++) { //Cria um observable list para adicionar na tabela.
                obj = new Objeto((String)list.get(i)[1], (String)list.get(i)[2], (String)list.get(i)[3], (String)list.get(i)[4], (String)list.get(i)[9], (String)list.get(i)[8]);
                obsList.add(obj);
            }

            if(obsList.size() > 0) //Habilitar os botoes selecionar e  cancelar
            {
                lblCancelar.setText("Cancelar");
                habilitarBotoes(true, true, true);
            }
            
            tableview.setItems(FXCollections.observableArrayList(obsList)); //Adicione observable list na tabela.
        }
    }

    private void iniciarColunas() {
        
        colCnpj.setCellValueFactory(new PropertyValueFactory<>("param1"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("param2"));
        colCelular.setCellValueFactory(new PropertyValueFactory<>("param3"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("param4"));
        colCep.setCellValueFactory(new PropertyValueFactory<>("param5"));
        colCidade.setCellValueFactory(new PropertyValueFactory<>("param6"));
        
    }

    @FXML
    private void evtRdNome(ActionEvent event) {
        setRadioButtons(true, false, false);
    }

    @FXML
    private void evtRdCpf(ActionEvent event) {
        setRadioButtons(false, true, false);
    }

    @FXML
    private void evtRdEmail(ActionEvent event) {
        setRadioButtons(false, false, true);
    }

    private void setRadioButtons(boolean v1, boolean v2, boolean v3)
    {
        rdNome.setSelected(v1);
        rdCnpj.setSelected(v2);
        rdEmail.setSelected(v3);
    }

    public static int getFlag() {
        return flag;
    }

    public static Object[] getRetorno() {
        return retorno;
    }
}
