package isthatkirill.hwoneaop.service;

import isthatkirill.hwoneaop.model.Book;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author Kirill Emelyanov
 */

public interface BookService {

    CompletableFuture<Book> addBook(Book book);

    CompletableFuture<Book> getBookById(Long id);

    CompletableFuture<List<Book>> getBooksWithFilters(Map<String, String> params);

}
