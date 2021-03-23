# 命令行窗口(小黑屏)、CMD窗口、终端、shell
 - 开始菜单 --> 运行 --> CMD --> 回车
 - 常用的指令：
   * dir 列出当前目录下的所有文件
   * cd 目录名 进入到指定的目录
   * md 目录名 创建一个文件夹
   * rd 目录名 删除一个文件夹  	

- 目录
	* . 表示当前目录
	* .. 表示上一级目录
		
- 环境变量（windows系统中变量）	
	* path
        C:\work\jdk\jdk1.7.0_75/bin;
        %CATALINA_HOME%/bin;
        C:\work\soft\tools\AppServ\Apache24\bin;
        C:\work\soft\tools\AppServ\php5;
        C:\Users\lilichao\AppData\Local\Programs\Fiddler;
        C:\work\environment\Egret\Egret Wing 3\bin;
        C:\Users\lilichao\AppData\Roaming\npm;
        C:\Program Files\MongoDB\Server\3.2\bin;
        C:\Users\lilichao\Desktop\hello
			
- 当我们在命令行窗口打开一个文件，或调用一个程序时，
    系统会首先在当前目录下寻找文件程序，如果找到了则直接打开
    如果没有找到则会依次到环境变量path的路径中寻找，直到找到为止
    如果没找到则报错
    - 所以我们可以将一些经常需要访问的程序和文件的路径添加到path中，
        这样我们就可以在任意位置来访问这些文件和程序了

# I/O (Input/Output)
	- I/O操作指的是对磁盘的读写操作
	
# Node
 - Node是对ES标准一个实现，Node也是一个JS引擎
 - 通过Node可以使js代码在服务器端执行
 - Node仅仅对ES标准进行了实现，所以在Node中不包含DOM 和 BOM	
 - Node中可以使用所有的内建对象(String Number Boolean Math Date RegExp Function Object Array)
	- 而BOM和DOM都不能使用
			但是可以使用 console 也可以使用定时器（setTimeout() setInterval()）
			
	- Node可以在后台来编写服务器
		Node编写服务器都是单线程的服务器
		- 进程
			- 进程就是一个一个的工作计划（工厂中的车间）
		- 线程
			- 线程是计算机最小的运算单位（工厂中的工人）
				线程是干活的
				
	- 传统的服务器都是多线程的
		- 每进来一个请求，就创建一个线程去处理请求
		
	- Node的服务器单线程的
		- Node处理请求时是单线程，但是在后台拥有一个I/O线程池
# 模块化
- 在Node中，一个js文件就是一个模块
- 在Node中，每一个js文件中的js代码都是独立运行在一个函数中
        而不是全局作用域，所以一个模块的中的变量和函数在其他模块中无法访问
- 我们可以通过 exports 来向外部暴露变量和方法
	只需要将需要暴露给外部的变量或方法设置为exports的属性即可
## 在node中，通过require()函数来引入外部的模块
        require()可以传递一个文件的路径作为参数，node将会自动根据该路径来引入外部模块
        这里路径，如果使用相对路径，必须以.或..开头

	使用require()引入模块以后，该函数会返回一个对象，这个对象代表的是引入的模块

	我们使用require()引入外部模块时，使用的就是模块标识，我们可以通过模块标识来找到指定的模块
	- 模块分成两大类
		核心模块
			- 由node引擎提供的模块
			- 核心模块的标识就是，模块的名字
		文件模块
			- 由用户自己创建的模块
			- 文件模块的标识就是文件的路径（绝对路径，相对路径）
				相对路径使用.或..开头
## node的全局对象
        在node中有一个全局对象 global，它的作用和网页中window类似
        在全局中创建的变量都会作为global的属性保存
        在全局中创建的函数都会作为global的方法保存

	当node在执行模块中的代码时，它会首先在代码的最顶部，添加如下代码
 			function (exports, require, module, __filename, __dirname) {

 	在代码的最底部，添加如下代码
 			}

 	实际上模块中的代码都是包装在一个函数中执行的，并且在函数执行时，同时传递进了5个实参
		 exports
		 	- 该对象用来将变量或函数暴露到外部

		 require
		 	- 函数，用来引入外部的模块

		 module
		 	- module代表的是当前模块本身
		 	- exports就是module的属性
		 	- 既可以使用 exports 导出，也可以使用module.exports导出

		 __filename
 			C:\Users\lilichao\WebstormProjects\class0705\01.node\04.module.js
 			- 当前模块的完整路径

	  	 __dirname
 			C:\Users\lilichao\WebstormProjects\class0705\01.node
 			- 当前模块所在文件夹的完整路径
        arguments.callee
        - 这个属性保存的是当前执行的函数对象
### exports的使用方法(暴露内部变量)
        exports 和 module.exports
        - 通过exports只能使用.的方式来向外暴露内部变量
                exports.xxx = xxx

        - 而module.exports既可以通过.的形式，也可以直接赋值
                module.exports.xxx = xxxx
                module.exports = {}
        - 模块的标识
        - 模块的标识就是模块的名字或路径
                我们node通过模块的标识来寻找模块的
                对于核心模块（npm中下载的模块），直接使用模块的名字对其进行引入
                        var fs = require("fs");
                        var express = require("express");
                        
                对于自定义的文件模块，需要通过文件的路径来对模块进行引入
                        路径可以是绝对路径，如果是相对路径必须以./或 ../开头
                        var router = require("./router");
# npm神器的使用
        通过npm下载的包都放到node_modules文件夹中
	        我们通过npm下载的包，直接通过包名引入即可

	node在使用模块名字来引入模块时，它会首先在当前目录的node_modules中寻找是否含有该模块
		如果有则直接使用，如果没有则去上一级目录的node_modules中寻找
		如果有则直接使用，如果没有则再去上一级目录寻找，直到找到为止
		直到找到磁盘的根目录，如果依然没有，则报错
# node结构与命令行
	- 包（package）
		- 将多个模块组合为一个完整的功能，就是一个包
		- 包结构
			bin
				- 二进制的可执行文件，一般都是一些工具包中才有
			lib
				- js文件
			doc
				- 文档
			test
				- 测试代码
			package.json
				- 包的描述文件
				
		- package.json	
			- 它是一个json格式的文件，在它里面保存了包各种相关的信息
				name 包名
				version 版本
				dependencies 依赖
				main 包的主要的文件
				bin 可执行文件
				
	- npm（Node Package Manager node的包管理器）
		- 通过npm可以对node中的包进行上传、下载、搜索等操作
		- npm会在安装完node以后，自动安装
		- npm的常用指令
			npm -v 查看npm的版本
			npm version 查看所有模块的版本
			npm init 初始化项目（创建package.json）
			npm i/install 包名 安装指定的包
			npm i/install 包名 --save 安装指定的包并添加依赖（重点使用）
			npm i/install 自动下载当前项目所依赖的包
			npm i/install 包名 -g 全局安装（一般都是一些工具）
			npm i/install 安装当前项目所依赖的包
			npm s/search 包名 搜索包	
			npm r/remove 包名 删除一个包
# 文件系统（File System）简介
	- Buffer（缓冲区）
		- Buffer和数组的结构的非常类似，Buffer是用来存储二进制数据的
		- Buffer的方法
			- Buffer.from(字符串)
				- 将一个字符串中内容保存到一个buffer中
			- buf.toString()
				- 将buffer转换为一个字符串
			- Buffer.alloc(size)
				- 创建一个指定大小的buffer对象
			- Buffer.allocUnsafe(size)
				- 创建一个指定大小的buffer对象，可以包含敏感数据
				
				
	- fs模块
		- 在Node通过fs模块来对系统中的文件进行操作，fs模块是node中已经继承好了，不需要在使用npm下载，直接引入即可
		- 引入fs
			var fs = require("fs");
		- fs模块中的大部分操作都提供了两种方法，同步方法和异步方法
			同步方法带sync
			异步方法没有sync，都需要回调函数
			
		- 写入文件
			1.同步写入
			2.异步写入
			3.简单写入
			4.流式写入
			
		- 读取文件
			1.同步读取
			2.异步读取
			3.简单读取
			4.流式读取
			
		- 方法
			- 打开文件
				fs.open(path, flags[, mode], callback)
				fs.openSync(path, flags[, mode])
				
			- 读写文件
				fs.write(fd, string[, position[, encoding]], callback)
				fs.writeSync(fd, string[, position[, encoding]])
				
				fs.read(fd, buffer, offset, length, position, callback)
				fs.readSync(fd, buffer, offset, length, position)
				
			- 关闭文件
				fs.close(fd,callback)
				fs.closeSync(fd);
				
			- 简单文件读取和写入
				fs.writeFile(file, data[, options], callback)
				fs.writeFileSync(file, data[, options])
				
				fs.readFile(path[, options], callback)
				fs.readFileSync(path[, options])
				
				
			- 流式文件读取和写入
				- 流式读取和写入适用于一些比较大的文件
					fs.createWriteStream(path[, options])
					fs.createReadStream(path[, options])
# Buffer(缓冲区)
        - Buffer的结构和数组很像，操作的方法也和数组类似
        - 数组中不能存储二进制的文件，而buffer就是专门用来存储二进制数据
        - 使用buffer不需要引入模块，直接使用即可
        - 在buffer中存储的都是二进制数据，但是在显示时都是以16进制的形式显示
                buffer中每一个元素的范围是从00 - ff   0 - 255
                00000000 - 11111111

                计算机 一个0 或一个1 我们称为1位（bit）

                8bit = 1byte（字节）
                1024byte = 1kb
                1024kb = 1mb
                1024mb = 1gb
                1024gb = 1tb

                buffer中的一个元素，占用内存的一个字节

        - Buffer的大小一旦确定，则不能修改，Buffer实际上是对底层内存的直接操作
##  例子
```js
var str = "Hello 尚硅谷";

//将一个字符串保存到buffer中
var buf = Buffer.from(str);

//console.log(buf.length); //占用内存的大小
//console.log(str.length);//字符串的长度
//console.log(buf);

//创建一个指定大小的buffer
//buffer构造函数都是不推荐使用的
//var buf2 = new Buffer(10);//10个字节的buffer
//console.log(buf2.length);

//创建一个10个字节的buffer
var buf2 = Buffer.alloc(10);
//通过索引，来操作buf中的元素
buf2[0] = 88;
buf2[1] = 255;
buf2[2] = 0xaa;
buf2[3] = 255;

//只要数字在控制台或页面中输出一定是10进制
//console.log(buf2[2].toString(16));

/*for(var i=0 ; i<buf2.length ; i++){
	console.log(buf2[i]);
}*/

//Buffer.allocUnsafe(size) 创建一个指定大小的buffer，但是buffer中可能含有敏感数据
/*var buf3 = Buffer.allocUnsafe(10);
console.log(buf3);*/

/*
	Buffer.from(str) 将一个字符串转换为buffer
	Buffer.alloc(size) 创建一个指定大小的Buffer
	Buffer.alloUnsafe(size) 创建一个指定大小的Buffer，但是可能包含敏感数据
 	buf.toString() 将缓冲区中的数据转换为字符串
 */

var buf4 = Buffer.from("我是一段文本数据");

console.log(buf4.toString());
```
## 文件系统（File System）
        - 文件系统简单来说就是通过Node来操作系统中的文件
        - 使用文件系统，需要先引入fs模块，fs是核心模块，直接引入不需要下载

	同步文件的写入
		- 手动操作的步骤
			1.打开文件
 				fs.openSync(path, flags[, mode])
 					- path 要打开文件的路径
 					- flags 打开文件要做的操作的类型
 						r 只读的
 						w 可写的
 					- mode 设置文件的操作权限，一般不传
				 返回值：
				 - 该方法会返回一个文件的描述符作为结果，我们可以通过该描述符来对文件进行各种操作

			2.向文件中写入内容
 				fs.writeSync(fd, string[, position[, encoding]])
 					- fd 文件的描述符，需要传递要写入的文件的描述符
 					- string 要写入的内容
 					- position 写入的起始位置
 					- encoding 写入的编码，默认utf-8

			3.保存并关闭文件
 				fs.closeSync(fd)
 					- fd 要关闭的文件的描述符
## 同步文件的写入
```js
//打开文件
var fd = fs.openSync("hello.txt" , "w");

//向文件中写入内容
fs.writeSync(fd , "今天天气真不错~~~", 2);

//关闭文件
fs.closeSync(fd);

console.log("程序向下执行~~~");
```
## 异步文件的写入
        fs.open(path, flags[, mode], callback)
                - 用来打开一个文件
        - 异步调用的方法，结果都是通过回调函数的参数返回的
                - 回调函数两个参数：
                        err 错误对象，如果没有错误则为null
                        fd  文件的描述符
        fs.write(fd, string[, position[, encoding]], callback)
                - 用来异步写入一个文件

        fs.close(fd, callback)
                - 用来关闭文件
```js
//打开文件
fs.open("hello2.txt","w",function (err , fd) {
	//判断是否出错
	if(!err){
		//如果没有出错，则对文件进行写入操作
		fs.write(fd,"这是异步写入的内容",function (err) {
			if(!err){
				console.log("写入成功~~");
			}
			//关闭文件
			fs.close(fd , function (err) {
				if(!err){
					console.log("文件已关闭~~~");
				}
			});
		});
	}else{
		console.log(err);
	}
});

console.log("程序向下执行~~~");
```
## 简单文件写入
	 fs.writeFile(file, data[, options], callback)
	 fs.writeFileSync(file, data[, options])
        - file 要操作的文件的路径
        - data 要写入的数据
        - options 选项，可以对写入进行一些设置
        - callback 当写入完成以后执行的函数

        - flag
                r 只读
                w 可写
                a 追加
```js
fs.writeFile("./hello.txt","这是通过writeFile写入的内容",{flag:"w"} , function (err) {
	if(!err){
		console.log("写入成功~~~");
	}else{
		console.log(err);
	}
});
```
## 流式文件写入
同步、异步、简单文件的写入都不适合大文件的写入，性能较差，容易导致内存溢出
```js
//流式文件写入
//创建一个可写流
/*
	fs.createWriteStream(path[, options])
		- 可以用来创建一个可写流
		- path，文件路径
		- options 配置的参数
 */
var ws = fs.createWriteStream("hello3.txt");

//可以通过监听流的open和close事件来监听流的打开和关闭
/*
	on(事件字符串,回调函数)
		- 可以为对象绑定一个事件

	once(事件字符串,回调函数)
		- 可以为对象绑定一个一次性的事件，该事件将会在触发一次以后自动失效

* */
ws.once("open",function () {
	console.log("流打开了~~~");
});

ws.once("close",function () {
	console.log("流关闭了~~~");
});

//通过ws向文件中输出内容
ws.write("通过可写流写入文件的内容");
ws.write("今天天气真不错");
ws.write("锄禾日当午");
ws.write("红掌拨清清");
ws.write("清清真漂亮");

//关闭流
ws.end();
```

## 文件的读取
        1.同步文件读取
        2.异步文件读取
        3.简单文件读取
                fs.readFile(path[, options], callback)
                fs.readFileSync(path[, options])
                - path 要读取的文件的路径
                - options 读取的选项
                - callback回调函数，通过回调函数将读取到内容返回(err , data)
                        err 错误对象
                        data 读取到的数据，会返回一个Buffer

        4.流式文件读取
```js
var path = "C:/Users/lilichao/Desktop/笔记.mp3";

fs.readFile("an.jpg" , function (err , data) {
	if(!err){
		//console.log(data);
		//将data写入到文件中
		fs.writeFile("C:/Users/lilichao/Desktop/hello.jpg",data,function(err){
			if(!err){
				console.log("文件写入成功");
			}
		} );
	}
});
```
流式文件读取也适用于一些比较大的文件，可以分多次将文件读取到内存中
```js
//创建一个可读流
var rs = fs.createReadStream("C:/Users/lilichao/Desktop/笔记.mp3");
//创建一个可写流
var ws = fs.createWriteStream("a.mp3");

//监听流的开启和关闭
rs.once("open",function () {
	console.log("可读流打开了~~");
});

rs.once("close",function () {
	console.log("可读流关闭了~~");
	//数据读取完毕，关闭可写流

	ws.end();
});

ws.once("open",function () {
	console.log("可写流打开了~~");
});

ws.once("close",function () {
	console.log("可写流关闭了~~");
});

//如果要读取一个可读流中的数据，必须要为可读流绑定一个data事件，data事件绑定完毕，它会自动开始读取数据
rs.on("data", function (data) {
	//console.log(data);
	//将读取到的数据写入到可写流中
	ws.write(data);
});
```
最简单的读写
```js
//创建一个可读流
var rs = fs.createReadStream("C:/Users/lilichao/Desktop/笔记.mp3");
//创建一个可写流
var ws = fs.createWriteStream("b.mp3");

//pipe()可以将可读流中的内容，直接输出到可写流中
rs.pipe(ws);
```