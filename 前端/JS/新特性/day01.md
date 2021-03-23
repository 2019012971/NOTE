# ES6的新特性
# 1.let
```js
//声明变量
    let a;
    let b,c,d;
    let e = 100;
    let f = 521, g = 'iloveyou', h = [];

    //1. 变量不能重复声明
    let star = '罗志祥';
    let star = '小猪';

    //2. 块儿级作用域  全局, 函数, eval
    // if else while for 
    {
        let girl = '周扬青';
    }
    console.log(girl);

    //3. 不存在变量提升(必须先声明)
    console.log(song);
    let song = '恋爱达人';

    //4. 不影响作用域链
    {
        let school = '尚硅谷';
        function fn(){
            console.log(school);
        }
        fn();
    }

</script>
```
## for循环中的i不变(不同于var)
```js
//遍历并绑定事件
    for(let i = 0;i<items.length;i++){
        items[i].onclick = function(){
            //修改当前元素的背景颜色
            // this.style.background = 'pink';
            items[i].style.background = 'pink'; //var(全局范围内都有效)的话 不可以这样用,而let是块级作用域
        }
    }
```
# 2.const
```js
//声明常量
    const SCHOOL = '尚硅谷';

    //1. 一定要赋初始值
    const A;
    //2. 一般常量使用大写(潜规则)
    const a = 100;
    //3. 常量的值不能修改
    SCHOOL = 'ATGUIGU';
    //4. 块儿级作用域
    {
        const PLAYER = 'UZI';
    }
    console.log(PLAYER);
    // 5. 对于数组和对象的元素修改, 不算做对常量的修改, 不会报错
    const TEAM = ['UZI','MXLG','Ming','Letme'];
    TEAM.push('Meiko');
```
# 3.变量的解构赋值
```js
    //ES6 允许按照一定模式从数组和对象中提取值，对变量进行赋值，
    //这被称为解构赋值。
    //1. 数组的结构
    const F4 = ['小沈阳','刘能','赵四','宋小宝'];
    let [xiao, liu, zhao, song] = F4;
    // console.log(xiao);
    // console.log(liu);
    // console.log(zhao);
    // console.log(song);

    //2. 对象的解构
    const zhao = {
        name: '赵本山',
        age: '不详',
        xiaopin: function(){
            console.log("我可以演小品");
        }
    };

    let {name, age, xiaopin} = zhao;
    // console.log(name);
    // console.log(age);
    // console.log(xiaopin);
    // xiaopin();

    let {xiaopin} = zhao;
    xiaopin();
```
# 4.模板字符串
```js
    // ES6 引入新的声明字符串的方式 『``』 '' "" 
    //1. 声明
    let str = `我也是一个字符串哦!`;
    console.log(str, typeof str);

    //2. 内容中可以直接出现换行符
    let str = `<ul>
                <li>沈腾</li>
                <li>玛丽</li>
                <li>魏翔</li>
                <li>艾伦</li>
                </ul>`;
    //3. 变量拼接
    let lovest = '魏翔';
    let out = `${lovest}是我心目中最搞笑的演员!!`;
    console.log(out);
```
# 5.箭头函数
```js
// ES6 允许使用「箭头」（=>）定义函数。
    //声明一个函数
    // let fn = function(){

    // }
    let fn = (a,b) => {
        return a + b;
    }
    //调用函数
    // let result = fn(1, 2);
    // console.log(result);


    //1. this 是静态的. this 始终指向函数声明时所在作用域下的 this 的值（this指向箭头函数声明时的外层对象）
    function getName(){
        console.log(this.name);
    }
    let getName2 = () => {
        console.log(this.name);
    }

    //设置 window 对象的 name 属性
    window.name = '尚硅谷';
    const school = {
        name: "ATGUIGU"
    }

    //直接调用
    // getName();
    // getName2();

    //call 方法调用
    // getName.call(school);
    // getName2.call(school);

    //2. 不能作为构造实例化对象
    // let Person = (name, age) => {
    //     this.name = name;
    //     this.age = age;
    // }
    // let me = new Person('xiao',30);
    // console.log(me);

    //3. 不能使用 arguments 变量
    // let fn = () => {
    //     console.log(arguments);
    // }
    // fn(1,2,3);

    //4. 箭头函数的简写
        //1) 省略小括号, 当形参有且只有一个的时候
        let add = n => {
            return n + n;
        }
        // console.log(add(9));
        //2) 省略花括号, 当代码体只有一条语句的时候, 此时 return 必须省略
        // 而且语句的执行结果就是函数的返回值
        let pow = n => n * n;
            
        console.log(pow(8));
```
## 箭头函数适用地点与比较
* 箭头函数`适合`与 this `无关`的回调：`定时器`, `数组`的方法回调
* 箭头函数`不适合`与 this `有关`的回调：`事件回调`, `对象`的方法
## 例子
```js
//需求-1  点击 div 2s 后颜色变成『粉色』
    //获取元素
    let ad = document.getElementById('ad');
    //绑定事件
    ad.addEventListener("click", function(){
        //保存 this 的值
        // let _this = this;
        //定时器
        setTimeout(() => {
            //修改背景颜色 this
            // console.log(this);
            // _this.style.background = 'pink';
            this.style.background = 'pink';
        }, 2000);
    });

    //需求-2  从数组中返回偶数的元素
    const arr = [1,6,9,10,100,25];
    // const result = arr.filter(function(item){
    //     if(item % 2 === 0){
    //         return true;
    //     }else{
    //         return false;
    //     }
    // });
    
    const result = arr.filter(item => item % 2 === 0);

    console.log(result);
```
# 6.rest参数
```js
// ES6 引入 rest 参数，用于获取函数的实参，用来代替 arguments
    // ES5 获取实参的方式
    // function date(){
    //     console.log(arguments);
    // }
    // date('白芷','阿娇','思慧');

    // rest 参数
    function date(...args){
        console.log(args);// filter some every map 
    }
    date('阿娇','柏芝','思慧');

    // rest 参数必须要放到参数最后
    function fn(a,b,...args){
        console.log(a);
        console.log(b);
        console.log(args);
    }
    fn(1,2,3,4,5,6);
```
# 7.spread扩展运算符
* `『...』` 扩展运算符能将『数组』转换为逗号分隔的『参数序列』
```js
    //声明一个数组 ...
    const tfboys = ['易烊千玺','王源','王俊凯'];
    // => '易烊千玺','王源','王俊凯'

    // 声明一个函数
    function chunwan(){
        console.log(arguments);
    }

    chunwan(...tfboys);// chunwan('易烊千玺','王源','王俊凯')
```
```js
    //1. 数组的合并 情圣  误杀  唐探
    const kuaizi = ['王太利','肖央'];
    const fenghuang = ['曾毅','玲花'];
    // const zuixuanxiaopingguo = kuaizi.concat(fenghuang);
    const zuixuanxiaopingguo = [...kuaizi, ...fenghuang];
    console.log(zuixuanxiaopingguo);

    //2. 数组的克隆
    const sanzhihua = ['E','G','M'];
    const sanyecao = [...sanzhihua];//  ['E','G','M']
    console.log(sanyecao);

    //3. 将伪数组转为真正的数组
    const divs = document.querySelectorAll('div');
    const divArr = [...divs];
    console.log(divArr);// arguments
```
# 8.生成器
## 入门
```js
//生成器其实就是一个特殊的函数
    //异步编程  纯回调函数  node fs  ajax mongodb
    //函数代码的分隔符
    function * gen(){
        // console.log(111);
        yield '一只没有耳朵';
        // console.log(222);
        yield '一只没有尾部';
        // console.log(333);
        yield '真奇怪';
        // console.log(444);
    }

    let iterator = gen();
    console.log(iterator.next());
    console.log(iterator.next());
    console.log(iterator.next());
    console.log(iterator.next());

    //遍历
    // for(let v of gen()){
    //     console.log(v);
    // }
```
## 生成器函数参数
```js
function * gen(arg){
    console.log(arg);
    let one = yield 111;
    console.log(one);
    let two = yield 222;
    console.log(two);
    let three = yield 333;
    console.log(three);
}

//执行获取迭代器对象
let iterator = gen('AAA');
console.log(iterator.next());
//next方法可以传入实参
console.log(iterator.next('BBB'));
console.log(iterator.next('CCC'));
console.log(iterator.next('DDD'));
```
## 生成器实例1
```js
    // 异步编程  文件操作 网络操作(ajax, request) 数据库操作
    // 1s 后控制台输出 111  2s后输出 222  3s后输出 333 
    // 回调地狱
    // setTimeout(() => {
    //     console.log(111);
    //     setTimeout(() => {
    //         console.log(222);
    //         setTimeout(() => {
    //             console.log(333);
    //         }, 3000);
    //     }, 2000);
    // }, 1000);

    function one(){
        setTimeout(()=>{
            console.log(111);
            iterator.next();
        },1000)
    }

    function two(){
        setTimeout(()=>{
            console.log(222);
            iterator.next();
        },2000)
    }

    function three(){
        setTimeout(()=>{
            console.log(333);
            iterator.next();
        },3000)
    }

    function * gen(){
        yield one();
        yield two();
        yield three();
    }

    //调用生成器函数
    let iterator = gen();
    iterator.next();
```
## 生成器实例2
```js
//模拟获取  用户数据  订单数据  商品数据 
    function getUsers(){
        setTimeout(()=>{
            let data = '用户数据';
            //调用 next 方法, 并且将数据传入
            iterator.next(data);
        }, 1000);
    }

    function getOrders(){
        setTimeout(()=>{
            let data = '订单数据';
            iterator.next(data);
        }, 1000)
    }

    function getGoods(){
        setTimeout(()=>{
            let data = '商品数据';
            iterator.next(data);
        }, 1000)
    }

    function * gen(){
        let users = yield getUsers();
        let orders = yield getOrders();
        let goods = yield getGoods();
    }

    //调用生成器函数
    let iterator = gen();
    iterator.next();
```
# 9.Promise
## 基本语法
```js
//实例化 Promise 对象
    const p = new Promise(function(resolve, reject){
        setTimeout(function(){
            //
            // let data = '数据库中的用户数据';
            // resolve
            // resolve(data);

            let err = '数据读取失败';
            reject(err);
        }, 1000);
    });

    //调用 promise 对象的 then 方法
    p.then(function(value){
        console.log(value);
    }, function(reason){
        console.error(reason);
    })
```
## Promise封装读取文件
```js
//1. 引入 fs 模块
const fs = require('fs');

//2. 调用方法读取文件
// fs.readFile('./resources/为学.md', (err, data)=>{
//     //如果失败, 则抛出错误
//     if(err) throw err;
//     //如果没有出错, 则输出内容
//     console.log(data.toString());
// });

//3. 使用 Promise 封装
const p = new Promise(function(resolve, reject){
    fs.readFile("./resources/为学.md", (err, data)=>{
        //判断如果失败
        if(err) reject(err);
        //如果成功
        resolve(data);
    });
});

p.then(function(value){
    console.log(value.toString());
}, function(reason){
    console.log("读取失败!!");
});
```
## Promise封装AJAX请求
```js
// 接口地址: https://api.apiopen.top/getJoke
    const p = new Promise((resolve, reject) => {
        //1. 创建对象
        const xhr = new XMLHttpRequest();

        //2. 初始化
        xhr.open("GET", "https://api.apiopen.top/getJoke");

        //3. 发送
        xhr.send();

        //4. 绑定事件, 处理响应结果
        xhr.onreadystatechange = function () {
            //判断
            if (xhr.readyState === 4) {
                //判断响应状态码 200-299
                if (xhr.status >= 200 && xhr.status < 300) {
                    //表示成功
                    resolve(xhr.response);
                } else {
                    //如果失败
                    reject(xhr.status);
                }
            }
        }
    })
    
    //指定回调
    p.then(function(value){
        console.log(value);
    }, function(reason){
        console.error(reason);
    });
```
## then方法
```js
//创建 promise 对象
    const p = new Promise((resolve, reject)=>{
        setTimeout(()=>{
            resolve('用户数据');
            // reject('出错啦');
        }, 1000)
    });

    //调用 then 方法  then方法的返回结果是 Promise 对象, 对象状态由回调函数的执行结果决定
    //1. 如果回调函数中返回的结果是 非 promise 类型的属性, 状态为成功, 返回值为对象的成功的值

    // const result = p.then(value => {
    //     console.log(value);
    //     //1. 非 promise 类型的属性
    //     // return 'iloveyou';
    //     //2. 是 promise 对象
    //     // return new Promise((resolve, reject)=>{
    //     //     // resolve('ok');
    //     //     reject('error');
    //     // });
    //     //3. 抛出错误
    //     // throw new Error('出错啦!');
    //     throw '出错啦!';
    // }, reason=>{
    //     console.warn(reason);
    // });

    //链式调用
    p.then(value=>{

    }).then(value=>{

    });
```
## catch方法
```js
const p = new Promise((resolve, reject)=>{
            setTimeout(()=>{
                //设置 p 对象的状态为失败, 并设置失败的值
                reject("出错啦!");
            }, 1000)
        });

        // p.then(function(value){}, function(reason){
        //     console.error(reason);
        // });

        p.catch(function(reason){
            console.warn(reason);
        });
```
## 实践：读取多个文件
```js
//引入 fs 模块
const fs = require("fs");

// fs.readFile('./resources/为学.md', (err, data1)=>{
//     fs.readFile('./resources/插秧诗.md', (err, data2)=>{
//         fs.readFile('./resources/观书有感.md', (err, data3)=>{
//             let result = data1 + '\r\n' +data2  +'\r\n'+ data3;
//             console.log(result);
//         });
//     });
// });

//使用 promise 实现
const p = new Promise((resolve, reject) => {
    fs.readFile("./resources/为学.md", (err, data) => {
        resolve(data);
    });
});

p.then(value => {
    return new Promise((resolve, reject) => {
        fs.readFile("./resources/插秧诗.md", (err, data) => {
            resolve([value, data]);
        });
    });
}).then(value => {
    return new Promise((resolve, reject) => {
        fs.readFile("./resources/观书有感.md", (err, data) => {
            //压入
            value.push(data);
            resolve(value);
        });
    })
}).then(value => {
    console.log(value.join('\r\n'));
});
```
# 10.Set
```js
//声明一个 set
        let s = new Set();
    let s2 = new Set(['大事儿','小事儿','好事儿','坏事儿','小事儿']);

    //元素个数
    // console.log(s2.size);
    //添加新的元素
    // s2.add('喜事儿');
    //删除元素
    // s2.delete('坏事儿');
    //检测
    // console.log(s2.has('糟心事'));
    //清空
    // s2.clear();
    // console.log(s2);

    for(let v of s2){
        console.log(v);
    }
```
## 应用
```js
    let arr = [1,2,3,4,5,4,3,2,1];
    //1. 数组去重
    // let result = [...new Set(arr)];
    // console.log(result);
    //2. 交集
    let arr2 = [4,5,6,5,6];
    // let result = [...new Set(arr)].filter(item => {
    //     let s2 = new Set(arr2);// 4 5 6
    //     if(s2.has(item)){
    //         return true;
    //     }else{
    //         return false;
    //     }
    // });
    // let result = [...new Set(arr)].filter(item => new Set(arr2).has(item));
    // console.log(result);

    //3. 并集
    // let union = [...new Set([...arr, ...arr2])];
    // console.log(union);

    //4. 差集
    let diff = [...new Set(arr)].filter(item => !(new Set(arr2).has(item)));
    console.log(diff);
```
# 11.Map
```js
//声明 Map
    let m = new Map();

    //添加元素
    m.set('name','尚硅谷');
    m.set('change', function(){
        console.log("我们可以改变你!!");
    });
    let key = {
        school : 'ATGUIGU'
    };
    m.set(key, ['北京','上海','深圳']);

    //size
    // console.log(m.size);

    //删除
    // m.delete('name');

    //获取
    // console.log(m.get('change'));
    // console.log(m.get(key));

    //清空
    // m.clear();

    //遍历
    for(let v of m){
        console.log(v);
    }

    // console.log(m);
```
# 12.class
## 类入门
```js
//手机
    function Phone(brand, price){
        this.brand = brand;
        this.price = price;
    }

    //添加方法
    Phone.prototype.call = function(){
        console.log("我可以打电话!!");
    }

    //实例化对象
    let Huawei = new Phone('华为', 5999);
    Huawei.call();
    console.log(Huawei);

    //class
    class Shouji{
        //构造方法 名字不能修改
        constructor(brand, price){
            this.brand = brand;
            this.price = price;
        }

        //方法必须使用该语法, 不能使用 ES5 的对象完整形式
        call(){
            console.log("我可以打电话!!");
        }
    }

    let onePlus = new Shouji("1+", 1999);

    console.log(onePlus);
```
## 类的静态成员
```js
// function Phone(){
    // }
    // Phone.name = '手机';
    // Phone.change = function(){
    //     console.log("我可以改变世界");
    // }
    // Phone.prototype.size = '5.5inch';

    // let nokia = new Phone();

    // console.log(nokia.name);
    // // nokia.change();
    // console.log(nokia.size);

    class Phone{
        //静态属性
        static name = '手机';
        static change(){
            console.log("我可以改变世界");
        }
    }

    let nokia = new Phone();
    console.log(nokia.name);
    console.log(Phone.name);
```
## 类继承
```js
原生

//手机
    function Phone(brand, price){
        this.brand = brand;
        this.price = price;
    }

    Phone.prototype.call = function(){
        console.log("我可以打电话");
    }

    //智能手机
    function SmartPhone(brand, price, color, size){
        Phone.call(this, brand, price);
        this.color = color;
        this.size = size;
    }

    //设置子级构造函数的原型
    SmartPhone.prototype = new Phone;
    SmartPhone.prototype.constructor = SmartPhone;

    //声明子类的方法
    SmartPhone.prototype.photo = function(){
        console.log("我可以拍照")
    }

    SmartPhone.prototype.playGame = function(){
        console.log("我可以玩游戏");
    }

    const chuizi = new SmartPhone('锤子',2499,'黑色','5.5inch');

    console.log(chuizi);
```
```js
class的继承

class Phone{
        //构造方法
        constructor(brand, price){
            this.brand = brand;
            this.price = price;
        }
        //父类的成员属性
        call(){
            console.log("我可以打电话!!");
        }
    }

    class SmartPhone extends Phone {
        //构造方法
        constructor(brand, price, color, size){
            super(brand, price);// Phone.call(this, brand, price)
            this.color = color;
            this.size = size;
        }

        photo(){
            console.log("拍照");
        }

        playGame(){
            console.log("玩游戏");
        }

        call(){
            console.log('我可以进行视频通话');
        }
    }

    const xiaomi = new SmartPhone('小米',799,'黑色','4.7inch');
    // console.log(xiaomi);
    xiaomi.call();
    xiaomi.photo();
    xiaomi.playGame();
```
## class的set和get
```js
// get 和 set  
    class Phone{
        get price(){
            console.log("价格属性被读取了");
            return 'iloveyou';
        }

        set price(newVal){
            console.log('价格属性被修改了');
        }
    }

    //实例化对象
    let s = new Phone();

    // console.log(s.price);
    s.price = 'free';
```
# 13.数值与对象方法的新扩展
```js
    //0. Number.EPSILON 是 JavaScript 表示的最小精度
    //EPSILON 属性的值接近于 2.2204460492503130808472633361816E-16
    function equal(a, b){
        if(Math.abs(a-b) < Number.EPSILON){
            return true;
        }else{
            return false;
        }
    }
    console.log(0.1 + 0.2 === 0.3);
    console.log(equal(0.1 + 0.2, 0.3))

    //1. 二进制和八进制
    let b = 0b1010;
    let o = 0o777;
    let d = 100;
    let x = 0xff;
    console.log(x);

    //2. Number.isFinite  检测一个数值是否为有限数
    console.log(Number.isFinite(100));
    console.log(Number.isFinite(100/0));
    console.log(Number.isFinite(Infinity));
    
    //3. Number.isNaN 检测一个数值是否为 NaN 
    console.log(Number.isNaN(123)); 

    //4. Number.parseInt Number.parseFloat字符串转整数
    console.log(Number.parseInt('5211314love'));
    console.log(Number.parseFloat('3.1415926神奇'));

    //5. Number.isInteger 判断一个数是否为整数
    console.log(Number.isInteger(5));
    console.log(Number.isInteger(2.5));

    //6. Math.trunc 将数字的小数部分抹掉  
    console.log(Math.trunc(3.5));

    //7. Math.sign 判断一个数到底为正数 负数 还是零
    console.log(Math.sign(100)); //1
    console.log(Math.sign(0)); //0 
    console.log(Math.sign(-20000)); //-1
```
```js
    //1. Object.is 判断两个值是否完全相等 
    console.log(Object.is(120, 120));// === 
    console.log(Object.is(NaN, NaN));// === 
    console.log(NaN === NaN);// === 

    //2. Object.assign 对象的合并
    const config1 = {
        host: 'localhost',
        port: 3306,
        name: 'root',
        pass: 'root',
        test: 'test'
    };
    const config2 = {
        host: 'http://atguigu.com',
        port: 33060,
        name: 'atguigu.com',
        pass: 'iloveyou',
        test2: 'test2'
    }
    console.log(Object.assign(config1, config2));

    //3. Object.setPrototypeOf 设置原型对象  Object.getPrototypeof
    const school = {
        name: '尚硅谷'
    }
    const cities = {
        xiaoqu: ['北京','上海','深圳']
    }
    Object.setPrototypeOf(school, cities);
    console.log(Object.getPrototypeOf(school));
    console.log(school);
```
# 14.模块化
## 模块的导入
```js
<body>
<script type="module">
    //1. 通用的导入方式
    //引入 m1.js 模块内容
    // import * as m1 from "./src/js/m1.js";
    // //引入 m2.js 模块内容
    // import * as m2 from "./src/js/m2.js";
    // //引入 m3.js 
    // import * as m3 from "./src/js/m3.js";

    //2. 解构赋值形式
    // import {school, teach} from "./src/js/m1.js";
    // import {school as guigu, findJob} from "./src/js/m2.js";
    // import {default as m3} from "./src/js/m3.js";

    //3. 简便形式  针对默认暴露
    // import m3 from "./src/js/m3.js";
    // console.log(m3);
</script>

<script src="./src/js/app.js" type="module"></script>
</body>
```
## 分别暴露
```js
//分别暴露
export let school = '尚硅谷';

export function teach() {
    console.log("我们可以教给你开发技能");
}
```
## 统一暴露
```js
//统一暴露
let school = '尚硅谷';

function findJob(){
    console.log("我们可以帮助你找工作!!");
}

//
export {school, findJob};
```

## 默认暴露
```js
//默认暴露
export default {
    school: 'ATGUIGU',
    change: function(){
        console.log("我们可以改变你!!");
    }
}
```