package com.userapp.controller;

import com.userapp.model.User;
import com.userapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final UserService userService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        String username = (String) session.getAttribute("username");

        if (userId == null || username == null) {
            return "redirect:/login";
        }

        model.addAttribute("username", username);
        return "dashboard";
    }

    @GetMapping("/view-data")
    public String viewAllData(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/login";
        }

        User user = userService.getUserById(userId);
        model.addAttribute("user", user);

        return "view-data";
    }

}
