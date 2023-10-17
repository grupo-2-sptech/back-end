package collectiva.org.collecta.exception.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;


@Data
@AllArgsConstructor
public class MessageErrorResponse {
    private Timestamp timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

}
