# This is the provider used to spin up the VM instance
provider "google" {
 
  project = "kalyanproject-dev"
  credentials = file("terraform.json")
  region  = "us-central1"
  
}

resource "google_compute_instance" "vm-instance1" {
  name         = "myfirstvm"
  machine_type = "n1-standard-1"
  zone         = "us-central1-a"
  tags = ["web-app"]

 network_interface {
    network = "default"

     }
  }
}  
