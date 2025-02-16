package com.HirePortal2025.HirePortal2025.util;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * The `FileDownloadUtil` class provides utility methods for handling file downloads.
 * It includes methods to retrieve files as resources from a specified directory.
 *
 * Fields:
 * - `foundfile`: A `Path` object representing the file found during the search.
 *
 * Purpose:
 * - To provide a utility method for retrieving files as resources from a specified directory.
 *
 * Key Functionalities:
 * - `getFileAsResource(String downloadDir, String fileName)`:
 *   Retrieves a file as a `Resource` from the specified directory with the given filename.
 *   - Parameters:
 *     - `downloadDir`: The directory where the file will be searched.
 *     - `fileName`: The name of the file to be retrieved.
 *   - Returns:
 *     - A `Resource` object representing the file if found, otherwise `null`.
 *   - Throws:
 *     - `IOException`: If an I/O error occurs during file retrieval.
 */
public class FileDownloadUtil {

    private Path foundfile;

    public Resource getFileAsResourse(String downloadDir, String fileName) throws IOException {

        Path path = Paths.get(downloadDir);
        Files.list(path).forEach(file -> {
            if(file.getFileName().toString().startsWith(fileName)){
                foundfile = file;
            }
        });

        if(foundfile != null){
            return new UrlResource(foundfile.toUri());
        }
        return null;

    }
}
