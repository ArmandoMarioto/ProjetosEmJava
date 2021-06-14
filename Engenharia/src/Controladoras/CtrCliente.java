/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladoras;

import Entidades.Cliente;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Luish
 */
public class CtrCliente
{

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
            cbbairro.getSelectionModel().select(c.getBairro());
            cbcidade.getSelectionModel().select(c.getCidade());
            cbestado.getSelectionModel().select(c.getEstado());
            cbpais.getSelectionModel().select(c.getPais());
        }
    }

    private CtrCliente()
    {

    }

    public static boolean SalvarCliente(String nome, String cpf, String rg, String telefone, String email, String endereco, String cep, String pais, String estado, String cidade, String bairro, Date dtCadastro)
    {
        Cliente c = new Cliente(nome, cpf, rg, telefone, email, endereco, cep, pais, estado, cidade, bairro, dtCadastro);
        return c.Add();
    }

    public static boolean ExcluirCliente(Object o)
    {
        Cliente c = (Cliente) o;
        return c.Remove();
    }

    public static boolean AlteraCliente(Object o, String nome, String cpf, String rg, String telefone, String email, String endereco, String cep, String pais, String estado, String cidade, String bairro, Date dtCadastro)
    {
        Cliente c = new Cliente(((Cliente) (o)).getCodigo(), nome, cpf, rg, telefone, email, endereco, cep, pais, estado, cidade, bairro, dtCadastro);
        return c.Altera();
    }

    public static ArrayList<Object> GetListaCliente(String filtro, String filtro2)
    {
        return new Cliente().GetAvancado(filtro, filtro2);
    }
}
