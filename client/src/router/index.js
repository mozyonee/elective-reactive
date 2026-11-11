import { createRouter, createWebHistory } from 'vue-router'
import CoursesView from '../views/CoursesView.vue'
import StudentsView from '../views/StudentsView.vue'
import ArchiveView from '../views/ArchiveView.vue'

export default createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/', redirect: '/courses' },
    { path: '/courses', component: CoursesView },
    { path: '/students', component: StudentsView },
    { path: '/archive', component: ArchiveView }
  ]
})
