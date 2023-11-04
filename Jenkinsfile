pipeline{
    agent any



    stages {


        stage('Getting project from Git') {
            steps{
      			checkout([$class: 'GitSCM', branches: [[name: '*/main']],
			extensions: [],
			userRemoteConfigs: [[url: 'https://github.com/housseml17/DevOps_Project.git']]])
            }
        }


       stage('Cleaning the project') {
            steps{
                	sh "mvn -B -DskipTests clean  "
            }
        }



        stage('Artifact Construction') {
            steps{
                	sh "mvn -B -DskipTests package "
            }
        }



         stage('JUnit / Mockito') {
            steps{
               		 sh "mvn test "
            }
        }



        stage('SONARQUBE') {
            steps{

             		sh " mvn clean verify sonar:sonar -Dsonar.projectKey=DevOps_Project -Dsonar.projectName='DevOps_Project' -Dsonar.host.url=http://192.168.56.2:9000 -Dsonar.token=sqp_313fd4947b5c9b8ca0ba0214ca027607fd9955f8 "
            }
        }


        stage('Publish to Nexus') {
            steps {


  sh 'mvn deploy'


            }
        }

stage('Build Docker Image') {
                      steps {
                          script {
                            sh 'docker build -t toumi15/spring-app:Toumi .'
                          }
                      }
                  }

                  stage('login dockerhub') {
                                        steps {
                                     // sh 'echo dckr_pat_-SnwrdC_ELsL6it2JT6cgIcAlrs | docker login -u azizbenhaha --password-stdin'
				sh 'docker login -u toumi15 --password dckr_pat_0iaom9peVjYUg0VIvUkeT-5V4bg'
                                            }
		  }

	                      stage('Push Docker Image') {
                                        steps {
                                   sh 'docker push toumi15/spring-app:Toumi'
                                            }
		  }


		   stage('Run Spring && MySQL Containers') {
                                steps {
                                    script {
                                      sh 'docker-compose up -d'
                                    }
                                }
                            }






}


    /*    post {
		success{
		mail bcc: '', body: '''Dear Houssem Toumi,
we are happy to inform you that your pipeline build was successful.
Great work !
-Jenkins Team-''', cc: '', from: 'houssem.toumi@@esprit.tn', replyTo: '', subject: 'Build Finished - Success', to: 'houssem.toumi@@esprit.tn'
		}

		failure{
mail bcc: '', body: '''Dear  Houssem Toumi,
we are sorry to inform you that your pipeline build failed.
Keep working !
-Jenkins Team-''', cc: '', from: 'houssem.toumi@@esprit.tn', replyTo: '', subject: 'Build Finished - Failure', to: 'houssem.toumi@@esprit.tn'
		}

       always {
		emailext attachLog: true, body: '', subject: 'Build finished',from: 'houssem.toumi@@esprit.tn' , to: 'houssem.toumi@@esprit.tn'
            cleanWs()
       }
    }

*/

}

