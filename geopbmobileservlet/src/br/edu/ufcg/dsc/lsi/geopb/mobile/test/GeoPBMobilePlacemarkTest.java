package br.edu.ufcg.dsc.lsi.geopb.mobile.test;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.edu.ufcg.dsc.lsi.geopb.mobile.util.GeoPBMobilePlacemark;


public class GeoPBMobilePlacemarkTest {

	private GeoPBMobilePlacemark placemark1;
	private GeoPBMobilePlacemark placemark2;
	
	@Before
	public void setUp() {
		placemark1 = new GeoPBMobilePlacemark("201078", "00012007");
		placemark2 = new GeoPBMobilePlacemark("201078", "00152003");
	}
	
	@Test
	public void testEditBuildInformations() throws IOException {
		Assert.assertEquals("<table border=1><tr><td><b>Código da Unidade Gestora</b></td><td>\n"+
							"201078</td></tr><tr><td><b>Número da Obra</b></td><td>\n" +
							"00012007</td></tr><tr><td><b>Data Cadastro</b></td><td>\n" +
							"09/02/2007</td></tr><tr><td><b>Tipo Patrimônio</b></td><td>\n" +
							"N</td></tr><tr><td><b>Descrição Localização</b></td><td>\n" +
							"PEDRA PINTADA                                                                                                                                         " +
							"</td></tr><tr><td><b>Descrição Sucinta</b></td><td>\n" +
							"RECONSTRUÇÃO DE UNIDADES HABITACIONAIS                                                                                                                                                                                                                         " +
							"</td></tr><tr><td><b>Tipo Obra</b></td><td>Unidade habitacional</td></tr><tr><td><b>Tipo Categoria Obra</b></td><td>\n"+
							"Recuperação</td></tr><tr><td><b>Tempo Previsto</b></td><td>\n"+
							"122007</td></tr><tr><td><b>Data Início</b></td><td>09/02/2007</td></tr><tr><td><b>Data Conclusão</b></td><td>31/12/2007</td></tr><tr><td><b>Data Recebimento</b></td><td>\n"+
							"31/12/2007</td></tr><tr><td><b>Tipo Fonte Obra</b></td><td>Próprios</td></tr></table>", 
							placemark1.editBuildInformations());
		
		Assert.assertEquals("<table border=1><tr><td><b>Código da Unidade Gestora</b></td><td>\n"+
							"201078</td></tr><tr><td><b>Número da Obra</b></td><td>\n" +
							"00152003</td></tr><tr><td><b>Data Cadastro</b></td><td>\n" +
							"31/12/2003</td></tr><tr><td><b>Tipo Patrimônio</b></td><td>\n" +
							"S</td></tr><tr><td><b>Descrição Localização</b></td><td>\n" +
							"SITIO TIMBAUBA                                                                                                                                        " +
							"</td></tr><tr><td><b>Descrição Sucinta</b></td><td>\n" +
							"CONSTRUÇÃO DE UMA UNIDADE BASICA DE SAUDE                                                                                                                                                                                                                      " +
							"</td></tr><tr><td><b>Tipo Obra</b></td><td>Posto de saúde</td></tr><tr><td><b>Tipo Categoria Obra</b></td><td>\n"+
							"Construção</td></tr><tr><td><b>Tempo Previsto</b></td><td>\n"+
							"042004</td></tr><tr><td><b>Data Início</b></td><td>31/12/2003</td></tr><tr><td><b>Data Conclusão</b></td><td></td></tr><tr><td><b>Data Recebimento</b></td><td>\n"+
							"</td></tr><tr><td><b>Tipo Fonte Obra</b></td><td>Próprios</td></tr></table>", 
							placemark2.editBuildInformations());

	}
	
}
