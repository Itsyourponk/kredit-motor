package com.example.pr_kredit_irfansm_ti26_;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Koneksi {

    public String call(String url) {
        int BUFFER_SIZE = 2000;
        InputStream in = null;

        try {
            in = OpenHttpConnection(url);
        } catch (IOException e) {
            e.printStackTrace();
            return ""; // Kembalikan kosong jika gagal total agar tidak nge-crash
        }

        if (in == null) {
            return "";
        }

        InputStreamReader isr = new InputStreamReader(in);
        int charRead;
        StringBuilder str = new StringBuilder();
        char[] inputBuffer = new char[BUFFER_SIZE];

        try {
            while ((charRead = isr.read(inputBuffer)) > 0) {
                str.append(inputBuffer, 0, charRead);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

        return str.toString();
    }

    private InputStream OpenHttpConnection(String url) throws IOException {
        InputStream in = null;
        URL url1 = new URL(url);

        URLConnection conn = url1.openConnection();
        if (!(conn instanceof HttpURLConnection)) {
            throw new IOException("Not An Http Connection");
        }

        try {
            HttpURLConnection httpconn = (HttpURLConnection) conn;
            httpconn.setAllowUserInteraction(false);
            httpconn.setInstanceFollowRedirects(true);
            httpconn.setRequestMethod("GET");
            httpconn.connect();

            in = httpconn.getInputStream();
            
        } catch (Exception e) {
            throw new IOException("Error connecting: " + e.getMessage());
        }

        return in;
    }
}