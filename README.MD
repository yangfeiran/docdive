## 安装JDK
下载JDK1.8，安装，设置好java环境变量

## 安装Libreoffice
1. 下载LibreOffice_5.1.2_Linux_x86-64_rpm.tar.gz或最新版本并解压
1. 进入到的RPMS目录，大的文件解压后，里面有两个目录readmes和RPMS，进入RPMS
1. 切换到root 权限，执行 rpm -ivh  \*.rpm  --nodeps --force或rpm -ivh lib* ，等待安装完成便可

## 安装ElasticSearch
1. 项目中使用es的lib包是es2.3的，下载相应版本的es安装，设置好集群名称和http、tcp端口，默认http端口为9200，tcp端口为9300（如果需要使用其他版本的es，请更换对应的sql4es包！）
2. 安装ik分词插件，参考：https://my.oschina.net/xiaohui249/blog/232784
3. 以非root用户启动ES服务

## 安装MySQL
如果没有MySQL的话，请安装之。

## 安装tomcat
下载tomcat8并解压，根据需要修改tomcat端口

# 部署系统
1. 将系统的class文件拷贝到 tomcatHome/webapps/ROOT下
2. **初始化Mysql**，主要是使用oneaa的三张表，执行tomcatHome/webapps/ROOT/WEB-INF/classes/docdive.sql脚本
3. **初始化es**，首先创建索引库，然后创建需要的Mapping，具体步骤为：打开tomcatHome/webapps/ROOT/WEB-INF/classes/ESMapping.txt后，根据文档说明分别请求相应的es APi即可，其中es的url需要根据实际情况修改
4. 修改tomcatHome/webapps/ROOT/WEB-INF/classes/application.properties文件中相关部分：
    * server.port: 8081 --可默认（使用tomcat时可忽略该项）
    * spring.datasource.url、spring.datasource.username和spring.datasource.password --数据库连接部分
    * sql4es.driver、sql4es.url和sql4es.clusterName --elasticsearch连接部分
    * mir.sdk.oneaa.endpoint、mir.sdk.oneaa.clientid和mir.sdk.oneaa.secret --oneaa接口和子系统配置部分
    * file.configInfo.local-path --上传文件保存路径部分
    * file.configInfo.setLibreOfficeHOme和file.configInfo.libreOfficeHome --linux下libreoffice安装目录