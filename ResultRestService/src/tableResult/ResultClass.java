package tableResult;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
 
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
 
 
@Path("/skill_id-search")
public class ResultClass {
 
 // This method is called if JSON is request
 @GET
  
 @Path("/json/{id : (\\w+)?}")
 public Response getCustomerNameJson(@PathParam("skill_id") String id,@QueryParam("id")String ids) throws JSONException {
 
 if(id == null || id.trim().length() == 0) {
 return Response.serverError().entity("id No cannot be blank").build();
 }
 
 
 JSONArray jArray= new JSONArray();
 
 List<DataEntity> DataEntity = getCustomerList(id);
 for(DataEntity entity:DataEntity){
 JSONObject jObj = new JSONObject();
 
 }
 
 
 
 if(jArray.toString() == null || jArray.toString() == "") {
 return Response.status(Response.Status.NOT_FOUND).entity("Data not found for ID: " + id).build();
 }
 
 return Response.ok(jArray.toString(), MediaType.APPLICATION_JSON).build(); 
 }
 
 private List<DataEntity> getCustomerList(String customerNo){
 
 List<DataEntity> customerList = new ArrayList<DataEntity>();
 
 
  
 
  
 
 
 return customerList;
 
 }
 
}