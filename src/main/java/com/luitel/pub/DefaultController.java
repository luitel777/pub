package com.luitel.pub;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luitel.pub.storage.StorageService;
import com.luitel.pub.utilities.Info;

@Controller
public class DefaultController {
  private static String Username;
  private static String Description;

  // this will be retrived from database later on
  public static String GetUserProfile() {
    Username = Info.GetName();
    return Info.GetName();
  }

  @RequestMapping("/")
  public static String index(String args, Model model) {
    Username = GetUserProfile();
    Description = "very lazy guy";
    StorageService.get_file_list();
    model.addAttribute("name", Username);
    model.addAttribute("named2", Description);
    model.addAttribute("user", Username);
    model.addAttribute("list_of_pdf", StorageService.get_file_list());
    return "index";
  }

  @RequestMapping("/user")
  public @ResponseBody String user(){
    return GetUserProfile();
  }


}
