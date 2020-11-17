
## 这是我的个人博客系统   
### 使用的技术栈为：  


#### 后端：
* 核心框架：springboot
* jdk版本：jdk1.8
* jar包管理：maven3.3.9
* 数据操作模板：thymeleaf
* 分页插件：pageHelper
#### 前端：
* js框架：jquery
* css框架：semantic-ui
* 编辑器：markdown文本编辑器
* 代码高亮插件：prism插件
* 动态效果插件：animate
* 生成文章目录结构的插件：tocbot
* 生成二维码插件：qrcode
#### 数据库：
* mysql：5.6
* 数据库框架：mybatis
-------------------------------------------------------------------  
#### 前端展示：  
* 前端首页：  
* 前端首页详情：
首页的最新推荐是根据_访问量_来选择的，从数据库中选择*四个访问量*最高的文章，展示在*首页推荐列表*上，在最新推荐下，是所有文章的一个列表，用户可以查看本博客的所有文章  
在这里用到了*pagehelper分页插件*，在查询数据库后根据pagehelper的参数设置进行展示相应的文章，在这里默认是**每页展示5篇文章**  
![前端首页](https://github.com/login-w/MyBlog-Springboot/blob/master/src/main/resources/static/readmeImg/%E9%A6%96%E9%A1%B5.png)  
------------------------------------  
* 前端标签页：  
* 前端标签页详情：  
从数据库中获取所有标签，展示在页面，用户进入标签页时，默认展示**第一个标签**对应的文章，用户可以根据自己的喜好，具体选择某一个标签进行文章阅读，在这里对标签的文章也进行了分页处理  
每一页只能展示**5篇文章**，当用户点击翻页时，考虑到当前页顶部的标签是固定的，频繁查询数据库必定影响用户体验，所有在这通过ajax进行异步请求，只请求当前标签的下一页内容，不改变当前    
页面顶部的内容，在这里要留意**ajax的click失效问题**。*可以用on方法解决*  
![前端标签页](https://github.com/login-w/MyBlog-Springboot/blob/master/src/main/resources/static/readmeImg/%E6%A0%87%E7%AD%BE%E9%A1%B5.png)  
-------------------------------------  

* 前端归档页：  
* 前端归档页详情： 
归档页面采取的jquery的**timeline插件**，从数据库中按时间取出所有文章，并在页面按时间的倒序依次展示。  
![前端归档页](https://github.com/login-w/MyBlog-Springboot/blob/master/src/main/resources/static/readmeImg/%E5%BD%92%E6%A1%A3%E9%A1%B5.png)  
-------------------------------------  
* 关于我页：  
关于我页详情：  
这个页面的标签是从数据库中获取的*所有标签*，用户可以点击进入到具体的*某一个标签的文章列表下*。  
![关于我页](https://github.com/login-w/MyBlog-Springboot/blob/master/src/main/resources/static/readmeImg/%E5%85%B3%E4%BA%8E%E6%88%91%E9%A1%B5.png)  
-------------------------------------    
#### 后端展示：  
* 后端首页：  
![关于我页](https://github.com/login-w/MyBlog-Springboot/blob/master/src/main/resources/static/readmeImg/%E5%90%8E%E7%AB%AF%E9%A6%96%E9%A1%B5.png)  
* 后端标签页：  
* 后端标签页：  
博主在登录到后台系统后，可以对标签进行增、删、改、查，在这里修改和增加用的是同一个界面，在这里应用了**springboot的restful风格**，即根据不同的请求方式，虽然请求路径一样，但却可以  
调用不同的controller方法。  
![关于我页](https://github.com/login-w/MyBlog-Springboot/blob/master/src/main/resources/static/readmeImg/%E5%90%8E%E7%AB%AF%E6%A0%87%E7%AD%BE%E7%AE%A1%E7%90%86%E9%A1%B5.png)  
--------------------------------------    
* 后端博客管理页：  
* 后端博客管理页详情：  
管理员/博主在登录后可以对文章进行增、删、改、查，在新增的时候调用标签查询方法，返回到前端页面，供博主选择具体的标签，同时因为一篇文章只能有一个标签，所以在这里是单选择的，删除时，  给予警告框，以防误删、错删现象，修改时可以修改文章的是否开启赞赏功能，是否开启推荐、是发布还是保存为草稿状态，若为草稿状态则不会展示在页面，即允许博主长时间编写一篇文章，待完全写好  
后可以点击发布，进而可以展示给用户访问，同时还有是否开启评论功能，当文章开启评论功能后，访客可以留言，与他人进行交流，当然为了防止多级的评论占据大量空间，在这里把所有评论做成了  
两级结构，即第一级是父评论，第二级评论都是建立在第一级评论基础之上的，所有在第一级评论底下进行的评论都放在第二级，不再过多展示更多级别。同时在评论区区别了普通访客和博主的留言，  
当是博主留言时会有一个博主的标志，用以区分。
![关于我页](https://github.com/login-w/MyBlog-Springboot/blob/master/src/main/resources/static/readmeImg/%E5%90%8E%E7%AB%AF%E5%8D%9A%E5%AE%A2%E7%AE%A1%E7%90%86%E9%A1%B5.png)  
---------------------------------------    
* 后端博客编辑/修改页：  
![关于我页](https://github.com/login-w/MyBlog-Springboot/blob/master/src/main/resources/static/readmeImg/%E5%90%8E%E7%AB%AF%E5%8D%9A%E5%AE%A2%E7%BC%96%E8%BE%91%E4%BF%AE%E6%94%B9%E9%A1%B5.png)  
--------------------------  
==博客==尚有很多不足，但作为自己springboot阶段的锻炼也还好了，嘿嘿~ 最后 感谢您阅读到了这里，人生路远，咱们江湖再见^_^



