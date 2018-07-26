package id.amoled.newsgits.Model;

import java.util.List;

public class WebSite {

    private List<Articles> articles;


    public WebSite(){

    }

    public WebSite(List<Articles> articles) {

        this.articles = articles;
    }

    public List<Articles> getArticles() {
        return articles;
    }

}
