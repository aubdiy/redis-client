package self.aub.product.redis.client.gen;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.util.Properties;

public class VelocityConfig {

    public static void init() {
        Velocity.init(generateProperties());
    }

    private static Properties generateProperties() {
        Properties p = new Properties();
        p.setProperty(VelocityEngine.INPUT_ENCODING, "UTF-8");
        p.setProperty(VelocityEngine.OUTPUT_ENCODING, "UTF-8");
        String path = VelocityConfig.class.getResource("/").getPath().toString();
        p.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, path.concat("template"));
        return p;
    }

    public static void main(String[] args) {
        init();
        Velocity.getTemplate("method-jedis.vm");
    }

}
