import VueRouter from 'vue-router';

const routes=[
    {
        path:'/',
        name:'login',
        component:()=>import('@/components/MyLogin')
    },
    {
        path:'/MyIndex',
        name:'MyIndex',
        component:()=>import('../components/MyIndex'),
        children:[{
            path:'/Home',
            name:'home',
            meta:{
              title:'首页'
            },
            component:()=>import('../components/HomeGo'),
        // },{
        //     path:'/Admin',
        //     name:'admin',
        //     meta:{
        //         title:'管理员模式'
        //     },
        //     component:()=>import('../components/AdminGo'),
        // },{
        //     path:'/User',
        //     name:'user',
        //     meta:{
        //         title:'用户模式'
        //     },
        //     component:()=>import('../components/UserGo'),
    }]
    }
]
const router=new VueRouter({
    mode:'history',
    routes
})
export  default  router
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}
export function resetRouter() {
    router.matcher = new VueRouter({
        mode:'history',
        routes: []
    }).matcher
}