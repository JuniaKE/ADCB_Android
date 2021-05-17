package co.ke.meritsystems.adcb_service_app.models;

public class SubCountyWards {
    public int id;
    public String ward_name;
    public int ward_request_count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWard_name() {
        return ward_name;
    }

    public void setWard_name(String ward_name) {
        this.ward_name = ward_name;
    }

    public int getWard_request_count() {
        return ward_request_count;
    }

    public void setWard_request_count(int ward_request_count) {
        this.ward_request_count = ward_request_count;
    }
}