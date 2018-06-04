package com.yuratrumpe.controller;


import com.yuratrumpe.model.Role;
import com.yuratrumpe.model.User;
import com.yuratrumpe.services.RoleService;
import com.yuratrumpe.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @RequestMapping(path = {"/login"}, method = RequestMethod.GET)
    public String login() {

        return "login";
    }

    @RequestMapping(path = {"/", "/view-users"}, method = RequestMethod.GET)
    public ModelAndView viewUsers() {

        return new ModelAndView("users_view", "usersList", userService.getAllUsers());
    }

    @RequestMapping(path = "/admin/edit-user", method = RequestMethod.GET)
    public ModelAndView editUser(@RequestParam("id") Long id, ModelMap modelMap) {

        modelMap.addAttribute("user", userService.getUserById(id));
        modelMap.addAttribute("roleList", roleService.getAllRoles());

        return new ModelAndView("user_edit", modelMap);
    }

    @RequestMapping(path = "/admin/edit-user", method = RequestMethod.POST)
    public String editUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, @RequestParam String role) {

//        if (bindingResult.hasErrors()) {
//            return "user_edit";
//        }
        user.setRole(roleService.getRoleByName(role));

        userService.updateUser(user);

        return "redirect:/view-users";
    }

    @RequestMapping(path = "/admin/delete-user", method = RequestMethod.GET)
    public String updateUser(@RequestParam("id") Long id) {

        userService.deleteUser(id);

        return "redirect:/view-users";
    }

    @RequestMapping(path = "/admin/add-user", method = RequestMethod.GET)
    public ModelAndView addUser(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        modelMap.addAttribute("roleList", roleService.getAllRoles());

        return new ModelAndView("user_add", modelMap);
    }

    @RequestMapping(path = "/admin/add-user", method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, @RequestParam String role) {


        if (bindingResult.hasErrors()) {
            return "user_add";
        }

        user.setRole(roleService.getRoleByName(role));
        userService.addUser(user);

        return "redirect:/view-users";
    }


    @RequestMapping(path = "/user/user", method = RequestMethod.GET)
    public ModelAndView user() {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return new ModelAndView("user", "username", userDetails.getUsername());
    }

    @RequestMapping(path = "/admin/admin", method = RequestMethod.GET)
    public ModelAndView admin() {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return new ModelAndView("admin", "username", userDetails.getUsername());
    }
}
