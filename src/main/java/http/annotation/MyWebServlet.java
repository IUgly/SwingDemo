package http.annotation;

import javax.servlet.annotation.WebServlet;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyWebServlet {
    String name() default "";
}
