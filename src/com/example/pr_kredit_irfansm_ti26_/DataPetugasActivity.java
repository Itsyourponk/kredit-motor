package com.example.pr_kredit_irfansm_ti26_;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class DataPetugasActivity extends Activity implements OnClickListener {

    // Inisialisasi Objek + Variabel + Class
    Petugas petugas = new Petugas();
    TableLayout tbPetugas;
    Button btTambahPetugas, btRefreshDataPetugas;

    ArrayList<Button> buttonEdit = new ArrayList<Button>();
    ArrayList<Button> buttonDelete = new ArrayList<Button>();
    JSONArray arrayPetugas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_petugas);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        // Pemberian Nama komponen
        tbPetugas = (TableLayout) findViewById(R.id.tbPetugas);
        btTambahPetugas = (Button) findViewById(R.id.btTambahPetugas);
        btRefreshDataPetugas = (Button) findViewById(R.id.btRefreshDataPetugas);

        tampildataPetugas();
    }

    // Metode KlikbtTambahPetugas
    public void KlikbtTambahPetugas(View v) {
        tambahPetugas();
    }

    // Refresh Data
    public void klikRefreshDataMahasiswa(View v) {
        /* restart activity */
        finish();
        startActivity(getIntent());
    }

    // Metode Tampil Data Petugas
    public void tampildataPetugas() {
        TableRow barisTabel = new TableRow(this);
        barisTabel.setBackgroundColor(Color.BLACK);

        // Memberi ID Header Tabel
        TextView viewHeaderKdPetugas = new TextView(this);
        TextView viewHeaderNama = new TextView(this);
        TextView viewHeaderJabatan = new TextView(this);
        TextView viewHeaderAction = new TextView(this);

        // Memberi Nama kolom HEADER
        viewHeaderKdPetugas.setText("KdPetugas");
        viewHeaderNama.setText("Nama");
        viewHeaderJabatan.setText("Jabatan");
        viewHeaderAction.setText("Action");

        viewHeaderKdPetugas.setTextColor(Color.WHITE);
        viewHeaderNama.setTextColor(Color.WHITE);
        viewHeaderJabatan.setTextColor(Color.WHITE);
        viewHeaderAction.setTextColor(Color.WHITE);

        viewHeaderKdPetugas.setPadding(5, 1, 5, 1);
        viewHeaderNama.setPadding(5, 1, 5, 1);
        viewHeaderJabatan.setPadding(5, 1, 5, 1);
        viewHeaderAction.setPadding(5, 1, 5, 1);

        barisTabel.addView(viewHeaderKdPetugas);
        barisTabel.addView(viewHeaderNama);
        barisTabel.addView(viewHeaderJabatan);
        barisTabel.addView(viewHeaderAction);

        tbPetugas.addView(barisTabel, new TableLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        try {
        	arrayPetugas = new JSONArray(petugas.tampilPetugas());

            for (int i = 0; i < arrayPetugas.length(); i++) {
                JSONObject jsonChildNode = arrayPetugas.getJSONObject(i);

                // Ambil data dari JSON tabel database
                String idpetugas = jsonChildNode.optString("idpetugas");
                String kdpetugas = jsonChildNode.optString("kdpetugas");
                String nama = jsonChildNode.optString("nama");
                String jabatan = jsonChildNode.optString("jabatan");

                barisTabel = new TableRow(this);
                if (i % 2 == 0) {
                    barisTabel.setBackgroundColor(Color.LTGRAY);
                }

                TextView viewKdPetugas = new TextView(this);
                viewKdPetugas.setText(kdpetugas);
                viewKdPetugas.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewKdPetugas);

                TextView viewNama = new TextView(this);
                viewNama.setText(nama);
                viewNama.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewNama);

                TextView viewJabatan = new TextView(this);
                viewJabatan.setText(jabatan);
                viewJabatan.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewJabatan);

                // Button Edit
                buttonEdit.add(i, new Button(this));
                buttonEdit.get(i).setId(Integer.parseInt(idpetugas));
                buttonEdit.get(i).setTag("Edit");
                buttonEdit.get(i).setText("Edit");
                buttonEdit.get(i).setOnClickListener(this);
                barisTabel.addView(buttonEdit.get(i));

                // Button Delete
                buttonDelete.add(i, new Button(this));
                buttonDelete.get(i).setId(Integer.parseInt(idpetugas));
                buttonDelete.get(i).setTag("Delete");
                buttonDelete.get(i).setText("Delete");
                buttonDelete.get(i).setOnClickListener(this);
                barisTabel.addView(buttonDelete.get(i));

                tbPetugas.addView(barisTabel, new TableLayout.LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Hapus Petugas berdasarkan ID
    public void deletePetugas(int idpetugas) {
        petugas.deletePetugas(idpetugas);
        /* restart activity */
        finish();
        startActivity(getIntent());
    }

    // Ambil data petugas berdasarkan Id
    public void getPetugasByKdpetugas(int idpetugas) {
        String idpetugasEdit = "";
        String kdpetugasEdit = "";
        String namaEdit = "";
        String jabatanEdit = "";
        JSONArray arrayPersonal;

        try {
            arrayPersonal = new JSONArray(petugas.getPetugasByKdpetugas(idpetugas));

            for (int i = 0; i < arrayPersonal.length(); i++) {
                JSONObject jsonChildNode = arrayPersonal.getJSONObject(i);

                idpetugasEdit = jsonChildNode.optString("idpetugas");
                kdpetugasEdit = jsonChildNode.optString("kdpetugas");
                namaEdit = jsonChildNode.optString("nama");
                jabatanEdit = jsonChildNode.optString("jabatan");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);

        final TextView viewKdpetugas = new TextView(this);
        viewKdpetugas.setText("Kode = " + kdpetugasEdit);
        viewKdpetugas.setBackgroundColor(Color.TRANSPARENT);
        viewKdpetugas.setTextColor(Color.WHITE);
        viewKdpetugas.setTextSize(20);
        layoutInput.addView(viewKdpetugas);

        final EditText editIdPetugas = new EditText(this);
        editIdPetugas.setText(idpetugasEdit);
        // Bisa di-hide atau dimatikan jika id tidak boleh diubah:
        editIdPetugas.setVisibility(View.GONE);
        layoutInput.addView(editIdPetugas);

        final EditText editNama = new EditText(this);
        editNama.setText(namaEdit);
        layoutInput.addView(editNama);

        final EditText editJabatan = new EditText(this);
        editJabatan.setText(jabatanEdit);
        layoutInput.addView(editJabatan);

        final String finalKdpetugasEdit = kdpetugasEdit;

        AlertDialog.Builder builderEditPetugas = new AlertDialog.Builder(this);
        builderEditPetugas.setTitle("Update Petugas");
        builderEditPetugas.setView(layoutInput);
        builderEditPetugas.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String idpetugas = editIdPetugas.getText().toString();
                String kdpetugas = finalKdpetugasEdit;
                String nama = editNama.getText().toString();
                String jabatan = editJabatan.getText().toString();
                String laporan = petugas.updatePetugas(idpetugas, kdpetugas, nama, jabatan);
                Toast.makeText(DataPetugasActivity.this, laporan, Toast.LENGTH_SHORT).show();

                /* restart activity */
                finish();
                startActivity(getIntent());
            }
        });

        builderEditPetugas.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderEditPetugas.show();
    }

    // Metode tambah Petugas
    public void tambahPetugas() {
        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);

        final EditText editKdPetugas = new EditText(this);
        editKdPetugas.setHint("KdPetugas");
        layoutInput.addView(editKdPetugas);

        final EditText editNama = new EditText(this);
        editNama.setHint("Nama");
        layoutInput.addView(editNama);

        final EditText editJabatan = new EditText(this);
        editJabatan.setHint("Jabatan");
        layoutInput.addView(editJabatan);

        AlertDialog.Builder builderInsertPetugas = new AlertDialog.Builder(this);
        builderInsertPetugas.setTitle("Insert Petugas");
        builderInsertPetugas.setView(layoutInput);
        builderInsertPetugas.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String kdpetugas = editKdPetugas.getText().toString();
                String nama = editNama.getText().toString();
                String jabatan = editJabatan.getText().toString();
                String laporan = petugas.insertPetugas(kdpetugas, nama, jabatan);
                Toast.makeText(DataPetugasActivity.this, laporan, Toast.LENGTH_SHORT).show();

                /* restart activity */
                finish();
                startActivity(getIntent());
            }
        });

        builderInsertPetugas.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderInsertPetugas.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.data_petugas, menu);
        return true;
    }

    // Metode OnClick ketika Button Delete / Edit dipilih
    @Override
    public void onClick(View view) {
        for (int i = 0; i < buttonEdit.size(); i++) {
            /* jika yang diklik adalah button edit */
            if (view.getId() == buttonEdit.get(i).getId() && view.getTag().toString().trim().equals("Edit")) {
                int idpetugas = buttonEdit.get(i).getId();
                getPetugasByKdpetugas(idpetugas);
                break;
            }
            /* jika yang diklik adalah button delete */
            else if (view.getId() == buttonDelete.get(i).getId() && view.getTag().toString().trim().equals("Delete")) {
                int idpetugas = buttonDelete.get(i).getId();
                deletePetugas(idpetugas);
                break;
            }
        }
    }
}