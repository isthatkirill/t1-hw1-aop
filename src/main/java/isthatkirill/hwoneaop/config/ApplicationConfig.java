package isthatkirill.hwoneaop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author Kirill Emelyanov
 */

@EnableAsync
@Configuration
public class ApplicationConfig {

    @Bean("defaultExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(16);
        executor.setMaxPoolSize(16);
        executor.setThreadNamePrefix("Thread-");
        executor.initialize();
        return executor;
    }

}
