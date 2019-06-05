package calcinsulina.FMU.projetointegrado.View;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import calcinsulina.FMU.projetointegrado.Model.Usuario;
import calcinsulina.FMU.projetointegrado.R;

public class TelaCadastro {

    MainActivity act;
    TextView txtCad;
    Button btnCadastrar;
    EditText edNome, edPeso, edSensibFator, edDataNasc, edEmail;
    CheckBox cbEULA;
    ImageView FatorSensibilHint, EULAread, btnCalendar;
    CalendarView calendar;
    String telaAnterior = "MainActivity", dataNasc = "-";


    public TelaCadastro(MainActivity act, String telaAnterior) {
        this.act = act;
        this.telaAnterior = telaAnterior;
    }

    public void CarregarTela() {
        //act.setContentView(R.layout.header_cadastro);
        act.setContentView(R.layout.tela_cadastro);
        txtCad = act.findViewById(R.id.txtCadastro);
        btnCadastrar = act.findViewById(R.id.btnCadastrar);
        edNome = act.findViewById(R.id.edNome);
        edPeso = act.findViewById(R.id.edPeso);
        edSensibFator = act.findViewById(R.id.edSensibFator);
        cbEULA = act.findViewById(R.id.cbEULA);
        edEmail = act.findViewById(R.id.edEmail);
        edDataNasc = act.findViewById(R.id.edDataNasc);
        FatorSensibilHint = act.findViewById(R.id.fatorSensibilHint);
        EULAread = act.findViewById(R.id.EULARead);
//        calendar = act.findViewById(R.id.calendarView);

        if (telaAnterior.equalsIgnoreCase("TelaConfig")) {
            txtCad.setText("Alterar Cadastro");
            btnCadastrar.setText("Salvar");
            edNome.setText(act.getaUsuario().get(0).getNome());
            edEmail.setText(act.getaUsuario().get(0).getEmail());
            edDataNasc.setText(act.getaUsuario().get(0).getDataNascimento());
            edPeso.setText(String.valueOf(act.getaUsuario().get(0).getPeso()));
            edSensibFator.setText(String.valueOf(act.getaUsuario().get(0).getFatorSensibilidade()));

            cbEULA.setChecked(true);
            // As linhas abaixo tornam a checkbox e ? do EULA invisiveis
            cbEULA.setVisibility(View.GONE);
            EULAread.setVisibility(View.GONE);
        }

        SimpleMaskFormatter SMDataNasc = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher maskDataNasc = new MaskTextWatcher(edDataNasc, SMDataNasc);
        edDataNasc.addTextChangedListener(maskDataNasc);

//        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                edDataNasc.setText(dayOfMonth + "/" + (month+1) + "/" + year);
//            }
//        });


        FatorSensibilHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExibeMensagem("Essa informação é fornecida por seu médico.");
            }
        });

        EULAread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(act, TelaEULA.class);
                act.startActivity(intent);
            }
        });

        try {
            btnCadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(act);
                    String msgErro = "";
                    if (edNome.getText().toString().length() == 0) {
                        msgErro = msgErro.concat("\n• Informar o nome corretamente.");
                    }
                    if (edPeso.getText().length() == 0) {
                        msgErro = msgErro.concat("\n• Informar o peso corretamente.");
                    }
                    if (edDataNasc.getText().length() == 0) {
                        msgErro = msgErro.concat("\n• Informar a data de nascimento.");
                    }
                    if (edSensibFator.getText().length() == 0) {
                        msgErro = msgErro.concat("\n• Informar o fator de sensibilidade.");
                    }
                    if (edEmail.getText().length() == 0 || edEmail.getText().toString().indexOf("@") < 0) {
                        msgErro = msgErro.concat("\n• Informar o E-Mail corretamente.");
                    }
                    if (!cbEULA.isChecked()) {
                        msgErro = msgErro.concat("\n• Aceitar os Termos de Uso.");
                    }
                    if (msgErro.length() > 0) {
                        ExibeMensagem(msgErro);
                    } else {
                        dialogo.setTitle("Atenção");
                        dialogo.setMessage("As informações foram preenchidas corretamente?");
                        dialogo.setNegativeButton("Não", null);
                        dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String nome = edNome.getText().toString();
                                double peso = Double.parseDouble(edPeso.getText().toString());
                                String dataNasc = edDataNasc.getText().toString();
                                double fatorSensibilidade = Double.parseDouble(edSensibFator.getText().toString());
                                String email = edEmail.getText().toString();
                                String pattern = "dd-MM-yyyy";
                                DateFormat df = new SimpleDateFormat(pattern);
                                Date today = Calendar.getInstance().getTime();
                                String dataRegistro = df.format(today);
                                act.aUsuario.clear();
                                act.getaUsuario().add(new Usuario(nome, peso, dataNasc, fatorSensibilidade, email, dataRegistro));
                                Toast.makeText(act, "Feito o cadastro.", Toast.LENGTH_SHORT).show();
                                act.tela_principal.CarregarTela();
                            }
                        });
                        dialogo.show();
                    }
                }
            });
        }catch (RuntimeException re){
            String erro = re.toString();
            ExibeMensagem(erro);
        }
    }

    public void ExibeMensagem(String mensagem) {
        AlertDialog.Builder Dialogo = new AlertDialog.Builder(act);
        Dialogo.setTitle("Atenção");
        Dialogo.setMessage(mensagem);
        Dialogo.setNeutralButton("OK", null);
        Dialogo.show();
    }

    public boolean ValidaDataNasc(String dataNasc){
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int CurrentYear = Integer.parseInt(dateFormat.format(date));

        int Day, Month, Year, valid = 0;
        String[] InsertedDate = dataNasc.split("/");
        Day = Integer.parseInt(InsertedDate[0]);
        Month = Integer.parseInt(InsertedDate[1]);
        Year = Integer.parseInt(InsertedDate[2]);

        if(Day >= 32 && Day <= 0){
            valid = 1;
        }else if(Day >= 29 && Month == 2 && Year % 100 == 0 && Year % 4 != 0){
            valid = 1;
        }else if(Day >= 30 && Month == 2 && Year % 100 != 0 && Year % 4 == 0){
            valid = 1;
        }
        if(Month >= 13 && Month <= 0){
            valid = 1;
        }
        if(Year > CurrentYear && Year <= 0){
            valid = 1;
        }
        if(valid == 1){
            return false;
        }else{
            return true;
        }
    }
}