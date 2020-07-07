package org.zuoyu.examples.config;

import java.lang.reflect.Method;
import java.net.InetAddress;
import javax.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zuoyu
 * @date 2020/7/7
 * @time 下午3:02
 * @description 项目初始化完成行为.
 */
@ConfigurationProperties(prefix = "browser.auto")
public class StartConfig {

  private static final String OS_NAME = "os.name";
  private static final String MAC_OS = "mac";
  private static final String WINDOWS_OS = "windows";
  private static final String LINUX_OS = "linux";
  private static final String MAC_CLASS_NAME = "com.apple.eio.FileManager";
  private static final String WINDOWS_EXEC = "rundll32 url.dll,FileProtocolHandler ";
  private static final String[] BROWSERS = {"google-chrome", "firefox"};
  private boolean isEnable = true;
  private String url = "";

  private final ServerProperties serverProperties;

  public StartConfig(ServerProperties serverProperties) {
    this.serverProperties = serverProperties;
  }

  @PostConstruct
  public void applicationContextEvent() {
    if (!this.isEnable) {
      return;
    }
    if (this.url.trim().isEmpty()){
      this.url = swaggerURL();
    }
    String osName = System.getProperty(OS_NAME).toLowerCase();
    if (osName.contains(MAC_OS)) {
      macOpener(this.url);
    }
    if (osName.contains(WINDOWS_OS)) {
      windowsOpener(this.url);
    }
    if (osName.contains(LINUX_OS)) {
      linuxOpener(this.url);
    }
  }

  @SneakyThrows
  private void macOpener(String url) {
    Class fileManager = Class.forName(MAC_CLASS_NAME);
    Method openURL = fileManager.getDeclaredMethod("openURL", String.class);
    openURL.invoke(null, url);
  }

  @SneakyThrows
  private void windowsOpener(String url) {
    Runtime runtime = Runtime.getRuntime();
    runtime.exec(WINDOWS_EXEC + url);
  }

  @SneakyThrows
  private void linuxOpener(String url) {
    String browser = null;
    for (String s : BROWSERS) {
      if (Runtime.getRuntime().exec(new String[] {"which", s}).waitFor() == 0) {
        browser = s;
        break;
      }
    }
    Runtime.getRuntime().exec(new String[] {browser, url});
  }

  private String swaggerURL(){
    final String base = "http://127.0.0.1:";
    final String swagger = "/swagger-ui.html";
    Integer port = serverProperties.getPort();
    String contextPath = serverProperties.getServlet().getContextPath();
    return base + port.toString() + contextPath + swagger;
  }

  public boolean isEnable() {
    return isEnable;
  }

  public void setEnable(boolean enable) {
    isEnable = enable;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
