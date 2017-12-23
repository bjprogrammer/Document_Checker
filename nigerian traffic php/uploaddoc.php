<?php
require_once 'config.php';
$conn = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);

$username=$_POST["username"];
$drivinglicense=$_POST["drivinglicense"];
$tyreexpiration=$_POST["tyreexpiration"];
$fireextinguisher=$_POST["fireextinguisher"];
$viodocument=$_POST["viodocument"];
$insurance=$_POST["insurance"];
$hackneypermit=$_POST["hackneypermit"];
$ownershipproof=$_POST["ownershipproof"];
$policepermit=$_POST["policepermit"];
$tintedglasspermit=$_POST["tintedglasspermit"];

   $res=array("Documents uploaded successfully","Error");
   
   if(filter_var($username, FILTER_VALIDATE_EMAIL)) 
   {
        $mysqliupdate="UPDATE npdocumentupload SET dlpic='$drivinglicense', tepic='$tyreexpiration', fepic='$fireextinguisher', 
        viodocpic='$viodocument', insurancepic='$insurance', hppic='$hackneypermit', oppic='$ownershipproof', pppic='$policepermit',
        tgppic='$tintedglasspermit',dlstatus='Under Verification',testatus='Under Verification',festatus='Under Verification',
        viodocstatus='Under Verification',insurancestatus='Under Verification',hpstatus='Under Verification',
        opstatus='Under Verification',ppstatus='Under Verification',tgpstatus='Under Verification' WHERE email='$username'";
    }
    else if (!filter_var($username, FILTER_VALIDATE_FLOAT) === false) 
    {
        $mysqliupdate="UPDATE npdocumentupload SET dlpic='$drivinglicense', tepic='$tyreexpiration', fepic='$fireextinguisher', 
        viodocpic='$viodocument', insurancepic='$insurance', hppic='$hackneypermit', oppic='$ownershipproof', pppic='$policepermit',
        tgppic='$tintedglasspermit',dlstatus='Under Verification',testatus='Under Verification',festatus='Under Verification',
        viodocstatus='Under Verification',insurancestatus='Under Verification',hpstatus='Under Verification',
        opstatus='Under Verification',ppstatus='Under Verification',tgpstatus='Under Verification' WHERE mobile='$username'";
    } 
    else 
    {
        $mysqliupdate="UPDATE npdocumentupload SET dlpic='$drivinglicense', tepic='$tyreexpiration', fepic='$fireextinguisher', 
        viodocpic='$viodocument', insurancepic='$insurance', hppic='$hackneypermit', oppic='$ownershipproof', pppic='$policepermit',
        tgppic='$tintedglasspermit',dlstatus='Under Verification',testatus='Under Verification',festatus='Under Verification',
        viodocstatus='Under Verification',insurancestatus='Under Verification',hpstatus='Under Verification',
        opstatus='Under Verification',ppstatus='Under Verification',tgpstatus='Under Verification' WHERE username='$username'";
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