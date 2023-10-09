package collectiva.org.collecta.exception.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageErrorResponseList {
    private Timestamp timestamp;
    private int status;
    private String error;
    private List<String> messages;
    private String path;

}
