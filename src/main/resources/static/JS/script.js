function makeActive(linkid)
{
	document.getElementById(linkid).style.color='red';
	//document.getElementById(linkid).style.backgroundColor='black';
}
function checkAmountLimit()
{
	if(document.getElementById("amount").value>20000){
		document.getElementById("error").innerHTML="Maximum 20000 is allowed per transaction";
		return false;
	}
	return true;
}
function checkSelfAccountNo()
{
	var oacn=document.getElementById("userAccountNo").innerHTML;
	var pacn=document.getElementById("accountNo").value;
	if(parseInt(oacn)==parseInt(pacn))
	{
		document.getElementById("error").innerHTML="This is your own account number";
		return false;
	}
	return true;
}