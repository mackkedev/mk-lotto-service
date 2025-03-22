echo "Initializing job!"

agent any

node {
    stage("checkout"){
    sh "ls"
    git branch: 'main', url: 'https://github.com/mackkedev/mk-lotto-service'
    sh "ls"
    }

    stage("build"){
        sh "mvn package"
    }

    stage("tests"){
        sh "mvn clean install"
    }

    stage("capture"){
        archiveArtifacts artifacts: '**/target/*.jar'
        junit '**/target/surefire-reports/TEST*.xml'
    }

    stage("post action"){}
}