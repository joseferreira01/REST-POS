/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.rest.pos.autenticar;

import java.io.IOException;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author jose
 */
@WebServlet(name = "Autho", urlPatterns = {"/autho"})
public class Autho extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String codi = request.getParameter("code");
        Client client = ClientBuilder.newClient();
        WebTarget rot = client.target("https://github.com/login/oauth/access_token");
        // montar form
        Form form = new Form("client_id", "a91d9138b14249b9852b")
                .param("client_secret", "429fa1d315c146e4a4f3ef4411d2ef9f70233f56")
                .param("code", codi)
                .param("autho", "repo");

        Entity<Form> entity = Entity.form(form);
        Response post = rot
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .post(entity);
        //pegar json
        JsonObject json = post.readEntity(JsonObject.class);
        WebTarget user = client.target("https://api.github.com/user");
        String token = "token " + json.getString("access_token");
        JsonObject jsonUser = user
                .request()
                .header("Authorization", token)
                .get(JsonObject.class);

        response.getWriter().println(
                //                jsonUser.getString("login")
                jsonUser.get("login"));
        response.sendRedirect("faces/home.xhtml");

    }

}
