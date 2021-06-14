
package Controladoras;

import Entidades.Fornecedor;
import java.util.ArrayList;


public class CtrFornecedor {
    private Fornecedor forn;
    
    public int salvar(Object[] obj, int flag)
    {
        boolean ret;
        forn = new Fornecedor((int)obj[0] ,(String)obj[1], (String)obj[2], (String)obj[3], (String)obj[4], (String)obj[5], (String)obj[6], (int)obj[7], (String)obj[8], (String)obj[9]);
        return forn.salvar(flag);
    }
    
    public ArrayList<Object[]> buscar (String str)
    {
        forn = new Fornecedor();
        ArrayList<Fornecedor> listAux = forn.buscar(str);
        ArrayList<Object[]> list = new ArrayList<>();
        Object[] obj;
        
        for (int i = 0; i < listAux.size(); i++) {
            obj = new Object[10];
            
            obj[0] = listAux.get(i).getCod();
            obj[1] = listAux.get(i).getCnpj();
            obj[2] = listAux.get(i).getNome();
            obj[3] = listAux.get(i).getTelefone();
            obj[4] = listAux.get(i).getEmail();
            obj[5] = listAux.get(i).getRua();
            obj[6] = listAux.get(i).getBairro();
            obj[7] = listAux.get(i).getNum();
            obj[8] = listAux.get(i).getCidade();
            obj[9] = listAux.get(i).getCep();
            
            list.add(obj);
        }
        
        return list;
    }
    
    public boolean excluir(int cod)
    {
        forn = new Fornecedor(cod);
        return forn.excluir();
    }
}
