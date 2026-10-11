package com.example.pr_kredit_irfansm_ti26_;

import java.util.ArrayList;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class PengajuanKreditActivity extends Activity implements OnClickListener, OnItemSelectedListener {

    Kreditor kreditor = new Kreditor();
    Motor motor = new Motor();
    Kredit kredit = new Kredit();
    
    Spinner SpinnerNamaKreditor, SpinnerNamaMotor;
    EditText editUangMuka, editBunga, editLamaAngsuran;
    TextView textNamaMotor, textHargaMotor, textHargaKredit, textTotalKredit, textAngsuranPerbulan;
    Button buttonProsesPengajuanKredit, buttonSimpanPengajuanKredit, buttonResetKredit;

    ArrayList<String> arrayListNamaKreditor = new ArrayList<String>();
    ArrayList<String> arrayListNamaMotor = new ArrayList<String>();

    JSONArray arrayKreditor;
    JSONArray arrayMotor;
    String idkreditorspiner;

    private double HargaKredit = 0.0;
    private double TotalKredit = 0.0;
    private double Angsuran = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajuan_kredit);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        
        SpinnerNamaKreditor = (Spinner) findViewById(R.id.SpinnerNamaKreditor);
        
        SpinnerNamaMotor = (Spinner) findViewById(R.id.SpinnerNamaMotor);
        SpinnerNamaMotor.setOnItemSelectedListener(this);

        textNamaMotor = (TextView) findViewById(R.id.textNamaMotor);
        textHargaMotor = (TextView) findViewById(R.id.textHargaMotor);
        textHargaKredit = (TextView) findViewById(R.id.textHargaKredit);
        textTotalKredit = (TextView) findViewById(R.id.textTotalKredit);
        textAngsuranPerbulan = (TextView) findViewById(R.id.textAngsuranPerbulan);

        editBunga = (EditText) findViewById(R.id.editBunga);
        editUangMuka = (EditText) findViewById(R.id.editUangMuka);
        editLamaAngsuran = (EditText) findViewById(R.id.editLamaAngsuran);
        
        buttonProsesPengajuanKredit = (Button) findViewById(R.id.buttonProsesPengajuanKredit);
        buttonSimpanPengajuanKredit = (Button) findViewById(R.id.buttonSimpanPengajuanKredit);
        buttonResetKredit = (Button) findViewById(R.id.buttonResetKredit);

        Spinerkreditor();
        Spinermotor();
    }

    // Procedure menampilkan ke spinner Kreditor
    public void Spinerkreditor() {
        try {
            arrayKreditor = new JSONArray(kreditor.tampilKreditorbyIdNama());
            for (int i = 0; i < arrayKreditor.length(); i++) {
                JSONObject jsonChildNode = arrayKreditor.getJSONObject(i);
                String idkreditor = jsonChildNode.optString("idkreditor");
                String nama = jsonChildNode.optString("nama");
                arrayListNamaKreditor.add(idkreditor);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        ArrayAdapter<String> adapterNamaKreditor = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, arrayListNamaKreditor);
        adapterNamaKreditor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerNamaKreditor.setAdapter(adapterNamaKreditor);
        SpinnerNamaKreditor.setSelection(0);
    }

    // Procedure menampilkan ke spinner Motor
    public void Spinermotor() {
        try {
            arrayMotor = new JSONArray(motor.tampilMotorbyIdNama());
            for (int i = 0; i < arrayMotor.length(); i++) {
                JSONObject jsonChildNode = arrayMotor.getJSONObject(i);
                String kdmotor = jsonChildNode.optString("kdmotor");
                arrayListNamaMotor.add(kdmotor);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        ArrayAdapter<String> adapterNamaMotor = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, arrayListNamaMotor);
        adapterNamaMotor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerNamaMotor.setAdapter(adapterNamaMotor);
        SpinnerNamaMotor.setSelection(0);
    }

    public void HitungKredit() {
        String sharga = textHargaMotor.getText().toString();
        String sdp = editUangMuka.getText().toString();
        String sbunga = editBunga.getText().toString();
        String slama = editLamaAngsuran.getText().toString();

        if ((sharga.equalsIgnoreCase("")) || (sharga.equalsIgnoreCase("0"))
                || (sdp.equalsIgnoreCase("")) || (sdp.equalsIgnoreCase("0"))
                || (sbunga.equalsIgnoreCase("")) || (sbunga.equalsIgnoreCase("0"))
                || (slama.equalsIgnoreCase("")) || (slama.equalsIgnoreCase("0"))) {
            Toast.makeText(this, "Silahkan Lengkapi Data !", Toast.LENGTH_LONG).show();
        } else {
            double harga = Double.parseDouble(textHargaMotor.getText().toString());
            double dp = Double.parseDouble(editUangMuka.getText().toString());
            double bunga = Double.parseDouble(editBunga.getText().toString());
            double lama = Double.parseDouble(editLamaAngsuran.getText().toString());

            HargaKredit = harga - dp;
            TotalKredit = HargaKredit + (HargaKredit * (bunga / 100) * 12);
            Angsuran = TotalKredit / lama;

            String sHargaKredit = String.format(Locale.US, "%,.2f", HargaKredit);
            textHargaKredit.setText(sHargaKredit);

            String sTotalKredit = String.format(Locale.US, "%,.2f", TotalKredit);
            textTotalKredit.setText(sTotalKredit);

            String sAngsuran = String.format(Locale.US, "%,.2f", Angsuran);
            textAngsuranPerbulan.setText(sAngsuran);
        }
    }

    public void KlikbuttonProsesPengajuanKredit(View v) {
        HitungKredit();
    }

    public void KlikbuttonResetKredit(View v) {
        editUangMuka.setText("");
        editBunga.setText("");
        editLamaAngsuran.setText("");
        textHargaKredit.setText("");
        textTotalKredit.setText("");
        textAngsuranPerbulan.setText("");
        textNamaMotor.setText("-");
        textHargaMotor.setText("0");
    }

    public void KlikbuttonSimpanPengajuanKredit(View v) {
        simpanKredit();
    }

    // Metode tambah kredit
    public void simpanKredit() {
        String idkreditor = SpinnerNamaKreditor.getSelectedItem() != null ? SpinnerNamaKreditor.getSelectedItem().toString() : "";
        String kdmotor = SpinnerNamaMotor.getSelectedItem() != null ? SpinnerNamaMotor.getSelectedItem().toString() : "";
        String hrgtunai = textHargaMotor.getText().toString();
        String dp = editUangMuka.getText().toString();
        String hrgkredit = textHargaKredit.getText().toString();
        String bunga = editBunga.getText().toString();
        String lama = editLamaAngsuran.getText().toString();
        String totalkredit = textTotalKredit.getText().toString();
        String angsuran = textAngsuranPerbulan.getText().toString();

        String laporan = kredit.simpan_kredit(idkreditor, kdmotor, hrgtunai, dp, hrgkredit, bunga, lama, totalkredit, angsuran);
        Toast.makeText(PengajuanKreditActivity.this, laporan, Toast.LENGTH_SHORT).show();

        finish();
        startActivity(getIntent());
    }

    // Method untuk menangkap detail motor saat spinner dipilih
    private void getKdmotorKredit() {
        if (SpinnerNamaMotor.getSelectedItem() != null) {
            String kdmotor = SpinnerNamaMotor.getSelectedItem().toString();
            try {
                String responServer = motor.select_by_KdmotorKredit(kdmotor);
                if (responServer != null && responServer.startsWith("[")) {
                    JSONArray arrayDataMotor = new JSONArray(responServer);
                    for (int i = 0; i < arrayDataMotor.length(); i++) {
                        JSONObject jsonChildNode = arrayDataMotor.getJSONObject(i);
                        String nama = jsonChildNode.optString("nama");
                        String harga = jsonChildNode.optString("harga");
                        textNamaMotor.setText(nama);
                        textHargaMotor.setText(harga);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pengajuan_kredit, menu);
        return true;
    }

    @Override
    public void onClick(View arg0) {}

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {}

    @Override
    public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
        getKdmotorKredit();
    }
}