package com.DirectoryScanner.DirScanner;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

public class Scanner {


    public void scanDirectory(String scanDirectory, int waitTime) throws IOException, InterruptedException {
        Path folderToScan = Paths.get(scanDirectory);
        WatchService watchService = FileSystems.getDefault().newWatchService();
        folderToScan.register(watchService, ENTRY_CREATE);
        boolean keepMonitoring = true;
        while (keepMonitoring) {
            WatchKey poll = watchService.take();
            for (WatchEvent event : poll.pollEvents()) {
                if (event.kind().name().equals("ENTRY_CREATE")) {
                        System.out.println("New file found: " + event.context());
                }
            }

            keepMonitoring = poll.reset();
            Thread.sleep(waitTime * 1000);
        }
    }
}
