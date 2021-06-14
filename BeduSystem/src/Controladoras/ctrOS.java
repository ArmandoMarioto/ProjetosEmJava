
package Controladoras;

import Entidades.OS;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.util.ArrayList;
import Entidades.Funcionário;
import Entidades.Status;
import java.sql.Date;

public class ctrOS
{
    public boolean alterar(JFXComboBox cb, JFXTextField funcionario, JFXTextArea desc, JFXDatePicker data,int cod)
    {
        OS o= new OS(cod, desc.getText(), Date.valueOf(data.getValue()), new Funcionário().get_nome(funcionario.getText()), 
                new Status().buscaStatus(cb.getSelectionModel().getSelectedItem().toString()));
        return o.alterar();
    }
    
    public ArrayList<Object[]> procurar()
    {
        ArrayList<Object[]> usuarios = new ArrayList<>();
        ArrayList<OS> aux = new ArrayList<>();
        Object[] obj;
        int tipo;
        
        aux = new OS().busca();
            
        for(int i = 0; i < aux.size(); i++)
        {
            obj = new Object[8];
            
            obj[0] = aux.get(i).getCodigo();
            obj[1] = aux.get(i).getDescricao();
            obj[2] = aux.get(i).getFuncionario().getCodigo();
            obj[3] = aux.get(i).getOrcamento().getCodigo();
            obj[4] = aux.get(i).getOrcamento().getCliente().getNome();
            obj[5] = aux.get(i).getOrcamento().getVeiculo().getVei_placa();
            obj[6] = aux.get(i).getStatus().getDescricao();
            obj[7] = aux.get(i).getData();
            usuarios.add(obj);
        }
        
        return usuarios;
    }
}
