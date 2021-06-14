package Classes;

public class Material
{
    private int codigo;
    private String nome;
    private Categoria categoria;
    private int estoque_min;
    private int estoque;
    private double valor_base;
    private double valor_venda;
    private int quantidade;
    private String descricao;

    public Material()
    {
        
    }

    public Material(String nome, Categoria categoria, int estoque_min, int estoque, double valor_base, double valor_venda, int quantidade, String descricao)
    {
        this.nome = nome;
        this.categoria = categoria;
        this.estoque_min = estoque_min;
        this.estoque = estoque;
        this.valor_base = valor_base;
        this.valor_venda = valor_venda;
        this.quantidade = quantidade;
        this.descricao = descricao;
    }

    public Material(int codigo, String nome, Categoria categoria, int estoque_min, int estoque, double valor_base, double valor_venda, int quantidade, String descricao)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
        this.estoque_min = estoque_min;
        this.estoque = estoque;
        this.valor_base = valor_base;
        this.valor_venda = valor_venda;
        this.quantidade = quantidade;
        this.descricao = descricao;
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

    public Categoria getCategoria()
    {
        return categoria;
    }

    public void setCategoria(Categoria categoria)
    {
        this.categoria = categoria;
    }

    public int getEstoque_min()
    {
        return estoque_min;
    }

    public void setEstoque_min(int estoque_min)
    {
        this.estoque_min = estoque_min;
    }

    public int getEstoque()
    {
        return estoque;
    }

    public void setEstoque(int estoque)
    {
        this.estoque = estoque;
    }

    public double getValor_base()
    {
        return valor_base;
    }

    public void setValor_base(double valor_base)
    {
        this.valor_base = valor_base;
    }

    public double getValor_venda()
    {
        return valor_venda;
    }

    public void setValor_venda(double valor_venda)
    {
        this.valor_venda = valor_venda;
    }

    public int getQuantidade()
    {
        return quantidade;
    }

    public void setQuantidade(int quantidade)
    {
        this.quantidade = quantidade;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }
    
    
}
