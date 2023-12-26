package com.luitel.pub.utilities;

import org.springframework.security.core.context.SecurityContextHolder;

public class Info {
  static public String GetName() {
    return SecurityContextHolder.getContext().getAuthentication().getName();
  }
}
