package br.com.onlinebuscas.relatorios;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class ReportUtil
{
  public static final String TEMPLATE = "C:\\Users\\luis\\workspace_java\\onlinebuscas\\WebContent\\WEB-INF\\relatorios\\rel_cadastros_plano.jrxml";
  
  public StreamedContent geraRelatorio(HashMap parametrosRelatorio)
    throws Exception
  {
    StreamedContent arquivoRetorno = null;
    try
    {
      Connection conexao = getConexao();
      InputStream reportStream = getClass().getResourceAsStream("C:\\Users\\luis\\workspace_java\\onlinebuscas\\WebContent\\WEB-INF\\relatorios\\rel_cadastros_plano.jrxml");
      JasperDesign jd = JRXmlLoader.load(reportStream);
      JasperReport jr = JasperCompileManager.compileReport(jd);
      JasperPrint jp = JasperFillManager.fillReport(jr, parametrosRelatorio, conexao);
      
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      JRPdfExporter exporter = new JRPdfExporter();
      exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
      exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
      exporter.exportReport();
      
      ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
      
      arquivoRetorno = new DefaultStreamedContent(bais, "pdf", "rel_cadastros_empresas.pdf");
    }
    catch (JRException e)
    {
      e.printStackTrace();
      throw new Exception("N�o foi poss�vel gerar o relat�rio.", e);
    }
    catch (FileNotFoundException e)
    {
      throw new Exception("Arquivo do relat�rio n�o encontrado.", e);
    }
    return arquivoRetorno;
  }
  
  private Connection getConexao()
    throws Exception
  {
    Connection conexao = null;
    try
    {
      Context initContext = new InitialContext();
      Context envContext = (Context)initContext.lookup("java:/comp/env/");
      DataSource ds = (DataSource)envContext.lookup("jdbc/onlinebuscas");
      conexao = ds.getConnection();
    }
    catch (NamingException e)
    {
      throw new Exception("N�o foi poss�vel encontrar o nome da conex�o do banco.", e);
    }
    catch (SQLException e)
    {
      throw new Exception("Ocorreu um erro de SQL.", e);
    }
    return conexao;
  }
}
