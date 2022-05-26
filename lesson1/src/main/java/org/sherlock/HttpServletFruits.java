package org.sherlock;

import org.sherlock.persists.Fruit;
import org.sherlock.persists.FruitRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/http_servlet_fruits/*")
public class HttpServletFruits extends HttpServlet {
    private FruitRepository fruitRepository;

    @Override
    public void init() throws ServletException {
        fruitRepository = new FruitRepository();
        fruitRepository.insert(new Fruit("Orange"));
        fruitRepository.insert(new Fruit("Apple"));
        fruitRepository.insert(new Fruit("Melon"));
        fruitRepository.insert(new Fruit("Lemon"));
        fruitRepository.insert(new Fruit("Pear"));
        fruitRepository.insert(new Fruit("Banana"));
        fruitRepository.insert(new Fruit("Kiwi"));
        fruitRepository.insert(new Fruit("Mandarin"));
        fruitRepository.insert(new Fruit("Pineapple"));
        fruitRepository.insert(new Fruit("Coconut"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter wr = resp.getWriter();
        wr.println("<table>");
        wr.println("<tr>");
        wr.println("<th>Id</th>");
        wr.println("<th>FruitName</th>");
        wr.println("</tr>");

        if(req.getPathInfo()==null || req.getPathInfo().equals("/")){
            for(Fruit fruit: fruitRepository.findAll()){
                outWrInfo(wr,fruit);
            }
        } else{
            String pathInfo = req.getPathInfo().substring(1);
            int idFruit =  Integer.parseInt(pathInfo);
            Fruit fruit = fruitRepository.findById(idFruit);
            outWrInfo(wr,fruit);
        }
        wr.println("</table>");
    }

    private void outWrInfo(PrintWriter wr, Fruit fruit){
        wr.println("<tr>");
        wr.println("<th>" + fruit.getId() + "</th>");
        wr.println("<th>" + fruit.getFruitName() + "</th>");
        wr.println("</tr>");
    }
}
