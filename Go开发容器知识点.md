0，Centos部署go开发环境：
#########################################
wget https://dl.google.com/go/go1.12.5.linux-amd64.tar.gz
tar -xzvf go1.12.5.linux-amd64.tar.gz -C /usr/local/
mkdir -p /home/gopath
cat >> /etc/profile <<EOF
export GOROOT=/usr/local/go
export GOPATH=/home/gopath
export PATH=\$PATH:\$GOROOT/bin
EOF
source /etc/profile
go version
#############################################

入门1：https://www.cnblogs.com/java-zhao/p/9942311.html
入门2：https://www.zybuluo.com/octopus/note/1212993#split-%E5%AD%97%E7%AC%A6%E4%B8%B2%E5%88%86%E5%89%B2%E4%B8%BA%E6%95%B0%E7%BB%84
编译go程序：go build

package main

import (
        "net/http"
        "log"
        "fmt"
)

func home(w http.ResponseWriter, req *http.Request) {
        w.Write([]byte("Welcome to this website!\n"))
}

func main() {
        http.HandleFunc("/", home)
        fmt.Println("Webserver start")
        fmt.Println("  -> listen on port:1111")
        err := http.ListenAndServe(":1111", nil)
        if err != nil {
                log.Fatal("ListenAndServe:", err)
        }
}


FROM golang:alpine as builder
WORKDIR /home/gopath
COPY hh.go .
RUN go build -o myhttpserver ./hh.go
From alpine:latest
WORKDIR /root/
COPY --from=builder /home/gopath/myhttpserver .
RUN chmod +x /root/myhttpserver
ENTRYPOINT ["/root/myhttpserver"]

FROM alpine:latest
RUN apk add --no-cache \
    python-dev \
    py-pip \
    && mkdir /run/nginx
RUN pip install -i https://pypi.tuna.tsinghua.edu.cn/simple numpy


docker build -t myrepo/myhttserver-multi-stage3:latest .
docker run myrepo/myhttserver-multi-stage3:latest