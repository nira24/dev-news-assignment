package se.sdaproject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index(){
        return "Hello World!";
    }
    @RequestMapping("/name")
    public String getName() {
        return "Test";
    }
    @RequestMapping("/name/{input}")
    public String getName(@PathVariable(value="input") String input) {
        return input;
    }

}
