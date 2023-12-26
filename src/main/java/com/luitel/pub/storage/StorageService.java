package com.luitel.pub.storage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import com.luitel.pub.utilities.Info;

@Controller
public class StorageService {
  private Path rootLocation = Paths.get("uploads");

  public void store(MultipartFile file) {
    File temp = new File("uploads/" + Info.GetName());
    temp.mkdir();

    rootLocation = Paths.get("uploads/"+Info.GetName());
    System.out.println(Info.GetName());

    Path destinationFile = this.rootLocation.resolve(
        Paths.get(file.getOriginalFilename())).normalize().toAbsolutePath();

    try (InputStream inputStream = file.getInputStream()) {
      Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      System.out.println("Error reading or writing file");
    }
  }

  static public ArrayList<String> get_file_list() {
    File dir = new File("uploads/" + Info.GetName());
    if (!dir.exists()) {
      return null;
    } else {
      File[] files = dir.listFiles();
      Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
      ArrayList<String> listed_files = new ArrayList<String>();
      for (File f : files) {
        listed_files.add(f.getName());
      }
      return listed_files;
    }
  }
}
