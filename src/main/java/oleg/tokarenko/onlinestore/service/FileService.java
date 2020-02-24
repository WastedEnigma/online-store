package oleg.tokarenko.onlinestore.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.*;
import java.util.*;

@Service
public class FileService {

    public static final String FILE_DIR =
            System.getProperty("user.home") + File.separator +
                    "store-images" + File.separator;

    public String saveFile(String file, String name) throws IOException {
        createDir();

        String[] data = file.split(",");
        String metaInfo = data[0];
        String base64File = data[1];

        String fileName = createFileName(name, getFileExtensionFromMetaInfo(metaInfo));

        Files.write(
                Paths.get(FILE_DIR, fileName),
                Base64.getDecoder().decode(base64File.getBytes())
        );

        return fileName;
    }

    private String createFileName(String fileName, String fileExtension) {
        if (fileName == null) {
            fileName = UUID.randomUUID().toString();
        }

        return String.format("%s.%s", fileName, fileExtension);
    }

    private String getFileExtensionFromMetaInfo(String metaInfo) {
        return metaInfo.split("/")[1]
                .split(";")[0];
    }

    private void createDir() {
        File file = new File(FILE_DIR);

        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
