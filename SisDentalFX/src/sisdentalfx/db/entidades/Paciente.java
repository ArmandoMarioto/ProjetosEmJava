package sisdentalfx.db.entidades;

import java.sql.Blob;

public class Paciente extends Pessoa {

    private String CPF, telefone, email, hisClinico, tipo, CEP, bairro, rua, numero;
    private Blob foto;
    private Cidade cidade;

    public Paciente() {
        this(0, "", "", "", "", "", "", "", "", "", null, null, "");
    }

    public Paciente(String nome, String CPF, String telefone, String email, String hisClinico, String CEP, String bairro, String rua, String numero, Blob foto, Cidade cidade, String tipo) {
        this(0, nome, CPF, telefone, email, hisClinico, CEP, bairro, rua, numero, foto, cidade, tipo);
    }

    public Paciente(int codigo, String nome, String CPF, String telefone, String email, String hisClinico, String CEP, String bairro, String rua, String numero, Blob foto, Cidade cidade, String tipo) {
        super(codigo, nome);
        this.CPF = CPF;
        this.telefone = telefone;
        this.email = email;
        this.hisClinico = hisClinico;
        this.CEP = CEP;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.foto = foto;
        this.cidade = cidade;
        this.tipo = tipo;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
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

    public String getHisClinico() {
        return hisClinico;
    }

    public void setHisClinico(String hisClinico) {
        this.hisClinico = hisClinico;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Blob getFoto() {
        return foto;
    }

    public void setFoto(Blob foto) {
        this.foto = foto;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
