package id.co.mdd.databindingexcercise.utils;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtil {
    public static <T> T request(Class<T> service, String baseUrl){
        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(baseUrl)
                            .addConverterFactory(
                                    GsonConverterFactory.create(
                                            new GsonBuilder().setLenient().create()
                                    )

                            )
                            .build();

        return retrofit.create(service);
    }
}
