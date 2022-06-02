package org.sherlock;

import org.sherlock.products.Product;
import org.sherlock.products.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = "/http_servlet_fruits/*")
public class HttpServletProducts extends HttpServlet {
    private ProductRepository productRepository;

    private static final Pattern PARAM_PATTERN = Pattern.compile("\\/(\\d+)");

    @Override
    public void init() throws ServletException {
        initProductRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //initProductRepository();


        if(req.getPathInfo()==null || req.getPathInfo().equals("/")){
            PrintWriter wr = resp.getWriter();
            wr.println("<table>");
            wr.println("<tr>");
            wr.println("<th>Id</th>");
            wr.println("<th>ProductName</th>");
            wr.println("<th>Cost</th>");
            wr.println("</tr>");
            for(Product product : productRepository.findAll()){
                outWrInfo(wr, product);
            }
            wr.println("</table>");
        } else{
            Matcher matcher = PARAM_PATTERN.matcher(req.getPathInfo());
            if(matcher.matches()){
                long idProduct = Long.parseLong(matcher.group(1));
                Product product = productRepository.findById(idProduct);
                if(product == null){
                    resp.getWriter().println("Product not found");
                    resp.setStatus(404);
                    return;
                }
                resp.getWriter().println("<p>Id: " + product.getId() + "</p>");
                resp.getWriter().println("<p>Product: " + product.getProductName() + "</p>");
            }else{
                resp.getWriter().println("Bad parameter value");
                resp.setStatus(400);
            }
        }
    }

    private void outWrInfo(PrintWriter wr, Product product){
        wr.println("<tr>");
        wr.println("<th>" + product.getId() + "</th>");
        wr.println("<th>" + product.getProductName() + "</th>");
        wr.println("<th>" + product.getCost() + "</th>");
        wr.println("</tr>");
    }

    private void initProductRepository(){
        productRepository = new ProductRepository();
        productRepository.insert(new Product("Orange","Orange",20));
        productRepository.insert(new Product("Apple","Apple",10.5));
        productRepository.insert(new Product("Melon","Melon",8.9));
        productRepository.insert(new Product("Lemon","Lemon",5.0));
        productRepository.insert(new Product("Pear","Pear",12));
        productRepository.insert(new Product("Banana","Banana",5.5));
        productRepository.insert(new Product("Kiwi","Kiwi",7));
        productRepository.insert(new Product("Mandarin","Mandarin",4));
        productRepository.insert(new Product("Pineapple","Pineapple",15));
        productRepository.insert(new Product("Coconut","Coconut",17));
    }
}
