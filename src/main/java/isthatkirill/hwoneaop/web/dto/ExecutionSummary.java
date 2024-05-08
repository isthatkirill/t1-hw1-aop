package isthatkirill.hwoneaop.web.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * @author Kirill Emelyanov
 */

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExecutionSummary {

    String name;
    Long executions;
    Long min;
    Double avg;
    Long max;
    Double successRate;

}
