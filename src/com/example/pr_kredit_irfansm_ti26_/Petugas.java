package com.example.pr_kredit_irfansm_ti26_;

public class Petugas extends Koneksi {

    // --- BAGIAN YANG DIPERBAIKI ---
    Server server = new Server();
    String SERVER = server.urlDatabase1();
    String URL = "http://" + SERVER + "/jskreditmotor/tbpetugas1.php";
    // ------------------------------

    // 1. Tampil data petugas (operasi=view)
    public String tampilPetugas() {
        String url = URL + "?operasi=view";
        return call(url);
    }

    // 2. Ambil data petugas berdasarkan ID (operasi=get_petugas_by_kdpetugas)
    public String getPetugasByKdpetugas(int idpetugas) {
        String url = URL + "?operasi=get_petugas_by_kdpetugas&idpetugas=" + idpetugas;
        return call(url);
    }

    // 3. Insert data petugas baru (operasi=insert)
    public String insertPetugas(String kdpetugas, String nama, String jabatan) {
        kdpetugas = kdpetugas.replace(" ", "%20");
        nama = nama.replace(" ", "%20");
        jabatan = jabatan.replace(" ", "%20");

        String url = URL + "?operasi=insert&kdpetugas=" + kdpetugas + "&nama=" + nama + "&jabatan=" + jabatan;
        System.out.println("URL Insert Petugas : " + url);
        return call(url);
    }

    // 4. Update data petugas (operasi=update)
    public String updatePetugas(String idpetugas, String kdpetugas, String nama, String jabatan) {
        idpetugas = idpetugas.replace(" ", "%20");
        kdpetugas = kdpetugas.replace(" ", "%20");
        nama = nama.replace(" ", "%20");
        jabatan = jabatan.replace(" ", "%20");

        String url = URL + "?operasi=update&idpetugas=" + idpetugas + "&kdpetugas=" + kdpetugas + "&nama=" + nama + "&jabatan=" + jabatan;
        System.out.println("URL Update Petugas : " + url);
        return call(url);
    }

    // 5. Delete data petugas (operasi=delete)
    public String deletePetugas(int idpetugas) {
        String url = URL + "?operasi=delete&idpetugas=" + idpetugas;
        System.out.println("URL Hapus Petugas : " + url);
        return call(url);
    }
}