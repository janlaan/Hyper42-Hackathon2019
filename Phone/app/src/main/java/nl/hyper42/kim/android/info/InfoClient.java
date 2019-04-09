package nl.hyper42.kim.android.info;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoClient {
    /**
     * URLS
     */
    private static final String ROOT_URL = "http://localhost/";

    /**
     * Get Retrofit Instance
     */
    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    public static InfoService getInfoService() {
        return getRetrofitInstance().create(InfoService.class);
    }

}
