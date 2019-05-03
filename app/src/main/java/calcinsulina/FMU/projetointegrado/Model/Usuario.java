package calcinsulina.FMU.projetointegrado.Model;

import java.util.Date;

public class Usuario {

    private int id;
    private String nome;
    private double peso;
    private String dataNascimento;
    private double fatorSensibilidade;
    private String email;
//    private String dataRegistro;

    public Usuario(){
    }

    public Usuario(String nome, double peso, String dataNascimento, double fatorSensibilidade, String email) {
        this.nome = nome;
        this.peso = peso;
        this.dataNascimento = dataNascimento;
        this.fatorSensibilidade = fatorSensibilidade;
        this.email = email;
        //Date obj = new Date();
        //this.dataRegistro = obj.getDate();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public double getFatorSensibilidade() {
        return fatorSensibilidade;
    }

    public void setFatorSensibilidade(double fatorSensibilidade) {
        this.fatorSensibilidade = fatorSensibilidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getDataRegistro() {
//        return dataRegistro;
//    }

//    public void setDataRegistro(String dataRegistro) {
//        this.dataRegistro = dataRegistro;
//    }

    public void salvar(){
        //lógica aqui
    }

    public void alterar(){
        //lógica aqui
    }

}
