package lourdes.pampa.panamericanos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView panamericanosList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        panamericanosList = findViewById(R.id.list_panamericanos);
        panamericanosList.setLayoutManager(new LinearLayoutManager(this));
        panamericanosList.setAdapter(new AdaptadorPanamericano());
        initialize();

    }

    private void initialize() {
        ApiServicePanamericanos service = ApíServicioPanamericanoGenerador.createService(ApiServicePanamericanos.class);
        Call<List<Panamericanos>> call = service.getpanamericano();
        call.enqueue(new Callback<List<Panamericanos>>() {
            @Override
            public void onResponse(Call<List<Panamericanos>> call, Response<List<Panamericanos>> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);
                    if (response.isSuccessful()) {
                        List<Panamericanos> panamericanos= response.body();
                        Log.d(TAG, "juegos: " + panamericanos);
                        AdaptadorPanamericano adapter = (AdaptadorPanamericano)panamericanosList.getAdapter();

                        adapter.setPanamericanos(panamericanos);
                        adapter.notifyDataSetChanged();


                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }
            @Override
            public void onFailure(Call<List<Panamericanos>>call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(getApplicationContext() , t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
