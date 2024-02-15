/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import javax.servlet.http.Part;

/**
 *
 * @author ACER
 */
public class FileUtils {

    public void deleteImage() {

    }

    public String uniqueFileName(Part filePart, String originName) {
        String name = "";
        // Generate random name.
        name = UUID.randomUUID().toString() + originName;
        return name;
    }

    public String insertImage(Part filePart) throws IOException {
        String uploadPath = Constants.IMAGE_FOLDER;
        String filePath = "";
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString().trim();
        fileName = fileName.replaceAll("\\s", "_");
        fileName = CharacterUtils.convertSpecialCharToEng(fileName);
        fileName = uniqueFileName(filePart, fileName);
        filePath = Constants.IMAGE_PERMALINK + fileName;
        // Tạo đường dẫn đầy đủ đến file trong thư mục "Assets/img/"
        InputStream input = filePart.getInputStream();
        try {
            // Tạo thư mục nếu nó chưa tồn tại
            File uploadDir = new File(uploadPath, fileName);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            // Chuyển file vào thư mục "Assets/img/"
            Files.copy(input, uploadDir.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            System.err.println("Error writing file to disk: " + e.getMessage());
            e.printStackTrace();
        } finally {
            input.close();
        }
        return filePath;
    }
}
