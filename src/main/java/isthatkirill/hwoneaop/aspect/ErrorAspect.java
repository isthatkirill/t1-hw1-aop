package isthatkirill.hwoneaop.aspect;

import isthatkirill.hwoneaop.model.Execution;
import isthatkirill.hwoneaop.service.ExecutionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Kirill Emelyanov
 */

@Slf4j
@Aspect
@Order(2)
@Component
@RequiredArgsConstructor
public class ErrorAspect {

    private final ExecutionService executionService;

    @AfterThrowing(value = "@annotation(isthatkirill.hwoneaop.aspect.annotation.TrackTime) || " +
            "@annotation(isthatkirill.hwoneaop.aspect.annotation.TrackAsyncTime)", throwing = "e")
    public void logError(JoinPoint point, Exception e) {
        String methodName = point.getSignature().getName();
        String className = point.getSignature().getDeclaringTypeName();
        Object[] args = point.getArgs();

        log.error("Method [{}] with args {} from class [{}] failed with exception [{}]. Message: {}",
                methodName, args, className, e.getClass().getSimpleName(), e.getMessage());

        executionService.save(
                Execution.builder()
                        .className(className)
                        .methodName(methodName)
                        .args(args)
                        .millisTook(null)
                        .isSuccess(false)
                        .build()
        );

    }

}
