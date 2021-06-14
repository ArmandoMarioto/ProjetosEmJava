
package Banco;

public class BD
{   
    static private Conexao con;
    static public boolean acesso;
    private BD()
    { 
        
    }
    
    static public boolean conectar()
    {
        con = new Conexao();
        return con.conectar("jdbc:postgresql://localhost/", "Eng2", "postgres", "postgres123");
    }
    
    static public Conexao getCon()
    {  
        return con;
    }    
}
