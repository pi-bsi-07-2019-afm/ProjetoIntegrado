package calcinsulina.FMU.projetointegrado.View;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import calcinsulina.FMU.projetointegrado.R;

public class TelaPrincipal extends AppCompatActivity {

    MainActivity act;
    ImageView btnCalc, btnConfig, btnReport;

    public TelaPrincipal() {
        this.act = act;
    }

    public void CarregarTela(){
        act.setContentView(R.layout.tela_principal);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_principal);
        //Remove a barra de título
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btnCalc = findViewById(R.id.btnCalc);
        btnConfig = findViewById(R.id.btnConfig);
        btnReport = findViewById(R.id.btnReport);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TelaPrincipal.this, TelaCalculadora.class);
                startActivity(intent);

            }
        });
        btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaPrincipal.this, TelaConfig.class);
                startActivity(intent);
            }
        });

        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaPrincipal.this, TelaCadastro.class);
                startActivity(intent);
            }
        });

    }

}
