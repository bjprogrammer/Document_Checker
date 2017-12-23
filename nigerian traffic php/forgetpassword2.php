<?php
     require_once 'config.php';
       $conn = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);
         
         $email=$_POST["email"];
         $password =$_POST["password"];
         $OTP1=$_POST["OTP"];
             
             if (!filter_var($email, FILTER_VALIDATE_FLOAT)=== false) 
            {
             $mysqliselect1="Select OTP from npforgetpassword where mobile='$email' ";
             $result1=mysqli_query($conn,$mysqliselect1);
             if(mysqli_num_rows($result1)>0)
             {
                while($row=mysqli_fetch_array($result1))
                { 
	           $OTP=$row[0];
                }
                
                if($OTP1==$OTP)
                {
                  if(mysqli_query($conn, "UPDATE npsignin SET password='$password' WHERE mobile='$email'"))
                  {
                    echo "Password changed successfully";
                  } 
                  else 
                  {
                     echo "Error";
                  }
                }  
               else 
               {
                     echo "Please enter correct OTP";
               }
      }
      }
      else if(filter_var($email, FILTER_VALIDATE_EMAIL)) 
   {
   $mysqliselect1="Select OTP from npforgetpassword where email='$email' ";
             $result1=mysqli_query($conn,$mysqliselect1);
             if(mysqli_num_rows($result1)>0)
             {
                while($row=mysqli_fetch_array($result1))
                { 
	           $OTP=$row[0];
                }
                
                if($OTP1==$OTP)
                {
                  if(mysqli_query($conn, "UPDATE npsignin SET password='$password' WHERE email='$email'"))
                  {
                    echo "Password changed successfully";
                  } 
                  else 
                  {
                     echo "Error";
                  }
                }  
               else 
               {
                     echo "Please enter correct OTP";
               }
      }
   
   }
         
           mysqli_close($conn);
      ?>