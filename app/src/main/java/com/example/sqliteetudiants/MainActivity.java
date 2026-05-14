package com.example.sqliteetudiants;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqliteetudiants.classes.Etudiant;
import com.example.sqliteetudiants.service.EtudiantService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String HC_TAG = "HC_SQLite";

    private TextInputEditText etNom, etPrenom, etId;
    private TextView tvResult;
    private EtudiantService hcService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNom = findViewById(R.id.etNom);
        etPrenom = findViewById(R.id.etPrenom);
        etId = findViewById(R.id.etId);
        tvResult = findViewById(R.id.tvResult);

        hcService = new EtudiantService(this);

        // Test initial Logcat
        hcService.create(new Etudiant("ALAMI", "ALI"));
        hcService.create(new Etudiant("RAMI", "AMAL"));
        hcService.create(new Etudiant("SAFI", "MHMED"));

        Log.d(HC_TAG, "--- Liste initiale ---");
        for (Etudiant e : hcService.findAll()) {
            Log.d(HC_TAG, e.getId() + " : " + e.getNom() + " " + e.getPrenom());
        }

        MaterialButton btnAjouter = findViewById(R.id.btnAjouter);
        MaterialButton btnChercher = findViewById(R.id.btnChercher);
        MaterialButton btnSupprimer = findViewById(R.id.btnSupprimer);

        btnAjouter.setOnClickListener(v -> hcAjouter());
        btnChercher.setOnClickListener(v -> hcChercher());
        btnSupprimer.setOnClickListener(v -> hcSupprimer());
    }

    private void hcAjouter() {
        String nom = etNom.getText().toString().trim();
        String prenom = etPrenom.getText().toString().trim();

        if (nom.isEmpty() || prenom.isEmpty()) {
            Toast.makeText(this, "⚠️ Remplir nom et prénom", Toast.LENGTH_SHORT).show();
            return;
        }

        hcService.create(new Etudiant(nom, prenom));
        etNom.setText("");
        etPrenom.setText("");
        tvResult.setText("✅ Étudiant ajouté : " + nom + " " + prenom);
        Toast.makeText(this, "✅ Étudiant ajouté !", Toast.LENGTH_SHORT).show();
    }

    private void hcChercher() {
        String txt = etId.getText().toString().trim();
        if (txt.isEmpty()) {
            Toast.makeText(this, "⚠️ Saisir un ID", Toast.LENGTH_SHORT).show();
            return;
        }

        Etudiant e = hcService.findById(Integer.parseInt(txt));
        if (e == null) {
            tvResult.setText("❌ Étudiant introuvable (id=" + txt + ")");
            Toast.makeText(this, "❌ Introuvable", Toast.LENGTH_SHORT).show();
            return;
        }

        tvResult.setText("🎓 " + e.getNom() + " " + e.getPrenom() +
                "\n🔑 ID : " + e.getId());
    }

    private void hcSupprimer() {
        String txt = etId.getText().toString().trim();
        if (txt.isEmpty()) {
            Toast.makeText(this, "⚠️ Saisir un ID", Toast.LENGTH_SHORT).show();
            return;
        }

        Etudiant e = hcService.findById(Integer.parseInt(txt));
        if (e == null) {
            Toast.makeText(this, "❌ Étudiant introuvable", Toast.LENGTH_SHORT).show();
            return;
        }

        hcService.delete(e);
        tvResult.setText("🗑️ Supprimé : " + e.getNom() + " " + e.getPrenom());
        etId.setText("");
        Toast.makeText(this, "✅ Étudiant supprimé !", Toast.LENGTH_SHORT).show();
    }
}