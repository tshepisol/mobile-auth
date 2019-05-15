package za.co.pps.auth.swagger;


import io.swagger.models.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;



public class Bootstrap extends HttpServlet {


    @Override
    public void init(ServletConfig config) throws ServletException {
        Info info = new Info()
                .description("This is PPS Authentication API")
                .contact(new Contact().email("apiteam@pps.co.za"));

        ServletContext context = config.getServletContext();
        Swagger swagger = new Swagger()
                .info(info);

        swagger.tag(new Tag()
                .name("api")
                .description("Authentication and Token Validation APIs"));



        context.setAttribute("swagger", swagger);
    }
}
