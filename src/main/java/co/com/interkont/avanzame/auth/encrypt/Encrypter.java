package co.com.interkont.avanzame.auth.encrypt;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ResourceBundle;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import co.com.interkont.avanzame.config.Constantes;

/**
 * -----------------------------------------------------------------------------
 * The following example implements a class for encrypting and decrypting
 * strings using several Cipher algorithms. The class is created with a key and
 * can be used repeatedly to encrypt and decrypt strings using that key. Some of
 * the more popular algorithms are: Blowfish DES DESede PBEWithMD5AndDES
 * PBEWithMD5AndTripleDES TripleDES
 */
public class Encrypter {

    private Cipher ecipher;
    private Cipher dcipher;
    private static Encrypter instance;

    /**
     * Inits the Encrypter with "PBEWithMD5AndDES" algorithm
     * @return
     * @throws Exception
     */
    public static Encrypter getInstance() throws Exception {
        if (instance == null) {

            String key = Constantes.KEY_ENCODER;
            instance = new Encrypter(key, AlgorithmEncrypter.MD5_DES);
        }
        return instance;
    }

    public static Encrypter getInstance(AlgorithmEncrypter algorithm) throws Exception {
        if (instance == null) {

            if (!algorithm.equals(AlgorithmEncrypter.MD5_DES)
                    && !algorithm.equals(AlgorithmEncrypter.MD5_TRIPLE_DES)) {

                SecretKey key = getKey(algorithm);
                instance = new Encrypter(key, algorithm.getName());

            } else {
                String key = ResourceBundle.getBundle("key").getString("key");
                instance = new Encrypter(key, algorithm);
            }

        }
        return instance;
    }

    /**
     * Constructor used to create this object. Responsible for setting and
     * initializing this object's encrypter and decrypter Chipher instances
     * given a Secret Key and algorithm.
     *
     * @param key
     *            Secret Key used to initialize both the encrypter and decrypter
     *            instances.
     * @param algorithm
     *            Which algorithm to use for creating the encrypter and
     *            decrypter instances.
     */
    private Encrypter(SecretKey key, String algorithm) throws Exception {
        try {

            ecipher = Cipher.getInstance(algorithm);
            dcipher = Cipher.getInstance(algorithm);
            ecipher.init(Cipher.ENCRYPT_MODE, key);
            dcipher.init(Cipher.DECRYPT_MODE, key);

        } catch (NoSuchPaddingException e) {
            throw e;
        } catch (NoSuchAlgorithmException e) {
            throw e;
        } catch (InvalidKeyException e) {
            throw e;
        }
    }

    /**
     * Constructor used to create this object. Responsible for setting and
     * initializing this object's encrypter and decrypter Chipher instances
     * given a Pass Phrase and algorithm.
     *
     * @param passPhrase
     *            Pass Phrase used to initialize both the encrypter and
     *            decrypter instances.
     */
    private Encrypter(String passPhrase, AlgorithmEncrypter algorithm) throws Exception{

        // 8-bytes Salt
        byte[] salt = {(byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
            (byte) 0x56, (byte) 0x34, (byte) 0xE3, (byte) 0x03};

        // Iteration count
        int iterationCount = 19;

        try {

            KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt,
                    iterationCount);
            SecretKey key = SecretKeyFactory.getInstance(algorithm.getName()).generateSecret(keySpec);

            ecipher = Cipher.getInstance(key.getAlgorithm());
            dcipher = Cipher.getInstance(key.getAlgorithm());

            // Prepare the parameters to the cipthers
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt,
                    iterationCount);

            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

        } catch (InvalidAlgorithmParameterException e) {
            throw e;
        } catch (InvalidKeySpecException e) {
            throw e;
        } catch (NoSuchPaddingException e) {
            throw e;
        } catch (NoSuchAlgorithmException e) {
            throw e;
        } catch (InvalidKeyException e) {
            throw e;
        }
    }

    /**
     * Takes a single String as an argument and returns an Encrypted version of
     * that String.
     *
     * @param str
     *            String to be encrypted
     * @return <code>String</code> Encrypted version of the provided String
     */
    public String encrypt(String str) throws Exception{
        try {
            // Encode the string into bytes using utf-8
            byte[] utf8 = str.getBytes("UTF8");

            // Encrypt
            byte[] enc = ecipher.doFinal(utf8);

            // Encode bytes to base64 to get a string
            return new sun.misc.BASE64Encoder().encode(enc);

        } catch (BadPaddingException e) {
            throw e;
        } catch (IllegalBlockSizeException e) {
            throw e;
        } catch (UnsupportedEncodingException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Takes a encrypted String as an argument, decrypts and returns the
     * decrypted String.
     *
     * @param str
     *            Encrypted String to be decrypted
     * @return <code>String</code> Decrypted version of the provided String
     */
    public String decrypt(String str) throws Exception{

        try {

            // Decode base64 to get bytes
            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);

            // Decrypt
            byte[] utf8 = dcipher.doFinal(dec);

            // Decode using utf-8
            return new String(utf8, "UTF8");

        } catch (BadPaddingException e) {
            throw e;
        } catch (IllegalBlockSizeException e) {
            throw e;
        } catch (UnsupportedEncodingException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
    }

    private static SecretKey getKey(AlgorithmEncrypter algorithm) throws NoSuchAlgorithmException {

        SecretKey key = null;

        if (algorithm.equals(AlgorithmEncrypter.DES)) {
            key = KeyGenerator.getInstance(AlgorithmEncrypter.DES.getName()).generateKey();
            return key;
        }

        if (algorithm.equals(AlgorithmEncrypter.BLOWFISH)) {
            key = KeyGenerator.getInstance(AlgorithmEncrypter.BLOWFISH.getName()).generateKey();
            return key;
        }

        if (algorithm.equals(AlgorithmEncrypter.DESEDE)) {
            key = KeyGenerator.getInstance(AlgorithmEncrypter.DESEDE.getName()).generateKey();
            return key;
        }

        if (algorithm.equals(AlgorithmEncrypter.AES)) {
            key = KeyGenerator.getInstance(AlgorithmEncrypter.AES.getName()).generateKey();
            return key;
        }

        return key;
    }

}
