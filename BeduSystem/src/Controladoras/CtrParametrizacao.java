package Controladoras;

import Entidades.Parametrizacao;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;

public class CtrParametrizacao 
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
    public boolean Manipular(String nome, String fantasia, String logoGrande, String logoPequeno, String telefone, String email, String razaoSocial, String rua, String bairro, String cidade, String cep, String cor, String site) throws SQLException, IOException
    {
        return new Parametrizacao(nome, fantasia, telefone, email, razaoSocial, rua, bairro, cidade,cep,cor,site).Manipular(logoGrande, logoPequeno);
    }
    
        public boolean alterar(String nome, String fantasia, BufferedImage logoGrande, BufferedImage logoPequeno, String telefone, String email, String razaoSocial, String rua, String bairro, String cidade, String cep, String cor, String site) throws SQLException, IOException
    {
        return new Parametrizacao(nome, fantasia, telefone, email, razaoSocial, rua, bairro, cidade,cep,cor,site).alterar(logoGrande, logoPequeno);
    }


}
