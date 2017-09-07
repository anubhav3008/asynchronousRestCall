package com.yatra.AsycnRestCallClient;

import java.util.concurrent.Future;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.Response;

public class AsyncRestCall {
	
	public static void main(String args[]){
		
		Client client = ClientBuilder.newBuilder().build();
		Future<Response> futureResponse = client.target("https://api.github.com/search/users?q=anubhav3008")
			   .request().async().get(new InvocationCallback<Response>() {

				public void completed(Response response) {
					System.out.println("Response code "
							+ response.getStatus() );
					System.out.println(response.readEntity(String.class) );
					
				}

				public void failed(Throwable throwable) {
					System.out.println("Failed");
					throwable.printStackTrace();
					
				}
			});
	}

}
