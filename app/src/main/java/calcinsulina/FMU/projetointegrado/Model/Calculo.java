package calcinsulina.FMU.projetointegrado.Model;

import java.util.Date;

public class Calculo {
    private int id;
    private double quantCarbPorUnidInsulina;
    private double glicemiaAlvo;
    private double glicemiaObtida;
    private int[] conjuntoAlimentos;
    private double[] conjuntoMultiplicadores;

    private double totalCarb;
    private int totalInsulina;
    private String dataRegistro;

    public double[] getConjuntoMultiplicadores() {
        return conjuntoMultiplicadores;
    }

    public void setConjuntoMultiplicadores(double[] conjuntoMultiplicadores) {
        this.conjuntoMultiplicadores = conjuntoMultiplicadores;
    }

    public Calculo() {

    }

    public Calculo(double quantCarbPorUnidInsulina, double glicemiaAlvo, int[] conjuntoAlimentos, double totalCarb, int totalInsulina) {
        this.quantCarbPorUnidInsulina = quantCarbPorUnidInsulina;
        this.glicemiaAlvo = glicemiaAlvo;
        this.conjuntoAlimentos = conjuntoAlimentos;
        this.totalCarb = totalCarb;
        this.totalInsulina = totalInsulina;
        Date obj = new Date();
        this.dataRegistro = obj.toString();
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

    public String getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(String dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getStringConjuntoAlimentos(){
        String retorno = "";
        for (int i = 0; i < conjuntoAlimentos.length; i ++){
            if (retorno != ""){
                retorno += ",";
            }
            retorno += String.valueOf(conjuntoAlimentos[i]);
        }
        return retorno;
    }

    public void setConjuntoAlimentosFromString(String s){
        String[] vetor = s.split(",");
        int[] conjuntoAlimentosT = new int[vetor.length];
        for (int i = 0; i < vetor.length; i++){
            conjuntoAlimentosT[i] = Integer.getInteger(vetor[i]);
        }
        this.conjuntoAlimentos = conjuntoAlimentosT;
    }

    public double getGlicemiaObtida() {
        return glicemiaObtida;
    }

    public void setGlicemiaObtida(double glicemiaObtida) {
        this.glicemiaObtida = glicemiaObtida;
    }
}