package calcinsulina.FMU.projetointegrado.View;

import android.os.Handler;

import calcinsulina.FMU.projetointegrado.R;

public class SplashScreen {

    MainActivity act;
    TelaPrincipal tela_principal;

    public SplashScreen(MainActivity act, TelaPrincipal tela_principal){
        this.act = act;
        this.tela_principal = tela_principal;
    }
    public void CarregarTela() {
        act.setContentView(R.layout.splash_screen);
        tela_principal = new TelaPrincipal(act);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                 act.tela_principal.CarregarTela();
            }
        }, 5000);
    }
}
