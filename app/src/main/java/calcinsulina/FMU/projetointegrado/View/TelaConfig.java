package calcinsulina.FMU.projetointegrado.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import calcinsulina.FMU.projetointegrado.R;

public class TelaConfig extends AppCompatActivity {

    MainActivity ma;

    public TelaConfig(MainActivity ma){
        this.ma = ma;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_config);
    }
}
