# 事件的委派
    - 指将事件统一绑定给元素的共同的祖先元素，这样当后代元素上的事件触发时，会一直冒泡到祖先元素，从而通过祖先元素的响应函数来处理事件。
    -事件委派是利用了冒泡，通过委派可以减少事件绑定的次数，提高程序的性能
```js
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title></title>
    <script type="text/javascript">
        
        window.onload = function(){
            
            var u1 = document.getElementById("u1");
            
            //点击按钮以后添加超链接
            var btn01 = document.getElementById("btn01");
            btn01.onclick = function(){
                //创建一个li
                var li = document.createElement("li");
                li.innerHTML = "<a href='javascript:;' class='link'>新建的超链接</a>";
                
                //将li添加到ul中
                u1.appendChild(li);
            };
            
            
            /*
                * 为每一个超链接都绑定一个单击响应函数
                * 这里我们为每一个超链接都绑定了一个单击响应函数，这种操作比较麻烦，
                * 	而且这些操作只能为已有的超链接设置事件，而新添加的超链接必须重新绑定
                */
            //获取所有的a
            var allA = document.getElementsByTagName("a");
            //遍历
            /*for(var i=0 ; i<allA.length ; i++){
                allA[i].onclick = function(){
                    alert("我是a的单击响应函数！！！");
                };
            }*/
            
            /*
                * 我们希望，只绑定一次事件，即可应用到多个的元素上，即使元素是后添加的
                * 我们可以尝试将其绑定给元素的共同的祖先元素
                * 
                * 事件的委派
                * 	- 指将事件统一绑定给元素的共同的祖先元素，这样当后代元素上的事件触发时，会一直冒泡到祖先元素
                * 		从而通过祖先元素的响应函数来处理事件。
                *  - 事件委派是利用了冒泡，通过委派可以减少事件绑定的次数，提高程序的性能
                */
            
            //为ul绑定一个单击响应函数
            u1.onclick = function(event){
                event = event || window.event;
                
                /*
                    * target
                    * 	- event中的target表示的触发事件的对象
                    */
                //alert(event.target);
                
                
                //如果触发事件的对象是我们期望的元素，则执行否则不执行
                if(event.target.className === "link"){
                    alert("我是ul的单击响应函数");
                }
                
            };
            
        };
        
    </script>
</head>
<body>
    <button id="btn01">添加超链接</button>
    
    <ul id="u1" style="background-color: #bfa;">
        <li>
            <p>我是p元素</p>
        </li>
        <li><a href="javascript:;" class="link">超链接一</a></li>
        <li><a href="javascript:;" class="link">超链接二</a></li>
        <li><a href="javascript:;" class="link">超链接三</a></li>
    </ul>
    
</body>
</html>
```
# 事件的绑定
```js
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <script type="text/javascript">
        
        window.onload = function(){

            //获取按钮对象
            var btn01 = document.getElementById("btn01");
            
            /*
                * 使用 对象.事件 = 函数 的形式绑定响应函数，
                * 	它只能同时为一个元素的一个事件绑定一个响应函数，
                * 	不能绑定多个，如果绑定了多个，则后边会覆盖掉前边的
                */
            
            //为btn01绑定一个单击响应函数
            /*btn01.onclick = function(){
                alert(1);
            };*/
            
            //为btn01绑定第二个响应函数
            /*btn01.onclick = function(){
                alert(2);
            };*/
            
            /*
                * addEventListener()
                * 	- 通过这个方法也可以为元素绑定响应函数
                *  - 参数：
                * 		1.事件的字符串，不要on
                * 		2.回调函数，当事件触发时该函数会被调用
                * 		3.是否在捕获阶段触发事件，需要一个布尔值，一般都传false
                * 
                * 使用addEventListener()可以同时为一个元素的相同事件同时绑定多个响应函数，
                * 	这样当事件被触发时，响应函数将会按照函数的绑定顺序执行
                * 
                * 这个方法不支持IE8及以下的浏览器
                */
            /*btn01.addEventListener("click",function(){
                alert(1);
            },false);
            
            btn01.addEventListener("click",function(){
                alert(2);
            },false);
            
            btn01.addEventListener("click",function(){
                alert(3);
            },false);*/
            
            /*
                * attachEvent()
                * 	- 在IE8中可以使用attachEvent()来绑定事件
                *  - 参数：
                * 		1.事件的字符串，要on
                * 		2.回调函数
                * 
                *  - 这个方法也可以同时为一个事件绑定多个处理函数，
                * 		不同的是它是后绑定先执行，执行顺序和addEventListener()相反
                */
            /*btn01.attachEvent("onclick",function(){
                alert(1);
            });
            
            btn01.attachEvent("onclick",function(){
                alert(2);
            });
            
            btn01.attachEvent("onclick",function(){
                alert(3);
            });*/
            
            /*btn01.addEventListener("click",function(){
                alert(this);
            },false);*/
            
            /*btn01.attachEvent("onclick",function(){
                alert(this);
            });*/
            
            bind(btn01 , "click" , function(){
                alert(this);
            });
        
            
        };
        
        //定义一个函数，用来为指定元素绑定响应函数
        /*
            * addEventListener()中的this，是绑定事件的对象
            * attachEvent()中的this，是window
            *  需要统一两个方法this
            */
        /*
            * 参数：
            * 	obj 要绑定事件的对象
            * 	eventStr 事件的字符串(不要on)
            *  callback 回调函数
            */
        function bind(obj , eventStr , callback){
            if(obj.addEventListener){
                //大部分浏览器兼容的方式
                obj.addEventListener(eventStr , callback , false);
            }else{
                /*
                    * this是谁由调用方式决定
                    * callback.call(obj)
                    */
                //IE8及以下
                obj.attachEvent("on"+eventStr , function(){
                    //在匿名函数中调用回调函数
                    callback.call(obj);
                });
            }
        }
        
    </script>
</head>
<body>
    
    <button id="btn01">点我一下</button>
</body>
</html>
```

# 事件的传播
```js
<script type="text/javascript">	
    window.onload = function(){
        
        /*
        * 分别为三个div绑定单击响应函数
        */
        var box1 = document.getElementById("box1");
        var box2 = document.getElementById("box2");
        var box3 = document.getElementById("box3");
        
        /*
        * 事件的传播
        * 	- 关于事件的传播网景公司和微软公司有不同的理解
        * 	- 微软公司认为事件应该是由内向外传播，也就是当事件触发时，应该先触发当前元素上的事件，
        * 		然后再向当前元素的祖先元素上传播，也就说事件应该在冒泡阶段执行。
        *  - 网景公司认为事件应该是由外向内传播的，也就是当前事件触发时，应该先触发当前元素的最外层的祖先元素的事件，
        * 		然后在向内传播给后代元素
        * 	- W3C综合了两个公司的方案，将事件传播分成了三个阶段
        * 		1.捕获阶段
        * 			- 在捕获阶段时从最外层的祖先元素，向目标元素进行事件的捕获，但是默认此时不会触发事件
        * 		2.目标阶段
        * 			- 事件捕获到目标元素，捕获结束开始在目标元素上触发事件
        * 		3.冒泡阶段
        * 			- 事件从目标元素向他的祖先元素传递，依次触发祖先元素上的事件
        * 
        * 		- 如果希望在捕获阶段就触发事件，可以将addEventListener()的第三个参数设置为true
        * 			一般情况下我们不会希望在捕获阶段触发事件，所以这个参数一般都是false
        * 
        * 	- IE8及以下的浏览器中没有捕获阶段
        */
        
        bind(box1,"click",function(){
            alert("我是box1的响应函数")
        });
        
        bind(box2,"click",function(){
            alert("我是box2的响应函数")
        });
        
        bind(box3,"click",function(){
            alert("我是box3的响应函数")
        });
        
    };
    
    
    function bind(obj , eventStr , callback){
        if(obj.addEventListener){
            //大部分浏览器兼容的方式
            obj.addEventListener(eventStr , callback , true);
        }else{
            /*
                * this是谁由调用方式决定
                * callback.call(obj)
                */
            //IE8及以下
            obj.attachEvent("on"+eventStr , function(){
                //在匿名函数中调用回调函数
                callback.call(obj);
            });
        }
    }
    
</script>
```

# 进阶--事件的拖拽
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <style type="text/css">
        
        #box1{
            width: 100px;
            height: 100px;
            background-color: red;
            position: absolute;
        }
        
        #box2{
            width: 100px;
            height: 100px;
            background-color: yellow;
            position: absolute;
            
            left: 200px;
            top: 200px;
        }
        
    </style>
    
    <script type="text/javascript">
        
        window.onload = function(){
            /*
            * 拖拽box1元素
            *  - 拖拽的流程
            * 	1.当鼠标在被拖拽元素上按下时，开始拖拽  onmousedown
            * 	2.当鼠标移动时被拖拽元素跟随鼠标移动 onmousemove
            * 	3.当鼠标松开时，被拖拽元素固定在当前位置	onmouseup
            */
            
            //获取box1、box2、img1对象
            var box1 = document.getElementById("box1");
            var box2 = document.getElementById("box2");
            var img1 = document.getElementById("img1");
            
            //开启box1的拖拽
            drag(box1);
            //开启box2的
            drag(box2);
            //开启img1的
            drag(img1);
     
        };
        
        /*
        * 提取一个专门用来设置拖拽的函数
        * 参数：开启拖拽的元素
        */
        function drag(obj){
            //当鼠标在被拖拽元素上按下时，开始拖拽  onmousedown
            obj.onmousedown = function(event){
                
                //设置box1捕获所有鼠标按下的事件
                /*
                * setCapture()
                * 	- 只有IE支持，但是在火狐中调用时不会报错，
                * 	  而如果使用chrome调用，会报错
                */
                /*if(box1.setCapture){
                    box1.setCapture();
                }*/
                obj.setCapture && obj.setCapture();
                
                
                event = event || window.event;
                //div的偏移量 鼠标.clentX - 元素.offsetLeft
                //div的偏移量 鼠标.clentY - 元素.offsetTop
                var ol = event.clientX - obj.offsetLeft;
                var ot = event.clientY - obj.offsetTop;
                
                
                //为document绑定一个onmousemove事件
                document.onmousemove = function(event){
                    event = event || window.event;
                    //当鼠标移动时被拖拽元素跟随鼠标移动 onmousemove
                    //获取鼠标的坐标
                    var left = event.clientX - ol;
                    var top = event.clientY - ot;
                    
                    //修改box1的位置
                    obj.style.left = left+"px";
                    obj.style.top = top+"px";
                    
                };
                
                //为document绑定一个鼠标松开事件
                document.onmouseup = function(){
                    //当鼠标松开时，被拖拽元素固定在当前位置 onmouseup
                    //取消document的onmousemove事件
                    document.onmousemove = null;
                    //取消document的onmouseup事件
                    document.onmouseup = null;
                    //当鼠标松开时，取消对事件的捕获
                    obj.releaseCapture && obj.releaseCapture();
                };
                
                /*
                * 当我们拖拽一个网页中的内容时，浏览器会默认去搜索引擎中搜索内容，
                * 	此时会导致拖拽功能的异常，这个是浏览器提供的默认行为，
                * 	如果不希望发生这个行为，则可以通过return false来取消默认行为
                * 
                * 但是这招对IE8不起作用
                */
                return false;
                
            };
        }
        
        
    </script>
</head>
<body>
    
    我是一段文字
    
    <div id="box1"></div>
    
    <div id="box2"></div>
    
    <img src="img/an.jpg" id="img1" style="position: absolute;"/>
</body>
</html>
```
# 鼠标滚轮事件
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <style type="text/css">
        
        #box1{
            width: 100px;
            height: 100px;
            background-color: red;
        }
        
    </style>
    <script type="text/javascript">
        
        window.onload = function(){
            
            
            //获取id为box1的div
            var box1 = document.getElementById("box1");
            
            //为box1绑定一个鼠标滚轮滚动的事件
            /*
                * onmousewheel鼠标滚轮滚动的事件，会在滚轮滚动时触发，
                * 	但是火狐不支持该属性
                * 
                * 在火狐中需要使用 DOMMouseScroll 来绑定滚动事件
                * 	注意该事件需要通过addEventListener()函数来绑定
                */
            
            
            box1.onmousewheel = function(event){
                
                event = event || window.event;
                
                
                //event.wheelDelta 可以获取鼠标滚轮滚动的方向
                //向上滚 120   向下滚 -120
                //wheelDelta这个值我们不看大小，只看正负
                
                //alert(event.wheelDelta);
                
                //wheelDelta这个属性火狐中不支持
                //在火狐中使用event.detail来获取滚动的方向
                //向上滚 -3  向下滚 3
                //alert(event.detail);
                
                
                /*
                    * 当鼠标滚轮向下滚动时，box1变长
                    * 	当滚轮向上滚动时，box1变短
                    */
                //判断鼠标滚轮滚动的方向
                if(event.wheelDelta > 0 || event.detail < 0){
                    //向上滚，box1变短
                    box1.style.height = box1.clientHeight - 10 + "px";
                    
                }else{
                    //向下滚，box1变长
                    box1.style.height = box1.clientHeight + 10 + "px";
                }
                
                /*
                    * 使用addEventListener()方法绑定响应函数，取消默认行为时不能使用return false
                    * 需要使用event来取消默认行为event.preventDefault();
                    * 但是IE8不支持event.preventDefault();这个玩意，如果直接调用会报错
                    */
                event.preventDefault && event.preventDefault();
                
                
                /*
                    * 当滚轮滚动时，如果浏览器有滚动条，滚动条会随之滚动，
                    * 这是浏览器的默认行为，如果不希望发生，则可以取消默认行为
                    */
                return false;
   
            };
            
            //为火狐绑定滚轮事件
            bind(box1,"DOMMouseScroll",box1.onmousewheel);
            
            
        };
        
        
        function bind(obj , eventStr , callback){
            if(obj.addEventListener){
                //大部分浏览器兼容的方式
                obj.addEventListener(eventStr , callback , false);
            }else{
                /*
                    * this是谁由调用方式决定
                    * callback.call(obj)
                    */
                //IE8及以下
                obj.attachEvent("on"+eventStr , function(){
                    //在匿名函数中调用回调函数
                    callback.call(obj);
                });
            }
        }
        
    </script>
</head>
<body style="height: 2000px;">
    
    <div id="box1"></div>
    
</body>
</html>
```
# 键盘事件
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <script type="text/javascript">
        
        window.onload = function(){
            
            /*
            * 键盘事件：
            * 	onkeydown
            * 		- 按键被按下
            * 		- 对于onkeydown来说如果一直按着某个按键不松手，则事件会一直触发
            * 		- 当onkeydown连续触发时，第一次和第二次之间会间隔稍微长一点，其他的会非常的快
            * 			这种设计是为了防止误操作的发生。
            * 	onkeyup
            * 		- 按键被松开
            * 
            *  键盘事件一般都会绑定给一些可以获取到焦点的对象或者是document
            */
            
            document.onkeydown = function(event){
                event = event || window.event;
                
                /*
                    * 可以通过keyCode来获取按键的编码
                    * 	通过它可以判断哪个按键被按下
                    * 除了keyCode，事件对象中还提供了几个属性
                    * 	altKey
                    * 	ctrlKey
                    * 	shiftKey
                    * 		- 这个三个用来判断alt ctrl 和 shift是否被按下
                    * 			如果按下则返回true，否则返回false
                    */
                
                //console.log(event.keyCode);
                
                //判断一个y是否被按下
                //判断y和ctrl是否同时被按下
                if(event.keyCode === 89 && event.ctrlKey){
                    console.log("ctrl和y都被按下了");
                }
                
                
            };
            
            /*document.onkeyup = function(){
                console.log("按键松开了");
            };*/
            
            //获取input
            var input = document.getElementsByTagName("input")[0];
            
            input.onkeydown = function(event){
                
                event = event || window.event;
                
                //console.log(event.keyCode);
                //数字 48 - 57
                //使文本框中不能输入数字
                if(event.keyCode >= 48 && event.keyCode <= 57){
                    //在文本框中输入内容，属于onkeydown的默认行为
                    //如果在onkeydown中取消了默认行为，则输入的内容，不会出现在文本框中
                    return false;
                }
                
                
            };
        };
        
        
    </script>
</head>
<body>
    
    <input type="text" />
    
</body>
</html>
```
# BOM - 浏览器对象模型
    - BOM可以使我们通过JS来操作浏览器
    - 在BOM中为我们提供了一组对象，用来完成对浏览器的操作
    - BOM对象
        Window
            - 代表的是整个浏览器的窗口，同时window也是网页中的全局对象
        Navigator
            - 代表的当前浏览器的信息，通过该对象可以来识别不同的浏览器
        Location
            - 代表当前浏览器的地址栏信息，通过Location可以获取地址栏信息，或者操作浏览器跳转页面
        History
            - 代表浏览器的历史记录，可以通过该对象来操作浏览器的历史记录
                由于隐私原因，该对象不能获取到具体的历史记录，只能操作浏览器向前或向后翻页
                而且该操作只在当次访问时有效
        Screen
            - 代表用户的屏幕的信息，通过该对象可以获取到用户的显示器的相关的信息


        这些BOM对象在浏览器中都是作为window对象的属性保存的，
            可以通过window对象来使用，也可以直接使用
 ## Navigator
```js
<script type="text/javascript">
    /*
    * Navigator
    * 	- 代表的当前浏览器的信息，通过该对象可以来识别不同的浏览器
    * 	- 由于历史原因，Navigator对象中的大部分属性都已经不能帮助我们识别浏览器了
    * 	- 一般我们只会使用userAgent来判断浏览器的信息，
    * 		userAgent是一个字符串，这个字符串中包含有用来描述浏览器信息的内容，
    * 		不同的浏览器会有不同的userAgent
    * 
    * 火狐的userAgent
    * 	Mozilla/5.0 (Windows NT 6.1; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0
    * 
    * Chrome的userAgent
    *  Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.82 Safari/537.36
    * 
    * IE8
    * 	Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/7.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)
    * 
    * IE9
    * 	Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/7.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)
    * 
    * IE10
    * 	Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/7.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)
    * 
    * IE11
    * 	Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E; rv:11.0) like Gecko
    * 	- 在IE11中已经将微软和IE相关的标识都已经去除了，所以我们基本已经不能通过UserAgent来识别一个浏览器是否是IE了
    */

    //alert(navigator.appName);

    var ua = navigator.userAgent;

    console.log(ua);

    if(/firefox/i.test(ua)){
        alert("你是火狐！！！");
    }else if(/chrome/i.test(ua)){
        alert("你是Chrome");
    }else if(/msie/i.test(ua)){
        alert("你是IE浏览器~~~");
    }else if("ActiveXObject" in window){
        alert("你是IE11，枪毙了你~~~");
    }

    /*
        * 如果通过UserAgent不能判断，还可以通过一些浏览器中特有的对象，来判断浏览器的信息
        * 比如：ActiveXObject
        */
    /*if("ActiveXObject" in window){
        alert("你是IE，我已经抓住你了~~~");
    }else{
        alert("你不是IE~~~");
    }*/

    /*alert("ActiveXObject" in window);*/
    </script>
```
## history
```js
<script type="text/javascript">
    /*
        * History
        * 	- 对象可以用来操作浏览器向前或向后翻页
        */
    window.onload = function(){
        
        //获取按钮对象
        var btn = document.getElementById("btn");
        
        btn.onclick = function(){
            /*
                * length
                * 	- 属性，可以获取到当成访问的链接数量
                */
            //alert(history.length);
            
            /*
                * back()
                * 	- 可以用来回退到上一个页面，作用和浏览器的回退按钮一样
                */
            //history.back();
            
            /*
                * forward()
                * 	- 可以跳转下一个页面，作用和浏览器的前进按钮一样
                */
            //history.forward();
            
            /*
                * go()
                * 	- 可以用来跳转到指定的页面
                * 	- 它需要一个整数作为参数
                * 		1:表示向前跳转一个页面 相当于forward()
                * 		2:表示向前跳转两个页面
                * 		-1:表示向后跳转一个页面
                * 		-2:表示向后跳转两个页面
                */
            history.go(-2);
        };
        
    };
    
</script>
```
## Location
```js
<script type="text/javascript">
    /*
        * Location
        * 	- 该对象中封装了浏览器的地址栏的信息
        */
    window.onload = function(){
        
        //获取按钮对象
        var btn = document.getElementById("btn");
        
        btn.onclick = function(){
            
            //如果直接打印location，则可以获取到地址栏的信息（当前页面的完整路径）
            //alert(location);
            
            /*
                * 如果直接将location属性修改为一个完整的路径，或相对路径
                * 	则我们页面会自动跳转到该路径，并且会生成相应的历史记录
                */
            //location = "http://www.baidu.com";
            //location = "01.BOM.html";
            
            /*
                * assign()
                * 	- 用来跳转到其他的页面，作用和直接修改location一样
                */
            //location.assign("http://www.baidu.com");
            
            /*
                * reload()
                * 	- 用于重新加载当前页面，作用和刷新按钮一样
                * 	- 如果在方法中传递一个true，作为参数，则会强制清空缓存刷新页面
                */
            //location.reload(true);
            
            /*
                * replace()
                * 	- 可以使用一个新的页面替换当前页面，调用完毕也会跳转页面
                * 		不会生成历史记录，不能使用回退按钮回退
                */
            location.replace("01.BOM.html");
            
        };
        
    };
    
</script>
```
# 定时器
```js
<script type="text/javascript">
    
    window.onload = function(){
        
        //获取count
        var count = document.getElementById("count");
        
        //使count中的内容，自动切换
        /*
            * JS的程序的执行速度是非常非常快的
            * 	如果希望一段程序，可以每间隔一段时间执行一次，可以使用定时调用
            */
        /*for(var i=0 ; i<10000 ; i++){
            count.innerHTML = i;
            
            alert("hello");
        }*/
        
        /*
            * setInterval()
            * 	- 定时调用
            * 	- 可以将一个函数，每隔一段时间执行一次
            * 	- 参数：
            * 		1.回调函数，该函数会每隔一段时间被调用一次
            * 		2.每次调用间隔的时间，单位是毫秒
            * 
            * 	- 返回值：
            * 		返回一个Number类型的数据
            * 		这个数字用来作为定时器的唯一标识
            */
        var num = 1;
        
        var timer = setInterval(function(){
            
            count.innerHTML = num++;
            
            if(num == 11){
                //关闭定时器
                clearInterval(timer);
            }
            
        },1000);
        
        //console.log(timer);
        
        //clearInterval()可以用来关闭一个定时器
        //方法中需要一个定时器的标识作为参数，这样将关闭标识对应的定时器
        //clearInterval(timer);
        
    };
    
    
</script>
```
## 延时调用
```js
    /*
    * 延时调用，
    * 	延时调用一个函数不马上执行，而是隔一段时间以后在执行，而且只会执行一次
    * 
    * 延时调用和定时调用的区别，定时调用会执行多次，而延时调用只会执行一次
    * 
    * 延时调用和定时调用实际上是可以互相代替的，在开发中可以根据自己需要去选择
    */
    var timer = setTimeout(function(){
        console.log(num++);
    },3000);

    //使用clearTimeout()来关闭一个延时调用
    clearTimeout(timer);
```
## 轮播图
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <script type="text/javascript">
        
        window.onload = function(){
            
            /*
                * 使图片可以自动切换
                */
            
            //获取img标签
            var img1 = document.getElementById("img1");
            
            //创建一个数组来保存图片的路径
            var imgArr = ["img/1.jpg","img/2.jpg","img/3.jpg","img/4.jpg","img/5.jpg"];
            
            //创建一个变量，用来保存当前图片的索引
            var index = 0;
            
            //定义一个变量，用来保存定时器的标识
            var timer;
            
            //为btn01绑定一个单击响应函数
            var btn01 = document.getElementById("btn01");
            btn01.onclick = function(){
                
                /*
                    * 目前，我们每点击一次按钮，就会开启一个定时器，
                    * 	点击多次就会开启多个定时器，这就导致图片的切换速度过快，
                    * 	并且我们只能关闭最后一次开启的定时器
                    */
                
                //在开启定时器之前，需要将当前元素上的其他定时器关闭
                clearInterval(timer);
                
                /*
                    * 开启一个定时器，来自动切换图片
                    */
                timer = setInterval(function(){
                    //使索引自增
                    index++;
                    //判断索引是否超过最大索引
                    /*if(index >= imgArr.length){
                        //则将index设置为0
                        index = 0;
                    }*/
                    index %= imgArr.length;
                    //修改img1的src属性
                    img1.src = imgArr[index];
                    
                },1000);
            };
            
            //为btn02绑定一个单击响应函数
            var btn02 = document.getElementById("btn02");
            btn02.onclick = function(){
                //点击按钮以后，停止图片的自动切换，关闭定时器
                /*
                    * clearInterval()可以接收任意参数，
                    * 	如果参数是一个有效的定时器的标识，则停止对应的定时器
                    * 	如果参数不是一个有效的标识，则什么也不做
                    */
                clearInterval(timer);
                
            };
            
            
        };
        
    </script>
</head>
<body>
    
    <img id="img1" src="img/1.jpg"/>
    <br /><br />
    <button id="btn01">开始</button>
    <button id="btn02">停止</button>
</body>
</html>
```
## 移动div训练
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <style type="text/css">
        #box1{
            width: 100px;
            height: 100px;
            background-color: red;
            position: absolute;
        }

    </style>
    <script type="text/javascript">
        
        //使div可以根据不同的方向键向不同的方向移动
        /*
            * 按左键，div向左移
            * 按右键，div向右移
            * 。。。
            */
        window.onload = function(){
            
            //定义一个变量，来表示移动的速度
            var speed = 10;
            
            //创建一个变量表示方向
            //通过修改dir来影响移动的方向
            var dir = 0;
            
            //开启一个定时器，来控制div的移动
            setInterval(function(){
                /*
                    * 37 左
                    * 38 上
                    * 39 右
                    * 40 下
                    */
                switch(dir){
                    case 37:
                        //alert("向左"); left值减小
                        box1.style.left = box1.offsetLeft - speed + "px";
                        break;
                    case 39:
                        //alert("向右");
                        box1.style.left = box1.offsetLeft + speed + "px";
                        break;
                    case 38:
                        //alert("向上");
                        box1.style.top = box1.offsetTop - speed + "px";
                        break;
                    case 40:
                        //alert("向下");
                        box1.style.top = box1.offsetTop + speed + "px";
                        break;
                }
            },30);

            //为document绑定一个按键按下的事件
            document.onkeydown = function(event){
                event = event || window.event;
                
                //当用户按了ctrl以后，速度加快
                if(event.ctrlKey){
                    speed = 500;
                }else{
                    speed = 10;
                }
                
                //使dir等于按键的值
                dir = event.keyCode;
            };
            
            //当按键松开时，div不再移动
            document.onkeyup = function(){
                //设置方向为0
                dir = 0;
            };
            
        };

    </script>
</head>
<body>
    <div id="box1"></div>
</body>
</html>

```

## 轮播图(高级)
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    
    <style type="text/css">
        *{
            margin: 0;
            padding: 0;
        }
        
        /*
            * 设置outer的样式
            */
        #outer{
            /*设置宽和高*/
            width: 520px;
            height: 333px;
            /*居中*/
            margin: 50px auto;
            /*设置背景颜色*/
            background-color: greenyellow;
            /*设置padding*/
            padding: 10px 0;
            /*开启相对定位*/
            position: relative;
            /*裁剪溢出的内容*/
            overflow: hidden;
        }
        
        /*设置imgList*/
        #imgList{
            /*去除项目符号*/
            list-style: none;
            /*设置ul的宽度*/
            /*width: 2600px;*/
            /*开启绝对定位*/
            position: absolute;
            /*设置偏移量*/
            /*
                * 每向左移动520px，就会显示到下一张图片
                */
            left: 0px;
        }
        
        /*设置图片中的li*/
        #imgList li{
            /*设置浮动*/
            float: left;
            /*设置左右外边距*/
            margin: 0 10px;
        }
        
        /*设置导航按钮*/
        #navDiv{
            /*开启绝对定位*/
            position: absolute;
            /*设置位置*/
            bottom: 15px;
            /*设置left值
                outer宽度  520
                navDiv宽度 25*5 = 125
                    520 - 125 = 395/2 = 197.5
                * */
            /*left: 197px;*/
        }
        
        #navDiv a{
            /*设置超链接浮动*/
            float: left;
            /*设置超链接的宽和高*/
            width: 15px;
            height: 15px;
            /*设置背景颜色*/
            background-color: red;
            /*设置左右外边距*/
            margin: 0 5px;
            /*设置透明*/
            opacity: 0.5;
            /*兼容IE8透明*/
            filter: alpha(opacity=50);
        }
        
        /*设置鼠标移入的效果*/
        #navDiv a:hover{
            background-color: black;
        }
    </style>
    
    <!--引用工具-->
    <script type="text/javascript" src="js/tools.js"></script>
    <script type="text/javascript">
        window.onload = function(){
            //获取imgList
            var imgList = document.getElementById("imgList");
            //获取页面中所有的img标签
            var imgArr = document.getElementsByTagName("img");
            //设置imgList的宽度
            imgList.style.width = 520*imgArr.length+"px";
            
            
            /*设置导航按钮居中*/
            //获取navDiv
            var navDiv = document.getElementById("navDiv");
            //获取outer
            var outer = document.getElementById("outer");
            //设置navDiv的left值
            navDiv.style.left = (outer.offsetWidth - navDiv.offsetWidth)/2 + "px";
            
            //默认显示图片的索引
            var index = 0;
            //获取所有的a
            var allA = document.getElementsByTagName("a");
            //设置默认选中的效果
            allA[index].style.backgroundColor = "black";
            
            /*
                点击超链接切换到指定的图片
                    点击第一个超链接，显示第一个图片
                    点击第二个超链接，显示第二个图片
                * */
            
            //为所有的超链接都绑定单击响应函数
            for(var i=0; i<allA.length ; i++){
                
                //为每一个超链接都添加一个num属性
                allA[i].num = i;
                
                //为超链接绑定单击响应函数
                allA[i].onclick = function(){
                    
                    //关闭自动切换的定时器
                    clearInterval(timer);
                    //获取点击超链接的索引,并将其设置为index
                    index = this.num;
                    
                    //切换图片
                    /*
                        * 第一张  0 0
                        * 第二张  1 -520
                        * 第三张  2 -1040
                        */
                    //imgList.style.left = -520*index + "px";
                    //设置选中的a
                    setA();
                    
                    //使用move函数来切换图片
                    move(imgList , "left" , -520*index , 20 , function(){
                        //动画执行完毕，开启自动切换
                        autoChange();
                    });
                    
                };
            }
            
            
            //开启自动切换图片
            autoChange();
            
            
            //创建一个方法用来设置选中的a
            function setA(){
                
                //判断当前索引是否是最后一张图片
                if(index >= imgArr.length - 1){
                    //则将index设置为0
                    index = 0;
                    
                    //此时显示的最后一张图片，而最后一张图片和第一张是一摸一样
                    //通过CSS将最后一张切换成第一张
                    imgList.style.left = 0;
                }
                
                //遍历所有a，并将它们的背景颜色设置为红色
                for(var i=0 ; i<allA.length ; i++){
                    allA[i].style.backgroundColor = "";
                }
                
                //将选中的a设置为黑色
                allA[index].style.backgroundColor = "black";
            };
            
            //定义一个自动切换的定时器的标识
            var timer;
            //创建一个函数，用来开启自动切换图片
            function autoChange(){
                
                //开启一个定时器，用来定时去切换图片
                timer = setInterval(function(){
                    
                    //使索引自增
                    index++;
                    
                    //判断index的值
                    index %= imgArr.length;
                    
                    //执行动画，切换图片
                    move(imgList , "left" , -520*index , 20 , function(){
                        //修改导航按钮
                        setA();
                    });
                    
                },3000);
            }
        };
        
    </script>
</head>
<body>
    <!-- 创建一个外部的div，来作为大的容器 -->
    <div id="outer">
        <!-- 创建一个ul，用于放置图片 -->
        <ul id="imgList">
            <li><img src="img/1.jpg"/></li>
            <li><img src="img/2.jpg"/></li>
            <li><img src="img/3.jpg"/></li>
            <li><img src="img/4.jpg"/></li>
            <li><img src="img/5.jpg"/></li>
            <li><img src="img/1.jpg"/></li>
        </ul>
        <!--创建导航按钮-->
        <div id="navDiv">
            <a href="javascript:;"></a>
            <a href="javascript:;"></a>
            <a href="javascript:;"></a>
            <a href="javascript:;"></a>
            <a href="javascript:;"></a>
        </div>
    </div>
</body>
</html>

```
 		
