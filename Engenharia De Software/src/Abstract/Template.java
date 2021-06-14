
package Abstract;

import Banco.Banco;
import java.sql.SQLException;

public abstract class Template 
{
    public final boolean template_gravar()
    {
        boolean flag = false;
        try
        {
            try
            {
                Banco.getConexao().getConnection().setAutoCommit(false);
            } catch (SQLException ex)
            {
                System.out.println("Erro");
                return false;
            }
           flag = this.insert();
           try
            {
                if (flag)
                {
                    Banco.getConexao().getConnection().commit();
                } else
                {
                    Banco.getConexao().getConnection().rollback();
                }

                Banco.getConexao().getConnection().setAutoCommit(true);
            } catch (SQLException ex)
            {
                System.out.println("Erro");
                flag = false;
            }
        }catch(Exception ex)
        {
             try
            {
                Banco.getConexao().getConnection().rollback();
            } catch (SQLException ex1)
            {
                System.out.println("Erro fatal: " + ex1.getMessage());
            }
        }
        return true;
    }
    protected abstract Boolean insert();
}
