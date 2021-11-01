import java.math.BigInteger;

public class AffineCipher {

    private final static int module = 26;

    static CipherModel encrypt(CipherModel cipherModel) {

        int a = cipherModel.getA();
        int b = cipherModel.getB();
        String plainText = cipherModel.getPlainText();

        StringBuilder textAfterOperation = new StringBuilder();

        for (int index = 0; index < plainText.length(); index++) {
            char originalCharacter = plainText.charAt(index);
            char character = Character.toLowerCase(plainText.charAt(index));
            char flagCharacter = character;
            if (Character.isLetter(character)) {
                character = (char) ((a * (character - 'a') + b) % module + 'a');
            }
            if (originalCharacter != flagCharacter) {
                character = Character.toUpperCase(character);
            }
            textAfterOperation.append(character);
        }
        System.out.println("encrypt: " + plainText + " -> " + textAfterOperation);
        return new CipherModel(a,b, cipherModel.getOperation(), plainText, textAfterOperation.toString());
    }

    static CipherModel decrypt(CipherModel cipherModel){

        int a = cipherModel.getA();
        int b = cipherModel.getB();
        String cryptogram = cipherModel.getCryptogram();

        StringBuilder textAfterOperation = new StringBuilder();
        BigInteger inverse = BigInteger.valueOf(a).modInverse(BigInteger.valueOf(module));

        for (int index = 0; index < cryptogram.length(); index++) {
            Character character = Character.toLowerCase(cryptogram.charAt(index));
            char originalCharacter = cryptogram.charAt(index);
            char flagCharacter = character;
            if (Character.isLetter(character)) {
                int decoded = (char) (inverse.intValue() * (character - 'a' - b + module));
                character = (char) (decoded % module + 'a');
            }
            if (originalCharacter != flagCharacter) {
                character = Character.toUpperCase(character);
            }
            textAfterOperation.append(character);
        }
        System.out.println("decrypt: " + cryptogram + " -> " + textAfterOperation);
        return new CipherModel(a,b, cipherModel.getOperation(), textAfterOperation.toString(), cryptogram);
    }
}
