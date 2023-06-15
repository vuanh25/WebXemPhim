package com.example.webxemphim.Util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ImageStorageHelper {
    public static String saveImage(MultipartFile imageFile, String directoryPath) throws IOException {
        String imageName = generateUniqueImageName(imageFile.getOriginalFilename());
        String imagePath = directoryPath + File.separator + imageName;
        File file = new File(imagePath);
        imageFile.transferTo(file);
        return imageName;
    }

    private static String generateUniqueImageName(String originalFilename) {
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        return UUID.randomUUID().toString() + "." + extension;
    }
}
