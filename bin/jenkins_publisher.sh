#!/usr/bin/env bash
#----------------------------------#
# 功能说明: jenkins发布执行脚本, sh app_name.sh build_num app_name
#          执行脚本钱需要在jenkins中执行[source /etc/profile]加载环境变量
# 作者    : lwk
# 日期    : 2020-1-10 22:01:05
#----------------------------------#

begin_time=`date +"%s"`
echo "script begin_time: " $(date +"%Y-%m-%d %H:%M:%S")

echo "=========================================="
echo "jenkins远程执行开始..."
echo "------------------------------------------"

# 系统日期
sys_date=`date +"%Y%m%d"`
echo "系统日期: ${sys_date}"

# 构建版本号
build_num=$1
echo "构建版本号: ${build_num}"

# 应用名
app_name=$2
app_version=1.0.0
echo "应用版本名: ${app_name}-${app_version}"

app_tar="/home/coding/app/${app_name}-${app_version}.tar.gz"
if [ ! -e ${app_tar} ]; then
  echo "${app_tar##*/} is not exist..."
  exit 1
fi

# jenkins远程执行命令式需要手动加载下远程服务器的系统变量
#source /etc/profile
# 检查运行环境变量
env_label=${ENV_LABEL}
if [ -z ${env_label} ]; then
  echo "ENV_LABEL is not set up or system variable read failed..."
  exit 1
fi
echo "运行环境: "${env_label}

# 先关闭运行中的服务再发布新的版本程序
app_dir=/home/coding/app/${app_name}
sh ${app_dir}/bin/${app_name}.sh stop
rm -rf ${app_dir}
mkdir -p ${app_dir}
tar -zxvf /home/coding/app/${app_name}-${app_version}.tar.gz -C ${app_dir}
echo "${app_tar} decompression completed~"

sh ${app_dir}/bin/${app_name}.sh start

echo "------------------------------------------"
echo "jenkins远程执行结束..."
echo "=========================================="

end_time=`date +"%s"`
echo "script end_time: " $(date +"%Y-%m-%d %H:%M:%S")
echo "script exec_cost: " $((end_time - begin_time))