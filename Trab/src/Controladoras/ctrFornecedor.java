
package Controladoras;

import Classes.Fornecedor;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ctrFornecedor
{
    private String sql;
    
    public boolean Salvar(Fornecedor o) throws SQLException
    {
        sql = "INSERT INTO fornecedor(for_cod, for_nome, for_fone, for_email) VALUES ($1,'$2','$3','$4')";
        sql = sql.replace("$1", String.valueOf(JDBC.Banco.getCon().getMaxPK("fornecedor", "for_cod") + 1));
        sql = sql.replace("$2", o.getNome());
        sql = sql.replace("$3", o.getFone());
        sql = sql.replace("$4", o.getEmail());
        
        return JDBC.Banco.getCon().manipular(sql);
    }
    
    public boolean Alterar(Fornecedor o)
    {
        sql = "UPDATE fornecedor SET for_nome = '$2', for_fone = '$3', for_email = '$4' WHERE for_cod = " + o.getCodigo();
        sql = sql.replace("$2", o.getNome());
        sql = sql.replace("$3", o.getFone());
        sql = sql.replace("$4", o.getEmail());
        
        return JDBC.Banco.getCon().manipular(sql);
    }
    
    public boolean Apagar(Fornecedor o)
    {
        sql = "DELETE FROM fornecedor WHERE for_cod = " + o.getCodigo();

       return JDBC.Banco.getCon().manipular(sql); 
    }
    
    public Fornecedor Buscar(String filtro)
    {
        Fornecedor obj = null;

        ResultSet rs = JDBC.Banco.getCon().consultar("SELECT * FROM fornecedor WHERE for_nome = '" + filtro + "'");

        try
        {
            if(rs != null && rs.next())
                obj = new Fornecedor(rs.getInt("for_cod"), rs.getString("for_nome"), rs.getString("for_email"), rs.getString("for_fone"));
        }
        catch(SQLException ex)
        {

        }
        return obj;
    }
    
    public ObservableList<Fornecedor> BuscarTodos()
    {
        ObservableList<Fornecedor> obj = FXCollections.observableArrayList();

        ResultSet rs = JDBC.Banco.getCon().consultar("SELECT * FROM fornecedor");

        try
        {
            while(rs != null && rs.next())
                obj.add(new Fornecedor(rs.getInt("for_cod"), rs.getString("for_nome"), rs.getString("for_email"), rs.getString("for_fone")));
        }
        catch(SQLException ex)
        {

        }
        return obj;
    }
}
