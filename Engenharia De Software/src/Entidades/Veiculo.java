package Entidades;

import Banco.Banco;
import Interface.Entidade;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.scene.paint.Color;

public class Veiculo implements Entidade
{

    private Integer codigo;
    private Cliente proprietario;
    private Modelo modelo;
    private Marca marca;
    private String chassi;
    private Color cor;
    private String placa;

    public Veiculo()
    {
    }

    public Veiculo(int codigo)
    {
        this.codigo = codigo;
    }

    public Veiculo(Integer codigo, Integer cod_proprietario, Integer cod_modelo, Integer cod_marca, String chassi, Color cor, String placa)//update
    {
        this.codigo = codigo;
        this.proprietario = new Cliente(cod_proprietario);
        this.modelo = new Modelo(cod_modelo);
        this.marca = new Marca(cod_marca);
        this.chassi = chassi;
        this.cor = cor;
        this.placa = placa;
    }

    public Veiculo(Integer codigo, int modelo, int marca, String chassi, Color cor, String placa)
    {
        this.codigo = codigo;
        this.modelo = new Modelo(modelo);
        this.marca = new Marca(marca);
        this.chassi = chassi;
        this.cor = cor;
        this.placa = placa;
    }

    public Integer getCodigo()
    {
        return codigo;
    }

    public void setCodigo(Integer codigo)
    {
        this.codigo = codigo;
    }

    public Cliente getProprietario()
    {
        return proprietario;
    }

    public void setProprietario(Cliente proprietario)
    {
        this.proprietario = proprietario;
    }

    public Modelo getModelo()
    {
        return modelo;
    }

    public void setModelo(Modelo modelo)
    {
        this.modelo = modelo;
    }

    public Marca getMarca()
    {
        return marca;
    }

    public void setMarca(Marca marca)
    {
        this.marca = marca;
    }

    public String getChassi()
    {
        return chassi;
    }

    public void setChassi(String chassi)
    {
        this.chassi = chassi;
    }

    public Color getCor()
    {
        return cor;
    }

    public void setCor(Color cor)
    {
        this.cor = cor;
    }

    public String getPlaca()
    {
        return placa;
    }

    public void setPlaca(String placa)
    {
        this.placa = placa;
    }

    @Override
    public Boolean insert()
    {
        String scor = Integer.toString((int) cor.getRed()) + "|"
                + Integer.toString((int) cor.getGreen()) + "|"
                + Integer.toString((int) cor.getBlue());
        String sql = "INSERT INTO veiculo(cli_cod, mod_cod, marc_cod, vei_chassi ,vei_cor ,vei_placa) VALUES(" + proprietario.getCodigo() + ", "
                + modelo.getCodigo() + ", " + marca.getCodigo() + ", '" + chassi + "', '" + scor + "', '" + placa + "'";
        return Banco.getConexao().manipular(sql);
    }

    @Override
    public Boolean update()
    {
        String scor = Integer.toString((int) cor.getRed()) + "|"
                + Integer.toString((int) cor.getGreen()) + "|"
                + Integer.toString((int) cor.getBlue());
        String sql = "UPDATE veiculo SET cli_cod = " + proprietario.getCodigo()
                + ", mod_cod = " + modelo.getCodigo()
                + ", marc_cod = " + marca.getCodigo()
                + ", vei_chassi = '" + chassi
                + "', vei_cor = '" + scor
                + "', vei_placa = '" + placa + "' WHERE vei_cod = " + codigo;
        return Banco.getConexao().manipular(sql);
    }

    @Override
    public Boolean delete()
    {
        String sql = "DELETE FROM veiculo WHERE vei_cod = " + codigo;
        return Banco.getConexao().manipular(sql);
    }

    public ArrayList<Veiculo> get(String filtro, String busca)
    {
        String[] ac;
        Color c;
        String sql = "SELECT * FROM veiculos"
                + ((!busca.isEmpty()) ? busca + " LIKE '" + filtro + "%" + "'" : "");

        ArrayList<Veiculo> obj = new ArrayList<>();
        ResultSet rs = Banco.getConexao().consultar(sql);

        try
        {
            while (rs != null && rs.next())
            {
                ac = rs.getString("vei_cor").split("|");

                c = new Color(Integer.parseInt(ac[0]),
                        Integer.parseInt(ac[1]),
                        Integer.parseInt(ac[2]), 0);

                obj.add(new Veiculo(rs.getInt("vei_cod"),
                        rs.getInt("cli_cod"),
                        rs.getInt("mod_cod"),
                        rs.getInt("marc_cod"),
                        rs.getString("vei_chassi"),
                        c,
                        rs.getString("vei_placa")));
            }
            //codigo, nome, cnpf, email, fone, site, contato, celular
        } catch (Exception ex)
        {
            System.out.println("Erro na classe veiculo: " + ex.getMessage());
        }
        return obj;
    }

    public ArrayList<Veiculo> getAll()
    {
        String[] ac;
        Color c;
        String sql = "SELECT * FROM veiculos";

        ArrayList<Veiculo> obj = new ArrayList<>();
        ResultSet rs = Banco.getConexao().consultar(sql);

        try
        {
            while (rs != null && rs.next())
            {
                ac = rs.getString("vei_cor").split("|");

                c = new Color(Integer.parseInt(ac[0]),
                        Integer.parseInt(ac[1]),
                        Integer.parseInt(ac[2]), 0);

                obj.add(new Veiculo(rs.getInt("vei_cod"),
                        rs.getInt("cli_cod"),
                        rs.getInt("mod_cod"),
                        rs.getInt("marc_cod"),
                        rs.getString("vei_chassi"),
                        c,
                        rs.getString("vei_placa")));
            }
            //codigo, nome, cnpf, email, fone, site, contato, celular
        } catch (Exception ex)
        {
            System.out.println("Erro na classe veiculo: " + ex.getMessage());
        }
        return obj;
    }

    public ArrayList<Veiculo> getAll_Owner(int cod)
    {
        String[] ac;
        Color c;
        String sql = "SELECT * FROM veiculos where cli_cod = " + cod;

        ArrayList<Veiculo> obj = new ArrayList<>();
        ResultSet rs = Banco.getConexao().consultar(sql);

        try
        {
            while (rs != null && rs.next())
            {
                ac = rs.getString("vei_cor").split("|");

                c = new Color(Integer.parseInt(ac[0]),
                        Integer.parseInt(ac[1]),
                        Integer.parseInt(ac[2]), 0);

                obj.add(new Veiculo(rs.getInt("vei_cod"),
                        rs.getInt("cli_cod"),
                        rs.getInt("mod_cod"),
                        rs.getInt("marc_cod"),
                        rs.getString("vei_chassi"),
                        c,
                        rs.getString("vei_placa")));
            }
            //codigo, nome, cnpf, email, fone, site, contato, celular
        } catch (Exception ex)
        {
            System.out.println("Erro na classe veiculo: " + ex.getMessage());
        }
        return obj;
    }

    @Override
    public ArrayList<Object> get(String filtro)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
