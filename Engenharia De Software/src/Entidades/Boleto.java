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
public class Boleto implements Strategy
{

    private Double desconto;

    public Boleto(Double desconto)
    {
        this.desconto = desconto;
    }

    @Override
    public Double pagar(Double valor)
    {
        if(valida_boleto())
            return valor*(desconto/100);
        else
            return 0.0;
    }

    private boolean valida_boleto() //checa no banco se o boleto e valido atraves a api
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
