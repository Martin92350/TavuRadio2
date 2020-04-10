package com.esiea.tavuradio;

import java.util.List;

public class RestActuResponse {

    private Integer count;
    private String URL ;
    private List<Actu> results;

    public Integer getCount() {
        return count;
    }

    public String getURL() {
        return URL;
    }

    public List<Actu> getResults() {
        return results;
    }
}
