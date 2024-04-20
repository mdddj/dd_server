FROM karluto/jdk21-apline3.18:latest
USER root
RUN apk add --no-cache nginx
VOLUME /tmp
VOLUME /root/.dd
ADD ./build/libs/dd_service-0.0.2.jar dd_service.jar
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
COPY nginx.conf /etc/nginx/nginx.conf
COPY dd_server_admin/dist /home/admin/
ENTRYPOINT ["sh", "-c", "nginx -g 'daemon off;' & java -jar /dd_service.jar"]
EXPOSE 8082 8083