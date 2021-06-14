
package Controladoras;

import Banco.Banco;
import Entidades.Cliente;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CtrCliente
{

    private static CtrCliente con;

    private CtrCliente()
    {
    }

    public static CtrCliente instancia()
    {
        if (con == null)
        {
            con = new CtrCliente();
        }
        return con;
    }

    public static void GetCliente(JFXTextField tnome, JFXTextField tcpf, JFXTextField trg, JFXTextField ttelefone, JFXTextField temail, JFXTextField tendereco, JFXTextField tcep, JFXComboBox<String> cbpais, JFXComboBox<String> cbestado, JFXComboBox<String> cbcidade, JFXComboBox<String> cbbairro, JFXDatePicker dtcadastro, Object o)
    {
        Cliente c = (Cliente) o;
        if (c != null)
        {
            tcep.setText(c.getCep());
            tcpf.setText(c.getCpf());
            temail.setText(c.getEmail());
            tendereco.setText(c.getEndereco());
            tnome.setText(c.getNome());
            trg.setText(c.getRg());
            ttelefone.setText(c.getTelefone());
            dtcadastro.setValue(c.getDtCadastro().toLocalDate());
            cbbairro.getSelectionModel().select(c.getBairro());
            cbcidade.getSelectionModel().select(c.getCidade());
            cbestado.getSelectionModel().select(c.getEstado());
            cbpais.getSelectionModel().select(c.getPais());
        }
    }

    public static boolean getClienteSimples(JFXTextField txcpf, JFXTextField txcodigocliente, JFXTextField txnomecliente, JFXTextField txrgcliente)
    {
        Cliente c = null;
        try
        {
            c = (Cliente) new Cliente().Get_Por_cpf(txcpf.getText());
            if (c != null)
            {
                txcodigocliente.setText(Integer.toString(c.getCodigo()));
                txnomecliente.setText(c.getNome());
                txrgcliente.setText(c.getRg());
            }
        } catch (SQLException ex)
        {
            c = null;
        }
        return c != null;
    }

    public static boolean SalvarCliente(String nome, String cpf, String rg, String telefone, String email, String endereco, String cep, String pais, String estado, String cidade, String bairro, Date dtCadastro)
    {
        Cliente c = new Cliente(nome, cpf, rg, telefone, email, endereco, cep, pais, estado, cidade, bairro, dtCadastro);
        return c.insert();
    }

    public static boolean ExcluirCliente(Object o)
    {
        Cliente c = (Cliente) o;
        return c.delete();
    }

    public static boolean AlteraCliente(Object o, String nome, String cpf, String rg, String telefone, String email, String endereco, String cep, String pais, String estado, String cidade, String bairro, Date dtCadastro)
    {
        Cliente c = new Cliente(((Cliente) (o)).getCodigo(), nome, cpf, rg, telefone, email, endereco, cep, pais, estado, cidade, bairro, dtCadastro);
        return c.update();
    }
    
        protected String MontaStringFiltros(String filtro1, String filtro2)
     {
        String sql = "select *from cliente";
        if (!filtro1.isEmpty())
        {
            if (filtro2.equals("Nome"))
                sql += " Where cli_nome like '%" + filtro1 + "%'";
            else if (filtro2.equals("RG"))
                sql += " Where cli_rg like '%"  + filtro1 + "%'";
        }
        return sql;
    }
    protected Object InstanciaItem(ResultSet rs) 
    {
        try 
        {
            return new Cliente(rs.getInt("cli_cod"), rs.getString("cli_nome"),
                    rs.getString("cli_cpf"), rs.getString("cli_rg"), rs.getString("cli_telefone"),
                    rs.getString("cli_email"), rs.getString("cli_endereco"), rs.getString("cli_cep"), rs.getDate("cli_datacadastro"));
        } catch (SQLException ex) 
        {
            System.out.println("Classe CtrCliente: "+ex.getMessage());
            return null;
        }
    }

    public final ArrayList<Object> getLista(String filtro1, String filtro2)
    {
        ArrayList<Object> a = new ArrayList<>();
        String sql = MontaStringFiltros(filtro1, filtro2);
        ResultSet rs = Banco.getCon().consultar(sql);
        try 
        {
            while(rs.next())
            {
                a.add(InstanciaItem(rs));
            }
        } catch (SQLException ex) 
        {
            System.out.println(ex);
        }
        return a;
    }
}
