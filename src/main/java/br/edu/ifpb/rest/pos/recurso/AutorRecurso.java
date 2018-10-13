/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.rest.pos.recurso;

import br.edu.ifpb.rest.pos.dominio.Autor;
import br.edu.ifpb.rest.pos.dominio.Livro;
import br.edu.ifpb.rest.pos.servico.AutorServico;
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
@Path("autores") // .../vendas
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AutorRecurso implements Serializable{
    @EJB
    private AutorServico servico;
     @GET
    public Response todas(@Context UriInfo uriInfo) {
        List<Autor> autores = servico.todos();
       GenericEntity<List<Autor>> entity = new GenericEntity<List<Autor>>(autores) {
        };
        return Response.ok() //200
                .entity(entity)
                .build();

    }

  @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON})//, MediaType.APPLICATION_XML})
    public Response salvar(Autor autor, @Context UriInfo uriInfo) {
        Autor entity = this.servico.salvar(autor);
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
    public Response integrante(@PathParam("id") Long id,@Context UriInfo uriInfo) {
        
        Optional<Autor> entity = this.servico.LivroComId(id);
          
        URI location = uriInfo.getAbsolutePathBuilder().path(String.valueOf(id)).build();
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
    public Response removerLivro(@PathParam("id") Long id,@Context UriInfo uriInfo) {
        Optional<Autor> entity = this.servico.removerAutorCom(id);
        
        URI location = uriInfo.getAbsolutePathBuilder().path(String.valueOf(id)).build();
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
    public Response atualizarAutor(
            @PathParam("id") Long id, Autor autor,@Context UriInfo uriInfo) {
        Optional<Autor> entity = this.servico.atualizarAutorCom(id, autor);
         
        URI location = uriInfo.getAbsolutePathBuilder().path(String.valueOf(id)).build();
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
