package Controladoras;

import Entidades.Fabricante;
import Entidades.Produto;
import Entidades.Tipo;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.util.ArrayList;
import javafx.collections.FXCollections;

public class CtrProduto
{

    private CtrProduto()
    {

    }

    public static void GetProduto(JFXTextField tcodigo, JFXTextField tnome, JFXTextField tpreco, JFXComboBox<Object> cbfabricante/*, JFXComboBox<Object> cbfornecedor*/, JFXComboBox<Object> cbtipo, JFXTextField tquantidade, JFXTextArea tobs, JFXTextField tlote, Object o)
    {
        int i;
        Produto p = (Produto) o;
        if (o != null)
        {
            tcodigo.setText(p.getCodigo().toString());
            tnome.setText(p.getNome());
            for (i = 0; i < cbfabricante.getItems().size() && ((Fabricante) (cbfabricante.getItems().get(i))).getCodigo() != p.getFabricante().getCodigo(); i++)
            {
            }
            cbfabricante.getSelectionModel().select(i);
            /*for (i = 0; i < cbfornecedor.getItems().size() && ((Fornecedor)(cbfornecedor.getItems().get(i))).getCodigo() != p.getFornecedor().getCodigo(); i++){}
            cbfornecedor.getSelectionModel().select(i);*/
            for (i = 0; i < cbtipo.getItems().size() && ((Tipo) (cbtipo.getItems().get(i))).getCodigo() != p.getTipo().getCodigo(); i++)
            {
            }
            cbtipo.getSelectionModel().select(i);
            tquantidade.setText(p.getQuantidade().toString());
            tobs.setText(p.getObs());
            tlote.setText(p.getLote());

            String aux = p.getPreco().toString().substring(p.getPreco().toString().length() - 2, p.getPreco().toString().length() - 1).equals(".") ? "0" : "";
            tpreco.setText(p.getPreco().toString() + aux);
        }
    }

    public static void CarregaCombosDaTela(JFXComboBox<Object> cbfabricante/*, JFXComboBox<Object> cbfornecedor*/, JFXComboBox<Object> cbtipo)
    {
        cbfabricante.setItems(FXCollections.observableArrayList(new Fabricante().get()));
        //cbfornecedor.setItems(FXCollections.observableArrayList(new Fornecedor().get()));
        cbtipo.setItems(FXCollections.observableArrayList(new Tipo().get()));
    }

    public static boolean SalvarProduto(String nome, Double preco, Object fabricante/*, Object fornecedor*/, Object tipo, Integer quantidade, String obs, String lote)
    {
        return new Produto(nome, preco, fabricante/*, fornecedor*/, tipo, quantidade, obs, lote).Add();
    }

    public static boolean Remove(Integer o)
    {
        Produto p = new Produto();
        p.setCodigo(o);
        return p.Remove();
    }

    public static boolean Altera(Integer codigo, String nome, Double preco, Object fabricante/*, Object fornecedor*/, Object tipo, Integer quantidade, String obs, String lote)
    {
        return new Produto(codigo, nome, preco, fabricante/*, fornecedor*/, tipo, quantidade, obs, lote).Altera();
    }

    public static ArrayList<Object> GetListaProdutos(String filtro, String filtro2)
    {
        return new Produto().GetAvancado(filtro, filtro2);
    }
}
