package sisdentalfx.db.entidades;

import java.util.ArrayList;

public class Agenda {

    private ArrayList<Horarios> arrayHorarios;
    private Dentista dentista;

    public Agenda() {
    }

    public Agenda(Dentista dentista, ArrayList<Horarios> arrayHorarios) {
        this.dentista = dentista;
        this.arrayHorarios = arrayHorarios;
    }

    public Agenda(Dentista dentista) {
        this.dentista = dentista;
        arrayHorarios = new ArrayList();
    }

    public ArrayList<Horarios> getArrayHorarios() {
        return arrayHorarios;
    }

    public void setArrayHorarios(ArrayList<Horarios> arrayHorarios) {
        this.arrayHorarios = arrayHorarios;
    }

    public Dentista getDentista() {
        return dentista;
    }

    public void setDentista(Dentista dentista) {
        this.dentista = dentista;
    }
}
