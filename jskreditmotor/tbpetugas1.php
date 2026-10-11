<?php

$server = "localhost";
$username = "root";
$password = "";
$database = "db_android_1";
$koneksi = mysqli_connect($server, $username, $password, $database);

// Cek koneksi
if (!$koneksi) {
    die("Koneksi gagal: " . mysqli_connect_error());
}

@$operasi = $_GET['operasi'];

switch ($operasi) {
    case "view":
        $query_tampil_petugas = mysqli_query($koneksi, "SELECT * FROM petugas") or die(mysqli_error($koneksi));
        $data_array = array();
        while ($data = mysqli_fetch_assoc($query_tampil_petugas)) {
            $data_array[] = $data;
        }
        echo json_encode($data_array);
        break;

    case "insert":
        /* Source code untuk Insert data */
        @$kdpetugas = $_GET['kdpetugas'];
        @$nama = $_GET['nama'];
        @$jabatan = $_GET['jabatan'];
        
        $query_insert_data = mysqli_query($koneksi, "INSERT INTO petugas (kdpetugas, nama, jabatan) VALUES('$kdpetugas', '$nama', '$jabatan')");
        if ($query_insert_data) {
            echo "Data Berhasil Disimpan";
        } else {
            echo "Error Insert petugas: " . mysqli_error($koneksi);
        }
        break;

    case "get_petugas_by_kdpetugas":
        /* Source code untuk mengambil data berdasarkan idpetugas yang diminta */
        @$idpetugas = $_GET['idpetugas'];
        
        $query_tampil_petugas = mysqli_query($koneksi, "SELECT * FROM petugas WHERE idpetugas='$idpetugas'") or die(mysqli_error($koneksi));
        $data_array = mysqli_fetch_assoc($query_tampil_petugas);
        
        echo "[" . json_encode($data_array) . "]";
        break;

    case "update":
        @$idpetugas = $_GET['idpetugas']; 
        @$kdpetugas = $_GET['kdpetugas'];
        @$nama = $_GET['nama']; 
        @$jabatan = $_GET['jabatan'];

        $query_update_petugas = mysqli_query($koneksi, "UPDATE petugas SET kdpetugas='$kdpetugas', nama='$nama', jabatan='$jabatan' WHERE idpetugas='$idpetugas'"); 
        if ($query_update_petugas) {
            echo "Update Data Berhasil";
        } else {
            echo mysqli_error($koneksi);
        }
        break;

    case "delete":
        /* Source code untuk Delete data */
        @$idpetugas = $_GET['idpetugas'];

        $query_delete_petugas = mysqli_query($koneksi, "DELETE FROM petugas WHERE idpetugas='$idpetugas'");
        if ($query_delete_petugas) {
            echo "Delete Data Berhasil";
        } else {
            echo mysqli_error($koneksi);
        }
        break;
        
    default: 
        break;
}
?>

