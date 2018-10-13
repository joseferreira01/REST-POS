/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.rest.pos.recurso;

import br.edu.ifpb.rest.pos.dominio.Livro;
import br.edu.ifpb.rest.pos.servico.LivroServico;
import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author jose
 */
@Stateless
@Path("livros") // .../vendas
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LivroRecurso implements Serializable{
    @EJB
    private LivroServico servico;
     @GET
    public Response todas(@Context UriInfo uriInfo) {
        List<Livro> livros = servico.todos();
        GenericEntity<List<Livro>> entity = new GenericEntity<List<Livro>>(livros) {
        };
        return Response.ok() //200
                .entity(entity)
                .build();

    }

  @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON})//, MediaType.APPLICATION_XML})
    public Response salvar(Livro integrante, @Context UriInfo uriInfo) {
        Livro entity = this.servico.salvar(integrante);
        String id = String.valueOf(entity.getId());
        URI location = uriInfo.getAbsolutePathBuilder().path(id).build();
        return Response.created(location)
//                .header("Access-Control-Allow-Origin", "*")
                .entity(entity)
                .build();
    }
 @GET
    @Path("{id}") //http://localhost:8080/dac-rest/api/integrantes/1
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response livro(@PathParam("id") String id,@Context UriInfo uriInfo) {
        
        Optional<Livro> entity = this.servico.LivroComId(id);
         String id2 = String.valueOf(id);
        URI location = uriInfo.getAbsolutePathBuilder().path(id).build();
        Response.created(location)
//                .header("Access-Control-Allow-Origin", "*")
                .entity(entity)
                .build();

        if (!entity.isPresent()) {
            return Response.noContent()// 200
                    .build();
        }

        return Response.ok() // 200
//                .header("Access-Control-Allow-Origin", "*")
                .entity(entity.get())
                .build();
    }
    @DELETE
    @Path("{id}") //http://localhost:8080/dac-rest/api/integrantes/1
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response removerLivro(@PathParam("id") String id,@Context UriInfo uriInfo) {
        Optional<Livro> entity = this.servico.removerLivroCom(id);
        
        URI location = uriInfo.getAbsolutePathBuilder().path(id).build();
         Response.created(location)
//                .header("Access-Control-Allow-Origin", "*")
                .entity(entity)
                .build();

        if (!entity.isPresent()) {
            return Response.noContent()// 200
                    .build();
        }

        return Response.ok() // 200
                .entity(entity.get())
                .build();
    }
    @POST
    @Path("{id}") //http://localhost:8080/dac-rest/api/integrantes/1
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response atualizarLivro(
            @PathParam("id") String id, Livro livro,@Context UriInfo uriInfo) {
        Optional<Livro> entity = this.servico.atualizarLivroCom(id, livro);
         
        URI location = uriInfo.getAbsolutePathBuilder().path(id).build();
         Response.created(location)
//                .header("Access-Control-Allow-Origin", "*")
                .entity(entity)
                .build();

        if (!entity.isPresent()) {
            return Response.noContent()// 200
                    .build();
        }

        return Response.ok() // 200
                .entity(entity.get())
                .build();
    }
    
}
