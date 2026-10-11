<?php
$host = "localhost";
$user = "root";
$pass = "";
$db   = "db_android_1"; // Diubah sesuai phpMyAdmin

$koneksi = mysqli_connect($host, $user, $pass, $db);

if (!$koneksi) {
    die("Koneksi database gagal: " . mysqli_connect_error());
}
?>