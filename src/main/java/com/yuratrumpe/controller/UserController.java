package com.yuratrumpe.controller;


import com.yuratrumpe.model.User;
import com.yuratrumpe.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView main() {

        return new ModelAndView("login_form", "user", new User());
    }

    @RequestMapping(path = "/check-user", method = RequestMethod.POST)
    public String checkUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "login_form";
        }

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
    public String editUserPost(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "user_edit";
        }

        userService.updateUser(user.getId(), user.getUserName(), user.getPassword(), user.getRole());

        return "redirect:/view-users";
    }

    @RequestMapping(path = "/delete-user", method = RequestMethod.GET)
    public String updateUser(@RequestParam("id") Long id) {

        userService.deleteUser(id);

        return "redirect:/view-users";
    }

    @RequestMapping(path = "/add-user-get", method = RequestMethod.GET)
    public ModelAndView addUserGet() {
        return new ModelAndView("user_add", "user", new User());
    }

    @RequestMapping(path = "/add-user-post", method = RequestMethod.POST)
    public String addUserPost(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "user_add";
        }

        userService.addUser(user.getUserName(), user.getPassword(), user.getRole());

        return "redirect:/view-users";
    }
}
