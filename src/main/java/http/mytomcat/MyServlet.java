package http.mytomcat;

import http.annotation.MyWebServlet;
import http.mytomcat.MyRequest;
import http.mytomcat.MyResponse;

/**
 * @author kuangjunlin
 */
@MyWebServlet
public abstract class MyServlet {
    public abstract void doGet(MyRequest request, MyResponse response);

    public abstract void doPost(MyRequest request, MyResponse response);

    public void service(MyRequest request, MyResponse response){
        if (request.getMethod().equals("POST")){
            doPost(request, response);
        }else if (request.getMethod().equals("GET")){
            doGet(request, response);
        }
    }
}
