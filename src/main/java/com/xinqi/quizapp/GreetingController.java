package com.xinqi.quizapp;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    private static final String template ="Hello, %s!";
    private final AtomicLong counter=new AtomicLong();
@GetMapping("/greeting")
//Getmapping annotation ensures Http get requests to /greeting are mapped to the greeting() method.
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "world") String name){
    //requestparams binds the value of the query string parameter name into the name parameter of the greeting() method, default value is "world".
    //The implementation of the method body creates ans returns a new Greeting object based on the next value from the conter and formats the given name by using the greeting template.
    // A key difference between a traditional MVC controller and the RESTful web service controller shown earlier is the way that the HTTP response body is created. Rather than relying on a view technology to perform server-side rendering of the greeting data to HTML, this RESTful web service controller populates and returns a Greeting object. The object data will be written directly to the HTTP response as JSON.
    return new Greeting(counter.incrementAndGet(), String.format(template,name));
}
    
}
