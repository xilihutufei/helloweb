package pre.fei.util;

import org.springframework.util.ResourceUtils;
import pre.fei.exceptions.RequestException;
import pre.fei.vo.IConst;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Objects;

public class ParamUtil {

    public static String getUser(HttpServletRequest request){
        if (request == null){
            return null;
        }
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            if (Objects.equals(cookie.getName(), IConst.COOKIE_USER)) {
                return cookie.getValue();
            }
        }
        return null;

    }



    public static void main(String[] args) {
        try {
            String path = ResourceUtils.getURL("classpath:").getPath();
            System.out.println(path);
        }catch (Exception e){
            e.printStackTrace();
        }
    }




}
