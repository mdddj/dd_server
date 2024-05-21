# 典典小卖部服务端

## 1.运行环境

* Java21
* Springboot: 3.2.x
* Kotlin
* Mysql8
* Redis


> 提示后台前端代码在`dd_server_admin`目录

## 2.安装

##### * 自己编译项目
```bash
sudo docker build -t liangdiandian/dd_server:tagname .
```
`tagname`: 版本号,例子:0.0.1

##### * 使用docker hub 打包好的

```bash
docker pull liangdiandian/dd_server:latest
```
> `latest`:需要替换为最新版本,可以在[Docker Hub](https://hub.docker.com/search?q=dd_server)查看

## 3.运行

```bash
docker run --name dd_erver --network=host \
 -p 8082:8082 \
 -p 8083:8083 \
 -e MYSQL_URL=1Panel-mysql-Z4Mc:3306 \
 -e MYSQL_DATABASE=test \
 -e MYSQL_USERNAME=root \
 -e MYSQL_PASSWORD=123456 \
 -e REDIS_HOST=1Panel-redis-F20g \
 -e REDIS_PORT=6379 \
 -e REDIS_PASSWORD=123456 \
 -v /dd-service-config:/root/.dd \
 liangdiandian/dd_server:0.0.2
```

> `latest`: 替换为最新版本

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



## 其他

# 上传文件服务
```kotlin
abstract class StorageAbstract {
    abstract fun getLinkUrl(file: MultipartFile,subFolderName: String = "",host:String = "",user: User?) : FileInfo?
}
///调用示例
// 参数1 文件对象 - MultipartFile
// 参数2 avatar - 二级目录名称,多个目录可以 avatar/user 像这样分开
storageServiceImpl.getLinkUrl(file, "avatar", httpServletRequest.getCurrentHost(), user);
```


## 开发插件
如果你用的是 IntelliJ IDEA Ultimate
可以使用dd_kotlin_util工具快速生成一些模板代码