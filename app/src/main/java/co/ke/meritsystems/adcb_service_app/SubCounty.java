package co.ke.meritsystems.adcb_service_app;

public class SubCounty {
    private String sub_county_name;
    private int sub_county_requests;

    public SubCounty(String sub_county_name, int sub_county_requests) {
        this.sub_county_name = sub_county_name;
        this.sub_county_requests = sub_county_requests;
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
