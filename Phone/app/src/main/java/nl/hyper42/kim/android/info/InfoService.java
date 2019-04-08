package nl.hyper42.kim.android.info;

import nl.hyper42.kim.android.generated.info.FlightInfo;
import retrofit2.Call;
import retrofit2.http.GET;

public interface InfoService {
    @GET("flightinfo")
    Call<FlightInfo> getFlightInfo();
}
