package sisdentalfx.db.entidades;

public class Procedimentos {

    private int codigo, tempo;
    private String descricao;
    private double preco;

    public Procedimentos() {
        this(0, "", 0, 0);
    }

    public Procedimentos(String descricao, double preco, int tempo) {
        this(0, descricao, preco, tempo);
    }

    public Procedimentos(int codigo, String descricao, double preco, int tempo) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
        this.tempo = tempo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    @Override
    public String toString() {
        return descricao;
    }

}
