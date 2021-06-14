package sisdentalfx.db.entidades;

import java.time.LocalDate;

public class Horarios {

    private int codigo;
    private LocalDate data;
    private String hora;
    private Paciente paciente;
    private Consulta consulta;

    public Horarios() {
        this(null, null, null, null);
    }

    public Horarios(LocalDate data, String hora, Paciente paciente, Consulta consulta) {
        this.data = data;
        this.hora = hora;
        this.paciente = paciente;
        this.consulta = consulta;
    }

    public Horarios(int codigo, LocalDate data, String hora, Paciente paciente, Consulta consulta) {
        this.codigo = codigo;
        this.data = data;
        this.hora = hora;
        this.paciente = paciente;
        this.consulta = consulta;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
