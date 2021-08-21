package com.template.webserver.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping(value={"/login", "/"})
    public String welcome(Map<String, Object> model) {
        return "login";
    }

    @PostMapping(value="/loginUser")
    private ResponseEntity<User> login(HttpServletRequest request, HttpServletResponse response, @RequestBody User user, Model model){
        User result = userService.login(user);
       if(result != null){

            HttpSession session = request.getSession();
            session.setAttribute("username",result.getUsername() );
            session.setAttribute("role",result.getRole());
            model.addAttribute("username", user.getUsername());

            return new ResponseEntity(result, HttpStatus.OK);

       }else {

            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
           }

    }

    @GetMapping(value="/logout")
    private ResponseEntity logout(HttpServletRequest request){

            HttpSession session = request.getSession();
            session.invalidate();
            return new ResponseEntity(HttpStatus.OK);
        }

    @GetMapping(value="/partyDashboard")
    private String partyDashboard(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String resultView = null;
        if(request != null) {

            if (request.getSession().getAttribute("role").equals("PARTY")) {
                modelAndView.addObject("user", "party");
                modelAndView.setViewName("partydashboard");
                System.out.println("party");

                resultView = "partydashboard";
            }
        }else {
            resultView = "login";
        }

        return resultView;

    }
        @GetMapping(value="/bankDashboard")
        private String bankDashboard(HttpServletRequest request){
            ModelAndView modelAndView= new ModelAndView();
        if(request != null){
            if(request.getSession().getAttribute("role").equals("BANK")) {
                modelAndView.addObject("user", "bank");
                modelAndView.setViewName("bankdashboard");
                System.out.println("bank");
                return "bankdashboard";
            }
        }
                return "login";

    }

    @GetMapping(value = "/home")
    public ModelAndView test(){
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.addObject("user","varma");
        modelAndView.setViewName("abc");
        return modelAndView;
    }

    @GetMapping(value = "/home1")
    private String test1(){
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.addObject("user","varma");
        modelAndView.setViewName("login");
        return "login";
    }
}
