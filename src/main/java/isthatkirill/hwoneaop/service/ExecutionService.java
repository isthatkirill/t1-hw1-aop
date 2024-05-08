package isthatkirill.hwoneaop.service;

import isthatkirill.hwoneaop.model.Execution;
import isthatkirill.hwoneaop.web.dto.ExecutionSummary;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author Kirill Emelyanov
 */

public interface ExecutionService {

    void save(Execution execution);

    CompletableFuture<Execution> getExecutionById(Long executionId);

    CompletableFuture<ExecutionSummary> getMethodSummary(String methodName, String className);

    CompletableFuture<List<Execution>> getAll();

}
