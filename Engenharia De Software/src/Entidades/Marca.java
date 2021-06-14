
package Entidades;

import Banco.Banco;
import Interface.Entidade;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Marca implements Entidade
{

    private Integer codigo;
    private String nome;

    public Marca()
    {
    }

    public Marca(Integer codigo, String nome)
    {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Marca(Integer codigo)
    {
        this.codigo = codigo;
        this.nome = getAll().get(0).getNome();
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

    /*public ArrayList<Marca> get(Integer cod)//retorna todos
    {
        ArrayList<Marca> a = new ArrayList<>();
        String sql = "select * from marca" + ((cod != null)? " where mod_cod = "+cod.toString() : "");
        ResultSet rs = Banco.getConexao().consultar(sql);
        try 
        {
            while(rs.next())
            {
                a.add(new Marca(rs.getInt("mod_cod"), rs.getString("mod_desc")));
            }
        } catch (Exception ex) 
        {
            System.out.println("Erro Classe Marca: "+ex.getMessage());
        }
        return a;
    }*/
    public Marca get(int cod)
    {
        Marca m = null;
        String sql = "SELECT * FROM marca WHERE mar_cod = " + cod;
        ResultSet rs = Banco.getConexao().consultar(sql);
        try
        {
            if (rs.next())
            {
                m = new Marca(rs.getInt("marc_cod"), rs.getString("marc_nome"));
            }
        } catch (Exception ex)
        {
            System.out.println("Erro Classe Marca: " + ex.getMessage());
        }
        return m;
    }

    public ArrayList<Marca> getAll()//retorna todos
    {
        ArrayList<Marca> a = new ArrayList<>();
        String sql = "select * from marca";
        ResultSet rs = Banco.getConexao().consultar(sql);
        try
        {
            while (rs.next())
            {
                a.add(new Marca(rs.getInt("marc_cod"), rs.getString("marc_nome")));
            }
        } catch (Exception ex)
        {
            System.out.println("Erro Classe Marca: " + ex.getMessage());
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
