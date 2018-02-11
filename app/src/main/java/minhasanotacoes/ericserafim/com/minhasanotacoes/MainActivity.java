package minhasanotacoes.ericserafim.com.minhasanotacoes;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity {

    private EditText texto;
    private ImageView botao;
    private static final String ARQUIVO_NOME = "anotacao.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texto = (EditText) findViewById(R.id.textoId);
        botao = (ImageView) findViewById(R.id.botaoSalvarId);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gravarNoArquivo(texto.getText().toString());
                Toast.makeText(MainActivity.this, "Anotação salva com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });

        String conteudo = lerArquivo();

        if (conteudo != null)
            texto.setText(conteudo);
    }

    private void gravarNoArquivo(String texto) {
        try {
            OutputStreamWriter writer = new OutputStreamWriter(openFileOutput(ARQUIVO_NOME, Context.MODE_PRIVATE));
            writer.write(texto);
            writer.close();
        } catch (IOException e) {
            Log.v("MainActivity", e.toString());
        }
    }

    private String lerArquivo() {
        String resultado = "";


        try {
            InputStream arquivo = openFileInput(ARQUIVO_NOME);

            if (arquivo != null) {
                InputStreamReader reader = new InputStreamReader(arquivo);
                BufferedReader buffer = new BufferedReader(reader);
                String linha = "";
                while ((linha = buffer.readLine()) != null) {
                    resultado += linha;
                }
            }

        }catch (IOException e) {
            Log.v("MainActivity", e.toString());
        }

        return resultado;
    }
}
