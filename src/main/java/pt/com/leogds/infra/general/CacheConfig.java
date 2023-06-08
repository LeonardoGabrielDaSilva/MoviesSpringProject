package pt.com.leogds.infra.general;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;

@Configuration
@EnableCaching
public class CacheConfig {

	@Value("${hazelcast.caches.top10Movies.ttl}")
	private String top10MoviesTTL;
	
	@Bean
    public CacheManager cacheManager() {
        return new HazelcastCacheManager(hazelcastInstance());
    }

    @Bean
    public HazelcastInstance hazelcastInstance() {
        Config config = new Config();
        config.setInstanceName("hazelcast-cache");
        
        MapConfig top10MoviesConfig = new MapConfig();
        top10MoviesConfig.setTimeToLiveSeconds(Integer.parseInt(top10MoviesTTL));
        config.getMapConfigs().put("top10Movies", top10MoviesConfig);

        return Hazelcast.newHazelcastInstance(config);
    }
}