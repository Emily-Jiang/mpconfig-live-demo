package com.example.livecode;

import javax.enterprise.context.Dependent;

import org.eclipse.microprofile.config.inject.ConfigProperties;

@ConfigProperties(prefix="customer")
@Dependent
public class CustomerDetails {
    public String name;
    public int age;
    public String nationality;
}
