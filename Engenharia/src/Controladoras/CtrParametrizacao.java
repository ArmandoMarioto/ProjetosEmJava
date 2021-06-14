package Controladoras;

import Entidades.Parametrizacao;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

public class CtrParametrizacao
{

    private String sql;

    public CtrParametrizacao()
    {
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

}
