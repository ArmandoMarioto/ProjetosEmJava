package sisdentalfx.db.entidades;

public class Dentista extends Pessoa {

    private String CRO, telefone, email;
    private Agenda agenda;

    public Dentista() {
        this(0, "", "", "", "", null);
    }

    public Dentista(String nome, String CRO, String telefone, String email, Agenda agenda) {
        this(0, nome, CRO, telefone, email, agenda);
    }

    public Dentista(int codigo, String nome, String CRO, String telefone, String email, Agenda agenda) {
        super(codigo, nome);
        this.CRO = CRO;
        this.telefone = telefone;
        this.email = email;
        this.agenda = agenda;
    }

    public String getCRO() {
        return CRO;
    }

    public void setCRO(String CRO) {
        this.CRO = CRO;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }
}
