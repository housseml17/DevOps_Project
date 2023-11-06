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




stage('Test & Jacoco Static Analysis') {
    	    steps {
               // Analysis of Test using Junit + Jacoco (Mockito + Junit)
          	  	junit 'target/surefire-reports/*.xml'
          		jacoco()
		   }
        }
stage('Run Spring && MySQL Containers') {
                                steps {
                                    script {
                                      sh 'docker-compose up -d'
                                    }
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





stage('Build Angular') {
            steps {
                dir('front') {
                    sh 'npm install'
                    sh 'npm run build '
                }
            }
        }


        stage("clone frontend"){
         steps{
             script{
                   checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url:"https://github.com/housseml17/front.git"


]]])
             }
         }



     }

stage("build and push frontend docker image") {


            steps {
                script {

              echo "connexion"

              def dockerUsername ="toumi15"
              def dockerPassword = "Toutvabien97818410 . ."



            sh " docker login -u ${dockerUsername} -p ${dockerPassword} "




            echo "Building Docker image..."
             sh "docker build --no-cache -t front:latest ."


            echo "renommer l'image"
             sh "docker tag front:latest aziz1123/front-app"
            echo "Pushing Docker image to Docker Hub..."
             sh "docker push toumi15/front-app:latest"

            echo "Docker image successfully pushed to Docker Hub."
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

