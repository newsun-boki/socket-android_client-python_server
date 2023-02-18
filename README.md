# Socket Test

使用Android手机作为客户端，电脑python服务端，进行socket通讯(主要是UDP)。

## Quick Start

+ 运行python服务器
: 打开一个终端，切换到`app\src\main\scripts`，运行udp服务器

    ```bash
    python udp_server.py
    ```

+ 运行Android客户端: 打开`app\src\main\java\com\boyu\socket_test\MainActivity.java`, 将74行替换为运行服务器的电脑的IP。
    ```java
    InetAddress address = InetAddress.getByName("192.168.137.177");
    ```
    
    再用Android Studio运行。