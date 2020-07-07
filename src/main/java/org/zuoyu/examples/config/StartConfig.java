package org.zuoyu.examples.config;

import java.lang.reflect.Method;
import lombok.SneakyThrows;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.EventListener;

/**
 * @author zuoyu
 * @date 2020/7/7
 * @time 下午3:02
 * @description 项目初始化完成行为.
 */
public class StartConfig {

	private final static String OS_NAME = "os.name";
	private final static String MAC_OS = "mac";
	private final static String WINDOWS_OS = "windows";
	private final static String LINUX_OS = "linux";
	private final static String MAC_CLASS_NAME = "com.apple.eio.FileManager";
	private final static String WINDOWS_EXEC = "rundll32 url.dll,FileProtocolHandler ";
	private final static String[] BROWSERS = {"google-chrome", "firefox"};

	@EventListener(ApplicationContextEvent.class)
	public void applicationContextEvent(){
		final String swagger2Url = "http://127.0.0.1:8080/swagger-ui.html";
		String osName = System.getProperty(OS_NAME).toLowerCase();
		if (osName.contains(MAC_OS)){
			macOpener(swagger2Url);
		}
		if (osName.contains(WINDOWS_OS)){
			windowsOpener(swagger2Url);
		}
		if (osName.contains(LINUX_OS)){
			linuxOpener(swagger2Url);
		}
	}

	@SneakyThrows
	private void macOpener(String url){
		Class fileManager = Class.forName(MAC_CLASS_NAME);
		Method openURL = fileManager.getDeclaredMethod("openURL", String.class);
		openURL.invoke(null, url);
	}

	@SneakyThrows
	private void windowsOpener(String url){
		Runtime runtime = Runtime.getRuntime();
		runtime.exec(WINDOWS_EXEC + url);
	}

	@SneakyThrows
	private void linuxOpener(String url){
		String browser = null;
		for (String s : BROWSERS) {
			if (Runtime.getRuntime().exec(new String[]{"which", s}).waitFor() == 0){
				browser = s;
				break;
			}
		}
		Runtime.getRuntime().exec(new String[]{browser, url});
	}
}
