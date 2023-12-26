package com.luitel.pub.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.luitel.pub.database.UserPostgres;
import com.luitel.pub.database.UserRepository;

@Controller
public class Register {
  @Autowired
  private UserRepository userRepository;

  // @GetMapping(path="/demo/all")
  // public @ResponseBody Iterable<UserPostgres> getAllUsers(){
  // return userRepository.findAll();
  // }

  @GetMapping("/register")
  public String register(Model model) {
    model.addAttribute("user", new UserPostgres());
    return "registration";
  }

  @PostMapping("/register")
  public String register_save(@ModelAttribute UserPostgres user, Model model, RedirectAttributes redirectAttributes) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    model.addAttribute("user", user);
    String encodedPassword = passwordEncoder.encode(user.getPassword());
    user.setPassword(encodedPassword);
    try {
      userRepository.save(user);
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("errorMessage", "cannot register, username or email already taken");
      return "redirect:/register";
    }
    return "redirect:/login";
  }
}
