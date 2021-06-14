package Classes;

public class Usuario
{
    private int codigo;
    private String nome;
    private String senha;
    private boolean adm;

    public Usuario()
    {
        
    }

    public Usuario(int codigo, String nome, String senha, boolean adm)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.senha = senha;
        this.adm = adm;
    }
    
    public int getCodigo()
    {
        return codigo;
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }

    public boolean isAdm()
    {
        return adm;
    }

    public void setAdm(boolean adm)
    {
        this.adm = adm;
    }
}
