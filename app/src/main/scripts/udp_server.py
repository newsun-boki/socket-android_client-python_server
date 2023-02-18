import socket

# 创建一个服务器的套接字基于udp，type=socket.SOCK_DGRAM表示使用udp协议
udp_sk = socket.socket(type=socket.SOCK_DGRAM)
udp_sk.bind(('', 555))  # 绑定服务器的ip和端口的套接字

i = 0
while i < 5:
    # udp协议不用建立连接
    print("接收")
    msg, addr = udp_sk.recvfrom(1024)  # 接收1024字节的消息 msg表示内容，addr表示ip和端口
    print(msg.decode('utf-8'))
    print("发送")
    udp_sk.sendto('hello'.encode('utf-8'), addr)  #发送消息，需写入对方的ip和端口
    i = i + 1
udp_sk.close()