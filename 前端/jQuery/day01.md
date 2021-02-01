# jQuery核心函数
 * 简称: jQuery函数($/jQuery)
 * jQuery库向外直接暴露的就是$/jQuery
 * 引入jQuery库后, 直接使用$即可
 * 当函数用: $(xxx)
 * 当对象用: $.xxx()
# jQuery核心对象
 * 简称: jQuery对象
 * 得到jQuery对象: 执行jQuery函数返回的就是jQuery对象
 * 使用jQuery对象: $obj.xxx()

# jQuery使用方式
## 作为一般函数调用: $(param)
    1). 参数为函数 : 当DOM加载完成后，执行此回调函数

    2). 参数为选择器字符串: 查找所有匹配的标签, 并将它们封装成jQuery对象
  
    3). 参数为DOM对象: 将dom对象封装成jQuery对象
  
    4). 参数为html标签字符串 (用得少): 创建标签对象并封装成jQuery对象
## 作为对象使用: $.xxx()
    1). $.each() : 隐式遍历数组

    2). $.trim() : 去除两端的空格
### 例子
```js
<script type="text/javascript">

  /*
   需求1. 点击按钮: 显示按钮的文本, 显示一个新的输入框
   需求2. 遍历输出数组中所有元素值
   需求3. 去掉"  my atguigu  "两端的空格
   */
  /*需求1. 点击按钮: 显示按钮的文本, 显示一个新的输入框*/
  //1). 参数为函数 : 当DOM加载完成后，执行其中的函数     回调函数
  $(function () {
    //2). 参数为选择器(selector)字符串: 查找所有匹配的标签, 并将它们封装成jQuery对象
    var $btn = $("#btn")
    $btn.click(function () {
      //显示按钮的文本
      //this就是发生事件的dom元素对象(也就是button)
      //3). 参数为DOM对象: 将dom对象封装成jQuery对象
      var text = $(this).html()
      alert(text)
      //显示一个新的输入框
      //4). 参数为html标签字符串 (用得少): 创建标签对象并封装成jQuery对象
      $('<input type="text" name="msg3" /><br />').appendTo('div')
    })
  })

  /*需求2. 遍历输出数组中所有元素值*/
  var arr = [123, 'atguigu', true]
  // 1). $.each() : 隐式遍历数组
  $.each(arr, function (index, item) {
    console.log('第' + (index + 1) + '个元素的值为' + item)
  })

  /*需求3. 去掉"  my atguigu  "两端的空格*/
  var str = "  my atguigu  "
  // 2). $.trim() : 去除两端的空格
  console.log(str.trim() === 'my atguigu')
  console.log($.trim(str) === 'my atguigu') //true

</script>
```
# jQuery对象
 * jQuery对象是一个包含所有匹配的任意多个dom元素的伪数组对象
## 对象的基本行为
  * size()/length: 包含的**DOM元素**个数
  * [index]/get(index): 得到对应位置的**DOM元素**
  * each(): 遍历包含的所有**DOM元素**
  * index(): 得到在所在兄弟元素中的下标
### 例子
```js
<body>
<button>测试一</button>
<button>测试二</button>
<button id="btn3">测试三</button>
<button>测试四</button>

<script type="text/javascript">
  /*
   需求:
   需求1. 统计一共有多少个按钮
   需求2. 取出第2个button的文本
   需求3. 输出所有button标签的文本
   需求4. 输出'测试三'按钮是所有按钮中的第几个
   */
  $(function () {
    var $btns = $('button')
    console.log($btns)
    // 需求1. 统计一共有多少个按钮
      /*size()/length: 包含的DOM元素个数*/
    console.log($btns.size(), $btns.length)

    // 需求2. 取出第2个button的文本
      /*[index]/get(index): 得到对应位置的DOM元素*/
    console.log($btns[1].innerHTML, $btns.get(1).innerHTML)

    // 需求3. 输出所有button标签的文本
      /*each(): 遍历包含的所有DOM元素*/
    $btns.each(function () {
      console.log(this.innerHTML)
    })

    // 需求4. 输出'测试三'按钮是所有按钮中的第几个
      /*index(): 得到在所在兄弟元素中的下标*/
    console.log($('#btn3').index())
  })
</script>
</body>
```

# 选择器
1. 是什么?
    - 有特定格式的字符串
2. 作用
    - 用来查找特定页面元素
## 基本选择器
```html
    - #id : id选择器
    - element : 元素选择器
    - .class : 属性选择器
    - * : 任意标签
    - selector1,selector2,selectorN : 取多个选择器的并集(组合选择器)
    - selector1selector2selectorN : 取多个选择器的交集(相交选择器)
```
### 例子——基本选择器
```js
<script type="text/javascript">
  /*
   需求:
   1. 选择id为div1的元素
   2. 选择所有的div元素
   3. 选择所有class属性为box的元素
   4. 选择所有的div和span元素
   5. 选择所有class属性为box的div元素
   */
  $(function () {
// 1. 选择id为div1的元素
    $('#div1').css('background', 'red')
// 2. 选择所有的div元素
    $('div').css('background', 'red')
// 3. 选择所有class属性为box的元素
    $('.box').css('background', 'red')
// 4. 选择所有的div和span元素
    $('div，span').css('background', 'red')
// 5. 选择所有class属性为box的div元素
    $('div.box').css('background', 'red')  //不能写.boxdiv
  })
</script>
```