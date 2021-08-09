#!/bin/bash
#进入本目录
path=/home/lv/AndroidStudioProjects/Tengbi
cd $path
#执行打包命令
git pull origin master
if [ $? -eq 0 ]; then
    ./gradlew assembleReRelease >/home/lv/1.txt
    if [ $? -eq 0 ]; then
        echo `tree app/build/outputs/apk`
        #拷贝打包文件到指定位置
        cp $path/app/build/outputs/apk/re/release/$1 /home/lv/html/release/
        echo "打包成功"
    else
        echo "打包失败"
    fi
else
    echo "下拉代码失败"
fi
