package ro.tremend.smartpark.model;

/**
 * Created by Andrei Olteanu on 22-Sep-17.
 */
public class CreateParkingRequest {

    private Double latitude;

    private Double longitude;

    private String state;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
