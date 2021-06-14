/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Banco.Banco;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Luish
 */
public class Fabricante
{

    private Integer codigo;
    private String nome;
    private Date dtCadastro;
    private String cnpj;
    private String fone;

    public Fabricante()
    {
    }

    /*public Fabricante(Integer codigo, String nome, Date dtCadastro)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.dtCadastro = dtCadastro;
    }*/
    public Fabricante(Integer codigo, String nome, Date dtCadastro, String cnpj, String fone)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.dtCadastro = dtCadastro;
        this.cnpj = cnpj;
        this.fone = fone;
    }

    public Fabricante(Integer codigo)
    {
        this.codigo = codigo;
        getDadosFabricante();
    }

    public String getCnpj()
    {
        return cnpj;
    }

    public void setCnpj(String cnpj)
    {
        this.cnpj = cnpj;
    }

    public String getFone()
    {
        return fone;
    }

    public void setFone(String fone)
    {
        this.fone = fone;
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

    public Date getDtCadastro()
    {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro)
    {
        this.dtCadastro = dtCadastro;
    }

    public ArrayList<Object> get()
    {
        String sql = "select *from fabricante";
        ResultSet rs = null;
        ArrayList<Object> a = new ArrayList<>();
        if (codigo != null)
        {
            sql += " fab_cod = " + codigo;
        }
        rs = Banco.con.consultar(sql);
        try
        {
            while (rs.next())
            {
                a.add(new Fabricante(rs.getInt("fab_cod"), rs.getString("fab_nome"), rs.getDate("fab_dtcadastro"), rs.getString("fab_cnpj"), rs.getString("fab_fone")));
            }
        } catch (SQLException ex)
        {
            a = null;
        }
        return a;
    }

    public void getDadosFabricante()
    {
        String sql = "select *from fabricante";
        ResultSet rs = null;
        Fabricante fa = null;
        if (codigo != null)
        {
            sql += " where fab_cod = " + codigo;
        }
        rs = Banco.con.consultar(sql);
        try
        {
            while (rs.next())
            {
                fa = new Fabricante(rs.getInt("fab_cod"), rs.getString("fab_nome"), rs.getDate("fab_dtcadastro"), rs.getString("fab_cnpj"), rs.getString("fab_fone"));
            }
        } catch (SQLException ex)
        {
            fa = null;
        }
        if (fa != null)
        {
            nome = fa.getNome();
            dtCadastro = fa.getDtCadastro();
        } else
        {
            nome = "Fabricante Não Cadastrado";
        }
    }

    @Override
    public String toString()
    {
        return nome;
    }

}
