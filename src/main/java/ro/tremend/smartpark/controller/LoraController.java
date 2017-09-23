package ro.tremend.smartpark.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.tremend.smartpark.model.ParkingSpot;
import ro.tremend.smartpark.repository.ParkingSpotRepository;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by Andrei Olteanu on 23-Sep-17.
 */
@RestController
public class LoraController {

    @Autowired
    ParkingSpotRepository parkingSpotRepository;

    @PostMapping(value = "/lora")
    public void loraCallback(@RequestBody JsonNode jsonNode) {
        String payload_hex = jsonNode.get("DevEUI_uplink").get("payload_hex").textValue();
        byte[] bytes = new byte[0];
        try {
            bytes = Hex.decodeHex(payload_hex.toCharArray());
        } catch (DecoderException e) {
            e.printStackTrace();
        }
        try {
            String decodedPayload = new String(bytes, "UTF-8");
            System.out.println("=============================");
            System.out.println("payload_hex:  " + payload_hex);
            System.out.println("Decoded " + decodedPayload);
            System.out.println("=============================");
            String[] latLong = decodedPayload.split("\\|");
            ParkingSpot parkingSpot = new ParkingSpot();
            parkingSpot.setLatitude(Double.valueOf(latLong[0]));
            parkingSpot.setLongitude(Double.valueOf(latLong[1]));
            parkingSpot.setState("available");
            parkingSpot.setModified(new Date());

            if(parkingSpot.getLatitude().equals(Double.valueOf("0.000000"))) {
                System.out.println("Ignoring parking spot 0.000000");
                return;
            }

            ParkingSpot existing = null;
            Iterator<ParkingSpot> iterator = parkingSpotRepository.findAll().iterator();
            while (iterator.hasNext()) {
                ParkingSpot parkingSpot1 = iterator.next();
                if(almostEqual(parkingSpot.getLatitude(), parkingSpot1.getLatitude())
                        && almostEqual(parkingSpot.getLongitude(), parkingSpot1.getLongitude())) {
                    existing = parkingSpot1;
                    break;
                }
            }

            if(existing != null) {
                existing.setLatitude(parkingSpot.getLatitude());
                existing.setLongitude(parkingSpot.getLongitude());
                existing.setState("available");
                System.out.println("Updating parking spot " + existing.toString());
                parkingSpotRepository.save(existing);
            } else {
                System.out.println("Creating parking spot " + parkingSpot.toString());
                parkingSpotRepository.save(parkingSpot);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static boolean almostEqual(Double a, Double b){
        double eps = 0.000;
        return Math.abs(a-b)<eps;
    }
}
