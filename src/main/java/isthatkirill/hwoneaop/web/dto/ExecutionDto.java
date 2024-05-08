package isthatkirill.hwoneaop.web.dto;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/**
 * @author Kirill Emelyanov
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExecutionDto {

    Long id;
    String methodName;
    String className;
    Boolean isSuccess;
    Long millisTook;
    LocalDateTime executedAt;

    @JsonRawValue
    Object args;

}