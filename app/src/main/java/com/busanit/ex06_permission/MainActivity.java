package com.busanit.ex06_permission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        checkPermissions(permissions);
    }

    private void checkPermissions(String[] permissions) {
        ArrayList<String> targetList = new ArrayList<String>();
        for (int i=0;i<permissions.length;i++){
            String curPermission = permissions[i];
            int permissionCheck = ContextCompat.checkSelfPermission(this,curPermission);
            if(permissionCheck== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,curPermission+"권한 있음", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,curPermission+"권한 없음",Toast.LENGTH_SHORT).show();
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, curPermission)){
                    //사용자가 거부한 경우
                    Toast.makeText(this,curPermission+"권한 설명 필요함",Toast.LENGTH_SHORT).show();
                } else {
                    targetList.add(curPermission);
                }
            }
        }
        if(targetList!=null && targetList.size()!=0){
            String[] targets = new String[targetList.size()];
            targetList.toArray(targets);
            ActivityCompat.requestPermissions(this, targets,101);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 101:
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"첫번째 권한을 승인함",Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(this, "첫번째 권한 거부됨",Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }
}