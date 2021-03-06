package com.cdtc.hospital.view.hosregister;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.cdtc.hospital.R;
import com.cdtc.hospital.base.BaseActivity;
import com.cdtc.hospital.entity.HosRegister;
import com.cdtc.hospital.service.HosRegisterTask;

import java.util.List;

public class HosRegisterDetailsActivity extends BaseActivity {

    private TextView hosR_name;
    private TextView hosR_idCard;
    private TextView hosR_medical;
    private TextView hosR_regPrice;
    private TextView hosR_phone;
    private TextView hosR_selfPrice;
    private TextView hosR_sex;
    private TextView hosR_age;
    private TextView hosR_work;
    private TextView hosR_lookDoctor;
    private TextView d_keshi;
    private TextView d_name;
    private TextView hosR_remark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hos_register_details);

        bindViewId();
        initData();

        setActionBarTitle("详情");
        showLeftButton();
    }

    @Override
    protected void bindViewId() {
        hosR_name = findViewById(R.id.hos_r_name);
        hosR_idCard = findViewById(R.id.hos_r_id_card);
        hosR_medical = findViewById(R.id.hos_r_medical);
        hosR_regPrice = findViewById(R.id.hos_r_reg_price);
        hosR_phone = findViewById(R.id.hos_r_phone);
        hosR_selfPrice = findViewById(R.id.hos_r_self_price);
        hosR_sex = findViewById(R.id.hos_r_sex);
        hosR_age = findViewById(R.id.hos_r_age);
        hosR_work = findViewById(R.id.hos_r_work);
        hosR_lookDoctor = findViewById(R.id.hos_r_look_doctor);
        d_keshi = findViewById(R.id.d_keshi);
        d_name = findViewById(R.id.d_name);
        hosR_remark = findViewById(R.id.hos_r_remark);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        Integer hosR_id = intent.getIntExtra("hosR_id", 1001);

        HosRegisterTask hosRegisterTask = new HosRegisterTask(activity, hosR_id);
        hosRegisterTask.execute();
        hosRegisterTask.setOnSuccessListener(this::onSuccess);


    }

    @SuppressLint("SetTextI18n")
    private void onSuccess(List<HosRegister> list) {
        HosRegister hosRegister = list.get(0);
        hosR_name.setText(hosRegister.getHosR_name());
        hosR_idCard.setText(hosRegister.getHosR_idCard());
        hosR_medical.setText(hosRegister.getHosR_medical());
        hosR_regPrice.setText(String.valueOf(hosRegister.getHosR_regPrice()) + "元");
        hosR_phone.setText(hosRegister.getHosR_phone());
        hosR_selfPrice.setText(hosRegister.getHosR_selfPrice() == 0 ? "自费" : "免费");
        hosR_sex.setText(hosRegister.getHosR_sex() == 0 ? "男" : "女");
        hosR_age.setText(String.valueOf(hosRegister.getHosR_age()));
        hosR_work.setText(hosRegister.getHosR_work());
        hosR_lookDoctor.setText(hosRegister.getHosR_lookDoctor() == 0 ? "初诊" : "复诊");
        d_keshi.setText(hosRegister.getD_keshi());
        d_name.setText(hosRegister.getD_name());
        hosR_remark.setText(hosRegister.getHosR_remark());
    }
}
