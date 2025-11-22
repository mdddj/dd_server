FROM nginx:latest
COPY nginx.conf /etc/nginx/nginx.conf
COPY dist /home/admin
EXPOSE 8083
CMD ["nginx","-g","daemon off;"]