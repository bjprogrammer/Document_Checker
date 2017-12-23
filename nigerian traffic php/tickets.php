<?php
require_once 'config.php';
$conn = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);

$username=$_POST["username"];


   $res=array("Fetching records","No record found");
   
   if(filter_var($username, FILTER_VALIDATE_EMAIL)) {
        $mysqliselect="Select * from npticketrecord where email='$username'";
    }
    else if (!filter_var($username, FILTER_VALIDATE_FLOAT) === false) 
    {
       $mysqliselect="Select * from npticketrecord where mobile='$username'";
    } 
    else 
    {
      $mysqliselect="Select * from npticketrecord where username='$username'";
    }
  
  $result=mysqli_query($conn,$mysqliselect);
  if(mysqli_num_rows($result)>0)
  { 
             echo ($res[0]);
             $response=array();
             while($row=mysqli_fetch_array($result))
             {
               	array_push($response,array("time"=>$row[0],"title"=>$row[1],"genre"=>$row[2],"id"=>$row[3],"location"=>$row[4],"issue"=>$row[5]));
             }
             echo json_encode(array("server_response"=>$response));     
   }

  else 
  {
              echo ($res[1]);
  }    
  mysqli_close($conn);
?>