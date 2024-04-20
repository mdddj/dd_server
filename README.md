# 典典小卖部服务端

## 1.运行环境

* Java21
* Springboot: 3.2.x
* Kotlin
* Mysql8
* Redis

## 2.安装

##### * 自己编译项目
```bash
sudo docker build -t dd_server:v0.0.2 -f ./Dockerfile .
```

#### * 使用docker hub 打包好的
```bash
docker pull liangdiandian/dd_server:latest
```


## 3.运行

```bash
docker run --name dd_erver --network=host \
 -p 8082:8082 \
 -p 8083:8083 \
 -e MYSQL_URL=127.0.0.1:3306 \
 -e MYSQL_DATABASE=test \
 -e MYSQL_USERNAME=root \
 -e MYSQL_PASSWORD=123456 \
 -e REDIS_HOST=127.0.0.1 \
 -e REDIS_PORT=6379 \
 -e REDIS_PASSWORD=123456 \
 -v /Users/ldd/.test:/root/.dd \
 dd_server:v0.0.2
```

##### 1.支持的可配置属性

| 名称             | 属性                        |
|----------------|---------------------------|
| MYSQL_URL      | 数据库连接地址,例: 127.0.0.1:3306 |
| MYSQL_DATABASE | 数据库名称,例子: dd_server       |
| MYSQL_USERNAME | 数据库用户名,例子: root           |
| MYSQL_PASSWORD | 数据库密码,例子:123456           |
| REDIS_HOST     | Redis连接地址,例子:127.0.0.1    |
| REDIS_PORT     | Redis端口,例子:6379           |
| REDIS_PASSWORD | Redis连接密码,没有就留空           |

##### 2.配置文件映射
` -v /Users/ldd/.test:/root/.dd`: 映射配置文件目录到本机

`/Users/ldd/.test`:你的本机目录
`/root/.dd`:容器存放配置目录

##### 3.端口

容器端口: `8082`,`8083`

`8082`: 服务端接口

`8083`: 管理端后台接口

## 4. 默认管理员账号

用户名:`admin`
密码:`123456`

## 5. 管理端后台入口

`http://{服务器ip}:8083`

如果要使用https:

方案1: 参考`nginx.conf`配置添加证书后自己构建镜像

方案2: 自己服务器部署一个nginx,使用反向代理
