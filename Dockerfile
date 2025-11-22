FROM amazoncorretto:25-alpine3.22
#RUN echo "http://mirrors.aliyun.com/alpine/latest-stable/main/" > /etc/apk/repositories && \
#    echo "http://mirrors.aliyun.com/alpine/latest-stable/community/" >> /etc/apk/repositories
USER root
#RUN apk add --no-cache nginx && \
#    apk add curl
VOLUME /tmp
VOLUME /root/.dd
ADD ./build/libs/dd_service.jar dd_service.jar
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
COPY nginx.conf /etc/nginx/nginx.conf
#COPY dd_server_admin/dist /home/admin/
ENTRYPOINT ["java","-jar","/dd_service.jar","--spring.profiles.active=docker"]
EXPOSE 8082 8083