package calcinsulina.FMU.projetointegrado.View;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import calcinsulina.FMU.projetointegrado.R;

public class TelaCarregando extends AppCompatActivity {

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_carregando);
        //Remove a barra de título
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(TelaCarregando.this, TelaCalculadora.class);
                System.out.println("SUCESSO!");
                startActivity(intent);
            }
        }, 15000);





    }
}
