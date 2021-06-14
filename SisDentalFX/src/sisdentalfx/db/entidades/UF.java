package sisdentalfx.db.entidades;

public class UF {

    private int codigo, pais;
    private String sigla, nome;

    public UF() {
        this(0, "", "");
    }

    public UF(String sigla, String nome) {
        this(0, sigla, nome);
    }

    public UF(int codigo, String sigla, String nome) {
        this.codigo = codigo;
        this.sigla = sigla;
        this.nome = nome;
        pais = 1;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return sigla;
    }
}
