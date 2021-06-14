/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Interface.Strategy;

/**
 *
 * @author Aluno
 */
public class Dinheiro implements Strategy
{

    private Double desconto;

    public Dinheiro(Double desconto)
    {
        this.desconto = desconto;
    }

    @Override
    public Double pagar(Double valor)
    {
        return valor*(desconto/100);
    }
}
