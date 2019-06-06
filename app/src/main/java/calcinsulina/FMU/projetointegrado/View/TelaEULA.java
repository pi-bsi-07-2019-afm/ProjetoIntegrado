package calcinsulina.FMU.projetointegrado.View;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import calcinsulina.FMU.projetointegrado.R;

public class TelaEULA extends Activity {

    Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_eula);

    btnVoltar = findViewById(R.id.btnVoltar);

    btnVoltar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });
    }
}
