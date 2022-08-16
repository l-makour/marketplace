node {
    stage("checkout") {
        checkout([$class: 'GitSCM', branches: [[name: '*/develop']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/l-makour/marketplace.git']]])
    }
    stage("build") {
        sh "chmod 777 ./mvnw && ./mvnw clean package"
        stash includes: 'target/*.jar', name: 'livrable'
        stash includes: 'Dockerfile', name: 'Dockerfile'

    }

    node("vm-int") {
        stage("build docker image") {
            unstash 'livrable'
            unstash 'Dockerfile'
            sh "sudo docker build -t marketplace ."
        }

        stage("deploy application") {
            try{
                sh "docker stop marketplace"
                sh "docker rm marketplace"
                sh "sudo docker run --name marketplace -p 20000:9090 marketplace &"
            }catch(Exception e){
                sh "sudo docker run --name marketplace -p 20000:9090 marketplace &"
            }
        }
    }
}
