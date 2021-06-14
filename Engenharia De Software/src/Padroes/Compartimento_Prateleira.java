/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Padroes;

import Entidades.Produto;
import java.util.ArrayList;

/**
 *
 * @author Luish
 */
public class Compartimento_Prateleira
{

    private Integer numero;
    private Integer capacidade;
    private ArrayList<Produto> pecas_na_prateleira;

    public Compartimento_Prateleira()
    {
    }

    public Compartimento_Prateleira(Integer numero, Integer capacidade)
    {
        this.numero = numero;
        this.capacidade = capacidade;
    }

    public Compartimento_Prateleira(Integer numero, Integer capacidade, ArrayList<Produto> pecas_na_prateleira)
    {
        this.numero = numero;
        this.capacidade = capacidade;
        this.pecas_na_prateleira = pecas_na_prateleira;
    }

    public Integer getNumero()
    {
        return numero;
    }

    public void setNumero(Integer numero)
    {
        this.numero = numero;
    }

    public Integer getCapacidade()
    {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade)
    {
        this.capacidade = capacidade;
    }

    public ArrayList<Produto> getPecas_na_prateleira()
    {
        return pecas_na_prateleira;
    }

    public void setPecas_na_prateleira(ArrayList<Produto> pecas_na_prateleira)
    {
        this.pecas_na_prateleira = pecas_na_prateleira;
    }

    public Integer calcular_Espaco_Livre()
    {
        return 0;
    }

    public Boolean atribuir_A_Produto(int codigo_do_produto)
    {
        return true;
    }

    public Boolean liberarPrateleira()
    {
        return true;
    }
}
