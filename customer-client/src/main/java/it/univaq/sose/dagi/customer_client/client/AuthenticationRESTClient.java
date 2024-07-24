package it.univaq.sose.dagi.customer_client.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.client.ClientConfiguration;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;

import io.swagger.api.AuthenticationApi;
import io.swagger.model.authentication_provider.Credentials;
import io.swagger.model.authentication_provider.Customer;
import it.univaq.sose.dagi.customer_client.jackson.DateCompatibleJacksonJsonProvider;

public class AuthenticationRESTClient {
	
	//Singleton
	private static AuthenticationRESTClient instance = null;
	
	private AuthenticationApi api;
	
	private AuthenticationRESTClient() {
		setup();
	}
	
	public static AuthenticationRESTClient getInstance() {
		if(instance == null) {
			instance = new AuthenticationRESTClient();
		}
		return instance;
	}
	
	private void setup() {
        DateCompatibleJacksonJsonProvider provider = new DateCompatibleJacksonJsonProvider();
        List providers = new ArrayList();
        providers.add(provider);
        
        api = JAXRSClientFactory.create("http://localhost:8080/api/", AuthenticationApi.class, providers);
        org.apache.cxf.jaxrs.client.Client client = WebClient.client(api);
        
        ClientConfiguration config = WebClient.getConfig(client);
        config.getOutInterceptors().add(new LoggingOutInterceptor());
        config.getInInterceptors().add(new LoggingInInterceptor());
    }
	
	public Long signupCustomer(Customer customer) {
		Long id = api.signUpCustomer(customer);
		return id;
	}
	
	public Long signinCustomer(Credentials credentials) {
		Long id = api.signInCustomer(credentials);
		return id;
	}
}
