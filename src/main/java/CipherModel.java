public class CipherModel {

    private int a;
    private int b;
    private String operation;
    private String plainText;
    private String cryptogram;

    public CipherModel() {
    }

    public CipherModel(int a, int b,String operation, String plainText, String cryptogram) {
        this.a = a;
        this.b = b;
        this.operation = operation;
        this.plainText = plainText;
        this.cryptogram = cryptogram;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    public void setCryptogram(String cryptogram) {
        this.cryptogram = cryptogram;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getCryptogram() {
        return cryptogram;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public String getPlainText() {
        return plainText;
    }

    public String getOperation() {
        return operation;
    }
}
