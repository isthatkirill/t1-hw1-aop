package isthatkirill.hwoneaop.repository.impl;

import isthatkirill.hwoneaop.model.Book;
import isthatkirill.hwoneaop.repository.CriteriaBookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @author Kirill Emelyanov
 */

@Slf4j
public class CriteriaBookRepositoryImpl implements CriteriaBookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> findBooksWithFilters(Map<String, String> params) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = cb.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);
        Predicate predicate = cb.conjunction();

        Integer from = parseIntSafeOrZero(params.remove("from"));
        Integer max = parseIntSafeOrZero(params.remove("max"));

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            log.error(key);
            if (root.getModel().getAttribute(key) != null) {
                predicate = cb.and(predicate, root.get(key).in(value));
            } else {
                log.warn("Field {} does not exists.", key);
            }
        }

        return entityManager
                .createQuery(query.select(root).where(predicate))
                .setFirstResult(from)
                .setMaxResults(max == 0 ? Integer.MAX_VALUE : max)
                .getResultList();
    }

    private Integer parseIntSafeOrZero(String sInt) {
        try {
            return Integer.parseInt(sInt);
        } catch (NumberFormatException ignored) {
        }
        return 0;
    }

}
