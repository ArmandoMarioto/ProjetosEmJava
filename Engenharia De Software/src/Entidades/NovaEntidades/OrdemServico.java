
package Entidades.NovaEntidades;

import Abstract.Template;
import Entidades.Cliente;
import Entidades.Funcionario;
import java.sql.Date;
import Banco.Banco;
import Entidades.Pagamento;
import Entidades.Veiculo;
import Interface.Entidade;
import Interface.Strategy;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdemServico extends Template
{

    private Integer codigo;
    private Orcamento orcamento;
    private Cliente cliente;
    private Funcionario funcionario;
    private Date data_os;
    private Date data_fechamento;
    private Integer qtdParcelas;
    private Double valor_total;
    private Veiculo veiculo;
    private ArrayList<Pagamento> parcelas;
    private ArrayList<ItemOrdemProduto> produtosOS;
    private ArrayList<ItemOrdemServico> servicosOS;
    private Strategy forma;
    public OrdemServico()
    {
    }

    public OrdemServico(Integer codigo, Orcamento orcamento, Cliente cliente, Funcionario funcionario, Date data_os, Date data_fechamento, Integer qtdParcelas, Double valor_total)
    {
        this.codigo = codigo;
        this.orcamento = orcamento;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.data_os = data_os;
        this.data_fechamento = data_fechamento;
        this.qtdParcelas = qtdParcelas;
        this.valor_total = valor_total;
    }

    public OrdemServico(Orcamento orcamento, Cliente cliente, Funcionario funcionario, Date data_os, Date data_fechamento, Integer qtdParcelas, Double valor_total, Strategy forma)
    {
        this.orcamento = orcamento;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.data_os = data_os;
        this.data_fechamento = data_fechamento;
        this.qtdParcelas = qtdParcelas;
        this.valor_total = valor_total;
        this.forma = forma;
    }

    public Veiculo getVeiculo()
    {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo)
    {
        this.veiculo = veiculo;
    }

    public ArrayList<ItemOrdemProduto> getProdutosOS()
    {
        return produtosOS;
    }

    public void setProdutosOS(ArrayList<ItemOrdemProduto> produtosOS)
    {
        this.produtosOS = produtosOS;
    }

    public ArrayList<ItemOrdemServico> getServicosOS()
    {
        return servicosOS;
    }

    public void setServicosOS(ArrayList<ItemOrdemServico> servicosOS)
    {
        this.servicosOS = servicosOS;
    }

    public Integer getCodigo()
    {
        return codigo;
    }

    public void setCodigo(Integer codigo)
    {
        this.codigo = codigo;
    }

    public Orcamento getOrcamento()
    {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento)
    {
        this.orcamento = orcamento;
    }

    public Cliente getCliente()
    {
        return cliente;
    }

    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario()
    {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario)
    {
        this.funcionario = funcionario;
    }

    public Date getData_os()
    {
        return data_os;
    }

    public void setData_os(Date data_os)
    {
        this.data_os = data_os;
    }

    public Date getData_fechamento()
    {
        return data_fechamento;
    }

    public void setData_fechamento(Date data_fechamento)
    {
        this.data_fechamento = data_fechamento;
    }

    public Integer getQtdParcelas()
    {
        return qtdParcelas;
    }

    public void setQtdParcelas(Integer qtdParcelas)
    {
        this.qtdParcelas = qtdParcelas;
    }

    public Double getValor_total()
    {
        return valor_total;
    }

    public void setValor_total(Double valor_total)
    {
        this.valor_total = valor_total;
    }

    @Override
    public Boolean insert()
    {
        boolean flag;
            String sql = "insert into ordemservico(orc_cod, cli_cod, dt_os, dt_fechamento, qtd_parcelas, valor_tot, usr_id) VALUES($1, $2, '$3', '$4', $5, $6, $7)";
            sql = sql.replace("$1", orcamento.getCodigo().toString())
                    .replace("$2", cliente.getCodigo().toString())
                    .replace("$3", data_os.toString())
                    .replace("$4", data_fechamento.toString())
                    .replace("$5", qtdParcelas.toString())
                    .replace("$6", valor_total.toString())
                    .replace("$7", funcionario.getCodigo().toString());
            flag = Banco.getConexao().manipular(sql);
            codigo = Banco.getConexao().getMaxPK("ordemservico", "os_cod");
            String sqlb1;

            for (int i = 0; i < produtosOS.size(); i++)
            {
                sqlb1 = "insert into itensordempro(os_cod, prod_cod, valor, qtd) VALUES ('$1', '$2', $3, $4)";
                sqlb1 = sqlb1.replace("$1", codigo.toString())
                        .replace("$2", Integer.toString(produtosOS.get(i).getProduto().getCodigo()))
                        .replace("$3", Double.toString(produtosOS.get(i).getValor()))
                        .replace("$4", Double.toString(produtosOS.get(i).getQtd()));
                System.out.println(sqlb1);
                flag = flag && Banco.getConexao().manipular(sqlb1);
            }

            for (int i = 0; i < servicosOS.size(); i++)
            {
                sqlb1 = "insert into itensordemser(os_cod, serv_cod, valor, qtd) VALUES ('$1', '$2', $3, $4)";
                sqlb1 = sqlb1.replace("$1", codigo.toString())
                        .replace("$2", Integer.toString(servicosOS.get(i).getServico().getCodigo_servico()))
                        .replace("$3", Double.toString(servicosOS.get(i).getValor()))
                        .replace("$4", Double.toString(servicosOS.get(i).getQtd()));
                System.out.println(sqlb1);
                flag = flag && Banco.getConexao().manipular(sqlb1);
            }
            Integer codigoparc;
            for (Integer i = 0; i < qtdParcelas; i++) 
            {
                parcelas.add(new Pagamento(i, this, forma));
                parcelas.get(i).insert();
            }
        return flag;
    }

    public static ArrayList<OrdemServico> getAll(String filtro)
    {
        ArrayList<OrdemServico> a = new ArrayList<>();
        String sql = "select * from ordemservico";
        ResultSet rs = null;
        rs = Banco.getConexao().consultar(sql);
        try
        {
            while (rs.next())
            {
                a.add(new OrdemServico(rs.getInt("os_cod"),
                        new Orcamento(rs.getInt("orc_cod")),
                        new Cliente(rs.getInt("cli_cod")),
                        new Funcionario(rs.getInt("usr_id")),
                        rs.getDate("dt_os"),
                        rs.getDate("dt_fechamento"),
                        rs.getInt("qtd_parcelas"), rs.getDouble("valor_tot")));
            }
        } catch (SQLException ex)
        {
            a = null;
        }
        return a;
    }

}
