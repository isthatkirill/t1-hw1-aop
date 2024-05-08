package isthatkirill.hwoneaop.service.impl;

import isthatkirill.hwoneaop.aspect.annotation.TrackAsyncTime;
import isthatkirill.hwoneaop.aspect.annotation.TrackTime;
import isthatkirill.hwoneaop.model.Book;
import isthatkirill.hwoneaop.repository.BookRepository;
import isthatkirill.hwoneaop.service.BookService;
import isthatkirill.hwoneaop.utils.ThreadUtils;
import isthatkirill.hwoneaop.web.controller.handler.exception.BookAlreadyExistsException;
import isthatkirill.hwoneaop.web.controller.handler.exception.EntityNotFoundException;
import isthatkirill.hwoneaop.web.dto.BookDto;
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
    @TrackAsyncTime
    public CompletableFuture<Book> getBookById(Long bookId) {
        ThreadUtils.sleep(150L);
        return CompletableFuture.completedFuture(checkIfBookExistsAndGet(bookId));
    }

    @Async
    @Override
    @TrackAsyncTime
    public CompletableFuture<List<Book>> getBooksWithFilters(Map<String, String> params) {
        ThreadUtils.sleep(200L);
        return CompletableFuture.completedFuture(bookRepository.findBooksWithFilters(params));
    }

    @Async
    @Override
    @TrackAsyncTime
    public void deleteBook(Long bookId) {
        ThreadUtils.sleep(100L);
        checkIfBookExists(bookId);
        bookRepository.deleteById(bookId);
    }

    @Override
    @TrackTime
    public Book addBook(Book book) {
        ThreadUtils.sleep(200L);
        checkIfBookAlreadyExists(book.getIsbn());
        return bookRepository.save(book);
    }

    @Override
    @TrackTime
    public Book updateBook(BookDto bookDto, Long bookId) {
        ThreadUtils.sleep(200L);
        Book book = checkIfBookExistsAndGet(bookId);
        if (bookDto.getIsbn() != null) {
            checkIfBookAlreadyExists(bookDto.getIsbn());
            book.setAuthor(bookDto.getIsbn());
        }
        if (bookDto.getName() != null) book.setName(bookDto.getName());
        if (bookDto.getAuthor() != null) book.setAuthor(bookDto.getAuthor());
        if (bookDto.getGenre() != null) book.setGenre(bookDto.getGenre());
        if (bookDto.getPages() != null) book.setPages(bookDto.getPages());
        if (bookDto.getPublisher() != null) book.setPublisher(bookDto.getPublisher());
        if (bookDto.getYearOfPublication() != null) book.setYearOfPublication(bookDto.getYearOfPublication());

        return bookRepository.save(book);
    }

    private void checkIfBookAlreadyExists(String isbn) {
        if (bookRepository.existsByIsbn(isbn)) {
            throw new BookAlreadyExistsException(isbn);
        }
    }

    private Book checkIfBookExistsAndGet(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException(Book.class, bookId));
    }

    private void checkIfBookExists(Long bookId) {
        if (bookRepository.existsById(bookId)) {
            return;
        }
        throw new EntityNotFoundException(Book.class, bookId);
    }


}
