# 实现基于OAuth2 Spring Cloud Security的认证方式

视频链接(p26-p41)：https://www.bilibili.com/video/av73730658?p=1

```
/oauth/authorize : 授权端点
/oauth/token : 令牌端点
/oauth/confirm_access : 用户确认授权提交端点
/oauth/error : 授权服务错误信息端点
/oauth/check_token : 用于资源服务访问的令牌解析端点
/oauth/token_key : 提供公钥的端点，如果使用的是JWT令牌。
```

**PS：数据库相关可以参考 security-spring-boot 项目的 mysql.sql 文件**

#### 项目介绍
```
distributed-security-discovery：注册中心 端口：53000
distributed-security-gateway：网关 端口：53010
distributed-security-uaa：认证服务器 端口：53020
distributed-security-order：资源服务器 端口：53021
```

#### 网关模式
获取Token：
```
POST:
http://localhost:53010/uaa/oauth/token
参数x-www-form-urlencoded：
client_id：c1
client_secret：secret
grant_type：password
username：zhangsan
password: 123
```

验证Token：
```
POST
http://localhost:53010/uaa/oauth/check_token
参数x-www-form-urlencoded：
token：需要验证的token
```

调用接口：
```
POST
http://localhost:53010/order/r1
参数x-www-form-urlencoded：
Authorization：Bearer 获取到的token
```
AuthorizationEndpoint

**PS：下面的这些模式可能需要修改部分相关代码才能运行，具体请参考视频教学**

#### 授权码模式
    
    http://localhost:53020/uaa/oauth/authorize?client_id=c1&response_type=code&scope=all&redirect_uri=http://www.baidu.com
    302
    http://localhost:53020/uaa/login 登录页面
    
    POST提交 http://localhost:53020/uaa/login
    
    用户名密码校验
    
    302
    
    http://localhost:53020/uaa/login?error
    
    http://localhost:53020/uaa/oauth/authorize?client_id=c1&response_type=code&scope=all&redirect_uri=http://www.baidu.com
    
    确认授权
    
    POST http://localhost:53020/uaa/oauth/authorize 
    user_oauth_approval: true
    scope.all: true
    authorize: Authorize
    
    302
    
    返回code
    
    http://www.baidu.com?code=l8QcD6
    申请令牌
    
    curl --location --request POST 'http://localhost:53020/uaa/oauth/token' \
    --header 'Content-Type: application/x-www-form-urlencoded' \
    --header 'Cookie: JSESSIONID=C6A23473EC2A9AA68FF832A6D6FCEFBD' \
    --data-urlencode 'client_id=c1' \
    --data-urlencode 'client_secret=123456' \
    --data-urlencode 'grant_type=authorization_code' \
    --data-urlencode 'code=kKRc8b' \
    --data-urlencode 'redirect_uri=http://www.baidu.com'
    
    {
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzMSJdLCJ1c2VyX25hbWUiOiJ7XCJmdWxsbmFtZVwiOlwi5byg5LiJXCIsXCJpZFwiOlwiMVwiLFwibW9iaWxlXCI6XCIxMjM0NTZcIixcInBhc3N3b3JkXCI6XCIkMmEkMTAkb3VpRWZWc2Zxdk9CZnY4YUo5aWZ6dVVqR3c1djhWL2U0cUxYMHZHbUF1WHVoL3hEenlNMVdcIixcInVzZXJuYW1lXCI6XCJ5YW5nY2FvXCJ9Iiwic2NvcGUiOlsiYWxsIl0sImV4cCI6MTU5MjMyNDkyNiwiYXV0aG9yaXRpZXMiOlsicDEiLCJwMiJdLCJqdGkiOiIxM2NmYzhjNi01NjEzLTQ0YmItYmZmYi0xOTdmY2Q5ZGI2NTkiLCJjbGllbnRfaWQiOiJjMSJ9.FPDgiKU7r-GpZGsYbTlUsl7ttavi7X7aeYiiVnQU7aI",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzMSJdLCJ1c2VyX25hbWUiOiJ7XCJmdWxsbmFtZVwiOlwi5byg5LiJXCIsXCJpZFwiOlwiMVwiLFwibW9iaWxlXCI6XCIxMjM0NTZcIixcInBhc3N3b3JkXCI6XCIkMmEkMTAkb3VpRWZWc2Zxdk9CZnY4YUo5aWZ6dVVqR3c1djhWL2U0cUxYMHZHbUF1WHVoL3hEenlNMVdcIixcInVzZXJuYW1lXCI6XCJ5YW5nY2FvXCJ9Iiwic2NvcGUiOlsiYWxsIl0sImF0aSI6IjEzY2ZjOGM2LTU2MTMtNDRiYi1iZmZiLTE5N2ZjZDlkYjY1OSIsImV4cCI6MTU5MjU3NjkyNiwiYXV0aG9yaXRpZXMiOlsicDEiLCJwMiJdLCJqdGkiOiJiYjIzMWUyNi1jMjdiLTQ4ZDYtODJlZS05MGFlZWY4MDZjMmYiLCJjbGllbnRfaWQiOiJjMSJ9.QRI1EB8pGK1XXqi8rAhiPXYzWZcGOGgVBjNgNzDw2FQ",
    "expires_in": 7199,
    "scope": "all",
    "jti": "13cfc8c6-5613-44bb-bffb-197fcd9db659"
    }


#### 简化模式

    http://localhost:53020/uaa/oauth/authorize?client_id=c1&response_type=token&scope=all&redirect_uri=http://www.baidu.com`
   
    POST http://localhost:53020/uaa/login
    
    username: yangcao
    password: 123456`
    
    302
    http://localhost:53020/uaa/oauth/authorize
	user_oauth_approval: true
    scope.all: true
    authorize: Authorize
    
    302
    http://www.baidu.com#access_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzMSJdLCJ1c2VyX25hbWUiOiJ7XCJmdWxsbmFtZVwiOlwi5byg5LiJXCIsXCJpZFwiOlwiMVwiLFwibW9iaWxlXCI6XCIxMjM0NTZcIixcInBhc3N3b3JkXCI6XCIkMmEkMTAkb3VpRWZWc2Zxdk9CZnY4YUo5aWZ6dVVqR3c1djhWL2U0cUxYMHZHbUF1WHVoL3hEenlNMVdcIixcInVzZXJuYW1lXCI6XCJ5YW5nY2FvXCJ9Iiwic2NvcGUiOlsiYWxsIl0sImV4cCI6MTU5MjMyNTY5NiwiYXV0aG9yaXRpZXMiOlsicDEiLCJwMiJdLCJqdGkiOiJjMzBiY2Y1Yi1jZmRlLTQwNTItYTdjYS05MGUzODA2OGY2ODciLCJjbGllbnRfaWQiOiJjMSJ9.yRNeKJzaBF__hXt9Yo85EiWPZIHuPMwu8b9xogE3I80&token_type=bearer&expires_in=7199&jti=c30bcf5b-cfde-4052-a7ca-90e38068f687
    
#### 密码模式
    
    curl --location --request POST 'http://localhost:53020/uaa/oauth/token' \
    --form 'client_id=c1' \
    --form 'client_secret=123456' \
    --form 'grant_type=password' \
    --form 'username=yangcao' \
    --form 'password=123456'
    
    {
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzMSJdLCJzY29wZSI6WyJhbGwiXSwiZXhwIjoxNTkyMzI1OTc5LCJqdGkiOiI2ZGY5NzRmNC0wMTI4LTQyM2EtOTE1Mi01YjQ5YTJjYmM2ZTgiLCJjbGllbnRfaWQiOiJjMSJ9.B4OFNsPjRu-RZCL8n--OnMmIGUVb_qSjLAWgE-YAV38",
    "token_type": "bearer",
    "expires_in": 7199,
    "scope": "all",
    "jti": "6df974f4-0128-423a-9152-5b49a2cbc6e8"
    }

#### 客户端模式

    curl --location --request POST 'http://localhost:53020/uaa/oauth/token' \
    --header 'Cookie: JSESSIONID=8AFAA47A753BFC4AEC06C44156E4DFE4' \
    --form 'client_id=c1' \
    --form 'client_secret=123456' \
    --form 'grant_type=client_credentials'
    
    {
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzMSJdLCJzY29wZSI6WyJhbGwiXSwiZXhwIjoxNTkyMzI1OTc5LCJqdGkiOiI2ZGY5NzRmNC0wMTI4LTQyM2EtOTE1Mi01YjQ5YTJjYmM2ZTgiLCJjbGllbnRfaWQiOiJjMSJ9.B4OFNsPjRu-RZCL8n--OnMmIGUVb_qSjLAWgE-YAV38",
    "token_type": "bearer",
    "expires_in": 7199,
    "scope": "all",
    "jti": "6df974f4-0128-423a-9152-5b49a2cbc6e8"
    }

## Order 资源服务操作

> 校验令牌信息
```
POST:
http://localhost:53020/uaa/oauth/check_token
参数x-www-form-urlencoded：
token：token
```

> 访问Order服务的接口
```
POST:
http://localhost:53021/order/r1
参数x-www-form-urlencoded：
Authorization：Bearer token
```