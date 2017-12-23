<?php
require_once 'config.php';
$conn = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);

$username=$_POST["username"];
$otp=$_POST["otp"];

   $res=array("Mobile no. verified","OTP is not correct");
   
   
   if(filter_var($username, FILTER_VALIDATE_EMAIL)) 
   {
    $mysqliselect="Select * from npmobileverification WHERE OTP='$otp' and email='$username'";
   }
   else if (!filter_var($username, FILTER_VALIDATE_FLOAT) === false) 
   {
    $mysqliselect="Select * from npmobileverification WHERE OTP='$otp' and mobile='$username'";
   }
   else
   {
    $mysqliselect="Select * from npmobileverification WHERE OTP='$otp' and username='$username'";
   }
    
   $result=mysqli_query($conn,$mysqliselect); 
   if(mysqli_num_rows($result)>0)
   {
    if(filter_var($username, FILTER_VALIDATE_EMAIL)) {
        $mysqliupdate="UPDATE npmobileverification SET mobileverification='yes', created_at=Now() WHERE OTP='$otp' and email='$username'";
    }
    else if (!filter_var($username, FILTER_VALIDATE_FLOAT) === false) 
    {
        $mysqliupdate="UPDATE npmobileverification SET mobileverification='yes', created_at=Now() WHERE OTP='$otp' and mobile='$username'";
    } 
    else 
    {
       $mysqliupdate="UPDATE npmobileverification SET  mobileverification='yes', created_at=Now() WHERE OTP='$otp' and username='$username'";
    }
    $result=mysqli_query($conn,$mysqliupdate);
    echo ($res[0]); 
   }
   else 
  {
              echo ($res[1]);
  }
      
  mysqli_close($conn);
?>