package sandbox.replicated.cache.shell;

import sandbox.replicated.cache.CachingRepository;
import sandbox.replicated.cache.Feature;

//@ShellComponent
public class DemoShell {

    //@Resource
    CachingRepository cachingRepository;

    //@ShellMethod(key = "list", value = "List all cache's content")
    public String listCache() {
        return cachingRepository.listKeys();
    }

    //@ShellMethod(value = "Set a value to a feature")
    public void set(String key, String value) {
        cachingRepository.setFeature(new Feature(key, value));
    }

    //@ShellMethod(value = "Read feature's value")
    public String read(String key) {
        Feature feature = cachingRepository.getFeature(key);
        if (feature == null) {
            System.err.println("Feature not found");
            return "";
        } else {
            return (String) feature.getValue();
        }
    }
}
