# lable标签
## 点击label标签的文字就可以触发按钮
```html
<html>
<body>

<p>请点击文本标记之一，就可以触发相关控件：</p>

<form>
<label for="male">Male</label>
<input type="radio" name="sex" id="male" />
<br />
<label for="female">Female</label>
<input type="radio" name="sex" id="female" />
</form>

</body>
</html>
```
>css 中lable 浮动的问题，为什么不加float的时宽和高不起作用，加了就正常了？

回答：label 是inline元素，类似span，而加了float会被强制转化为block元素；所以，正常的手法应该加 display:block; 
# CSS position 
## position 属性规定元素的定位类型

@import "QQ图片20210121213807.png"







