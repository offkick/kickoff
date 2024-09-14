pipeline {
    agent any
    stages {
        stage('github-clone') {
            steps {
                git branch: 'main', credentialsId: 'deckurodev', url: 'https://github.com/offkick/kickoff.git'
            }
        }

        stage('echo test') {
            steps {
                echo 'Hello, JDK'
                sh 'echo $PWD'
            }
        }

        stage('Gradle Build') {
            steps {
                // Gradle 빌드 실행
                sh './gradlew kick-off-batch:bootJar -x test'
            }
        }
   	}
}