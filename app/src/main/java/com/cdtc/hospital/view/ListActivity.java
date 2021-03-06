package com.cdtc.hospital.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cdtc.hospital.R;
import com.cdtc.hospital.base.App;
import com.cdtc.hospital.base.BaseActivity;
import com.cdtc.hospital.local.dao.BaseLocalDao;
import com.cdtc.hospital.local.dao.UserLocalDao;
import com.cdtc.hospital.local.dao.impl.UserLocalDaoImpl;
import com.cdtc.hospital.view.behospital.BeHospitalActivity;
import com.cdtc.hospital.view.hosregister.HosRegisterActivity;

public class ListActivity extends BaseActivity {

    private TextView welcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        setCustomerActionBar(KeyActionBarButtonKind.ACTIONBAR_RIGHT, BTN_TYPE_TEXT, "退出");
        showRightButton();
        bindViewId();
        initData();
    }

    @Override
    protected void bindViewId() {
        welcomeTextView = findViewById(R.id.welcome);
    }

    @Override
    protected void initData() {
        String welcome = "Welcome " + (App.trueName != null ? App.trueName + "." : "to " + getString(R.string.app_name));
        welcomeTextView.setText(welcome);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gua_hao: {
                startActivity(HosRegisterActivity.class);
                break;
            }
            case R.id.zhu_yuan: {
                startActivity(BeHospitalActivity.class);
                break;
            }
            default: {
                toast.showShort("新功能待开发，敬请期待~");
                break;
            }
        }
    }

    @Override
    protected void rightDoWhat() {
        UserLocalDao userLocalDao = new UserLocalDaoImpl(activity, BaseLocalDao.UPDATE);
        try {
            userLocalDao.updateLogSate(App.LOG_OUT, App.loginName);
            App.loginState = App.LOG_OUT;
            App.loginName = null;
            App.trueName = null;
        } catch (Exception e) {
            userLocalDao.queryLocalLogSate();
            userLocalDao.updateLogSate(App.LOG_OUT, App.loginName);
            App.loginState = App.LOG_OUT;
            App.loginName = null;
            App.trueName = null;
        }
        finish();
        startActivity(LoginActivity.class);

    }
}
