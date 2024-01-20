pipeline {
    agent any

    environment {
    SVC_ACCOUNT_KEY = credentials('Jenkins-secret')
  }
     
    stages {
      	stage('Set creds') {
            steps {
              
                sh 'echo $SVC_ACCOUNT_KEY | base64 -d > ./jenkins.json'
		        sh 'pwd'
                       
               
            }
        }

	
	stage('Auth-Project') {
	 steps {
    
        sh 'gcloud auth activate-service-account jenkins1@kalyanproject-dev.iam.gserviceaccount.com --key-file=jenkins.json'
    }
    }
	
 	 
	stage('Create Instance') {
	 steps {
    
    sh 'gcloud compute instances create test2 --zone=us-central1-b --tags=http-server --scopes=storage-ro --metadata-from-file=startup-script=./startupscript.sh'
      
    }
    }

    stage('Instance list') {
	 steps {
    
    sh 'gcloud compute instances list'
        
    }
    }

    stage('Collect External IP') {
steps {

sh "gcloud compute instances describe test2 --zone=us-central1-b --format='get(networkInterfaces[0].accessConfigs[0].natIP)' > ip.txt"
sh 'cat ip.txt'

}
}



stage('App health check') {
steps {
sh 'sleep 240'
sh 'curl http://$(cat ip.txt)'

}
}
    
   }
}
