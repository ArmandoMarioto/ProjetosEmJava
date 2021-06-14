package Controladoras;

import Classes.Categoria;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ctrCategoria
{
    private String sql;
    
    public boolean Salvar(Categoria o) throws SQLException
    {
        sql = "INSERT INTO categoria(cat_cod, cat_desc) VALUES ($1,'$2')";
        sql = sql.replace("$1", String.valueOf(JDBC.Banco.getCon().getMaxPK("categoria", "cat_cod") + 1));
        sql = sql.replace("$2", o.getDescricao());
        
        return JDBC.Banco.getCon().manipular(sql);
    }
    
    public boolean Alterar(Categoria o)
    {
        sql = "UPDATE categoria SET cat_desc = '$2' WHERE cat_cod = " + o.getCodigo();
        sql = sql.replace("$2", o.getDescricao());
        
        return JDBC.Banco.getCon().manipular(sql);
    }
    
    public boolean Apagar(Categoria o)
    {
        sql = "DELETE FROM categoria WHERE cat_cod = " + o.getCodigo();

       return JDBC.Banco.getCon().manipular(sql); 
    }
    
    public Categoria Buscar(String filtro)
    {
        Categoria obj = null;

        ResultSet rs = JDBC.Banco.getCon().consultar("SELECT * FROM categoria WHERE cat_nome = '" + filtro + "'");

        try
        {
            if(rs != null && rs.next())
                obj = new Categoria(rs.getInt("cat_cod"), rs.getString("cat_desc"));
        }
        catch(SQLException ex)
        {

        }
        return obj;
    }
    
    public ObservableList<Categoria> BuscarTodos()
    {
        ObservableList<Categoria> obj = FXCollections.observableArrayList();

        ResultSet rs = JDBC.Banco.getCon().consultar("SELECT * FROM categoria");

        try
        {
            while(rs != null && rs.next())
                obj.add(new Categoria(rs.getInt("cat_cod"), rs.getString("cat_desc")));
        }
        catch(SQLException ex)
        {

        }
        return obj;
    }
}
