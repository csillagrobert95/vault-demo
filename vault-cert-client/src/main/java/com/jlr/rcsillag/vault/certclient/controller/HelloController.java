package com.jlr.rcsillag.vault.certclient.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Robi on 5/27/2020.
 */
@RestController
@RequestMapping(value="/hello")
public class HelloController {
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String sayHello(@PathVariable(name="name")String name){
        return "Hello " + name;
    }
}
