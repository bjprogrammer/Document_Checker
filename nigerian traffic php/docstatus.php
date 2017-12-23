<?php
require_once 'config.php';
$conn = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);

$username=$_POST["username"];


   $res=array("Fetching documents","Error");
   
   if(filter_var($username, FILTER_VALIDATE_EMAIL)) {
        $mysqliselect="Select * from npdocumentupload where email='$username'";
    }
    else if (!filter_var($username, FILTER_VALIDATE_FLOAT) === false) 
    {
       $mysqliselect="Select * from npdocumentupload where mobile='$username'";
    } 
    else 
    {
      $mysqliselect="Select * from npdocumentupload where username='$username'";
    }
  
  $result=mysqli_query($conn,$mysqliselect);
  if(mysqli_num_rows($result)>0)
  { 
             echo ($res[0]);
             $response=array();
             while($row=mysqli_fetch_array($result))
             {
                if($row[5]=="Verified") 
                {
                  if((time() >strtotime($row[4]))
                  {
                   $row[5]="Expired";
                  }
                  else if(strtotime($row[4])-time()<(60*60*24*30))
                  {
                    $row[5]="About to Expire";
                  }
                }
                
                if($row[8]=="Verified") 
                {
                 if((time() >strtotime($row[7]))
                  {
                   $row[8]="Expired";
                  }
                  else if(strtotime($row[7])-time()<(60*60*24*30))
                  {
                    $row[8]="About to Expire";
                  }
                }
                
                if($row[13]=="Verified") 
                {
                 if((time() >strtotime($row[14]))
                  {
                   $row[13]="Expired";
                  }
                  else if(strtotime($row[14])-time()<(60*60*24*30))
                  {
                    $row[13]="About to Expire";
                  }
                }
                
                if($row[16]=="Verified") 
                {
                 if((time() >strtotime($row[17]))
                  {
                   $row[16]="Expired";
                  }
                  else if(strtotime($row[17])-time()<(60*60*24*30))
                  {
                    $row[16]="About to Expire";
                  }
                }
                
                if($row[19]=="Verified") 
                {
                 if((time() >strtotime($row[20]))
                  {
                   $row[19]="Expired";
                  }
                  else if(strtotime($row[20])-time()<(60*60*24*30))
                  {
                    $row[19]="About to Expire";
                  }
                }
                
                if($row[21]=="Verified") 
                {
                 if((time() >strtotime($row[23]))
                  {
                   $row[21]="Expired";
                  }
                  else if(strtotime($row[23])-time()<(60*60*24*30))
                  {
                    $row[21]="About to Expire";
                  }
                }
                
                if($row[25]=="Verified") 
                {
                 if((time() >strtotime($row[26]))
                  {
                   $row[25]="Expired";
                  }
                  else if(strtotime($row[26])-time()<(60*60*24*30))
                  {
                    $row[25]="About to Expire";
                  }
                }
                
                if($row[28]=="Verified") 
                {
                 if((time() >strtotime($row[29]))
                  {
                   $row[28]="Expired";
                  }
                  else if(strtotime($row[29])-time()<(60*60*24*30))
                  {
                    $row[28]="About to Expire";
                  }
                }
                
                if($row[10]=="Verified") 
                {
                 if((time() >strtotime($row[11]))
                  {
                   $row[10]="Expired";
                  }
                  else if(strtotime($row[11])-time()<(60*60*24*30))
                  {
                    $row[10]="About to Expire";
                  }
                }
               	array_push($response,array("drivinglicense"=>$row[3],"drivinglicensestatus"=>$row[5],"tyreexpiration"=>$row[6],"tyreexpirationstatus"=>$row[8],"viodocument"=>$row[9],"viodocumentstatus"=>$row[10],
                "insurance"=>$row[12],"insurancestatus"=>$row[13],"fireextinguisher"=>$row[15],"fireextinguisherstatus"=>$row[16],"hackneypermit"=>$row[18],"hackneypermitstatus"=>$row[19],
                "ownershipproofstatus"=>$row[21],"ownershipproof"=>$row[22],"policepermit"=>$row[24],"policepermitstatus"=>$row[25],"tintedglasspermit"=>$row[27],"tintedglasspermitstatus"=>$row[28]));
             }
             echo json_encode(array("server_response"=>$response));     
   }

  else 
  {
              echo ($res[1]);
  }    
  mysqli_close($conn);
?>