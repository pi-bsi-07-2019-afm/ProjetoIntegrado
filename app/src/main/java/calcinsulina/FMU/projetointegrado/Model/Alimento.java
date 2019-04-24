package calcinsulina.FMU.projetointegrado.Model;

public class Alimento {

    private int id;
    private String nome;
    private int tipoMedida;
    private double gOuMl;
    private double quantCarb;
    private double calorias;
    private double quantCarbPorG;

    public Alimento(){
    }

    public Alimento(String nome, int tipoMedida, double gOuMl, double quantCarb, double calorias, double quantCarbPorG) {
        this.nome = nome;
        this.tipoMedida = tipoMedida;
        this.gOuMl = gOuMl;
        this.quantCarb = quantCarb;
        this.calorias = calorias;
        this.quantCarbPorG = quantCarbPorG;
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

    public int getTipoMedida() {
        return tipoMedida;
    }

    public void setTipoMedida(int tipoMedida) {
        this.tipoMedida = tipoMedida;
    }

    public double getgOuMl() {
        return gOuMl;
    }

    public void setgOuMl(double gOuMl) {
        this.gOuMl = gOuMl;
    }

    public double getQuantCarb() {
        return quantCarb;
    }

    public void setQuantCarb(double quantCarb) {
        this.quantCarb = quantCarb;
    }

    public double getCalorias() {
        return calorias;
    }

    public void setCalorias(double calorias) {
        this.calorias = calorias;
    }

    public double getQuantCarbPorG() {
        return quantCarbPorG;
    }

    public void setQuantCarbPorG(double quantCarbPorG) {
        this.quantCarbPorG = quantCarbPorG;
    }

}
