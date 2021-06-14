package Entidades;

import java.sql.Date;

public class Parcela
{
    private int codigo;
    private boolean status;
    private Date data_vencimento;
    private Date data_recebimento;
    private double valor;
    private Recebimento recebimento;
    private Pagamento pagamento;

    public Parcela()
    {
    }

    public Parcela(int codigo, boolean status, Date data_vencimento, double valor, Recebimento recebimento, Pagamento pagamento)
    {
        this.codigo = codigo;
        this.status = status;
        this.data_vencimento = data_vencimento;
        this.valor = valor;
        this.recebimento = recebimento;
        this.pagamento = pagamento;
    }

    public Parcela(int codigo, boolean status, Date data_vencimento, Date data_recebimento, double valor, Recebimento recebimento, Pagamento pagamento)
    {
        this.codigo = codigo;
        this.status = status;
        this.data_vencimento = data_vencimento;
        this.data_recebimento = data_recebimento;
        this.valor = valor;
        this.recebimento = recebimento;
        this.pagamento = pagamento;
    }

    public int getCodigo()
    {
        return codigo;
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public boolean isStatus()
    {
        return status;
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }

    public Date getData_vencimento()
    {
        return data_vencimento;
    }

    public void setData_vencimento(Date data_vencimento)
    {
        this.data_vencimento = data_vencimento;
    }

    public Date getData_recebimento()
    {
        return data_recebimento;
    }

    public void setData_recebimento(Date data_recebimento)
    {
        this.data_recebimento = data_recebimento;
    }

    public double getValor()
    {
        return valor;
    }

    public void setValor(double valor)
    {
        this.valor = valor;
    }

    public Recebimento getRecebimento()
    {
        return recebimento;
    }

    public void setRecebimento(Recebimento recebimento)
    {
        this.recebimento = recebimento;
    }

    public Pagamento getPagamento()
    {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento)
    {
        this.pagamento = pagamento;
    }
    
}
