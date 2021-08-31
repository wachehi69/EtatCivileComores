pipeline{
agent any 
stages{
  stage('Build') { 
    steps{
    sh '/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/Maven3.8.2/bin/mvn  clean install' 
    }
  }
  stage('Test') { 
    steps{
    sh '/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/Maven3.8.2/bin/mvn  test' 
      }
    }
           
  }
}