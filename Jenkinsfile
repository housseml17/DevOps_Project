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
              def dockerPassword = "dckr_pat_0iaom9peVjYUg0VIvUkeT-5V4bg"
              

            
            sh " docker login -u ${dockerUsername} -p ${dockerPassword} " 
            
         
            
           
            echo "Building Docker image..."
             sh "docker build  -t front:latest ." 
           

            echo "renommer l'image"
             sh "docker tag front:latest toumi15/front-app"
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

