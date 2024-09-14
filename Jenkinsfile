pipeline {
    agent any
    stages {
        stage('github-clone') {
            steps {
                git branch: 'main', credentialsId: 'github_token', url: 'https://github.com/offkick/kickoff.git'
            }
        }
   	}
}