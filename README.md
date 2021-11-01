This program uses affine cipher to encrypt and decrypt messages. 
You need to have Java 16 installed to run the program and Maven to run the tests.

To run the tests, run the following command from any command line:
   mvn clean install

To run the program:
1. Download the AffineCipher.jar file from the main directory.
2. Open any command line tool (Git Bash, CMD, etc.) and go into the directory where the JAR file was downloaded
3. Run java -jar command including the JAR filename and two arguments:
4. Path to the file containing message to encrypt/decrypt
5. Output base path for the result of the operation

Example command:
java -jar AffineCipher.jar D:/Input/decrypt.json D:/Output

Input file for the program is a JSON file containing following structure:

{
   "a": key1,
   "b": key2,
   "operation":"decrypt",
   "cryptogram":"oguucigVqapetarv",
   "plainText": "exampleText"
}

1. Field “a” must not have common divisors with 26, it must be an odd number between 1 and 25 (excluding 13)
2. Field “b” can be any whole number
3. Field “operation” may contain values “decrypt” or “encrypt”. This will determine what operation will be performed
4. Field “cryptogram” can contain text for decryption
5. Field “plainText” can contain text to be encrypted

Fields “cryptogram” and “plainText” can be both present in the input file, but only one of the values will be used, based on the operation type.