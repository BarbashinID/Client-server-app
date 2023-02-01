package org.example;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class DataDownload {
    public DataDownload(String json, String ftpUrl, String host, String user, String pass, String uploadPath) {

            ftpUrl = String.format(ftpUrl, user, pass, host, uploadPath);
            System.out.println("Upload URL: " + ftpUrl);

            try {
                URL url = new URL(ftpUrl);
                URLConnection conn = url.openConnection();
                OutputStream outputStream = conn.getOutputStream();
                InputStream inputStream = new ByteArrayInputStream(json.getBytes());

                int len = inputStream.available();
                byte[] buffer = new byte[len];
                int bytesRead = -1;
                while ((bytesRead = inputStream.read(buffer)) != -1) {

                    outputStream.write(buffer, 0, bytesRead);
                }
            inputStream.close();
            outputStream.close();


        } catch (
                IOException ex) {
            ex.printStackTrace();
        }
    }
}
