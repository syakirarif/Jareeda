package id.amoled.newsgits.Interface;

import id.amoled.newsgits.Common.Common;
import id.amoled.newsgits.Model.WebSite;
import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsService {
    @GET("v2/top-headlines?country=id&apiKey=" + Common.API_KEY)
    Call<WebSite> getHeadline();

    @GET("v2/top-headlines?country=id&category=business&apiKey=" + Common.API_KEY)
    Call<WebSite> getBusiness();

    @GET("v2/top-headlines?country=id&category=entertainment&apiKey=" + Common.API_KEY)
    Call<WebSite> getEntertainment();

    @GET("v2/top-headlines?country=id&category=health&apiKey=" + Common.API_KEY)
    Call<WebSite> getHealth();

    @GET("v2/top-headlines?country=id&category=science&apiKey=" + Common.API_KEY)
    Call<WebSite> getScience();

    @GET("v2/top-headlines?country=id&category=sports&apiKey=" + Common.API_KEY)
    Call<WebSite> getSport();

    @GET("v2/top-headlines?country=id&category=technology&apiKey=" + Common.API_KEY)
    Call<WebSite> getTech();
}
