package isthatkirill.hwoneaop.web.controller;

import isthatkirill.hwoneaop.model.Execution;
import isthatkirill.hwoneaop.service.ExecutionService;
import isthatkirill.hwoneaop.web.dto.ExecutionDto;
import isthatkirill.hwoneaop.web.mapper.ExecutionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * @author Kirill Emelyanov
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/executions")
public class ExecutionController {

    private final ExecutionService executionService;
    private final ExecutionMapper executionMapper;

    @GetMapping("/{executionId}")
    public ResponseEntity<ExecutionDto> getById(@PathVariable Long executionId) {
        CompletableFuture<Execution> result = executionService.getExecutionById(executionId);
        return ResponseEntity
                .ok(executionMapper.toDto(result.join()));
    }

}
