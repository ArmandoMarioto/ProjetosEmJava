
package Entidades.NovaEntidades;

import Abstract.Template;
import Banco.Banco;
import Entidades.Cliente;
import Entidades.Funcionario;
import Interface.Entidade;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.beans.property.BooleanProperty;

public class Orcamento extends Template
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

    @Override
    public Boolean insert()
    {
        boolean flag = false;
        String sqlb1;
        String sql = "insert into orcamento(cli_cod, dt_orcamento, dt_validade, valor_tot, obsformapg, usr_id) VALUES('$1', '$2', '$3', '$4', '$5', '$6')";
        sql = sql.replace("$1", Integer.toString(cliente.getCodigo())).replace("$2", dtorcamento.toString()).replace("$3", dtvalidade.toString())
                .replace("$4", Double.toString(total)).replace("$5", obsformapagamento).replace("$6", Integer.toString(usuarioid.getCodigo()));

        flag = flag && Banco.getConexao().manipular(sql);
        String cOrcamento = Integer.toString(Banco.getConexao().getMaxPK("orcamento", "orc_cod"));

        for (int i = 0; i < produtosOrcamento.size(); i++)
        {
            sqlb1 = "insert into itensorpro(orc_cod, prod_cod, valor, qtd) VALUES ('$1', '$2', $3, $4)";
            sqlb1 = sqlb1.replace("$1", cOrcamento)
                    .replace("$2", Integer.toString(produtosOrcamento.get(i).getProduto().getCodigo()))
                    .replace("$3", Double.toString(produtosOrcamento.get(i).getValor()))
                    .replace("$4", Double.toString(produtosOrcamento.get(i).getQtd()));
            System.out.println(sqlb1);
            flag = flag && Banco.getConexao().manipular(sqlb1);
        }

        for (int i = 0; i < servicosOrcamento.size(); i++)
        {
            sqlb1 = "insert into itensorser(orc_cod, serv_cod, valor, qtd) VALUES ('$1', '$2', $3, $4)";
            sqlb1 = sqlb1.replace("$1", cOrcamento)
                    .replace("$2", Integer.toString(servicosOrcamento.get(i).getServico().getCodigo_servico()))
                    .replace("$3", Double.toString(servicosOrcamento.get(i).getValor()))
                    .replace("$4", Double.toString(servicosOrcamento.get(i).getQtd()));
            System.out.println(sqlb1);
            flag = flag && Banco.getConexao().manipular(sqlb1);
        }

        return flag;
    }

    public Boolean delete()
    {
        boolean flag = false;
        String sql1 = "delete from itensorpro where orc_cod = " + codigo;
        String sql2 = "delete from itensorser where orc_cod = " + codigo;
        String sql3 = "delete from orcamento where orc_cod = " + codigo;
        try
        {
            Banco.getConexao().getConnection().setAutoCommit(false);
            flag = Banco.getConexao().manipular(sql1) && Banco.getConexao().manipular(sql2) && Banco.getConexao().manipular(sql3);
            if (flag)
            {
                Banco.getConexao().getConnection().commit();
            } else
            {
                Banco.getConexao().getConnection().rollback();
            }
            Banco.getConexao().getConnection().setAutoCommit(true);
        } catch (SQLException ex)
        {
            flag = false;
            try
            {
                Banco.getConexao().getConnection().rollback();
                Banco.getConexao().getConnection().setAutoCommit(true);
            } catch (SQLException ex1)
            {
                System.out.println("Falha Critica Rollback Incapacitado");
            }
        }
        return flag;
    }

    private boolean altera()
    {
        String sql = "insert into orcamento(cli_cod, dt_orcamento, dt_validade, valor_tot, obsformapg, usr_id) VALUES('$1', '$2', '$3', '$4', '$5', '$6')";
        sql = sql.replace("$1", Integer.toString(cliente.getCodigo())).replace("$2", dtorcamento.toString()).replace("$3", dtvalidade.toString())
                .replace("$4", Double.toString(total)).replace("$5", obsformapagamento).replace("$6", Integer.toString(usuarioid.getCodigo()));
        return Banco.getConexao().manipular(sql);
    }

    public ArrayList<Object> get(String filtro)
    {
        ArrayList<Object> a = new ArrayList<>();

        String sql = "select * from orcamento";
        ResultSet rs = null;
        rs = Banco.getConexao().consultar(sql);

        try
        {
            while (rs.next())
            {
                a.add(new Orcamento(rs.getInt("orc_cod"), rs.getDate("dt_orcamento"), rs.getDate("dt_validade"), rs.getDouble("valor_tot"), rs.getString("obsformapg"), rs.getInt("usr_id"), rs.getInt("cli_cod")));
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

        String sql = "select * from orcamento";
        if (tipo != null)
        {
            if (tipo.equals("RG") && !filtro.isEmpty())
            {
                sql += " INNER JOIN cliente ON orcamento.cli_cod = cliente.cli_cod AND cliente.cli_rg = '" + filtro + "'";
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
        rs = Banco.getConexao().consultar(sql);

        try
        {
            while (rs.next())
            {
                a.add(new Orcamento(rs.getInt("orc_cod"), rs.getDate("dt_orcamento"), rs.getDate("dt_validade"), rs.getDouble("valor_tot"), rs.getString("obsformapg"), rs.getInt("usr_id"), rs.getInt("cli_cod")));
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
            Banco.getConexao().getConnection().setAutoCommit(false);
            String sql = "update orcamento set cli_cod = '$1', dt_orcamento = '$2', dt_validade = '$3', valor_tot = '$4', obsformapg = '$5', usr_id = '$6' WHERE orc_cod = " + codigo;
            sql = sql.replace("$1", Integer.toString(cliente.getCodigo())).replace("$2", dtorcamento.toString()).replace("$3", dtvalidade.toString())
                    .replace("$4", Double.toString(total)).replace("$5", obsformapagamento).replace("$6", Integer.toString(usuarioid.getCodigo()));

            flag = flag && Banco.getConexao().manipular(sql);
            //String cOrcamento = Integer.toString(Banco.con.getMaxPK("orcamento", "orc_cod"));

            //System.out.println(sql);
            for (int i = 0; i < produtosOrcamento.size(); i++)
            {
                if (produtosOrcamento.get(i).getCodigo_orcamento() != null)
                {
                    sqlb1 = "update itensorpro set orc_cod = '$1', prod_cod = '$2', valor = $3, qtd = $4 WHERE orc_cod = " + codigo + " and prod_cod = " + Integer.toString(produtosOrcamento.get(i).getProduto().getCodigo());
                    sqlb1 = sqlb1.replace("$1", Integer.toString(codigo))
                            .replace("$2", Integer.toString(produtosOrcamento.get(i).getProduto().getCodigo()))
                            .replace("$3", Double.toString(produtosOrcamento.get(i).getValor()))
                            .replace("$4", Double.toString(produtosOrcamento.get(i).getQtd()));
                } else
                {
                    sqlb1 = "insert into itensorpro(orc_cod, prod_cod, valor, qtd) VALUES ('$1', '$2', $3, $4)";
                    sqlb1 = sqlb1.replace("$1", codigo.toString())
                            .replace("$2", Integer.toString(produtosOrcamento.get(i).getProduto().getCodigo()))
                            .replace("$3", Double.toString(produtosOrcamento.get(i).getValor()))
                            .replace("$4", Double.toString(produtosOrcamento.get(i).getQtd()));
                }

                flag = flag && Banco.getConexao().manipular(sqlb1);
            }

            for (int i = 0; i < servicosOrcamento.size(); i++)
            {
                if (servicosOrcamento.get(i).getCodigo_orcamento() != null)
                {
                    sqlb1 = "update itensorser set orc_cod = '$1', serv_cod = '$2', valor = $3, qtd = $4 WHERE orc_cod = " + codigo + " and serv_cod = " + Integer.toString(servicosOrcamento.get(i).getServico().getCodigo_servico());
                    sqlb1 = sqlb1.replace("$1", Integer.toString(codigo))
                            .replace("$2", Integer.toString(servicosOrcamento.get(i).getServico().getCodigo_servico()))
                            .replace("$3", Double.toString(servicosOrcamento.get(i).getValor()))
                            .replace("$4", Double.toString(servicosOrcamento.get(i).getQtd()));
                } else
                {
                    sqlb1 = "insert into itensorser(orc_cod, serv_cod, valor, qtd) VALUES ('$1', '$2', $3, $4)";
                    sqlb1 = sqlb1.replace("$1", codigo.toString())
                            .replace("$2", Integer.toString(servicosOrcamento.get(i).getServico().getCodigo_servico()))
                            .replace("$3", Double.toString(servicosOrcamento.get(i).getValor()))
                            .replace("$4", Double.toString(servicosOrcamento.get(i).getQtd()));
                }

                flag = flag && Banco.getConexao().manipular(sqlb1);
            }
            if (flag)
            {
                Banco.getConexao().getConnection().commit();
            } else
            {
                Banco.getConexao().getConnection().rollback();
            }
        } catch (Exception ex)
        {
            try
            {
                Banco.getConexao().getConnection().rollback();
            } catch (SQLException ex1)
            {
                System.out.println("erro orcamento");
            }
        }
        try
        {
            Banco.getConexao().getConnection().setAutoCommit(true);
        } catch (SQLException ex)
        {
            System.out.println("erro orcamento");
        }
        return flag;
    }

    public ArrayList<Object> getAvancado(String cliente_cpf, String funcionario_nome, BooleanProperty considerardatas, Date inicio, Date fim)
    {
        ArrayList<Object> a = new ArrayList<>();

        String sql = "select * from orcamento";
        ResultSet rs = null;
        if (!cliente_cpf.isEmpty())
        {
            sql += " inner join cliente";
        }
        if (!funcionario_nome.isEmpty())
        {
            sql += " inner join funcionario";
        }

        if (!cliente_cpf.isEmpty() || !funcionario_nome.isEmpty() || considerardatas.getValue())
        {
            sql += "WHERE ";
            if (!cliente_cpf.isEmpty())
            {
                sql += " cliente.cli_cpf = '" + cliente_cpf + "'" + ((!funcionario_nome.isEmpty()) ? "AND" : "");
            }
            if (!funcionario_nome.isEmpty())
            {
                sql += "  funcionario.nome like '%" + funcionario_nome + "%'" + ((considerardatas.getValue()) ? "AND" : "");
            }

            if (considerardatas.getValue())
            {
                sql += " orcamento.dt_orcamento BETWEEN '" + inicio.toString() + "' AND '" + fim.toString() + "'";
            }
        }
        rs = Banco.getConexao().consultar(sql);

        try
        {
            while (rs.next())
            {
                a.add(new Orcamento(rs.getInt("orc_cod"), rs.getDate("dt_orcamento"), rs.getDate("dt_validade"), rs.getDouble("valor_tot"), rs.getString("obsformapg"), rs.getInt("usr_id"), rs.getInt("cli_cod")));
            }
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return a;
    }
}
