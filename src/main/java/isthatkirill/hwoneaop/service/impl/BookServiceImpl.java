package isthatkirill.hwoneaop.service.impl;

import isthatkirill.hwoneaop.model.Book;
import isthatkirill.hwoneaop.repository.BookRepository;
import isthatkirill.hwoneaop.service.BookService;
import isthatkirill.hwoneaop.utils.ThreadUtils;
import isthatkirill.hwoneaop.web.dto.BookDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author Kirill Emelyanov
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Async
    @Override
    public CompletableFuture<Book> getBookById(Long bookId) {
        ThreadUtils.sleep(500L);
        log.info("Executed by thread --> {}", Thread.currentThread().getName());
        return CompletableFuture.completedFuture(checkIfBookExistsAndGet(bookId));
    }

    @Async
    @Override
    public CompletableFuture<List<Book>> getBooksWithFilters(Map<String, String> params) {
        ThreadUtils.sleep(500L);
        log.info("Executed by thread --> {}", Thread.currentThread().getName());
        return CompletableFuture.completedFuture(bookRepository.findBooksWithFilters(params));
    }

    @Override
    @Async
    public void deleteBook(Long bookId) {
        ThreadUtils.sleep(500L);
        log.info("Executed by thread --> {}", Thread.currentThread().getName());
        checkIfBookExists(bookId);
        bookRepository.deleteById(bookId);
    }

    @Override
    public Book addBook(Book book) {
        ThreadUtils.sleep(500L);
        log.info("Executed by thread --> {}", Thread.currentThread().getName());
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(BookDto bookDto, Long bookId) {
        ThreadUtils.sleep(500L);
        log.info("Executed by thread --> {}", Thread.currentThread().getName());
        Book book = checkIfBookExistsAndGet(bookId);

        if (bookDto.getName() != null) book.setName(bookDto.getName());
        if (bookDto.getAuthor() != null) book.setAuthor(bookDto.getAuthor());
        if (bookDto.getGenre() != null) book.setGenre(bookDto.getGenre());
        if (bookDto.getPages() != null) book.setPages(bookDto.getPages());
        if (bookDto.getPublisher() != null) book.setPublisher(bookDto.getPublisher());
        if (bookDto.getYearOfPublication() != null) book.setYearOfPublication(bookDto.getYearOfPublication());

        return bookRepository.save(book);
    }

    private Book checkIfBookExistsAndGet(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(bookId)));
    }

    private void checkIfBookExists(Long bookId) {
        if (bookRepository.existsById(bookId)) {
            return;
        }
        throw new EntityNotFoundException(String.valueOf(bookId));
    }


}
