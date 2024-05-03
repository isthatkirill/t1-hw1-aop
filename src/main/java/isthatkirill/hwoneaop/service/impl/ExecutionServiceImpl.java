package isthatkirill.hwoneaop.service.impl;

import isthatkirill.hwoneaop.model.Execution;
import isthatkirill.hwoneaop.repository.ExecutionRepository;
import isthatkirill.hwoneaop.service.ExecutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Kirill Emelyanov
 */

@Service
@RequiredArgsConstructor
public class ExecutionServiceImpl implements ExecutionService {

    private final ExecutionRepository executionRepository;

    @Async
    @Override
    public void save(Execution execution) {
        executionRepository.save(execution);
    }
}
