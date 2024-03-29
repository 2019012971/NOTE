# DOM
	- Document Object Model
	- 文档对象模型，通过DOM可以来任意来修改网页中各个内容
	- 文档
		- 文档指的是网页，一个网页就是一个文档
	- 对象
		- 对象指将网页中的每一个节点都转换为对象
			转换完对象以后，就可以以一种纯面向对象的形式来操作网页了
	- 模型
		- 模型用来表示节点和节点之间的关系，方便操作页面
	- 节点（Node）
		- 节点是构成网页的最基本的单元，网页中的每一个部分都可以称为是一个节点
		- 虽然都是节点，但是节点的类型却是不同的
		- 常用的节点
			- 文档节点 （Document），代表整个网页
			- 元素节点（Element），代表网页中的标签
			- 属性节点（Attribute），代表标签中的属性
			- 文本节点（Text），代表网页中的文本内容
			
	- DOM操作
		- DOM查询
		- 在网页中浏览器已经为我们提供了document对象，
			它代表的是整个网页，它是window对象的属性，可以在页面中直接使用。
```js
- document查询方法：
    - 根据元素的id属性查询一个元素节点对象：
        - document.getElementById("id属性值");
    - 根据元素的name属性值查询一组元素节点对象:
        - document.getElementsByName("name属性值");
    - 根据标签名来查询一组元素节点对象：
        - document.getElementsByTagName("标签名");
```				
		- 元素的属性：
			- 读取元素的属性：
				语法：元素.属性名
				例子：ele.name  
					  ele.id  
					  ele.value 
					  ele.className
					  
			- 修改元素的属性：
				语法：元素.属性名 = 属性值
				
			- innerHTML
				- 使用该属性可以获取或设置元素内部的HTML代码
				
				
	- 事件（Event）
		- 事件指的是用户和浏览器之间的交互行为。比如：点击按钮、关闭窗口、鼠标移动。。。
		- 我们可以为事件来绑定回调函数来响应事件。
		- 绑定事件的方式：
			1.可以在标签的事件属性中设置相应的JS代码
				例子：
					<button onclick="js代码...">按钮</button>
			2.可以通过为对象的指定事件属性设置回调函数的形式来处理事件
				例子：
					<button id="btn">按钮</button>
					<script>
						var btn = document.getElementById("btn");
						btn.onclick = function(){
						
						};
					</script>
					
	- 文档的加载
		- 浏览器在加载一个页面时，是按照自上向下的顺序加载的，加载一行执行一行。
		- 如果将js代码编写到页面的上边，当代码执行时，页面中的DOM对象还没有加载，
			此时将会无法正常获取到DOM对象，导致DOM操作失败。
		- 解决方式一：
			- 可以将js代码编写到body的下边
			<body>
				<button id="btn">按钮</button>
				<script>
					var btn = document.getElementById("btn");
					btn.onclick = function(){
					
					};
				</script>
			</body>
			
		- 解决方式二：
			- 将js代码编写到window.onload = function(){}中
			- window.onload 对应的回调函数会在整个页面加载完毕以后才执行，
				所以可以确保代码执行时，DOM对象已经加载完毕了
			<script>
				window.onload = function(){
					var btn = document.getElementById("btn");
					btn.onclick = function(){
					
					};
				};
			
			</script>	

# DOM查询
```js
	- 通过具体的元素节点来查询
		- 元素.getElementsByTagName()
			- 通过标签名查询当前元素的指定后代元素
			
		- 元素.childNodes
			- 获取当前元素的所有子节点
			- 会获取到空白的文本子节点
		
		- 元素.children
			- 获取当前元素的所有子元素
		
		- 元素.firstChild
			- 获取当前元素的第一个子节点
		
		- 元素.lastChild
			- 获取当前元素的最后一个子节点
		
		- 元素.parentNode
			- 获取当前元素的父元素
		
		- 元素.previousSibling
			- 获取当前元素的前一个兄弟节点
		
		- 元素.nextSibling
			- 获取当前元素的后一个兄弟节点
			
	innerHTML和innerText
		- 这两个属性并没有在DOM标准定义，但是大部分浏览器都支持这两个属性
		- 两个属性作用类似，都可以获取到标签内部的内容，
			不同是innerHTML会获取到html标签，而innerText会自动去除标签
		- 如果使用这两个属性来设置标签内部的内容时，没有任何区别的	
		
	读取标签内部的文本内容 (特别麻烦,倒不如innerHTML/Text)
		<h1>h1中的文本内容</h1>
		元素.firstChild.nodeValue
        	
	- document对象的其他的属性和方法
		document.all
			- 获取页面中的所有元素，相当于document.getElementsByTagName("*");
			
		document.documentElement
			- 获取页面中html根元素
			
		document.body
			- 获取页面中的body元素
			
		document.getElementsByClassName()
			- 根据元素的class属性值查询一组元素节点对象
			- 这个方法不支持IE8及以下的浏览器
			
		document.querySelector() (仿CSS选择器)
			- 根据CSS选择器去页面中查询一个元素
			- 如果匹配到的元素有多个，则它会返回查询到的第一个元素	
			
		document.querySelectorAll()	
			- 根据CSS选择器去页面中查询一组元素
			- 会将匹配到所有元素封装到一个数组中返回，即使只匹配到一个
```			
# DOM修改
	document.createElement()
		- 可以根据标签名创建一个元素节点对象
		
	document.createTextNode()
		- 可以根据文本内容创建一个文本节点对象
		
	父节点.appendChild(子节点)
		- 向父节点中添加指定的子节点
		
	父节点.insertBefore(新子节点,旧子节点)
		- 将一个新的子节点插入到旧子节点的前边
		
	父节点.replaceChild(新节点,旧节点)
		- 使用一个新的节点去替换旧节点
		
	父节点.removeChild(子节点)
		- 删除指定的子节点
		- 推荐方式：子节点.parentNode.removeChild(子节点)

@import "src/dom查询.html"
@import "src/dom增删改.html"
@import "src/dom查询的其他的方法.html"
@import "src/dom增删改.html"