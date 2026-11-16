<template>
  <section>
    <h1 class="h3">Архів</h1>
    <table class="table">
      <thead>
        <tr><th>Студент</th><th>Курс</th><th>Оцінка</th></tr>
      </thead>
      <tbody>
        <tr v-for="grade in grades" :key="grade.id">
          <td>{{ studentOf(grade.enrollmentId) }}</td>
          <td>{{ courseOf(grade.enrollmentId) }}</td>
          <td>{{ grade.grade }}</td>
        </tr>
      </tbody>
    </table>
    <form class="row g-2 align-items-end" @submit.prevent="addGrade">
      <div class="col-md-6">
        <label class="form-label" for="grade-enrollment">Реєстрація</label>
        <select id="grade-enrollment" v-model="enrollmentId" class="form-select" required>
          <option disabled value="">Оберіть реєстрацію</option>
          <option v-for="enrollment in enrollments" :key="enrollment.id" :value="enrollment.id">
            {{ studentName(enrollment.studentId) }} — {{ courseTitle(enrollment.courseId) }}
          </option>
        </select>
      </div>
      <div class="col-md-3">
        <label class="form-label" for="grade-value">Оцінка</label>
        <input id="grade-value" v-model.number="grade" class="form-control" type="number" min="0" max="100" required>
      </div>
      <div class="col-md-3">
        <button class="btn btn-primary" type="submit">Виставити оцінку</button>
      </div>
    </form>
  </section>
</template>

<script>
import { mapActions, mapState } from 'vuex'
import api from '../api'

export default {
  data() {
    return { enrollmentId: '', grade: 95 }
  },
  computed: mapState(['students', 'courses', 'enrollments', 'grades']),
  mounted() {
    this.load()
  },
  methods: {
    ...mapActions(['load']),
    enrollmentOf(id) {
      return this.enrollments.find(item => item.id === id)
    },
    studentName(id) {
      const student = this.students.find(item => item.id === id)
      return student ? `${student.firstName} ${student.lastName}` : ''
    },
    courseTitle(id) {
      const course = this.courses.find(item => item.id === id)
      return course ? course.title : ''
    },
    studentOf(enrollmentId) {
      const enrollment = this.enrollmentOf(enrollmentId)
      return enrollment ? this.studentName(enrollment.studentId) : ''
    },
    courseOf(enrollmentId) {
      const enrollment = this.enrollmentOf(enrollmentId)
      return enrollment ? this.courseTitle(enrollment.courseId) : ''
    },
    async addGrade() {
      await api.post('/grades', { enrollmentId: this.enrollmentId, grade: this.grade })
      this.enrollmentId = ''
      await this.load()
    }
  }
}
</script>
