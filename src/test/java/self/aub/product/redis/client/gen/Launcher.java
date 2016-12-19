package self.aub.product.redis.client.gen;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import java.io.StringWriter;
import java.util.List;

/**
 * Created by liujinxin on 2016/12/13.
 */
public class Launcher {

    private static Template getTemplate(String templateFile) {
        Template template = null;
        try {
            template = Velocity.getTemplate(templateFile);
        } catch (ResourceNotFoundException rnfe) {
            rnfe.printStackTrace();
        } catch (ParseErrorException pee) {
            pee.printStackTrace();
        }
        return template;
    }

    public static void main(String[] args) {
        VelocityConfig.init();

        String methodFilePath = "/Users/liujinxin/Workspace/GitSelf/redis-client/src/test/resources/redis-method.data";
        MethodReader methodReader = new MethodReader(methodFilePath);
        List<MethodBean> methodBeens = methodReader.readMethod();
        VelocityContext context = new VelocityContext();
        context.put("methodBeens", methodBeens);

        //Template template = getTemplate("method-jedis.vm");
        //Template template = getTemplate("method-jedis-pool.vm");
        Template template = getTemplate("method-jedis-cluster.vm");

        StringWriter stringWriter = new StringWriter();
        template.merge(context, stringWriter);
        System.out.println(stringWriter.toString());
    }
}
