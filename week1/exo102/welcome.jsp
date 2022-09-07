<%
// TODO : check if authenticated !
//   if not : show an error an a link to auth.jsp

Boolean isLogged = (Boolean) session.getAttribute("logged");
if(isLogged == null || !isLogged){
    out.print("<div>Please <a href=\"auth.jsp\">log in</a></div>");
    return;
}
%>
<h1>Welcome Admin !</h1>

[ <a href="auth?logout" style="color: red; text-decoration: none">DISCONNECT</a> ]
