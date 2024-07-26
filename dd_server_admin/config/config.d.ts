import { defineConfig } from '@umijs/max';


export default defineConfig({
  define: {
    "process.env": {
      "host": "http://localhost:8082"
    }
  }
})
