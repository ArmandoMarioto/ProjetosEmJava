
package Entidades.NovaEntidades;

import Entidades.Produto;

public class ItemOrdemProduto
{

    private OrdemServico os;
    private Produto produto;
    private Double valor;
    private Integer qtd;

    public ItemOrdemProduto(Integer codigo_os, Produto produto, Double valor, Integer qtd)
    {
        this.os = new OrdemServico();
        this.os.setCodigo(codigo_os);
        this.produto = produto;
        this.valor = valor;
        this.qtd = qtd;
    }

    public Integer getCodigo_os()
    {
        return os.getCodigo();
    }

    public void setCodigo_os(Integer codigo_os)
    {
        this.os.setCodigo(codigo_os);
    }

    public Produto getProduto()
    {
        return produto;
    }

    public void setProduto(Produto produto)
    {
        this.produto = produto;
    }

    public Double getValor()
    {
        return valor;
    }

    public void setValor(Double valor)
    {
        this.valor = valor;
    }

    public Integer getQtd()
    {
        return qtd;
    }

    public void setQtd(Integer qtd)
    {
        this.qtd = qtd;
    }

}
