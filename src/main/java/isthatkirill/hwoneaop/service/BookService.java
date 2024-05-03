package isthatkirill.hwoneaop.service;

import isthatkirill.hwoneaop.model.Book;
import isthatkirill.hwoneaop.web.dto.BookDto;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author Kirill Emelyanov
 */

public interface BookService {

    Book addBook(Book book);

    CompletableFuture<Book> getBookById(Long id);

    CompletableFuture<List<Book>> getBooksWithFilters(Map<String, String> params);

    Book updateBook(BookDto bookDto, Long bookId);

    void deleteBook(Long bookId);
}
