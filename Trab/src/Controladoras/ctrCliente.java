package Controladoras;

import Classes.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ctrCliente
{
    private String sql;
    
    public boolean Salvar(Cliente o) throws SQLException
    {
        sql = "INSERT INTO cliente(cli_cod, cli_nome, cli_cpf, cli_fone, cli_email) VALUES ($1,'$2','$3','$4', '$5')";
        sql = sql.replace("$1", String.valueOf(JDBC.Banco.getCon().getMaxPK("cliente", "cli_cod") + 1));
        sql = sql.replace("$2", o.getNome());
        sql = sql.replace("$3", o.getCpf());
        sql = sql.replace("$4", o.getFone());
        sql = sql.replace("$5", o.getEmail());
        
        return JDBC.Banco.getCon().manipular(sql);
    }
    
    public boolean Alterar(Cliente o)
    {
        sql = "UPDATE cliente SET cli_nome = '$2', cli_cpf = '$3', cli_fone = '$4', cli_email = '$5' WHERE cli_cod = " + o.getCodigo();
        sql = sql.replace("$2", o.getNome());
        sql = sql.replace("$3", o.getCpf());
        sql = sql.replace("$4", o.getFone());
        sql = sql.replace("$5", o.getEmail());
        
        return JDBC.Banco.getCon().manipular(sql);
    }
    
    public boolean Apagar(Cliente o)
    {
        sql = "DELETE FROM cliente WHERE cli_cod = " + o.getCodigo();

       return JDBC.Banco.getCon().manipular(sql); 
    }
    
    public Cliente Buscar(String filtro)
    {
        Cliente obj = null;

        ResultSet rs = JDBC.Banco.getCon().consultar("SELECT * FROM cliente WHERE cli_nome = '" + filtro + "'");

        try
        {
            if(rs != null && rs.next())
                obj = new Cliente(rs.getInt("cli_cod"), rs.getString("cli_nome"), rs.getString("cli_cpf"), rs.getString("cli_fone"), rs.getString("cli_email"));
        }
        catch(SQLException ex)
        {

        }
        return obj;
    }
    
    public ObservableList<Cliente> BuscarTodos()
    {
        ObservableList<Cliente> obj = FXCollections.observableArrayList();

        ResultSet rs = JDBC.Banco.getCon().consultar("SELECT * FROM cliente");

        try
        {
            if(rs != null && rs.next())
                obj.add(new Cliente(rs.getInt("cli_cod"), rs.getString("cli_nome"), rs.getString("cli_cpf"), rs.getString("cli_fone"), rs.getString("cli_email")));
        }
        catch(SQLException ex)
        {

        }
        return obj;
    }
}
