<?php
require_once 'config.php';
$conn = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);

$username=$_POST["username"];
$pic=$_POST["pic"];
$mobile=$_POST["mobile"];
$email=$_POST["email"];

   $res=array("Profile updated successfully","Error");
   
   if(filter_var($username, FILTER_VALIDATE_EMAIL)) {
        $mysqliupdate="UPDATE npsignin SET mobile='$mobile', email='$email', pic='$pic' WHERE email='$username'";
        $mysqliupdate2="UPDATE npdocumentupoad SET mobile='$mobile', email='$email' WHERE email='$username'";
        $mysqliupdate3="UPDATE npemaillverification SET mobile='$mobile', email='$email' WHERE email='$username'";
        $mysqliupdate4="UPDATE npforgetpassword SET mobile='$mobile', email='$email' WHERE email='$username'";
        $mysqliupdate5="UPDATE npmobileverification SET mobile='$mobile', email='$email' WHERE email='$username'";
        $mysqliupdate6="UPDATE npsettings SET mobile='$mobile', email='$email' WHERE email='$username'";
       
    }
    else if (!filter_var($username, FILTER_VALIDATE_FLOAT) === false) 
    {
        $mysqliupdate="UPDATE npsignin SET mobile='$mobile', email='$email', pic='$pic' WHERE mobile='$username'";
        $mysqliupdate2="UPDATE npdocumentupoad SET mobile='$mobile', email='$email' WHERE email='$username'";
        $mysqliupdate3="UPDATE npemaillverification SET mobile='$mobile', email='$email' WHERE email='$username'";
        $mysqliupdate4="UPDATE npforgetpassword SET mobile='$mobile', email='$email' WHERE email='$username'";
        $mysqliupdate5="UPDATE npmobileverification SET mobile='$mobile', email='$email' WHERE email='$username'";
        $mysqliupdate6="UPDATE npsettings SET mobile='$mobile', email='$email' WHERE email='$username'";
    } 
    else 
    {
        $mysqliupdate="UPDATE npsignin SET mobile='$mobile', email='$email', pic='$pic' WHERE username='$username'";
        $mysqliupdate2="UPDATE npdocumentupoad SET mobile='$mobile', email='$email' WHERE email='$username'";
        $mysqliupdate3="UPDATE npemaillverification SET mobile='$mobile', email='$email' WHERE email='$username'";
        $mysqliupdate4="UPDATE npforgetpassword SET mobile='$mobile', email='$email' WHERE email='$username'";
        $mysqliupdate5="UPDATE npmobileverification SET mobile='$mobile', email='$email' WHERE email='$username'";
        $mysqliupdate6="UPDATE npsettings SET mobile='$mobile', email='$email' WHERE email='$username'";
    }
  
  $result=mysqli_query($conn,$mysqliupdate);
  $result=mysqli_query($conn,$mysqliupdate2);
  $result=mysqli_query($conn,$mysqliupdate3);
  $result=mysqli_query($conn,$mysqliupdate4);
  $result=mysqli_query($conn,$mysqliupdate5);
  $result=mysqli_query($conn,$mysqliupdate6);
  if($result)
  { 
             echo ($res[0]);
                
   }

  else 
  {
              echo ($res[1]);
  }    
  mysqli_close($conn);
?>