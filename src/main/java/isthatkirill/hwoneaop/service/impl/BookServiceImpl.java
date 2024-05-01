package isthatkirill.hwoneaop.service.impl;

import isthatkirill.hwoneaop.dto.BookDto;
import isthatkirill.hwoneaop.model.Book;
import isthatkirill.hwoneaop.repository.BookRepository;
import isthatkirill.hwoneaop.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Kirill Emelyanov
 */

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

}
