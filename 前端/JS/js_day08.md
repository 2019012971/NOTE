# DOM对CSS的操作
	- 读取和修改内联样式
		- 使用style属性来操作元素的内联样式
		- 读取内联样式：
			语法：元素.style.样式名
			- 例子：
				元素.style.width
				元素.style.height
				- 注意：如果样式名中带有-，则需要将样式名修改为驼峰命名法
					将-去掉，然后-后的字母改大写
				- 比如：background-color --> backgroundColor
						border-width ---> borderWidth
						
		- 修改内联样式：
			语法：元素.style.样式名 = 样式值
			- 通过style修改的样式都是内联样式，由于内联样式的优先级比较高，
				所以我们通过JS来修改的样式，往往会立即生效，
				但是如果样式中设置了!important，则内联样式将不会生效。
				
	- 读取元素的当前样式
		- 正常浏览器
			- 使用getComputedStyle()
			- 这个方法是window对象的方法，可以返回一个对象，这个对象中保存着当前元素生效样式
			- 参数：
				1.要获取样式的元素
				2.可以传递一个伪元素，一般传null
			- 例子：
				获取元素的宽度
					getComputedStyle(box , null)["width"];
			- 通过该方法读取到样式都是只读的不能修改

		- IE8
			- 使用currentStyle
			- 语法：
				元素.currentStyle.样式名
			- 例子：
				box.currentStyle["width"]
			- 通过这个属性读取到的样式是只读的不能修改
## 兼容性例子：
```js
function getStyle(obj,name) {
    //判断有没有getComputedStyle属性
    if (window.getComputedStyle)
        return getComputedStyle(obj,null)[name];
    else
        return obj.currentStyle[name];
}
```

# 其他的样式相关的属性
    注意：以下样式都是只读的

    clientHeight
        - 元素的可见高度，指元素的内容区和内边距的高度
    clientWidth
        - 元素的可见宽度，指元素的内容区和内边距的宽度
    offsetHeight
        - 整个元素的高度，包括内容区、内边距、边框
    offfsetWidth
        - 整个元素的宽度，包括内容区、内边距、边框
    offsetParent
        - 当前元素的定位父元素
        - 离他最近的开启了定位的祖先元素，如果所有的元素都没有开启定位，则返回body
    offsetLeft
    offsetTop
        - 当前元素和定位父元素之间的偏移量
        - offsetLeft水平偏移量  offsetTop垂直偏移量
    
    scrollHeight
    scrollWidth
        - 获取元素滚动区域的高度和宽度
    
    scrollTop
    scrollLeft
        - 获取元素垂直和水平滚动条滚动的距离
        
    判断滚动条是否滚动到底
        - 垂直滚动条
            scrollHeight - scrollTop = clientHeight
            
        - 水平滚动	
            scrollWidth - scrollLeft = clientWidth
## 滚动条练习
```js
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <style type="text/css">
        
        #info{
            width: 300px;
            height: 500px;
            background-color: #bfa;
            overflow: auto;
        }
        
    </style>
    <script type="text/javascript">
        window.onload = function(){
            
            /*
                * 当垂直滚动条滚动到底时使表单项可用
                * onscroll
                * 	- 该事件会在元素的滚动条滚动时触发
                */
            
            //获取id为info的p元素
            var info = document.getElementById("info");
            //获取两个表单项
            var inputs = document.getElementsByTagName("input");
            //为info绑定一个滚动条滚动的事件
            info.onscroll = function(){
                
                //检查垂直滚动条是否滚动到底
                if(info.scrollHeight - info.scrollTop == info.clientHeight){
                    //滚动条滚动到底，使表单项可用
                    /*
                        * disabled属性可以设置一个元素是否禁用，
                        * 	如果设置为true，则元素禁用
                        * 	如果设置为false，则元素可用
                        */
                    inputs[0].disabled = false;
                    inputs[1].disabled = false;
                }
                
            };
            
        };
        
        
    </script>
</head>
<body>
    <h3>欢迎亲爱的用户注册</h3>
    <p id="info">
        亲爱的用户，请仔细阅读以下协议，如果你不仔细阅读你就别注册
    </p>
    <!-- 如果为表单项添加disabled="disabled" 则表单项将变成不可用的状态 -->
    <input type="checkbox" disabled="disabled" />我已仔细阅读协议，一定遵守
    <input type="submit" value="注册" disabled="disabled" />
</body>
</html>
```
		
# 事件（Event）
	- 事件对象
	- 当响应函数被调用时，浏览器每次都会将一个事件对象作为实参传递进响应函数中，
		这个事件对象中封装了当前事件的相关信息，比如：鼠标的坐标，键盘的按键，鼠标的按键，滚轮的方向。。
	- 可以在响应函数中定义一个形参，来使用事件对象，但是在IE8以下浏览器中事件对象没有做完实参传递，而是作为window对象的属性保存
		- 例子：
			元素.事件 = function(event){
				event = event || window.event;
				
			};
			
			元素.事件 = function(e){
				e = e || event;
				
			};
			
	- 事件的冒泡（Bubble）
	- 事件的冒泡指的是事件向上传导，当后代元素上的事件被触发时，将会导致其祖先元素上的同类事件也会触发。
	- 事件的冒泡大部分情况下都是有益的，如果需要取消冒泡，则需要使用事件对象来取消
	- 可以将事件对象的cancelBubble设置为true，即可取消冒泡
		- 例子：
				元素.事件 = function(event){
					event = event || window.event;
					event.cancelBubble = true;
				};