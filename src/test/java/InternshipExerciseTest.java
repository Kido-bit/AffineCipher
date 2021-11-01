import org.junit.Assert;
import org.junit.Test;

public class InternshipExerciseTest {

    @Test
    public void shouldEncryptText() {
        //given
        Integer key1 = 3;
        Integer key2 = 7;
        String operation = "encrypt";
        String plainText = "messageToEncrypt";
        String cryptogram = "rtjjhztMxTungbam";
        CipherModel cipherModel = new CipherModel(key1, key2, operation, plainText, cryptogram);

        //when
        CipherModel result = AffineCipher.encrypt(cipherModel);

        //then
        Assert.assertEquals(cryptogram, result.getCryptogram());
    }

    @Test
    public void shouldDecryptText() {
        //given
        Integer key1 = 3;
        Integer key2 = 7;
        String operation = "decrypt";
        String plainText = "messageToEncrypt";
        String cryptogram = "rtjjhztMxTungbam";
        CipherModel cipherModel = new CipherModel(key1, key2, operation, plainText, cryptogram);

        //when
        CipherModel result = AffineCipher.decrypt(cipherModel);

        //then
        Assert.assertEquals(plainText, result.getPlainText());
    }

    @Test
    public void encryptionEqualsDecryption() {
        //given
        Integer key1 = 7;
        Integer key2 = 11;
        String encryptOperation = "encrypt";
        String decryptOperation = "decrypt";
        String plainText = "messageToEncrypt";
        String cryptogram = "rnhhlbnOfNyzaxmo";
        CipherModel encryption = new CipherModel(key1, key2, encryptOperation, plainText, cryptogram);
        CipherModel decryption = new CipherModel(key1, key2, decryptOperation, plainText, cryptogram);

        //when
        CipherModel encryptionResult = AffineCipher.encrypt(encryption);
        CipherModel decryptionResult = AffineCipher.decrypt(decryption);

        //then
        Assert.assertEquals(encryptionResult.getCryptogram(), decryptionResult.getCryptogram());
    }
}
