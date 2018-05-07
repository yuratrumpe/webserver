package com.yuratrumpe.controller;


import com.yuratrumpe.model.User;
import com.yuratrumpe.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView main() {

        return new ModelAndView("login_form", "user", new User());
    }

    @RequestMapping(path = "/check-user", method = RequestMethod.POST)
    public String checkUser(@ModelAttribute("user") User user) {

        return "redirect:/view-users";
    }

    @RequestMapping(path = "/view-users", method = RequestMethod.GET)
    public ModelAndView viewUsers() {

        return new ModelAndView("users_view", "usersList", userService.getAllUsers());
    }

    @RequestMapping(path = "/edit-user-get", method = RequestMethod.GET)
    public ModelAndView editUserGet(@RequestParam("id") Long id) {

        return new ModelAndView("user_edit", "user", userService.getUserById(id));
    }

    @RequestMapping(path = "/edit-user-post", method = RequestMethod.POST)
    public String editUserPost(@RequestParam("id") Long id, @RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("role") String role) {

        userService.updateUser(id, name, password, role);

        return "redirect:/view-users";
    }

    @RequestMapping(path = "/delete-user", method = RequestMethod.GET)
    public String updateUser(@RequestParam("id") Long id) {

        userService.deleteUser(id);

        return "redirect:/view-users";
    }

    @RequestMapping(path = "/add-user-get", method = RequestMethod.GET)
    public String addUserGet() {
        return "user_add";
    }

    @RequestMapping(path = "/add-user-post", method = RequestMethod.POST)
    public String addUserPost(@RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("role") String role) {

        userService.addUser(name, password, role);

        return "redirect:/view-users";
    }
}
