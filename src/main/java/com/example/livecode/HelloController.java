package com.example.livecode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.ConfigValue;
import org.eclipse.microprofile.config.inject.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 *
 */
@Path("/config")
@Singleton
public class HelloController {

    @Inject @ConfigProperties CustomerDetails customer;
    @Inject @ConfigProperties(prefix="client") CustomerDetails client;

    @Inject @ConfigProperty(name = "discount") ConfigValue discountCv;
    
    @GET
    public String sayHello() {
        return "Today's discount for ice creams is " + discountCv.getValue() + "%! from the config source: " + discountCv.getSourceName() + " its ordinal: " + discountCv.getSourceOrdinal();
    }

    @GET
    @Path("/customer")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<CustomerDetails> displayCustomer() {
        List<CustomerDetails> customers = new ArrayList<>();
        customers.add(customer);
        customers.add(client);
        return customers;
    }
}