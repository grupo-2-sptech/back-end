package collectiva.org.collecta.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private Timestamp timestamp;
    private int status;
    private String error;
    private List<String> message;
    private String path;

    private ErrorResponse() {
        this.message = new ArrayList<>();
    }
}
