package com.cyd.cache.property;

import com.cyd.cache.constant.CommonConstants;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.List;

@Data
@ConfigurationProperties(prefix = CommonConstants.REDIS_CONFIG_PREFIX)
@Slf4j
@Configuration
public class RedisAutoConfigurationProperty {

    private ClientType clientType;
    private Sentinel sentinel;
    private Cluster cluster;
    private final Lettuce lettuce =
            new Lettuce();

    private String userName;

    private String url;

    private String clusterUserName;

    private String redisClusterConfiguration;

    private String sentinelMasterName;

    private String sentinelName;

    private String host;

    private Integer database;

    private Integer port;

    private String password;

    /**
     * 连接池最大连接数（使用负值表示没有限制）
     */
    private  Integer maxActive;

    /**
     * 连接池最大阻塞等待时间（使用负值表示没有限制）
     */
    private String maxWait;

    /**
     * 连接池中的最大空闲连接
     */
    private Integer maxIdle;

    /**
     * 连接池中的最小空闲连接
     */
    private Integer minIdle;

    /**
     * 连接池超时时间（毫秒）
     */
    private String timeout;

    public static class Sentinel {
        private String master;
        private List<String> nodes;
        private String password;

        public Sentinel() {
        }

        public String getMaster() {
            return this.master;
        }

        public void setMaster(String master) {
            this.master = master;
        }

        public List<String> getNodes() {
            return this.nodes;
        }

        public void setNodes(List<String> nodes) {
            this.nodes = nodes;
        }

        public String getPassword() {
            return this.password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class Cluster {
        private List<String> nodes;
        private Integer maxRedirects;

        public Cluster() {
        }

        public List<String> getNodes() {
            return this.nodes;
        }

        public void setNodes(List<String> nodes) {
            this.nodes = nodes;
        }

        public Integer getMaxRedirects() {
            return this.maxRedirects;
        }

        public void setMaxRedirects(Integer maxRedirects) {
            this.maxRedirects = maxRedirects;
        }
    }

    public static class Pool {
        private int maxIdle = 8;
        private int minIdle = 0;
        private int maxActive = 8;
        private Duration maxWait = Duration.ofMillis(-1L);
        private Duration timeBetweenEvictionRuns;

        public Pool() {
        }

        public int getMaxIdle() {
            return this.maxIdle;
        }

        public void setMaxIdle(int maxIdle) {
            this.maxIdle = maxIdle;
        }

        public int getMinIdle() {
            return this.minIdle;
        }

        public void setMinIdle(int minIdle) {
            this.minIdle = minIdle;
        }

        public int getMaxActive() {
            return this.maxActive;
        }

        public void setMaxActive(int maxActive) {
            this.maxActive = maxActive;
        }

        public Duration getMaxWait() {
            return this.maxWait;
        }

        public void setMaxWait(Duration maxWait) {
            this.maxWait = maxWait;
        }

        public Duration getTimeBetweenEvictionRuns() {
            return this.timeBetweenEvictionRuns;
        }

        public void setTimeBetweenEvictionRuns(Duration timeBetweenEvictionRuns) {
            this.timeBetweenEvictionRuns = timeBetweenEvictionRuns;
        }
    }


    public static class Lettuce {
        private Duration shutdownTimeout = Duration.ofMillis(100L);
        private Pool pool;
        private final Cluster cluster
                = new Cluster();

        public Lettuce() {
        }

        public Duration getShutdownTimeout() {
            return this.shutdownTimeout;
        }

        public void setShutdownTimeout(Duration shutdownTimeout) {
            this.shutdownTimeout = shutdownTimeout;
        }

        public Pool getPool() {
            return this.pool;
        }

        public void setPool(Pool pool) {
            this.pool = pool;
        }

        public Cluster getCluster() {
            return this.cluster;
        }

        public static class Cluster {
            private final Refresh refresh = new Refresh();

            public Cluster() {
            }

            public Refresh getRefresh() {
                return this.refresh;
            }

            public static class Refresh {
                private boolean dynamicRefreshSources = true;
                private Duration period;
                private boolean adaptive;

                public Refresh() {
                }

                public boolean isDynamicRefreshSources() {
                    return this.dynamicRefreshSources;
                }

                public void setDynamicRefreshSources(boolean dynamicRefreshSources) {
                    this.dynamicRefreshSources = dynamicRefreshSources;
                }

                public Duration getPeriod() {
                    return this.period;
                }

                public void setPeriod(Duration period) {
                    this.period = period;
                }

                public boolean isAdaptive() {
                    return this.adaptive;
                }

                public void setAdaptive(boolean adaptive) {
                    this.adaptive = adaptive;
                }
            }
        }
    }

    public static enum ClientType {
        LETTUCE,
        JEDIS;

        private ClientType() {
        }
    }

}
