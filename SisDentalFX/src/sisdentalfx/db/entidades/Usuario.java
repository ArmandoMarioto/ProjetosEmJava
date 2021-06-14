package sisdentalfx.db.entidades;

public class Usuario extends Pessoa {

    private int nivel;
    private String senha;

    public Usuario() {
        this(0, "", ' ', "");
    }

    public Usuario(String nome, int nivel, String senha) {
        this(0, nome, nivel, senha);
    }

    public Usuario(int codigo, String nome, int nivel, String senha) {
        super(codigo, nome);
        this.nivel = nivel;
        this.senha = senha;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
