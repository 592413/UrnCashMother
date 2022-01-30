package com.recharge.ApiClient;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class VendorPayApi {

	private static final Logger log = Logger.getLogger(VendorPayApi.class);

	private static final String url = "https://www.bijoukart.in/payment";

	public static String sendRequestToPayVendorWebservice(MultivaluedMap input) {
		String output = "";
		// Client client = null;
		try {
			// client = ClientBuilder.newClient();
			// Response response =
			// client.target(url).request(MediaType.APPLICATION_JSON).accept("text/html").post(Entity.json(input));
			// output = response.readEntity(String.class);

			Client restClient = Client.create();
			WebResource webResource = restClient.resource(url);
			// ClientResponse resp =
			// webResource.accept("text/html").type("application/json").post(ClientResponse.class,input);

			ClientResponse resp = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).accept("text/html")
					.post(ClientResponse.class, input);
			output = resp.getEntity(String.class);
			log.warn("inside sendRequestToPayVendorWebservice method" + output);
		} catch (Exception e) {
			log.error("sendRequestToPayVendorWebservice::::::::::::::::" + e.getMessage());
			return null;
		}
		return output;
	}

}
