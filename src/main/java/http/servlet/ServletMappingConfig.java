package http.servlet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kuangjunlin
 */
public class ServletMappingConfig {
    public static List<ServletMapping> servletMappingList = new ArrayList<ServletMapping>();

    static {

        servletMappingList.add(new ServletMapping(
                "helloCrown",
                "/hello",
                "http.servlet.HelloServlet"));
    }
}
