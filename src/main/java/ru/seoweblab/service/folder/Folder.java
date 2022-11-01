package ru.seoweblab.service.folder;

import java.io.File;

public class Folder {
  private File folder;

    /**
     * Создает папку, если папка уже существует, то нет.
     * @param applicationPath путь нашего веб-приложения
     * @param folder каталог
     * @param subdirectory подкаталог
     */
    public void createFolder(String applicationPath, String folder, String subdirectory) {
        String filePath = applicationPath + File.separator + folder + File.separator + subdirectory + File.separator;
        this.folder = new File(filePath);
        if (!this.folder.exists()) {
            this.folder.mkdirs();
        }
    }


    public void createFolder(String applicationPath) {
        String filePath = applicationPath;
        this.folder = new File(filePath);
        if (!this.folder.exists()) {
            this.folder.mkdirs();
        }
    }

    /**
     * Получает имена файлов в данном каталоге.
     * @param applicationPath путь нашего веб-приложения
     * @param folder каталог
     * @param subdirectory подкаталог
     * @return возвращает массив строк с именами файлов.
     */
    public String[] getFilesInDir(String applicationPath, String folder, String subdirectory) {
        String filepath = applicationPath + File.separator+folder+ File.separator + subdirectory + File.separator;
        File dir = new File(filepath);
        return dir.list();
    }


    public String[] getFilesInDir(String applicationPath) {
        String filepath = applicationPath;
        File dir = new File(filepath);
        return dir.list();
    }
    /**
     * Возвращает <code>File</code> папку
     * @param applicationPath путь нашего веб-приложения
     * @param folder каталог
     * @param subdirectory подкаталог
     * @return возвращает массив строк с именами файлов.
     */
    public File getFolder(String applicationPath, String folder, String subdirectory) {
        String filepath = applicationPath + File.separator+folder+ File.separator + subdirectory + File.separator;
        File dir = new File(filepath);
        return dir;
    }

    public File getFolder(String applicationPath) {
        String filepath = applicationPath;
        File dir = new File(filepath);
        return dir;
    }

    /**
     * Возвращает <code>String</code> папку
     * @param applicationPath путь нашего веб-приложения
     * @param folder каталог
     * @param subdirectory подкаталог
     * @return возвращает массив строк с именами файлов.
     */
    public String getFolderString(String applicationPath, String folder, String subdirectory) {
        String filepath = applicationPath + File.separator+folder+ File.separator + subdirectory + File.separator;
        return filepath;
    }

    /**
     * Удаляет файл/файлы в каталоге.
     * @param applicationPath путь нашего веб-приложения
     * @param folder каталог
     * @param subdirectory подкаталог
     */
    public void deleteListFolder(String applicationPath, String folder, String subdirectory) {
        File dir = new File(applicationPath + File.separator + folder + File.separator + subdirectory + File.separator);
        try {
            for (File file : dir.listFiles()
            ) {
                if (file.delete()) {
                }
            }
        } catch (NullPointerException e )
        {
            e.printStackTrace();
        }
    }

    /**
     * Удаляет файл/файлы в каталоге.
     * @param applicationPath путь нашего веб-приложения
     */
    public void deleteListFolder(String applicationPath) {
        File dir = new File(applicationPath);
        try {
            for (File file : dir.listFiles()
            ) {
                if (file.delete()) {
                }
            }
        } catch (NullPointerException e )
        {
            e.printStackTrace();
        }
    }
}
