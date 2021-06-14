/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Banco.Banco;
import Entidades.NovaEntidades.OrdemServico;
import Interface.Strategy;

/**
 *
 * @author Aluno
 */
public class Pagamento
{

    private Integer codigo;
    private OrdemServico os;
    private Double valor_parc;
    private Strategy forma_pagamento;

    public Pagamento(Integer codigo, OrdemServico os, Strategy forma_pagamento)
    {
        this.codigo = codigo;
        this.os = os;
        this.forma_pagamento = forma_pagamento;
        this.valor_parc = os.getValor_total()/os.getQtdParcelas();
    }
    public Integer getCodigo()
    {
        return codigo;
    }

    public void setCodigo(Integer codigo)
    {
        this.codigo = codigo;
    }

    public OrdemServico getOs()
    {
        return os;
    }

    public void setOs(OrdemServico os)
    {
        this.os = os;
    }

    public Strategy getForma_pagamento()
    {
        return forma_pagamento;
    }

    public void setForma_pagamento(Strategy forma_pagamento)
    {
        this.forma_pagamento = forma_pagamento;
    }
    public boolean insert()
    {
        String sqlb1 = "insert into pagamento(parc_cod, os_cod, form_pag, valor_parc)("+
                    codigo+", "+os.getCodigo()+", "+
                    forma_pagamento.toString()+", "+
                    forma_pagamento.pagar(valor_parc)+")";
        return Banco.getConexao().manipular(sqlb1);
    }
}
