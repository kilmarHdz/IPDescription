package com.debugprojects.ipdeescription;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Math.pow;
import static java.lang.Math.round;

public class MainActivity extends AppCompatActivity {

    private EditText IP_Parte1;
    private EditText IP_Parte2;
    private EditText IP_Parte3;
    private EditText IP_Parte4;
    private EditText Mask;

    private TextView Id_Red;
    private TextView Broadcast;
    private TextView Cant_Hosts;
    private TextView Cant_Redes;
    private TextView Clase_Red;
    private TextView Parte_Host;
    private TextView Parte_red;
    private TextView Debugg;

    private Button Calcular;

    private int IP_Parte1_int;
    private int IP_Parte2_int;
    private int IP_Parte3_int;
    private int IP_Parte4_int;
    private int Submask;

    private String Id_Red_Var="";
    private String Broadcast_Var="";
    private String Cant_Host_Var="";
    private String Clase_Red_Var="";
    private String Cant_Redes_Var="";
    private String Parte_Host_Var="";
    private String Parte_red_Var="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IP_Parte1= findViewById(R.id.edit_ip_id1);
        IP_Parte2= findViewById(R.id.edit_ip_id2);
        IP_Parte3= findViewById(R.id.edit_ip_id3);
        IP_Parte4= findViewById(R.id.edit_ip_id4);
        Mask= findViewById(R.id.edit_submask);

        Id_Red= findViewById(R.id.edit_id_red);
        Broadcast= findViewById(R.id.edit_broadcast);
        Cant_Hosts= findViewById(R.id.edit_cant_host);
        Cant_Redes= findViewById(R.id.edit_cant_redes);
        Clase_Red= findViewById(R.id.edit_clase_red);
        Parte_Host= findViewById(R.id.edit_parte_host);
        Parte_red= findViewById(R.id.edit_parte_red);
        Debugg= findViewById(R.id.text_debug);

        Calcular= findViewById(R.id.button_calc);

        if(savedInstanceState!=null){
            Id_Red_Var= savedInstanceState.getString("Id_Red");
            Broadcast_Var= savedInstanceState.getString("Broadcast");
            Cant_Host_Var= savedInstanceState.getString("Cant_Host");
            Cant_Redes_Var= savedInstanceState.getString("Cant_Redes");
            Parte_red_Var= savedInstanceState.getString("Parte_Red");
            Parte_Host_Var= savedInstanceState.getString("Parte_Host");
            Clase_Red_Var= savedInstanceState.getString("Clase_Red");

        }

        Parte_red.setText(Parte_red_Var);
        Parte_Host.setText(Parte_Host_Var);
        Clase_Red.setText(Clase_Red_Var);
        Id_Red.setText(Id_Red_Var);
        Broadcast.setText(Broadcast_Var);
        Cant_Redes.setText(Cant_Redes_Var);
        Cant_Hosts.setText(Cant_Host_Var);

        Calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    IP_Parte1_int= Integer.parseInt(IP_Parte1.getText().toString());
                    IP_Parte2_int= Integer.parseInt(IP_Parte2.getText().toString());
                    IP_Parte3_int= Integer.parseInt(IP_Parte3.getText().toString());
                    IP_Parte4_int= Integer.parseInt(IP_Parte4.getText().toString());
                    Submask= Integer.parseInt(Mask.getText().toString());

                    CalcularDatos(IP_Parte1_int,IP_Parte2_int,IP_Parte3_int,IP_Parte4_int,Submask);

                }catch (Exception e){
                    Debugg.setTextColor(Debugg.getContext().getResources().getColor(R.color.Error));
                    Debugg.setText("Error de entrada...");
                }


            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("Id_Red", Id_Red_Var);
        outState.putString("Broadcast", Broadcast_Var);
        outState.putString("Cant_Host", Cant_Host_Var);
        outState.putString("Cant_Redes", Cant_Redes_Var);
        outState.putString("Clase_Red", Clase_Red_Var);
        outState.putString("Parte_Red", Parte_red_Var);
        outState.putString("Parte_Host", Parte_Host_Var);
        super.onSaveInstanceState(outState);
    }

    private void CalcularDatos(int IP_Parte1_int, int IP_Parte2_int, int IP_Parte3_int, int IP_Parte4_int, int SubMask){

        if(IP_Parte1_int >= 0 && IP_Parte1_int <= 127 && SubMask == 8 && VerificarDatos()){ // 01111111
            Clase_Red_Var= "A";
            Parte_red_Var= IP_Parte1_int +"";
            Parte_Host_Var= IP_Parte2_int +"."+IP_Parte3_int+"."+IP_Parte4_int;
            Id_Red_Var= Parte_red_Var+".0.0.0";
            Broadcast_Var= Parte_red_Var+".255.255.255";
            Cant_Redes_Var= round(pow(2,7))+"";
            Cant_Host_Var= round(pow(2,24))+"";
        }
        else if(IP_Parte1_int >= 128 && IP_Parte1_int <= 191 && SubMask == 16 && VerificarDatos()){ // 10111111 11111111
            Clase_Red_Var= "B";
            Parte_red_Var= IP_Parte1_int +"."+IP_Parte2_int;
            Parte_Host_Var= IP_Parte3_int+"."+IP_Parte4_int;
            Id_Red_Var= Parte_red_Var+".0.0";
            Broadcast_Var= Parte_red_Var+".255.255";
            Cant_Redes_Var= round(pow(2,14))+"";
            Cant_Host_Var= round(pow(2,16))+"";
        }
        else if(IP_Parte1_int >= 192 && IP_Parte1_int <= 223 && SubMask == 24 && VerificarDatos()){ //11011111 11111111 11111111
            Clase_Red_Var= "C";
            Parte_red_Var= IP_Parte1_int +"."+IP_Parte2_int+"."+IP_Parte3_int;
            Parte_Host_Var=IP_Parte4_int+"";
            Id_Red_Var= Parte_red_Var+".0";
            Broadcast_Var= Parte_red_Var+".255";
            Cant_Redes_Var= round(pow(2,21))+"";
            Cant_Host_Var= round(pow(2,8))+"";
        }
        else if(IP_Parte1_int >= 224 && IP_Parte1_int <= 239){
            Clase_Red_Var= "D";
            Id_Red_Var="IP Restringida";
            Parte_Host_Var="";
            Parte_red_Var="";
            Cant_Redes_Var="";
            Cant_Host_Var="";
            Broadcast_Var="";
        }
        else if(IP_Parte1_int >= 240 && IP_Parte1_int <= 255){
            Clase_Red_Var= "E";
            Id_Red_Var="IP Restringida";
            Parte_Host_Var="";
            Parte_red_Var="";
            Cant_Redes_Var="";
            Cant_Host_Var="";
            Broadcast_Var="";
        }
        else{
            Debugg.setTextColor(Debugg.getContext().getResources().getColor(R.color.Error));
            Debugg.setText("FUERA DE RANGO...");
            return;
        }

        Parte_red.setText(Parte_red_Var);
        Parte_Host.setText(Parte_Host_Var);
        Clase_Red.setText(Clase_Red_Var);
        Id_Red.setText(Id_Red_Var);
        Broadcast.setText(Broadcast_Var);
        Cant_Redes.setText(Cant_Redes_Var);
        Cant_Hosts.setText(Cant_Host_Var);

        Debugg.setTextColor(Debugg.getContext().getResources().getColor(R.color.Passed));
        Debugg.setText("Calculo completo!");
    }

    private boolean VerificarDatos(){
        if(this.IP_Parte2_int < 0 || this.IP_Parte2_int >255){
            return false;
        }else if (this.IP_Parte3_int < 0 || this.IP_Parte3_int >255){
            return false;
        }else if (this.IP_Parte4_int < 0 || this.IP_Parte4_int >255){
            return false;
        }else{
            return true;
        }
    }
}