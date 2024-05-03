package isthatkirill.hwoneaop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Kirill Emelyanov
 */

@Component
@Aspect
@Order(1)
@Slf4j
public class ExecutionAspect {

    @Around(value = "@annotation(isthatkirill.hwoneaop.aspect.annotation.TrackTime)")
    public Object executionTime(ProceedingJoinPoint point) throws Throwable {
        String methodName = point.getSignature().getName();
        String className = point.getSignature().getDeclaringTypeName();
        long startTime = System.currentTimeMillis();
        Object object = point.proceed();
        long endTime = System.currentTimeMillis();
        log.info("Method [{}] from class [{}] executed in {} millis.", methodName, className, endTime - startTime);
        return object;
    }

}
