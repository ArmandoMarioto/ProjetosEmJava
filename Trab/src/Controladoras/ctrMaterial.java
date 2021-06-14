package Controladoras;

import Classes.Categoria;
import Classes.Material;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ctrMaterial
{
    private String sql;
    
    public boolean Salvar(Material o) throws SQLException
    {
        sql = "INSERT INTO material(mat_cod, mat_nome, mat_cat, mat_estq, mat_estq_min, mat_valor_base, mat_valor_venda, mat_quant, mat_desc) VALUES ($1,'$2', $3, $4, $5, $6, $7, $8, '$9')";
        sql = sql.replace("$1", String.valueOf(JDBC.Banco.getCon().getMaxPK("material", "mat_cod") + 1));
        sql = sql.replace("$2", o.getNome());
        sql = sql.replace("$3", String.valueOf(o.getCategoria().getCodigo()));
        sql = sql.replace("$4", String.valueOf(o.getEstoque()));
        sql = sql.replace("$5", String.valueOf(o.getEstoque_min()));
        sql = sql.replace("$6", String.valueOf(o.getValor_base()));
        sql = sql.replace("$7", String.valueOf(o.getValor_venda()));
        sql = sql.replace("$8", String.valueOf(o.getQuantidade()));
        sql = sql.replace("$9", o.getDescricao());
        
        return JDBC.Banco.getCon().manipular(sql);
    }
    
    public boolean Alterar(Material o)
    {
        sql = "UPDATE material SET mat_nome = '$2', mat_cat = $3, mat_estq = $4, mat_estq_min = $5, mat_valor_base = $6, mat_valor_venda = $7, mat_quant = $8, mat_desc = '$9' WHERE mat_cod = " + o.getCodigo();
        sql = sql.replace("$2", o.getNome());
        sql = sql.replace("$3", String.valueOf(o.getCategoria().getCodigo()));
        sql = sql.replace("$4", String.valueOf(o.getEstoque()));
        sql = sql.replace("$5", String.valueOf(o.getEstoque_min()));
        sql = sql.replace("$6", String.valueOf(o.getValor_base()));
        sql = sql.replace("$7", String.valueOf(o.getValor_venda()));
        sql = sql.replace("$8", String.valueOf(o.getQuantidade()));
        sql = sql.replace("$9", o.getDescricao());
        
        return JDBC.Banco.getCon().manipular(sql);
    }
    
    public boolean Apagar(Material o)
    {
        sql = "DELETE FROM material WHERE mat_cod = " + o.getCodigo();

       return JDBC.Banco.getCon().manipular(sql); 
    }
    
    public Material Buscar(String filtro)
    {
        Material obj = null;

        ResultSet rs = JDBC.Banco.getCon().consultar("SELECT * FROM fornecedor WHERE for_nome = '" + filtro + "'");
        
        
        try
        {
            if(rs != null && rs.next())
            {
                ResultSet rs2 = JDBC.Banco.getCon().consultar("SELECT * FROM categoria WHERE cat_cod = " + rs.getInt("mat_cat"));
                rs2.next();
                Categoria cat = new Categoria(rs2.getInt("cat_cod"), rs2.getString("cat_desc"));
                
                obj = new Material(rs.getInt("mat_cod"), rs.getString("mat_nome"), cat, rs.getInt("mat_estq"), rs.getInt("mat_estq_min"), rs.getDouble("mat_valor_base"), rs.getDouble("mat_valor_venda"), rs.getInt("mat_quant"), rs.getString("mat_desc"));
            }
                
        }
        catch(SQLException ex)
        {

        }
        return obj;
    }
    
    public ObservableList<Material> BuscarTodos()
    {
        ObservableList<Material> obj = FXCollections.observableArrayList();
        ResultSet rs = JDBC.Banco.getCon().consultar("SELECT * FROM material");
        
        
        try
        {
            while(rs != null && rs.next())
            {
                ResultSet rs2 = JDBC.Banco.getCon().consultar("SELECT * FROM categoria WHERE cat_cod = " + rs.getInt("mat_cat"));
                rs2.next();
                Categoria cat = new Categoria(rs2.getInt("cat_cod"), rs2.getString("cat_desc"));
                
                obj.add(new Material(rs.getInt("mat_cod"), rs.getString("mat_nome"), cat, rs.getInt("mat_estq"), rs.getInt("mat_estq_min"), rs.getDouble("mat_valor_base"), rs.getDouble("mat_valor_venda"), rs.getInt("mat_quant"), rs.getString("mat_desc")));
            }
                
        }
        catch(SQLException ex)
        {

        }
        return obj;
    }
}
