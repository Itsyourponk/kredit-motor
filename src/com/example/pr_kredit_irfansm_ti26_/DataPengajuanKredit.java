package com.example.pr_kredit_irfansm_ti26_;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;

@SuppressLint("NewApi")
public class DataPengajuanKredit extends Activity implements OnClickListener {

    Kredit kredit = new Kredit();
    TableLayout tbQueryKredit;
    Button btRefreshKredit;

    ArrayList<Button> buttonPdf = new ArrayList<Button>();
    ArrayList<Button> buttonDelete = new ArrayList<Button>();
    JSONArray arrayQueryKredit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pengajuan_kredit);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        tbQueryKredit = (TableLayout) findViewById(R.id.tbQueryKredit);
        btRefreshKredit = (Button) findViewById(R.id.btRefreshKredit);

        tampilQueryKredit();
    }

    // Procedure menampilkan data query kredit ke tabel
    public void tampilQueryKredit() {
        tbQueryKredit.removeAllViews();
        buttonPdf.clear();
        buttonDelete.clear();

        TableRow barisTabel = new TableRow(this);
        barisTabel.setBackgroundColor(Color.BLACK);

        // Memberi ID Header Tabel
        TextView viewHeaderInvoice = new TextView(this);
        TextView viewHeaderTgl = new TextView(this);
        TextView viewHeaderIdKreditor = new TextView(this);
        TextView viewHeaderNama = new TextView(this);
        TextView viewHeaderAlamat = new TextView(this);
        TextView viewHeaderKdMotor = new TextView(this);
        TextView viewHeaderNmMotor = new TextView(this);
        TextView viewHeaderHrgTunai = new TextView(this);
        TextView viewHeaderDP = new TextView(this);
        TextView viewHeaderHrgKredit = new TextView(this);
        TextView viewHeaderBunga = new TextView(this);
        TextView viewHeaderLama = new TextView(this);
        TextView viewHeaderTotKredit = new TextView(this);
        TextView viewHeaderAngsuran = new TextView(this);

        // Memberi Nama kolom HEADER
        viewHeaderInvoice.setText("Invoice");
        viewHeaderTgl.setText("Tgl");
        viewHeaderIdKreditor.setText("IdKreditor");
        viewHeaderNama.setText("Nama");
        viewHeaderAlamat.setText("Alamat");
        viewHeaderKdMotor.setText("KdMotor");
        viewHeaderNmMotor.setText("NmMotor");
        viewHeaderHrgTunai.setText("HrgTunai");
        viewHeaderDP.setText("DP");
        viewHeaderHrgKredit.setText("HrgKredit");
        viewHeaderBunga.setText("Bunga");
        viewHeaderLama.setText("Lama");
        viewHeaderTotKredit.setText("TotKredit");
        viewHeaderAngsuran.setText("Angsuran");

        // Warna teks Header
        viewHeaderInvoice.setTextColor(Color.WHITE);
        viewHeaderTgl.setTextColor(Color.WHITE);
        viewHeaderIdKreditor.setTextColor(Color.WHITE);
        viewHeaderNama.setTextColor(Color.WHITE);
        viewHeaderAlamat.setTextColor(Color.WHITE);
        viewHeaderKdMotor.setTextColor(Color.WHITE);
        viewHeaderNmMotor.setTextColor(Color.WHITE);
        viewHeaderHrgTunai.setTextColor(Color.WHITE);
        viewHeaderDP.setTextColor(Color.WHITE);
        viewHeaderHrgKredit.setTextColor(Color.WHITE);
        viewHeaderBunga.setTextColor(Color.WHITE);
        viewHeaderLama.setTextColor(Color.WHITE);
        viewHeaderTotKredit.setTextColor(Color.WHITE);
        viewHeaderAngsuran.setTextColor(Color.WHITE);

        // Padding Header
        viewHeaderInvoice.setPadding(5, 1, 5, 1);
        viewHeaderTgl.setPadding(5, 1, 5, 1);
        viewHeaderIdKreditor.setPadding(5, 1, 5, 1);
        viewHeaderNama.setPadding(5, 1, 5, 1);
        viewHeaderAlamat.setPadding(5, 1, 5, 1);
        viewHeaderKdMotor.setPadding(5, 1, 5, 1);
        viewHeaderNmMotor.setPadding(5, 1, 5, 1);
        viewHeaderHrgTunai.setPadding(5, 1, 5, 1);
        viewHeaderDP.setPadding(5, 1, 5, 1);
        viewHeaderHrgKredit.setPadding(5, 1, 5, 1);
        viewHeaderBunga.setPadding(5, 1, 5, 1);
        viewHeaderLama.setPadding(5, 1, 5, 1);
        viewHeaderTotKredit.setPadding(5, 1, 5, 1);
        viewHeaderAngsuran.setPadding(5, 1, 5, 1);

        // Tambah view header ke baris tabel
        barisTabel.addView(viewHeaderInvoice);
        barisTabel.addView(viewHeaderTgl);
        barisTabel.addView(viewHeaderIdKreditor);
        barisTabel.addView(viewHeaderNama);
        barisTabel.addView(viewHeaderAlamat);
        barisTabel.addView(viewHeaderKdMotor);
        barisTabel.addView(viewHeaderNmMotor);
        barisTabel.addView(viewHeaderHrgTunai);
        barisTabel.addView(viewHeaderDP);
        barisTabel.addView(viewHeaderHrgKredit);
        barisTabel.addView(viewHeaderBunga);
        barisTabel.addView(viewHeaderLama);
        barisTabel.addView(viewHeaderTotKredit);
        barisTabel.addView(viewHeaderAngsuran);

        tbQueryKredit.addView(barisTabel, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        try {
            arrayQueryKredit = new JSONArray(kredit.tampil_query_kredit());

            for (int i = 0; i < arrayQueryKredit.length(); i++) {
                JSONObject jsonChildNode = arrayQueryKredit.getJSONObject(i);
                
                String invoice = jsonChildNode.optString("invoice");
                String tanggal = jsonChildNode.optString("tanggal");
                String idkreditor = jsonChildNode.optString("idkreditor");
                String nama = jsonChildNode.optString("nama");
                String alamat = jsonChildNode.optString("alamat");
                String kdmotor = jsonChildNode.optString("kdmotor");
                String nmotor = jsonChildNode.optString("nmotor");
                String hrgtunai = jsonChildNode.optString("hrgtunai");
                String dp = jsonChildNode.optString("dp");
                String hrgkredit = jsonChildNode.optString("hrgkredit");
                String bunga = jsonChildNode.optString("bunga");
                String lama = jsonChildNode.optString("lama");
                String totalkredit = jsonChildNode.optString("totalkredit");
                String angsuran = jsonChildNode.optString("angsuran");

                barisTabel = new TableRow(this);
                if (i % 2 == 0) {
                    barisTabel.setBackgroundColor(Color.LTGRAY);
                }

                TextView viewInvoice = new TextView(this);
                viewInvoice.setText(invoice);
                viewInvoice.setPadding(5, 1, 5, 1);
                viewInvoice.setGravity(Gravity.CENTER);
                barisTabel.addView(viewInvoice);

                TextView viewTgl = new TextView(this);
                viewTgl.setText(tanggal);
                viewTgl.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewTgl);

                TextView viewIdKreditor = new TextView(this);
                viewIdKreditor.setText(idkreditor);
                viewIdKreditor.setPadding(5, 1, 5, 1);
                viewIdKreditor.setGravity(Gravity.CENTER);
                barisTabel.addView(viewIdKreditor);

                TextView viewNama = new TextView(this);
                viewNama.setText(nama);
                viewNama.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewNama);

                TextView viewAlamat = new TextView(this);
                viewAlamat.setText(alamat);
                viewAlamat.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewAlamat);

                TextView viewKdMotor = new TextView(this);
                viewKdMotor.setText(kdmotor);
                viewKdMotor.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewKdMotor);

                TextView viewNmMotor = new TextView(this);
                viewNmMotor.setText(nmotor);
                viewNmMotor.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewNmMotor);

                TextView viewHrgTunai = new TextView(this);
                viewHrgTunai.setText(hrgtunai);
                viewHrgTunai.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewHrgTunai);

                TextView viewDP = new TextView(this);
                viewDP.setText(dp);
                viewDP.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewDP);

                TextView viewHrgKredit = new TextView(this);
                viewHrgKredit.setText(hrgkredit);
                viewHrgKredit.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewHrgKredit);

                TextView viewBunga = new TextView(this);
                viewBunga.setText(bunga);
                viewBunga.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewBunga);

                TextView viewLama = new TextView(this);
                viewLama.setText(lama);
                viewLama.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewLama);

                TextView viewTotKredit = new TextView(this);
                viewTotKredit.setText(totalkredit);
                viewTotKredit.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewTotKredit);

                TextView viewAngsuran = new TextView(this);
                viewAngsuran.setText(angsuran);
                viewAngsuran.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewAngsuran);

                // Membuat Button PDF pada Baris
                Button btnPdf = new Button(this);
                btnPdf.setId(Integer.parseInt(invoice));
                btnPdf.setTag("PDF");
                btnPdf.setText("PDF");
                btnPdf.setOnClickListener(this);
                buttonPdf.add(btnPdf);
                barisTabel.addView(btnPdf);

                // Membuat Button Delete pada Baris
                Button btnDelete = new Button(this);
                btnDelete.setId(Integer.parseInt(invoice));
                btnDelete.setTag("Delete");
                btnDelete.setText("Delete");
                btnDelete.setOnClickListener(this);
                buttonDelete.add(btnDelete);
                barisTabel.addView(btnDelete);

                tbQueryKredit.addView(barisTabel, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void deleteKredit(int invoice) {
        kredit.hapus_kredit(invoice);
        finish();
        startActivity(getIntent());
    }

    public void KlikbtRefreshKredit(View v) {
        finish();
        startActivity(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.data_pengajuan_kredit, menu);
        return true;
    }


    @Override
    public void onClick(View view) {
        for (int i = 0; i < buttonPdf.size(); i++) {
            // Jika yang diklik adalah button PDF
            if (view.getId() == buttonPdf.get(i).getId() && view.getTag().toString().trim().equals("PDF")) {
                int invoice = buttonPdf.get(i).getId();
                
                // Memanggil file PHP di XAMPP dengan membawa parameter invoice
                Server server = new Server();
                String urlPdf = "http://" + server.urlDatabase1() + "/jskreditmotor/cetak_pdf.php?invoice=" + invoice;
                
                Intent intent = new Intent(Intent.ACTION_VIEW, android.net.Uri.parse(urlPdf));
                startActivity(intent);
                return;
            } 
            else if (view.getId() == buttonDelete.get(i).getId() && view.getTag().toString().trim().equals("Delete")) {
                int invoice = buttonDelete.get(i).getId();
                deleteKredit(invoice);
                return;
            }
        }
    }
    
    }
