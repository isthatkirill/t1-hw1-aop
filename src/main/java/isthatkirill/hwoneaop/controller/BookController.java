package isthatkirill.hwoneaop.controller;

import isthatkirill.hwoneaop.dto.BookDto;
import isthatkirill.hwoneaop.dto.marker.OnCreate;
import isthatkirill.hwoneaop.mapper.BookMapper;
import isthatkirill.hwoneaop.model.Book;
import isthatkirill.hwoneaop.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kirill Emelyanov
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody @Validated(OnCreate.class) BookDto bookDto) {
        Book book = bookMapper.toEntity(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookMapper.toDto(bookService.addBook(book)));
    }


}
