import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ResultWriter {

    private final static String JSON_EXTENSION = ".json";

    public static void writeResultJsonFile(CipherModel result, String operationType, String inputPath, String outputBasePath) {
        String originalFileName = extractOriginalFileName(inputPath);
        String currentDateTime = getCurrentDateTime();
        String fileName = buildFileName(originalFileName, operationType, currentDateTime);
        System.out.println(fileName);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(outputBasePath + "/" + fileName), result);
            System.out.println("Successfully save the result to " + outputBasePath + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String extractOriginalFileName(String inputPath) {
        int indexOfLastSlash = inputPath.lastIndexOf("/");
        int indexOfExtension = inputPath.lastIndexOf(JSON_EXTENSION);
        return inputPath.substring(indexOfLastSlash + 1, indexOfExtension);
    }

    private static String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        return formatter.format(now);
    }

    private static String buildFileName(String originalFileName, String operationType, String currentDateTime) {
        return "/" + originalFileName + "_" + operationType + "_" + currentDateTime + JSON_EXTENSION;
    }
}
