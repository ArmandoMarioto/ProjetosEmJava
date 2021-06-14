/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.util.ArrayList;

/**
 *
 * @author Luish
 */
public interface Entidade
{

    public abstract Boolean insert();

    public abstract Boolean update();

    public abstract Boolean delete();

    public abstract ArrayList<Object> get(String filtro);
}
