package sandbox.replicated.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.UUID;

public class Application {
    public static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    private final CachingRepository cachingRepository;

    public Application(CachingRepository cachingRepository) {
        this.cachingRepository = cachingRepository;
    }

    @PostConstruct
    public void startServer() {
        LOGGER.info("Starting application...");
        cachingRepository.getFeature(UUID.randomUUID().toString());
        LOGGER.info("Application is running.");
    }
}
