package ro.tremend.smartpark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.tremend.smartpark.model.*;
import ro.tremend.smartpark.repository.ParkingSpotRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Andrei Olteanu on 22-Sep-17.
 */

@RestController
@RequestMapping(value = "/parking")
public class ParkingController {

    @Autowired
    ParkingSpotRepository parkingSpotRepository;

    @PostMapping(value = "/reserve/{parkingSpotId}")
    public FreeParkingSpotResponse reserveParkingSpot(@PathVariable Long parkingSpotId,
                                 @RequestBody ReserveParkingRequest reserveParkingRequest) {

        ParkingSpot parkingSpot = parkingSpotRepository.findOne(parkingSpotId);
        parkingSpot.setState("reserved");
        parkingSpot.setModified(new Date());
        parkingSpot.setUsername(reserveParkingRequest.getUsername());
        System.out.println("Reserving: " + parkingSpot);
        parkingSpotRepository.save(parkingSpot);
        return new FreeParkingSpotResponse(parkingSpot.getId(),
                parkingSpot.getLongitude(), parkingSpot.getLatitude());
    }


    @PostMapping(value = "/claim/{parkingSpotId}")
    public FreeParkingSpotResponse claimParkingSpot(@PathVariable Long parkingSpotId,
                                 @RequestBody ClaimParkingRequest claimParkingRequest) {

        ParkingSpot parkingSpot = parkingSpotRepository.findOne(parkingSpotId);
        parkingSpot.setState("not-free");
        parkingSpot.setModified(new Date());
        parkingSpot.setUsername(claimParkingRequest.getUsername());
        parkingSpotRepository.save(parkingSpot);
        return new FreeParkingSpotResponse(parkingSpot.getId(),
                parkingSpot.getLongitude(), parkingSpot.getLatitude());
    }

    @PostMapping(value = "/free/{parkingSpotId}")
    public void freeParkingSpot(@PathVariable Long parkingSpotId,
                                 @RequestBody FreeParkingRequest freeParkingRequest) {

        ParkingSpot parkingSpot = parkingSpotRepository.findOne(parkingSpotId);
        parkingSpot.setState("available");
        parkingSpot.setModified(new Date());
        parkingSpot.setUsername(freeParkingRequest.getUsername());
        parkingSpotRepository.save(parkingSpot);
    }

    @GetMapping(value = "/list/available")
    public List<FreeParkingSpotResponse> listAvailable() {
        List<ParkingSpot> parkingSpotList = parkingSpotRepository.findAvailable();
        return parkingSpotList.stream()
                .map(parkingSpot ->
                        new FreeParkingSpotResponse(parkingSpot.getId(),
                                parkingSpot.getLongitude(),
                                parkingSpot.getLatitude()))
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/create")
    public void createParkingRequest(@RequestBody CreateParkingRequest createParkingRequest) {
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setLatitude(createParkingRequest.getLatitude());
        parkingSpot.setLongitude(createParkingRequest.getLongitude());
        parkingSpot.setState(createParkingRequest.getState());
        parkingSpot.setModified(new Date());

        parkingSpotRepository.save(parkingSpot);
    }
}
