package com.example.pr_kredit_irfansm_ti26_;

import java.net.URLEncoder;

public class Kreditor extends Koneksi {

    Server server = new Server();
    String SERVER = server.urlDatabase1();
    
    String URL = "http://" + SERVER + "/jskreditmotor/tbkreditor1.php";
    String url = ""; 
    String response = "";

    public String tampilKreditorbyIdNama() {
        try {
            url = URL + "?operasi=view"; 
            System.out.println("URL Tampil Kreditor: " + url);
            response = call(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
   
    public String getKreditorById(int idkreditor) {
        try {
            url = URL + "?operasi=get_kreditor_by_id&idkreditor=" + idkreditor;
            System.out.println("URL Get Kreditor by ID: " + url);
            response = call(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    // Fungsi untuk nambah data (Insert) - Diubah ke "insert"
    public String insertKreditor(String idkreditor, String nama, String pekerjaan, String telp, String alamat) {
        try {
            nama = URLEncoder.encode(nama, "UTF-8");
            pekerjaan = URLEncoder.encode(pekerjaan, "UTF-8");
            telp = URLEncoder.encode(telp, "UTF-8");
            alamat = URLEncoder.encode(alamat, "UTF-8");
            
            url = URL + "?operasi=insert&idkreditor=" + idkreditor + 
                  "&nama=" + nama + "&pekerjaan=" + pekerjaan + 
                  "&telp=" + telp + "&alamat=" + alamat;
            System.out.println("URL Insert Kreditor: " + url);
            response = call(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    // Fungsi untuk ngedit data (Update) - Diubah ke "update"
    public String updateKreditor(String idkreditor, String nama, String pekerjaan, String telp, String alamat) {
        try {
            nama = URLEncoder.encode(nama, "UTF-8");
            pekerjaan = URLEncoder.encode(pekerjaan, "UTF-8");
            telp = URLEncoder.encode(telp, "UTF-8");
            alamat = URLEncoder.encode(alamat, "UTF-8");
            
            url = URL + "?operasi=update&idkreditor=" + idkreditor + 
                  "&nama=" + nama + "&pekerjaan=" + pekerjaan + 
                  "&telp=" + telp + "&alamat=" + alamat;
            System.out.println("URL Update Kreditor: " + url);
            response = call(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    // Fungsi untuk hapus data (Delete) - Diubah ke "delete"
    public String deleteKreditor(int idkreditor) {
        try {
            url = URL + "?operasi=delete&idkreditor=" + idkreditor;
            System.out.println("URL Delete Kreditor: " + url);
            response = call(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}