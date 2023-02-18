package com.boyu.socket_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private final int HANDLER_MSG_TELL_RECV = 0x124;
    @SuppressLint("HandlerLeak")
    Handler handler01 = new Handler(){
        public void handleMessage(Message msg){
            //接受到服务器信息时执行
            Toast.makeText(MainActivity.this,(msg.obj).toString(),Toast.LENGTH_LONG).show();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        startNetThread();
        startUdpThread();
    }
    private void startNetThread() {
        new Thread() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket("192.168.137.177", 555);
                    InputStream is = socket.getInputStream();
                    OutputStream os = socket.getOutputStream();
                    byte[] bytes = new byte[1024];
                    int n =is.read(bytes);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

                    while(true) {
                        //向服务器端发送一条消息
                        bw.write("测试客户端\n");
                        bw.flush();
//                        Message msg = handler01.obtainMessage(HANDLER_MSG_TELL_RECV, new String(bytes, 0, n));
//                        msg.sendToTarget();
//                        n =is.read(bytes);
                    }
                } catch (Exception e) {
                }
            }
        }.start();
    }
    private void startUdpThread() {
        new Thread() {
            @Override
            public void run() {
                try {
                    /*
                     * 向服务器端发送数据
                     */
                    // 1.定义服务器的地址、端口号、数据
                    InetAddress address = InetAddress.getByName("192.168.137.177");
                    int port = 555;
                    byte[] data = "用户名：admin;密码：123".getBytes();
                    // 2.创建数据报，包含发送的数据信息
                    DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
                    // 3.创建DatagramSocket对象
                    DatagramSocket socket = new DatagramSocket();
                    // 4.向服务器端发送数据报
//                    socket.send(packet);

                    /*
                     * 接收服务器端响应的数据
                     */
                    // 1.创建数据报，用于接收服务器端响应的数据
                    byte[] data2 = new byte[1024];
                    DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
                    // 2.接收服务器响应的数据
//                    socket.receive(packet2);
//                    // 3.读取数据
//                    String reply = new String(data2, 0, packet2.getLength());
//
//                    System.out.println("我是客户端，服务器说：" + reply);
//                    Log.i("udp","我是客户端，服务器说：" + reply);
//                    Message msg = handler01.obtainMessage(HANDLER_MSG_TELL_RECV, reply);
//                    msg.sendToTarget();
                    int i = 0;
                    while(i < 5){
                        socket.send(packet);
                        socket.receive(packet2);
                        String reply = new String(data2, 0, packet2.getLength());
                        Log.i("udp","我是客户端，服务器说：" + reply);
                    }
                    // 4.关闭资源
                    socket.close();

                }
                catch (Exception e) {
                }
            }
        }.start();
    }
}