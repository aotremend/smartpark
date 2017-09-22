package ro.tremend.smartpark.model;

/**
 * Created by Andrei Olteanu on 22-Sep-17.
 */
public class ClaimParkingRequest {

    private String username;

    private Double latitude;

    private Double longitude;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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
}
