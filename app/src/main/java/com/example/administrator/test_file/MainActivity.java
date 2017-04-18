package com.example.administrator.test_file;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.aip.face.AipFace;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    private Button btn_sel;
    private ImageView img;
    private TextView tv;

    private Gson mGson;
    private person p;

    public static final String APP_ID = "9532623";
    public static final String API_KEY = "4yObABBxEXIqdXcTKOvSGpcp";
    public static final String SECRET_KEY = "rT9jGKDYUPkaRiZA5dNIDhxCLOmgix27";

    int left = 0;
    int width = 0;
    int height = 0;
    int Top = 0;

    private Handler myhandler;
    private Myview myview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        myview = (Myview) findViewById(R.id.myview);
        initHandler();
    }

    private void initHandler() {
        myhandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Log.d("AAA", "进来了");
                Rect rect = (Rect) msg.obj;
                myview.drawRect(rect);
                return true;
            }
        });

    }

    private void initView() {
        btn_sel = (Button) findViewById(R.id.btn_sel);
/*        tv = (TextView) findViewById(R.id.tv);*/
        img = (ImageView) findViewById(R.id.head);


        btn_sel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1001);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            if (requestCode == 1001
                    && resultCode == Activity.RESULT_OK) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                final String picturePath = cursor.getString(columnIndex);
                cursor.close();
                img.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                Toast.makeText(MainActivity.this, picturePath, Toast.LENGTH_LONG).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

                        // 可选：设置网络连接参数
                        client.setConnectionTimeoutInMillis(2000);
                        client.setSocketTimeoutInMillis(60000);

                        // 调用API
                        String image = "test.jpg";
                        HashMap<String, String> options = new HashMap<String, String>();
                        options.put("max_face_num", "1");
                        options.put("face_fields", "expression,gender");
                        final JSONObject res = client.detect(picturePath, options);
                        try {
                            System.out.println(res.toString(2));
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mGson = new Gson();
                                    String s = res.toString();
/*                                    p = mGson.fromJson(s,person.class);
                                    String  expression = "";
                                    Log.d("AAA", s);
                                        if (p.getResult().get(0).getExpression() == 0) {
                                            expression = "不笑";
                                        } else if (p.getResult().get(0).getExpression() == 1) {
                                            expression = "微笑";
                                        } else {
                                            expression = "大笑";
                                        }

                                    if(!expression.equals("")) {
                                        tv.setText("人脸数目:" + p.getResult_num() + "\n人脸置信度:" + p.getResult().get(0).getFace_probability() + "\n表情:" + expression);
                                    }else {
                                        tv.setText("图片或者其他参数出错");
                                    }*/
                                    //获取图片的位置信息
                                    Log.d("AAA", res.toString());
                                    p = mGson.fromJson(s,person.class);
                                    left = p.getResult().get(0).getLocation().getLeft();
                                    width= p.getResult().get(0).getLocation().getWidth();
                                    Top  = p.getResult().get(0).getLocation().getTop();
                                    height= p.getResult().get(0).getLocation().getHeight();
                                    Log.d("AAA", "left"+left+" width:" +width+" Top"+Top+" height"+height);
                                    Rect rect = new Rect((int) (left/1.5), (int) (Top/1.5), (int) ((left+width)/1.5), (int) ((height+Top)/1.5));
                                    //将这个矩形传递出去
                                    Message   msg = Message.obtain();
                                    msg.obj = rect;
                                    myhandler.sendMessage(msg);
                                    /*tv.setText(s);*/
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }
    }

}
