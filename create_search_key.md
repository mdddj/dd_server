

## 创建搜索key



搜索key: 21e6bdff291cd3beec0e8b852d6b164b3cf07a4b13d796a538f89dc3d91b4767


root: HmPEKZhcoANzRt3DTPhRQVRxPEVZw7m2TymKwKhs_efTyb3

```curl

curl \
  -X POST 'http://localhost:7700/keys' \
  -H 'Authorization: Bearer key' \
  -H 'Content-Type: application/json' \
  --data-binary '{
    "description": "典典博客搜索",
    "actions": ["search"],
    "indexes": ["*"],
  }'
  
```