
package Banco;

public class Banco
{
    static public Conexao con;
    private Banco()
    { 
        
    }
    
    static public boolean conectar()
    {
        con = new Conexao();
        return con.conectar("jdbc:postgresql://localhost/", "BeduSystem", "postgres", "postgres123");
    }
    
    static public Conexao getCon()
    {  
        return con;
    }    
}
