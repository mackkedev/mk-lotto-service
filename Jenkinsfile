echo "Initializing job!"


node {
    stage("checkout"){
    sh "ls"
    git branch: 'main', url: 'https://github.com/mackkedev/mk-lotto-service'
    sh "ls"
    }

    stage("build"){
        sh "mvn package"
    }

    stage("capture"){
        archiveArtifacts artifacts: '**/target/*.jar'
        junit '**/target/surefire-reports/TEST*.xml'
    }
}