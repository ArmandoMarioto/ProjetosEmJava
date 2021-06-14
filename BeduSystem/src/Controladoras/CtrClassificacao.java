
package Controladoras;

import Entidades.Classificacao;
import java.util.ArrayList;


public class CtrClassificacao {
    private Classificacao classif;
    
    public ArrayList<Object[]> buscar()
    {
        ArrayList<Object[]> list = new ArrayList<>();
        ArrayList<Classificacao> listAux;
        Object[] obj;
        
        classif = new Classificacao();
        listAux = classif.buscar();
        for (int i = 0; i < listAux.size(); i++) {
            obj = new Object[2];
            obj[0] = listAux.get(i).getCod();
            obj[1] = listAux.get(i).getDescricao();
            
            list.add(obj);
        }
        
        return list;
    }
}
