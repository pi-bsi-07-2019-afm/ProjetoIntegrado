package calcinsulina.FMU.projetointegrado.View;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;
import java.util.ArrayList;
import calcinsulina.FMU.projetointegrado.Model.Alimento;
import calcinsulina.FMU.projetointegrado.Model.Calculo;
import calcinsulina.FMU.projetointegrado.Model.DAO;
import calcinsulina.FMU.projetointegrado.Model.TipoMedida;
import calcinsulina.FMU.projetointegrado.Model.Usuario;

public class MainActivity extends Activity {
    private ArrayList<Usuario> aUsuario = new ArrayList<>();
    private ArrayList<Calculo> aCalculo = new ArrayList<>();
    private ArrayList<TipoMedida> aTipoMedida = new ArrayList<>();
    private ArrayList<Alimento> aAlimento = new ArrayList<>();
    TelaPrincipal tela_principal;
    TelaCalculadora tela_calculadora;
    TelaConfig tela_config;
    TelaCadastro tela_cadastro;
    private DAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao = new DAO(this);

        //Inserindo Valores do DB para as ArrayList
        aAlimento = dao.recuperaAlimentos();
        aCalculo = dao.recuperaCalculo();
        aTipoMedida = dao.recuperaTipoMedida();
        aUsuario = dao.recuperaUsuarios();
    }

    @Override
    protected void onStop(){
        super.onStop();
        dao.limpaTabela();
        dao.insereAlimento(aAlimento);
        dao.insereCalculo(aCalculo);
        dao.insereTipoMedida(aTipoMedida);
        dao.insereUsuario(aUsuario);
    }

    protected void onRestart(){
        super.onRestart();
    }

    protected void onDestroy(){
        super.onDestroy();
    }

    public ArrayList<Usuario> getaUsuario(){
        return aUsuario;
    }
    public ArrayList<Calculo> getaCalculo(){
        return aCalculo;
    }
    public ArrayList<TipoMedida> getaTipoMedida(){
        return aTipoMedida;
    }
    public ArrayList<Alimento> getaAlimento(){
        return aAlimento;
    }
}
