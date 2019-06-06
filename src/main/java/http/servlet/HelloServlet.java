package http.servlet;

import http.annotation.MyWebServlet;
import http.mytomcat.MyRequest;
import http.mytomcat.MyResponse;
import http.mytomcat.MyServlet;

import java.io.IOException;

/**
 * @author kuangjunlin
 */
@MyWebServlet(name = "/hello")
public class HelloServlet extends MyServlet {
    @Override
    public void doGet(MyRequest request, MyResponse response) {
        try {
            response.write("hello crown by get");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(MyRequest request, MyResponse response) {
        try {
            response.write("hello crown by post");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
