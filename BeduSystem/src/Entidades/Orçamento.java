package Entidades;

public class Orçamento
{
    private int codigo;
    private String descricao;
    private Funcionário funcionario;
    private Cliente cliente;
    private Veiculo veiculo;

    public Orçamento()
    {
    }

    public Orçamento(String descricao, Funcionário funcionario, Cliente cliente)
    {
        this.descricao = descricao;
        this.funcionario = funcionario;
        this.cliente = cliente;
    }

    public Orçamento(int codigo, Funcionário funcionario, Cliente cliente)
    {
        this.codigo = codigo;
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.descricao = "";
    }

    public Orçamento(int codigo, String descricao, Funcionário funcionario, Cliente cliente)
    {
        this.codigo = codigo;
        this.descricao = descricao;
        this.funcionario = funcionario;
        this.cliente = cliente;
    }

    public Orçamento(int codigo, String descricao, Funcionário funcionario, Cliente cliente, Veiculo veiculo)
    {
        this.codigo = codigo;
        this.descricao = descricao;
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.veiculo = veiculo;
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

    public Funcionário getFuncionario()
    {
        return funcionario;
    }

    public void setFuncionario(Funcionário funcionario)
    {
        this.funcionario = funcionario;
    }

    public Cliente getCliente()
    {
        return cliente;
    }

    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo()
    {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo)
    {
        this.veiculo = veiculo;
    }
    
}
