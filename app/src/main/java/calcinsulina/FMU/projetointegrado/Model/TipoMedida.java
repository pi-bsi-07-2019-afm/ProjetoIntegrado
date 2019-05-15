package calcinsulina.FMU.projetointegrado.Model;

public class TipoMedida {
    private int id;
    private String nome;
    private boolean medidoEmMl;

    public TipoMedida() {
    }

    public TipoMedida(String nome, boolean medidoEmMl) {
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

    public boolean isMedidoEmMl() {
        return medidoEmMl;
    }

    public void setMedidoEmMl(boolean medidoEmMl) {
        this.medidoEmMl = medidoEmMl;
    }
}