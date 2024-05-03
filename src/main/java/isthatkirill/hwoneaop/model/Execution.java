package isthatkirill.hwoneaop.model;

import isthatkirill.hwoneaop.model.converter.ArgsConverter;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * @author Kirill Emelyanov
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "executions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Execution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String methodName;
    String className;
    Boolean isSuccess;
    Long millisTook;

    @CreationTimestamp
    LocalDateTime executedAt;

    @Convert(converter = ArgsConverter.class)
    Object args;

}
