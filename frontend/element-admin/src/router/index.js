import Vue from 'vue'
import VueRouter from 'vue-router'
import CreateArticle from '../views/CreateArticle.vue'
import ListArticle from '../views/ListArticle.vue'
import EditArticle from '../views/EditArticle.vue'

Vue.use(VueRouter)

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

const router = new VueRouter({
  routes
})

export default router
