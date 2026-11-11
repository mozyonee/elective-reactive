<template>
  <form class="card card-body col-md-6" @submit.prevent="submit">
    <h2 class="h5">Вхід</h2>
    <label class="form-label" for="username">Ім'я</label>
    <input id="username" v-model="username" class="form-control mb-2" autocomplete="username" required>
    <label class="form-label" for="password">Пароль</label>
    <input id="password" v-model="password" class="form-control mb-3" type="password" autocomplete="current-password" required>
    <p v-if="error" class="text-danger">Ім'я або пароль не підійшли.</p>
    <button class="btn btn-primary" type="submit">Увійти</button>
  </form>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  data() {
    return { username: '', password: '', error: false }
  },
  methods: {
    ...mapActions(['login']),
    async submit() {
      this.error = false
      try {
        await this.login({ username: this.username, password: this.password })
      } catch (error) {
        this.error = true
      }
    }
  }
}
</script>
