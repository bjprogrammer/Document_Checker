<?php
require_once 'config.php';
$conn = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);

$msg='';
if(!empty($_GET['code']) && isset($_GET['code']))
{
$code=mysqli_real_escape_string($conn,$_GET['code']);
$c=mysqli_query($conn,"SELECT username FROM npemailverification WHERE activation='$code'");

if(mysqli_num_rows($c) > 0)
{
$count=mysqli_query($conn,"SELECT username FROM npemailverification WHERE activation='$code' and emailverified='no'");

if(mysqli_num_rows($count) == 1)
{
mysqli_query($conn,"UPDATE npemailveriifcation SET emailverified='yes', created_at=Now() WHERE activation='$code'");
$msg="Your account is activated"; 
}
else
{
$msg ="Your account is already active, no need to activate again";
}

}
else
{
$msg ="Wrong activation code.";
}

}
?>
//HTML Part
<?php echo $msg; ?>