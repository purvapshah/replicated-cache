package sandbox.replicated.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.HashMap;

import static java.util.stream.Collectors.joining;

public class CachingRepository {

    private HashMap<String, Feature> features = new HashMap<>();

    public static final Logger LOGGER = LoggerFactory.getLogger(CachingRepository.class);

    public final String CACHE_FEATURES = "inMemoryFeatures";

    @Cacheable(value = CACHE_FEATURES, key = "'feature-' + #name", unless = "#result == null")
    public Feature getFeature(String name) {
        LOGGER.info("Getting feature value: {}", name);
        return features.get(name);
    }

    @CacheEvict(value = CACHE_FEATURES, key = "'feature-' + #feature.getName()")
    public Feature setFeature(Feature feature) {
        LOGGER.info("Update value in the cache: {}: {}", feature.getName(), feature.getValue());
        features.put(feature.getName(), feature);
        return feature;
    }

    public String listKeys() {
        return features.keySet().stream().collect(joining(", "));
    }
}
