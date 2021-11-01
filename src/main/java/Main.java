import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String OPERATION_TYPE_ENCRYPT = "encrypt";
    private static final String OPERATION_TYPE_DECRYPT = "decrypt";

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Please provide path to the file and the base path for the output");
        }

        if (args.length != 2) {
            throw new IllegalArgumentException("Please provide valid path to input file and valid base path the output");
        }
        String jsonInputPath = args[0];
        String jsonOutputDirectoryPath = args[1];

        CipherModel input = parseFile(jsonInputPath);

        if (input.getA() % 2 == 0 || input.getA() == 13) {
            throw new IllegalArgumentException("Provided key (a) is not valid. " +
                    "(a) must be relatively prime to m (26) and of range 1 - m (26).");
        }

        if (input.getB() < 0) {
            throw new IllegalArgumentException("Provided key (b) is not valid. " +
                    "(b) must be a whole number.");
        }

        if (input.getOperation() == null) {
            throw new IllegalArgumentException("Field \"operation\" is not present in the input.");
        }


        CipherModel result;

        if (input.getOperation().equalsIgnoreCase(OPERATION_TYPE_ENCRYPT)) {
            result = AffineCipher.encrypt(input);
        } else if (input.getOperation().equalsIgnoreCase(OPERATION_TYPE_DECRYPT)) {
            result = AffineCipher.decrypt(input);
        } else {
            throw new IllegalArgumentException("Provided operation type is not valid. " +
                    "Allowed values are \"encrypt\" or \"decrypt\"");
        }


        ResultWriter.writeResultJsonFile(result, input.getOperation(), jsonInputPath, jsonOutputDirectoryPath);
    }

    private static CipherModel parseFile(String path) {
        try {
            return objectMapper.readValue(Paths.get(path).toFile(), CipherModel.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid file path.");
        }
    }
}
