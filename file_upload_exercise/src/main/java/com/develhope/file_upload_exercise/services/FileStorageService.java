package com.develhope.file_upload_exercise.services;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileStorageService {
    // parameters
    // vado a iniettare il valore in application.yml qua
    @Value("{fileRepositoryFolder}")
    private String fileRepositoryFolder;

    /**
     *
     * @param file File from upload controller
     * @return  new file name with extension
     * @throws IOException
     */
    /**
     * 1. @Value for the filerepofolder in the application.yml
     * 2. in applicationrepofolder add the folder path
     * 3. add the dependency
     * 4. create the method upload
     */

    // metodo upload

    public String upload(MultipartFile file)throws IOException {
        /**
         * 5. add the MultipartFile parameter so the user can upload different types of files(jpeg,png,gif)
         * 6. creamo un nuovo String newFileName per cambiare il nome del file per non avere due nomi stessi
         * 7. un nuovo String extension per preservare il tipo di file quando cambiamo il nome
         * 8. nuovo String completeFileName
         * 9. Creates a File object pointing to the target directory(fileRepositoryFolder) and attach the new file name
         */
       String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String newFileName = UUID.randomUUID().toString();
        String completeFileName = newFileName + "." + extension;
        // so we can do the if methods more easily
        File finalFolder = new File(fileRepositoryFolder);
        if(!finalFolder.exists()) throw new IOException("folder doesn't exist");
        if(!finalFolder.isDirectory()) throw new IOException("provided folder is not the directory");
        File finalDestination = new File(fileRepositoryFolder + "\\" + completeFileName);
        if(finalDestination.exists()) throw new IOException("file already exists");
        file.transferTo(finalDestination);
        return completeFileName;
    }

    // METHODO DOWNLOAD
    public byte[] download(String fileName) throws IOException{
        // specify the path to the file directory
        File fileFromRepository = new File(fileRepositoryFolder+ "\\" + fileName);
        if(!fileFromRepository.exists()) throw new IOException("file doesn/t exist");
        return IOUtils.toByteArray(new FileInputStream(fileFromRepository));

    }
}
