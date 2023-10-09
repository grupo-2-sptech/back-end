package collectiva.org.collecta.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseList {
    private Timestamp timestamp;
    private int status;
    private String error;
    private List<String> messages;
    private String path;

}
