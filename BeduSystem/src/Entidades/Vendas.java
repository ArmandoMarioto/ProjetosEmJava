package Entidades;

public class Vendas extends Movimento
{
    private int codigo;
    private String descricao;
    private Cliente cliente;
    private Funcionário funcionario;
    private Recebimento recebimento;
    private double total;
    private int parcelas;
    private double juros;

    public Vendas()
    {
    }

    public Vendas(int codigo, Cliente cliente, Funcionário funcionario, Recebimento recebimento, double total, int parcelas, double juros)
    {
        this.codigo = codigo;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.recebimento = recebimento;
        this.total = total;
        this.parcelas = parcelas;
        this.juros = juros;
        this.descricao = "";
    }

    public Vendas(int codigo, String descricao, Cliente cliente, Funcionário funcionario, Recebimento recebimento, double total, int parcelas, double juros)
    {
        this.codigo = codigo;
        this.descricao = descricao;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.recebimento = recebimento;
        this.total = total;
        this.parcelas = parcelas;
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

    public Cliente getCliente()
    {
        return cliente;
    }

    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }

    public Funcionário getFuncionario()
    {
        return funcionario;
    }

    public void setFuncionario(Funcionário funcionario)
    {
        this.funcionario = funcionario;
    }

    public Recebimento getRecebimento()
    {
        return recebimento;
    }

    public void setRecebimento(Recebimento recebimento)
    {
        this.recebimento = recebimento;
    }

    public double getTotal()
    {
        return total;
    }

    public void setTotal(double total)
    {
        this.total = total;
    }

    public int getParcelas()
    {
        return parcelas;
    }

    public void setParcelas(int parcelas)
    {
        this.parcelas = parcelas;
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
