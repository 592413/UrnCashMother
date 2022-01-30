package com.redbus.util;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.ClientFilter;
import com.sun.jersey.oauth.client.OAuthClientFilter;
import com.sun.jersey.oauth.signature.OAuthParameters;
import com.sun.jersey.oauth.signature.OAuthSecrets;


public class WebServiceClient {
	public static ClientResponse callGet(String url)
	{
		Client client=Client.create();
		OAuthParameters params = new OAuthParameters().signatureMethod("HMAC-SHA1").consumerKey(Constants.CONSUMER_KEY).version("1.0");
		OAuthSecrets secrets = new OAuthSecrets().consumerSecret(Constants.CONSUMER_SECRET);
		OAuthClientFilter filter = new OAuthClientFilter(client.getProviders(), params, secrets);
		client.addFilter((ClientFilter)filter);
		WebResource webResource=client.resource(url);
		ClientResponse response=webResource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		return response;
	}
	public static ClientResponse callPost(String url,String param)
	{
		Client client=Client.create();
		OAuthParameters params = new OAuthParameters().signatureMethod("HMAC-SHA1").consumerKey(Constants.CONSUMER_KEY).version("1.0");
		OAuthSecrets secrets = new OAuthSecrets().consumerSecret(Constants.CONSUMER_SECRET);
		OAuthClientFilter filter = new OAuthClientFilter(client.getProviders(), params, secrets);
		client.addFilter((ClientFilter)filter);
		WebResource webResource=client.resource(url);
		ClientResponse response=null;
		response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, param);
		return response;
	}
}
