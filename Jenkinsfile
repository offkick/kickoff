pipeline {
    agent any
    stages {
        stage('github-clone') {
            steps {
                git branch: 'main', credentialsId: 'deckurodev', url: 'https://github.com/offkick/kickoff.git'
            }
        }

        stage('echo test') {
            sh '''echo $PWD
            echo before start build'''
        }
   	}
}