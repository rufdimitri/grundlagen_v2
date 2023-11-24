package rd.aes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Assert;
import org.junit.Test;

public class AESUtil {

	public final static String AES_ALGORITHM = "AES/CBC/PKCS5Padding";

	public static SecretKey generateKey(int n) {
		KeyGenerator keyGenerator;
		try {
			keyGenerator = KeyGenerator.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		keyGenerator.init(n);
		SecretKey key = keyGenerator.generateKey();
		return key;
	}

	public static SecretKey getKeyFromPassword(String password, String salt) {
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
			SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
			return secret;
		} catch (Throwable t) {
			throw new RuntimeException(t);
		}
	}

	public static IvParameterSpec generateIv() {
		byte[] iv = new byte[16];
		new SecureRandom().nextBytes(iv);
		return new IvParameterSpec(iv);
	}

	public static String encrypt(String algorithm, String input, SecretKey key, IvParameterSpec iv) {
		try {
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			byte[] cipherText = cipher.doFinal(input.getBytes());
			return Base64.getEncoder().encodeToString(cipherText);
		} catch (Throwable t) {
			throw new RuntimeException(t);
		}
	}

	/** 
	 * decrypting an input string, we can initialize our cipher using the DECRYPT_MODE to decrypt the content:
	
	 */
	public static String decrypt(String algorithm, String cipherText, SecretKey key, IvParameterSpec iv) {
		try {
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.DECRYPT_MODE, key, iv);
			byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
			return new String(plainText);
		} catch (Throwable t) {
			throw new RuntimeException(t);
		}
	}

	/**
	 * a test method for encrypting and decrypting a string input:
	 */

	@Test
	public void givenString_whenEncrypt_thenSuccess() {

		String input = "hallo";
		SecretKey key = AESUtil.generateKey(256);
		IvParameterSpec ivParameterSpec = AESUtil.generateIv();
		String algorithm = "AES/CBC/PKCS5Padding";
		String cipherText = AESUtil.encrypt(algorithm, input, key, ivParameterSpec);
		String plainText = AESUtil.decrypt(algorithm, cipherText, key, ivParameterSpec);
		Assert.assertEquals(input, plainText);
	}

	@Test
	public void testDifferentIVParameter() {

		String input = "hallo";
		SecretKey key = AESUtil.generateKey(256);
		IvParameterSpec ivParameterSpec = AESUtil.generateIv();
		IvParameterSpec ivParameterSpec2 = AESUtil.generateIv();
		String algorithm = "AES/CBC/PKCS5Padding";
		String cipherText = AESUtil.encrypt(algorithm, input, key, ivParameterSpec);
		String plainText = AESUtil.decrypt(algorithm, cipherText, key, ivParameterSpec2);
		Assert.assertEquals(input, plainText);
	}

	/**
	 * For decrypting a file, we use similar steps and initialize our cipher using DECRYPT_MODE as we saw before.
	 * @param algorithm
	 * @param key
	 * @param iv
	 * @param cipherMode - <b>Cipher.ENCRYPT_MODE</b> or <b>Cipher.DECRYPT_MODE</b> (javax.crypto.Cipher)
	 * @param inputFile
	 * @param outputFile
	 */
	public static void encryptFile(String algorithm, SecretKey key, IvParameterSpec iv, int cipherMode, File inputFile,
			File outputFile) {

		try (FileInputStream inputStream = new FileInputStream(inputFile);
				FileOutputStream outputStream = new FileOutputStream(outputFile);) {
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(cipherMode, key, iv);
			byte[] buffer = new byte[64];
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				byte[] output = cipher.update(buffer, 0, bytesRead);
				if (output != null) {
					outputStream.write(output);
				}
			}
			byte[] outputBytes = cipher.doFinal();
			if (outputBytes != null) {
				outputStream.write(outputBytes);
			}
			inputStream.close();
			outputStream.close();
		} catch (Throwable t) {
			throw new RuntimeException(t);
		}
	}

	@Test
	public void givenFile_whenEncrypt_thenSuccess() {
		try {
			/*
			SecretKey key = AESUtil.generateKey(128);
			String algorithm = "AES/CBC/PKCS5Padding";
			IvParameterSpec ivParameterSpec = AESUtil.generateIv();
			Resource resource = new ClassPathResource("inputFile/hallo.txt");
			File inputFile = resource.getFile();
			File encryptedFile = new File("classpath:hallo.encrypted");
			File decryptedFile = new File("document.decrypted");
			AESUtil.encryptFile(algorithm, key, ivParameterSpec, inputFile, encryptedFile);
			AESUtil.decryptFile(algorithm, key, ivParameterSpec, encryptedFile, decryptedFile);
			assertThat(inputFile).hasSameTextualContentAs(decryptedFile);
			*/
		} catch (Throwable t) {
			throw new RuntimeException(t);
		}
	}

}
