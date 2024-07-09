#!/bin/bash
echo "> KICK OFF 프로젝트 어플리케이션 배포 시작"

BUILD_PATH=/home/ubuntu/kickoff/kick-off-api/build/kick-off-api-0.0.1-SNAPSHOT.jar
JAR_NAME=$(basename $BUILD_PATH)
echo "> build 파일명: $JAR_NAME"

echo "> build 파일 복사"
DEPLOY_PATH=/home/ubuntu/kickoff/kick-off-api/build/
cp $BUILD_PATH $DEPLOY_PATH

echo "> kickoff-0.0.1.jar 교체"
CP_JAR_PATH=$DEPLOY_PATH$JAR_NAME
APPLICATION_JAR_NAME=kickoff-0.0.1.jar
APPLICATION_JAR=$DEPLOY_PATH$APPLICATION_JAR_NAME

ln -Tfs $CP_JAR_PATH $APPLICATION_JAR

echo "> 현재 실행중인 애플리케이션 pid 확인"
CURRENT_PID=$(pgrep -f $APPLICATION_JAR_NAME)

# .jar 파일을 포함한 프로세스 목록을 찾기
processes=$(ps aux | grep '\.jar' | grep -v grep | awk '{print $2}')

# 찾은 프로세스가 있는지 확인
if [ -z "$processes" ]; then
  echo "No .jar processes found."
fi
# 프로세스 종료
echo "Killing the following .jar processes:"
echo "$processes"
for pid in $processes; do
  kill -9 $pid
  echo "Killed process $pid"
done

echo "> $APPLICATION_JAR 배포"

CURRENT_SERVER_ADDRESS=$(hostname -I)
echo "> CURRENT_SERVER_ADDRESS : $CURRENT_SERVER_ADDRESS"

echo "> 개발서버이므로 개발 환경으로 배포합니다."
java -jar $BUILD_PATH > /dev/null 2> /dev/null < /dev/null --spring.profiles.active=prod &


echo "> 서버가 성공적으로 배포되었습니다. "