package self.aub.product.redis.client;

import redis.clients.jedis.BitOP;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.Client;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster.Reset;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.ZParams;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.params.geo.GeoRadiusParam;
import redis.clients.jedis.params.sortedset.ZAddParams;
import redis.clients.jedis.params.sortedset.ZIncrByParams;
import redis.clients.util.Slowlog;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by liujinxin on 2016/12/12.
 */
public class PoolRedisClient {

    private JedisPool jedisPool;

    public PoolRedisClient(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }


    public String set(String key, String value) {
        return set(1, key, value);
    }

    public String set(int retries, String key, String value) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.set(key, value);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String set(String key, String value, String nxxx, String expx, long time) {
        return set(1, key, value, nxxx, expx, time);
    }

    public String set(int retries, String key, String value, String nxxx, String expx, long time) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.set(key, value, nxxx, expx, time);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String get(String key) {
        return get(1, key);
    }

    public String get(int retries, String key) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.get(key);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long exists(String... keys) {
        return exists(1, keys);
    }

    public Long exists(int retries, String... keys) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.exists(keys);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Boolean exists(String key) {
        return exists(1, key);
    }

    public Boolean exists(int retries, String key) {
        Boolean result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.exists(key);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long del(String... keys) {
        return del(1, keys);
    }

    public Long del(int retries, String... keys) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.del(keys);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long del(String key) {
        return del(1, key);
    }

    public Long del(int retries, String key) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.del(key);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String type(String key) {
        return type(1, key);
    }

    public String type(int retries, String key) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.type(key);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Set<String> keys(String pattern) {
        return keys(1, pattern);
    }

    public Set<String> keys(int retries, String pattern) {
        Set<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.keys(pattern);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String randomKey() {
        return randomKey(1);
    }

    public String randomKey(int retries) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.randomKey();
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String rename(String oldkey, String newkey) {
        return rename(1, oldkey, newkey);
    }

    public String rename(int retries, String oldkey, String newkey) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.rename(oldkey, newkey);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long renamenx(String oldkey, String newkey) {
        return renamenx(1, oldkey, newkey);
    }

    public Long renamenx(int retries, String oldkey, String newkey) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.renamenx(oldkey, newkey);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long expire(String key, int seconds) {
        return expire(1, key, seconds);
    }

    public Long expire(int retries, String key, int seconds) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.expire(key, seconds);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long expireAt(String key, long unixTime) {
        return expireAt(1, key, unixTime);
    }

    public Long expireAt(int retries, String key, long unixTime) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.expireAt(key, unixTime);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long ttl(String key) {
        return ttl(1, key);
    }

    public Long ttl(int retries, String key) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.ttl(key);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long move(String key, int dbIndex) {
        return move(1, key, dbIndex);
    }

    public Long move(int retries, String key, int dbIndex) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.move(key, dbIndex);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String getSet(String key, String value) {
        return getSet(1, key, value);
    }

    public String getSet(int retries, String key, String value) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.getSet(key, value);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public List<String> mget(String... keys) {
        return mget(1, keys);
    }

    public List<String> mget(int retries, String... keys) {
        List<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.mget(keys);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long setnx(String key, String value) {
        return setnx(1, key, value);
    }

    public Long setnx(int retries, String key, String value) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.setnx(key, value);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String setex(String key, int seconds, String value) {
        return setex(1, key, seconds, value);
    }

    public String setex(int retries, String key, int seconds, String value) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.setex(key, seconds, value);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String mset(String... keysvalues) {
        return mset(1, keysvalues);
    }

    public String mset(int retries, String... keysvalues) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.mset(keysvalues);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long msetnx(String... keysvalues) {
        return msetnx(1, keysvalues);
    }

    public Long msetnx(int retries, String... keysvalues) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.msetnx(keysvalues);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long decrBy(String key, long integer) {
        return decrBy(1, key, integer);
    }

    public Long decrBy(int retries, String key, long integer) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.decrBy(key, integer);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long decr(String key) {
        return decr(1, key);
    }

    public Long decr(int retries, String key) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.decr(key);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long incrBy(String key, long integer) {
        return incrBy(1, key, integer);
    }

    public Long incrBy(int retries, String key, long integer) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.incrBy(key, integer);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Double incrByFloat(String key, double value) {
        return incrByFloat(1, key, value);
    }

    public Double incrByFloat(int retries, String key, double value) {
        Double result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.incrByFloat(key, value);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long incr(String key) {
        return incr(1, key);
    }

    public Long incr(int retries, String key) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.incr(key);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long append(String key, String value) {
        return append(1, key, value);
    }

    public Long append(int retries, String key, String value) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.append(key, value);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String substr(String key, int start, int end) {
        return substr(1, key, start, end);
    }

    public String substr(int retries, String key, int start, int end) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.substr(key, start, end);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long hset(String key, String field, String value) {
        return hset(1, key, field, value);
    }

    public Long hset(int retries, String key, String field, String value) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.hset(key, field, value);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String hget(String key, String field) {
        return hget(1, key, field);
    }

    public String hget(int retries, String key, String field) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.hget(key, field);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long hsetnx(String key, String field, String value) {
        return hsetnx(1, key, field, value);
    }

    public Long hsetnx(int retries, String key, String field, String value) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.hsetnx(key, field, value);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String hmset(String key, Map<String,String> hash) {
        return hmset(1, key, hash);
    }

    public String hmset(int retries, String key, Map<String,String> hash) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.hmset(key, hash);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public List<String> hmget(String key, String... fields) {
        return hmget(1, key, fields);
    }

    public List<String> hmget(int retries, String key, String... fields) {
        List<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.hmget(key, fields);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long hincrBy(String key, String field, long value) {
        return hincrBy(1, key, field, value);
    }

    public Long hincrBy(int retries, String key, String field, long value) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.hincrBy(key, field, value);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Double hincrByFloat(String key, String field, double value) {
        return hincrByFloat(1, key, field, value);
    }

    public Double hincrByFloat(int retries, String key, String field, double value) {
        Double result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.hincrByFloat(key, field, value);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Boolean hexists(String key, String field) {
        return hexists(1, key, field);
    }

    public Boolean hexists(int retries, String key, String field) {
        Boolean result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.hexists(key, field);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long hdel(String key, String... fields) {
        return hdel(1, key, fields);
    }

    public Long hdel(int retries, String key, String... fields) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.hdel(key, fields);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long hlen(String key) {
        return hlen(1, key);
    }

    public Long hlen(int retries, String key) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.hlen(key);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Set<String> hkeys(String key) {
        return hkeys(1, key);
    }

    public Set<String> hkeys(int retries, String key) {
        Set<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.hkeys(key);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public List<String> hvals(String key) {
        return hvals(1, key);
    }

    public List<String> hvals(int retries, String key) {
        List<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.hvals(key);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Map<String,String> hgetAll(String key) {
        return hgetAll(1, key);
    }

    public Map<String,String> hgetAll(int retries, String key) {
        Map<String,String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.hgetAll(key);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long rpush(String key, String... strings) {
        return rpush(1, key, strings);
    }

    public Long rpush(int retries, String key, String... strings) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.rpush(key, strings);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long lpush(String key, String... strings) {
        return lpush(1, key, strings);
    }

    public Long lpush(int retries, String key, String... strings) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.lpush(key, strings);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long llen(String key) {
        return llen(1, key);
    }

    public Long llen(int retries, String key) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.llen(key);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public List<String> lrange(String key, long start, long end) {
        return lrange(1, key, start, end);
    }

    public List<String> lrange(int retries, String key, long start, long end) {
        List<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.lrange(key, start, end);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String ltrim(String key, long start, long end) {
        return ltrim(1, key, start, end);
    }

    public String ltrim(int retries, String key, long start, long end) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.ltrim(key, start, end);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String lindex(String key, long index) {
        return lindex(1, key, index);
    }

    public String lindex(int retries, String key, long index) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.lindex(key, index);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String lset(String key, long index, String value) {
        return lset(1, key, index, value);
    }

    public String lset(int retries, String key, long index, String value) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.lset(key, index, value);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long lrem(String key, long count, String value) {
        return lrem(1, key, count, value);
    }

    public Long lrem(int retries, String key, long count, String value) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.lrem(key, count, value);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String lpop(String key) {
        return lpop(1, key);
    }

    public String lpop(int retries, String key) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.lpop(key);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String rpop(String key) {
        return rpop(1, key);
    }

    public String rpop(int retries, String key) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.rpop(key);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String rpoplpush(String srckey, String dstkey) {
        return rpoplpush(1, srckey, dstkey);
    }

    public String rpoplpush(int retries, String srckey, String dstkey) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.rpoplpush(srckey, dstkey);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long sadd(String key, String... members) {
        return sadd(1, key, members);
    }

    public Long sadd(int retries, String key, String... members) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.sadd(key, members);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Set<String> smembers(String key) {
        return smembers(1, key);
    }

    public Set<String> smembers(int retries, String key) {
        Set<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.smembers(key);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long srem(String key, String... members) {
        return srem(1, key, members);
    }

    public Long srem(int retries, String key, String... members) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.srem(key, members);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String spop(String key) {
        return spop(1, key);
    }

    public String spop(int retries, String key) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.spop(key);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Set<String> spop(String key, long count) {
        return spop(1, key, count);
    }

    public Set<String> spop(int retries, String key, long count) {
        Set<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.spop(key, count);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long smove(String srckey, String dstkey, String member) {
        return smove(1, srckey, dstkey, member);
    }

    public Long smove(int retries, String srckey, String dstkey, String member) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.smove(srckey, dstkey, member);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long scard(String key) {
        return scard(1, key);
    }

    public Long scard(int retries, String key) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.scard(key);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Boolean sismember(String key, String member) {
        return sismember(1, key, member);
    }

    public Boolean sismember(int retries, String key, String member) {
        Boolean result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.sismember(key, member);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Set<String> sinter(String... keys) {
        return sinter(1, keys);
    }

    public Set<String> sinter(int retries, String... keys) {
        Set<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.sinter(keys);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long sinterstore(String dstkey, String... keys) {
        return sinterstore(1, dstkey, keys);
    }

    public Long sinterstore(int retries, String dstkey, String... keys) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.sinterstore(dstkey, keys);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Set<String> sunion(String... keys) {
        return sunion(1, keys);
    }

    public Set<String> sunion(int retries, String... keys) {
        Set<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.sunion(keys);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long sunionstore(String dstkey, String... keys) {
        return sunionstore(1, dstkey, keys);
    }

    public Long sunionstore(int retries, String dstkey, String... keys) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.sunionstore(dstkey, keys);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Set<String> sdiff(String... keys) {
        return sdiff(1, keys);
    }

    public Set<String> sdiff(int retries, String... keys) {
        Set<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.sdiff(keys);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long sdiffstore(String dstkey, String... keys) {
        return sdiffstore(1, dstkey, keys);
    }

    public Long sdiffstore(int retries, String dstkey, String... keys) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.sdiffstore(dstkey, keys);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String srandmember(String key) {
        return srandmember(1, key);
    }

    public String srandmember(int retries, String key) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.srandmember(key);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public List<String> srandmember(String key, int count) {
        return srandmember(1, key, count);
    }

    public List<String> srandmember(int retries, String key, int count) {
        List<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.srandmember(key, count);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long zadd(String key, double score, String member) {
        return zadd(1, key, score, member);
    }

    public Long zadd(int retries, String key, double score, String member) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zadd(key, score, member);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long zadd(String key, double score, String member, ZAddParams params) {
        return zadd(1, key, score, member, params);
    }

    public Long zadd(int retries, String key, double score, String member, ZAddParams params) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zadd(key, score, member, params);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long zadd(String key, Map<String,Double> scoreMembers) {
        return zadd(1, key, scoreMembers);
    }

    public Long zadd(int retries, String key, Map<String,Double> scoreMembers) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zadd(key, scoreMembers);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long zadd(String key, Map<String,Double> scoreMembers, ZAddParams params) {
        return zadd(1, key, scoreMembers, params);
    }

    public Long zadd(int retries, String key, Map<String,Double> scoreMembers, ZAddParams params) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zadd(key, scoreMembers, params);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Set<String> zrange(String key, long start, long end) {
        return zrange(1, key, start, end);
    }

    public Set<String> zrange(int retries, String key, long start, long end) {
        Set<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zrange(key, start, end);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long zrem(String key, String... members) {
        return zrem(1, key, members);
    }

    public Long zrem(int retries, String key, String... members) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zrem(key, members);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Double zincrby(String key, double score, String member) {
        return zincrby(1, key, score, member);
    }

    public Double zincrby(int retries, String key, double score, String member) {
        Double result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zincrby(key, score, member);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Double zincrby(String key, double score, String member, ZIncrByParams params) {
        return zincrby(1, key, score, member, params);
    }

    public Double zincrby(int retries, String key, double score, String member, ZIncrByParams params) {
        Double result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zincrby(key, score, member, params);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long zrank(String key, String member) {
        return zrank(1, key, member);
    }

    public Long zrank(int retries, String key, String member) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zrank(key, member);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long zrevrank(String key, String member) {
        return zrevrank(1, key, member);
    }

    public Long zrevrank(int retries, String key, String member) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zrevrank(key, member);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Set<String> zrevrange(String key, long start, long end) {
        return zrevrange(1, key, start, end);
    }

    public Set<String> zrevrange(int retries, String key, long start, long end) {
        Set<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zrevrange(key, start, end);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Set<Tuple> zrangeWithScores(String key, long start, long end) {
        return zrangeWithScores(1, key, start, end);
    }

    public Set<Tuple> zrangeWithScores(int retries, String key, long start, long end) {
        Set<Tuple> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zrangeWithScores(key, start, end);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
        return zrevrangeWithScores(1, key, start, end);
    }

    public Set<Tuple> zrevrangeWithScores(int retries, String key, long start, long end) {
        Set<Tuple> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zrevrangeWithScores(key, start, end);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long zcard(String key) {
        return zcard(1, key);
    }

    public Long zcard(int retries, String key) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zcard(key);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Double zscore(String key, String member) {
        return zscore(1, key, member);
    }

    public Double zscore(int retries, String key, String member) {
        Double result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zscore(key, member);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String watch(String... keys) {
        return watch(1, keys);
    }

    public String watch(int retries, String... keys) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.watch(keys);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public List<String> sort(String key) {
        return sort(1, key);
    }

    public List<String> sort(int retries, String key) {
        List<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.sort(key);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public List<String> sort(String key, SortingParams sortingParameters) {
        return sort(1, key, sortingParameters);
    }

    public List<String> sort(int retries, String key, SortingParams sortingParameters) {
        List<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.sort(key, sortingParameters);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public List<String> blpopWithTimeout(int timeout, String... keys) {
        return blpopWithTimeout(1, timeout, keys);
    }

    public List<String> blpopWithTimeout(int retries, int timeout, String... keys) {
        List<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.blpop(timeout, keys);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public List<String> blpop(String... args) {
        return blpop(1, args);
    }

    public List<String> blpop(int retries, String... args) {
        List<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.blpop(args);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public List<String> brpop(String... args) {
        return brpop(1, args);
    }

    public List<String> brpop(int retries, String... args) {
        List<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.brpop(args);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long sort(String key, SortingParams sortingParameters, String dstkey) {
        return sort(1, key, sortingParameters, dstkey);
    }

    public Long sort(int retries, String key, SortingParams sortingParameters, String dstkey) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.sort(key, sortingParameters, dstkey);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long sort(String key, String dstkey) {
        return sort(1, key, dstkey);
    }

    public Long sort(int retries, String key, String dstkey) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.sort(key, dstkey);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public List<String> brpopWithTimeout(int timeout, String... keys) {
        return brpopWithTimeout(1, timeout, keys);
    }

    public List<String> brpopWithTimeout(int retries, int timeout, String... keys) {
        List<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.brpop(timeout, keys);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long zcount(String key, double min, double max) {
        return zcount(1, key, min, max);
    }

    public Long zcount(int retries, String key, double min, double max) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zcount(key, min, max);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long zcount(String key, String min, String max) {
        return zcount(1, key, min, max);
    }

    public Long zcount(int retries, String key, String min, String max) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zcount(key, min, max);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Set<String> zrangeByScore(String key, double min, double max) {
        return zrangeByScore(1, key, min, max);
    }

    public Set<String> zrangeByScore(int retries, String key, double min, double max) {
        Set<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zrangeByScore(key, min, max);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Set<String> zrangeByScore(String key, String min, String max) {
        return zrangeByScore(1, key, min, max);
    }

    public Set<String> zrangeByScore(int retries, String key, String min, String max) {
        Set<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zrangeByScore(key, min, max);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        return zrangeByScore(1, key, min, max, offset, count);
    }

    public Set<String> zrangeByScore(int retries, String key, double min, double max, int offset, int count) {
        Set<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zrangeByScore(key, min, max, offset, count);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
        return zrangeByScore(1, key, min, max, offset, count);
    }

    public Set<String> zrangeByScore(int retries, String key, String min, String max, int offset, int count) {
        Set<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zrangeByScore(key, min, max, offset, count);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
        return zrangeByScoreWithScores(1, key, min, max);
    }

    public Set<Tuple> zrangeByScoreWithScores(int retries, String key, double min, double max) {
        Set<Tuple> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zrangeByScoreWithScores(key, min, max);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
        return zrangeByScoreWithScores(1, key, min, max);
    }

    public Set<Tuple> zrangeByScoreWithScores(int retries, String key, String min, String max) {
        Set<Tuple> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zrangeByScoreWithScores(key, min, max);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
        return zrangeByScoreWithScores(1, key, min, max, offset, count);
    }

    public Set<Tuple> zrangeByScoreWithScores(int retries, String key, double min, double max, int offset, int count) {
        Set<Tuple> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zrangeByScoreWithScores(key, min, max, offset, count);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
        return zrangeByScoreWithScores(1, key, min, max, offset, count);
    }

    public Set<Tuple> zrangeByScoreWithScores(int retries, String key, String min, String max, int offset, int count) {
        Set<Tuple> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zrangeByScoreWithScores(key, min, max, offset, count);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Set<String> zrevrangeByScore(String key, double max, double min) {
        return zrevrangeByScore(1, key, max, min);
    }

    public Set<String> zrevrangeByScore(int retries, String key, double max, double min) {
        Set<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zrevrangeByScore(key, max, min);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Set<String> zrevrangeByScore(String key, String max, String min) {
        return zrevrangeByScore(1, key, max, min);
    }

    public Set<String> zrevrangeByScore(int retries, String key, String max, String min) {
        Set<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zrevrangeByScore(key, max, min);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
        return zrevrangeByScore(1, key, max, min, offset, count);
    }

    public Set<String> zrevrangeByScore(int retries, String key, double max, double min, int offset, int count) {
        Set<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zrevrangeByScore(key, max, min, offset, count);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
        return zrevrangeByScoreWithScores(1, key, max, min);
    }

    public Set<Tuple> zrevrangeByScoreWithScores(int retries, String key, double max, double min) {
        Set<Tuple> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zrevrangeByScoreWithScores(key, max, min);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
        return zrevrangeByScoreWithScores(1, key, max, min, offset, count);
    }

    public Set<Tuple> zrevrangeByScoreWithScores(int retries, String key, double max, double min, int offset, int count) {
        Set<Tuple> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {
        return zrevrangeByScoreWithScores(1, key, max, min, offset, count);
    }

    public Set<Tuple> zrevrangeByScoreWithScores(int retries, String key, String max, String min, int offset, int count) {
        Set<Tuple> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
        return zrevrangeByScore(1, key, max, min, offset, count);
    }

    public Set<String> zrevrangeByScore(int retries, String key, String max, String min, int offset, int count) {
        Set<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zrevrangeByScore(key, max, min, offset, count);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
        return zrevrangeByScoreWithScores(1, key, max, min);
    }

    public Set<Tuple> zrevrangeByScoreWithScores(int retries, String key, String max, String min) {
        Set<Tuple> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zrevrangeByScoreWithScores(key, max, min);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long zremrangeByRank(String key, long start, long end) {
        return zremrangeByRank(1, key, start, end);
    }

    public Long zremrangeByRank(int retries, String key, long start, long end) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zremrangeByRank(key, start, end);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long zremrangeByScore(String key, double start, double end) {
        return zremrangeByScore(1, key, start, end);
    }

    public Long zremrangeByScore(int retries, String key, double start, double end) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zremrangeByScore(key, start, end);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long zremrangeByScore(String key, String start, String end) {
        return zremrangeByScore(1, key, start, end);
    }

    public Long zremrangeByScore(int retries, String key, String start, String end) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zremrangeByScore(key, start, end);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long zunionstore(String dstkey, String... sets) {
        return zunionstore(1, dstkey, sets);
    }

    public Long zunionstore(int retries, String dstkey, String... sets) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zunionstore(dstkey, sets);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long zunionstore(String dstkey, ZParams params, String... sets) {
        return zunionstore(1, dstkey, params, sets);
    }

    public Long zunionstore(int retries, String dstkey, ZParams params, String... sets) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zunionstore(dstkey, params, sets);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long zinterstore(String dstkey, String... sets) {
        return zinterstore(1, dstkey, sets);
    }

    public Long zinterstore(int retries, String dstkey, String... sets) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zinterstore(dstkey, sets);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long zinterstore(String dstkey, ZParams params, String... sets) {
        return zinterstore(1, dstkey, params, sets);
    }

    public Long zinterstore(int retries, String dstkey, ZParams params, String... sets) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zinterstore(dstkey, params, sets);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long zlexcount(String key, String min, String max) {
        return zlexcount(1, key, min, max);
    }

    public Long zlexcount(int retries, String key, String min, String max) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zlexcount(key, min, max);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Set<String> zrangeByLex(String key, String min, String max) {
        return zrangeByLex(1, key, min, max);
    }

    public Set<String> zrangeByLex(int retries, String key, String min, String max) {
        Set<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zrangeByLex(key, min, max);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
        return zrangeByLex(1, key, min, max, offset, count);
    }

    public Set<String> zrangeByLex(int retries, String key, String min, String max, int offset, int count) {
        Set<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zrangeByLex(key, min, max, offset, count);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Set<String> zrevrangeByLex(String key, String max, String min) {
        return zrevrangeByLex(1, key, max, min);
    }

    public Set<String> zrevrangeByLex(int retries, String key, String max, String min) {
        Set<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zrevrangeByLex(key, max, min);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count) {
        return zrevrangeByLex(1, key, max, min, offset, count);
    }

    public Set<String> zrevrangeByLex(int retries, String key, String max, String min, int offset, int count) {
        Set<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zrevrangeByLex(key, max, min, offset, count);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long zremrangeByLex(String key, String min, String max) {
        return zremrangeByLex(1, key, min, max);
    }

    public Long zremrangeByLex(int retries, String key, String min, String max) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zremrangeByLex(key, min, max);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long strlen(String key) {
        return strlen(1, key);
    }

    public Long strlen(int retries, String key) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.strlen(key);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long lpushx(String key, String... string) {
        return lpushx(1, key, string);
    }

    public Long lpushx(int retries, String key, String... string) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.lpushx(key, string);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long persist(String key) {
        return persist(1, key);
    }

    public Long persist(int retries, String key) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.persist(key);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long rpushx(String key, String... string) {
        return rpushx(1, key, string);
    }

    public Long rpushx(int retries, String key, String... string) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.rpushx(key, string);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String echo(String string) {
        return echo(1, string);
    }

    public String echo(int retries, String string) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.echo(string);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long linsert(String key, Client.LIST_POSITION where, String pivot, String value) {
        return linsert(1, key, where, pivot, value);
    }

    public Long linsert(int retries, String key, Client.LIST_POSITION where, String pivot, String value) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.linsert(key, where, pivot, value);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String brpoplpush(String source, String destination, int timeout) {
        return brpoplpush(1, source, destination, timeout);
    }

    public String brpoplpush(int retries, String source, String destination, int timeout) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.brpoplpush(source, destination, timeout);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Boolean setbit(String key, long offset, boolean value) {
        return setbit(1, key, offset, value);
    }

    public Boolean setbit(int retries, String key, long offset, boolean value) {
        Boolean result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.setbit(key, offset, value);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Boolean setbit(String key, long offset, String value) {
        return setbit(1, key, offset, value);
    }

    public Boolean setbit(int retries, String key, long offset, String value) {
        Boolean result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.setbit(key, offset, value);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Boolean getbit(String key, long offset) {
        return getbit(1, key, offset);
    }

    public Boolean getbit(int retries, String key, long offset) {
        Boolean result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.getbit(key, offset);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long setrange(String key, long offset, String value) {
        return setrange(1, key, offset, value);
    }

    public Long setrange(int retries, String key, long offset, String value) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.setrange(key, offset, value);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String getrange(String key, long startOffset, long endOffset) {
        return getrange(1, key, startOffset, endOffset);
    }

    public String getrange(int retries, String key, long startOffset, long endOffset) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.getrange(key, startOffset, endOffset);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long bitpos(String key, boolean value) {
        return bitpos(1, key, value);
    }

    public Long bitpos(int retries, String key, boolean value) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.bitpos(key, value);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long bitpos(String key, boolean value, BitPosParams params) {
        return bitpos(1, key, value, params);
    }

    public Long bitpos(int retries, String key, boolean value, BitPosParams params) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.bitpos(key, value, params);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public List<String> configGet(String pattern) {
        return configGet(1, pattern);
    }

    public List<String> configGet(int retries, String pattern) {
        List<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.configGet(pattern);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String configSet(String parameter, String value) {
        return configSet(1, parameter, value);
    }

    public String configSet(int retries, String parameter, String value) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.configSet(parameter, value);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Object eval(String script, int keyCount, String... params) {
        return eval(1, script, keyCount, params);
    }

    public Object eval(int retries, String script, int keyCount, String... params) {
        Object result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.eval(script, keyCount, params);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public void subscribe(JedisPubSub jedisPubSub, String... channels) {
        subscribe(1, jedisPubSub, channels);
    }

    public void subscribe(int retries, JedisPubSub jedisPubSub, String... channels) {
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.subscribe(jedisPubSub, channels);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
    }

    public Long publish(String channel, String message) {
        return publish(1, channel, message);
    }

    public Long publish(int retries, String channel, String message) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.publish(channel, message);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public void psubscribe(JedisPubSub jedisPubSub, String... patterns) {
        psubscribe(1, jedisPubSub, patterns);
    }

    public void psubscribe(int retries, JedisPubSub jedisPubSub, String... patterns) {
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.psubscribe(jedisPubSub, patterns);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
    }

    public Object eval(String script, List<String> keys, List<String> args) {
        return eval(1, script, keys, args);
    }

    public Object eval(int retries, String script, List<String> keys, List<String> args) {
        Object result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.eval(script, keys, args);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Object eval(String script) {
        return eval(1, script);
    }

    public Object eval(int retries, String script) {
        Object result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.eval(script);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Object evalsha(String script) {
        return evalsha(1, script);
    }

    public Object evalsha(int retries, String script) {
        Object result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.evalsha(script);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Object evalsha(String sha1, List<String> keys, List<String> args) {
        return evalsha(1, sha1, keys, args);
    }

    public Object evalsha(int retries, String sha1, List<String> keys, List<String> args) {
        Object result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.evalsha(sha1, keys, args);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Object evalsha(String sha1, int keyCount, String... params) {
        return evalsha(1, sha1, keyCount, params);
    }

    public Object evalsha(int retries, String sha1, int keyCount, String... params) {
        Object result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.evalsha(sha1, keyCount, params);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Boolean scriptExists(String sha1) {
        return scriptExists(1, sha1);
    }

    public Boolean scriptExists(int retries, String sha1) {
        Boolean result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.scriptExists(sha1);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public List<Boolean> scriptExists(String... sha1) {
        return scriptExists(1, sha1);
    }

    public List<Boolean> scriptExists(int retries, String... sha1) {
        List<Boolean> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.scriptExists(sha1);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String scriptLoad(String script) {
        return scriptLoad(1, script);
    }

    public String scriptLoad(int retries, String script) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.scriptLoad(script);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public List<Slowlog> slowlogGet() {
        return slowlogGet(1);
    }

    public List<Slowlog> slowlogGet(int retries) {
        List<Slowlog> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.slowlogGet();
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public List<Slowlog> slowlogGet(long entries) {
        return slowlogGet(1, entries);
    }

    public List<Slowlog> slowlogGet(int retries, long entries) {
        List<Slowlog> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.slowlogGet(entries);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long objectRefcount(String string) {
        return objectRefcount(1, string);
    }

    public Long objectRefcount(int retries, String string) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.objectRefcount(string);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String objectEncoding(String string) {
        return objectEncoding(1, string);
    }

    public String objectEncoding(int retries, String string) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.objectEncoding(string);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long objectIdletime(String string) {
        return objectIdletime(1, string);
    }

    public Long objectIdletime(int retries, String string) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.objectIdletime(string);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long bitcount(String key) {
        return bitcount(1, key);
    }

    public Long bitcount(int retries, String key) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.bitcount(key);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long bitcount(String key, long start, long end) {
        return bitcount(1, key, start, end);
    }

    public Long bitcount(int retries, String key, long start, long end) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.bitcount(key, start, end);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long bitop(BitOP op, String destKey, String... srcKeys) {
        return bitop(1, op, destKey, srcKeys);
    }

    public Long bitop(int retries, BitOP op, String destKey, String... srcKeys) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.bitop(op, destKey, srcKeys);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public List<Map<String,String>> sentinelMasters() {
        return sentinelMasters(1);
    }

    public List<Map<String,String>> sentinelMasters(int retries) {
        List<Map<String,String>> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.sentinelMasters();
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public List<String> sentinelGetMasterAddrByName(String masterName) {
        return sentinelGetMasterAddrByName(1, masterName);
    }

    public List<String> sentinelGetMasterAddrByName(int retries, String masterName) {
        List<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.sentinelGetMasterAddrByName(masterName);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long sentinelReset(String pattern) {
        return sentinelReset(1, pattern);
    }

    public Long sentinelReset(int retries, String pattern) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.sentinelReset(pattern);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public List<Map<String,String>> sentinelSlaves(String masterName) {
        return sentinelSlaves(1, masterName);
    }

    public List<Map<String,String>> sentinelSlaves(int retries, String masterName) {
        List<Map<String,String>> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.sentinelSlaves(masterName);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String sentinelFailover(String masterName) {
        return sentinelFailover(1, masterName);
    }

    public String sentinelFailover(int retries, String masterName) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.sentinelFailover(masterName);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String sentinelMonitor(String masterName, String ip, int port, int quorum) {
        return sentinelMonitor(1, masterName, ip, port, quorum);
    }

    public String sentinelMonitor(int retries, String masterName, String ip, int port, int quorum) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.sentinelMonitor(masterName, ip, port, quorum);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String sentinelRemove(String masterName) {
        return sentinelRemove(1, masterName);
    }

    public String sentinelRemove(int retries, String masterName) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.sentinelRemove(masterName);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String sentinelSet(String masterName, Map<String,String> parameterMap) {
        return sentinelSet(1, masterName, parameterMap);
    }

    public String sentinelSet(int retries, String masterName, Map<String,String> parameterMap) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.sentinelSet(masterName, parameterMap);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public byte[] dump(String key) {
        return dump(1, key);
    }

    public byte[] dump(int retries, String key) {
        byte[] result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.dump(key);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String restore(String key, int ttl, byte[] serializedValue) {
        return restore(1, key, ttl, serializedValue);
    }

    public String restore(int retries, String key, int ttl, byte[] serializedValue) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.restore(key, ttl, serializedValue);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long pexpire(String key, long milliseconds) {
        return pexpire(1, key, milliseconds);
    }

    public Long pexpire(int retries, String key, long milliseconds) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.pexpire(key, milliseconds);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long pexpireAt(String key, long millisecondsTimestamp) {
        return pexpireAt(1, key, millisecondsTimestamp);
    }

    public Long pexpireAt(int retries, String key, long millisecondsTimestamp) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.pexpireAt(key, millisecondsTimestamp);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long pttl(String key) {
        return pttl(1, key);
    }

    public Long pttl(int retries, String key) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.pttl(key);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String psetex(String key, long milliseconds, String value) {
        return psetex(1, key, milliseconds, value);
    }

    public String psetex(int retries, String key, long milliseconds, String value) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.psetex(key, milliseconds, value);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String set(String key, String value, String nxxx) {
        return set(1, key, value, nxxx);
    }

    public String set(int retries, String key, String value, String nxxx) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.set(key, value, nxxx);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String set(String key, String value, String nxxx, String expx, int time) {
        return set(1, key, value, nxxx, expx, time);
    }

    public String set(int retries, String key, String value, String nxxx, String expx, int time) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.set(key, value, nxxx, expx, time);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String clientKill(String client) {
        return clientKill(1, client);
    }

    public String clientKill(int retries, String client) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.clientKill(client);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String clientSetname(String name) {
        return clientSetname(1, name);
    }

    public String clientSetname(int retries, String name) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.clientSetname(name);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String migrate(String host, int port, String key, int destinationDb, int timeout) {
        return migrate(1, host, port, key, destinationDb, timeout);
    }

    public String migrate(int retries, String host, int port, String key, int destinationDb, int timeout) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.migrate(host, port, key, destinationDb, timeout);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public ScanResult<String> scan(String cursor) {
        return scan(1, cursor);
    }

    public ScanResult<String> scan(int retries, String cursor) {
        ScanResult<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.scan(cursor);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public ScanResult<String> scan(String cursor, ScanParams params) {
        return scan(1, cursor, params);
    }

    public ScanResult<String> scan(int retries, String cursor, ScanParams params) {
        ScanResult<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.scan(cursor, params);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public ScanResult<Map.Entry<String,String>> hscan(String key, String cursor) {
        return hscan(1, key, cursor);
    }

    public ScanResult<Map.Entry<String,String>> hscan(int retries, String key, String cursor) {
        ScanResult<Map.Entry<String,String>> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.hscan(key, cursor);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public ScanResult<Map.Entry<String,String>> hscan(String key, String cursor, ScanParams params) {
        return hscan(1, key, cursor, params);
    }

    public ScanResult<Map.Entry<String,String>> hscan(int retries, String key, String cursor, ScanParams params) {
        ScanResult<Map.Entry<String,String>> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.hscan(key, cursor, params);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public ScanResult<String> sscan(String key, String cursor) {
        return sscan(1, key, cursor);
    }

    public ScanResult<String> sscan(int retries, String key, String cursor) {
        ScanResult<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.sscan(key, cursor);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public ScanResult<String> sscan(String key, String cursor, ScanParams params) {
        return sscan(1, key, cursor, params);
    }

    public ScanResult<String> sscan(int retries, String key, String cursor, ScanParams params) {
        ScanResult<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.sscan(key, cursor, params);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public ScanResult<Tuple> zscan(String key, String cursor) {
        return zscan(1, key, cursor);
    }

    public ScanResult<Tuple> zscan(int retries, String key, String cursor) {
        ScanResult<Tuple> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zscan(key, cursor);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public ScanResult<Tuple> zscan(String key, String cursor, ScanParams params) {
        return zscan(1, key, cursor, params);
    }

    public ScanResult<Tuple> zscan(int retries, String key, String cursor, ScanParams params) {
        ScanResult<Tuple> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.zscan(key, cursor, params);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String clusterNodes() {
        return clusterNodes(1);
    }

    public String clusterNodes(int retries) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.clusterNodes();
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String readonly() {
        return readonly(1);
    }

    public String readonly(int retries) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.readonly();
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String clusterMeet(String ip, int port) {
        return clusterMeet(1, ip, port);
    }

    public String clusterMeet(int retries, String ip, int port) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.clusterMeet(ip, port);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String clusterReset(Reset resetType) {
        return clusterReset(1, resetType);
    }

    public String clusterReset(int retries, Reset resetType) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.clusterReset(resetType);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String clusterAddSlots(int... slots) {
        return clusterAddSlots(1, slots);
    }

    public String clusterAddSlots(int retries, int... slots) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.clusterAddSlots(slots);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String clusterDelSlots(int... slots) {
        return clusterDelSlots(1, slots);
    }

    public String clusterDelSlots(int retries, int... slots) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.clusterDelSlots(slots);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String clusterInfo() {
        return clusterInfo(1);
    }

    public String clusterInfo(int retries) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.clusterInfo();
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public List<String> clusterGetKeysInSlot(int slot, int count) {
        return clusterGetKeysInSlot(1, slot, count);
    }

    public List<String> clusterGetKeysInSlot(int retries, int slot, int count) {
        List<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.clusterGetKeysInSlot(slot, count);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String clusterSetSlotNode(int slot, String nodeId) {
        return clusterSetSlotNode(1, slot, nodeId);
    }

    public String clusterSetSlotNode(int retries, int slot, String nodeId) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.clusterSetSlotNode(slot, nodeId);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String clusterSetSlotMigrating(int slot, String nodeId) {
        return clusterSetSlotMigrating(1, slot, nodeId);
    }

    public String clusterSetSlotMigrating(int retries, int slot, String nodeId) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.clusterSetSlotMigrating(slot, nodeId);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String clusterSetSlotImporting(int slot, String nodeId) {
        return clusterSetSlotImporting(1, slot, nodeId);
    }

    public String clusterSetSlotImporting(int retries, int slot, String nodeId) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.clusterSetSlotImporting(slot, nodeId);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String clusterSetSlotStable(int slot) {
        return clusterSetSlotStable(1, slot);
    }

    public String clusterSetSlotStable(int retries, int slot) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.clusterSetSlotStable(slot);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String clusterForget(String nodeId) {
        return clusterForget(1, nodeId);
    }

    public String clusterForget(int retries, String nodeId) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.clusterForget(nodeId);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String clusterFlushSlots() {
        return clusterFlushSlots(1);
    }

    public String clusterFlushSlots(int retries) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.clusterFlushSlots();
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long clusterKeySlot(String key) {
        return clusterKeySlot(1, key);
    }

    public Long clusterKeySlot(int retries, String key) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.clusterKeySlot(key);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long clusterCountKeysInSlot(int slot) {
        return clusterCountKeysInSlot(1, slot);
    }

    public Long clusterCountKeysInSlot(int retries, int slot) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.clusterCountKeysInSlot(slot);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String clusterSaveConfig() {
        return clusterSaveConfig(1);
    }

    public String clusterSaveConfig(int retries) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.clusterSaveConfig();
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String clusterReplicate(String nodeId) {
        return clusterReplicate(1, nodeId);
    }

    public String clusterReplicate(int retries, String nodeId) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.clusterReplicate(nodeId);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public List<String> clusterSlaves(String nodeId) {
        return clusterSlaves(1, nodeId);
    }

    public List<String> clusterSlaves(int retries, String nodeId) {
        List<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.clusterSlaves(nodeId);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String clusterFailover() {
        return clusterFailover(1);
    }

    public String clusterFailover(int retries) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.clusterFailover();
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public List<Object> clusterSlots() {
        return clusterSlots(1);
    }

    public List<Object> clusterSlots(int retries) {
        List<Object> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.clusterSlots();
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String asking() {
        return asking(1);
    }

    public String asking(int retries) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.asking();
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public List<String> pubsubChannels(String pattern) {
        return pubsubChannels(1, pattern);
    }

    public List<String> pubsubChannels(int retries, String pattern) {
        List<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.pubsubChannels(pattern);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long pubsubNumPat() {
        return pubsubNumPat(1);
    }

    public Long pubsubNumPat(int retries) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.pubsubNumPat();
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Map<String,String> pubsubNumSub(String... channels) {
        return pubsubNumSub(1, channels);
    }

    public Map<String,String> pubsubNumSub(int retries, String... channels) {
        Map<String,String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.pubsubNumSub(channels);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long pfadd(String key, String... elements) {
        return pfadd(1, key, elements);
    }

    public Long pfadd(int retries, String key, String... elements) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.pfadd(key, elements);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public long pfcount(String key) {
        return pfcount(1, key);
    }

    public long pfcount(int retries, String key) {
        long result = 0;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.pfcount(key);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public long pfcount(String... keys) {
        return pfcount(1, keys);
    }

    public long pfcount(int retries, String... keys) {
        long result = 0;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.pfcount(keys);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String pfmerge(String destkey, String... sourcekeys) {
        return pfmerge(1, destkey, sourcekeys);
    }

    public String pfmerge(int retries, String destkey, String... sourcekeys) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.pfmerge(destkey, sourcekeys);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public List<String> blpopWithTimeout(int timeout, String key) {
        return blpopWithTimeout(1, timeout, key);
    }

    public List<String> blpopWithTimeout(int retries, int timeout, String key) {
        List<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.blpop(timeout, key);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public List<String> brpopWithTimeout(int timeout, String key) {
        return brpopWithTimeout(1, timeout, key);
    }

    public List<String> brpopWithTimeout(int retries, int timeout, String key) {
        List<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.brpop(timeout, key);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long geoadd(String key, double longitude, double latitude, String member) {
        return geoadd(1, key, longitude, latitude, member);
    }

    public Long geoadd(int retries, String key, double longitude, double latitude, String member) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.geoadd(key, longitude, latitude, member);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long geoadd(String key, Map<String,GeoCoordinate> memberCoordinateMap) {
        return geoadd(1, key, memberCoordinateMap);
    }

    public Long geoadd(int retries, String key, Map<String,GeoCoordinate> memberCoordinateMap) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.geoadd(key, memberCoordinateMap);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Double geodist(String key, String member1, String member2) {
        return geodist(1, key, member1, member2);
    }

    public Double geodist(int retries, String key, String member1, String member2) {
        Double result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.geodist(key, member1, member2);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Double geodist(String key, String member1, String member2, GeoUnit unit) {
        return geodist(1, key, member1, member2, unit);
    }

    public Double geodist(int retries, String key, String member1, String member2, GeoUnit unit) {
        Double result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.geodist(key, member1, member2, unit);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public List<String> geohash(String key, String... members) {
        return geohash(1, key, members);
    }

    public List<String> geohash(int retries, String key, String... members) {
        List<String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.geohash(key, members);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public List<GeoCoordinate> geopos(String key, String... members) {
        return geopos(1, key, members);
    }

    public List<GeoCoordinate> geopos(int retries, String key, String... members) {
        List<GeoCoordinate> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.geopos(key, members);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit) {
        return georadius(1, key, longitude, latitude, radius, unit);
    }

    public List<GeoRadiusResponse> georadius(int retries, String key, double longitude, double latitude, double radius, GeoUnit unit) {
        List<GeoRadiusResponse> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.georadius(key, longitude, latitude, radius, unit);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
        return georadius(1, key, longitude, latitude, radius, unit, param);
    }

    public List<GeoRadiusResponse> georadius(int retries, String key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
        List<GeoRadiusResponse> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.georadius(key, longitude, latitude, radius, unit, param);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit) {
        return georadiusByMember(1, key, member, radius, unit);
    }

    public List<GeoRadiusResponse> georadiusByMember(int retries, String key, String member, double radius, GeoUnit unit) {
        List<GeoRadiusResponse> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.georadiusByMember(key, member, radius, unit);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit, GeoRadiusParam param) {
        return georadiusByMember(1, key, member, radius, unit, param);
    }

    public List<GeoRadiusResponse> georadiusByMember(int retries, String key, String member, double radius, GeoUnit unit, GeoRadiusParam param) {
        List<GeoRadiusResponse> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.georadiusByMember(key, member, radius, unit, param);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public List<Long> bitfield(String key, String... arguments) {
        return bitfield(1, key, arguments);
    }

    public List<Long> bitfield(int retries, String key, String... arguments) {
        List<Long> result = null;
        for (int i = 0; i <= retries; ++i) {
            try (Jedis jedis = jedisPool.getResource()) {
                result = jedis.bitfield(key, arguments);
            } catch (JedisConnectionException e) {
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    private void throwJCE(int retries, int i, JedisConnectionException e) {
        if (i == retries) {
            throw e;
        }
    }

    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool();
        //jedisPool.getResource().
    }


}
