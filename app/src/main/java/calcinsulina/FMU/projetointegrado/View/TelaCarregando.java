package calcinsulina.FMU.projetointegrado.View;

import android.os.Handler;

import calcinsulina.FMU.projetointegrado.Model.Calculo;
import calcinsulina.FMU.projetointegrado.R;

public class TelaCarregando {

    MainActivity act;
    TelaPrincipal tela_principal;
    Handler handler = new Handler();

    public TelaCarregando(MainActivity act, TelaPrincipal tela_principal) {
        this.act = act;
        this.tela_principal = tela_principal;
    }

    public void CarregarTela(final Calculo objCalculo) {
        act.setContentView(R.layout.tela_carregando);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                act.tela_resultado.CarregarTela(objCalculo);
            }
        }, 3000);

    }
}
