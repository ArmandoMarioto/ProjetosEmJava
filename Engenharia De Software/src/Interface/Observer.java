/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Entidades.Produto;

/**
 *
 * @author Aluno
 */
public interface Observer
{

    public boolean send(Produto p);

    public boolean receive(Produto P);
}
