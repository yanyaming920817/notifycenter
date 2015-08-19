NotifyCenterV2.0
================

与上一版本的比较：

1、使用上更方便、简单，精简了API
2、更换了短信服务的运营商、短信发送更加快，稳定
3、代码上耦合更低更规范，效率更高，数据库设计简单
4、支持单次多号码一起发送（不同于组发）
5、增加了短信发送的权限，短信长度的限制
云润消息服务V2.0（短信）

1、平台介绍
功能介绍：
本平台支持短信服务，支持群发和指定对象发送，支持单次不超过200条，每次长度不超过3*70字

2、部署
在linux环境下直接执行（后面会增加脚本启动）
cd NotifyCenterV2.0
cd notifycenter
mvn clean package

3、运行
cd messageserver
cd target
java -jar messageserver-0.0.1-SNAPSHOT-jar-with-dependencies.jar 


3、配置文件：
本消息服务有如下两个关键的配置文件：
（1）server.conf
﻿#短信端口设置，默认9431
message.server.port=8050

#Mysql服务器配置
mysql.url=jdbc:mysql://192.168.1.206:3306/messagecenter?useUnicode=true&characterEncoding=utf-8
mysql.user=root
mysql.password=urun

 (2)message.conf
 #短信服务的配置  （一般不需要配置，都是固定的）
message.name=yunrun   (购买的服务账号和密码)
message.password=60850C65951C71CF5EAE6D539E7B
message.sign=云润大数据     （短信结尾的签名，不能改，已经备案了）
message.url=http://web.1xinxi.cn/asmx/smsservice.aspx   （提交短信的接口，只要运营商不该借口就不改变）


#查询余额的网址
message.balance=http://web.1xinxi.cn/asmx/smsservice.aspx （查询短信余额的接口，只要运营商不该借口就不改变）


4、调用的协议
1、发送短信：
  http://host:port/message/send?receiver=xxxx&content=xxxx
  注意：本协议可支持多条一起发，receiver参数用英文","将号码隔开。例如：receiver=10086,10010,10011
  
2、短信群发（即以组为单位发送）
   http://host:port/message/group?groupid=xxxx&content=xxxx

3、查询余额
    http://host:port/message/balance
    返回短信账号剩下的短信额度，例如：查询成功，账号还剩余：4条短信

5,关于返回码的说明：
-1	    用户名或者密码不正确
1,     批次号	1代表发送短信成功, 批次号用来获取状态
0,     批次号	0发送短信失败
2	   余额不足
3	   扣费失败
6	   有效号码为空
7	   短信内容为空
8	   签名不规范
审核不通过	短信内容有敏感词等，审核失败，实时退费 

