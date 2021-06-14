package Entidades;

import Banco.Banco;
import Interface.Entidade;
import Interface.Observer;
import Interface.Subject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Produto implements Entidade, Subject
{

    private Integer codigo;
    private String nome;
    private Double preco;
    private Fabricante fabricante;
    private Tipo tipo;
    private Integer quantidade;
    private String obs;
    ArrayList<Observer> observadores = new ArrayList<>();

    public Produto(Integer codigo, String nome, Double preco, Object fabricante, Object tipo, Integer quantidade, String obs)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.fabricante = (Fabricante) fabricante;
        this.tipo = (Tipo) tipo;
        this.quantidade = quantidade;
        this.obs = obs;
    }

    public Produto(String nome, Double preco, Object fabricante/*, Object fornecedor*/, Object tipo, Integer quantidade, String obs)
    {
        this.nome = nome;
        this.preco = preco;
        this.fabricante = (Fabricante) fabricante;
        //this.fornecedor = (Fornecedor) fornecedor;
        this.tipo = (Tipo) tipo;
        this.quantidade = quantidade;
        this.obs = obs;
    }

    public Produto()
    {

    }

    public Produto(Integer codigo, String nome, Double preco, Fabricante fabricante, Tipo tipo, Integer quantidade, String obs)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.fabricante = fabricante;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.obs = obs;
    }

    public Fabricante getFabricante()
    {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante)
    {
        this.fabricante = fabricante;
    }

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

    public ArrayList<Observer> getObservadores()
    {
        return observadores;
    }

    public void setObservadores(ArrayList<Observer> observadores)
    {
        this.observadores = observadores;
    }

    public void addObservadores(Object obs)
    {
        observadores.add((Observer) obs);
    }

    @Override
    public Boolean insert()
    {
        String sql = "insert into produto(tip_cod, fab_cod, prod_quantidade, prod_preco, prod_obs, prod_nome)"//prod_cod, for_cod, tip_cod, fab_cod, prod_quantidade, prod_lote, prod_preco, prod_obs, prod_nome
                + "VALUES($2, $3, $4, '$5', $6, '$7', '$8')";
        sql = sql.replace("$2", tipo.getCodigo().toString())
                .replace("$3", fabricante.getCodigo().toString())
                .replace("$4", quantidade.toString())
                .replace("$6", preco.toString())
                .replace("$7", obs)
                .replace("$8", nome);
        mynotify();
        return Banco.getConexao().manipular(sql);
    }

    @Override
    public Boolean update()
    {
        String sql = "UPDATE produto SET tip_cod = '$2', fab_cod = '$3', "
                + "prod_quantidade = '$4', prod_preco = '$6', prod_obs = '$7', "
                + "prod_nome = '$8' WHERE prod_cod = " + codigo;
        sql = sql.replace("$2", tipo.getCodigo().toString()).replace("$3", fabricante.getCodigo().toString()).replace("$4",
                quantidade.toString()).replace("$6", preco.toString()).replace("$7", obs).replace("$8", nome);
        //System.out.println(sql);
        mynotify();
        return Banco.getConexao().manipular(sql);
    }

    @Override
    public Boolean delete()
    {
        String sql = "delete from produto where prod_cod = " + codigo;
        return Banco.getConexao().manipular(sql);
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
        rs = Banco.getConexao().consultar(sql);
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
                        rs.getString("prod_obs")));
            }
        } catch (SQLException ex)
        {
            a = null;
        }
        return a;
    }

    public ArrayList<Produto> GetProd(String filtro)
    {
        String sql = "select *from produto";
        ResultSet rs = null;
        ArrayList<Produto> a = new ArrayList<>();
        if (!filtro.isEmpty())
        {
            sql += " Where " + filtro;
        }
        rs = Banco.getConexao().consultar(sql);
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
                        rs.getString("prod_obs")));
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
        //System.out.println(sql);
        rs = Banco.getConexao().consultar(sql);
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
                        rs.getString("prod_obs")));
            }
        } catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
            a = null;
        }
        return a;
    }

    @Override
    public String toString()
    {
        return nome;
    }

    @Override
    public ArrayList<Object> get(String filtro)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean mynotify() //toda vez que eu cadastrar um prouto novo ou alterar um produto, sera notificado aos observadores
    {
        boolean flag = true;
        int i, j;
        for (i = 0; i < observadores.size() && flag; i++)
        {
            if (observadores.get(i) instanceof Cliente)
            {
                flag = flag && ((Cliente) (observadores.get(i))).send(this);
            }
        }
        for (j = 0; j < i; j++)
        {
            observadores.remove(j);
        }
        return flag;
    }

    @Override
    public boolean adicionar()
    {
        return true;
    }

    @Override
    public boolean remover()
    {
        return true;
    }

}
