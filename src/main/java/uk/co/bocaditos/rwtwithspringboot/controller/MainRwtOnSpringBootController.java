package uk.co.bocaditos.rwtwithspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import uk.co.bocaditos.rwtwithspringboot.entrypoints.MainRwtOnSpringBootEntryPoint;


/**
 * Simple Spring MVC controller that redirects to the desired RWT entry-point. Not necessarily 
 * needed, but demonstrates how to easily redirect the root URL of your App to an RWT entry-point.
 */
@Controller
public class MainRwtOnSpringBootController {

    @GetMapping("/")
    public String index() {
        return "redirect:/" + MainRwtOnSpringBootEntryPoint.PATH;
    }

} // end class MainRwtOnSpringBootController
