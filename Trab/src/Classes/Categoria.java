package Classes;

public class Categoria
{
    private int codigo;
    private String descricao;

    public Categoria()
    {
        
    }

    public Categoria(String descricao)
    {
        this.descricao = descricao;
    }

    public Categoria(int codigo, String descricao)
    {
        this.codigo = codigo;
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

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    @Override
    public String toString()
    {
        return descricao;
    }
}
