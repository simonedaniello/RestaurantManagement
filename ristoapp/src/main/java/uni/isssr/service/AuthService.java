package uni.isssr.service;


import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


@Service
public class AuthService {

    private static final String SECRET_KEY_1 = "ssdkF$HUy2A#D%kd";
    private static final byte[] toEncrypt = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    private IvParameterSpec ivParameterSpec;
    private SecretKeySpec secretKeySpec;
    private Cipher cipher;

    // AES-128 implementation
    public AuthService() throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException {
        ivParameterSpec = new IvParameterSpec(SECRET_KEY_1.getBytes("UTF-8"));
        cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    }

    // SHA-256 implementation
    private byte[] convertTo16BytesKey(String pass) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(pass.getBytes("UTF-8"));
        byte[] key = new byte[hash.length/2];
        for (int i = 0; i < key.length; i++){
            key[i] = hash[i];
        }
        return key;
    }


    /**
     * Encrypt the string with this internal algorithm.
     *
     * @param pass string object to be encrypt.
     * @return returns encrypted string.
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public String encrypt(String pass) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        byte[] passBytes = convertTo16BytesKey(pass);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(passBytes, "AES"), ivParameterSpec);
        byte[] encrypted = cipher.doFinal(toEncrypt);
        return new String(Base64.getEncoder().encode(encrypted));
    }

    /**
     * Decrypt this string with the internal algorithm. The passed argument should be encrypted using
     * {@link #encrypt(String) encrypt} method of this class.
     *
     * @param pass encrypted string that was encrypted using {@link #encrypt(String) encrypt} method.
     * @return decrypted string.
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public String decrypt(String pass) throws InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] passBytes = convertTo16BytesKey(pass);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(passBytes, "AES"), ivParameterSpec);
        byte[] decryptedBytes = cipher.doFinal(toEncrypt);
        return new String(decryptedBytes);
    }
}
