package Entidades.A;

import Banco.Banco;
import Entidades.Classificacao;
import Entidades.Produto;
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
        String sql = "select * from Produtos_Orcamento where orc_codigo = " + orcamento.getCodigo();
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            while (rs.next())
            {
                a.add(new ItemOrcamentoProduto(rs.getInt("orc_codigo"), rs.getInt("prod_codigo"), rs.getDouble("prod_orc_preco"), rs.getInt("prod_orc_quantidade")));
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
        String sql = "select *from produto where prod_codigo = " + cod;
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            while (rs.next())
            {
                p = new Produto(rs.getInt("prod_codigo"),
                        rs.getString("prod_nome"),
                        rs.getInt("prod_quantidade"),
                        new Classificacao(rs.getInt("class_codigo")),
                        rs.getDouble("prod_preco"));

            }
        } catch (Exception ex)
        {
            p = null;
        }
        return p;
    }

    public boolean remove()
    {
        String sql = "delete from Produtos_Orcamento where orc_codigo = " + orcamento.getCodigo() + " and prod_codigo = " + produto.getCodigo();
        return Banco.getCon().manipular(sql);
    }

}
