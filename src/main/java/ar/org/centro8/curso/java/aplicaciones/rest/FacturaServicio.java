package ar.org.centro8.curso.java.aplicaciones.rest;

import ar.org.centro8.curso.java.aplicaciones.connectors.Connector;
import ar.org.centro8.curso.java.aplicaciones.dao.jdbc.FacturaRepository;
import ar.org.centro8.curso.java.aplicaciones.entities.Factura;
import ar.org.centro8.curso.java.aplicaciones.enumerados.Letra;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/facturas/v1")
public class FacturaServicio {
    @GET
    public String info(){
        return "Servicio de facturas activo";
    }
    
   /*
    http://localhost:8080/Server/facturas/v1/alta?letra=A&numero=16&fecha=12-05-2020&monto=5463&idCliente=2
    */ 
    
    @Path("alta")
    @GET
    public String alta(
            @QueryParam("letra") String letra,
            @QueryParam("numero") String numero,
            @QueryParam("fecha") String fecha,
            @QueryParam("monto") String monto,
            @QueryParam("idCliente") String idCliente
    ){
        FacturaRepository fr=new FacturaRepository(Connector.getConnection());
        Factura factura = new Factura(
                Letra.valueOf(letra.toUpperCase()),
                Integer.parseInt(numero),
                fecha,
                Double.parseDouble(monto),
                Integer.parseInt(idCliente)
        );
        fr.save(factura);
        return factura.getId()+"";
    } 
    
    
    @Path("baja")
    @GET
    public String baja(@QueryParam("letra") String letra,@QueryParam("numero") String numero){
        try {
            FacturaRepository fr = new FacturaRepository(Connector.getConnection());
            fr.remove(fr.getByLetraNumero(Letra.valueOf(letra.toUpperCase() ),Integer.parseInt(numero)));
            return "true";
        } catch (Exception e) {
            return "false";
        }
    }
    
    
    @Path("getAll")
    @GET
    public String getAll(){
        String text="";
        FacturaRepository fr = new FacturaRepository(Connector.getConnection());
        for(Factura f:fr.getAll()) text+=f+"\n";
        return text;
    }
    
    
    @Path("getLikeCliente")
    @GET
    public String getLikeCliente(@QueryParam("idCliente") String idCliente){
        String text="";
        FacturaRepository fr = new FacturaRepository(Connector.getConnection());
        for(Factura f:fr.getByIdCliente(Integer.parseInt(idCliente))) 
            text+=f+"\n";
        return text;
    }
}
