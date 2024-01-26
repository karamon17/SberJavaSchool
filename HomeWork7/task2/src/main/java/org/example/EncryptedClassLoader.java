package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class EncryptedClassLoader extends ClassLoader {
    private final String key;
    private final File dir;

    public EncryptedClassLoader(String key, File dir, ClassLoader parent) {
        super(parent);
        this.key = key;
        this.dir = dir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            File classFile = new File(dir, name.replace('.', '/') + ".encrypted");

            byte[] encryptedBytes = readBytesFromFile(classFile);

            byte[] decryptedBytes = decrypt(encryptedBytes, key);

            return defineClass(name, decryptedBytes, 0, decryptedBytes.length);
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new ClassNotFoundException("Failed to load class: " + name, e);
        }
    }

    /**
     * Читает все байты из файла
     *
     * @param file файл
     * @return массив байтов
     * @throws IOException если не удалось прочитать файл
     */
    private byte[] readBytesFromFile(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[(int) file.length()];
            int bytesRead = fis.read(buffer);
            if (bytesRead != buffer.length) {
                throw new IOException("Failed to read the entire file");
            }
            return buffer;
        }
    }

    /**
     * Дешифрует массив байтов
     *
     * @param encryptedBytes зашифрованные байты
     * @param key            ключ шифрования
     * @return дешифрованные байты
     * @throws NoSuchAlgorithmException если не найден алгоритм хеширования
     */
    private byte[] decrypt(byte[] encryptedBytes, String key) throws NoSuchAlgorithmException {
        // Пример простой логики дешифрования: каждый байт увеличивается на значение хеша ключа
        byte[] keyHash = Arrays.copyOf(hashKey(key), encryptedBytes.length);
        byte[] decryptedBytes = new byte[encryptedBytes.length];
        for (int i = 0; i < encryptedBytes.length; i++) {
            decryptedBytes[i] = (byte) (encryptedBytes[i] - keyHash[i]);
        }
        return decryptedBytes;
    }

    /**
     * Хеширует ключ
     *
     * @param key ключ
     * @return хеш ключа
     * @throws NoSuchAlgorithmException если не найден алгоритм хеширования
     */
    private byte[] hashKey(String key) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(key.getBytes());
    }
}
