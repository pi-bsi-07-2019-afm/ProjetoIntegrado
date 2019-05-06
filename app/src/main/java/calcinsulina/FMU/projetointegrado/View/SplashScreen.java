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

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if(act.getaUsuario().isEmpty()){
                    act.tela_cadastro.CarregarTela();
                }else{
                    act.tela_principal.CarregarTela();
                }

            }
        }, 1500);
    }
}
