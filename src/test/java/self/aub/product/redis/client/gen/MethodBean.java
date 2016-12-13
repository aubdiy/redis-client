package self.aub.product.redis.client.gen;

/**
 * Created by liujinxin on 2016/12/12.
 */
public class MethodBean {
    /**
     * 方法名
     */
    private String name;
    /**
     * 返回类型
     */
    private String returnType;


    private String parameterWithType;
    private String parameterWithoutType;

    public MethodBean() {
    }

    public MethodBean(String name, String returnType, String parameterWithType, String parameterWithoutType) {
        this.name = name;
        this.returnType = returnType;
        this.parameterWithType = parameterWithType;
        this.parameterWithoutType = parameterWithoutType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getParameterWithType() {
        return parameterWithType;
    }

    public void setParameterWithType(String parameterWithType) {
        this.parameterWithType = parameterWithType;
    }

    public String getParameterWithoutType() {
        return parameterWithoutType;
    }

    public void setParameterWithoutType(String parameterWithoutType) {
        this.parameterWithoutType = parameterWithoutType;
    }
}
