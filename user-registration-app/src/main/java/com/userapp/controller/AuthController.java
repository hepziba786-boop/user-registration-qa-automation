package com.userapp.controller;

import com.userapp.dto.LoginDTO;
import com.userapp.dto.UserRegisterDTO;
import com.userapp.exception.UserAlreadyExistsException;
import com.userapp.model.User;
import com.userapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class AuthController {

    private final UserService userService;

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("registerDTO", new UserRegisterDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("registerDTO") UserRegisterDTO registerDTO,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            userService.registerUser(registerDTO);
            model.addAttribute("successMessage", "Account created successfully");
            return "redirect:/login";
        } catch (UserAlreadyExistsException e) {
            model.addAttribute("errorMessages", e.getMessage());
            return "register";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessages", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@Valid @ModelAttribute("loginDTO") LoginDTO loginDTO,
                            BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "login";
        }

        try {
            User user = userService.findByUsername(loginDTO.getUsername())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

            if (!userService.validatePassword(user, loginDTO.getPassword())) {
                throw new IllegalArgumentException("Invalid username or password");
            }

            session.setAttribute("userId", user.getId());
            session.setAttribute("username", user.getUsername());

            return "redirect:/dashboard";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessages", e.getMessage());
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}
