package calcinsulina.FMU.projetointegrado.View;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import calcinsulina.FMU.projetointegrado.Model.Usuario;
import calcinsulina.FMU.projetointegrado.R;

public class TelaPrincipal {

    MainActivity act;
    ImageView btnCalc, btnConfig, btnReport;
    TelaCadastro tela_cadastro;
    TelaCalculadora tela_calculadora;
    TelaConfig tela_config;
    TelaCarregando tela_carregando;
    SplashScreen splashScreen;
    String telaAnterior;

    public TelaPrincipal(MainActivity act, String telaAnterior) {
        this.act = act;
        this.telaAnterior = telaAnterior;
    }

    public void CarregarTela(){
        act.setContentView(R.layout.tela_principal);
        btnCalc = act.findViewById(R.id.btnCalc);
        btnConfig = act.findViewById(R.id.btnConfig);
        btnReport = act.findViewById(R.id.btnReport);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                act.tela_calculadora.CarregarTela();
            }
        });
        btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                act.tela_config.CarregarTela();
            }
        });

//         Verificando se já existe um cadastro do Usuário e a tela anterios é a SplashScreen
        if(!act.getaUsuario().isEmpty() && telaAnterior.equalsIgnoreCase("SplashScreen")){
            String username = usernameSearch(act.getaUsuario());
            Toast.makeText(act, "Bem Vindo de Volta " + username, Toast.LENGTH_SHORT).show();
        }

     }
     public void setTelaCalculadora(TelaCalculadora tela_calculadora){

        this.tela_calculadora = tela_calculadora;
     }
     public void setTelaConfig(TelaConfig tela_config){

        this.tela_config = tela_config;
     }
     public void setTelaCadastro(TelaCadastro tela_cadastro){

        this.tela_cadastro = tela_cadastro;
     }

    public void setTelaCarregando(TelaCarregando tela_carregando){

        this.tela_carregando = tela_carregando;
    }

    public void setSplashScreen(SplashScreen splashScreen){
        this.splashScreen = splashScreen;
    }

    public String usernameSearch(ArrayList<Usuario> array){
        return array.get(0).getNome();
    }

}

