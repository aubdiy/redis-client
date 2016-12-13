package self.aub.product.redis.client.gen;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liujinxin on 2016/12/12.
 */
public class MethodReader {
    private String methodFilePath;

    public MethodReader(String methodFilePath) {
        this.methodFilePath = methodFilePath;
    }

    public List<MethodBean> readMethod() {
        Path path = Paths.get(methodFilePath);
        List<String> codes = null;
        try {
            codes = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<MethodBean> methods = new ArrayList<>(codes.size());
        for (String line : codes) {
            int paramStartIndex = line.indexOf("(");
            String baseInfo = line.substring(0, paramStartIndex);
            String[] baseInfoArr = baseInfo.split(" ");
            String methodName = baseInfoArr[1];
            String methodReturnType = baseInfoArr[0];

            if (line.length() == paramStartIndex + 1) {
                methods.add(new MethodBean(methodName, methodReturnType, "", ""));
            } else {
                String parameterWithType = line.substring(paramStartIndex + 1);
                StringBuilder parameterWithoutType = new StringBuilder();
                String[] paramArr = parameterWithType.split(", ");
                for (String paramInfo : paramArr) {
                    String[] paramInfoArr = paramInfo.split(" ");
                    String paramName = paramInfoArr[1];
                    parameterWithoutType.append(paramName).append(", ");
                }
                parameterWithoutType.setLength(parameterWithoutType.length() - 2);
                methods.add(new MethodBean(methodName, methodReturnType, parameterWithType, parameterWithoutType.toString()));
            }
            // TODO: 2016/12/13
            //break;
        }
        return methods;
    }

    public static void main(String[] args) {
        String methodFilePath = "/Users/liujinxin/Workspace/GitSelf/redis-client/src/test/resources/redis-method.data";
        MethodReader methodReader = new MethodReader(methodFilePath);
        List<MethodBean> methodBeen = methodReader.readMethod();
    }
}
