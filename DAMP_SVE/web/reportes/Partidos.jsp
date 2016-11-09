<%-- 
    Document   : Partidos
    Created on : 07-nov-2016, 20:56:54
    Author     : daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="net.sf.jasperreports.engine.*"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="java.sql.*"%>
<%
    Connection con;   
    Class.forName("org.postgresql.Driver");
    con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sistemavotacion?user=postgres&password=1234");
    File rf=new File (application.getRealPath("/reportes/Partidos.jasper"));
    Map parameters= new HashMap();
    parameters.put(null,null);
    byte[] bytes=JasperRunManager.runReportToPdf(rf.getPath(),parameters,con);
    response.setContentType("application.pdf");
    response.setContentLength(bytes.length);
    ServletOutputStream outp=response.getOutputStream();
    outp.write(bytes, 0, bytes.length);
    outp.flush();
    outp.close();

%>
