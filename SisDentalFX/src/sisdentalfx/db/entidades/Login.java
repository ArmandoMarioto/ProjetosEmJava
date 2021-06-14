package sisdentalfx.db.entidades;

public class Login {

    private String usuario, senha;
    private int nivel;
    private boolean tipo = false;

    public Login(String usuario, String senha, boolean tipo, int nivel) {
        this.usuario = usuario;
        this.senha = senha;
        this.tipo = tipo;
        this.nivel = nivel;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public boolean isTipo() {
        return tipo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
