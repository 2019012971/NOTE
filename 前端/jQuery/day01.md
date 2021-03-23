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
## 层次选择器: 查找子元素, 后代元素, 兄弟元素的选择器

1. ancestor descendant
  在给定的祖先元素下匹配所有的后代元素

2. parent>child
  在给定的父元素下匹配所有的子元素

3. prev+next
  匹配所有紧接在 prev 元素后的 next 元素

4. prev~siblings
  匹配 prev 元素之后的所有 siblings 元素
### 例子：
```js
<script type="text/javascript">
  /*
   需求:
   1. 选中ul下所有的的span
   2. 选中ul下所有的子元素span
   3. 选中class为box的下一个li
   4. 选中ul下的class为box的元素后面的所有兄弟元素
   */
  // 1. 选中ul下所有的的span
  $('ul span').css('background', 'yellow')

  // 2. 选中ul下所有的子元素span
  $('ul>span').css('background', 'yellow')

  // 3. 选中class为box的下一个li
  $('.box+li').css('background', 'yellow')

  // 4. 选中ul下的class为box的元素后面的所有兄弟元素
  $('ul .box~*').css('background', 'yellow')
</script>
```

## 过滤选择器
* 在原有匹配元素中筛选出其中一些
  * :first
  * :last
  * :eq(index)
  * :lt
  * :gt
  * :odd
  * :even
  * :not(selector)
  * :hidden
  * :visible
  * [attrName]
  * [attrName=value]
### 例子
```js
<script type="text/javascript">

  /*
   需求:
   1. 选择第一个div
   2. 选择最后一个class为box的元素
   3. 选择所有class属性不为box的div
   4. 选择第二个和第三个li元素
   5. 选择内容为BBBBB的li
   6. 选择隐藏的li
   7. 选择有title属性的li元素
   8. 选择所有属性title为hello的li元素
   */
  //1. 选择第一个div
  $('div:first').css('background', 'red')

  //2. 选择最后一个class为box的元素
  $('.box:last').css('background', 'red')

  //3. 选择所有class属性不为box的div
  $('div:not(.box)').css('background', 'red')  //没有class属性也可以

  //4. 选择第二个和第三个li元素
  // $('li:gt(0):lt(2)').css('background', 'red') // 多个过滤选择器不是同时执行, 而是依次
  $('li:lt(3):gt(0)').css('background', 'red')

  //5. 选择内容为BBBBB的li
  $('li:contains("BBBBB")').css('background', 'red')

  //6. 选择隐藏的li
  console.log($('li:hidden').length, $('li:hidden')[0])

  //7. 选择有title属性的li元素
  $('li[title]').css('background', 'red')

  //8. 选择所有属性title为hello的li元素
  $('li[title="hello"]').css('background', 'red')

</script>
```
## 表单选择器
* 表单
      * :input
      * :text
      * :checkbox
      * :radio
      * :checked: 选中的
### 例子
```js
<script type="text/javascript">
  /*
   需求:
   1. 选择不可用的文本输入框
   2. 显示选择爱好 的个数
   3. 显示选择的城市名称
   */
  //1. 选择不可用的文本输入框
  $(':text:disabled').css('background', 'red')

  //2. 显示选择爱好 的个数
  console.log($(':checkbox:checked').length)

  //3. 显示选择的城市名称
  $(':submit').click(function () {
    var city = $('select>option:selected').html() // 选择的option的标签体文本
    city = $('select').val()  // 选择的option的value属性值
    alert(city)
  })
</script>
```
