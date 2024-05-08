package isthatkirill.hwoneaop.web.controller;

import isthatkirill.hwoneaop.model.Execution;
import isthatkirill.hwoneaop.service.ExecutionService;
import isthatkirill.hwoneaop.web.dto.ExecutionDto;
import isthatkirill.hwoneaop.web.dto.ExecutionSummary;
import isthatkirill.hwoneaop.web.mapper.ExecutionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/summary/")
    public ResponseEntity<ExecutionSummary> getSummary(
            @RequestParam(value = "className", required = true) String className,
            @RequestParam(value = "methodName", required = false) String methodName) {
        CompletableFuture<ExecutionSummary> result = executionService.getMethodSummary(methodName, className);
        return ResponseEntity
                .ok(result.join());
    }

}
