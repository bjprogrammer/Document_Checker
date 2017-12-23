<?php
require_once 'config.php';
$conn = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);

$username=$_POST["username"];
$pic=$_POST["pic"];
$docname=$_POST["docname"];

   $res=array("Document uploaded successfully","Error");
   
   if(filter_var($username, FILTER_VALIDATE_EMAIL)) 
   {
   
        if($docname=='drivinglicense')
        {
          $mysqliupdate="UPDATE npdocumentupload SET dlpic='$pic', dlstatus='Under Verification' WHERE email='$username'";
        }
        else if($docname=='tyreexpiration')
        {
          $mysqliupdate="UPDATE npdocumentupload SET tepic='$pic', testatus='Under Verification' WHERE email='$username'";
        }
        else if($docname=='fireextinguisher')
        {
          $mysqliupdate="UPDATE npdocumentupload SET fepic='$pic', festatus='Under Verification' WHERE email='$username'";
        }
        else if($docname=='viodoc')
        {
          $mysqliupdate="UPDATE npdocumentupload SET viodocpic='$pic', viodocstatus='Under Verification' WHERE email='$username'";
        }
        else if($docname=='insurance')
        {
          $mysqliupdate="UPDATE npdocumentupload SET insurancepic='$pic', insurancestatus='Under Verification' WHERE email='$username'";
        }
        else if($docname=='hackneypermit')
        {
          $mysqliupdate="UPDATE npdocumentupload SET hppic='$pic', hpstatus='Under Verification' WHERE email='$username'";
        }
        else if($docname=='ownershipproof')
        {
          $mysqliupdate="UPDATE npdocumentupload SET oppic='$pic', opstatus='Under Verification' WHERE email='$username'";
        }
        else if($docname=='policepermit')
        {
          $mysqliupdate="UPDATE npdocumentupload SET pppic='$pic', ppstatus='Under Verification' WHERE email='$username'";
        }
        else if($docname=='tintedglasspermit')
        {
          $mysqliupdate="UPDATE npdocumentupload SET tgppic='$pic', tgpstatus='Under Verification' WHERE email='$username'";
        }
    } 
    else if (!filter_var($username, FILTER_VALIDATE_FLOAT) === false) 
    {
        if($docname=='drivinglicense')
        {
          $mysqliupdate="UPDATE npdocumentupload SET dlpic='$pic', dlstatus='Under Verification' WHERE mobile='$username'";
        }
        else if($docname=='tyreexpiration')
        {
          $mysqliupdate="UPDATE npdocumentupload SET tepic='$pic', testatus='Under Verification' WHERE mobile='$username'";
        }
        else if($docname=='fireextinguisher')
        {
          $mysqliupdate="UPDATE npdocumentupload SET fepic='$pic', festatus='Under Verification' WHERE mobile='$username'";
        }
        else if($docname=='viodoc')
        {
          $mysqliupdate="UPDATE npdocumentupload SET viodocpic='$pic', viodocstatus='Under Verification' WHERE mobile='$username'";
        }
        else if($docname=='insurance')
        {
          $mysqliupdate="UPDATE npdocumentupload SET insurancepic='$pic', insurancestatus='Under Verification' WHERE mobile='$username'";
        }
        else if($docname=='hackneypermit')
        {
          $mysqliupdate="UPDATE npdocumentupload SET hppic='$pic', hpstatus='Under Verification' WHERE mobile='$username'";
        }
        else if($docname=='ownershipproof')
        {
          $mysqliupdate="UPDATE npdocumentupload SET oppic='$pic', opstatus='Under Verification' WHERE mobile='$username'";
        }
        else if($docname=='policepermit')
        {
          $mysqliupdate="UPDATE npdocumentupload SET pppic='$pic', ppstatus='Under Verification' WHERE mobile='$username'";
        }
        else if($docname=='tintedglasspermit')
        {
          $mysqliupdate="UPDATE npdocumentupload SET tgppic='$pic', tgpstatus='Under Verification' WHERE mobile='$username'";
        }
    } 
    else 
    {
        if($docname=="drivinglicense")
        {
          $mysqliupdate="UPDATE npdocumentupload SET dlpic='$pic', dlstatus='Under Verification' WHERE username='$username'";
        }
        else if($docname=="tyreexpiration")
        {
          $mysqliupdate="UPDATE npdocumentupload SET tepic='$pic', testatus='Under Verification' WHERE username='$username'";
        }
        else if($docname=="fireextinguisher")
        {
          $mysqliupdate="UPDATE npdocumentupload SET fepic='$pic', festatus='Under Verification' WHERE username='$username'";
        }
        else if($docname=="viodoc")
        {
          $mysqliupdate="UPDATE npdocumentupload SET viodocpic='$pic', viodocstatus='Under Verification' WHERE username='$username'";
        }
        else if($docname=="insurance")
        {
          $mysqliupdate="UPDATE npdocumentupload SET insurancepic='$pic', insurancestatus='Under Verification' WHERE usename='$username'";
        }
        else if($docname=="hackneypermit")
        {
          $mysqliupdate="UPDATE npdocumentupload SET hppic='$pic', hpstatus='Under Verification' WHERE username='$username'";
        }
        else if($docname=="ownershipproof")
        {
          $mysqliupdate="UPDATE npdocumentupload SET oppic='$pic', opstatus='Under Verification' WHERE username='$username'";
        }
        else if($docname=="policepermit")
        {
          $mysqliupdate="UPDATE npdocumentupload SET pppic='$pic', ppstatus='Under Verification' WHERE username='$username'";
        }
        else if($docname=="tintedglasspermit")
        {
          $mysqliupdate="UPDATE npdocumentupload SET tgppic='$pic', tgpstatus='Under Verification' WHERE username='$username'";
        }
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