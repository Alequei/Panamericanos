package lourdes.pampa.panamericanos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiServicePanamericanos {
    String API_BASE_URL = "https://deportes-panamericanos-nunez.herokuapp.com/api/";
    @GET("panamericanos/")
    Call<List<Panamericanos>> getpanamericano();

    @GET("panamericanos/{id}")
    Call<Panamericanos> showPanamericanos(@Path("id") Integer id);

}
