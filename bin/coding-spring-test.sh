#!/usr/bin/env bash
#----------------------------------#
# 功能说明: 项目启动脚本, sh coding-spring-test.sh [start|stop|restart|status]
# 作者    : lwk
# 日期    : 2020-01-05 20:21:11
#----------------------------------#

begin_time=`date +"%s"`
echo "script begin_time: " $(date +"%Y-%m-%d %H:%M:%S")

echo "=========================================="
echo "开始执行..."
echo "------------------------------------------"

# 系统日期
sys_date=`date +"%Y%m%d"`
echo "系统日期: "${sys_date}

# 运行环境
RUN_ENV=${ENV_LABEL}
echo "运行环境: "${RUN_ENV}

command=$1
echo "执行命令: "${command}


# 项目目录
HOME_PATH=$(cd `dirname $0`;cd ../; pwd);
echo "项目目录: "${HOME_PATH}

# 项目名称
app_name=coding-spring-test
app_jar=${app_name}".jar"
echo "项目名称: "${app_jar}

# DUMP快照
dump_path=/appdata/logs/oom/
echo "DUMP快照: "${dump_path}
echo "------------------------------------------"

#---------------------------------------------------------------------#
# 'nohup' : 表示不挂断运行命令,当账户退出或终端关闭时,程序仍然运行
# 'java -jar xxx.jar' : 启动jar包
# '>xxx.tex' : jar包运行时控制台日志重定向到文件xxx.tex中
# '&' : 代表后台运行
# '-Xms512m -Xmx512m' : 最大堆内存和最小堆内存,设置一致时能避免虚拟机动态计算分配内存空间
# '-Xmn256m' : 新生代大小，非G1收集器可以设置这个值
# '-Xss2m' : 每个线程的栈空间大小,一般不需要设置，除非有递归方法存在可能会爆栈
# '-XX:+HeapDumpOnOutOfMemoryError' : 在堆溢出时保存快照
# '$!' : Shell最后运行的后台Process的PID 
#---------------------------------------------------------------------#
# 启动服务
start_app(){
    app_pid=`ps -ef|grep $app_jar|grep -v grep|grep -v kill|awk '{print $2}'`
    if [ ${app_pid} ]; then
        echo ${app_jar}" is running, pid = "${app_pid}
    else
        if [ -e ${app_jar} ]; then
            # 日志文件夹不存在，则创建
            if [ ! -d ${dump_path} ]; then
                mkdir -p ${dump_path}
            fi
            
            rm -f ${app_name}.pid
            nohup java -jar -server -Xms512m -Xmx512m -Xmn256m -Xss2m         \
                -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${dump_path} \
                --spring.profiles.active=${RUN_ENV}                           \
                --jasypt.encryptor.password=${ENC_KEY}                        \
                ${app_jar} >/dev/null 2>&1 &

            echo $! > ${app_name}.pid
            
            # 打印运行状态
            status_app
        else
            echo ${app_jar}" is not exist..."
        fi
    fi
}

# 停止服务
stop_app(){
    # app_pid=$(cat ${app_name}.pid)
    app_pid=`ps -ef|grep $app_jar|grep -v grep|grep -v kill|awk '{print $2}'`
    if [ ${app_pid} ]; then
        echo ${app_jar}" is beginning to be killed normally, pid = "${app_pid}
        kill -15 ${app_pid}
    fi
    sleep 3

    app_pid=`ps -ef|grep $app_jar|grep -v grep|grep -v kill|awk '{print $2}'`
    if [ ${app_pid} ]; then
        echo ${app_jar}" is beginning to be killed forcibly, pid = "${app_pid}
        kill -9 ${app_pid}
        
        sleep 3
        echo ${app_jar}" has been killed..."
    else
        echo ${app_jar}" is not running..."
    fi
    rm -f ${app_name}.pid
}

# 查看服务状态
status_app(){
    app_pid=`ps -ef|grep $app_jar|grep -v grep|grep -v kill|awk '{print $2}'`
    if [ ${app_pid} ]; then
        echo ${app_jar}" is running, pid = "${app_pid}
    else
        echo ${app_jar}" is not running..."
    fi
}

# 重启服务
restart_app(){
  stop_app
  start_app
}

case "${command}" in 
  "start") 
    start_app 
    ;; 
  "stop") 
    stop_app 
    ;; 
  "status") 
    status_app 
    ;; 
   "restart") 
    restart_app 
    ;; 
  *)
    echo "NOTE: sh coding-spring-test.sh [start|stop|status|restart]"
    ;;
esac

echo "------------------------------------------"
echo "执行结束..."
echo "=========================================="

end_time=`date +"%s"`
echo "script end_time: " $(date +"%Y-%m-%d %H:%M:%S")
echo "script exec_cost: " $((end_time - begin_time))