import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class ValidationTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void emptyArgsResultInException() {
        //expect
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Please provide path to the file and the base path for the output");

        //when
        Main.main(new String[]{});
    }

    @Test
    public void invalidArgsResultInException() {
        //expect
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Invalid file path.");

        //when
        Main.main(new String[]{"1", "2"});
    }

    @Test
    public void noOperationFieldValueResultsInException() {
        //expect
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Field \"operation\" is not present in the input.");

        //when
        String resourcePath = getResourcePath("NoOperationField.json");
        Main.main(new String[]{resourcePath, "test-path"});
    }

    @Test
    public void wrongOperationFieldValueResultsInException() {
        //expect
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Provided operation type is not valid. Allowed values are \"encrypt\" or \"decrypt\"");

        //when
        String resourcePath = getResourcePath("WrongOperationFieldValue.json");
        Main.main(new String[]{resourcePath, "test-path"});
    }

    @Test
    public void keyADivisibleByTwoResultsInException() {
        //expect
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Provided key (a) is not valid. " +
                "(a) must be relatively prime to m (26) and of range 1 - m (26).");

        //when
        String resourcePath = getResourcePath("KeyADivisibleByTwo.json");
        Main.main(new String[]{resourcePath, "test-path"});
    }

    @Test
    public void keyAEqualToThirteenResultsInException() {
        //expect
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Provided key (a) is not valid. " +
                "(a) must be relatively prime to m (26) and of range 1 - m (26).");

        //when
        String resourcePath = getResourcePath("KeyAEqualToThirteen.json");
        Main.main(new String[]{resourcePath, "test-path"});
    }

    @Test
    public void keyBIsNegativeResultsInException() {
        //expect
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Provided key (b) is not valid." +
                " (b) must be a whole number.");

        //when
        String resourcePath = getResourcePath("KeyBIsNegative.json");
        Main.main(new String[]{resourcePath, "test-path"});
    }

    private String getResourcePath(String filename) {
        URL resource = ValidationTest.class.getResource(filename);
        try {
            return Paths.get(resource.toURI()).toFile().getAbsolutePath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new IllegalStateException("Files required for the tests are missing.");
        }
    }
}