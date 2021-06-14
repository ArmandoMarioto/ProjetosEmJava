package Banco;

public class Banco
{

    static public Conexao con = null;

    private Banco()
    {
    }

    static public boolean conectar()
    {
        if (con == null)
        {
            con = new Conexao();
            return con.conectar("jdbc:postgresql://localhost:5432/", "SGAD", "postgres", "postgres123");
        }
        return true;
    }
}
