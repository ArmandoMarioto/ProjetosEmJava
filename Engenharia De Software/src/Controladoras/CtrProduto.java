package Controladoras;

import Abstract.BuscaTemplate;
import Entidades.Fabricante;
import Entidades.Produto;
import Entidades.Tipo;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;

public class CtrProduto extends BuscaTemplate
{

    private static CtrProduto con;

    private CtrProduto()
    {
    }

    public static CtrProduto instancia()
    {
        if (con == null)
        {
            con = new CtrProduto();
        }
        return con;
    }

    public static void GetProduto(JFXTextField tcodigo, JFXTextField tnome, JFXTextField tpreco, JFXComboBox<Object> cbfabricante/*, JFXComboBox<Object> cbfornecedor*/, JFXComboBox<Object> cbtipo, JFXTextField tquantidade, JFXTextArea tobs, Object o)
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

            String aux = p.getPreco().toString().substring(p.getPreco().toString().length() - 2, p.getPreco().toString().length() - 1).equals(".") ? "0" : "";
            tpreco.setText(p.getPreco().toString() + aux);
        }
    }

    public static void CarregaCombosDaTela(JFXComboBox<Object> cbfabricante/*, JFXComboBox<Object> cbfornecedor*/, JFXComboBox<Object> cbtipo)
    {
        cbfabricante.setItems(FXCollections.observableArrayList(new Fabricante().get("")));
        cbtipo.setItems(FXCollections.observableArrayList(new Tipo().get()));
    }

    public static boolean SalvarProduto(String nome, Double preco, Object fabricante, Object tipo, Integer quantidade, String obs)
    {
        return new Produto(nome, preco, fabricante, tipo, quantidade, obs).insert();
    }

    public static boolean Remove(Integer o)
    {
        Produto p = new Produto();
        p.setCodigo(o);
        return p.delete();
    }

    public static boolean Altera(Integer codigo, String nome, Double preco, Object fabricante, Object tipo, Integer quantidade, String obs)
    {
        return new Produto(codigo, nome, preco, fabricante, tipo, quantidade, obs).update();
    }

    /*public static ArrayList<Object> GetListaProdutos(String filtro, String filtro2)
    {
        return new Produto().GetAvancado(filtro, filtro2);
    }*/

    @Override
    protected String MontaStringFiltros(String filtro1, String filtro2) 
    {
        String sql = "select *from produto";
        if (!filtro1.isEmpty())
        {
            if (filtro2.equals("Nome"))
                sql += " Where prod_nome like '%" + filtro1 + "%'";
            else if (filtro2.equals("Tipo"))
                sql += " INNER JOIN tipo ON produto.tip_cod = tipo.tip_cod WHERE tipo.tip_nome = '" + filtro1 + "'";
        }
        return sql;
    }

    @Override
    protected Object InstanciaItem(ResultSet rs) 
    {
        try 
        {
            return new Produto(rs.getInt("prod_cod"),
                    rs.getString("prod_nome"),
                    rs.getDouble("prod_preco"),
                    new Fabricante(rs.getInt("fab_cod")),
                    new Tipo(rs.getInt("tip_cod")),
                    rs.getInt("prod_quantidade"),
                    rs.getString("prod_obs"));
        } catch (SQLException ex) 
        {
            System.out.println("Classe CtrProduto: "+ex.getMessage());
            return null;
        }
    }
}
