package Entidades;

public class Compra extends Movimento 
{
	private int codigo;
	private String descricao;
	private Fornecedor fornecedor;
	private Funcionário funcionario;
	private int parcelas;
	private double total;
	private double juros;

    public Compra()
    {
    }

    public Compra(int codigo, Fornecedor fornecedor, Funcionário funcionario, int parcelas, double total, double juros)
    {
        this.codigo = codigo;
        this.fornecedor = fornecedor;
        this.funcionario = funcionario;
        this.parcelas = parcelas;
        this.total = total;
        this.juros = juros;
        this.descricao = "";
    }

    public Compra(int codigo, String descricao, Fornecedor fornecedor, Funcionário funcionario, int parcelas, double total, double juros)
    {
        this.codigo = codigo;
        this.descricao = descricao;
        this.fornecedor = fornecedor;
        this.funcionario = funcionario;
        this.parcelas = parcelas;
        this.total = total;
        this.juros = juros;
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

    public Fornecedor getFornecedor()
    {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor)
    {
        this.fornecedor = fornecedor;
    }

    public Funcionário getFuncionario()
    {
        return funcionario;
    }

    public void setFuncionario(Funcionário funcionario)
    {
        this.funcionario = funcionario;
    }

    public int getParcelas()
    {
        return parcelas;
    }

    public void setParcelas(int parcelas)
    {
        this.parcelas = parcelas;
    }

    public double getTotal()
    {
        return total;
    }

    public void setTotal(double total)
    {
        this.total = total;
    }

    public double getJuros()
    {
        return juros;
    }

    public void setJuros(double juros)
    {
        this.juros = juros;
    }

}
