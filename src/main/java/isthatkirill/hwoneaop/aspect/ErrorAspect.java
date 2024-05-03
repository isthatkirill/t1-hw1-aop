package isthatkirill.hwoneaop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Kirill Emelyanov
 */

@Component
@Aspect
@Order(2)
@Slf4j
public class ErrorAspect {


    @AfterThrowing(value = "@annotation(isthatkirill.hwoneaop.aspect.annotation.TrackTime)", throwing = "e")
    public void logError(JoinPoint joinPoint, Exception e) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();

        log.error("Method [{}] from class [{}] failed with exception [{}]. Message: {}",
                methodName, className, e.getClass().getSimpleName(), e.getMessage());
    }

}
