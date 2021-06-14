package Classes;

public class Cliente
{
    private int codigo;
    private String nome;
    private String cpf;
    private String email;
    private String fone;

    public Cliente()
    {
        
    }

    public Cliente(String nome, String cpf, String email, String fone)
    {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.fone = fone;
    }

    public Cliente(int codigo, String nome, String cpf, String fone, String email)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.fone = fone;
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

    public String getCpf()
    {
        return cpf;
    }

    public void setCpf(String cpf)
    {
        this.cpf = cpf;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getFone()
    {
        return fone;
    }

    public void setFone(String fone)
    {
        this.fone = fone;
    }
}
