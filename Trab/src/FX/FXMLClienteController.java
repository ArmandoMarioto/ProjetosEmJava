package FX;

import Classes.Cliente;
import Classes.MaskFieldUtil;
import Controladoras.ctrCliente;
import com.jfoenix.controls.JFXButton;
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
import javax.swing.JOptionPane;

public class FXMLClienteController implements Initializable
{
    private Cliente s_cliente;
    @FXML
    private JFXButton btn_novo;
    @FXML
    private JFXButton btn_alterar;
    @FXML
    private JFXButton btn_apagar;
    @FXML
    private JFXButton btn_limpar;
    @FXML
    private TableView<Cliente> tv_table;
    @FXML
    private TableColumn<Cliente, Integer> c_codigo;
    @FXML
    private TableColumn<Cliente, String> c_nome;
    @FXML
    private TableColumn<Cliente, String> c_cpf;
    @FXML
    private TableColumn<Cliente, String> c_fone;
    @FXML
    private TableColumn<Cliente, String> c_email;
    @FXML
    private JFXTextField tf_buscar;
    @FXML
    private Button btn_buscar;
    @FXML
    private JFXTextField tf_codigo;
    @FXML
    private JFXTextField tf_nome;
    @FXML
    private JFXTextField tf_cpf;
    @FXML
    private JFXTextField tf_fone;
    @FXML
    private JFXTextField tf_email;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        tf_codigo.setDisable(true);
        Inicial();
        c_codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        c_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        c_cpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        c_fone.setCellValueFactory(new PropertyValueFactory<>("fone"));
        c_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        MaskFieldUtil.onlyAlfaNumericValue(tf_nome);
        MaskFieldUtil.cpfField(tf_cpf);
        MaskFieldUtil.foneField(tf_fone);
    }    

    private void Inicial()
    {
        btn_novo.setDisable(false);
        btn_alterar.setDisable(true);
        btn_apagar.setDisable(true);
        btn_limpar.setDisable(true);
        btn_novo.setText("Novo");
        
        tf_nome.setDisable(true);
        tf_cpf.setDisable(true);
        tf_fone.setDisable(true);
        tf_email.setDisable(true);
    }
    
    private void Liberar()
    {
        btn_alterar.setDisable(false);
        btn_apagar.setDisable(false);
        btn_limpar.setDisable(false);
        tf_nome.setDisable(false);
        tf_cpf.setDisable(false);
        tf_fone.setDisable(false);
        tf_email.setDisable(false);
    }
    
    private void Limpar()
    {
        tf_codigo.setText("");
        tf_nome.setText("");
        tf_cpf.setText("");
        tf_fone.setText("");
        tf_email.setText("");
        tf_buscar.setText("");
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
            if(!tf_nome.getText().isEmpty() && !tf_fone.getText().isEmpty())
            {
                Cliente cliente = new Cliente(0, tf_nome.getText(), tf_cpf.getText(), tf_fone.getText(), tf_email.getText());
                if(new ctrCliente().Salvar(cliente))
                {
                    Limpar();
                    Inicial();
                    tv_table.setItems(new ctrCliente().BuscarTodos());
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Falha na Inserção.");
                    alert.showAndWait();
                }
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Dados Insuficientes.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void ClickAlterar(ActionEvent event)
    {
        if(!tf_nome.getText().isEmpty() && !tf_fone.getText().isEmpty())
        {
            s_cliente.setNome(tf_nome.getText());
            s_cliente.setCpf(tf_cpf.getText());
            s_cliente.setFone(tf_fone.getText());
            s_cliente.setEmail(tf_email.getText());
            
            if(new ctrCliente().Alterar(s_cliente))
            {
                Limpar();
                Inicial();
                tv_table.setItems(new ctrCliente().BuscarTodos());
            }
            else
                new JOptionPane("Erro ao alterar");
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Dados Insuficientes.");
            alert.showAndWait();
        }
    }

    @FXML
    private void ClickApagar(ActionEvent event)
    {
        if(new ctrCliente().Apagar(s_cliente))
        {
            Limpar();
            Inicial();
            tv_table.setItems(new ctrCliente().BuscarTodos());
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Falha na Exclusão.");
            alert.showAndWait();
            Limpar();
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
            s_cliente = tv_table.getSelectionModel().getSelectedItem();
            tf_codigo.setText(String.valueOf(s_cliente.getCodigo()));
            tf_nome.setText(s_cliente.getNome());
            tf_cpf.setText(s_cliente.getCpf());
            tf_fone.setText(s_cliente.getFone());
            tf_email.setText(s_cliente.getEmail());
        }
    }

    @FXML
    private void ClickBuscar(ActionEvent event)
    {
        if(tf_buscar.getText().isEmpty())
            tv_table.setItems(new ctrCliente().BuscarTodos());
        else
        {
            tv_table.getItems().clear();
            tv_table.getItems().add(new ctrCliente().Buscar(tf_buscar.getText()));
        }
    }
    
}
