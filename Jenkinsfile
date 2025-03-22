pipeline {
    agent any  // Runs on any available Jenkins agent

    environment {
        MAVEN_HOME = tool 'Maven'  // Ensure Maven is installed in Jenkins
    }

    stages {
        stage("Run tests") {
            steps {
                sh "mvn clean install"
            }
        }

        stage("Build with Maven") {
            steps {
                sh "mvn package"
            }
        }

        stage("Capture Artifacts") {
            steps {
                archiveArtifacts artifacts: '**/target/*.jar'
                junit '**/target/surefire-reports/TEST*.xml'
            }
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