/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladoras;

import Entidades.NovaEntidades.Servico;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Aluno
 */
public class CtrServico
{

    public static ArrayList<Object> getAll(String filtro)
    {
        try
        {
            return Servico.get(filtro);
        } catch (SQLException ex)
        {
            return null;
        }
    }
}
