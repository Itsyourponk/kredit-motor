<?php
$server = "localhost";
$username = "root";
$password = "";
$database = "db_android_1";
$koneksi = mysqli_connect($server, $username, $password, $database);

@$operasi = $_GET['operasi'];

switch ($operasi) { 
    case "view":
        $query = mysqli_query($koneksi, "SELECT * FROM kreditor") or die(mysqli_error($koneksi));
        $data_array = array();
        while ($data = mysqli_fetch_assoc($query)) {
            $data_array[] = $data;
        }
        echo json_encode($data_array); 
        break;

    case "insert":
        // Karena idkreditor tidak ada auto_increment, kita ambil dari inputan Android
        @$idkreditor = $_GET['idkreditor'];
        @$nama = $_GET['nama']; 
        @$pekerjaan = $_GET['pekerjaan'];
        @$telp = $_GET['telp'];
        @$alamat = $_GET['alamat'];

        $query = mysqli_query($koneksi, "INSERT INTO kreditor (idkreditor, nama, pekerjaan, telp, alamat) VALUES('$idkreditor', '$nama', '$pekerjaan', '$telp', '$alamat')");
        
        if ($query) {
            echo "Data Berhasil Disimpan";
        } else {
            echo "Error Insert: " . mysqli_error($koneksi);
        }
        break;

    case "get_kreditor_by_id":
        @$idkreditor = $_GET['idkreditor'];
        $query = mysqli_query($koneksi, "SELECT * FROM kreditor WHERE idkreditor='$idkreditor'") or die(mysqli_error($koneksi));
        $data_array = mysqli_fetch_assoc($query); 
        echo "[" . json_encode($data_array) . "]";
        break;

    case "update":
        @$idkreditor = $_GET['idkreditor']; 
        @$nama = $_GET['nama']; 
        @$pekerjaan = $_GET['pekerjaan'];
        @$telp = $_GET['telp'];
        @$alamat = $_GET['alamat'];

        $query = mysqli_query($koneksi, "UPDATE kreditor SET nama='$nama', pekerjaan='$pekerjaan', telp='$telp', alamat='$alamat' WHERE idkreditor='$idkreditor'");
        
        if ($query) { 
            echo "Update Data Berhasil";
        } else {
            echo "Error Update: " . mysqli_error($koneksi);
        }
        break;

    case "delete":
        @$idkreditor = $_GET['idkreditor'];
        $query = mysqli_query($koneksi, "DELETE FROM kreditor WHERE idkreditor='$idkreditor'"); 
        
        if ($query) {
            echo "Delete Data Berhasil";
        } else {
            echo "Error Delete: " . mysqli_error($koneksi);
        }
        break;

    default: 
        echo "Operasi tidak ditemukan";
        break;
}
?>