/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Controladoras.CtrFornecedor;
import Entidades.Fornecedor;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.json.JSONObject;
import util.MaskFieldUtil;
import util.ValidaCNPJ;


/**
 * FXML Controller class
 *
 * @author armando
 */
public class TelaFornecedorController implements Initializable
{

    private Fornecedor s_fornecedor;
    @FXML
    private JFXButton btn_novo;
    @FXML
    private JFXButton btn_alterar;
    @FXML
    private JFXButton btn_apagar;
    @FXML
    private JFXButton btn_limpar;
    @FXML
    private TableView<Fornecedor> tv_table;
    @FXML
    private TableColumn<Fornecedor, String> c_cod;
    @FXML
    private TableColumn<Fornecedor, String> c_nome;
    @FXML
    private TableColumn<Fornecedor, String> c_fone;
    @FXML
    private TableColumn<Fornecedor, String> c_email;
    @FXML
    private JFXTextField tf_codigo;
    @FXML
    private JFXTextField tf_nome;
    @FXML
    private JFXTextField tf_fone;
    @FXML
    private JFXTextField tf_email;
    @FXML
    private JFXTextField tf_buscar;
    @FXML
    private TableColumn<Fornecedor, String> c_cnpj;
    @FXML
    private JFXTextField tf_cnpj;
    @FXML
    private JFXTextField tf_contato;
    @FXML
    private JFXTextField tf_celular;
    @FXML
    private JFXTextField tf_site;
    @FXML
    private JFXRadioButton rb_nome;
    @FXML
    private TableColumn<Fornecedor, String> c_contato;
    @FXML
    private TableColumn<Fornecedor, String> c_celular;
    @FXML
    private TableColumn<Fornecedor, String> c_site;
    @FXML
    private JFXComboBox<String> cbestado;
    @FXML
    private JFXComboBox<String> cbpais;
    @FXML
    private JFXComboBox<String> cbcidade;
    @FXML
    private JFXComboBox<String> cbbairro;
    @FXML
    private JFXTextField tcep;
    @FXML
    private JFXTextField tf_endereco;
    @FXML
    private TableColumn<Fornecedor, String> c_endereco;
    @FXML
    private TableColumn<Fornecedor, String> c_cep;
    @FXML
    private Button btn_buscar;
    @FXML
    private ToggleGroup Gruop;
    @FXML
    private JFXRadioButton rb_site;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        tf_codigo.setDisable(true);
        Inicial();
        c_cod.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        c_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        c_cnpj.setCellValueFactory(new PropertyValueFactory<>("cnpf"));
        c_fone.setCellValueFactory(new PropertyValueFactory<>("fone"));
        c_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        c_contato.setCellValueFactory(new PropertyValueFactory<>("contato"));
        c_celular.setCellValueFactory(new PropertyValueFactory<>("celular"));
        c_site.setCellValueFactory(new PropertyValueFactory<>("site"));
        c_endereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        c_cep.setCellValueFactory(new PropertyValueFactory<>("cep"));

        MaskFieldUtil.onlyAlfaNumericValue(tf_nome);
        MaskFieldUtil.foneField(tf_fone);
        MaskFieldUtil.cnpjField(tf_cnpj);
        MaskFieldUtil.foneField(tf_celular);
        MaskFieldUtil.onlyAlfaNumericValue(tf_contato);

        tv_table.setItems(new CtrFornecedor().BuscarTodos());
    }
    public String consultaCep(String cep, String formato)
    {
        String username = "seu login";
        String password = "sua senha";
        String proxyHost = "177.131.35.1";
        String proxyPort = "3128";

        StringBuffer dados = new StringBuffer();
        try
        {
            //URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato"+formato);
            URL url = new URL("http://apps.widenet.com.br/busca-cep/api/cep." + formato + "?code=" + cep);

            //String userpass = username + ":" + password;
            //System.setProperty("http.proxyHost", proxyHost);
            //System.setProperty("http.proxyPort", proxyPort);
            URLConnection con = url.openConnection();
            con.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            //String encodedLogin = new BASE64Encoder().encode(userpass.getBytes());
            //con.setRequestProperty("Proxy-Authorization", "Basic " + encodedLogin);
            //con.setDoInput(true);
            //con.setDoOutput(true);
            //con.setAllowUserInteraction(false);
            InputStream in = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String s = "";
            while (null != (s = br.readLine()))
            {
                dados.append(s);
            }
            br.close();
        } catch (Exception ex)
        {
            System.out.println(ex);
        }
        return dados.toString();
    }

    private void Inicial()
    {
        btn_novo.setDisable(false);
        btn_alterar.setDisable(true);
        btn_apagar.setDisable(true);
        btn_limpar.setDisable(true);
        btn_novo.setText("Novo");

        tf_cnpj.setDisable(true);
        tf_nome.setDisable(true);
        tf_fone.setDisable(true);
        tf_email.setDisable(true);
        tf_contato.setDisable(true);
        tf_celular.setDisable(true);
        tf_site.setDisable(true);
        tf_endereco.setDisable(true);
        tcep.setDisable(true);
    }

    private void Liberar()
    {
        btn_alterar.setDisable(false);
        btn_apagar.setDisable(false);
        btn_limpar.setDisable(false);
        tf_nome.setDisable(false);
        tf_cnpj.setDisable(false);
        tf_fone.setDisable(false);
        tf_email.setDisable(false);
        tf_contato.setDisable(false);
        tf_celular.setDisable(false);
        tf_site.setDisable(false);
        tf_endereco.setDisable(false);
        tcep.setDisable(false);

    }

    private void Limpar()
    {
        tf_codigo.setText("");
        tf_nome.setText("");
        tf_fone.setText("");
        tf_cnpj.setText("");
        tf_email.setText("");
        tf_buscar.setText("");
        tf_contato.setText("");
        tf_celular.setText("");
        tf_site.setText("");
        tcep.setText("");
        tf_endereco.setText("");
    }

    @FXML
    private void ClickNovo(ActionEvent event) throws SQLException
    {
        if (btn_novo.getText().equals("Novo"))
        {
            Liberar();
            btn_novo.setText("Salvar");
        } else//salvar
        {
            if (!tf_nome.getText().isEmpty() && !tf_fone.getText().isEmpty() && !tf_email.getText().isEmpty() && !tf_cnpj.getText().isEmpty())
            {
                if (ValidaCNPJ.isCNPJ(tf_cnpj.getText()))
                {
                    //codigo, nome, cnpf, email, fone, site, contato, celular
                    if (new CtrFornecedor().Salvar(tf_nome.getText(), tf_cnpj.getText(), tf_email.getText(), tf_fone.getText(),
                            tf_site.getText(), tf_contato.getText(), tf_celular.getText(),tf_endereco.getText(),tcep.getText()))
                    {
                        Limpar();
                        Inicial();
                        tv_table.setItems(new CtrFornecedor().BuscarTodos());
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Salvo com sucesso!!!");
                        alert.showAndWait();
                    } else
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Erro ao salvar.");
                        alert.showAndWait();
                    }

                } else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("CNPJ Invalido.");
                    alert.showAndWait();
                }

            } else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Campos Insuficientes.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void ClickAlterar(ActionEvent event) throws SQLException
    {
        if (!tf_nome.getText().isEmpty() && !tf_fone.getText().isEmpty() && !tf_email.getText().isEmpty() && !tf_cnpj.getText().isEmpty())
        {

            if (ValidaCNPJ.isCNPJ(tf_cnpj.getText()))
            {
                if (new CtrFornecedor().Alterar(tf_codigo.getText(), tf_nome.getText(), tf_cnpj.getText(), tf_email.getText(), tf_fone.getText(), tf_site.getText(), tf_contato.getText(), tf_celular.getText(),tf_endereco.getText(),tcep.getText()))
                {
                    Limpar();
                    Inicial();
                    tv_table.setItems(new CtrFornecedor().BuscarTodos());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Alterado com sucesso!!!.");
                    alert.showAndWait();
                } else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Erro na Alteração.");
                    alert.showAndWait();
                }
            } else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("CNPJ Invalido.");
                alert.showAndWait();
            }
        } else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Campos Insuficientes.");
            alert.showAndWait();
        }
    }

    @FXML
    private void ClickApagar(ActionEvent event)
    {
        if (new CtrFornecedor().Apagar(s_fornecedor.getCodigo()))
        {
            Limpar();
            Inicial();
            tv_table.setItems(new CtrFornecedor().BuscarTodos());
        } else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Falha na Exclusão.");
            alert.showAndWait();
            Limpar();
        }
    }

    @FXML
    private void ClickTabela(MouseEvent event)
    {
        if (tv_table.getSelectionModel().getSelectedIndex() >= 0)
        {
            Inicial();
            Liberar();
            Limpar();
            btn_novo.setDisable(true);
            s_fornecedor = tv_table.getSelectionModel().getSelectedItem();
            tf_codigo.setText(String.valueOf(s_fornecedor.getCodigo()));
            tf_nome.setText(s_fornecedor.getNome());
            tf_cnpj.setText(s_fornecedor.getCnpf());
            tf_fone.setText(s_fornecedor.getFone());
            tf_email.setText(s_fornecedor.getEmail());
            tf_site.setText(s_fornecedor.getSite());
            tf_contato.setText(s_fornecedor.getContato());
            tf_celular.setText(s_fornecedor.getCelular());
            tf_endereco.setText(s_fornecedor.getEndereco());
            consultaCep(s_fornecedor.getCep().replace("-", ""), "json");
        }
    }

    @FXML
    private void ClickBuscar(ActionEvent event)
    {
        String busca;
        if (tf_buscar.getText().isEmpty())
        {
            tv_table.setItems(new CtrFornecedor().BuscarTodos());
        } else
        {
            tv_table.getItems().clear();
            if (rb_nome.isSelected())
            {
                busca = "for_nome";
            } else
            {
                busca = "for_site";
            }
            tv_table.setItems(new CtrFornecedor().Buscar(tf_buscar.getText(), busca));
        }
    }

    @FXML
    private void ClickLimpar(ActionEvent event)
    {
        Limpar();
        Inicial();
    }

    @FXML
    private void evtBuscaCep(ActionEvent event) 
    {
        String str = consultaCep(tcep.getText().replace("-", ""), "json");
        JSONObject my_obj = new JSONObject(str);
        cbestado.getSelectionModel().select(my_obj.getString("state"));
        cbcidade.getItems().clear();
        cbcidade.getItems().add(my_obj.getString("city"));
        cbcidade.getSelectionModel().select(my_obj.getString("city"));
        cbpais.getSelectionModel().select("Brasil");
    }

}
