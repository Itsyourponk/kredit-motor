package com.example.pr_kredit_irfansm_ti26_;

import java.net.URLEncoder;

public class Kredit extends Koneksi {

    private long id;

    Server server = new Server();
    String SERVER = server.urlDatabase1();
    String URL = "http://" + SERVER + "/jskreditmotor/tbkredit.php";
    String url = "";
    String response = "";

    public String tampil_query_kredit() {
        try {
            url = URL + "?operasi=query_kredit";
            System.out.println("URL tampil_query_kredit: " + url);
            response = call(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public String tampilKreditorbyIdNama() {
        try {
            url = URL + "?operasi=select_by_idnama";
            System.out.println("URL Tampil Kreditor: " + url);
            response = call(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public String simpan_kredit(String idkreditor, String kdmotor, String hrgtunai, String dp, String hrgkredit, String bunga, String lama, String totalkredit, String angsuran) {
        try {
            // Encoding parameter agar aman dari spasi atau karakter spesial di URL
            idkreditor = URLEncoder.encode(idkreditor, "UTF-8");
            kdmotor = URLEncoder.encode(kdmotor, "UTF-8");
            hrgtunai = URLEncoder.encode(hrgtunai, "UTF-8");
            dp = URLEncoder.encode(dp, "UTF-8");
            hrgkredit = URLEncoder.encode(hrgkredit, "UTF-8");
            bunga = URLEncoder.encode(bunga, "UTF-8");
            lama = URLEncoder.encode(lama, "UTF-8");
            totalkredit = URLEncoder.encode(totalkredit, "UTF-8");
            angsuran = URLEncoder.encode(angsuran, "UTF-8");

            url = URL + "?operasi=simpan_kredit" +
                    "&idkreditor=" + idkreditor +
                    "&kdmotor=" + kdmotor +
                    "&hrgtunai=" + hrgtunai +
                    "&dp=" + dp +
                    "&hrgkredit=" + hrgkredit +
                    "&bunga=" + bunga +
                    "&lama=" + lama +
                    "&totalkredit=" + totalkredit +
                    "&angsuran=" + angsuran;

            System.out.println("URL simpan_kredit : " + url);
            response = call(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public String hapus_kredit(int invoice) {
        try {
            url = URL + "?operasi=hapus_kredit&invoice=" + invoice;
            System.out.println("URL Hapus kredit : " + url);
            response = call(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public long getId() {
        return id;
    }
}