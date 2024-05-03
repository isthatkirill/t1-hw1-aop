package isthatkirill.hwoneaop.repository;

import isthatkirill.hwoneaop.model.Execution;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Kirill Emelyanov
 */

public interface ExecutionRepository extends JpaRepository<Execution, Long> {
}
