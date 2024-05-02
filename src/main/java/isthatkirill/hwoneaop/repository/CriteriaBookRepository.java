package isthatkirill.hwoneaop.repository;

import isthatkirill.hwoneaop.model.Book;

import java.util.List;
import java.util.Map;

/**
 * @author Kirill Emelyanov
 */

public interface CriteriaBookRepository {

    List<Book> findBooksWithFilters(Map<String, String> params);

}
