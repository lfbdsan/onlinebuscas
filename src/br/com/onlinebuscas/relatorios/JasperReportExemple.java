package br.com.onlinebuscas.relatorios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class JasperReportExemple
{
  private static final String url = "jdbc:mysql://127.0.0.1/onlinebuscas";
  private static final String driver = "com.mysql.jdbc.Driver";
  private static final String login = "root";
  private static final String pwd = "";
  
  public void gerar(String layout)
    throws JRException, SQLException, ClassNotFoundException
  {
    JasperDesign desenho = JRXmlLoader.load(layout);
    

    JasperReport relatorio = JasperCompileManager.compileReport(desenho);
    

    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/onlinebuscas", "root", "");
    Statement stm = con.createStatement();
    String query = "select * from plano";
    ResultSet rs = stm.executeQuery(query);
    

    JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
    

    Map parametros = new HashMap();
    parametros.put("IdEmpresa", Integer.valueOf(10));
    JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, jrRS);
    

    JasperViewer viewer = new JasperViewer(impressao, true);
    viewer.show();
  }
  
  public static void main(String[] args)
  {
    try
    {
      new JasperReportExemple().gerar("C:\\Users\\luis\\workspace_java\\onlinebuscas\\WebContent\\WEB-INF\\relatorios\\rel_cadastros_planos.jrxml");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
