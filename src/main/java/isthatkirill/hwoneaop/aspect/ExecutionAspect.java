package isthatkirill.hwoneaop.aspect;

import isthatkirill.hwoneaop.model.Execution;
import isthatkirill.hwoneaop.service.ExecutionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Kirill Emelyanov
 */

@Slf4j
@Aspect
@Order(1)
@Component
@RequiredArgsConstructor
public class ExecutionAspect {

    private final ExecutionService executionService;

    @Around(value = "@annotation(isthatkirill.hwoneaop.aspect.annotation.TrackTime)")
    public Object executionTime(ProceedingJoinPoint point) throws Throwable {
        String methodName = point.getSignature().getName();
        String className = point.getSignature().getDeclaringTypeName();
        Object[] args = point.getArgs();

        long startTime = System.currentTimeMillis();
        Object object = point.proceed();
        long millisTook = System.currentTimeMillis() - startTime;

        log.info("Method [{}] from class [{}] executed in {} millis with args = {}", methodName, className,
                millisTook, args);

        executionService.save(
                Execution.builder()
                        .className(className)
                        .methodName(methodName)
                        .args(args)
                        .millisTook(millisTook)
                        .isSuccess(true)
                        .build()
        );

        return object;
    }

}
