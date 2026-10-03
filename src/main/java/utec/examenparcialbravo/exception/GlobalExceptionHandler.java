package utec.examenparcialbravo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class GlobalExceptionHandler {

    @ExceptionHandler({AlreadyRequestedException.class})
    public ResponseEntity<String> handleAlreadyRequestedException(Exception e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(409);
        problemDetail.setTitle("Serve Error");
        problemDetail.setDetail(e.getMessage());
        return new ResponseEntity<>(problemDetail.toString(), HttpStatus.CONFLICT);
    }
}
