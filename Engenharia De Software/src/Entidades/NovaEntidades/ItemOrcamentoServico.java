
package Entidades.NovaEntidades;

import Banco.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemOrcamentoServico
{

    private Orcamento orcamento;
    private Servico servico;
    private Double valor;
    private Integer qtd;

    public ItemOrcamentoServico()
    {
    }

    public ItemOrcamentoServico(Integer codigo_orcamento, Servico servico, Double valor, Integer qtd)
    {
        this.orcamento = new Orcamento();
        this.orcamento.setCodigo(codigo_orcamento);
        this.servico = servico;
        this.valor = valor;
        this.qtd = qtd;
    }

    public ItemOrcamentoServico(Integer codigo_orcamento, int servico, Double valor, Integer qtd) throws SQLException
    {
        this.orcamento = new Orcamento();
        this.orcamento.setCodigo(codigo_orcamento);
        this.servico = new Servico(servico);
        this.valor = valor;
        this.qtd = qtd;
    }

    public ItemOrcamentoServico(Servico servico, Double valor, Integer qtd)
    {
        this.servico = servico;
        this.valor = valor;
        this.qtd = qtd;
    }

    public ItemOrcamentoServico(int orc_cod)
    {
        this.orcamento = new Orcamento();
        this.orcamento.setCodigo(orc_cod);
    }

    public Integer getCodigo_orcamento()
    {
        return orcamento.getCodigo();
    }

    public void setCodigo_orcamento(Integer codigo_orcamento)
    {
        this.orcamento.setCodigo(codigo_orcamento);
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

    @Override
    public String toString()
    {
        return servico.getNome();
    }

    public ArrayList<ItemOrcamentoServico> get()
    {
        ArrayList<ItemOrcamentoServico> a = new ArrayList<>();
        String sql = "select * from itensorser where orc_cod = " + orcamento.getCodigo();
        ResultSet rs = Banco.getConexao().consultar(sql);
        try
        {
            while (rs.next())
            {
                a.add(new ItemOrcamentoServico(rs.getInt("orc_cod"), rs.getInt("serv_cod"), rs.getDouble("valor"), rs.getInt("qtd")));
            }
        } catch (Exception ex)
        {
            a = new ArrayList<>();
            System.out.println(ex.getMessage());
        }
        return a;
    }

    public boolean remove()
    {
        String sql = "delete from itensorser where orc_cod = " + orcamento.getCodigo() + " and serv_cod = " + servico.getCodigo_servico();
        return Banco.getConexao().manipular(sql);
    }

}
