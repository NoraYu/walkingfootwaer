package walking.footwear.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import walking.footwear.model.Product;

import static org.springframework.http.ResponseEntity.status;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value=NotFoundException.class)
    @ResponseBody
    public ResponseEntity<Object> handleNotFound(NotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);

    }

    @ExceptionHandler(value=NullPointerException.class)
    @ResponseBody
    public ResponseEntity<Object> handleNull(NotFoundException ex){
        return new ResponseEntity<>("Record doesn't exist", HttpStatus.OK);

    }

}
