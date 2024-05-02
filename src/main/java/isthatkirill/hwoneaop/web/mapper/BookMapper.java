package isthatkirill.hwoneaop.web.mapper;

import isthatkirill.hwoneaop.model.Book;
import isthatkirill.hwoneaop.web.dto.BookDto;
import org.mapstruct.Mapper;

/**
 * @author Kirill Emelyanov
 */

@Mapper(componentModel = "spring")
public interface BookMapper extends Mappable<Book, BookDto> {
}
