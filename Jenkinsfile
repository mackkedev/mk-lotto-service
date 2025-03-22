echo "Initializing job!"


pipeline {
    agent any  // Runs on any available Jenkins agent

    environment {
        MAVEN_HOME = tool 'Maven'  // Ensure Maven is installed in Jenkins
    }
    stages {
        stage("Checkout code"){
        sh "ls"
        git branch: 'main', url: 'https://github.com/mackkedev/mk-lotto-service'
        sh "ls"
        }

        stage("Build with maven"){
            sh "mvn package"
        }

        stage("Run tests"){
            sh "mvn clean install"
        }

        stage("capture"){
            archiveArtifacts artifacts: '**/target/*.jar'
            junit '**/target/surefire-reports/TEST*.xml'
        }
    }

    post {
            success {
                echo "Build and tests completed successfully!"
            }
            failure {
                echo "Build failed! Check logs for errors."
            }
        }
}