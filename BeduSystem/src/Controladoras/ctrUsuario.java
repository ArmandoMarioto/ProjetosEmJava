
package Controladoras;

import Entidades.Funcionário;
import Entidades.Usuario;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.util.ArrayList;

public class ctrUsuario
{
    public boolean gravar(JFXTextField nome, JFXTextField login, JFXPasswordField senha, JFXComboBox nivel)
    {
        int n;
        if(new Funcionário().get(nome.getText()).getCodigo() == 0)
            return false;
        else
        {
            if(nivel.getSelectionModel().getSelectedItem().equals("alto"))
                n = 0;
            else if(nivel.getSelectionModel().getSelectedItem().equals("medio"))
                n = 1;
            else 
                n = 2;
            
            Usuario usuario = new Usuario(new Funcionário().get(nome.getText()), login.getText(), senha.getText(), n);
            return usuario.gravar();
        }
        
    }
    
    public boolean alterar(JFXTextField nome, JFXTextField login, JFXPasswordField senha, JFXComboBox nivel)
    {
        Funcionário funcionario = new Funcionário();
        Usuario usuario = new Usuario(funcionario.get(nome.getText()), login.getText(), senha.getText(), nivel.getSelectionModel().getSelectedIndex());
        return usuario.alterar();
    }
    
    public boolean remover(JFXTextField login)
    {
        if(!login.getText().equals("admin"))
        {
            Usuario usuario = new Usuario();
            usuario.setLogin(login.getText());
            return usuario.remover();
        }
        return false;
    }
    
    public ArrayList<Object[]> procurar(JFXRadioButton rb, JFXTextField filtro)
    {
        ArrayList<Object[]> usuarios = new ArrayList<>();
        ArrayList<Usuario> aux = new ArrayList<>();
        Object[] obj;
        int tipo;
        
        if(rb.getText().equals("Nome"))
            aux = new Usuario().busca(filtro.getText(), 0);
        else if(rb.getText().equals("Login"))
            aux = new Usuario().busca(filtro.getText(), 1);
        else
        {
            String txt = "";
            switch(filtro.getText())
            {
                case "alto":
                    txt = "0";
                    break;
                case "medio":
                    txt = "1";
                    break;
                case "baixo":
                    txt = "2";
                    break;
            }
            aux = new Usuario().busca(txt, 2);
        }
            
        for(int i = 0; i < aux.size(); i++)
        {
            obj = new Object[3];
            
            obj[0] = aux.get(i).getLogin();
            obj[1] = aux.get(i).getNivel();
            obj[2] = aux.get(i).getFuncionario().getNome();
            usuarios.add(obj);
        }
        
        return usuarios;
    }
    
    public String getSenha(JFXTextField filtro)
    {
        Funcionário f = new Funcionário().get(filtro.getText());
        return new Usuario(f).busca().getSenha();
    }
}
