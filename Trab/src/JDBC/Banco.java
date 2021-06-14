package JDBC;

public class Banco 
{   
    static private Conexao con;
    static public boolean acesso;
    private Banco()
    { 
        
    }
    
    static public boolean conectar()
    {
        con = new Conexao();
        return con.conectar("jdbc:postgresql://localhost/", "maveleiro", "postgres", "postgres123");
    }
    
    static public Conexao getCon()
    {  
        return con;
    }    
}
