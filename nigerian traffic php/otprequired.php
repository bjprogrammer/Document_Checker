<?php
require_once 'config.php';
$conn = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);

$username=$_POST["username"];

    $res=array("already verified","not verified");
   
    if(filter_var($username, FILTER_VALIDATE_EMAIL)) 
    {
        $mysqliselect="SELECT  mobileverification from npmobileverification WHERE email='$username'";
    }
    else if (!filter_var($int, FILTER_VALIDATE_FLOAT) === false) 
    {
        $mysqliselect="SELECT  mobileverification from npmobileverification WHERE mobile='$username'";
    } 
    else 
    {
       $mysqliselect="SELECT mobileverification from npmobileverification WHERE username='$username'";
    }
  
  $result=mysqli_query($conn,$mysqliselect);
  if(mysqli_num_rows($result)>0)
  { 
             while($row=mysqli_fetch_array($result))
             {
               	if($row[0]=="yes")
                {
                 echo ($res[0]); 
                }
                
                else
                {
                 echo ($res[1]);
                }
             }                        
  }
     
  mysqli_close($conn);
?>