package com.esliceu.socket.socketserver;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class Controller {

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "index";
    }
}
