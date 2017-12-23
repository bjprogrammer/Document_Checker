<?php
require_once 'config.php';
$conn = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);

$username=$_POST["username"];

  $mysqliselect="Select pic from npsignin where email='$email'";
  
  if(filter_var($username, FILTER_VALIDATE_EMAIL)) 
   {
        $mysqliselect="Select pic from npsignin where email='$username'";
    }
    else if (!filter_var($username, FILTER_VALIDATE_FLOAT)=== false) 
    {
       $mysqliselect="Select pic from npsignin where mobile='$username'";
    } 
    else 
    {
      $mysqliselect="Select pic from npsignin where username='$username'";
    }
    
  $result=mysqli_query($conn,$mysqliselect);
              while($row=mysqli_fetch_array($result))
              {
                   echo $row[0];
              }
      
  mysqli_close($conn);
?>