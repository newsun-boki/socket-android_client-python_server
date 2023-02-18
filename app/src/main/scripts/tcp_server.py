from socket import *
import easygui

test="test01"
print(gethostname())
# 1 定义域名和端口号
HOST,PORT ='',555
# 2 定义缓冲区(缓存)
BUFFER_SIZE = 1024
ADDR=(HOST,PORT)
# 3 创建服务器套接字 AF_INET:IPv4  SOCK_STREAM:协议
tcpServerSocket = socket(AF_INET,SOCK_STREAM)
# 4 绑定域名和端口号
tcpServerSocket.bind(ADDR)
# 5 监听连接  最大连接数
tcpServerSocket.listen(5)
print("begin loop")
# 6 定义一个循环 目的:等待客户端的连接
while True:
# 6.1 打开一个客户端对象 同意连接
    tcpClientSocket,addr = tcpServerSocket.accept()
    print(addr)
    # 连接成功后就弹出界面
    while True:
        Yes_or_No = easygui.buttonbox("socket01是否发送数据?", choices = ['Yes','No','退出'])
        if Yes_or_No=='退出':break
        if Yes_or_No=='Yes':
            tcpClientSocket.send(test.encode())
        msg = tcpClientSocket.recv(1024)
        print (msg.decode('utf-8'))