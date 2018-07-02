package com.galangaji.fuzzylogicsrinklercontrolsystem;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText inputSuhu, inputKelembapan;
    private TextInputLayout inputLayoutSuhu, inputLayoutKelembapan;
    private Button btnGet;
    private TextView hasil;
    double suhu,kelembapan,durasi;
    //variabel derajat anggota suhu
    double derAnggotaCold,derAnggotaCool,derAnggotaNormal,derAnggotaWarm,derAnggotaHot;

    //variabel derajat anggota kelembapan tanah
    double derAnggotaDry,derAnggotaMoist,derAnggotaWet;

    //varabel derajat anggota kelembapan tanah
    double derAnggotaPendek = 0,derAnggotaSedang = 0,derAnggotaPanjang = 0;

    //variabel derajat anggota Min kelembapan tanah
    double derAnggotaMinPendek,derAnggotaMinSedang,derAnggotaMinPanjang;

    //variabel menampung nilai-nilai linguistik kelembapan tanah
    List<Double> listderAnggotaMinPendek = new ArrayList<Double>();
    List<Double> listderAnggotaMinSedang = new ArrayList<Double>();
    List<Double> listderAnggotaMinPanjang = new ArrayList<Double>();

    //Membuat grafik suhu
    SetGrafikTrapesium cold=new SetGrafikTrapesium(-10,-10,0,3);
    SetGrafikTrapesium cool=new SetGrafikTrapesium(0,2,12,15);
    SetGrafikTrapesium normal=new SetGrafikTrapesium(12,15,24,27);
    SetGrafikTrapesium warm=new SetGrafikTrapesium(24,27,36,39);
    SetGrafikTrapesium hot=new SetGrafikTrapesium(36,39,50,50);

    //Membuat grafik kelembapan tanah
    SetGrafikTrapesium dry=new SetGrafikTrapesium(0,0,10,20);
    SetGrafikTrapesium moist=new SetGrafikTrapesium(10,20,40,50);
    SetGrafikTrapesium wet=new SetGrafikTrapesium(40,50,70,70);

    //Membuat grafik durasi penyiraman
    SetGrafikTrapesium pendek=new SetGrafikTrapesium(0,0,20,28);
    SetGrafikTrapesium sedang=new SetGrafikTrapesium(20,28,40,48);
    SetGrafikTrapesium panjang=new SetGrafikTrapesium(40,48,90,90);



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputSuhu = (EditText) findViewById(R.id.input_suhu);
        inputKelembapan = (EditText) findViewById(R.id.input_kelempan);
        btnGet = (Button) findViewById(R.id.btn_get);
        hasil=(TextView)findViewById(R.id.txt_hasil);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                suhu=Double.parseDouble(inputSuhu.getText().toString());
                kelembapan=Double.parseDouble(inputKelembapan.getText().toString());

                //proses fuzzyfikasi
                derAnggotaCold=cold.Membership(suhu);
                derAnggotaCool=cool.Membership(suhu);
                derAnggotaNormal=normal.Membership(suhu);
                derAnggotaWarm=warm.Membership(suhu);
                derAnggotaHot=hot.Membership(suhu);
                derAnggotaDry=dry.Membership(kelembapan);
                derAnggotaMoist=moist.Membership(kelembapan);
                derAnggotaWet=wet.Membership(kelembapan);

                //proses infrensi
                //penetapan rules fuzzy
                if((derAnggotaCold>0&&derAnggotaCold<=1)&&(derAnggotaDry>0&&derAnggotaDry<=1))
                {
                    derAnggotaMinPanjang=Math.min(derAnggotaCold,derAnggotaDry);
                    listderAnggotaMinPanjang.add(derAnggotaMinPanjang);
                }
                if((derAnggotaCold>0&&derAnggotaCold<=1)&&(derAnggotaMoist>0&&derAnggotaMoist<=1))
                {
                    derAnggotaMinPanjang=Math.min(derAnggotaCold,derAnggotaMoist);
                    listderAnggotaMinPanjang.add(derAnggotaMinPanjang);
                }
                if((derAnggotaCold>0&&derAnggotaCold<=1)&&(derAnggotaWet>0&&derAnggotaWet<=1))
                {
                    derAnggotaMinPendek=Math.min(derAnggotaCold,derAnggotaWet);
                    listderAnggotaMinPendek.add(derAnggotaMinPendek);
                }
                if((derAnggotaCool>0&&derAnggotaCool<=1)&&(derAnggotaDry>0&&derAnggotaDry<=1))
                {
                    derAnggotaMinPanjang=Math.min(derAnggotaCool,derAnggotaDry);
                    listderAnggotaMinPanjang.add(derAnggotaMinPanjang);
                }
                if((derAnggotaCool>0&&derAnggotaCool<=1)&&(derAnggotaMoist>0&&derAnggotaMoist<=1))
                {
                    derAnggotaMinSedang=Math.min(derAnggotaCool,derAnggotaMoist);
                    listderAnggotaMinSedang.add(derAnggotaMinSedang);
                }
                if((derAnggotaCool>0&&derAnggotaCool<=1)&&(derAnggotaWet>0&&derAnggotaWet<=1))
                {
                    derAnggotaMinPendek=Math.min(derAnggotaCool,derAnggotaWet);
                    listderAnggotaMinPendek.add(derAnggotaMinPendek);
                }
                if((derAnggotaNormal>0&&derAnggotaNormal<=1)&&(derAnggotaDry>0&&derAnggotaDry<=1))
                {
                    derAnggotaMinPanjang=Math.min(derAnggotaNormal,derAnggotaDry);
                    listderAnggotaMinPanjang.add(derAnggotaMinPanjang);
                }
                if((derAnggotaNormal>0&&derAnggotaNormal<=1)&&(derAnggotaMoist>0&&derAnggotaMoist<=1))
                {
                    derAnggotaMinSedang=Math.min(derAnggotaNormal,derAnggotaMoist);
                    listderAnggotaMinSedang.add(derAnggotaMinSedang);
                }
                if((derAnggotaNormal>0&&derAnggotaNormal<=1)&&(derAnggotaWet>0&&derAnggotaWet<=1))
                {
                    derAnggotaMinPendek=Math.min(derAnggotaNormal,derAnggotaWet);
                    listderAnggotaMinPendek.add(derAnggotaMinPendek);
                }
                if((derAnggotaWarm>0&&derAnggotaWarm<=1)&&(derAnggotaDry>0&&derAnggotaDry<=1))
                {
                    derAnggotaMinPanjang=Math.min(derAnggotaWarm,derAnggotaDry);
                    listderAnggotaMinPanjang.add(derAnggotaMinPanjang);
                }
                if((derAnggotaWarm>0&&derAnggotaWarm<=1)&&(derAnggotaMoist>0&&derAnggotaMoist<=1))
                {
                    derAnggotaMinSedang=Math.min(derAnggotaWarm,derAnggotaMoist);
                    listderAnggotaMinSedang.add(derAnggotaMinSedang);
                }
                if((derAnggotaWarm>0&&derAnggotaWarm<=1)&&(derAnggotaWet>0&&derAnggotaWet<=1))
                {
                    derAnggotaMinPendek=Math.min(derAnggotaWarm,derAnggotaWet);
                    listderAnggotaMinPendek.add(derAnggotaMinPendek);
                }
                if((derAnggotaHot>0&&derAnggotaHot<=1)&&(derAnggotaDry>0&&derAnggotaDry<=1))
                {
                    derAnggotaMinPanjang=Math.min(derAnggotaHot,derAnggotaDry);
                    listderAnggotaMinPanjang.add(derAnggotaMinPanjang);
                }
                if((derAnggotaHot>0&&derAnggotaHot<=1)&&(derAnggotaMoist>0&&derAnggotaMoist<=1))
                {   derAnggotaMinSedang=Math.min(derAnggotaHot,derAnggotaMoist);
                    listderAnggotaMinSedang.add(derAnggotaMinSedang);
                }
                if((derAnggotaHot>0&&derAnggotaHot<=1)&&(derAnggotaWet>0&&derAnggotaWet<=1))
                {   derAnggotaMinPendek=Math.min(derAnggotaHot,derAnggotaWet);
                    listderAnggotaMinPendek.add(derAnggotaMinPendek);
                }

                //derajat keanggotaan maksimum dari nilai-nilai linguistik
                for(int i=0;i<listderAnggotaMinPanjang.size();i++)
                {
                    if(listderAnggotaMinPanjang.get(i)>derAnggotaPanjang)
                    {
                        derAnggotaPanjang=listderAnggotaMinPanjang.get(i);
                    }
                }
                for(int i=0;i<listderAnggotaMinSedang.size();i++)
                {
                    if(listderAnggotaMinSedang.get(i)>derAnggotaSedang)
                    {
                        derAnggotaSedang=listderAnggotaMinSedang.get(i);
                    }
                }
                for(int i=0;i<listderAnggotaMinPendek.size();i++)
                {
                    if(listderAnggotaMinPendek.get(i)>derAnggotaPendek)
                    {
                        derAnggotaPendek=listderAnggotaMinPendek.get(i);
                    }
                }

                //proses defuzzyfikasi
                double randomNumPanjang = 0,randomNumPendek = 0,randomNumSedang = 0;
                double numPanjang,numPendek,numSedang;
                for(int i=0;i<5;i++){
                    randomNumPendek+= ThreadLocalRandom.current().nextInt(1, 20 + 1);
                }
                for(int i=0;i<5;i++){
                    randomNumSedang+=ThreadLocalRandom.current().nextInt(30, 40 + 1);
                }
                for(int i=0;i<5;i++){
                    randomNumPanjang+=ThreadLocalRandom.current().nextInt(50, 90 + 1);
                }
                numPanjang=randomNumPanjang*derAnggotaPanjang;
                numPendek=randomNumPendek*derAnggotaPendek;
                numSedang=randomNumSedang*derAnggotaSedang;
                double durasi=((numPanjang)+(numPendek)+(numSedang))/((derAnggotaPanjang*5)+(derAnggotaPendek*5)+(derAnggotaSedang*5));

                hasil.setText("Derajat Anggota Cold: "+derAnggotaCold+"\nDerajat Anggota Cool: "+derAnggotaCool+"\nDerajat Anggota Normal: "+derAnggotaNormal+"\nDerajat Anggota Warm: "+derAnggotaWarm+"\nDerajat Anggota Hot: "+derAnggotaHot+"\nDerajat Anggota Dry: "+derAnggotaDry+"\nDerajat Anggota Moist: "+derAnggotaMoist+"\nDerajat Anggota Wet: "+derAnggotaWet+"\nDerajat Anggota Durasi Panjang: "+derAnggotaPanjang+"\nDerajat Anggota Durasi Sedang: "+derAnggotaSedang+"\nDerajat Anggota Durasi Pendek :"+derAnggotaPendek+"\nHasil Durasi: "+durasi);
            }
        });


    }



}
