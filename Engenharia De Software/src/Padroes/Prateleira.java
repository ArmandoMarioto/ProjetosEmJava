/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Padroes;

import java.util.ArrayList;

/**
 *
 * @author Luish
 */
public class Prateleira
{

    private Integer numero;
    private ArrayList<Compartimento_Prateleira> compartimentos;

    public Prateleira(Integer numero)
    {
        this.numero = numero;
    }

    public Integer getNumero()
    {
        return numero;
    }

    public void setNumero(Integer numero)
    {
        this.numero = numero;
    }

    public ArrayList<Compartimento_Prateleira> getCompartimentos()
    {
        return compartimentos;
    }

    public void setCompartimentos(ArrayList<Compartimento_Prateleira> compartimentos)
    {
        this.compartimentos = compartimentos;
    }

    public void addCompartimento(Compartimento_Prateleira comp)
    {
        compartimentos.add(comp);
    }

    public static Integer Calcular_Espaço_Livre()
    {
        return 0;
    }
}
