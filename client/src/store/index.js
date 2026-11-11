import { createStore } from 'vuex'
import api from '../api'

function denied(error) {
  const status = error.response && error.response.status
  return status === 401 || status === 403
}

export default createStore({
  state: {
    username: sessionStorage.getItem('username') || '',
    authRequired: false,
    teachers: [],
    courses: [],
    students: [],
    enrollments: [],
    grades: []
  },
  mutations: {
    setUsername(state, username) {
      state.username = username
      state.authRequired = false
      if (username) sessionStorage.setItem('username', username)
      else sessionStorage.removeItem('username')
    },
    authRequired(state) {
      state.authRequired = true
      state.username = ''
      sessionStorage.removeItem('username')
      state.teachers = []
      state.courses = []
      state.students = []
      state.enrollments = []
      state.grades = []
    },
    setLists(state, lists) {
      state.authRequired = false
      state.teachers = lists.teachers
      state.courses = lists.courses
      state.students = lists.students
      state.enrollments = lists.enrollments
      state.grades = lists.grades
    }
  },
  actions: {
    async load({ commit }) {
      try {
        const [teachers, courses, students, enrollments, grades] = await Promise.all([
          api.get('/teachers'),
          api.get('/courses'),
          api.get('/students'),
          api.get('/enrollments'),
          api.get('/grades')
        ])
        commit('setLists', {
          teachers: teachers.data,
          courses: courses.data,
          students: students.data,
          enrollments: enrollments.data,
          grades: grades.data
        })
      } catch (error) {
        if (denied(error)) {
          commit('authRequired')
          return
        }
        throw error
      }
    },
    async login({ commit, dispatch }, { username, password }) {
      const body = new URLSearchParams()
      body.set('username', username)
      body.set('password', password)
      const response = await api.post('/login', body, {
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
      })
      const text = typeof response.data === 'string' ? response.data : ''
      if (!text.includes('Main page!')) {
        throw new Error('login failed')
      }
      commit('setUsername', username)
      await dispatch('load')
    },
    async logout({ commit }) {
      try {
        await api.post('/logout')
      } catch (error) {
        if (!denied(error)) {
          throw error
        }
      }
      commit('authRequired')
    }
  }
})
