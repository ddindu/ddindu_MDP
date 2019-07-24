package com.example.notipay_1;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class BarScan extends AppCompatActivity {

    String getData;
    MyAdapter mMyAdapter; //어댑터 변수
    IntentIntegrator brscan;

    Server_chat server_chat;
    String receive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_scan);

        server_chat = new Server_chat();
        server_chat.connser();

        mMyAdapter = new MyAdapter();// 어댑터 등록
        brscan = new IntentIntegrator(this);

        brscan.setPrompt("scanning");
        brscan.initiateScan(); //initiateScan() 함수 호출 (바코드스캐너)

    }
    //바코드 인식
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { //이 함수를 호출하면 requestcode, resultcode, data를 반환
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //qr,barcode 가 없으면
            if (result.getContents() == null) {
                Toast.makeText(BarScan.this, "취소", Toast.LENGTH_SHORT).show(); //toast 메세지
            } else {
                try {
                    JSONObject obj = new JSONObject(result.getContents());
                } catch (JSONException e) {
                    e.printStackTrace();
                    getData = result.getContents(); //바코드 값 저장
                    String bar_code = String.valueOf(getData); //스트링 형으로 변환
                    server_chat.senddata(bar_code);
                    receive = String.valueOf(Server_chat.receive);
                    Intent intent = new Intent(BarScan.this, ShoppingList.class);
                    intent.putExtra("barcode",receive);
                    startActivity(intent);
                    finish();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data); //onActivityResult 에서 requestCode,resultCode,data를 받아옴
        }
    }
}
