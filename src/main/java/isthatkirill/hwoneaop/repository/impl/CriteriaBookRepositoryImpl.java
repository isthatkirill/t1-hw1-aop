package isthatkirill.hwoneaop.repository.impl;

import isthatkirill.hwoneaop.model.Book;
import isthatkirill.hwoneaop.repository.CriteriaBookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Map;

/**
 * @author Kirill Emelyanov
 */

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

        var attributes = root.getModel().getDeclaredAttributes();

        for (var attribute : attributes) {
            String key = attribute.getName();
            String value = params.get(key);
            if (value != null) {
                predicate = cb.and(predicate, root.get(key).in(value));
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
