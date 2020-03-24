package sandbox.replicated.cache;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class ContextConfiguration {

    @Bean
    public CachingRepository cachingRepository() {
        return new CachingRepository();
    }

    @Bean
    public Application application(CachingRepository cachingRepository) {
        Application application = new Application(cachingRepository);
        return application;
    }

}
