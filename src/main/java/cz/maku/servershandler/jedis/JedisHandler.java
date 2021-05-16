package cz.maku.servershandler.jedis;

import cz.maku.servershandler.Instance;
import lombok.Getter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisHandler {

    @Getter
    private final Jedis jedis;

    public JedisHandler() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(3);
        jedisPoolConfig.setMaxWaitMillis(10000);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, Instance.getInstance().getConfig().getString("jedis.hostname"), Instance.getInstance().getConfig().getInt("jedis.port"));
        this.jedis = jedisPool.getResource();
        Instance.getInstance().getLogger().info("Connectiong to Redis...");
        jedis.connect();
        if (jedis.isConnected()) {
            Instance.getInstance().getLogger().info("§aSuccessfully connected to Redis.");
            Instance.getInstance().getLogger().info("Trying to auth to Redis...");
            jedis.auth(Instance.getInstance().getConfig().getString("jedis.password"));
        }


    }

}
