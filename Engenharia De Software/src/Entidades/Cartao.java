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
public class Cartao implements Strategy
{

    private Double juros;

    public Cartao(Double juros)
    {
        this.juros = juros;
    }

    @Override
    public Double pagar(Double valor)
    {
        return valor+(valor*(juros/100));
    }

}
