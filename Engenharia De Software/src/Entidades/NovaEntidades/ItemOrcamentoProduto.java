
package Entidades.NovaEntidades;

import Banco.Banco;
import Entidades.Fabricante;
import Entidades.Produto;
import Entidades.Tipo;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ItemOrcamentoProduto
{

    private Orcamento orcamento;
    private Produto produto;
    private Double valor;
    private Integer qtd;

    public ItemOrcamentoProduto()
    {
    }

    public ItemOrcamentoProduto(Integer codigo_orcamento, Produto produto, Double valor, Integer qtd)
    {
        this.orcamento = new Orcamento();
        this.orcamento.setCodigo(codigo_orcamento);
        this.produto = produto;
        this.valor = valor;
        this.qtd = qtd;
    }

    public ItemOrcamentoProduto(Produto produto, Double valor, Integer qtd)
    {
        this.produto = produto;
        this.valor = valor;
        this.qtd = qtd;
    }

    public ItemOrcamentoProduto(int orc_prod, int prod_cod, double valor, int qtd)
    {
        this.orcamento = new Orcamento();
        this.orcamento.setCodigo(orc_prod);
        this.produto = getProd(prod_cod);
        this.valor = valor;
        this.qtd = qtd;
    }

    public ItemOrcamentoProduto(Produto p)
    {
        this.produto = p;
    }

    public ItemOrcamentoProduto(int orc_cod)
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

    @Override
    public String toString()
    {
        return produto.getNome();
    }

    public ArrayList<ItemOrcamentoProduto> get()
    {
        ArrayList<ItemOrcamentoProduto> a = new ArrayList<>();
        String sql = "select * from itensorpro where orc_cod = " + orcamento.getCodigo();
        ResultSet rs = Banco.getConexao().consultar(sql);
        try
        {
            while (rs.next())
            {
                a.add(new ItemOrcamentoProduto(rs.getInt("orc_cod"), rs.getInt("prod_cod"), rs.getDouble("valor"), rs.getInt("qtd")));
            }
        } catch (Exception ex)
        {
            a = new ArrayList<>();
        }
        return a;
    }

    private Produto getProd(int cod)
    {
        Produto p = null;
        String sql = "select *from produto where prod_cod = " + cod;
        ResultSet rs = Banco.getConexao().consultar(sql);
        try
        {
            while (rs.next())
            {
                p = new Produto(rs.getInt("prod_cod"),
                        rs.getString("prod_nome"),
                        rs.getDouble("prod_preco"),
                        new Fabricante(rs.getInt("fab_cod")),
                        new Tipo(rs.getInt("tip_cod")),
                        rs.getInt("prod_quantidade"),
                        rs.getString("prod_obs"));
            }
        } catch (Exception ex)
        {
            p = null;
        }
        return p;
    }

    public boolean remove()
    {
        String sql = "delete from itensorpro where orc_cod = " + orcamento.getCodigo() + " and prod_cod = " + produto.getCodigo();
        return Banco.getConexao().manipular(sql);
    }

}
