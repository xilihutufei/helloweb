package pre.fei.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "evn")
public class EvmConfig {

    private String imgPath;
    private String redisIP;
    private Integer redisPort;
    private String passwd;
    private String showImgPath;

    public String getShowImgPath() {
        return showImgPath;
    }

    public void setShowImgPath(String showImgPath) {
        this.showImgPath = showImgPath;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public EvmConfig() {
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getRedisIP() {
        return redisIP;
    }

    public void setRedisIP(String redisIP) {
        this.redisIP = redisIP;
    }

    public Integer getRedisPort() {
        return redisPort;
    }

    public void setRedisPort(Integer redisPort) {
        this.redisPort = redisPort;
    }

    @Override
    public String toString() {
        return "EvmConfig{" +
                "imgPath='" + imgPath + '\'' +
                ", redisIP='" + redisIP + '\'' +
                ", redisPort=" + redisPort +
                '}';
    }
}
