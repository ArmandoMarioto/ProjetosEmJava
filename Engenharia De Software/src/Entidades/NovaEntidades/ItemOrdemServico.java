
package Entidades.NovaEntidades;

public class ItemOrdemServico
{

    private OrdemServico os;
    private Servico servico;
    private Double valor;
    private Integer qtd;

    public ItemOrdemServico(Integer codigo_os, Servico servico, Double valor, Integer qtd)
    {
        this.os = new OrdemServico();
        this.os.setCodigo(codigo_os);
        this.servico = servico;
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

    public Servico getServico()
    {
        return servico;
    }

    public void setServico(Servico servico)
    {
        this.servico = servico;
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
