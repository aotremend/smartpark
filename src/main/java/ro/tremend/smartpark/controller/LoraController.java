package ro.tremend.smartpark.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * Created by Andrei Olteanu on 23-Sep-17.
 */
@RestController
public class LoraController {

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
            System.out.println("HERE>>> " + decodedPayload);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
