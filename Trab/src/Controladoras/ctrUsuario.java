package Controladoras;
import Classes.Usuario;
import JDBC.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ctrUsuario
{
    private String sql;
    
    public boolean Salvar(Usuario o) throws SQLException
    {
        sql = "INSERT INTO usuario(usu_cod,usu_nome,usu_senha, usu_adm) VALUES ($1,'$2','$3',$4)";
        sql = sql.replace("$1", String.valueOf(JDBC.Banco.getCon().getMaxPK("usuario", "usu_cod") + 1));
        sql = sql.replace("$2", o.getNome());
        sql = sql.replace("$3", o.getSenha());
        sql = sql.replace("$4", String.valueOf(o.isAdm()));
        
        return JDBC.Banco.getCon().manipular(sql);
    }
    
    public boolean Alterar(Usuario o)
    {
        sql = "UPDATE usuario SET usu_nome = '$2', usu_senha = '$3', usu_adm = $4 WHERE usu_cod = " + o.getCodigo();
        sql = sql.replace("$2", o.getNome());
        sql = sql.replace("$3", o.getSenha());
        sql = sql.replace("$4", String.valueOf(o.isAdm()));

        
        return JDBC.Banco.getCon().manipular(sql);
    }
    
    public boolean Apagar(Usuario o)
    {
        boolean result = false;
        ResultSet rs;

        try
        {
            rs = JDBC.Banco.getCon().consultar("SELECT COUNT(*) AS n FROM usuario WHERE usu_adm IS true");
            rs.next();
            sql = "DELETE FROM usuario WHERE usu_cod = " + o.getCodigo();

            if(o.isAdm())
            {
                if(rs.getInt("n") > 1)
                    result = JDBC.Banco.getCon().manipular(sql);
            }
            else
                result = JDBC.Banco.getCon().manipular(sql);
        }
        catch(Exception ex)
        {
            
        }
        
        return result;
    }
    
    public Usuario Procurar(String login)
    {
        Usuario obj = null;

        ResultSet rs = JDBC.Banco.getCon().consultar("SELECT * FROM usuario WHERE usu_nome = " + login);

        try
        {
            if(rs.next())
                obj = new Usuario(rs.getInt("usu_cod"), rs.getString("usu_nome"), rs.getString("usu_senha"), rs.getBoolean("usu_adm"));
        }
        catch(SQLException ex)
        {

        }
        return obj;
    }
    
    public Usuario BuscarCodigo(String codigo)
    {
        Usuario obj = null;

        ResultSet rs = JDBC.Banco.getCon().consultar("SELECT * FROM usuario WHERE usu_cod = " + codigo);

        try
        {
            if(rs.next())
                obj = new Usuario(rs.getInt("usu_cod"), rs.getString("usu_nome"), rs.getString("usu_senha"), rs.getBoolean("usu_adm"));
        }
        catch(SQLException ex)
        {

        }
        return obj;
    }
    
    public Usuario BuscarLoginSenha(String login, String senha)
    {
        Usuario obj = null;

        ResultSet rs = JDBC.Banco.getCon().consultar("SELECT * FROM usuario WHERE usu_nome = '" + login + "' AND usu_senha = '" + senha + "'");

        try
        {
            if(rs != null && rs.next())
                obj = new Usuario(rs.getInt("usu_cod"), rs.getString("usu_nome"), rs.getString("usu_senha"), rs.getBoolean("usu_adm"));
        }
        catch(SQLException ex)
        {

        }
        return obj;
    }
    
    public ObservableList<Usuario> BuscarTodos()
    {
        ObservableList<Usuario> array = FXCollections.observableArrayList();
        
        ResultSet rs = JDBC.Banco.getCon().consultar("SELECT * FROM usuario");

         try
         {
             while(rs.next())
                 array.add(new Usuario(rs.getInt("usu_cod"), rs.getString("usu_nome"), rs.getString("usu_senha"), rs.getBoolean("usu_adm")));
         }
         catch(SQLException ex)
         {

         }

         return array;     
    }
    
}
