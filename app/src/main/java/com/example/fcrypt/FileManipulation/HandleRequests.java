package com.example.fcrypt.FileManipulation;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;

public class HandleRequests {
    String attachmentName = "bitmap";
    String attachmentFileName = "bitmap.bmp";
    String crlf = "\r\n";
    String twoHyphens = "--";
    String boundary =  "*****";

    URL url;
    HttpURLConnection httpUrlConnection = null;
    DataOutputStream request;
    Bitmap bitmap;

    public HandleRequests(String request_url) throws IOException {
        url = new URL(request_url);
        httpUrlConnection = (HttpURLConnection) url.openConnection();
        httpUrlConnection.setUseCaches(false);
        httpUrlConnection.setDoOutput(true);

        httpUrlConnection.setRequestMethod("POST");
        httpUrlConnection.setRequestProperty("Connection", "Keep-Alive");
        httpUrlConnection.setRequestProperty("Cache-Control", "no-cache");
        httpUrlConnection.setRequestProperty(
                "Content-Type", "multipart/form-data;boundary=" + this.boundary);

         request = new DataOutputStream(
                httpUrlConnection.getOutputStream());
    }
    public void setContentType(String content_type){
        httpUrlConnection.setRequestProperty(
                "Content-Type", content_type+";boundary=" + this.boundary);
    }

    public void sendFile(Uri uri) throws IOException {

        request.writeBytes(this.twoHyphens + this.boundary + this.crlf);
        request.writeBytes("Content-Disposition: form-data; name=\"" +
                this.attachmentName + "\";filename=\"" +
                this.attachmentFileName + "\"" + this.crlf);
        request.writeBytes(this.crlf);

        FileManipulation file_manipulation = new FileManipulation(uri);
        bitmap = file_manipulation.getBitmapOfFile();

        byte[] pixels = new byte[bitmap.getWidth() * bitmap.getHeight()];
        for (int i = 0; i < bitmap.getWidth(); ++i) {
            for (int j = 0; j < bitmap.getHeight(); ++j) {
                //we're interested only in the MSB of the first byte,
                //since the other 3 bytes are identical for B&W images
                pixels[i + j] = (byte) ((bitmap.getPixel(i, j) & 0x80) >> 7);
            }
        }
        request.write(pixels);

        request.flush();
        request.close();
    }
    public String getResponse() throws IOException {
        InputStream responseStream = new
                BufferedInputStream(httpUrlConnection.getInputStream());

        BufferedReader responseStreamReader =
                new BufferedReader(new InputStreamReader(responseStream));

        String line = "";
        StringBuilder stringBuilder = new StringBuilder();

        while ((line = responseStreamReader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        responseStreamReader.close();

        return stringBuilder.toString();
    }
}
