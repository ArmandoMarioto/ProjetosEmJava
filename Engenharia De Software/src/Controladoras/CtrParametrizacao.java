package Controladoras;

import Entidades.Parametrizacao;
import Interface.Controladora;
import java.io.IOException;
import java.sql.SQLException;

public class CtrParametrizacao implements Controladora
{

    private String sql;
    private static CtrParametrizacao con;

    private CtrParametrizacao()
    {
    }

    public static CtrParametrizacao instancia()
    {
        if (con == null)
        {
            con = new CtrParametrizacao();
        }
        return con;
    }

    public boolean inicia() throws SQLException
    {
        return new Parametrizacao().inicia();
    }

    public Parametrizacao carrega() throws SQLException, IOException
    {
        Parametrizacao para = new Parametrizacao().carrega();
        return para;
    }

    public boolean Manipular(String nomefantasia, String razaosocial, String LogoGrande, String LogoPequeno, String Endereco, String CorFundo, String TipoFonte, String Site, String Email, String Telefone) throws SQLException, IOException
    {
        return new Parametrizacao(nomefantasia, razaosocial, Endereco, CorFundo, TipoFonte, Site, Email, Telefone).Manipular(LogoGrande, LogoPequeno);
    }

    @Override
    public Boolean Salvar()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean Alterar()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean Excluir()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
