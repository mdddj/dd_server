# README

后台预览: https://admin.itbug.shop

# 典典的小卖部后台管理平台

使用`react,umi,antd`前后端分离的方式编写

# 安装依赖

```bash
pnpm install
```

# 运行

```bash
pnpm dev
```

# 修改为线上接口

```js
修改src / constants / index.ts里面的HOST_NAME变量;
```

# 预览 (部分)

[![image.png](https://i.postimg.cc/d3xbWM7x/image.png)](https://postimg.cc/fSYqLrfc) [![image.png](https://i.postimg.cc/cJh59q2B/image.png)](https://postimg.cc/cKKmCzsv) [![image.png](https://i.postimg.cc/fW1ZjXxT/image.png)](https://postimg.cc/Rq1jvWsj)

docker run -itd --name="onenav_extend" -p 8080:80 \
 -v /volume1/docker/OneNav-master:/data/wwwroot/default \
 tznb/onenav_extend
