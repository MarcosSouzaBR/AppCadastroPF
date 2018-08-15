//******************************************************

//Instituto Federal de São Paulo - Campus Sertãozinho

//Disciplina......: M4DADM

//Programação de Computadores e Dispositivos Móveis

//Aluno...........: Marcos Roberto Souza

//******************************************************


package marcossouzabr.projetofinal;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    Button btVoltar, btInserir, btListar;
    private DBHelper dh;
    EditText edNome, edCPF, edIdade, edTelefone, edEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btVoltar = (Button) findViewById(R.id.btVoltar);
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaPrimeiraTela();
            }
        });

    }

    void chamaPrimeiraTela() {
        Intent intent = new Intent();
        intent.setClass(SecondActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    this.dh = new DBHelper(this);

    edNome = (EditText) findViewById(R.id.edNome);

    edCPF = (EditText) findViewById(R.id.edCPF);

    edIdade = (EditText) findViewById(R.id.edIdade);

    edTelefone = (EditText) findViewById(R.id.edTelefone);

    edEmail = (EditText) findViewById(R.id.edEmail);


    btInserir = (Button) findViewById(R.id.btInserir);

    btListar = (Button) findViewById(R.id.btListar);

    btInserir.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onclick (View view){
        if (ednome.gettext().length() > 0 && edcpf.gettext().length() > 0 && edidade.gettext().length() > 0 && edtelefone.gettext().length() > 0 && edemail.gettext().length() > 0) {
            dh.insert(ednome.gettext().tostring(), edcpf.gettext().tostring(), edidade.gettext().tostring(), edtelefone.gettext().tostring(), edemail.gettext().tostring());
            alertdialog.builder adb = new alertdialog.builder(secondactivity.this);
            adb.settitle("sucesso");
            adb.setmessage("cadastro realizado!");
            adb.show();

            ednome.settext("");
            edcpf.settext("");
            edidade.settext("");
            edtelefone.settext("");
            edemail.settext("");
        } else {
            alertdialog.builder adb = new alertdialog.builder(secondactivity.this);
            adb.settitle("erro");
            adb.setmessage("todos os campos devem ser preenchidos!");
            adb.show();

            ednome.settext("");
            edcpf.settext("");
            edidade.settext("");
            edtelefone.settext("");
            edemail.settext("");

        }
    }
    });

    btListar.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View view){
        List<Contato> contatos = null;
        try {
            contatos = dh.queryGetAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (contatos == null) {
            AlertDialog.Builder adb = new AlertDialog.Builder(SecondActivity.this);
            adb.setTitle("Mensagem");
            adb.setMessage("Não há registros cadastrados!");
            adb.show();
            return;
        }
        for (int i = 0; i < contatos.size(); i++) {
            Contato contato = (Contato) contatos.get(i);
            AlertDialog.Builder adb = new AlertDialog.Builder(SecondActivity.this);
            adb.setTitle("Registro " + i);
            adb.setMessage("Nome: " + contato.getNome() + "\nCPF: " + contato.getCpf() + "\nIdade: " + contato.getIdade() + "\nTelefone: " + contato.getTelefone() + "\nE-mail: " + contato.getEmail());
            adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            adb.show();
        }

    }
    });
}

}


