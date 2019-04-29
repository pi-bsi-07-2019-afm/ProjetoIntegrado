package calcinsulina.FMU.projetointegrado.View;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import calcinsulina.FMU.projetointegrado.R;

public class SplashScreen {

    MainActivity act;
    TelaPrincipal tela_principal;

    public SplashScreen(MainActivity act, TelaPrincipal tela_principal){
        this.act = act;
        this.tela_principal = tela_principal;
    }
    public void CarregarTela() {
        tela_principal = new TelaPrincipal(act);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
//                 tela_principal.CarregarTela();
            }
        }, 5000);
    }
}
