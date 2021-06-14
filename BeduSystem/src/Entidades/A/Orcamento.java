package Entidades.A;

import Banco.Banco;
import Entidades.Cliente;
import Entidades.Funcionario;
import Entidades.Funcionário;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.beans.property.BooleanProperty;

public class Orcamento 
{

    private Integer codigo;
    private Date dtorcamento;
    private Date dtvalidade;
    private Double total;
    private String obsformapagamento;
    private Funcionario usuarioid;
    private Cliente cliente;
    private ArrayList<ItemOrcamentoProduto> produtosOrcamento;
    private ArrayList<ItemOrcamentoServico> servicosOrcamento;

    public Orcamento()
    {

    }

    public Orcamento(Integer codigo)
    {
        this.codigo = codigo;
    }

    public Orcamento(Integer codigo, Date dtorcamento, Date dtvalidade, Double total, String obsformapagamento, Funcionario usuarioid, Cliente cliente, ArrayList<ItemOrcamentoProduto> produtosOrcamento, ArrayList<ItemOrcamentoServico> servicosOrcamento)
    {
        this.codigo = codigo;
        this.dtorcamento = dtorcamento;
        this.dtvalidade = dtvalidade;
        this.total = total;
        this.obsformapagamento = obsformapagamento;
        this.usuarioid = usuarioid;
        this.cliente = cliente;
        this.produtosOrcamento = produtosOrcamento;
        this.servicosOrcamento = servicosOrcamento;
    }

    public Orcamento(Date dtorcamento, Date dtvalidade, Double total, String obsformapagamento, Funcionario usuarioid, Cliente cliente, ArrayList<ItemOrcamentoProduto> produtosOrcamento, ArrayList<ItemOrcamentoServico> servicosOrcamento)
    {
        this.dtorcamento = dtorcamento;
        this.dtvalidade = dtvalidade;
        this.total = total;
        this.obsformapagamento = obsformapagamento;
        this.usuarioid = usuarioid;
        this.cliente = cliente;
        this.produtosOrcamento = produtosOrcamento;
        this.servicosOrcamento = servicosOrcamento;
    }

    public Orcamento(int orc_cod, Date dt_orcamento, Date dt_validade, Double valor_tot, String obsformapag, int usr_id, int cli_cod)
    {
        this.codigo = orc_cod;
        this.dtorcamento = dt_orcamento;
        this.dtvalidade = dt_validade;
        this.total = valor_tot;
        this.obsformapagamento = obsformapag;
        this.usuarioid = new Funcionario(usr_id);
        this.cliente = new Cliente(cli_cod);

        this.produtosOrcamento = new ItemOrcamentoProduto(orc_cod).get();
        this.servicosOrcamento = new ItemOrcamentoServico(orc_cod).get();
    }

    public Integer getCodigo()
    {
        return codigo;
    }

    public void setCodigo(Integer codigo)
    {
        this.codigo = codigo;
    }

    public Date getDtorcamento()
    {
        return dtorcamento;
    }

    public void setDtorcamento(Date dtorcamento)
    {
        this.dtorcamento = dtorcamento;
    }

    public Date getDtvalidade()
    {
        return dtvalidade;
    }

    public void setDtvalidade(Date dtvalidade)
    {
        this.dtvalidade = dtvalidade;
    }

    public Double getTotal()
    {
        return total;
    }

    public void setTotal(Double total)
    {
        this.total = total;
    }

    public String getObsformapagamento()
    {
        return obsformapagamento;
    }

    public void setObsformapagamento(String obsformapagamento)
    {
        this.obsformapagamento = obsformapagamento;
    }

    public Funcionario getUsuarioid()
    {
        return usuarioid;
    }

    public void setUsuarioid(Funcionario usuarioid)
    {
        this.usuarioid = usuarioid;
    }

    public Cliente getCliente()
    {
        return cliente;
    }

    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }

    public ArrayList<ItemOrcamentoProduto> getProdutosOrcamento()
    {
        return produtosOrcamento;
    }

    public void setProdutosOrcamento(ArrayList<ItemOrcamentoProduto> produtosOrcamento)
    {
        this.produtosOrcamento = produtosOrcamento;
    }

    public ArrayList<ItemOrcamentoServico> getServicosOrcamento()
    {
        return servicosOrcamento;
    }

    public void setServicosOrcamento(ArrayList<ItemOrcamentoServico> servicosOrcamento)
    {
        this.servicosOrcamento = servicosOrcamento;
    }

    public Boolean insert()
    {
        boolean flag = false;
        String sqlb1;
        String sql = "insert into Orcamento(cli_codigo, dt_orcamento, dt_validade, valor_tot, orc_descricao, func_codigo) VALUES('$1', '$2', '$3', '$4', '$5', '$6')";
        sql = sql.replace("$1", Integer.toString(cliente.getCodigo())).replace("$2", dtorcamento.toString()).replace("$3", dtvalidade.toString())
                .replace("$4", Double.toString(total)).replace("$5", obsformapagamento).replace("$6", Integer.toString(usuarioid.getCodigo()));

        flag =  Banco.getCon().manipular(sql);
        String cOrcamento = Integer.toString(Banco.getCon().getMaxPK("Orcamento", "orc_codigo"));

        for (int i = 0; i < produtosOrcamento.size(); i++)
        {
            sqlb1 = "insert into Produtos_Orcamento(orc_codigo, prod_codigo, prod_orc_preco, prod_orc_quantidade) VALUES ('$1', '$2', $3, $4)";
            sqlb1 = sqlb1.replace("$1", cOrcamento)
                    .replace("$2", Integer.toString(produtosOrcamento.get(i).getProduto().getCodigo()))
                    .replace("$3", Double.toString(produtosOrcamento.get(i).getValor()))
                    .replace("$4", Double.toString(produtosOrcamento.get(i).getQtd()));
            System.out.println(sqlb1);
            flag = flag && Banco.getCon().manipular(sqlb1);
        }

        for (int i = 0; i < servicosOrcamento.size(); i++)
        {
            sqlb1 = "insert into Servicos_Orcamento(orc_codigo, serv_codigo, serv_cod_preco, serv_cod_quantidade) VALUES ('$1', '$2', $3, $4)";
            sqlb1 = sqlb1.replace("$1", cOrcamento)
                    .replace("$2", Integer.toString(servicosOrcamento.get(i).getServico().getCodigo_servico()))
                    .replace("$3", Double.toString(servicosOrcamento.get(i).getValor()))
                    .replace("$4", Double.toString(servicosOrcamento.get(i).getQtd()));
            System.out.println(sqlb1);
            flag = flag && Banco.getCon().manipular(sqlb1);
        }

        return flag;
    }

    public Boolean delete()
    {
        boolean flag = false;
        String sql1 = "delete from Produtos_Orcamento where orc_codigo = " + codigo;
        String sql2 = "delete from Servicos_Orcamento where orc_codigo = " + codigo;
        String sql3 = "delete from Orcamento where orc_codigo = " + codigo;
        try
        {
            Banco.getCon().getConnection().setAutoCommit(false);
            flag = Banco.getCon().manipular(sql1) && Banco.getCon().manipular(sql2) && Banco.getCon().manipular(sql3);
            if (flag)
            {
                Banco.getCon().getConnection().commit();
            } else
            {
                Banco.getCon().getConnection().rollback();
            }
            Banco.getCon().getConnection().setAutoCommit(true);
        } catch (SQLException ex)
        {
            flag = false;
            try
            {
                Banco.getCon().getConnection().rollback();
                Banco.getCon().getConnection().setAutoCommit(true);
            } catch (SQLException ex1)
            {
                System.out.println("Falha Critica Rollback Incapacitado");
            }
        }
        return flag;
    }

    private boolean altera()
    {
        String sql = "insert into Orcamento(cli_codigo, dt_orcamento, dt_validade, valor_tot, orc_descricao, func_codigo) VALUES('$1', '$2', '$3', '$4', '$5', '$6')";
        sql = sql.replace("$1", Integer.toString(cliente.getCodigo())).replace("$2", dtorcamento.toString()).replace("$3", dtvalidade.toString())
                .replace("$4", Double.toString(total)).replace("$5", obsformapagamento).replace("$6", Integer.toString(usuarioid.getCodigo()));
        return Banco.getCon().manipular(sql);
    }

    public ArrayList<Object> get(String filtro)
    {
        ArrayList<Object> a = new ArrayList<>();

        String sql = "select * from Orcamento";
        ResultSet rs = null;
        rs = Banco.getCon().consultar(sql);

        try
        {
            while (rs.next())
            {
                a.add(new Orcamento(rs.getInt("orc_codigo"), rs.getDate("dt_orcamento"), rs.getDate("dt_validade"), rs.getDouble("valor_tot"), rs.getString("orc_descricao"), rs.getInt("func_codigo"), rs.getInt("cli_codigo")));
            }
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return a;
    }

    public ArrayList<Object> getAvancado(String filtro, String tipo)
    {
        ArrayList<Object> a = new ArrayList<>();

        String sql = "select * from Orcamento";
        if (tipo != null)
        {
            if (tipo.equals("RG") && !filtro.isEmpty())
            {
                sql += " INNER JOIN Cliente ON Orcamento.cli_codigo = Cliente.cli_cod AND Cliente.cli_rg = '" + filtro + "'";
            } else if (tipo.equals("Ano") && !filtro.isEmpty())
            {
                Integer t = null;
                try
                {
                    t = Integer.parseInt(filtro);
                } catch (Exception ex)
                {
                    t = null;
                }
                if (t != null)
                {
                    sql += " where EXTRACT(YEAR FROM dt_orcamento) = " + filtro.toString();
                }
            }
        }
        ResultSet rs = null;
        rs = Banco.getCon().consultar(sql);

        try
        {
            while (rs.next())
            {
                a.add(new Orcamento(rs.getInt("orc_codigo"), rs.getDate("dt_orcamento"), rs.getDate("dt_validade"), rs.getDouble("valor_tot"), rs.getString("orc_descricao"), rs.getInt("func_codigo"), rs.getInt("cli_codigo")));
            }
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return a;
    }

    public Boolean update()
    {
        boolean flag = true;
        String sqlb1;
        try
        {
            Banco.getCon().getConnection().setAutoCommit(false);
            String sql = "update Orcamento set cli_codigo = '$1', dt_orcamento = '$2', dt_validade = '$3', valor_tot = '$4', orc_descricao = '$5', func_codigo = '$6' WHERE orc_codigo = " + codigo;
            sql = sql.replace("$1", Integer.toString(cliente.getCodigo())).replace("$2", dtorcamento.toString()).replace("$3", dtvalidade.toString())
                    .replace("$4", Double.toString(total)).replace("$5", obsformapagamento).replace("$6", Integer.toString(usuarioid.getCodigo()));

            flag = flag && Banco.getCon().manipular(sql);
            for (int i = 0; i < produtosOrcamento.size(); i++)
            {
                if (produtosOrcamento.get(i).getCodigo_orcamento() != null)
                {
                    sqlb1 = "update Produtos_Orcamento set orc_codigo = '$1', prod_codigo = '$2', prod_orc_preco = $3, prod_orc_quantidade = $4 WHERE orc_codigo = " + codigo + " and prod_codigo = " + Integer.toString(produtosOrcamento.get(i).getProduto().getCodigo());
                    sqlb1 = sqlb1.replace("$1", Integer.toString(codigo))
                            .replace("$2", Integer.toString(produtosOrcamento.get(i).getProduto().getCodigo()))
                            .replace("$3", Double.toString(produtosOrcamento.get(i).getValor()))
                            .replace("$4", Double.toString(produtosOrcamento.get(i).getQtd()));
                } else
                {
                    sqlb1 = "insert into Produtos_Orcamento(orc_codigo, prod_codigo, prod_orc_preco, prod_orc_quantidade) VALUES ('$1', '$2', $3, $4)";
                    sqlb1 = sqlb1.replace("$1", codigo.toString())
                            .replace("$2", Integer.toString(produtosOrcamento.get(i).getProduto().getCodigo()))
                            .replace("$3", Double.toString(produtosOrcamento.get(i).getValor()))
                            .replace("$4", Double.toString(produtosOrcamento.get(i).getQtd()));
                }

                flag = flag && Banco.getCon().manipular(sqlb1);
            }

            for (int i = 0; i < servicosOrcamento.size(); i++)
            {
                if (servicosOrcamento.get(i).getCodigo_orcamento() != null)
                {
                    sqlb1 = "update Servicos_Orcamento set orc_codigo = '$1', serv_codigo = '$2', serv_cod_preco = $3, serv_cod_quantidade = $4 WHERE orc_codigo = " + codigo + " and serv_codigo = " + Integer.toString(servicosOrcamento.get(i).getServico().getCodigo_servico());
                    sqlb1 = sqlb1.replace("$1", Integer.toString(codigo))
                            .replace("$2", Integer.toString(servicosOrcamento.get(i).getServico().getCodigo_servico()))
                            .replace("$3", Double.toString(servicosOrcamento.get(i).getValor()))
                            .replace("$4", Double.toString(servicosOrcamento.get(i).getQtd()));
                } else
                {
                    sqlb1 = "insert into Servicos_Orcamento(orc_codigo, serv_codigo, serv_cod_preco, serv_cod_quantidade) VALUES ('$1', '$2', $3, $4)";
                    sqlb1 = sqlb1.replace("$1", codigo.toString())
                            .replace("$2", Integer.toString(servicosOrcamento.get(i).getServico().getCodigo_servico()))
                            .replace("$3", Double.toString(servicosOrcamento.get(i).getValor()))
                            .replace("$4", Double.toString(servicosOrcamento.get(i).getQtd()));
                }

                flag = flag && Banco.getCon().manipular(sqlb1);
            }
            if (flag)
            {
                Banco.getCon().getConnection().commit();
            } else
            {
                Banco.getCon().getConnection().rollback();
            }
        } catch (Exception ex)
        {
            try
            {
                Banco.getCon().getConnection().rollback();
            } catch (SQLException ex1)
            {
                System.out.println("erro orcamento");
            }
        }
        try
        {
            Banco.getCon().getConnection().setAutoCommit(true);
        } catch (SQLException ex)
        {
            System.out.println("erro orcamento");
        }
        return flag;
    }

    public ArrayList<Object> getAvancado(String cliente_cpf, String funcionario_nome, BooleanProperty considerardatas, Date inicio, Date fim)
    {
        ArrayList<Object> a = new ArrayList<>();

        String sql = "select * from Orcamento";
        ResultSet rs = null;
        if (!cliente_cpf.isEmpty())
        {
            sql += " inner join Cliente";
        }
        if (!funcionario_nome.isEmpty())
        {
            sql += " inner join Funcionario";
        }

        if (!cliente_cpf.isEmpty() || !funcionario_nome.isEmpty() || considerardatas.getValue())
        {
            sql += "WHERE ";
            if (!cliente_cpf.isEmpty())
            {
                sql += " Cliente.cli_cpf = '" + cliente_cpf + "'" + ((!funcionario_nome.isEmpty()) ? "AND" : "");
            }
            if (!funcionario_nome.isEmpty())
            {
                sql += "  Funcionario.nome like '%" + funcionario_nome + "%'" + ((considerardatas.getValue()) ? "AND" : "");
            }

            if (considerardatas.getValue())
            {
                sql += " Orcamento.dt_orcamento BETWEEN '" + inicio.toString() + "' AND '" + fim.toString() + "'";
            }
        }
        rs = Banco.getCon().consultar(sql);

        try
        {
            while (rs.next())
            {
                a.add(new Orcamento(rs.getInt("orc_codigo"), rs.getDate("dt_orcamento"), rs.getDate("dt_validade"), rs.getDouble("valor_tot"), rs.getString("orc_descricao"), rs.getInt("func_codigo"), rs.getInt("cli_codigo")));
            }
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return a;
    }
}
