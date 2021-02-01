# 二级菜单
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>二级菜单</title>
    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
            list-style-type: none;
        }
        
        a,img {
            border: 0;
            text-decoration: none;
        }
        
        body {
            font: 12px/180% Arial, Helvetica, sans-serif, "新宋体";
        }
    </style>

    <link rel="stylesheet" type="text/css" href="css/sdmenu.css" />
    
    <script type="text/javascript" src="js/tools.js"></script>
    <script type="text/javascript">
        window.onload = function(){
            
            /*
                * 我们的每一个菜单都是一个div
                * 	当div具有collapsed这个类时，div就是折叠的状态
                * 	当div没有这个类是，div就是展开的状态
                */
            
            /*
                * 点击菜单，切换菜单的显示状态
                */
            //获取所有的class为menuSpan的元素
            var menuSpan = document.querySelectorAll(".menuSpan");
            
            //定义一个变量，来保存当前打开的菜单
            var openDiv = menuSpan[0].parentNode;
            
            //为span绑定单击响应函数
            for(var i=0 ; i<menuSpan.length ; i++){
                menuSpan[i].onclick = function(){
                    
                    //this代表我当前点击的span
                    //获取当前span的父元素
                    var parentDiv = this.parentNode;
                    
                    //切换菜单的显示状态
                    toggleMenu(parentDiv);
                    
                    
                    //判断openDiv和parentDiv是否相同
                    if(openDiv != parentDiv  && !hasClass(openDiv , "collapsed")){
                        //打开菜单以后，应该关闭之前打开的菜单
                        //为了可以统一处理动画过渡效果，我们希望在这将addClass改为toggleClass
                        //addClass(openDiv , "collapsed");
                        //此处toggleClass()不需要有移除的功能
                        //toggleClass(openDiv , "collapsed");
                        //切换菜单的显示状态
                        toggleMenu(openDiv);
                    }
                    
                    //修改openDiv为当前打开的菜单
                    openDiv = parentDiv;
                    
                };
            }
            
            /*
                * 用来切换菜单折叠和显示状态
                */
            function toggleMenu(obj){
                //在切换类之前，获取元素的高度
                var begin = obj.offsetHeight;
                
                //切换parentDiv的显示
                toggleClass(obj , "collapsed");
                
                //在切换类之后获取一个高度
                var end = obj.offsetHeight;
                
                //console.log("begin = "+begin +" , end = "+end);
                //动画效果就是将高度从begin向end过渡
                //将元素的高度重置为begin
                obj.style.height = begin + "px";
                
                //执行动画，从bengin向end过渡
                move(obj,"height",end,10,function(){
                    //动画执行完毕，内联样式已经没有存在的意义了，删除之
                    obj.style.height = "";
                });
            }
        };
    </script>
</head>
<body>

    <div id="my_menu" class="sdmenu">
        <div>
            <span class="menuSpan">在线工具</span>
            <a href="#">图像优化</a>
            <a href="#">收藏夹图标生成器</a>
            <a href="#">邮件</a>
            <a href="#">htaccess密码</a>
            <a href="#">梯度图像</a>
            <a href="#">按钮生成器</a>
        </div>
        <div class="collapsed">
            <span class="menuSpan">支持我们</span>
            <a href="#">推荐我们</a>
            <a href="#">链接我们</a>
            <a href="#">网络资源</a>
        </div>
        <div class="collapsed">
            <span class="menuSpan">合作伙伴</span>
            <a href="#">JavaScript工具包</a>
            <a href="#">CSS驱动</a>
            <a href="#">CodingForums</a>
            <a href="#">CSS例子</a>
        </div>
        <div class="collapsed">
            <span class="menuSpan">测试电流</span>
            <a href="#">Current or not</a>
            <a href="#">Current or not</a>
            <a href="#">Current or not</a>
            <a href="#">Current or not</a>
        </div>
    </div>
</body>
</html>
```
# json
```js
<script type="text/javascript">
    
    /*
    * JSON
    * 	- JS中的对象只有JS自己认识，其他的语言都不认识
    * 	- JSON就是一个特殊格式的字符串，这个字符串可以被任意的语言所识别，
    * 		并且可以转换为任意语言中的对象，JSON在开发中主要用来数据的交互
    * 	- JSON
    * 		- JavaScript Object Notation JS对象表示法
    * 		- JSON和JS对象的格式一样，只不过JSON字符串中的属性名必须加双引号
    * 			其他的和JS语法一致
    * 		JSON分类：
    * 			1.对象 {}
    * 			2.数组 []
    * 
    * 		JSON中允许的值：
    * 			1.字符串
    * 			2.数值
    * 			3.布尔值
    * 			4.null
    * 			5.对象
    * 			6.数组
    */
    
    //创建一个对象
    
    
    var arr = '[1,2,3,"hello",true]';
    
    var obj2 = '{"arr":[1,2,3]}';
    
    var arr2 ='[{"name":"孙悟空","age":18,"gender":"男"},{"name":"孙悟空","age":18,"gender":"男"}]';
    
    /*
        * 将JSON字符串转换为JS中的对象
        * 	在JS中，为我们提供了一个工具类，就叫JSON
        * 	这个对象可以帮助我们将一个JSON转换为JS对象，也可以将一个JS对象转换为JSON
        */
    
    var json = '{"name":"孙悟空","age":18,"gender":"男"}';
    
    /*
        * json --> js对象
        * 	 JSON.parse()
        * 		- 可以将以JSON字符串转换为js对象
        * 		- 它需要一个JSON字符串作为参数，会将该字符串转换为JS对象并返回
        */
    
    var o = JSON.parse(json);
    var o2 = JSON.parse(arr);
    
    //console.log(o.gender);
    //console.log(o2[1]);
    
    var obj3 = {name:"猪八戒" , age:28 , gender:"男"};
    
    
    /*
        * JS对象 ---> JSON
        * 	JSON.stringify()
        * 		- 可以将一个JS对象转换为JSON字符串
        * 		- 需要一个js对象作为参数，会返回一个JSON字符串
        */
    
    var str = JSON.stringify(obj3);
    //console.log(str);
    
    /*
        * JSON这个对象在IE7及以下的浏览器中不支持，所以在这些浏览器中调用时会报错
        */
    
    
    var str3 = '{"name":"孙悟空","age":18,"gender":"男"}';
    
    JSON.parse(str3);
</script>
```
# eval()函数
```js
<script type="text/javascript">
		
    var str = '{"name":"孙悟空","age":18,"gender":"男"}';
    
    /*
        * eval()
        * 	- 这个函数可以用来执行一段字符串形式的JS代码，并将执行结果返回
        * 	- 如果使用eval()执行的字符串中含有{},它会将{}当成是代码块
        * 		如果不希望将其当成代码块解析，则需要在字符串前后各加一个()
        * 
        * 	- eval()这个函数的功能很强大，可以直接执行一个字符串中的js代码，
        * 		但是在开发中尽量不要使用，首先它的执行性能比较差，然后它还具有安全隐患
        */
    
    var str2 = "alert('hello');";
    
    var obj = eval("("+str+")");
    
    //console.log(obj);
    
    
    
</script>
```