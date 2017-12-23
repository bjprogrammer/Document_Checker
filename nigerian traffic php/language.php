<?php
require_once 'config.php';
$conn = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);

$username=$_POST["username"];
$language=$_POST["language"];

   $res=array("Your settings are adjusted","Error");
   
   if(filter_var($username, FILTER_VALIDATE_EMAIL)) {
        $mysqliupdate="UPDATE npsettings SET language='$language', created_at=Now() WHERE email='$username'";
    }
    else if (!filter_var($username, FILTER_VALIDATE_FLOAT) === false) 
    {
        $mysqliupdate="UPDATE npsettings SET language='$language', created_at=Now() WHERE mobile='$username'";
    } 
    else 
    {
        $mysqliupdate="UPDATE npsettings SET language='$language', created_at=Now() WHERE username='$username'";
    }
  
  $result=mysqli_query($conn,$mysqliupdate);
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