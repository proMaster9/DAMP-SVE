<%-- 
    Document   : VotantesDepto
    Created on : 07-nov-2016, 22:48:41
    Author     : daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="net.sf.jasperreports.engine.*"%>
<%@page import="net.sf.jasperreports.view.JasperViewer"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="java.sql.*"%>
<%@include file="VistaVotantesDepto.html" %>
<%
    Connection con;   
    Class.forName("org.postgresql.Driver");
    con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sistemavotacion?user=postgres&password=1234");
    File rf=new File (application.getRealPath("/reportes/VotantesDepto.jasper"));
    Map <String,Object> parameters=new HashMap<String, Object>();
    String valor=request.getParameter("txt1");
    parameters.put("depto", new String(valor));
    byte[] bytes=JasperRunManager.runReportToPdf(rf.getPath(),parameters,con);
    response.setContentType("application.pdf");
    response.setContentLength(bytes.length);
    ServletOutputStream outp=response.getOutputStream();
    outp.write(bytes, 0, bytes.length);
    outp.flush();
    outp.close();

%>
