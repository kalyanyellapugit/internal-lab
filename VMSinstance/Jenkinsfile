pipeline {
    agent any


    environment {
    SVC_ACCOUNT_KEY = credentials('Jenkins-secret')
  }

    stages {
          

	stage('Set Terraform path') {
            steps {
                script {
                    def tfHome = tool name: 'Terraform'
                    env.PATH = "${tfHome}:${env.PATH}"
                }
                sh 'echo $SVC_ACCOUNT_KEY | base64 -d > ./VMSinstance/terraform.json'
                sh 'terraform --version'               
               
            }
        }




          stage('Initialize Terraform') {
		 steps {  
			  dir('VMSinstance')
			 {
                sh 'terraform init'
			 }
		 }
	 }

         stage('Terraform Action') {
		 steps {  
			  dir('VMSinstance')
			 {
		 sh 'terraform $TF_ACTIONS $TF_APPROVE'
				
			 }
		 }
	}

}
}
