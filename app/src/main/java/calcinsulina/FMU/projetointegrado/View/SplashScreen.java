package calcinsulina.FMU.projetointegrado.View;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import calcinsulina.FMU.projetointegrado.R;

public class SplashScreen extends AppCompatActivity {

    MainActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Remove a barra de título
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                ma.tela_cadastro.CarregarTela();
                finish();
            }
        }, 5000);
    }
}
