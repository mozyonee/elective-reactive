<template>
  <div>
    <nav class="navbar navbar-expand navbar-dark bg-dark px-3">
      <router-link class="navbar-brand" to="/courses">Факультатив</router-link>
      <div class="navbar-nav">
        <router-link class="nav-link" active-class="active" to="/courses">Курси</router-link>
        <router-link class="nav-link" active-class="active" to="/students">Студенти</router-link>
        <router-link class="nav-link" active-class="active" to="/archive">Архів</router-link>
      </div>
      <button v-if="username" class="btn btn-outline-light btn-sm ms-auto" type="button" @click="logout">Вийти</button>
    </nav>
    <main class="container py-4">
      <LoginForm v-if="authRequired" />
      <router-view v-else />
    </main>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
import LoginForm from './components/LoginForm.vue'

export default {
  components: { LoginForm },
  computed: mapState(['username', 'authRequired']),
  mounted() {
    this.load()
  },
  methods: mapActions(['load', 'logout'])
}
</script>
