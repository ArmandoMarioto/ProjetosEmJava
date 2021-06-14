package Banco;

public class Banco
{

    static private Conexao con = null;

    private Banco()
    {
    }

    public static Conexao getConexao()
    {
        return con;
    }

    static public boolean conectar()
    {
        if (con == null)
        {
            con = new Conexao();
            return con.conectar("jdbc:postgresql://localhost:5432/", "SGAD2", "postgres", "postgres123");
        }
        return true;
    }

}
