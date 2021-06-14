package JDBC;

import Classes.SystemData;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class Conexao 
{
    private Connection connect;
    private String erro;
   
    public Conexao()
    {  
        erro = "";
        connect = null;
    }
    
    public boolean conectar(String local, String banco, String usuario, String senha)
    {  
        boolean conectado=false;
        try 
        {
            //Class.forName(driver); "org.postgresql.Driver");
            String url = local+banco; //"jdbc:postgresql://localhost/"+banco;
            connect = DriverManager.getConnection( url, usuario,senha);
            conectado = true;
        }
        catch ( SQLException sqlex )
        { 
            erro="Impossivel conectar com a base de dados: " + sqlex.toString(); 
        }
        catch ( Exception ex )
        { 
            erro="Outro erro: " + ex.toString(); 
        }
        return conectado;
    }
    
    public String getMensagemErro() 
    {
        return erro;
    }
    
    public boolean getEstadoConexao() 
    {
        return (connect!=null);
    }
    
    public boolean manipular(String sql) // inserir, alterar,excluir
    {   
        boolean executou=false;
       
        try 
        {
            Statement statement = connect.createStatement();
            int result = statement.executeUpdate( sql );
            statement.close();
            
            if(result >= 1)
                executou = true;
        }
        catch ( SQLException sqlex )
        {  
            erro = "Erro: " + sqlex.toString();
            System.out.println(erro);
        }
        
        return executou;
    }
    
    public ResultSet consultar(String sql)
    {   
        ResultSet rs = null;
        
        try 
        {
           Statement statement = connect.createStatement();
           rs = statement.executeQuery( sql );
           //statement.close();
        }
        catch ( SQLException sqlex )
        { 
            erro="Erro: "+sqlex.toString();
            rs = null;
        }
        
        return rs;
    }
    
    public int getMaxPK(String tabela,String chave) 
    {
        String sql = "select max(" + chave + ") from " + tabela;
        int max = 0;
        ResultSet rs = consultar("select * from " + tabela);
        
        try 
        {
            if(rs != null)
            {
                rs = consultar(sql);
                if(rs != null && rs.next())
                    max = rs.getInt(1);
                else
                    max = 0;
            }
            else
                max = 0;
        }
        catch (SQLException sqlex)
        { 
             erro = "Erro: " + sqlex.toString();
             max = -1;
        }
        
        return max;
    }
    
    public Connection getConnection()
    {
        return connect;
    }
}
