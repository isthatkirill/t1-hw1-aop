package isthatkirill.hwoneaop.repository;

import isthatkirill.hwoneaop.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Kirill Emelyanov
 */

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


}
