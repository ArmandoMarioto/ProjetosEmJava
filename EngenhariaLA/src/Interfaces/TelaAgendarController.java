/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Controladoras.CtrAgendar;
import Entidades.Agendar;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.json.JSONObject;
import util.MaskFieldUtil;


/**
 * FXML Controller class
 *
 * @author armando
 */
public class TelaAgendarController implements Initializable
{

    @FXML
    private JFXButton btn_novo;
    @FXML
    private JFXButton btn_alterar;
    @FXML
    private JFXButton btn_apagar;
    @FXML
    private JFXTextField tf_nome;
    @FXML
    private JFXTextField tf_fone;
    @FXML
    private JFXComboBox<String> cbestado;
    @FXML
    private JFXComboBox<String> cbpais;
    @FXML
    private JFXComboBox<String> cbcidade;
    @FXML
    private JFXComboBox<String> cbbairro;
    @FXML
    private JFXTextField tf_endereco;
    @FXML
    private JFXButton btn_salvar;
    @FXML
    private JFXTextField tf_modelo;
    @FXML
    private JFXTextField tf_placa;
    @FXML
    private JFXTextField tf_cep;
    @FXML
    private JFXButton btn_consulta;
    @FXML
    private TableView<Agendar> tv_table;
    @FXML
    private ComboBox<String> cbServicos;
    @FXML
    private JFXTextField tf_codigo;
    private Agendar s_agendar;
    @FXML
    private TableColumn<Agendar,String> c_servico;
    @FXML
    private TableColumn<Agendar,Date> c_dia;
    @FXML
    private TableColumn<Agendar, String> c_horario;
    @FXML
    private TableColumn<Agendar, String> c_disponivel;
    @FXML
    private JFXDatePicker dp_horario;
    @FXML
    private JFXTextField tf_obs;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        Inicial();
       
        c_servico.setCellValueFactory(new PropertyValueFactory<>("servicos"));
        c_dia.setCellValueFactory(new PropertyValueFactory<>("dia"));
        c_horario.setCellValueFactory(new PropertyValueFactory<>("horario"));
        c_disponivel.setCellValueFactory(new PropertyValueFactory<>("disponivel"));
        MaskFieldUtil.onlyAlfaNumericValue(tf_nome);
        MaskFieldUtil.foneField(tf_fone);
        cbServicos.setDisable(false);
        cbServicos.setItems(new CtrAgendar().BuscarServicos());
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
            URL url = new URL("http://apps.widenet.com.br/busca-cep/api/cep." + formato + "?code=" + cep);
            URLConnection con = url.openConnection();
            con.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
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
        tf_cep.setDisable(true);
        btn_alterar.setDisable(true);
        btn_apagar.setDisable(true);
        btn_salvar.setDisable(true);
        tf_nome.setDisable(true);
        tf_fone.setDisable(true);
        tf_modelo.setDisable(true);
        tf_placa.setDisable(true);
        tf_endereco.setDisable(true);
        cbServicos.setDisable(true);
       btn_consulta.setDisable(true);
       tf_obs.setDisable(true);
    }

    private void Liberar()
    {
        btn_novo.setDisable(true);
        btn_alterar.setDisable(false);
        btn_apagar.setDisable(false);
        tf_cep.setDisable(false);
        btn_salvar.setDisable(false);
        tf_nome.setDisable(false);
        tf_fone.setDisable(false);
        tf_modelo.setDisable(false);
        tf_placa.setDisable(false);
        tf_endereco.setDisable(false);
        cbServicos.setDisable(false);
        btn_consulta.setDisable(false);
        tf_obs.setDisable(false);

    }

    private void Limpar()
    {
       
        tf_nome.setText("");
        tf_fone.setText("");
        tf_modelo.setText("");
        tf_placa.setText("");
        tf_endereco.setText("");
    }

    @FXML
    private void ClickNovo(ActionEvent event) throws SQLException
    {

        Liberar();
            
    }

    @FXML
    private void ClickAlterar(ActionEvent event) throws SQLException
    {
        if (!tf_nome.getText().isEmpty() && !tf_fone.getText().isEmpty() && !tf_modelo.getText().isEmpty() && !tf_placa.getText().isEmpty() && !tf_endereco.getText().isEmpty())
        {

                if (new CtrAgendar().Alterar(tf_codigo.getText(), tf_nome.getText(),tf_fone.getText(),tf_modelo.getText(), tf_placa.getText(), tf_endereco.getText(), tf_cep.getText(),cbServicos.getValue(),s_agendar.getDia(),s_agendar.getHorario(),s_agendar.getDisponivel(),s_agendar.getObs()))
                {
                    Limpar();
                    Inicial();
                    tv_table.setItems(new CtrAgendar().BuscarHorarios(s_agendar.getServicos(), s_agendar.getDia().toLocalDate()));
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Alterado com sucesso!!!.");
                    alert.showAndWait();
                } else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Erro na Alteração.");
                    alert.showAndWait();
                }
            } 
         else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Campos Insuficientes.");
            alert.showAndWait();
        }
    }

    @FXML
    private void ClickApagar(ActionEvent event)
    {
        if (new CtrAgendar().Apagar(s_agendar.getCodigo()))
        {
            Limpar();
            Inicial();
            tv_table.setItems(new CtrAgendar().BuscarHorarios(s_agendar.getServicos(), s_agendar.getDia().toLocalDate()));
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
            s_agendar = (Agendar) tv_table.getSelectionModel().getSelectedItem();
            tf_codigo.setText(String.valueOf(s_agendar.getCodigo()));
            tf_nome.setText(s_agendar.getNome());
           tf_fone.setText(s_agendar.getTelefone());
           tf_modelo.setText(s_agendar.getModelo());
           tf_placa.setText(s_agendar.getPlaca());
           tf_endereco.setText(s_agendar.getEndereco());
           tf_cep.setText(s_agendar.getCep());
           cbServicos.setValue(s_agendar.getServicos());
           dp_horario.setValue(s_agendar.getDia().toLocalDate());
           tf_obs.setText(s_agendar.getObs());
            consultaCep(s_agendar.getCep().replace("-", ""), "json");
        }
    }

    private void ClickBuscar(ActionEvent event)
    {
    }

    private void ClickLimpar(ActionEvent event)
    {
        Limpar();
        Inicial();
    }

    @FXML
    private void evtBuscaCep() 
    {
        String str = consultaCep(tf_cep.getText().replace("-", ""), "json");
        JSONObject my_obj = new JSONObject(str);
        cbestado.getSelectionModel().select(my_obj.getString("state"));
        cbcidade.getItems().clear();
        cbcidade.getItems().add(my_obj.getString("city"));
        cbcidade.getSelectionModel().select(my_obj.getString("city"));
        cbpais.getSelectionModel().select("Brasil");
    }

    @FXML
    private void ClickSalvar(ActionEvent event) throws SQLException 
    {
        
       
       if (!tf_nome.getText().isEmpty() && !tf_fone.getText().isEmpty() && !tf_modelo.getText().isEmpty() && !tf_placa.getText().isEmpty() && !tf_endereco.getText().isEmpty())
            {
                   
                    if (new CtrAgendar().Salvar(tf_nome.getText(),tf_fone.getText(),tf_modelo.getText(), tf_placa.getText(), tf_endereco.getText(), tf_cep.getText(), (String) cbServicos.getValue(),s_agendar.getDia(),s_agendar.getHorario(),s_agendar.getDisponivel(),s_agendar.getObs()))
                    {
                        Limpar();
                        Inicial();
                       tv_table.setItems(new CtrAgendar().BuscarHorarios(s_agendar.getServicos(), s_agendar.getDia().toLocalDate()));
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
                alert.setContentText("Campos Insuficientes.");
                alert.showAndWait();
            }
        
    }

    @FXML
    private void evtBuscaHorarios(ActionEvent event) 
    {
        tv_table.setItems(new CtrAgendar().BuscarHorarios(cbServicos.getValue(),dp_horario.getValue()));
    }

}
