# element-admin

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).


## 我的操作

1. 增加对element-ui的支持

```
vue add element
```

2. 修改界面

```html
<el-menu router :default-openeds="['1']"> <!--增加router属性，-->
    <el-submenu index="1">
        <template slot="title">
            <i class="el-icon-tickets"></i>内容管理
        </template>
        <el-menu-item index="/articles/index">文章列表</el-menu-item>
        <el-menu-item index="/articles/create">新增文章</el-menu-item>
    </el-submenu>
</el-menu>
```

```html
<el-main>
    <router-view></router-view> <!--将正文内容修改为路由对应的页面-->
</el-main>
```

3. 配置路由

```js
  const routes = [
  {
    path: '/',
    name: 'Home',
    redirect: '/articles/index'
  },
  {
    path: '/articles/index',
    name: '文章列表',
    component: ListArticle
  },
  {
    path: '/articles/create',
    name: '新建文章',
    component: CreateArticle
  },
  {
    path: '/articles/:id/edit',
    name: '文章详情',
    component: EditArticle
  }
]
```

4. 使用代码修改路由

```js
this.$router.push('path') // 代码修改路由

this.$route.params.{} // 获取路由中的参数
```

5. 定义全局http请求工具axios

```js
// 在main.js定义http请求工具
import axios from 'axios'
Vue.prototype.$http = axios.create({
  baseURL: 'http://localhost:3001/api'
})
```

```js
this.$http.[get,post,...]('url')
.then(res=>{
    //TODO
}) // 在代码中使用
```

6. 服务器代码

- 定义服务器

```js
const express = require('express')

const app = express()

// 允许跨域
app.use(require('cors')())

// 识别JSON
app.use(express.json())
```

定义mongo模型

```js
// 连接数据库
const mongoose = require('mongoose')
mongoose.connect("mongodb://localhost:27017/element-admin", {
    useNewUrlParser: true,
    useFindAndModify: true,
    useCreateIndex: true
})

// 定义数据库模型和结构
const Article = mongoose.model('Article', new mongoose.Schema({
    title: { type: String },
    content: { type: String }
}))
```

- 定义接口

```js
app.get('/', async (req, res) => {
    res.send('index')
})

// 新增文章
app.post('/api/articles', async (req, res)=>{
    const article = await Article.create(req.body)
    res.send(article)
})

// 所有文章
app.get('/api/articles', async (req, res) => {
    const articles = await Article.find()
    res.send(articles)
})

// 删除文章
app.delete('/api/articles/:id', async(req, res) => {
    await Article.findByIdAndDelete(req.params.id)
    res.send({
        status: true
    })
})

// 文章详情
app.get('/api/articles/:id', async (req, res)=>{
    const article = await Article.findById(req.params.id)
    res.send(article)
})


// 修改文章
app.put('/api/articles/:id', async (req, res)=>{
    const article = await Article.findByIdAndUpdate(req.params.id, req.body)
    res.send(article)
})
```

- 启动服务器

```js
app.listen(3001, () => {
    console.log("http://localhost:3001/")
})
```