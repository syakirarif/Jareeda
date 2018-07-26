package id.amoled.newsgits.Common;

import id.amoled.newsgits.Interface.NewsService;
import id.amoled.newsgits.Remote.RetrofitClient;

public class Common {

    private static final String BASE_URL = "https://newsapi.org/";
    public static final String API_KEY = "28d0d09e21ea41d9b824a6d3b1c2192f";

    public static NewsService getNewsService() {
        return RetrofitClient.getClient(BASE_URL).create(NewsService.class);
    }

}
