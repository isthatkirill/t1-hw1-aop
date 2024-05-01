package isthatkirill.hwoneaop.dto;

import isthatkirill.hwoneaop.dto.marker.OnCreate;
import isthatkirill.hwoneaop.dto.marker.OnUpdate;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.time.Year;

/**
 * @author Kirill Emelyanov
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDto {

    @Null(groups = {OnCreate.class, OnUpdate.class})
    Long id;

    @NotBlank(message = "Book title cannot be blank", groups = OnCreate.class)
    @Size(message = "Book title must be between 3 and 255 chars", min = 3, max = 255,
            groups = {OnCreate.class, OnUpdate.class})
    String name;

    @NotBlank(message = "Book author cannot be blank", groups = OnCreate.class)
    @Size(message = "Book title must be between 3 and 255 chars", min = 3, max = 255,
            groups = {OnCreate.class, OnUpdate.class})
    String author;

    @NotBlank(message = "Book publisher cannot be blank", groups = OnCreate.class)
    @Size(message = "Book publisher must be between 3 and 255 chars", min = 3, max = 255,
            groups = {OnCreate.class, OnUpdate.class})
    String publisher;

    @NotBlank(message = "Book genre cannot be blank", groups = OnCreate.class)
    @Size(message = "Book genre must be between 3 and 128 chars", min = 3, max = 128,
            groups = {OnCreate.class, OnUpdate.class})
    String genre;

    @NotNull(message = "Year of publication cannot be null", groups = OnCreate.class)
    @Min(value = 1454, message = "Year of publication must be in the range from 1454 (first book) to 2024",
            groups = {OnCreate.class, OnUpdate.class})
    @Max(value = 2024, message = "Year of publication must be in the range from 1454 (first book) to 2024",
            groups = {OnCreate.class, OnUpdate.class})
    Integer yearOfPublication;

    @NotNull(message = "Number of pages cannot be null", groups = OnCreate.class)
    @Positive(message = "Number of pages must be positive", groups = {OnCreate.class, OnUpdate.class})
    Integer pages;

}
