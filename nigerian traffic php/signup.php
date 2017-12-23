<?php
require_once 'config.php';
$conn = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);

$username=$_POST["username"];
$mobile=$_POST["mobile"];
$email=$_POST["email"];
$password=$_POST["password"];
$pic=$_POST["pic"];
$state=$_POST["state"];
$lga=$_POST["LGA"];
$address=$_POST["address"];
$name=$_POST["name"];

  $response=array("Successfully registered","Already registered");
  $mysqlinsert="Insert into npsignin(username,mobile,password,email,state,LGA,address,pic,name,created_at) values('$username','$mobile','$password','$email','$state','$lga','$address','$pic','$name',Now())" ;
  if(mysqli_query($conn,$mysqlinsert))
  {
              $mysqlinsert2="Insert into npemailverification(username,mobile,email) values('$username','$mobile','$email')" ;
              if(mysqli_query($conn,$mysqlinsert2))
              {
               $mysqlinsert3="Insert into npmobileverification(username,mobile,email) values('$username','$mobile','$email')" ;
               if(mysqli_query($conn,$mysqlinsert3))
               {
               
                 $mysqlinsert4="Insert into npsettings(username,mobile,email) values('$username','$mobile','$email')" ;
                 if(mysqli_query($conn,$mysqlinsert4))
                 {
                  $mysqlinsert5="Insert into npdocumentupload(username,mobile,email,dlstatus,testatus,viodocstatus,insurancestatus,festatus,hpstatus,opstatus,ppstatus,tgpstatus) values('$username','$mobile','$email','Not Uploaded','Not Uploaded','Not Uploaded','Not Uploaded','Not Uploaded','Not Uploaded','Not Uploaded','Not Uploaded','Not Uploaded')" ;
                  if(mysqli_query($conn,$mysqlinsert5))
                  {
                   $mysqlinsert6="Insert into npforgetpassword(username,mobile,email) values('$username','$mobile','$email')" ;
                   if(mysqli_query($conn,$mysqlinsert6))
                   {
                     
                      /* $activation=md5($email.time()); // encrypted email+timestamp
                   
                       mysqli_query($conn,"INSERT INTO npemailverification(emailverified,activation,created_at) VALUES('no','$activation',Now()) where email=$email");
                      
                       
                       $to=$email;
                       $subject="Email verification";
                       $header = "From:piyush@sixthsenseit.com \r\n";
                       $header .= "Cc:".$email." \r\n";
                       $header .= "MIME-Version: 1.0\r\n";
                       $header .= "Content-type: text/html\r\n";
                       
                       $body='Hi, <br/> <br/> We need to make sure you are human. Please verify your email and get started using your app account. <br/> <br/> <a href="'.$base_url.'activation/'.$activation.'">'.$base_url.'activation/'.$activation.'</a>';

                       mail ($to,$subject,$body,$header)  */        
                     
                    echo $response[0];
                   }
                  }
                 }
                }
               }
 }
 else 
 {
  echo $response[1];
 }
  mysqli_close($conn);
?>