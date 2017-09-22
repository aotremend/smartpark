package ro.tremend.smartpark.model;

/**
 * Created by Andrei Olteanu on 22-Sep-17.
 */
public class FreeParkingSpotResponse {

    private Long parkingSpotId;

    private Double longitude;

    private Double latitude;

    public FreeParkingSpotResponse(Long parkingSpotId, Double longitude, Double latitude) {
        this.parkingSpotId = parkingSpotId;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Long getParkingSpotId() {
        return parkingSpotId;
    }

    public void setParkingSpotId(Long parkingSpotId) {
        this.parkingSpotId = parkingSpotId;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
