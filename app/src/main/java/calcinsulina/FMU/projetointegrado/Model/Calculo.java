package calcinsulina.FMU.projetointegrado.Model;

import java.util.Date;

public class Calculo {
    private int id;
    private double quantCarbPorUnidInsulina;
    private double glicemiaAlvo;
    private int[] conjuntoAlimentos;
    private double totalCarb;
    private int totalInsulina;
    private Date dataRegistro;

    public Calculo(double quantCarbPorUnidInsulina, double glicemiaAlvo, int[] conjuntoAlimentos, double totalCarb, int totalInsulina) {
        this.quantCarbPorUnidInsulina = quantCarbPorUnidInsulina;
        this.glicemiaAlvo = glicemiaAlvo;
        this.conjuntoAlimentos = conjuntoAlimentos;
        this.totalCarb = totalCarb;
        this.totalInsulina = totalInsulina;
        //Date obj = new Date();
        //this.dataRegistro = obj.getDate();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getQuantCarbPorUnidInsulina() {
        return quantCarbPorUnidInsulina;
    }

    public void setQuantCarbPorUnidInsulina(double quantCarbPorUnidInsulina) {
        this.quantCarbPorUnidInsulina = quantCarbPorUnidInsulina;
    }

    public double getGlicemiaAlvo() {
        return glicemiaAlvo;
    }

    public void setGlicemiaAlvo(double glicemiaAlvo) {
        this.glicemiaAlvo = glicemiaAlvo;
    }

    public int[] getConjuntoAlimentos() {
        return conjuntoAlimentos;
    }

    public void setConjuntoAlimentos(int[] conjuntoAlimentos) {
        this.conjuntoAlimentos = conjuntoAlimentos;
    }

    public double getTotalCarb() {
        return totalCarb;
    }

    public void setTotalCarb(double totalCarb) {
        this.totalCarb = totalCarb;
    }

    public int getTotalInsulina() {
        return totalInsulina;
    }

    public void setTotalInsulina(int totalInsulina) {
        this.totalInsulina = totalInsulina;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
}
