package ru.seoweblab.service.zip;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipArchive {



    private static final String FILE_SEPARATOR = System.getProperty("file.separator");

    /**
     * Упаковывает файлы в архив zip.
     * @return <code>byte[]</code>
     * @throws IOException если произошло исключение ввода или вывода. А также
     * если файл не был найден в директории.
     */
    public byte[] zipFiles(File directory, String[] files) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);
        byte bytes[] = new byte[2048];

        for (String fileName : files) {
            FileInputStream fis = new FileInputStream(directory.getPath() +
                    ZipArchive.getFileSeparator() + fileName);
            BufferedInputStream bis = new BufferedInputStream(fis);

            zos.putNextEntry(new ZipEntry(fileName));

            int bytesRead;
            while ((bytesRead = bis.read(bytes)) != -1) {
                zos.write(bytes, 0, bytesRead);
            }
            zos.closeEntry();
            bis.close();
            fis.close();
        }
        zos.flush();
        baos.flush();
        zos.close();
        baos.close();
        return baos.toByteArray();
    }

    public static String getFileSeparator() {
        return FILE_SEPARATOR;
    }
}
