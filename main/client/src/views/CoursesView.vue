<template>
  <section>
    <h1 class="h3">Курси</h1>
    <table class="table">
      <thead>
        <tr><th>Назва</th><th>Викладач</th></tr>
      </thead>
      <tbody>
        <tr v-for="course in courses" :key="course.id">
          <td>{{ course.title }}</td>
          <td>{{ teacherName(course.teacherId) }}</td>
        </tr>
      </tbody>
    </table>
    <form class="row g-2 align-items-end mb-4" @submit.prevent="addTeacher">
      <div class="col-md-4">
        <label class="form-label" for="teacher-first">Ім'я викладача</label>
        <input id="teacher-first" v-model="teacherFirst" class="form-control" required>
      </div>
      <div class="col-md-4">
        <label class="form-label" for="teacher-last">Прізвище викладача</label>
        <input id="teacher-last" v-model="teacherLast" class="form-control" required>
      </div>
      <div class="col-md-4">
        <button class="btn btn-outline-primary" type="submit">Додати викладача</button>
      </div>
    </form>
    <form class="row g-2 align-items-end" @submit.prevent="addCourse">
      <div class="col-md-4">
        <label class="form-label" for="course-title">Назва курсу</label>
        <input id="course-title" v-model="title" class="form-control" required>
      </div>
      <div class="col-md-4">
        <label class="form-label" for="course-teacher">Викладач</label>
        <select id="course-teacher" v-model="teacherId" class="form-select" required>
          <option disabled value="">Оберіть викладача</option>
          <option v-for="teacher in teachers" :key="teacher.id" :value="teacher.id">
            {{ teacher.firstName }} {{ teacher.lastName }}
          </option>
        </select>
      </div>
      <div class="col-md-4">
        <button class="btn btn-primary" type="submit">Додати курс</button>
      </div>
    </form>
  </section>
</template>

<script>
import { mapActions, mapState } from 'vuex'
import api from '../api'

export default {
  data() {
    return { teacherFirst: '', teacherLast: '', title: '', teacherId: '' }
  },
  computed: mapState(['teachers', 'courses']),
  mounted() {
    this.load()
  },
  methods: {
    ...mapActions(['load']),
    teacherName(id) {
      const teacher = this.teachers.find(item => item.id === id)
      return teacher ? `${teacher.firstName} ${teacher.lastName}` : ''
    },
    async addTeacher() {
      await api.post('/teachers', { firstName: this.teacherFirst, lastName: this.teacherLast })
      this.teacherFirst = ''
      this.teacherLast = ''
      await this.load()
    },
    async addCourse() {
      await api.post('/courses', { title: this.title, teacherId: this.teacherId })
      this.title = ''
      this.teacherId = ''
      await this.load()
    }
  }
}
</script>
