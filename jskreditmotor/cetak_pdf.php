<?php
include "koneksi.php";

$invoice = $_GET['invoice'];

// Query menggunakan JOIN untuk menggabungkan tabel kredit, kreditor, dan motor
$query = mysqli_query($koneksi, "
    SELECT kredit.*, kreditor.nama AS nama_kreditor, kreditor.alamat, motor.nama AS nama_motor 
    FROM kredit 
    JOIN kreditor ON kredit.idkreditor = kreditor.idkreditor
    JOIN motor ON kredit.kdmotor = motor.kdmotor
    WHERE kredit.invoice = '$invoice'
");

$data = mysqli_fetch_array($query);

// Fungsi helper untuk membersihkan format angka sebelum diformat ulang
function formatRupiah($angka) {
    // Hilangkan koma/karakter non-angka jika ada, lalu jadikan float
    $bersih = floatval(str_replace(',', '', $angka));
    return number_format($bersih, 0, ',', '.');
}
?>

<!DOCTYPE html>
<html>
<head>
    <title>Cetak Data Pengajuan Kredit</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            font-size: 14px;
            color: #000;
            padding: 20px;
        }
        h2 {
            text-align: center;
            margin-bottom: 30px;
        }
        table {
            width: 100%;
            max-width: 600px;
            margin: 0 auto;
            border-collapse: collapse;
        }
        td {
            padding: 6px 4px;
            vertical-align: top;
        }
        .label {
            width: 35%;
            font-weight: bold;
        }
        .colon {
            width: 3%;
        }
        .value {
            width: 62%;
        }
        .footer-section {
            margin-top: 50px;
            width: 100%;
            max-width: 600px;
            margin-left: auto;
            margin-right: auto;
        }
    </style>
</head>
<body onload="window.print()">

    <h2>Data Pengajuan Kredit</h2>

    <table>
        <tr>
            <td class="label">Invoice</td>
            <td class="colon">:</td>
            <td class="value"><?php echo $data['invoice']; ?></td>
        </tr>
        <tr>
            <td class="label">Tanggal</td>
            <td class="colon">:</td>
            <td class="value"><?php echo $data['tanggal']; ?></td>
        </tr>
        <tr>
            <td class="label">ID Kreditor</td>
            <td class="colon">:</td>
            <td class="value"><?php echo $data['idkreditor']; ?></td>
        </tr>
        <tr>
            <td class="label">Nama</td>
            <td class="colon">:</td>
            <td class="value"><?php echo $data['nama_kreditor']; ?></td>
        </tr>
        <tr>
            <td class="label">Alamat</td>
            <td class="colon">:</td>
            <td class="value"><?php echo $data['alamat']; ?></td>
        </tr>
        <tr>
            <td class="label">Kode Motor</td>
            <td class="colon">:</td>
            <td class="value"><?php echo $data['kdmotor']; ?></td>
        </tr>
        <tr>
            <td class="label">Nama Motor</td>
            <td class="colon">:</td>
            <td class="value"><?php echo $data['nama_motor']; ?></td>
        </tr>
        <tr>
            <td class="label">Harga Tunai</td>
            <td class="colon">:</td>
            <td class="value">Rp. <?php echo formatRupiah($data['hrgtunai']); ?>,-</td>
        </tr>
        <tr>
            <td class="label">DP</td>
            <td class="colon">:</td>
            <td class="value">Rp. <?php echo formatRupiah($data['dp']); ?>,-</td>
        </tr>
        <tr>
            <td class="label">Hrg Kredit</td>
            <td class="colon">:</td>
            <td class="value">Rp. <?php echo formatRupiah($data['hrgkredit']); ?>,-</td>
        </tr>
        <tr>
            <td class="label">Bunga</td>
            <td class="colon">:</td>
            <td class="value"><?php echo $data['bunga']; ?>% / Tahun</td>
        </tr>
        <tr>
            <td class="label">Lama</td>
            <td class="colon">:</td>
            <td class="value"><?php echo $data['lama']; ?> Bulan</td>
        </tr>
        <tr>
            <td class="label">Total Kredit</td>
            <td class="colon">:</td>
            <td class="value">Rp. <?php echo formatRupiah($data['totalkredit']); ?>,-</td>
        </tr>
        <tr>
            <td class="label">Angsuran /bln</td>
            <td class="colon">:</td>
            <td class="value">Rp. <?php echo formatRupiah($data['angsuran']); ?>,-</td>
        </tr>
    </table>

    <div class="footer-section">
        <p>Menyatakan bahwa data diatas benar.</p>
        <br><br>
        <p>Semarang, <?php echo date('d-M-Y'); ?></p>
        <br><br><br>
        <p><b>Sales</b></p>
    </div>

</body>
</html>