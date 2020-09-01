package com.mitocode.javaweb.springbootmybatis.cliente.infraestructure.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mitocode.javaweb.springbootmybatis.cliente.application.ClienteService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/reporte")
public class ClienteReporteController {
	
	private static final String PATH = "src/main/resources/reportes";
	
	@Autowired
	private ClienteService clienteService;

	@GetMapping("/reporte01")
	public void reporte01(HttpServletResponse response,
//			@RequestParam(value = "mode", defaultValue = "inline")
			@RequestParam(value = "mode", required = false)
			String mode) throws JRException, IOException {
		
		byte[] data = createPDF();
		
		strearmReport(response, data, "cliente.pdf", mode);
	}
	
	private byte[] createPDF() throws JRException {
		JasperReport report = JasperCompileManager.compileReport(PATH + "/Clientes.jrxml");
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("titulo", "Banking MitoCode");
		
		JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(clienteService.obtenerClientes());
		
		JasperPrint print = JasperFillManager.fillReport(report, parameters, source);
		
		return JasperExportManager.exportReportToPdf(print);
	}
	
	private void strearmReport(HttpServletResponse response, byte[] data, String name, String mode) throws IOException {
		if(mode != null && mode.equals("inline")) {
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "inline; filename= " + name);
		} else {
			response.setContentType("application/x-download");
			response.setHeader("Content-disposition", "attachment; filename= " + name);
		}
		
		response.setContentLength(data.length);
		response.getOutputStream().write(data);
		response.getOutputStream().flush();
	}
}
