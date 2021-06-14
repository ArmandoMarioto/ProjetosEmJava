
package Entidades;

import Banco.Banco;
import Interface.Entidade;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Modelo implements Entidade
{

    private Integer codigo;
    private String nome;
    private Integer ano;

    public Modelo()
    {
    }

    public Modelo(Integer codigo, String nome, Integer ano)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.ano = ano;
    }

    public Modelo(Integer codigo)
    {
        this.codigo = codigo;
        Modelo aux = get(-1).get(0);
        this.nome = aux.getNome();
        this.ano = aux.getAno();
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

    public Integer getAno()
    {
        return ano;
    }

    public void setAno(Integer ano)
    {
        this.ano = ano;
    }

    public ArrayList<Modelo> get(Integer cod)//retorna todos
    {
        ArrayList<Modelo> a = new ArrayList<>();
        String sql = "select * from modelo" + ((cod != null && cod >= 0) ? " where marc_cod = " + cod.toString() : "");
        ResultSet rs = Banco.getConexao().consultar(sql);
        try
        {
            while (rs.next())
            {
                a.add(new Modelo(rs.getInt("mod_cod"),
                        rs.getString("mod_desc"), rs.getInt("mod_ano")));
            }
        } catch (Exception ex)
        {
            System.out.println("Erro Classe Modelo: " + ex.getMessage());
        }
        return a;
    }

    @Override
    public Boolean insert()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean update()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean delete()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> get(String filtro)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString()
    {
        return nome;
    }

}
