package com.example.agecalcu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button btn_fn,btn_fh,btn_cal;
    TextView lbl_res;
    DatePickerDialog.OnDateSetListener dpd_fnacimiento, dpd_fhoy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_fn = findViewById(R.id.btn_fn);
        btn_fh = findViewById(R.id.btn_fh);
        btn_cal = findViewById(R.id.btn_cal);
        lbl_res = findViewById(R.id.lbl_res);

        Calendar calendar = Calendar.getInstance();
        int anio = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "dd/MM/yyyy");
        String fecho_hoy = simpleDateFormat.format(Calendar.getInstance().getTime());
        btn_fh.setText(fecho_hoy);

        btn_fn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this
                        ,android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,dpd_fnacimiento,anio,mes,dia
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        dpd_fnacimiento = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                btn_fn.setText(date);
            }
        };

        btn_fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this
                        ,android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,dpd_fhoy,anio,mes,dia
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        dpd_fhoy = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                btn_fh.setText(date);
            }
        };

        btn_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aux_fna = btn_fn.getText().toString();
                String aux_fho = btn_fh.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                try {
                    Date fpa = sdf.parse(aux_fna);
                    Date fac = sdf.parse(aux_fho);

                    long startDate = fpa.getTime();
                    long endDate = fac.getTime();

                    if(startDate <= endDate) {
                        Period period = new Period(startDate,endDate, PeriodType.yearMonthDay());
                        int anio = period.getYears();
                        int mes = period.getMonths();
                        int dia = period.getDays();

                        lbl_res.setText(anio + " Años -  " + mes + " Meses - " + dia + " Dias");
                    }else{
                        Toast.makeText(getApplicationContext()
                                ,"La Fecha de nacimiento debe ser mayor a la fecha de hoy"
                                ,Toast.LENGTH_SHORT).show();
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
        });

    }
}