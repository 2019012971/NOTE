# 事件的绑定
## 事件绑定(2种)：
  * eventName(function(){})
    绑定对应事件名的监听,	例如：$('#div').click(function(){});
  * on(eventName, funcion(){})
    通用的绑定事件监听, 例如：$('#div').on('click', function(){})
  * 优缺点:
    eventName: 编码方便, ~~但只能加一个监听, 且有的事件监听不支持~~
    on: 编码不方便, 可以同时添加多个监听, 且更通用
## 事件解绑：
  * off(eventName)
### 面试题
<strong>区别mouseover与mouseenter?</strong>
 * mouseover: 在移入**子元素**时也会触发, 对应mouseout
 * mouseenter: 只在移入当前元素时才触发, 对应mouseleave
 * hover()使用的就是mouseenter()和mouseleave()

<strong>区别on('eventName1 eventName2', fun)与eventName(fun)</strong>
 * 都可以多次绑定同一个事件且不会取消前一个事件的绑定(区别于原生js的onclick方法)
 * on('eventName1 eventName2', fun): 通用, 但编码麻烦
 * eventName(fun): 编码简单, 但有的事件没有对应的方法

## 事件的坐标
  * event.clientX, event.clientY  相对于视口(浏览器显示的窗口)的左上角
  * event.pageX, event.pageY  相对于(整个html)页面的左上角
  * event.offsetX, event.offsetY 相对于事件元素(如button、div)左上角

## 事件相关处理
  * 停止事件冒泡 : event.stopPropagation()
  * 阻止事件默认行为 : event.preventDefault()

# 事件委派（重点）
## 事件委托(委派/代理):
  * 将多个子元素(li)的事件监听委托给父辈元素(ul)处理
  * 监听回调是加在了父辈元素上
  * 当操作任何一个子元素(li)时, 事件会冒泡到父辈元素(ul)
  * 父辈元素不会直接处理事件, 而是根据event.target得到发生事件的子元素(li), 通过这个子元素调用事件回调函数
## 事件委托的双方:
  * 委托方: 业主  li
  * 被委托方: 中介  ul
## 使用事件委托的好处
  * 添加新的子元素, 自动有事件响应处理
  * 减少事件监听的数量: n==>1
## jQuery的事件委托API
  * 设置事件委托: $(parentSelector).delegate(childrenSelector, eventName, callback)
  * 移除事件委托: $(parentSelector).undelegate(eventName)
### 例子
```html
<head>
  <meta charset="UTF-8">
  <title>20_事件委托2</title>
</head>

<body>
<ul>
  <li>1111</li>
  <li>2222</li>
  <li>3333</li>
  <li>4444</li>
</ul>

<li>22222</li>
<br>
<button id="btn1">添加新的li</button>
<button id="btn2">删除ul上的事件委托的监听器</button>
<script src="js/jquery-1.10.1.js"></script>
<script>
  // 设置事件委托
  $('ul').delegate('li', 'click', function () {
    // console.log(this)
    this.style.background = 'red'
  })

  $('#btn1').click(function () {
    $('ul').append('<li>新增的li....</li>')
  })

  $('#btn2').click(function () {
    // 移除事件委托
    $('ul').undelegate('click')
  })

</script>
</body>
```

# 动画效果
## 淡入淡出: 不断改变元素的透明度(opacity)来实现的
1. fadeIn(): 带动画的显示
2. fadeOut(): 带动画隐藏
3. fadeToggle(): 带动画切换显示/隐藏
## 滑动动画: 不断改变元素的高度实现
1. slideDown(): 带动画的展开
2. slideUp(): 带动画的收缩
3. slideToggle(): 带动画的切换展开/收缩
## 显示隐藏，默认没有动画, 动画(opacity/height/width)
1. show(): (不)带动画的显示
2. hide(): (不)带动画的隐藏
3. toggle(): (不)带动画的切换显示/隐藏
## 自定义动画
jQuery动画本质 : 在指定时间内不断改变元素样式值来实现的
1. animate(): 自定义动画效果的动画
2. stop(): 停止动画
### 自定义动画例子
```js
<script type="text/javascript">

  var $div1 = $('.div1')

  /*
   1. 逐渐扩大
     1). 宽/高都扩为200px
     2). 宽先扩为200px, 高后扩为200px
   */
  $('#btn1').click(function () {
    /*
    $div1.animate({
      width: 200,
      height: 200
    }, 1000)
    */
    $div1
      .animate({
        width: 200
      }, 1000)
      .animate({
        height: 200
      }, 1000)

  })

  /*
   2. 移动到指定位置
     1).移动到(500, 100)处
     2).移动到(100, 20)处
   */
  $('#btn2').click(function () {
    // 1).移动到(500, 100)处
    /*
    $div1.animate({ // 向右下移动
      left: 500,
      top: 100
    }, 1000)
    */

    // 2).移动到(100, 20)处
    $div1.animate({ // 向左上移动
      left: 100,  // 300
      top: 20  // 50
    }, 1000)
  })

  /*
   3.移动指定的距离
     1). 移动距离为(100, 50)
     2). 移动距离为(-100, -20)
   */
  $('#btn3').click(function () {
    // 1). 移动距离为(100, 50)
    /*$div1.animate({
      left: '+=100',
      top: '+=50'
    }, 1000)*/

    // 2). 移动距离为(-100, -20)
    $div1.animate({
      left: '-=100',
      top: '-=20'
    }, 3000)
  })

  $('#btn4').click(function () {
    $div1.stop()
  })

</script>
```
# 扩展插件
1. 扩展jQuery的工具方法
  $.extend(object)
2. 扩展jQuery对象的方法
  $.fn.extend(object)
## 例子
```js
//my_jQuery-plugin.js
(function () {

  // 扩展$的方法
  $.extend({
    min: function (a, b) {
      return a < b ? a : b
    },
    max: function (a, b) {
      return a > b ? a : b
    },
    leftTrim: function (str) {
      return str.replace(/^\s+/, '')
    },
    rightTrim: function (str) {
      return str.replace(/\s+$/, '')
    }
  })

  // 扩展jQuery对象的方法
  $.fn.extend({
    checkAll: function () {
      this.prop('checked', true) // this是jQuery对象
    },
    unCheckAll: function () {
      this.prop('checked', false)
    },
    reverseCheck: function () {
      // this是jQuery对象
      this.each(function () {
        // this是dom元素
        this.checked = !this.checked
      })
    }
  })

})()

//html
<script src="js/jquery-1.10.1.js" type="text/javascript"></script>
<script type="text/javascript" src="js/my_jQuery-plugin.js"></script>
<script type="text/javascript">
  /*
   需求：
   1. 给 $ 添加4个工具方法:
     * min(a, b) : 返回较小的值
     * max(c, d) : 返回较大的值
     * leftTrim() : 去掉字符串左边的空格
     * rightTrim() : 去掉字符串右边的空格
   2. 给jQuery对象 添加3个功能方法:
     * checkAll() : 全选
     * unCheckAll() : 全不选
     * reverseCheck() : 全反选
   */
  console.log($.min(3, 5), $.max(3, 5))
  var string = '   my atguigu    '
  console.log('-----' + $.leftTrim(string) + '-----')
  console.log('-----' + $.rightTrim(string) + '-----')

  var $items = $(':checkbox[name=items]')
  $('#checkedAllBtn').click(function () {
    $items.checkAll()
  })
  $('#checkedNoBtn').click(function () {
    $items.unCheckAll()
  })
  $('#reverseCheckedBtn').click(function () {
    $items.reverseCheck()
  })
</script>
```
# 多库共存
<strong>问题 : 如果有2个库都有$, 就存在冲突</strong>

 * 解决 : jQuery库可以释放$的使用权, 让另一个库可以正常使用, 此时jQuery库只能使用jQuery了
 * API : jQuery.noConflict()
# onload与ready
<strong>区别: window.onload与 $(document).ready()</strong>
  * window.onload（相当于$().on('load',function(){})）
    * 包括页面的图片加载完后才会回调(晚)
    * 只能有一个监听回调
  * $(document).ready()
    * 等同于: $(function(){})
    * 页面加载完就回调(早,可能图片都没加载完)
    * 可以有多个监听回调