package com.luitel.pub.storage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UploadFiles {
  private final StorageService storageService = new StorageService() {
  };

  @PostMapping("/")
  public String handleFileUpload(@RequestParam("file") MultipartFile file,
      RedirectAttributes redirectAttributes, RedirectAttributes attr) {

    if (file.getContentType().equalsIgnoreCase("application/pdf")) {
      storageService.store(file);
      return "redirect:/";
    } else
      attr.addFlashAttribute("errorMessage", "this does not seems like a pdf. HMMMMMMM");
    return "redirect:/error";
  }

}
