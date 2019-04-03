package calcinsulina.FMU.projetointegrado;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        //Remove a barra de título
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


    }
}
