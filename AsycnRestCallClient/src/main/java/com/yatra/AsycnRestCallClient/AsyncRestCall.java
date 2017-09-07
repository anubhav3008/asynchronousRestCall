package com.yatra.AsycnRestCallClient;

import java.util.concurrent.Future;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class AsyncRestCall {
	
	public static void main(String args[]) throws Exception{
		
		Response response;
		response=new AsyncRestCall().test1();
		response=new AsyncRestCall().test2();
		
		//System.out.println(response.readEntity(String.class));
	}
	public Response test1() throws Exception{
		Client client = ClientBuilder.newBuilder().build();
		Future<Response> futureResponse = client.target("https://api.github.com/search/users?q=anubhav3008")
			   .request().async().get(new InvocationCallback<Response>() {

				public void completed(Response response) {
				
					System.out.println("Test1 "+response.readEntity(String.class) );
					
				}

				public void failed(Throwable throwable) {
					System.out.println("Failed");
					throwable.printStackTrace();
					
				}
			});
		return futureResponse.get();
		
		
	}
	
	
	public Response test2() throws Exception{
	    Client client = ClientBuilder.newBuilder().build();
	    WebTarget target = client.target("https://api.github.com/search/users?q=anubhav3008");
	    
	    Invocation.Builder reqBuilder = target.request();
	    AsyncInvoker asyncInvoker = reqBuilder.async();
	    Future<Response> futureResp = asyncInvoker.get();
	    
	    Response response = futureResp.get(); //blocks until client responds or times out
	    
	    String responseBody = response.readEntity(String.class);
	    System.out.println("Test1 "+responseBody);
	    return Response.status(response.getStatus()).entity(responseBody).build();
	}

}
