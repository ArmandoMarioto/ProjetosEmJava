package sisdentalfx.db.entidades;

public class Cidade {

    private int codigo;
    private String nome;
    private UF uf;

    public Cidade() {
        this(0, "", null);
    }

    public Cidade(String nome, UF uf) {
        this(0, nome, uf);
    }

    public Cidade(int codigo, String nome, UF uf) {
        this.codigo = codigo;
        this.nome = nome;
        this.uf = uf;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return nome;
    }

}
