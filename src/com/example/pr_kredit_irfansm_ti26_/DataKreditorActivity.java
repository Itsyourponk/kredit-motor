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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class DataKreditorActivity extends Activity implements OnClickListener {
    Kreditor kreditor = new Kreditor();
    TableLayout tbKreditor;
    Button btTambahKreditor, btRefreshDataKreditor;

    ArrayList<Button> buttonEdit = new ArrayList<Button>();
    ArrayList<Button> buttonDelete = new ArrayList<Button>();
    JSONArray arrayKreditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_kreditor);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        tbKreditor = (TableLayout) findViewById(R.id.tbKreditor);
        btTambahKreditor = (Button) findViewById(R.id.btTambahKreditor);
        btRefreshDataKreditor = (Button) findViewById(R.id.btRefreshDataKreditor);
        
        tampildataKreditor();
    }

    public void KlikbtTambahKreditor(View v) {
        tambahKreditor();
    }

    public void KlikbtRefreshDataKreditor(View v) {
        finish();
        startActivity(getIntent());
    }

    public void tampildataKreditor() {
        tbKreditor.removeAllViews();
        buttonEdit.clear();
        buttonDelete.clear();

        TableRow barisTabel = new TableRow(this);
        barisTabel.setBackgroundColor(Color.BLACK);

        TextView viewHeaderIdKreditor = new TextView(this);
        TextView viewHeaderNama = new TextView(this);
        TextView viewHeaderPekerjaan = new TextView(this);
        TextView viewHeaderTelp = new TextView(this);
        TextView viewHeaderAlamat = new TextView(this);
        TextView viewHeaderAction = new TextView(this);

        viewHeaderIdKreditor.setText("ID");
        viewHeaderNama.setText("Nama");
        viewHeaderPekerjaan.setText("Pekerjaan");
        viewHeaderTelp.setText("Telp");
        viewHeaderAlamat.setText("Alamat");
        viewHeaderAction.setText("Action");

        viewHeaderIdKreditor.setTextColor(Color.WHITE);
        viewHeaderNama.setTextColor(Color.WHITE);
        viewHeaderPekerjaan.setTextColor(Color.WHITE);
        viewHeaderTelp.setTextColor(Color.WHITE);
        viewHeaderAlamat.setTextColor(Color.WHITE);
        viewHeaderAction.setTextColor(Color.WHITE);

        viewHeaderIdKreditor.setPadding(5, 5, 5, 5);
        viewHeaderNama.setPadding(5, 5, 5, 5);
        viewHeaderPekerjaan.setPadding(5, 5, 5, 5);
        viewHeaderTelp.setPadding(5, 5, 5, 5);
        viewHeaderAlamat.setPadding(5, 5, 5, 5);
        viewHeaderAction.setPadding(5, 5, 5, 5);

        barisTabel.addView(viewHeaderIdKreditor);
        barisTabel.addView(viewHeaderNama);
        barisTabel.addView(viewHeaderPekerjaan);
        barisTabel.addView(viewHeaderTelp);
        barisTabel.addView(viewHeaderAlamat);
        barisTabel.addView(viewHeaderAction);

        tbKreditor.addView(barisTabel, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        try {
            String hasilServer = kreditor.tampilKreditorbyIdNama();
            
            if (hasilServer != null && hasilServer.startsWith("[")) {
                arrayKreditor = new JSONArray(hasilServer);
                for (int i = 0; i < arrayKreditor.length(); i++) {
                    JSONObject jsonChildNode = arrayKreditor.getJSONObject(i);
                    
                    String idkreditor = jsonChildNode.optString("idkreditor");
                    String nama = jsonChildNode.optString("nama");
                    String pekerjaan = jsonChildNode.optString("pekerjaan");
                    String telp = jsonChildNode.optString("telp");
                    String alamat = jsonChildNode.optString("alamat");

                    barisTabel = new TableRow(this);
                    if (i % 2 == 0) { barisTabel.setBackgroundColor(Color.LTGRAY); }
                    
                    TextView viewIdKreditor = new TextView(this);
                    viewIdKreditor.setText(idkreditor);
                    viewIdKreditor.setPadding(5, 5, 5, 5);
                    barisTabel.addView(viewIdKreditor);

                    TextView viewNama = new TextView(this);
                    viewNama.setText(nama);
                    viewNama.setPadding(5, 5, 5, 5);
                    barisTabel.addView(viewNama);
                    
                    TextView viewPekerjaan = new TextView(this);
                    viewPekerjaan.setText(pekerjaan); 
                    viewPekerjaan.setPadding(5, 5, 5, 5);
                    barisTabel.addView(viewPekerjaan);
                    
                    TextView viewTelp = new TextView(this);
                    viewTelp.setText(telp);
                    viewTelp.setPadding(5, 5, 5, 5);
                    barisTabel.addView(viewTelp);
                    
                    TextView viewAlamat = new TextView(this);
                    viewAlamat.setText(alamat);
                    viewAlamat.setPadding(5, 5, 5, 5);
                    barisTabel.addView(viewAlamat);

                    Button btnEdit = new Button(this);
                    btnEdit.setId(i + 100); 
                    btnEdit.setTag("Edit_" + idkreditor);
                    btnEdit.setText("Edit");
                    btnEdit.setOnClickListener(this);
                    buttonEdit.add(btnEdit);
                    barisTabel.addView(btnEdit);

                    Button btnDelete = new Button(this);
                    btnDelete.setId(i + 200);
                    btnDelete.setTag("Delete_" + idkreditor);
                    btnDelete.setText("Delete");
                    btnDelete.setOnClickListener(this);
                    buttonDelete.add(btnDelete);
                    barisTabel.addView(btnDelete);
                    
                    tbKreditor.addView(barisTabel, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Gagal parsing JSON", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteKreditorByIdNama(int id) {
        kreditor.deleteKreditor(id);
        finish();
        startActivity(getIntent());
    }

    public void getKreditorById(int idkreditor) {
        String idkreditorEdit = null;
        String namaEdit = null;
        String pekerjaanEdit = null;
        String telpEdit = null;
        String alamatEdit = null;

        JSONArray arrayPersonal;
        try {
            arrayPersonal = new JSONArray(kreditor.getKreditorById(idkreditor));
            for (int i = 0; i < arrayPersonal.length(); i++) {
                JSONObject jsonChildNode = arrayPersonal.getJSONObject(i);
                idkreditorEdit = jsonChildNode.optString("idkreditor");
                namaEdit = jsonChildNode.optString("nama");
                pekerjaanEdit = jsonChildNode.optString("pekerjaan");
                telpEdit = jsonChildNode.optString("telp");
                alamatEdit = jsonChildNode.optString("alamat");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String finalIdKreditorEdit = idkreditorEdit; 

        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);

        final TextView viewIdKreditor = new TextView(this);
        viewIdKreditor.setText("ID Kreditor = " + String.valueOf(idkreditorEdit));
        viewIdKreditor.setTextColor(Color.BLACK);
        viewIdKreditor.setTextSize(18);
        layoutInput.addView(viewIdKreditor);

        final EditText editNama = new EditText(this);
        editNama.setText(namaEdit);
        layoutInput.addView(editNama);

        final EditText editPekerjaan = new EditText(this);
        editPekerjaan.setText(pekerjaanEdit);
        layoutInput.addView(editPekerjaan);
        
        final EditText editTelp = new EditText(this);
        editTelp.setText(telpEdit);
        layoutInput.addView(editTelp);
        
        final EditText editAlamat = new EditText(this);
        editAlamat.setText(alamatEdit);
        layoutInput.addView(editAlamat);

        AlertDialog.Builder builderEditKreditor = new AlertDialog.Builder(this);
        builderEditKreditor.setTitle("Update Kreditor");
        builderEditKreditor.setView(layoutInput);
        builderEditKreditor.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String idkreditor = finalIdKreditorEdit; 
                String nama = editNama.getText().toString();
                String pekerjaan = editPekerjaan.getText().toString();
                String telp = editTelp.getText().toString();
                String alamat = editAlamat.getText().toString();

                String laporan = kreditor.updateKreditor(idkreditor, nama, pekerjaan, telp, alamat);
                Toast.makeText(DataKreditorActivity.this, laporan, Toast.LENGTH_SHORT).show();

                finish();
                startActivity(getIntent());
            }
        });

        builderEditKreditor.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderEditKreditor.show();
    }

    public void tambahKreditor() {
        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);
        
        final EditText editIdKreditor = new EditText(this);
        editIdKreditor.setHint("ID Kreditor (Input Angka Unik)");
        layoutInput.addView(editIdKreditor);

        final EditText editNama = new EditText(this);
        editNama.setHint("Nama");
        layoutInput.addView(editNama);

        final EditText editPekerjaan = new EditText(this);
        editPekerjaan.setHint("Pekerjaan");
        layoutInput.addView(editPekerjaan);
        
        final EditText editTelp = new EditText(this);
        editTelp.setHint("Telp");
        layoutInput.addView(editTelp);
        
        final EditText editAlamat = new EditText(this);
        editAlamat.setHint("Alamat");
        layoutInput.addView(editAlamat);
        
        AlertDialog.Builder builderInsertKreditor = new AlertDialog.Builder(this);
        builderInsertKreditor.setTitle("Insert Kreditor");
        builderInsertKreditor.setView(layoutInput);
        builderInsertKreditor.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String idkreditor = editIdKreditor.getText().toString();
                String nama = editNama.getText().toString();
                String pekerjaan = editPekerjaan.getText().toString();
                String telp = editTelp.getText().toString();
                String alamat = editAlamat.getText().toString();

                String laporan = kreditor.insertKreditor(idkreditor, nama, pekerjaan, telp, alamat);
                Toast.makeText(DataKreditorActivity.this, laporan, Toast.LENGTH_SHORT).show();
                
                finish();
                startActivity(getIntent());
            }
        });
        
        builderInsertKreditor.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderInsertKreditor.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.data_kreditor, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        for (int i = 0; i < buttonEdit.size(); i++) {
            if (view.getId() == buttonEdit.get(i).getId()) {
                String tag = buttonEdit.get(i).getTag().toString(); 
                String idStr = tag.split("_")[1];
                getKreditorById(Integer.parseInt(idStr));
                return;
            }
        }
        
        for (int i = 0; i < buttonDelete.size(); i++) {
            if (view.getId() == buttonDelete.get(i).getId()) {
                String tag = buttonDelete.get(i).getTag().toString(); 
                String idStr = tag.split("_")[1];
                deleteKreditorByIdNama(Integer.parseInt(idStr));
                return;
            }
        }
    }
}