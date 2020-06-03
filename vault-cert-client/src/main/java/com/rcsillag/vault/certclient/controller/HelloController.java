package com.rcsillag.vault.certclient.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class provides the methods for handling incoming requests.
 */
@RestController
@RequestMapping(value="/hello")
public class HelloController {

    /**
     * Returns the string 'Hello ' concatenated with the name got as an argument.
     * @param name The name to greet.
     * @return 'Hello ' concatenated with the name got as an argument.
     */
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String sayHello(@PathVariable(name="name")String name){
        return "Hello " + name;
    }
}
