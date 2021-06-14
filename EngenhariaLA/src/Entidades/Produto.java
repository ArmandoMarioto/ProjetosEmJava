package Entidades;

import Banco.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Produto
{

    private Integer codigo;
    private String nome;
    private Double preco;
    private Fabricante fabricante;
    //private Fornecedor fornecedor;
    private Tipo tipo;
    private Integer quantidade;
    private String obs;
    private String lote;

    public Produto(Integer codigo, String nome, Double preco, Object fabricante/*, Object fornecedor*/, Object tipo, Integer quantidade, String obs, String lote)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.fabricante = (Fabricante) fabricante;
        //this.fornecedor = (Fornecedor) fornecedor;
        this.tipo = (Tipo) tipo;
        this.quantidade = quantidade;
        this.obs = obs;
        this.lote = lote;
    }

    public Produto(String nome, Double preco, Object fabricante/*, Object fornecedor*/, Object tipo, Integer quantidade, String obs, String lote)
    {
        this.nome = nome;
        this.preco = preco;
        this.fabricante = (Fabricante) fabricante;
        //this.fornecedor = (Fornecedor) fornecedor;
        this.tipo = (Tipo) tipo;
        this.quantidade = quantidade;
        this.obs = obs;
        this.lote = lote;
    }

    public Produto()
    {

    }

    public Fabricante getFabricante()
    {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante)
    {
        this.fabricante = fabricante;
    }

    /*public Fornecedor getFornecedor()
    {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor)
    {
        this.fornecedor = fornecedor;
    }*/
    public Tipo getTipo()
    {
        return tipo;
    }

    public void setTipo(Tipo tipo)
    {
        this.tipo = tipo;
    }

    public String getObs()
    {
        return obs;
    }

    public void setObs(String obs)
    {
        this.obs = obs;
    }

    public String getLote()
    {
        return lote;
    }

    public void setLote(String lote)
    {
        this.lote = lote;
    }

    public Produto(String nome)
    {
        this.nome = nome;
    }

    public Integer getQuantidade()
    {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade)
    {
        this.quantidade = quantidade;
    }

    public Integer getCodigo()
    {
        return codigo;
    }

    public void setCodigo(Integer codigo)
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

    public Double getPreco()
    {
        return preco;
    }

    public void setPreco(Double preco)
    {
        this.preco = preco;
    }

    public boolean Add()
    {
        String sql = "insert into produto(tip_cod, fab_cod, prod_quantidade, prod_lote, prod_preco, prod_obs, prod_nome)"//prod_cod, for_cod, tip_cod, fab_cod, prod_quantidade, prod_lote, prod_preco, prod_obs, prod_nome
                + "VALUES($2, $3, $4, '$5', $6, '$7', '$8')";
        sql = sql.replace("$2", tipo.getCodigo().toString())
                .replace("$3", fabricante.getCodigo().toString())
                .replace("$4", quantidade.toString())
                .replace("$5", lote)
                .replace("$6", preco.toString())
                .replace("$7", obs)
                .replace("$8", nome);
        System.out.println(sql);
        return Banco.con.manipular(sql);
    }

    public boolean Altera()
    {
        String sql = "UPDATE produto SET tip_cod = '$2', fab_cod = '$3', "
                + "prod_quantidade = '$4', prod_lote = '$5', prod_preco = '$6', prod_obs = '$7', "
                + "prod_nome = '$8' WHERE prod_cod = " + codigo;
        sql = sql.replace("$2", tipo.getCodigo().toString()).replace("$3", fabricante.getCodigo().toString()).replace("$4",
                quantidade.toString()).replace("$5", lote).replace("$6", preco.toString()).replace("$7", obs).replace("$8", nome);
        //System.out.println(sql);
        return Banco.con.manipular(sql);
    }

    public boolean Remove()
    {
        String sql = "delete from produto where prod_cod = " + codigo;
        return Banco.con.manipular(sql);
    }

    public ArrayList<Object> Get(String filtro)
    {
        String sql = "select *from produto";
        ResultSet rs = null;
        ArrayList<Object> a = new ArrayList<>();
        if (!filtro.isEmpty())
        {
            sql += " Where " + filtro;
        }
        rs = Banco.con.consultar(sql);
        try
        {
            while (rs.next())
            {
                a.add(new Produto(rs.getInt("prod_cod"),
                        rs.getString("prod_nome"),
                        rs.getDouble("prod_preco"),
                        new Fabricante(rs.getInt("fab_cod")),
                        new Tipo(rs.getInt("tip_cod")),
                        rs.getInt("prod_quantidade"),
                        rs.getString("prod_obs"),
                        rs.getString("prod_lote")));
            }
        } catch (SQLException ex)
        {
            a = null;
        }
        return a;
    }

    public ArrayList<Object> GetAvancado(String f1, String f2)
    {
        String sql = "select *from produto";
        ResultSet rs = null;
        ArrayList<Object> a = new ArrayList<>();
        if (!f1.isEmpty())
        {
            if (f2.equals("Nome"))
            {
                sql += " Where prod_nome like '%" + f1 + "%'";
            } else if (f2.equals("Tipo"))
            {
                sql += " INNER JOIN tipo ON produto.tip_cod = tipo.tip_cod WHERE tipo.tip_nome = '" + f1 + "'";
            }
        }
        rs = Banco.con.consultar(sql);
        try
        {
            while (rs.next())
            {
                a.add(new Produto(rs.getInt("prod_cod"),
                        rs.getString("prod_nome"),
                        rs.getDouble("prod_preco"),
                        new Fabricante(rs.getInt("fab_cod")),
                        new Tipo(rs.getInt("tip_cod")),
                        rs.getInt("prod_quantidade"),
                        rs.getString("prod_obs"),
                        rs.getString("prod_lote")));
            }
        } catch (SQLException ex)
        {
            a = null;
        }
        return a;
    }
}
