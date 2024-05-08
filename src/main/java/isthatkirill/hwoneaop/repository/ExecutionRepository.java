package isthatkirill.hwoneaop.repository;

import isthatkirill.hwoneaop.model.Execution;
import isthatkirill.hwoneaop.web.dto.ExecutionSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author Kirill Emelyanov
 */

public interface ExecutionRepository extends JpaRepository<Execution, Long> {

    @Query("select new isthatkirill.hwoneaop.web.dto.ExecutionSummary(" +
            "e.methodName, count(e), min(e.millisTook), avg(e.millisTook), max(e.millisTook), (cast(sum(case when e.isSuccess = true then 1 else 0 end) as double) / count(e)) * 100) " +
            "from Execution e " +
            "where e.methodName = :methodName and e.className = :className " +
            "group by e.methodName, e.className")
    Optional<ExecutionSummary> getMethodExecutionSummary(
            @Param("methodName") String methodName, @Param("className") String className
    );



}
