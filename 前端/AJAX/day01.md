# HTTP
HTTP（hypertext transport protocol）协议『超文本传输协议』，协议详细规定了浏览器和万维网服务器之间互相通信的规则。
约定, 规则

## 请求报文（request）
重点是格式与参数
```
行      POST  /s?ie=utf-8  HTTP/1.1 
头      Host: atguigu.com
        Cookie: name=guigu
        Content-type: application/x-www-form-urlencoded
        User-Agent: chrome 83
空行
体      username=admin&password=admin
```

## 响应报文（response）
```
行      HTTP/1.1  200  OK
头      Content-Type: text/html;charset=utf-8
        Content-length: 2048
        Content-encoding: gzip
空行    
体      <html>
            <head>
            </head>
            <body>
                <h1>尚硅谷</h1>
            </body>
        </html>
```
* 404 网页未找到或者网页丢失
* 403 服务器理解客户的请求，但拒绝处理它，通常由于服务器上文件或目录的权限设置导致的WEB访问错误。 
* 401 代表用户没有访问权限，需要进行身份认证
* 500 服务器内部错误
* 200 OK

# express框架的使用
```js
//1. 引入express
const express = require('express');

//2. 创建应用对象
const app = express();

//3. 创建路由规则
// request 是对请求报文的封装
// response 是对响应报文的封装
app.get('/', (request, response)=>{
    //设置响应
    response.send('HELLO EXPRESS');
});

//4. 监听端口启动服务
app.listen(8000, ()=>{
    console.log("服务已经启动, 8000 端口监听中....");
});
```
# 原生AJAX
## GET请求
```js
<script>
    //获取button元素
    const btn = document.getElementsByTagName('button')[0];
    const result = document.getElementById("result");
    //绑定事件
    btn.onclick = function(){
        //1. 创建对象
        const xhr = new XMLHttpRequest();
        //2. 初始化 设置请求方法和 url
        xhr.open('GET', 'http://127.0.0.1:8000/server?a=100&b=200&c=300');
        //3. 发送
        xhr.send();
        //4. 事件绑定 处理服务端返回的结果
        // on  when 当....时候
        // readystate 是 xhr 对象中的属性, 表示状态 0 1 2 3 4
        // change  改变
        xhr.onreadystatechange = function(){
            //判断 (服务端返回了所有的结果)
            if(xhr.readyState === 4){
                //判断响应状态码 200  404  403 401 500
                // 2xx 成功
                if(xhr.status >= 200 && xhr.status < 300){
                    //处理结果  行 头 空行 体
                    //响应 
                    // console.log(xhr.status);//状态码
                    // console.log(xhr.statusText);//状态字符串
                    // console.log(xhr.getAllResponseHeaders());//所有响应头
                    // console.log(xhr.response);//响应体
                    //设置 result 的文本
                    result.innerHTML = xhr.response;
                }else{
                    //xxxxxxx
                }
            }
        }
    }
</script>
```
## POST请求
```js
<script>
    //获取元素对象
    const result = document.getElementById("result");
    //绑定事件
    result.addEventListener("mouseover", function(){
        //1. 创建对象
        const xhr = new XMLHttpRequest();
        //2. 初始化 设置类型与 URL
        xhr.open('POST', 'http://127.0.0.1:8000/server');
        //设置请求头
        xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
        xhr.setRequestHeader('name','atguigu');
        //3. 发送
        xhr.send('a=100&b=200&c=300');
        // xhr.send('a:100&b:200&c:300');
        // xhr.send('1233211234567');
        
        //4. 事件绑定
        xhr.onreadystatechange = function(){
            //判断
            if(xhr.readyState === 4){
                if(xhr.status >= 200 && xhr.status < 300){
                    //处理服务端返回的结果
                    result.innerHTML = xhr.response;
                }
            }
        }
    });
</script>
```
## 接收服务器的json
```js
<script>
    const result = document.getElementById('result');
    //绑定键盘按下事件
    window.onkeydown = function(){
        //发送请求
        const xhr = new XMLHttpRequest();
        //设置响应体数据的类型
        xhr.responseType = 'json';
        //初始化
        xhr.open('GET','http://127.0.0.1:8000/json-server');
        //发送
        xhr.send();
        //事件绑定
        xhr.onreadystatechange = function(){
            if(xhr.readyState === 4){
                if(xhr.status >= 200 && xhr.status < 300){
                    //
                    // console.log(xhr.response);
                    // result.innerHTML = xhr.response;
                    // 1. 手动对数据转化
                    // let data = JSON.parse(xhr.response);
                    // console.log(data);
                    // result.innerHTML = data.name;
                    // 2. 自动转换
                    console.log(xhr.response);
                    result.innerHTML = xhr.response.name;
                }
            }
        }
    }
</script>
```
## 解决ie缓存问题
```js
<script>
    const btn = document.getElementsByTagName('button')[0];
    const result = document.querySelector('#result');

    btn.addEventListener('click', function(){
        const xhr = new XMLHttpRequest();
        xhr.open("GET",'http://127.0.0.1:8000/ie?t='+Date.now());
        xhr.send();
        xhr.onreadystatechange = function(){
            if(xhr.readyState === 4){
                if(xhr.status >= 200 && xhr.status< 300){
                    result.innerHTML = xhr.response;
                }
            }
        }
    })
</script>
```

## 超时与网络异常
```js
<script>
    const btn = document.getElementsByTagName('button')[0];
    const result = document.querySelector('#result');

    btn.addEventListener('click', function(){
        const xhr = new XMLHttpRequest();
        //超时设置 2s 设置
        xhr.timeout = 2000;
        //超时回调
        xhr.ontimeout = function(){
            alert("网络异常, 请稍后重试!!");
        }
        //网络异常回调
        xhr.onerror = function(){
            alert("你的网络似乎出了一些问题!");
        }

        xhr.open("GET",'http://127.0.0.1:8000/delay');
        xhr.send();
        xhr.onreadystatechange = function(){
            if(xhr.readyState === 4){
                if(xhr.status >= 200 && xhr.status< 300){
                    result.innerHTML = xhr.response;
                }
            }
        }
    })
</script>
```
## 取消请求
```js
<script>
        //获取元素对象
        const btns = document.querySelectorAll('button');
        let x = null;

        btns[0].onclick = function(){
            x = new XMLHttpRequest();
            x.open("GET",'http://127.0.0.1:8000/delay');
            x.send();
        }

        // abort
        btns[1].onclick = function(){
            x.abort();
        }
    </script>
```
## 重复请求问题
```js
<script>
    //获取元素对象
    const btns = document.querySelectorAll('button');
    let x = null;
    //标识变量
    let isSending = false; // 是否正在发送AJAX请求

    btns[0].onclick = function(){
        //判断标识变量
        if(isSending) x.abort();// 如果正在发送, 则取消该请求, 创建一个新的请求
        x = new XMLHttpRequest();
        //修改 标识变量的值
        isSending = true;
        x.open("GET",'http://127.0.0.1:8000/delay');
        x.send();
        x.onreadystatechange = function(){
            if(x.readyState === 4){
                //修改标识变量
                isSending = false;
            }
        }
    }

    // abort
    btns[1].onclick = function(){
        x.abort();
    }
</script>
```
# jQuery-AJAX
```js
 <script>
    $('button').eq(0).click(function(){
        $.get('http://127.0.0.1:8000/jquery-server', {a:100, b:200}, function(data){
            console.log(data);
        },'json');
    });

    $('button').eq(1).click(function(){
        $.post('http://127.0.0.1:8000/jquery-server', {a:100, b:200}, function(data){
            console.log(data);
        });
    });

    $('button').eq(2).click(function(){
        $.ajax({
            //url
            url: 'http://127.0.0.1:8000/jquery-server',
            //参数
            data: {a:100, b:200},
            //请求类型
            type: 'GET',
            //响应体结果
            dataType: 'json',
            //成功的回调
            success: function(data){
                console.log(data);
            },
            //超时时间
            timeout: 2000,
            //失败的回调
            error: function(){
                console.log('出错啦!!');
            },
            //头信息
            headers: {
                c:300,
                d:400
            }
        });
    });

</script>
```
## axios
```js
<script>
    // https://github.com/axios/axios
    const btns = document.querySelectorAll('button');

    //配置 baseURL
    axios.defaults.baseURL = 'http://127.0.0.1:8000';

    btns[0].onclick = function () {
        //GET 请求
        axios.get('/axios-server', {
            //url 参数
            params: {
                id: 100,
                vip: 7
            },
            //请求头信息
            headers: {
                name: 'atguigu',
                age: 20
            }
        }).then(value => {
            console.log(value);
        });
    }

    btns[1].onclick = function () {
        axios.post('/axios-server', {
            username: 'admin',
            password: 'admin'
        }, {
            //url 
            params: {
                id: 200,
                vip: 9
            },
            //请求头参数
            headers: {
                height: 180,
                weight: 180,
            }
        });
    }

    btns[2].onclick = function(){
        axios({
            //请求方法
            method : 'POST',
            //url
            url: '/axios-server',
            //url参数
            params: {
                vip:10,
                level:30
            },
            //头信息
            headers: {
                a:100,
                b:200
            },
            //请求体参数
            data: {
                username: 'admin',
                password: 'admin'
            }
        }).then(response=>{
            //响应状态码
            console.log(response.status);
            //响应状态字符串
            console.log(response.statusText);
            //响应头信息
            console.log(response.headers);
            //响应体
            console.log(response.data);
        })
    }
</script>
```
# fetch
```js
<script>
    //文档地址
    //https://developer.mozilla.org/zh-CN/docs/Web/API/WindowOrWorkerGlobalScope/fetch
    
    const btn = document.querySelector('button');

    btn.onclick = function(){
        fetch('http://127.0.0.1:8000/fetch-server?vip=10', {
            //请求方法
            method: 'POST',
            //请求头
            headers: {
                name:'atguigu'
            },
            //请求体
            body: 'username=admin&password=admin'
        }).then(response => {
            // return response.text();
            return response.json();
        }).then(response=>{
            console.log(response);
        });
    }
</script>
```
# 跨域问题
## 1.同源策略
```js
<script>
    const btn = document.querySelector('button');

    btn.onclick = function(){
        const x = new XMLHttpRequest();
        //这里因为是满足同源策略的, 所以 url 可以简写
        x.open("GET",'/data');
        //发送
        x.send();
        //
        x.onreadystatechange = function(){
            if(x.readyState === 4){
                if(x.status >= 200 && x.status < 300){
                    console.log(x.response);
                }
            }
        }
    }
</script>

> npm server.js
const express = require('express');

const app = express();

app.get('/home', (request, response)=>{
    //响应一个页面
    response.sendFile(__dirname + '/index.html');
});

app.get('/data', (request, response)=>{
    response.send('用户数据');
});

app.listen(9000, ()=>{
    console.log("服务已经启动...");
});
```
## 2.JSONP
## 3.CORS
```js
<script>
    const btn = document.querySelector('button');

    btn.onclick = function(){
        //1. 创建对象
        const x = new XMLHttpRequest();
        //2. 初始化设置
        x.open("GET", "http://127.0.0.1:8000/cors-server");
        //3. 发送
        x.send();
        //4. 绑定事件
        x.onreadystatechange = function(){
            if(x.readyState === 4){
                if(x.status >= 200 && x.status < 300){
                    //输出响应体
                    console.log(x.response);
                }
            }
        }
    }
</script>

app.all('/cors-server', (request, response)=>{
    //设置响应头
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Headers", '*');
    response.setHeader("Access-Control-Allow-Method", '*');
    // response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
    response.send('hello CORS');
});

```