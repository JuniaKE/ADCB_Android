package co.ke.meritsystems.adcb_service_app.models;

import java.util.List;

public class Subcounty {
    public int id;
    public String sub_county_name;
    public int sub_county_requests;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSub_county_name() {
        return sub_county_name;
    }

    public void setSub_county_name(String sub_county_name) {
        this.sub_county_name = sub_county_name;
    }

    public int getSub_county_requests() {
        return sub_county_requests;
    }

    public void setSub_county_requests(int sub_county_requests) {
        this.sub_county_requests = sub_county_requests;
    }



}