<template>
  <section>
    <h1 class="h3">Студенти</h1>
    <table class="table">
      <thead>
        <tr><th>Студент</th><th>Курс</th></tr>
      </thead>
      <tbody>
        <tr v-for="enrollment in enrollments" :key="enrollment.id">
          <td>{{ studentName(enrollment.studentId) }}</td>
          <td>{{ courseTitle(enrollment.courseId) }}</td>
        </tr>
      </tbody>
    </table>
    <form class="row g-2 align-items-end mb-4" @submit.prevent="addStudent">
      <div class="col-md-4">
        <label class="form-label" for="student-first">Ім'я</label>
        <input id="student-first" v-model="firstName" class="form-control" required>
      </div>
      <div class="col-md-4">
        <label class="form-label" for="student-last">Прізвище</label>
        <input id="student-last" v-model="lastName" class="form-control" required>
      </div>
      <div class="col-md-4">
        <button class="btn btn-outline-primary" type="submit">Додати студента</button>
      </div>
    </form>
    <form class="row g-2 align-items-end" @submit.prevent="enroll">
      <div class="col-md-4">
        <label class="form-label" for="enroll-student">Студент</label>
        <select id="enroll-student" v-model="studentId" class="form-select" required>
          <option disabled value="">Оберіть студента</option>
          <option v-for="student in students" :key="student.id" :value="student.id">
            {{ student.firstName }} {{ student.lastName }}
          </option>
        </select>
      </div>
      <div class="col-md-4">
        <label class="form-label" for="enroll-course">Курс</label>
        <select id="enroll-course" v-model="courseId" class="form-select" required>
          <option disabled value="">Оберіть курс</option>
          <option v-for="course in courses" :key="course.id" :value="course.id">{{ course.title }}</option>
        </select>
      </div>
      <div class="col-md-4">
        <button class="btn btn-primary" type="submit">Зареєструвати</button>
      </div>
    </form>
  </section>
</template>

<script>
import { mapActions, mapState } from 'vuex'
import api from '../api'

export default {
  data() {
    return { firstName: '', lastName: '', studentId: '', courseId: '' }
  },
  computed: mapState(['students', 'courses', 'enrollments']),
  mounted() {
    this.load()
  },
  methods: {
    ...mapActions(['load']),
    studentName(id) {
      const student = this.students.find(item => item.id === id)
      return student ? `${student.firstName} ${student.lastName}` : ''
    },
    courseTitle(id) {
      const course = this.courses.find(item => item.id === id)
      return course ? course.title : ''
    },
    async addStudent() {
      await api.post('/students', { firstName: this.firstName, lastName: this.lastName })
      this.firstName = ''
      this.lastName = ''
      await this.load()
    },
    async enroll() {
      await api.post('/enrollments', { studentId: this.studentId, courseId: this.courseId })
      this.studentId = ''
      this.courseId = ''
      await this.load()
    }
  }
}
</script>
