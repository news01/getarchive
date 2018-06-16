<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="<spring:url value="/static/js/jquery.js"/>" type="text/javascript" ></script>
<script src="<spring:url value="/static/js/jquery.qrcode.min.js"/>" type="text/javascript" ></script>
</head>
<body>
${content }
	<div id="qrcode" style="width:100%; text-align: center"></div>
				<script type="text/javascript">
		        function utf16to8(str) {  
		            var out, i, len, c;  
		            out = "";  
		            len = str.length;  
		            for (i = 0; i < len; i++) {  
		                c = str.charCodeAt(i);  
		                if ((c >= 0x0001) && (c <= 0x007F)) {  
		                    out += str.charAt(i);  
		                } else if (c > 0x07FF) {  
		                    out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));  
		                    out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));  
		                    out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));  
		                } else {  
		                    out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));  
		                    out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));  
		                }  
		            }  
		            return out;  
		        }  
		        $(function () {  
		            jQuery('#qrcode').qrcode(utf16to8('${content}'));  
		        })      
		    </script>  
</body>
</html>