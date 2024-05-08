package isthatkirill.hwoneaop.service;

import isthatkirill.hwoneaop.model.Execution;

import java.util.concurrent.CompletableFuture;

/**
 * @author Kirill Emelyanov
 */

public interface ExecutionService {

    void save(Execution execution);

    CompletableFuture<Execution> getExecutionById(Long executionId);
}
