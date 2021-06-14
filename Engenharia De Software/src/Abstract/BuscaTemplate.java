
package Abstract;

import Banco.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class BuscaTemplate 
{
    public final ArrayList<Object> getLista(String filtro1, String filtro2)
    {
        ArrayList<Object> a = new ArrayList<>();
        String sql = MontaStringFiltros(filtro1, filtro2);
        ResultSet rs = Banco.getConexao().consultar(sql);
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

    protected  abstract String MontaStringFiltros(String filtro1, String filtro2);

    protected abstract Object InstanciaItem(ResultSet rs);
}
