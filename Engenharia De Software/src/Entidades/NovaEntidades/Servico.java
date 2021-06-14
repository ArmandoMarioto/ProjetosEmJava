
package Entidades.NovaEntidades;

import Entidades.Produto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Servico
{

    private Integer codigo_servico;
    private String nome;
    private String desc;

    public Servico()
    {
    }

    public Servico(Integer codigo_servico, String nome, String desc)
    {
        this.codigo_servico = codigo_servico;
        this.nome = nome;
        this.desc = desc;
    }

    public Servico(String nome, String desc)
    {
        this.nome = nome;
        this.desc = desc;
    }

    public Servico(Integer codigo_servico) throws SQLException
    {
        this.codigo_servico = codigo_servico;
        preenceDados();
    }

    public Servico(Integer codigo_servico, String nome, String desc, ArrayList<Produto> peças)
    {
        this.codigo_servico = codigo_servico;
        this.nome = nome;
        this.desc = desc;
    }

    private void preenceDados() throws SQLException
    {
        String sql = "select * from servico where serv_cod = " + codigo_servico;
        ResultSet rs = null;
        rs = Banco.Banco.getConexao().consultar(sql);
        while (rs.next())
        {
            nome = rs.getString("ser_nome");
            desc = rs.getString("ser_desc");
        }
    }

    public Integer getCodigo_servico()
    {
        return codigo_servico;
    }

    public void setCodigo_servico(Integer codigo_servico)
    {
        this.codigo_servico = codigo_servico;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public static ArrayList<Object> get(String f) throws SQLException
    {
        String sql = "select * from servico";
        if (!f.isEmpty())
        {
            sql += " where ser_nome like'%" + f + "%'";
        }
        ResultSet rs = Banco.Banco.getConexao().consultar(sql);
        ArrayList<Object> a = new ArrayList<>();
        while (rs.next())
        {
            Servico s = new Servico(rs.getInt("serv_cod"), rs.getString("ser_nome"), rs.getString("ser_desc"));
            a.add(s);
        }
        return a;
    }

    @Override
    public String toString()
    {
        return nome;
    }

}
