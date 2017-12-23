<?php
     require_once 'config.php';
     $conn = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);
         
         $email=$_POST["email"];
         $res=array("logged in","Check your username and password");
   
         function randomPassword() 
         {
               $alphabet = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890';
               $pass = array(); 
               $alphaLength = strlen($alphabet) - 1; //put the length -1 in cache
               for ($i = 0; $i < 8; $i++) 
               {
                    $n = rand(0, $alphaLength);
                    $pass[] = $alphabet[$n];
               }
               return implode($pass); 
         }
        
        
    if (!filter_var($email, FILTER_VALIDATE_FLOAT)=== false) 
    {
       $mysqliselect1="Select mobile from npsignin where mobile='$email' ";
       $result1=mysqli_query($conn,$mysqliselect1);
       if(mysqli_num_rows($result1)>0)
       {
           $OTP= randomPassword();
           //if(mail ($to,$subject,$message,$header))
           {
             
             $mysqliselect2="Select OTP from npforgetpassword where mobile='$email' ";
             $result2=mysqli_query($conn,$mysqliselect2);
             if(mysqli_num_rows($result2)>0)
             {
             
               if(mysqli_query($conn, "UPDATE npforgetpassword SET OTP='$OTP' WHERE mobile='$email'"))
                {
                  echo "OTP sent successfully on your registered mobile no.";
                } 
                else 
                {
                   echo "Error";
                }
             }
             else
             {
                $mysqlinsert="Insert into npforgetpassword(mobile,OTP,created_at) values('$email','$OTP',Now())" ;
                if(mysqli_query($conn,$mysqlinsert))
                {
                  echo "OTP sent successfully on your registered mobile no.";
                } 
                else 
                {
                   echo "Error";
                }
             
             }
           }
           //else 
           {
              echo "Message could not be sent...";
           }
               
       }
       else 
       {
              echo "mobile no. not registered";
       }
               
    } 
   else if(filter_var($email, FILTER_VALIDATE_EMAIL)) 
   {
  
         $to =$email;
         $subject = "Password reset instructions";
         
         $header = "From:piyush@sixthsenseit.com \r\n";
         $header .= "Cc:".$email." \r\n";
         $header .= "MIME-Version: 1.0\r\n";
         $header .= "Content-type: text/html\r\n";

         $mysqliselect1="Select email from npsignin where email='$email' ";
         $result1=mysqli_query($conn,$mysqliselect1);
         if(mysqli_num_rows($result1)>0)
         {
               $OTP= randomPassword();
               $message = "Use this OTP to reset your password.<br><b>OTP- ".$OTP. "</b><br><br>Please do not reply to this email.This is an auto-generated email.";
           //if(mail ($to,$subject,$message,$header))
           {
             
             $mysqliselect2="Select OTP from npforgetpassword where email='$email' ";
             $result2=mysqli_query($conn,$mysqliselect2);
             if(mysqli_num_rows($result2)>0)
             {
             
               if(mysqli_query($conn, "UPDATE npforgetpassword SET OTP='$OTP' WHERE email='$email'"))
                {
                  echo "OTP sent successfully on your registered email-id";
                } 
                else 
                {
                   echo "Error";
                 }
                 }
             else
             {
                $mysqlinsert="Insert into npforgetpassword(email,OTP,created_at) values('$email','$OTP',Now())" ;
                if(mysqli_query($conn,$mysqlinsert))
                {
                  echo "OTP sent successfully on your registered email-id";
                } 
                else 
                {
                   echo "Error";
                }
             
             }
           }
          // else 
           {
              echo "Message could not be sent...";
           }
         
           }
           else 
           {
              echo "email-id not registered";
           }
         
         }
           mysqli_close($conn);
      ?>