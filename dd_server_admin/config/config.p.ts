import { defineConfig } from '@umijs/max';


export default defineConfig({
  define: {
    'process.env.UMI_ENV': 'p',
    "process.env.host": "https://api.itbug.shop"
  }
})
