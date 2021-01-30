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