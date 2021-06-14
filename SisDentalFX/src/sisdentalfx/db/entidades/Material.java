package sisdentalfx.db.entidades;

public class Material {

    private int codigo;
    private String descricao;
    private double preco;

    public Material() {
        this(0, "", 0);
    }

    public Material(String descricao, double preco) {
        this(0, descricao, preco);
    }

    public Material(int codigo, String descricao, double preco) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
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

    @Override
    public String toString() {
        return descricao;
    }

}
