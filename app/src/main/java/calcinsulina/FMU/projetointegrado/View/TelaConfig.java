package calcinsulina.FMU.projetointegrado.View;

import calcinsulina.FMU.projetointegrado.R;

public class TelaConfig {

    MainActivity act;
    TelaPrincipal tela_principal;

    public TelaConfig(MainActivity act, TelaPrincipal tela_principal){
        this.act = act;
        this.tela_principal = tela_principal;
    }

    public void CarregarTela() {
        act.setContentView(R.layout.tela_config);
    }
}
