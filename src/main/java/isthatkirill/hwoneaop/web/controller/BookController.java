package isthatkirill.hwoneaop.web.controller;

import isthatkirill.hwoneaop.model.Book;
import isthatkirill.hwoneaop.service.BookService;
import isthatkirill.hwoneaop.web.dto.BookDto;
import isthatkirill.hwoneaop.web.dto.marker.OnCreate;
import isthatkirill.hwoneaop.web.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author Kirill Emelyanov
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
    public ResponseEntity<List<BookDto>> getBooksWithFilters(@RequestParam Map<String, String> params) {
        CompletableFuture<List<Book>> result = bookService.getBooksWithFilters(params);
        return ResponseEntity
                .ok(bookMapper.toDto(result.join()));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long bookId) {
        CompletableFuture<Book> result = bookService.getBookById(bookId);
        return ResponseEntity
                .ok(bookMapper.toDto(result.join()));
    }

    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody @Validated(OnCreate.class) BookDto bookDto) {
        Book book = bookMapper.toEntity(bookDto);
        CompletableFuture<Book> result = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookMapper.toDto(result.join()));
    }

}
