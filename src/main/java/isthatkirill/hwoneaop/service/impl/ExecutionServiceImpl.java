package isthatkirill.hwoneaop.service.impl;

import isthatkirill.hwoneaop.model.Execution;
import isthatkirill.hwoneaop.repository.ExecutionRepository;
import isthatkirill.hwoneaop.service.ExecutionService;
import isthatkirill.hwoneaop.web.controller.handler.exception.EntityNotFoundException;
import isthatkirill.hwoneaop.web.dto.ExecutionSummary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @author Kirill Emelyanov
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ExecutionServiceImpl implements ExecutionService {

    private final ExecutionRepository executionRepository;

    @Async
    @Override
    public void save(Execution execution) {
        executionRepository.save(execution);
    }

    @Override
    @Async
    public CompletableFuture<Execution> getExecutionById(Long executionId) {
        return CompletableFuture.completedFuture(checkIfExecutionExistsAndGet(executionId));
    }

    @Override
    @Async
    public CompletableFuture<ExecutionSummary> getMethodSummary(String methodName, String className) {
        return CompletableFuture.completedFuture(
                executionRepository.getMethodExecutionSummary(methodName, className)
                .orElseThrow(() -> new EntityNotFoundException(ExecutionSummary.class))
        );
    }


    private Execution checkIfExecutionExistsAndGet(Long executionId) {
        return executionRepository.findById(executionId)
                .orElseThrow(() -> new EntityNotFoundException(Execution.class, executionId));
    }


}
