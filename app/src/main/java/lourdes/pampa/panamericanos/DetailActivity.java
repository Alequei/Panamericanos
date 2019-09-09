package lourdes.pampa.panamericanos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = DetailActivity.class.getSimpleName();
    private Integer id;
    private TextView nombreText;
    private TextView descripcionText;
    private TextView historioatext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        nombreText = (TextView)findViewById(R.id.nombre_text);
        descripcionText = (TextView)findViewById(R.id.descripcion_text);
        historioatext = (TextView)findViewById(R.id.historia_text);

        id = getIntent().getExtras().getInt("ID");
        Log.e(TAG, "id:" + id);

        initialize();


    }
    private void initialize() {

        ApiServicePanamericanos service = ApíServicioPanamericanoGenerador.createService(ApiServicePanamericanos.class);

        Call<Panamericanos> call = service.showPanamericanos(id);

        call.enqueue(new Callback<Panamericanos>() {
            @Override
            public void onResponse(Call<Panamericanos> call, Response<Panamericanos> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        Panamericanos panamericanos = response.body();
                        Log.d(TAG, "producto: " + panamericanos);
                        nombreText.setText(panamericanos.getNombredeport());
                        descripcionText.setText(panamericanos.getDescripcion());
                        historioatext.setText(panamericanos.getHistoria());

                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(DetailActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<Panamericanos> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(DetailActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
