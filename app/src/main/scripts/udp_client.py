import socket

ip_port = ('127.0.0.1', 555)  # 服务器的ip与端口

# 创建一个服务器的套接字,基于udp协议 type=socket.SOCK_DGRAM
udp_sk = socket.socket(type=socket.SOCK_DGRAM)
i=0
while i < 5:
    # 发送消息并编码成utf-8,需要传ip和端口
    print("发送")
    udp_sk.sendto('你好吗'.encode('utf-8'), ip_port)
    print("接收")
    # 接收1024字节的消息 加 ip+端口
    back_msg, addr = udp_sk.recvfrom(1024)
    print(back_msg.decode('utf-8'), addr)
    i = i + 1
udp_sk.close()