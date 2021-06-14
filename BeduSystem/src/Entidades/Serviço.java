package Entidades;

public class Serviço
{
    private int codigo;
    private String descricao;
    private double preco;
    private Funcionário funcionario;

    public Serviço()
    {
    }

    public Serviço(int codigo, double preco, Funcionário funcionario)
    {
        this.codigo = codigo;
        this.preco = preco;
        this.funcionario = funcionario;
        this.descricao = "";
    }

    public Serviço(int codigo, String descricao, double preco, Funcionário funcionario)
    {
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
        this.funcionario = funcionario;
    }

    public int getCodigo()
    {
        return codigo;
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public double getPreco()
    {
        return preco;
    }

    public void setPreco(double preco)
    {
        this.preco = preco;
    }

    public Funcionário getFuncionario()
    {
        return funcionario;
    }

    public void setFuncionario(Funcionário funcionario)
    {
        this.funcionario = funcionario;
    }
    
}
