package com.HirePortal2025.HirePortal2025.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * The `FileUploadUtil` class provides utility methods for handling file uploads.
 * It includes methods to save files to a specified directory.
 *
 * Purpose:
 * - To provide a utility method for saving uploaded files to a specified directory.
 *
 * Key Functionalities:
 * - `saveFile(String uploadDir, String filename, MultipartFile multipartFile)`:
 *   Saves the uploaded file to the specified directory with the given filename.
 *   - Parameters:
 *     - `uploadDir`: The directory where the file will be saved.
 *     - `filename`: The name of the file to be saved.
 *     - `multipartFile`: The uploaded file to be saved.
 *   - Throws:
 *     - `IOException`: If an I/O error occurs during file saving.
 */
public class FileUploadUtil {

    public static void saveFile(String uploadDir, String filename , MultipartFile multipartFile) throws IOException {

        Path uploadpath = Paths.get(uploadDir);
        if(!Files.exists(uploadpath)){
            Files.createDirectories(uploadpath);
        }

        try(InputStream inputStream = multipartFile.getInputStream();){
            Path path = uploadpath.resolve(filename);
            System.out.println("FilePath " + path);
            System.out.println(("fileName " + filename));
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
        }catch(IOException ioe){
            throw new IOException("Could not save image file: " + filename,ioe);

        }
    }
}
