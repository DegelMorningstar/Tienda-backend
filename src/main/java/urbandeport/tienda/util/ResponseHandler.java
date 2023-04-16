package urbandeport.tienda.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<>();
        map.put("estatus", status.value());
        map.put("mensaje", message);
        map.put("resultado", responseObj);
        map.put("salt", new Date());

        return new ResponseEntity<Object>(map,status);
    }
}
