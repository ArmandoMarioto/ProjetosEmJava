package sisdentalfx.db.entidades;

abstract public class Pessoa {

    protected int codigo;
    protected String nome;

    public Pessoa() {
        this(0, "");
    }

    public Pessoa(String nome) {
        this(0, nome);
    }

    public Pessoa(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
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

    @Override
    public String toString() {
        return nome;
    }
}
