package self.aub.product.redis.client;

import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.Client;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.params.geo.GeoRadiusParam;
import redis.clients.jedis.params.sortedset.ZAddParams;
import redis.clients.jedis.params.sortedset.ZIncrByParams;
import redis.clients.util.Hashing;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by liujinxin on 2016/12/12.
 */
public class ShardedRedisClient {
    private ShardedJedis jedis;
    private Hashing algo;
    private Pattern keyTagPattern;

    public ShardedRedisClient(ShardedJedis jedis) {
        this.jedis = jedis;
    }

    public ShardedRedisClient(ShardedJedis jedis, Hashing algo, Pattern keyTagPattern) {
        this.jedis = jedis;
        this.algo = algo;
        this.keyTagPattern = keyTagPattern;
    }

    public String set(String key, String value) {
        return set(1, key, value);
    }

    public String set(int retries, String key, String value) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try {
                result = jedis.set(key, value);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.set(key, value, nxxx, expx, time);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.get(key);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.exists(key);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.del(key);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.type(key);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.expire(key, seconds);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.expireAt(key, unixTime);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.ttl(key);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.move(key, dbIndex);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.getSet(key, value);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.setnx(key, value);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.setex(key, seconds, value);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.decrBy(key, integer);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.decr(key);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.incrBy(key, integer);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.incrByFloat(key, value);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.incr(key);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.append(key, value);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.substr(key, start, end);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.hset(key, field, value);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.hget(key, field);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.hsetnx(key, field, value);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public String hmset(String key, Map<String, String> hash) {
        return hmset(1, key, hash);
    }

    public String hmset(int retries, String key, Map<String, String> hash) {
        String result = null;
        for (int i = 0; i <= retries; ++i) {
            try {
                result = jedis.hmset(key, hash);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.hmget(key, fields);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.hincrBy(key, field, value);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.hincrByFloat(key, field, value);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.hexists(key, field);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.hdel(key, fields);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.hlen(key);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.hkeys(key);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.hvals(key);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Map<String, String> hgetAll(String key) {
        return hgetAll(1, key);
    }

    public Map<String, String> hgetAll(int retries, String key) {
        Map<String, String> result = null;
        for (int i = 0; i <= retries; ++i) {
            try {
                result = jedis.hgetAll(key);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.rpush(key, strings);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.lpush(key, strings);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.llen(key);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.lrange(key, start, end);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.ltrim(key, start, end);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.lindex(key, index);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.lset(key, index, value);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.lrem(key, count, value);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.lpop(key);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.rpop(key);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.sadd(key, members);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.smembers(key);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.srem(key, members);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.spop(key);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.spop(key, count);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.scard(key);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.sismember(key, member);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.srandmember(key);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.srandmember(key, count);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zadd(key, score, member);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zadd(key, score, member, params);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long zadd(String key, Map<String, Double> scoreMembers) {
        return zadd(1, key, scoreMembers);
    }

    public Long zadd(int retries, String key, Map<String, Double> scoreMembers) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try {
                result = jedis.zadd(key, scoreMembers);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long zadd(String key, Map<String, Double> scoreMembers, ZAddParams params) {
        return zadd(1, key, scoreMembers, params);
    }

    public Long zadd(int retries, String key, Map<String, Double> scoreMembers, ZAddParams params) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try {
                result = jedis.zadd(key, scoreMembers, params);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zrange(key, start, end);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zrem(key, members);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zincrby(key, score, member);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zincrby(key, score, member, params);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zrank(key, member);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zrevrank(key, member);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zrevrange(key, start, end);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zrangeWithScores(key, start, end);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zrevrangeWithScores(key, start, end);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zcard(key);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zscore(key, member);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.sort(key);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.sort(key, sortingParameters);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zcount(key, min, max);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zcount(key, min, max);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zrangeByScore(key, min, max);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zrangeByScore(key, min, max);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zrangeByScore(key, min, max, offset, count);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zrangeByScore(key, min, max, offset, count);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zrangeByScoreWithScores(key, min, max);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zrangeByScoreWithScores(key, min, max);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zrangeByScoreWithScores(key, min, max, offset, count);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zrangeByScoreWithScores(key, min, max, offset, count);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zrevrangeByScore(key, max, min);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zrevrangeByScore(key, max, min);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zrevrangeByScore(key, max, min, offset, count);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zrevrangeByScoreWithScores(key, max, min);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zrevrangeByScore(key, max, min, offset, count);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zrevrangeByScoreWithScores(key, max, min);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zremrangeByRank(key, start, end);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zremrangeByScore(key, start, end);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zremrangeByScore(key, start, end);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zlexcount(key, min, max);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zrangeByLex(key, min, max);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zrangeByLex(key, min, max, offset, count);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zrevrangeByLex(key, max, min);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zrevrangeByLex(key, max, min, offset, count);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zremrangeByLex(key, min, max);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.strlen(key);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.lpushx(key, string);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.persist(key);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.rpushx(key, string);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.echo(string);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.linsert(key, where, pivot, value);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.setbit(key, offset, value);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.setbit(key, offset, value);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.getbit(key, offset);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.setrange(key, offset, value);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.getrange(key, startOffset, endOffset);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.bitpos(key, value);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.bitpos(key, value, params);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.bitcount(key);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.bitcount(key, start, end);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.pexpire(key, milliseconds);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.pexpireAt(key, millisecondsTimestamp);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.pttl(key);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.psetex(key, milliseconds, value);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.set(key, value, nxxx);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.set(key, value, nxxx, expx, time);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public ScanResult<Map.Entry<String, String>> hscan(String key, String cursor) {
        return hscan(1, key, cursor);
    }

    public ScanResult<Map.Entry<String, String>> hscan(int retries, String key, String cursor) {
        ScanResult<Map.Entry<String, String>> result = null;
        for (int i = 0; i <= retries; ++i) {
            try {
                result = jedis.hscan(key, cursor);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public ScanResult<Map.Entry<String, String>> hscan(String key, String cursor, ScanParams params) {
        return hscan(1, key, cursor, params);
    }

    public ScanResult<Map.Entry<String, String>> hscan(int retries, String key, String cursor, ScanParams params) {
        ScanResult<Map.Entry<String, String>> result = null;
        for (int i = 0; i <= retries; ++i) {
            try {
                result = jedis.hscan(key, cursor, params);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.sscan(key, cursor);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.sscan(key, cursor, params);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zscan(key, cursor);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.zscan(key, cursor, params);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.pfadd(key, elements);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.pfcount(key);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.blpop(timeout, key);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.brpop(timeout, key);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.geoadd(key, longitude, latitude, member);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    public Long geoadd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
        return geoadd(1, key, memberCoordinateMap);
    }

    public Long geoadd(int retries, String key, Map<String, GeoCoordinate> memberCoordinateMap) {
        Long result = null;
        for (int i = 0; i <= retries; ++i) {
            try {
                result = jedis.geoadd(key, memberCoordinateMap);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.geodist(key, member1, member2);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.geodist(key, member1, member2, unit);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.geohash(key, members);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.geopos(key, members);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.georadius(key, longitude, latitude, radius, unit);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.georadius(key, longitude, latitude, radius, unit, param);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.georadiusByMember(key, member, radius, unit);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.georadiusByMember(key, member, radius, unit, param);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
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
            try {
                result = jedis.bitfield(key, arguments);
            } catch (JedisConnectionException e) {
                rebuild(jedis);
                throwJCE(retries, i, e);
            }
        }
        return result;
    }

    private void rebuild(ShardedJedis jedis) {
        Collection<JedisShardInfo> shardInfoCollection = jedis.getAllShardInfo();
        List<JedisShardInfo> shardInfoList = Arrays.asList(shardInfoCollection.toArray(new JedisShardInfo[shardInfoCollection.size()]));
        if (algo == null && keyTagPattern == null) {
            this.jedis = new ShardedJedis(shardInfoList);
        } else if (algo != null && keyTagPattern == null) {
            this.jedis = new ShardedJedis(shardInfoList, algo);
        } else if (algo == null && keyTagPattern != null) {
            this.jedis = new ShardedJedis(shardInfoList, keyTagPattern);
        } else {
            this.jedis = new ShardedJedis(shardInfoList, algo, keyTagPattern);
        }
    }

    private void throwJCE(int retries, int i, JedisConnectionException e) {
        if (i == retries) {
            throw e;
        }
    }
}