package com.tiagokihara.application.resources;

import com.tiagokihara.domain.entities.Fruit;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import java.net.URI;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/fruits")
@ApplicationScoped
public class FruitResource {
  
  @GET
  public Uni<List<Fruit>> get() {
    return Fruit.listAll(Sort.by("name"));
  }
  
  @GET
  @Path("/{id}")
  public Uni<Fruit> getSingle(Long id) {
    return Fruit.findById(id);
  }
  
  @POST
  public Uni<Response> create(Fruit fruit) {
    return Panache.<Fruit>withTransaction(fruit::persist)
        .map(inserted -> Response.created(URI.create("/fruits/" + inserted.id)).build());
  }
  
}
