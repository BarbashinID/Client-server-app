package org.example;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class DataUpload {
    public String DataUpload(String ftpUrl, String host, String user, String pass, String uploadPath) {
        String json = new String();

        ftpUrl = String.format(ftpUrl, user, pass, host, uploadPath);
        System.out.println("Upload URL: " + ftpUrl);
        try {

            URL url = new URL(ftpUrl);
            URLConnection conn = url.openConnection();
            InputStream inputStream = conn.getInputStream();
            OutputStream outputStream = new ByteArrayOutputStream();
            int len = inputStream.available();
            byte[] buffer = new byte[len];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {

                outputStream.write(buffer, 0, bytesRead);

                json  = new String(buffer, "UTF-8");
                json = json.replace(" ", "").replace("\r", "").replace("\n", "");

            }
            inputStream.close();
            outputStream.close();


        } catch (
                IOException ex) {
            ex.printStackTrace();
        }
        return json;

    }
}
