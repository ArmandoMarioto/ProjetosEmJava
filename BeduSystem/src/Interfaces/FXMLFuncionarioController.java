/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Controladoras.ctrFuncionarios;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import Entidades.Funcionário;
import java.time.LocalDate;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import Banco.Banco;
import Utils.MaskFieldUtil;
import Utils.Objeto;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Caique
 */
public class FXMLFuncionarioController implements Initializable
{
    final ToggleGroup group = new ToggleGroup();
    final ToggleGroup groupPesquisa = new ToggleGroup();
    private int cod;
    
    @FXML
    private JFXTextField tf_nome;
    @FXML
    private JFXTextField tf_funcao;
    @FXML
    private JFXTextField tf_celular;
    @FXML
    private JFXTextField tf_email;
    @FXML
    private JFXTextField tf_rua;
    @FXML
    private JFXTextField tf_bairro;
    @FXML
    private JFXTextField tf_cidade;
    @FXML
    private JFXRadioButton rb_masculino;
    @FXML
    private JFXRadioButton rb_feminino;
    @FXML
    private JFXDatePicker dp_admissao;
    @FXML
    private JFXTextField tf_telefone;
    @FXML
    private JFXButton btn_cep;
    @FXML
    private JFXButton btn_novo;
    @FXML
    private JFXButton btn_alterar;
    @FXML
    private JFXButton btn_remover;
    @FXML
    private JFXButton btn_cancelar;
    @FXML
    private JFXButton btn_pesquisar;
    @FXML
    private TableView<Objeto> tv_tabela;
    @FXML
    private TableColumn<Object, String> tc_nome;
    @FXML
    private TableColumn<Object, String> tc_funcao;
    @FXML
    private TableColumn<Object, String> tc_endereco;
    @FXML
    private JFXTextField tf_cep;
    @FXML
    private TableColumn<Object,String> tc_celular;
    @FXML
    private BorderPane panePrincipal;
    @FXML
    private TableColumn<Object, LocalDate> tc_admissao;
    @FXML
    private JFXTextField tf_busca;
    @FXML
    private JFXRadioButton rb_nome;
    @FXML
    private JFXRadioButton rb_funca;
    @FXML
    private JFXDatePicker dp_data_pesquisa;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        tc_admissao.setCellValueFactory(new PropertyValueFactory<>("param10"));
        tc_celular.setCellValueFactory(new PropertyValueFactory<>("param5"));
        tc_endereco.setCellValueFactory(new PropertyValueFactory<>("param13"));
        tc_funcao.setCellValueFactory(new PropertyValueFactory<>("param3"));
        tc_nome.setCellValueFactory(new PropertyValueFactory<>("param2"));
        /*tc_celular.setCellValueFactory(new PropertyValueFactory<Funcionário,String>("celular"));
        tc_funcao.setCellValueFactory(new PropertyValueFactory<Funcionário,String>("funcao"));
        tc_nome.setCellValueFactory(new PropertyValueFactory<Funcionário,String>("nome"));
        tc_endereco.setCellValueFactory(new PropertyValueFactory<Funcionário,String>("rua"));*/
        
        
        rb_feminino.setToggleGroup(group);
        rb_masculino.setToggleGroup(group);
        rb_masculino.setSelected(true);
        
        rb_funca.setToggleGroup(groupPesquisa);
        rb_nome.setToggleGroup(groupPesquisa);
        rb_nome.setSelected(true);
        
        inicializa();
    }
    
    public void limpar()
    {
        tf_nome.clear(); tf_funcao.clear(); tf_telefone.clear(); tf_celular.clear(); tf_bairro.clear(); tf_cep.clear();
        tf_cidade.clear(); tf_email.clear(); tf_rua.clear(); dp_admissao.setValue(LocalDate.now());
    }
    
    public void altera_campos(boolean b1,boolean b2,boolean b3,boolean b4)
    {
        tf_nome.setDisable(b1);
        tf_funcao.setDisable(b1);
        tf_telefone.setDisable(b1);
        tf_celular.setDisable(b1);
        tf_email.setDisable(b1);
        tf_cep.setDisable(b1);
        btn_cep.setDisable(b1);
        rb_feminino.setDisable(b1);
        rb_masculino.setDisable(b1);
        tf_rua.setDisable(b1);
        tf_bairro.setDisable(b1);
        tf_cidade.setDisable(b1);
        dp_admissao.setDisable(b1);
        btn_novo.setDisable(b2);
        btn_alterar.setDisable(b3);
        btn_remover.setDisable(b4);
    }
    
    public void inicializa()
    {
        cod = -1;
        altera_campos(true, false, true, true);
        btn_novo.setText("Novo");
        btn_alterar.setText("Alterar");
        dp_admissao.setValue(LocalDate.now());
        procurar();
    }

    @FXML
    private void clickCEP(ActionEvent event)
    {
        tf_rua.setText("");
        String res = consultaCep();
    }

    @FXML
    private void clickNovo(ActionEvent event)
    {
        altera_campos(false, false, true, true);
        String erro = "";
        if(btn_novo.getText().equals("Confirmar"))
        {
            erro = valida_campos();
            Alert alert;
            if(valida_campos().equals(""))
            {
                if(new ctrFuncionarios().gravar(tf_nome, tf_funcao, tf_telefone, tf_celular, tf_email, 
                        rb_feminino.isSelected()?rb_feminino:rb_masculino,tf_rua, tf_bairro, tf_cidade, dp_admissao))
                {
                    alert = new Alert(Alert.AlertType.INFORMATION, "Funcionário cadastrado com sucesso", ButtonType.OK);
                    limpar();
                    inicializa();
                }
                else
                    alert = new Alert(Alert.AlertType.ERROR, "Erro no cadastramento do funcionário: " + Banco.getCon().getMensagemErro(), ButtonType.OK);
            }
            else
               alert = new Alert(Alert.AlertType.ERROR, erro, ButtonType.OK); 
            alert.showAndWait();
        }
        else
            btn_novo.setText("Confirmar");
    }

    @FXML
    private void clickAlterar(ActionEvent event)
    {
        if(btn_alterar.getText().equals("Alterar"))
        {
            altera_campos(false, true, false, true);
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
                String erro = valida_campos();
                alert = new Alert(Alert.AlertType.INFORMATION);
                if(erro.equals("") && new ctrFuncionarios().alterar(tf_nome, tf_funcao, tf_telefone, tf_celular, tf_email, 
                        rb_feminino.isSelected()?rb_feminino:rb_masculino,tf_rua, tf_bairro, tf_cidade, dp_admissao,cod))
                {
                    alert.setContentText("Alteração concluida com sucesso");
                    clickCancelar(event);
                    inicializa();
                    btn_alterar.setText("Alterar");
                }
                else if(!erro.equals(""))
                    alert = new Alert(Alert.AlertType.ERROR, erro, ButtonType.NO);
                else
                    alert.setContentText("Erro na alteração do usuário, por favor corrige os dados e tente novamente");
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
            if(cod != -1 && new ctrFuncionarios().remover(cod))
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
    private void clickCancelar(ActionEvent event)
    {
        limpar();
        inicializa();
    }

    @FXML
    private void clickPesquisar(ActionEvent event)
    {
        ObservableList<Funcionário> funcionarios = FXCollections.observableArrayList();
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/Interfaces/FXMLBuscaFuncionario.fxml"));
        
            panePrincipal.getChildren().clear();
            panePrincipal.getChildren().add(root);
        }catch(Exception er)
        {
            Alert a = new Alert(Alert.AlertType.ERROR, "Erro ao abrir tela de Busca de Forncedores! " + er.getLocalizedMessage(), ButtonType.OK);
            a.showAndWait();
        }  
    }

    public void procurar()
    {
        ArrayList<Object[]> list;
        ArrayList<Objeto> obsList = new ArrayList<>();
        Objeto obj;
        
        list = new ctrFuncionarios().procurarTodos(tf_busca);
        for (int i = 0; i < list.size(); i++) 
        { 
            
            obj = new Objeto(String.valueOf(list.get(i)[0]), (String)list.get(i)[1], (String)list.get(i)[2],
                    (String)list.get(i)[3],(String)list.get(i)[4],(String)list.get(i)[5],(String)list.get(i)[6],
                    (String)list.get(i)[7],(String)list.get(i)[8],String.valueOf(list.get(i)[9]),String.valueOf(list.get(i)[10]),
                    String.valueOf(list.get(i)[11]),(String)list.get(i)[12]);
            obsList.add(obj);    
        }
       
        tv_tabela.setItems(FXCollections.observableArrayList(obsList));
        //list = new ctrFuncionarios()
        //tv_tabela.setItems(funcionarios);
    }
    
    @FXML
    private void clickTabela(MouseEvent event)
    {
        if(event.getClickCount() == 2 && tv_tabela.getSelectionModel().getSelectedItem() != null)
        {
            altera_campos(true, true, false, false);
            preenche_campos();
        }
    }
    
    public void preenche_campos()
    {
        tf_nome.setText(tv_tabela.getSelectionModel().getSelectedItem().getParam2());
        tf_funcao.setText(tv_tabela.getSelectionModel().getSelectedItem().getParam3());
        tf_telefone.setText(tv_tabela.getSelectionModel().getSelectedItem().getParam4());
        tf_celular.setText(tv_tabela.getSelectionModel().getSelectedItem().getParam5());
        tf_email.setText(tv_tabela.getSelectionModel().getSelectedItem().getParam6());
        tf_rua.setText(tv_tabela.getSelectionModel().getSelectedItem().getParam7());
        tf_bairro.setText(tv_tabela.getSelectionModel().getSelectedItem().getParam8());
        tf_cidade.setText(tv_tabela.getSelectionModel().getSelectedItem().getParam9());
        if(tv_tabela.getSelectionModel().getSelectedItem().getParam11().equals(true))
            rb_masculino.setSelected(true);
        else 
            rb_feminino.setSelected(true);
        cod = Integer.parseInt(tv_tabela.getSelectionModel().getSelectedItem().getParam1());
    }
    
    public String consultaCep()
    { 
        String urlString = "http://cep.republicavirtual.com.br/web_cep.php";
        urlString += "?cep=" + tf_cep.getText() + "&formato= xml";
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
            while (null != (s = br.readLine())) 
            {
                dados.append(s);
                s = s.replace("<", "").replace(">", "").replace("/", "");
                if(s.contains("bairro"))
                {
                    s = s.replace("bairro", "");
                    tf_bairro.setText(s);
                }
                if(s.contains("cidade"))
                {
                    s = s.replace("cidade", "");
                    tf_cidade.setText(s);
                }
                if(s.contains("tipo_logradouro") || s.contains("logradouro"))
                {
                    s = s.replace("tipo_logradouro", "").replace("logradouro", "");
                    tf_rua.setText(tf_rua.getText() + " " + s); 
                }
            } 
            
            br.close();
            return dados.toString();
        } 
        catch (Exception e) 
        {  
            return "Erro: " + e.getMessage();
        }
    }

    @FXML
    private void eventMaskNome(KeyEvent event)
    {
        MaskFieldUtil.onlyAlfaNumericValue(tf_nome);
    }

    @FXML
    private void eventMaskTel(KeyEvent event)
    {
        MaskFieldUtil.foneField(tf_telefone);
    }

    @FXML
    private void eventMaskCEl(KeyEvent event)
    {
        MaskFieldUtil.foneField(tf_celular);
    }

    @FXML
    private void eventMaskCEP(KeyEvent event)
    {
        MaskFieldUtil.cepField(tf_cep);
    }
    
    public String valida_campos()
    {
        String erro = "";
        if(tf_nome.getText().equals(""))
            erro += "Nome invalido\n";
         if(tf_funcao.getText().equals(""))
             erro += "Função inválida\n";
         if(tf_rua.getText().equals(""))
             erro += "Rua inválida\n";
         if(tf_bairro.getText().equals(""))
             erro += "Bairro inválido\n";
         if(tf_cidade.getText().equals(""))
             erro += "Cidade inválida\n";
         if(dp_admissao.getValue().compareTo(LocalDate.now()) > 0)
             erro += "Data inválida\n";
         return erro;
    }
}
