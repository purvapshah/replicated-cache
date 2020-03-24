package sandbox.replicated.cache.shell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import sandbox.replicated.cache.CachingRepository;
import sandbox.replicated.cache.Feature;

import javax.annotation.Resource;

@RestController
public class RestShell {

    public static final Logger LOGGER = LoggerFactory.getLogger(RestShell.class);

    @Resource
    CachingRepository cachingRepository;

    @GetMapping("/list")
    public String listCache() {
        return cachingRepository.listKeys();
    }

    @PostMapping("/set")
    public void set(@RequestHeader("key") String key, @RequestHeader("value") String value) {
        LOGGER.info("Setup value for key {} == {}", key, value);
        cachingRepository.setFeature(new Feature(key, value));
    }

    @GetMapping("/read")
    public String read(@RequestParam("key") String key) {
        Feature feature = cachingRepository.getFeature(key);
        if (feature == null) {
            LOGGER.warn("Feature not found");
            return "";
        } else {
            Object value = feature.getValue();
            LOGGER.info("Cached value for key {} == {}", key, value);
            return (String) value;
        }
    }
}
