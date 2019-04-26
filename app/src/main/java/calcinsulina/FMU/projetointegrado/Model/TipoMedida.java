package calcinsulina.FMU.projetointegrado.Model;

public class TipoMedida {
    private int id;
    private String nome;
    private int medidoEmMl;

    public TipoMedida(){
    }

    public TipoMedida(String nome, int medidoEmMl) {
        this.nome = nome;
        this.medidoEmMl = medidoEmMl;
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

    public int isMedidoEmMl() {
        return medidoEmMl;
    }

    public void setMedidoEmMl(int medidoEmMl) {
        this.medidoEmMl = medidoEmMl;
    }
}