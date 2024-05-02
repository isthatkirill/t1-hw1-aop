package isthatkirill.hwoneaop.service.impl;

import isthatkirill.hwoneaop.model.Book;
import isthatkirill.hwoneaop.repository.BookRepository;
import isthatkirill.hwoneaop.service.BookService;
import isthatkirill.hwoneaop.utils.ThreadUtils;
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
    public CompletableFuture<Book> addBook(Book book) {
        ThreadUtils.sleep(1000L);
        log.info("Executed by thread --> {}", Thread.currentThread().getName());
        return CompletableFuture.completedFuture(bookRepository.save(book));
    }

    @Async
    @Override
    public CompletableFuture<Book> getBookById(Long id) {
        ThreadUtils.sleep(1000L);
        log.info("Executed by thread --> {}", Thread.currentThread().getName());
        return CompletableFuture.completedFuture(bookRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new));

    }

    @Async
    @Override
    public CompletableFuture<List<Book>> getBooksWithFilters(Map<String, String> params) {
        ThreadUtils.sleep(1000L);
        log.info("Executed by thread --> {}", Thread.currentThread().getName());
        return CompletableFuture.completedFuture(bookRepository.findBooksWithFilters(params));
    }


}
