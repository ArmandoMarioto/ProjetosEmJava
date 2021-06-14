package FX;

import Classes.Categoria;
import Classes.MaskFieldUtil;
import Classes.Material;
import Controladoras.ctrCategoria;
import Controladoras.ctrMaterial;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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

public class FXMLMaterialController implements Initializable
{
    private Material s_material;
    @FXML
    private JFXButton btn_novo;
    @FXML
    private JFXButton btn_alterar;
    @FXML
    private JFXButton btn_apagar;
    @FXML
    private JFXButton btn_limpar;
    @FXML
    private TableView<Material> tv_table;
    @FXML
    private TableColumn<Material, Integer> c_codigo;
    @FXML
    private TableColumn<Material, String> c_nome;
    @FXML
    private TableColumn<Material, Categoria> c_categoria;
    @FXML
    private TableColumn<Material, Double> c_valorBase;
    @FXML
    private TableColumn<Material, Double> c_valorVenda;
    @FXML
    private TableColumn<Material, Integer> c_estoqueMin;
    @FXML
    private TableColumn<Material, Integer> c_estoque;
    @FXML
    private TableColumn<Material, Integer> c_quant;
    @FXML
    private TableColumn<Material, String> c_desc;
    @FXML
    private JFXTextField tf_buscar;
    @FXML
    private Button btn_buscar;
    @FXML
    private JFXTextField tf_codigo;
    @FXML
    private JFXTextField tf_nome;
    @FXML
    private JFXTextField tf_estoque;
    @FXML
    private JFXTextField tf_estoqueMin;
    @FXML
    private JFXTextField tf_valorBase;
    @FXML
    private JFXTextField tf_valorVenda;
    @FXML
    private JFXTextField tf_quant;
    @FXML
    private JFXTextField tf_desc;
    @FXML
    private JFXComboBox<Categoria> cb_categoria;
    

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        tf_codigo.setDisable(true);
        Inicial();
        c_codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        c_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        c_estoque.setCellValueFactory(new PropertyValueFactory<>("estoque"));
        c_estoqueMin.setCellValueFactory(new PropertyValueFactory<>("esoque_min"));
        c_categoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        c_valorBase.setCellValueFactory(new PropertyValueFactory<>("valor_base"));
        c_valorVenda.setCellValueFactory(new PropertyValueFactory<>("valor_venda"));
        c_quant.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        c_desc.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        
        cb_categoria.setItems(new ctrCategoria().BuscarTodos());
        cb_categoria.getSelectionModel().select(0);
        
        MaskFieldUtil.numericField(tf_estoque);
        MaskFieldUtil.numericField(tf_estoqueMin);
        MaskFieldUtil.numericField(tf_quant);
        MaskFieldUtil.monetaryField(tf_valorBase);
        MaskFieldUtil.monetaryField(tf_valorVenda);
    }    

    private void Inicial()
    {
        btn_novo.setDisable(false);
        btn_alterar.setDisable(true);
        btn_apagar.setDisable(true);
        btn_limpar.setDisable(true);
        btn_novo.setText("Novo");
        
        tf_nome.setDisable(true);
        tf_estoque.setDisable(true);
        tf_estoqueMin.setDisable(true);
        tf_valorBase.setDisable(true);
        tf_valorVenda.setDisable(true);
        tf_quant.setDisable(true);
        tf_desc.setDisable(true);
        cb_categoria.setDisable(true);
    }
    
    private void Liberar()
    {
        btn_alterar.setDisable(false);
        btn_apagar.setDisable(false);
        btn_limpar.setDisable(false);
        
        tf_nome.setDisable(false);
        tf_estoque.setDisable(false);
        tf_estoqueMin.setDisable(false);
        tf_valorBase.setDisable(false);
        tf_valorVenda.setDisable(false);
        tf_quant.setDisable(false);
        tf_desc.setDisable(false);
        cb_categoria.setDisable(false);
    }
    
    private void Limpar()
    {
        tf_codigo.setText("");
        tf_nome.setText("");
        tf_estoque.setText("");
        tf_estoqueMin.setText("");
        tf_valorBase.setText("");
        tf_valorVenda.setText("");
        tf_quant.setText("");
        tf_desc.setText("");
        cb_categoria.getSelectionModel().select(0);
        tf_buscar.setText("");
    }
    
    private boolean Check()
    {
        boolean result = true;
        
        if(tf_nome.getText().isEmpty())
            result = false;
        else if(tf_nome.getText().isEmpty())
            result = false;
        else if(tf_estoque.getText().isEmpty())
            result = false;
        else if(tf_estoqueMin.getText().isEmpty())
            result = false;
        else if(tf_valorBase.getText().isEmpty())
            result = false;
        else if(tf_valorVenda.getText().isEmpty())
            result = false;
        else if(tf_quant.getText().isEmpty())
            result = false;
  
        return result;
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
            if(Check())
            {
                Material material = new Material(0, tf_nome.getText(), cb_categoria.getValue(), Integer.parseInt(tf_estoque.getText()), Integer.parseInt(tf_estoqueMin.getText()), Double.parseDouble(tf_valorBase.getText()), Double.parseDouble(tf_valorVenda.getText()), Integer.parseInt(tf_quant.getText()), tf_desc.getText());
                if(new ctrMaterial().Salvar(material))
                {
                    Limpar();
                    Inicial();
                    tv_table.setItems(new ctrMaterial().BuscarTodos());
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
        if(Check())
        {
            s_material.setNome(tf_nome.getText());
            s_material.setCategoria(cb_categoria.getValue());
            s_material.setEstoque(Integer.parseInt(tf_estoque.getText()));
            s_material.setEstoque_min(Integer.parseInt(tf_estoqueMin.getText()));
            s_material.setValor_base(Double.parseDouble(tf_valorBase.getText()));
            s_material.setValor_venda(Double.parseDouble(tf_valorVenda.getText()));
            s_material.setQuantidade(Integer.parseInt(tf_quant.getText()));
            s_material.setDescricao(tf_desc.getText());
            
            if(new ctrMaterial().Alterar(s_material))
            {
                Limpar();
                Inicial();
                tv_table.setItems(new ctrMaterial().BuscarTodos());
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Falha na Alteração.");
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

    @FXML
    private void ClickApagar(ActionEvent event)
    {
        if(new ctrMaterial().Apagar(s_material))
        {
            Limpar();
            Inicial();
            tv_table.setItems(new ctrMaterial().BuscarTodos());
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
            s_material = tv_table.getSelectionModel().getSelectedItem();
            tf_codigo.setText(String.valueOf(s_material.getCodigo()));
            tf_nome.setText(s_material.getNome());
            tf_estoque.setText(String.valueOf(s_material.getEstoque()));
            tf_estoqueMin.setText(String.valueOf(s_material.getEstoque_min()));
            cb_categoria.setValue(s_material.getCategoria());
            tf_valorBase.setText(String.valueOf(s_material.getValor_base()));
            tf_valorVenda.setText(String.valueOf(s_material.getValor_venda()));
            tf_quant.setText(String.valueOf(s_material.getQuantidade()));
            tf_desc.setText(s_material.getDescricao());
        }
    }

    @FXML
    private void ClickBuscar(ActionEvent event)
    {
        if(tf_buscar.getText().isEmpty())
            tv_table.setItems(new ctrMaterial().BuscarTodos());
        else
        {
            tv_table.getItems().clear();
            tv_table.getItems().add(new ctrMaterial().Buscar(tf_buscar.getText()));
        } 
    }
    
}
