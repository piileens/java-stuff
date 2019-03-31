package com.DirectoryScanner.DirScanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.Properties;

@SpringBootApplication
public class DirScannerApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(DirScannerApplication.class, args);
		Properties prop = new Properties();

		FileInputStream input = new FileInputStream("src/main/resources/application.properties");
		prop.load(input);
		String scanDirectory = prop.getProperty("scan.folder");
		int waitTime ;
		try {
			waitTime = Integer.parseInt(prop.getProperty("scan.waitTime"));
		}
		catch (NumberFormatException e){
			waitTime = 0;
		}
		input.close();

		Scanner dirScanner = new Scanner();
		dirScanner.scanDirectory(scanDirectory,waitTime);


	}

}
