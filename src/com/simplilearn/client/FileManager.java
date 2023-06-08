package com.simplilearn.client;

import com.simplilearn.services.FileManagerService;

public class FileManager {
    public static void main(String[] args) {
        FileManagerService fileManagerService = new FileManagerService();
        fileManagerService.startTheApp();
    }
}
