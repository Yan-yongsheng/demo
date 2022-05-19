import { createRouter, createWebHashHistory } from 'vue-router'
import HomePage from '../components/HomePage.vue'
import ServicePage from '../components/ServicePage.vue'
import RequirementPage from '../components/RequirementPage.vue'
import ResultPage from '../components/ResultPage.vue'
import ServiceUpload from '../components/ServiceUpload.vue'
import ManagerPage from '../components/ManagerPage.vue'
import SearchPage from '../components/SearchPage.vue'
import welcome from '../components/welcome.vue'

const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        { path: '/', redirect: '/home' },
        { path: '/home', component: HomePage },
        { path: '/servicePage', component: ServicePage },
        { path: '/requirement', component: RequirementPage },
        { path: '/result/:id', component: ResultPage, props: true },
        { path: '/serviceUpload', component: ServiceUpload },
        { path: '/manager', component: ManagerPage },
        { path: '/searchPage/:searchText', component: SearchPage, props: true },
        { path: '/welcome', component: welcome },
    ]
})

export default router