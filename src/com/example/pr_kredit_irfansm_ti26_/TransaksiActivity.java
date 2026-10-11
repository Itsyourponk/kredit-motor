package com.example.pr_kredit_irfansm_ti26_;

import android.os.Bundle; 
import android.app.Activity; 
import android.content.Intent; 
import android.view.Menu; 
import android.view.View; 
import android.widget.Button;

public class TransaksiActivity extends Activity {
    Button btPengajuanKredit, btPembayaranAngsuran, btDataPengajuanKredit, btDataPembayaranAngsuran;

    @Override
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_transaksi);
        
        btPengajuanKredit = (Button) findViewById(R.id.btPengajuanKredit); 
        btPembayaranAngsuran = (Button) findViewById(R.id.btPembayaranAngsuran); 
        btDataPengajuanKredit = (Button) findViewById(R.id.btDataPengajuanKredit);
        btDataPembayaranAngsuran = (Button) findViewById(R.id.btDataPembayaranAngsuran);
    }

    // Tombol Form Input Pengajuan Kredit
    public void KlikbtPengajuanKredit(View v){
        Intent i = new Intent(getApplicationContext(), PengajuanKreditActivity.class);
        startActivity(i);
    }

    // Tombol Form Pembayaran Angsuran
    public void KlikbtPembayaranAngsuran(View v){ 
        Intent i = new Intent(getApplicationContext(), PembayaranAngsuranActivity.class);
        startActivity(i);
    }

    // Tombol Tabel Data Pengajuan Kredit (SUDAH DIPERBAIKI)
    public void KlikbtDataPengajuanKredit(View v){ 
        Intent i = new Intent(getApplicationContext(), DataPengajuanKredit.class);
        startActivity(i);
    }
    
    // Tombol Tabel Data Pembayaran Angsuran
    public void KlikbtDataPembayaranAngsuran(View v){ 
        Intent i = new Intent(getApplicationContext(), DataPembayaranAngsuran.class);
        startActivity(i);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.transaksi, menu);
        return true;
    }
}