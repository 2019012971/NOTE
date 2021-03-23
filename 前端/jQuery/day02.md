# 属性
1. 操作任意属性
   attr()
   removeAttr()
   prop()
2. 操作class属性
   addClass()
   removeClass()
3. 操作HTML代码/文本/值
   html()
   val()
## 例子：
```js
<script type="text/javascript">
  /*
   需求:
   1. 读取第一个div的title属性
   2. 给所有的div设置name属性(value为atguigu)
   3. 移除所有div的title属性
   4. 给所有的div设置class='guiguClass'
   5. 给所有的div添加class='abc'
   6. 移除所有div的guiguClass的class
   7. 得到最后一个li的标签体文本
   8. 设置第一个li的标签体为"<h1>mmmmmmmmm</h1>"
   9. 得到输入框中的value值
   10. 将输入框的值设置为atguigu
   11. 点击'全选'按钮实现全选
   12. 点击'全不选'按钮实现全不选
   */
  //1. 读取第一个div的title属性
  console.log($('div:first').attr('title')) // one

  //2. 给所有的div设置name属性(value为atguigu)
  $('div').attr('name', 'atguigu')

  //3. 移除所有div的title属性
  $('div').removeAttr('title')

  //4. 给所有的div设置class='guiguClass'
  $('div').attr('class', 'guiguClass')

  //5. 给所有的div添加class='abc'
  $('div').addClass('abc')

  //6. 移除所有div的guiguClass的class
  $('div').removeClass('guiguClass')

  //7. 得到最后一个li的标签体文本
  console.log($('li:last').html())

  //8. 设置第一个li的标签体为"<h1>mmmmmmmmm</h1>"
  $('li:first').html('<h1>mmmmmmmmm</h1>')

  //9. 得到输入框中的value值
  console.log($(':text').val()) // 读取

  //10. 将输入框的值设置为atguigu
  $(':text').val('atguigu') // 设置      读写合一
  //11. 点击'全选'按钮实现全选
    // attr(): 操作属性值为非布尔值的属性
    // prop(): 专门操作属性值为布尔值的属性
  var $checkboxs = $(':checkbox')
  $('button:first').click(function () {
    $checkboxs.prop('checked', true)
  })

  //12. 点击'全不选'按钮实现全不选
  $('button:last').click(function () {
    $checkboxs.prop('checked', false)
  })
</script>
```
# CSS
## css()方法
### 例子
```js
<script type="text/javascript">
  /*
   1. 得到第一个p标签的颜色
   2. 设置所有p标签的文本颜色为red
   3. 设置第2个p的字体颜色(#ff0011),背景(blue),宽(300px), 高(30px)
   */
  //1. 得到第一个p标签的颜色
  console.log($('p:first').css('color'))

  //2. 设置所有p标签的文本颜色为red
  $('p').css('color', 'red')

  //3. 设置第2个p的字体颜色(#ff0011),背景(blue),宽(300px), 高(30px)
  $('p:eq(1)').css({
    color: '#ff0011',
    background: 'blue',
    width: 300,
    height: 30
  })

</script>
```
## offset和position
获取/设置标签的位置数据
  * offset(): 相对页面左上角的坐标
  * position(): 相对于父元素左上角的坐标
```js
<script type="text/javascript">
  /*
  需求:
  1. 点击 btn1
    打印 div1 相对于页面左上角的位置
    打印 div2 相对于页面左上角的位置
    打印 div1 相对于父元素左上角的位置
    打印 div2 相对于父元素左上角的位置
  2. 点击 btn2
    设置 div2 相对于页面的左上角的位置
   */
  $('#btn1').click(function () {
//    打印 div1 相对于页面左上角的位置
    var offset = $('.div1').offset()
    console.log(offset.left, offset.top) // 10 20
//    打印 div2 相对于页面左上角的位置
    offset = $('.div2').offset()
    console.log(offset.left, offset.top) // 10 70
//    打印 div1 相对于父元素左上角的位置
    var position = $('.div1').position()
    console.log(position.left, position.top) // 10 20
//    打印 div2 相对于父元素左上角的位置
    position = $('.div2').position()
    console.log(position.left, position.top) // 0 50
  })

  $('#btn2').click(function () {
    $('.div2').offset({
      left: 50,
      top: 100
    })
  })
</script>
```
## 元素滚动
1. 
```js
scrollTop():
  读取/设置滚动条的Y坐标
```
2.
```js
$(document.body).scrollTop()+$(document.documentElement).scrollTop()
  读取页面滚动条的Y坐标(兼容chrome和IE)
```
3. 
```js
$('body,html').scrollTop(60);
  滚动到指定位置(兼容chrome和IE)
```
### 例子
```js
<script>
  /*
   需求:
   1. 得到div或页面滚动条的坐标
   2. 让div或页面的滚动条滚动到指定位置
   */
  //1. 得到div或页面滚动条的坐标
  $('#btn1').click(function () {
    console.log($('div').scrollTop())
    // console.log($('html').scrollTop()+$('body').scrollTop())
    console.log($(document.documentElement).scrollTop()+$(document.body).scrollTop()) // 兼容IE/Chrome
  })
  //2. 让div或页面的滚动条滚动到指定位置
  $('#btn2').click(function () {
    $('div').scrollTop(200)
    $('html,body').scrollTop(300)
  })
</script>
```
## 元素尺寸
1. 内容尺寸
  height(): height
  width(): width
2. 内部尺寸
  innerHeight(): height+padding
  innerWidth(): width+padding
3. 外部尺寸
  outerHeight(false/true): height+padding+border  如果是true, 加上margin
  outerWidth(false/true): width+padding+border 如果是true, 加上margin
### 例子
```js
<style>
  div {
    width: 100px;
    height: 150px;
    background: red;
    padding: 10px;
    border: 10px #fbd850 solid;
    margin: 10px;
  }
</style>
<script>
  var $div = $('div')
  // 1. 内容尺寸
  console.log($div.width(), $div.height())  // 100 150
  // 2. 内部尺寸
  console.log($div.innerWidth(), $div.innerHeight()) //120 170
  // 3. 外部尺寸
  console.log($div.outerWidth(), $div.outerHeight()) //140 190
  console.log($div.outerWidth(true), $div.outerHeight(true)) //160 210

</script>
```
## 过滤
### 在jQuery对象中的元素对象数组中过滤出一部分元素来
1. first()
2. last()
3. eq(index|-index)
4. filter(selector)
5. not(selector)
6. has(selector)
#### 例子
```js
<script type="text/javascript">
  /*
   需求:
   1. ul下li标签第一个
   2. ul下li标签的最后一个
   3. ul下li标签的第二个
   4. ul下li标签中title属性为hello的
   5. ul下li标签中title属性不为hello的
   6. ul下li标签中有span子标签的
   */
  var $lis = $('ul>li')
  //1. ul下li标签第一个
  $lis.first().css('background', 'red')
  $lis[0].style.background = 'red'

  //2. ul下li标签的最后一个
  $lis.last().css('background', 'red')

  //3. ul下li标签的第二个
  $lis.eq(1).css('background', 'red')

  //4. ul下li标签中title属性为hello的
  $lis.filter('[title=hello]').css('background', 'red')

  //5. ul下li标签中title属性不为hello的
  $lis.not('[title=hello]').css('background', 'red')
  $lis.filter('[title!=hello]').filter('[title]').css('background', 'red')

  //6. ul下li标签中有span子标签的
  $lis.has('span').css('background', 'red')
</script>
```
### 在已经匹配出的元素集合中根据选择器查找孩子/父母/兄弟标签
1. children(): 子标签中找
2. find() : 后代标签中找
3. parent() : 父标签
4. prevAll() : 前面所有的兄弟标签
5. nextAll() : 后面所有的兄弟标签
6. siblings() : 前后所有的兄弟标签
#### 例子
```js
<script type="text/javascript">
  /*
   需求:
   1. ul标签的第2个span子标签
   2. ul标签的第2个span后代标签
   3. ul标签的父标签
   4. id为cc的li标签的前面的所有li标签
   5. id为cc的li标签的所有兄弟li标签
   */
  var $ul = $('ul')
  //1. ul标签的第2个span子标签
  $ul.children('span:eq(1)').css('background', 'red')

  //2. ul标签的第2个span后代标签
  $ul.find('span:eq(1)').css('background', 'red')

  //3. ul标签的父标签
  $ul.parent().css('background', 'red')

  //4. id为cc的li标签的前面的所有li标签
  var $li = $('#cc')
   $li.prevAll('li').css('background', 'red')

  //5. id为cc的li标签的所有兄弟li标签
  $li.siblings('li').css('background', 'red')
</script>
```
# 文档的增删改
1. 添加/替换元素
  * append(content)
    向当前匹配的所有元素内部的最后插入指定内容
  * prepend(content)
    向当前匹配的所有元素内部的最前面插入指定内容
  * before(content)
    将指定内容插入到当前所有匹配元素的前面
  * after(content)
    将指定内容插入到当前所有匹配元素的后面替换节点
  * replaceWith(content)
    用指定内容替换所有匹配的标签删除节点
2. 删除元素
  * empty()
    删除所有匹配元素的子元素
  * remove()
    删除所有匹配的元素
## 例子
```js
<script type="text/javascript">
  /*
   需求:
   1. 向id为ul1的ul下添加一个span(最后)
   2. 向id为ul1的ul下添加一个span(最前)
   3. 在id为ul1的ul下的li(title为hello)的前面添加span
   4. 在id为ul1的ul下的li(title为hello)的后面添加span
   5. 将在id为ul2的ul下的li(title为hello)全部替换为p
   6. 移除id为ul2的ul下的所有li
   */

  //1. 向id为ul1的ul下添加一个span(最后)
  var $ul1 = $('#ul1')
  $ul1.append('<span>append()添加的span</span>')
  $('<span>appendTo()添加的span</span>').appendTo($ul1)

  //2. 向id为ul1的ul下添加一个span(最前)
  $ul1.prepend('<span>prepend()添加的span</span>')
  $('<span>prependTo()添加的span</span>').prependTo($ul1)

  //3. 在id为ul1的ul下的li(title为hello)的前面添加span
  $ul1.children('li[title=hello]').before('<span>before()添加的span</span>')

  //4. 在id为ul1的ul下的li(title为hello)的后面添加span
  $ul1.children('li[title=hello]').after('<span>after()添加的span</span>')

  //5. 将在id为ul2的ul下的li(title为hello)全部替换为p
  $('#ul2>li[title=hello]').replaceWith('<p>replaceAll()替换的p</p>')
  //6. 移除id为ul2的ul下的所有li
  $('#ul2').empty()  // <p>也会删除
  $('#ul2>li').remove()
</script>
```