package com.yatra.AsycnRestCallClient;

import java.util.concurrent.Future;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * Hello world!
 *
 */
public class RestClient 
{
    public static void main( String[] args ) throws Exception
    {
    	Response rs=new RestClient().test();
		System.out.println(rs.getEntity().toString());
    }
    
    
    public Response test() throws Exception{
	    Client client = ClientBuilder.newBuilder().build();
	    WebTarget target = client.target("https://api.github.com/search/users?q=anubhav3008");
	    
	    Invocation.Builder reqBuilder = target.request();
	    AsyncInvoker asyncInvoker = reqBuilder.async();
	    Future<Response> futureResp = asyncInvoker.get();
	    
	    Response response = futureResp.get(); //blocks until client responds or times out
	    
	    String responseBody = response.readEntity(String.class);
	    return Response.status(response.getStatus()).entity(responseBody).build();
	}
}
